package com.demo.proworks.domain.codeanalysis.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import com.demo.proworks.domain.codeanalysis.dao.CodeAnalysisDAO;
import com.demo.proworks.domain.codeanalysis.service.CodeAnalysisService;
import com.demo.proworks.domain.codeanalysis.vo.CodeAnalysisRequestVo;
import com.demo.proworks.domain.codeanalysis.vo.CodeAnalysisResultVo;

import com.inswave.elfw.exception.ElException;
import com.inswave.elfw.log.AppLog;

import org.json.JSONObject;
import org.json.JSONArray;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * @subject : 코드 분석 서비스 구현체
 * @description : Gemini API를 통한 코드 분석 서비스 구현 (REST API 직접 호출 방식)
 * @author : Inswave
 * @since : 2025/07/04
 */
@Service("codeAnalysisServiceImpl")
public class CodeAnalysisServiceImpl implements CodeAnalysisService {

    @Value("${gemini.api.key}")
    private String geminiApiKey;
    
    @Value("${gemini.model.name:gemini-2.0-flash-001}")
    private String modelName;
    
    @Resource(name = "codeAnalysisDAO")
    private CodeAnalysisDAO codeAnalysisDAO;

    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/{model}:generateContent?key={apiKey}";

    @Override
    public CodeAnalysisResultVo analyzeCode(CodeAnalysisRequestVo requestVo) throws Exception {
        try {
            AppLog.debug("=== 코드 분석 시작 (REST API 방식) ===");
            AppLog.debug("타입ID: " + requestVo.getTypeId());
            AppLog.debug("Gemini API 키 존재 여부: " + (geminiApiKey != null && !geminiApiKey.isEmpty()));
            AppLog.debug("모델명: " + modelName);
            
            // API 키 유효성 검사
            if (geminiApiKey == null || geminiApiKey.trim().isEmpty() || "YOUR_API_KEY".equals(geminiApiKey)) {
                throw new ElException("ERROR.BIZ.001", new String[]{"Gemini API 키가 설정되지 않았습니다. gemini.properties 파일을 확인하세요."});
            }
            
            // 분석할 코드 준비
            StringBuilder codeContent = new StringBuilder();
            codeContent.append("다음 Java 코드들을 분석해주세요:\n\n");
            
            if (requestVo.getModelFile() != null && !requestVo.getModelFile().isEmpty()) {
                codeContent.append("=== Model/Entity 파일 (").append(requestVo.getModelFileName()).append(") ===\n");
                codeContent.append(requestVo.getModelFile()).append("\n\n");
                AppLog.debug("Model 파일 추가됨: " + requestVo.getModelFileName());
            }
            
            if (requestVo.getControllerFile() != null && !requestVo.getControllerFile().isEmpty()) {
                codeContent.append("=== Controller 파일 (").append(requestVo.getControllerFileName()).append(") ===\n");
                codeContent.append(requestVo.getControllerFile()).append("\n\n");
                AppLog.debug("Controller 파일 추가됨: " + requestVo.getControllerFileName());
            }
            
            if (requestVo.getServiceFile() != null && !requestVo.getServiceFile().isEmpty()) {
                codeContent.append("=== Service 파일 (").append(requestVo.getServiceFileName()).append(") ===\n");
                codeContent.append(requestVo.getServiceFile()).append("\n\n");
                AppLog.debug("Service 파일 추가됨: " + requestVo.getServiceFileName());
            }
            
            if (requestVo.getRepositoryFile() != null && !requestVo.getRepositoryFile().isEmpty()) {
                codeContent.append("=== Repository/DAO 파일 (").append(requestVo.getRepositoryFileName()).append(") ===\n");
                codeContent.append(requestVo.getRepositoryFile()).append("\n\n");
                AppLog.debug("Repository 파일 추가됨: " + requestVo.getRepositoryFileName());
            }
            
            if (codeContent.toString().equals("다음 Java 코드들을 분석해주세요:\n\n")) {
                throw new ElException("ERROR.BIZ.002", new String[]{"분석할 코드가 없습니다. 하나 이상의 파일을 업로드하세요."});
            }
            
            String detectedLanguage = detectLanguage(codeContent.toString());
            AppLog.debug("Detected language: " + detectedLanguage);

            // 분석 프롬프트 생성
            String analysisPrompt = buildAnalysisPrompt(detectedLanguage) + "\n\n" + codeContent.toString();
            AppLog.debug("프롬프트 길이: " + analysisPrompt.length() + " 문자");
            
            // REST API 호출
            AppLog.debug("Gemini REST API 호출 시작...");
            String apiResponse = callGeminiAPI(analysisPrompt);
            AppLog.debug("Gemini REST API 호출 완료");
            
            // 응답 파싱
            AppLog.debug("응답 파싱 시작...");
            JSONObject parsedResult = parseGeminiResponse(apiResponse);
            AppLog.debug("응답 파싱 완료");
            
            // 결과 VO 생성
            CodeAnalysisResultVo resultVo = new CodeAnalysisResultVo();
            
            // typeId는 NULL로 설정 (독립적인 코드분석 테이블)
            resultVo.setTypeId(null);
            
            // 전체 분석 결과 저장 (새로운 JSON 구조)
            String typeCode = parsedResult.getString("typeCode");
            resultVo.setAnalysisResult(parsedResult.optString("fullAnalysis", parsedResult.toString()));
            resultVo.setTypeCode(typeCode);
            // 2차원 분석 결과를 새로운 통합 점수 필드에 매핑 (+-50 범위)
            resultVo.setDevelopmentStyleScore(parsedResult.getInt("developmentStyleScore"));
            resultVo.setDeveloperPreferenceScore(parsedResult.getInt("developerPreferenceScore"));
            resultVo.setConfidenceScore(parsedResult.getDouble("confidenceScore"));
            resultVo.setCreatedAt(new Date());
            resultVo.setLanguage(detectedLanguage);
            
            AppLog.debug("결과 VO 생성 완료 - 타입: " + resultVo.getTypeCode());
            
            // 결과 저장
            AppLog.debug("데이터베이스 저장 시작...");
            saveAnalysisResult(resultVo);
            AppLog.debug("데이터베이스 저장 완료");
            
            AppLog.debug("=== 코드 분석 완료 ===");
            
            return resultVo;
            
        } catch (ElException e) {
            AppLog.error("코드 분석 중 비즈니스 오류 발생", e);
            throw e;
        } catch (Exception e) {
            AppLog.error("코드 분석 중 예상치 못한 오류 발생: " + e.getClass().getName(), e);
            throw new ElException("ERROR.SYS.002", new String[]{"코드 분석 중 오류가 발생했습니다: " + e.getMessage()});
        }
    }
    
    /**
     * Gemini API 호출 (프롬프트 단순화 및 응답 안정성 개선)
     */
    private String callGeminiAPI(String prompt) throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();
            
            // API URL 생성
            String apiUrl = GEMINI_API_URL.replace("{model}", modelName).replace("{apiKey}", geminiApiKey);
            
            // 요청 본문 생성
            JSONObject requestBody = new JSONObject();
            JSONArray contents = new JSONArray();
            JSONObject content = new JSONObject();
            JSONArray parts = new JSONArray();
            JSONObject part = new JSONObject();
            part.put("text", prompt);
            parts.put(part);
            content.put("parts", parts);
            contents.put(content);
            requestBody.put("contents", contents);
            
            // Generation 설정 추가 - 더 안정적인 설정
            JSONObject generationConfig = new JSONObject();
            generationConfig.put("temperature", 0.3);  // 더 일관된 응답을 위해 낮춤
            generationConfig.put("topK", 20);          // 더 집중된 응답
            generationConfig.put("topP", 0.8);         // 더 예측 가능한 응답
            generationConfig.put("maxOutputTokens", 1024);  // 토큰 수 대폭 감소
            generationConfig.put("responseMimeType", "application/json");
            
            // 새로운 응답 스키마
            JSONObject responseSchema = new JSONObject();
            responseSchema.put("type", "object");
            JSONObject properties = new JSONObject();
            
            properties.put("type_code", new JSONObject().put("type", "string").put("pattern", "^[AB][RI]$"));
            properties.put("development_style_score", new JSONObject().put("type", "integer").put("minimum", -50).put("maximum", 50));
            properties.put("developer_preference_score", new JSONObject().put("type", "integer").put("minimum", -50).put("maximum", 50));
            properties.put("confidence_score", new JSONObject().put("type", "integer").put("minimum", 0).put("maximum", 100));
            properties.put("comment", new JSONObject().put("type", "string"));
            properties.put("language", new JSONObject().put("type", "string"));
            
            responseSchema.put("properties", properties);
            responseSchema.put("required", new JSONArray().put("type_code").put("development_style_score").put("developer_preference_score").put("confidence_score").put("comment").put("language"));
            
            generationConfig.put("responseSchema", responseSchema);
            requestBody.put("generationConfig", generationConfig);
            
            AppLog.debug("API 요청 URL: " + apiUrl);
            AppLog.debug("API 요청 본문 길이: " + requestBody.toString().length());
            
            // HTTP 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);
            
            // API 호출
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);
            
            AppLog.debug("API 응답 상태: " + response.getStatusCode());
            AppLog.debug("API 응답 길이: " + (response.getBody() != null ? response.getBody().length() : 0));
            
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                throw new Exception("Gemini API 호출 실패: " + response.getStatusCode());
            }
            
        } catch (Exception e) {
            AppLog.error("Gemini REST API 호출 중 오류 발생", e);
            throw new Exception("Gemini API 호출 중 오류가 발생했습니다: " + e.getMessage(), e);
        }
    }
    
    /**
     * Gemini API 응답 파싱 (단순화 및 안정성 개선)
     */
    private JSONObject parseGeminiResponse(String response) throws Exception {
        try {
            // 응답 길이 체크
            if (response == null || response.trim().isEmpty()) {
                throw new Exception("Gemini API에서 빈 응답을 받았습니다.");
            }
            
            AppLog.debug("Gemini API 원본 응답 길이: " + response.length());
            AppLog.debug("Gemini API 원본 응답 (처음 500자): " + response.substring(0, Math.min(500, response.length())));
            
            JSONObject jsonResponse = new JSONObject(response);
            
            // 응답 구조 검증
            if (!jsonResponse.has("candidates")) {
                throw new Exception("Gemini API 응답에 candidates 필드가 없습니다.");
            }
            
            JSONArray candidates = jsonResponse.getJSONArray("candidates");
            if (candidates.length() == 0) {
                throw new Exception("Gemini API에서 결과를 반환하지 않았습니다.");
            }
            
            JSONObject candidate = candidates.getJSONObject(0);
            
            // finishReason 확인
            if (candidate.has("finishReason")) {
                String finishReason = candidate.getString("finishReason");
                if (!"STOP".equals(finishReason)) {
                    AppLog.warn("응답이 완전하지 않을 수 있습니다. finishReason: " + finishReason);
                }
            }
            
            if (!candidate.has("content")) {
                throw new Exception("Gemini API 응답에 content 필드가 없습니다.");
            }
            
            JSONObject content = candidate.getJSONObject("content");
            JSONArray parts = content.getJSONArray("parts");
            
            if (parts.length() == 0) {
                throw new Exception("Gemini API 응답에 텍스트가 없습니다.");
            }
            
            String resultText = parts.getJSONObject(0).getString("text");
            AppLog.debug("Gemini API 응답 텍스트 길이: " + resultText.length());
            AppLog.debug("Gemini API 응답 텍스트: " + resultText);
            
            // JSON 형식 검증 및 파싱
            resultText = resultText.trim();
            if (!resultText.startsWith("{") || !resultText.endsWith("}")) {
                // JSON이 아닌 경우 래핑 시도
                AppLog.warn("응답이 JSON 형식이 아닙니다. 텍스트에서 JSON 추출을 시도합니다.");
                int startIdx = resultText.indexOf("{");
                int endIdx = resultText.lastIndexOf("}");
                if (startIdx != -1 && endIdx != -1 && endIdx > startIdx) {
                    resultText = resultText.substring(startIdx, endIdx + 1);
                } else {
                    throw new Exception("응답에서 유효한 JSON을 찾을 수 없습니다.");
                }
            }
            
            JSONObject analysisResult;
            try {
                analysisResult = new JSONObject(resultText);
            } catch (Exception e) {
                AppLog.error("JSON 파싱 실패. 응답 텍스트: " + resultText);
                throw new Exception("API 응답을 JSON으로 파싱할 수 없습니다: " + e.getMessage());
            }
            
            // 필수 필드 검증 (새로운 형식에 맞게)
            String[] requiredFields = {"type_code", "development_style_score", "developer_preference_score", "confidence_score"};
            for (String field : requiredFields) {
                if (!analysisResult.has(field)) {
                    throw new Exception("응답에 필수 필드가 없습니다: " + field);
                }
            }
            
            // 새로운 데이터베이스 구조에 맞게 변환 (이미 +-50 범위)
            JSONObject result = new JSONObject();
            
            // 타입 코드
            result.put("typeCode", analysisResult.getString("type_code"));
            
            // 점수는 이미 -50~+50 범위로 제공됨
            result.put("developmentStyleScore", analysisResult.getInt("development_style_score"));
            result.put("developerPreferenceScore", analysisResult.getInt("developer_preference_score"));
            
            // 신뢰도는 0-100 범위를 0.0-1.0으로 변환
            double confidenceScore = analysisResult.getInt("confidence_score") / 100.0;
            result.put("confidenceScore", Math.min(0.99, confidenceScore)); // 최대 0.99로 제한
            
            // 언어 정보
            String language = analysisResult.optString("language", "java");
            
            // 요약 정보 생성
            String comment = analysisResult.optString("comment", "코드 분석 완료");
            String summary = String.format("weaveType: %s (%s, 신뢰도: %.0f%%) - %s", 
                analysisResult.getString("type_code"), 
                language,
                confidenceScore * 100,
                comment);
            result.put("summary", summary);
            
            // 전체 응답도 저장
            result.put("fullAnalysis", analysisResult.toString());
            
            AppLog.debug("파싱 완료 - 타입: " + result.getString("typeCode") + ", 신뢰도: " + result.getDouble("confidenceScore"));
            
            return result;
            
        } catch (Exception e) {
            AppLog.error("Gemini API 응답 파싱 중 오류", e);
            throw new Exception("Gemini API 응답 파싱에 실패했습니다: " + e.getMessage(), e);
        }
    }
    
    @Override
    public void saveAnalysisResult(CodeAnalysisResultVo resultVo) throws Exception {
        try {
            int result = codeAnalysisDAO.insertCodeAnalysisResult(resultVo);
            if (result <= 0) {
                throw new Exception("코드 분석 결과 저장에 실패했습니다.");
            }
            AppLog.debug("코드 분석 결과 저장 완료 - ID: " + resultVo.getAnalysisId());
        } catch (ElException e) {
            AppLog.error("코드 분석 결과 저장 중 DAO 오류 발생", e);
            throw new Exception("코드 분석 결과 저장 중 데이터베이스 오류가 발생했습니다: " + e.getMessage(), e);
        }
    }
    
    @Override
    public CodeAnalysisResultVo getAnalysisResult(Long typeId) throws Exception {
        try {
            CodeAnalysisResultVo result = codeAnalysisDAO.selectLatestCodeAnalysisResult(typeId);
            if (result != null) {
                AppLog.debug("코드 분석 결과 조회 완료 - 타입ID: " + typeId);
            } else {
                AppLog.debug("코드 분석 결과가 없습니다 - 타입ID: " + typeId);
            }
            return result;
        } catch (ElException e) {
            AppLog.error("코드 분석 결과 조회 중 DAO 오류 발생", e);
            throw new Exception("코드 분석 결과 조회 중 데이터베이스 오류가 발생했습니다: " + e.getMessage(), e);
        }
    }
    
    /**
     * 새로운 분석 프롬프트 생성 (developer_preference_score 기반)
     */
    private String buildAnalysisPrompt(String language) {
        StringBuilder prompt = new StringBuilder();
        
        // 새로운 프롬프트 JSON 구조를 문자열로 포함
        prompt.append("{\n");
        prompt.append("  \"task\": \"developer_trait_analysis\",\n");
        prompt.append("  \"description\": \"Analyze developer traits based on their code to determine their development style and preferences\",\n");
        prompt.append("  \"note\": \"Please make sure to respond to this request strictly in the following JSON format. Do not say anything outside of the specified format.\",\n");
        prompt.append("  \"trait_definitions\": {\n");
        prompt.append("    \"development_style\": {\n");
        prompt.append("      \"B\": {\n");
        prompt.append("        \"name\": \"Builder\",\n");
        prompt.append("        \"description\": \"Prefers quick implementation and practicality\",\n");
        prompt.append("        \"indicators\": [\n");
        prompt.append("          \"Simple, straightforward solutions\",\n");
        prompt.append("          \"Minimal abstractions\",\n");
        prompt.append("          \"Focus on working code over perfect architecture\",\n");
        prompt.append("          \"Pragmatic approach to problem-solving\",\n");
        prompt.append("          \"Direct implementation without over-engineering\"\n");
        prompt.append("        ]\n");
        prompt.append("      },\n");
        prompt.append("      \"A\": {\n");
        prompt.append("        \"name\": \"Architect\",\n");
        prompt.append("        \"description\": \"Pursues systematic and perfect design\",\n");
        prompt.append("        \"indicators\": [\n");
        prompt.append("          \"Well-structured code with clear separation of concerns\",\n");
        prompt.append("          \"Use of design patterns\",\n");
        prompt.append("          \"Comprehensive error handling\",\n");
        prompt.append("          \"Detailed documentation\",\n");
        prompt.append("          \"Scalable and maintainable architecture\"\n");
        prompt.append("        ]\n");
        prompt.append("      }\n");
        prompt.append("    },\n");
        prompt.append("    \"developer_preference\": {\n");
        prompt.append("      \"R\": {\n");
        prompt.append("        \"name\": \"Refactor\",\n");
        prompt.append("        \"description\": \"Prefers improving and optimizing existing code\",\n");
        prompt.append("        \"indicators\": [\n");
        prompt.append("          \"Code refactoring patterns\",\n");
        prompt.append("          \"Performance optimizations\",\n");
        prompt.append("          \"Code cleanup and simplification\",\n");
        prompt.append("          \"Improving readability\",\n");
        prompt.append("          \"Fixing technical debt\"\n");
        prompt.append("        ]\n");
        prompt.append("      },\n");
        prompt.append("      \"I\": {\n");
        prompt.append("        \"name\": \"Innovator\",\n");
        prompt.append("        \"description\": \"Enjoys daring to explore new technologies\",\n");
        prompt.append("        \"indicators\": [\n");
        prompt.append("          \"Use of cutting-edge technologies\",\n");
        prompt.append("          \"Experimental approaches\",\n");
        prompt.append("          \"Integration of new libraries/frameworks\",\n");
        prompt.append("          \"Creative problem-solving\",\n");
        prompt.append("          \"Early adoption of new features\"\n");
        prompt.append("        ]\n");
        prompt.append("      }\n");
        prompt.append("    }\n");
        prompt.append("  },\n");
        prompt.append("  \"analysis_instructions\": {\n");
        prompt.append("    \"steps\": [\n");
        prompt.append("      \"Examine code structure and organization\",\n");
        prompt.append("      \"Identify design patterns and architectural choices\",\n");
        prompt.append("      \"Analyze technology choices and dependencies\",\n");
        prompt.append("      \"Evaluate code style and documentation\",\n");
        prompt.append("      \"Assess problem-solving approaches\"\n");
        prompt.append("    ],\n");
        prompt.append("    \"scoring_guidelines\": {\n");
        prompt.append("      \"development_style_score\": {\n");
        prompt.append("        \"range\": [-50, 50],\n");
        prompt.append("        \"interpretation\": {\n");
        prompt.append("          \"-50 to -21\": \"Strong Builder (B) tendency\",\n");
        prompt.append("          \"-20 to -1\": \"Moderate Builder (B) tendency\",\n");
        prompt.append("          \"0\": \"Balanced between Builder and Architect\",\n");
        prompt.append("          \"1 to 20\": \"Moderate Architect (A) tendency\",\n");
        prompt.append("          \"21 to 50\": \"Strong Architect (A) tendency\"\n");
        prompt.append("        }\n");
        prompt.append("      },\n");
        prompt.append("      \"developer_preference_score\": {\n");
        prompt.append("        \"range\": [-50, 50],\n");
        prompt.append("        \"interpretation\": {\n");
        prompt.append("          \"-50 to -21\": \"Strong Innovator (I) tendency\",\n");
        prompt.append("          \"-20 to -1\": \"Moderate Innovator (I) tendency\",\n");
        prompt.append("          \"0\": \"Balanced between Refiner and Innovator\",\n");
        prompt.append("          \"1 to 20\": \"Moderate Refiner (R) tendency\",\n");
        prompt.append("          \"21 to 50\": \"Strong Refiner (R) tendency\"\n");
        prompt.append("        }\n");
        prompt.append("      },\n");
        prompt.append("      \"confidence_score\": {\n");
        prompt.append("        \"range\": [0, 100],\n");
        prompt.append("        \"factors\": [\n");
        prompt.append("          \"Amount of code analyzed\",\n");
        prompt.append("          \"Variety of code patterns observed\",\n");
        prompt.append("          \"Consistency of traits across different code sections\",\n");
        prompt.append("          \"Clarity of trait indicators\"\n");
        prompt.append("        ]\n");
        prompt.append("      }\n");
        prompt.append("    }\n");
        prompt.append("  },\n");
        prompt.append("  \"output_format\": {\n");
        prompt.append("    \"analysis_result\": {\n");
        prompt.append("      \"type_code\": \"string (BR, BI, AR, AI)\",\n");
        prompt.append("      \"development_style_score\": \"integer (-50 to 50)\",\n");
        prompt.append("      \"developer_preference_score\": \"integer (-50 to 50)\",\n");
        prompt.append("      \"confidence_score\": \"integer (0 to 100)\",\n");
        prompt.append("      \"comment\": \"string (Korean language preferred)\",\n");
        prompt.append("      \"language\": \"string (java, python, javascript, etc.)\"\n");
        prompt.append("    }\n");
        prompt.append("  }\n");
        prompt.append("}\n\n");
        
        prompt.append("Based on the above analysis framework, analyze the provided " + language + " code and return ONLY a JSON response in this exact format:\n\n");
        prompt.append("{\n");
        prompt.append("  \"type_code\": \"BR\",\n");
        prompt.append("  \"development_style_score\": -25,\n");
        prompt.append("  \"developer_preference_score\": 15,\n");
        prompt.append("  \"confidence_score\": 75,\n");
        prompt.append("  \"comment\": \"실용적이고 빠른 구현을 선호하며, 기존 코드 개선에 집중하는 개발자\",\n");
        prompt.append("  \"language\": \"java\"\n");
        prompt.append("}\n\n");
        
        return prompt.toString();
    }

    private String detectLanguage(String code) {
        if (code.contains("import java.")) return "Java";
        if (code.contains("function ") || code.contains("console.log")) return "JavaScript";
        if (code.contains("def ") || code.contains("print(")) return "Python";
        // Add more detections as needed
        return "Unknown";
    }
} 