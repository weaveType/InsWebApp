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
            resultVo.setCollaborationScore(parsedResult.getInt("collaborationScore"));
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
            
            // 상세 응답 스키마
            JSONObject responseSchema = new JSONObject();
            responseSchema.put("type", "object");
            JSONObject properties = new JSONObject();
            
            properties.put("language", new JSONObject().put("type", "string"));
            properties.put("dev_style", new JSONObject().put("type", "string").put("enum", new JSONArray().put("A").put("B")));
            properties.put("dev_score", new JSONObject().put("type", "integer").put("minimum", 50).put("maximum", 100));
            properties.put("collab_style", new JSONObject().put("type", "string").put("enum", new JSONArray().put("I").put("T")));
            properties.put("collab_score", new JSONObject().put("type", "integer").put("minimum", 50).put("maximum", 100));
            properties.put("type_code", new JSONObject().put("type", "string").put("pattern", "^[AB][IT]$"));
            properties.put("confidence", new JSONObject().put("type", "number").put("minimum", 0).put("maximum", 1));
            
            // analysis_details 스키마 (상세 분석 결과)
            JSONObject analysisDetails = new JSONObject();
            analysisDetails.put("type", "object");
            JSONObject detailsProperties = new JSONObject();
            
            // architect_indicators (A 타입인 경우)
            JSONObject architectIndicators = new JSONObject();
            architectIndicators.put("type", "object");
            JSONObject architectProps = new JSONObject();
            architectProps.put("abstraction_level", new JSONObject().put("type", "integer").put("minimum", 0).put("maximum", 100));
            architectProps.put("design_patterns", new JSONObject().put("type", "integer").put("minimum", 0).put("maximum", 100));
            architectProps.put("scalability_consideration", new JSONObject().put("type", "integer").put("minimum", 0).put("maximum", 100));
            architectProps.put("architecture_complexity", new JSONObject().put("type", "integer").put("minimum", 0).put("maximum", 100));
            architectIndicators.put("properties", architectProps);
            
            // builder_indicators (B 타입인 경우)
            JSONObject builderIndicators = new JSONObject();
            builderIndicators.put("type", "object");
            JSONObject builderProps = new JSONObject();
            builderProps.put("fast_implementation", new JSONObject().put("type", "integer").put("minimum", 0).put("maximum", 100));
            builderProps.put("prototype_patterns", new JSONObject().put("type", "integer").put("minimum", 0).put("maximum", 100));
            builderProps.put("simple_solutions", new JSONObject().put("type", "integer").put("minimum", 0).put("maximum", 100));
            builderProps.put("hardcoding_frequency", new JSONObject().put("type", "integer").put("minimum", 0).put("maximum", 100));
            builderIndicators.put("properties", builderProps);
            
            // team_indicators (T 타입인 경우)
            JSONObject teamIndicators = new JSONObject();
            teamIndicators.put("type", "object");
            JSONObject teamProps = new JSONObject();
            teamProps.put("collaborative_naming", new JSONObject().put("type", "integer").put("minimum", 0).put("maximum", 100));
            teamProps.put("detailed_documentation", new JSONObject().put("type", "integer").put("minimum", 0).put("maximum", 100));
            teamProps.put("small_commits", new JSONObject().put("type", "integer").put("minimum", 0).put("maximum", 100));
            teamProps.put("interface_design", new JSONObject().put("type", "integer").put("minimum", 0).put("maximum", 100));
            teamProps.put("code_review_patterns", new JSONObject().put("type", "integer").put("minimum", 0).put("maximum", 100));
            teamIndicators.put("properties", teamProps);
            
            // individual_indicators (I 타입인 경우)
            JSONObject individualIndicators = new JSONObject();
            individualIndicators.put("type", "object");
            JSONObject individualProps = new JSONObject();
            individualProps.put("personal_naming_style", new JSONObject().put("type", "integer").put("minimum", 0).put("maximum", 100));
            individualProps.put("independent_modules", new JSONObject().put("type", "integer").put("minimum", 0).put("maximum", 100));
            individualProps.put("large_commits", new JSONObject().put("type", "integer").put("minimum", 0).put("maximum", 100));
            individualProps.put("minimal_documentation", new JSONObject().put("type", "integer").put("minimum", 0).put("maximum", 100));
            individualIndicators.put("properties", individualProps);
            
            // 우세한 타입에 따라 해당 지표들만 포함
            detailsProperties.put("architect_indicators", architectIndicators);
            detailsProperties.put("builder_indicators", builderIndicators);
            detailsProperties.put("team_indicators", teamIndicators);
            detailsProperties.put("individual_indicators", individualIndicators);
            analysisDetails.put("properties", detailsProperties);
            
            properties.put("analysis_details", analysisDetails);
            
            responseSchema.put("properties", properties);
            // analysis_details를 필수 필드에 추가
            responseSchema.put("required", new JSONArray().put("language").put("dev_style").put("dev_score").put("collab_style").put("collab_score").put("type_code").put("confidence").put("analysis_details"));
            
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
            
            // 필수 필드 검증
            String[] requiredFields = {"dev_style", "dev_score", "collab_style", "collab_score", "type_code", "confidence", "analysis_details"};
            for (String field : requiredFields) {
                if (!analysisResult.has(field)) {
                    throw new Exception("응답에 필수 필드가 없습니다: " + field);
                }
            }
            
            // 새로운 데이터베이스 구조에 맞게 변환 (+-50 범위)
            JSONObject result = new JSONObject();
            
            // 타입 코드
            result.put("typeCode", analysisResult.getString("type_code"));
            
            // 점수 추출
            String devType = analysisResult.getString("dev_style");
            int devScore = analysisResult.getInt("dev_score");
            String collabType = analysisResult.getString("collab_style");
            int collabScore = analysisResult.getInt("collab_score");
            
            // 새로운 통합 점수 계산 (+-50 범위)
            // 개발 스타일 점수: A는 +, B는 -
            int developmentStyleScore;
            if ("A".equals(devType)) {
                // A(Architect)가 우세: 점수를 -50~+50 범위의 + 값으로 변환
                developmentStyleScore = (int) Math.round((devScore - 50) * 50.0 / 50.0);
                developmentStyleScore = Math.max(1, Math.min(50, developmentStyleScore)); // 1~50 범위
            } else {
                // B(Builder)가 우세: 점수를 -50~+50 범위의 - 값으로 변환
                developmentStyleScore = (int) Math.round((devScore - 50) * -50.0 / 50.0);
                developmentStyleScore = Math.min(-1, Math.max(-50, developmentStyleScore)); // -50~-1 범위
            }
            
            // 협업 성향 점수: S는 +, T는 -
            int collaborationScore_value;
            if ("I".equals(collabType)) {
                // I(Individual/Soloist)가 우세: 점수를 -50~+50 범위의 + 값으로 변환  
                collaborationScore_value = (int) Math.round((collabScore - 50) * 50.0 / 50.0);
                collaborationScore_value = Math.max(1, Math.min(50, collaborationScore_value)); // 1~50 범위
            } else {
                // T(Team)가 우세: 점수를 -50~+50 범위의 - 값으로 변환
                collaborationScore_value = (int) Math.round((collabScore - 50) * -50.0 / 50.0);
                collaborationScore_value = Math.min(-1, Math.max(-50, collaborationScore_value)); // -50~-1 범위
            }
            
            result.put("developmentStyleScore", developmentStyleScore);
            result.put("collaborationScore", collaborationScore_value);
            
            // 신뢰도 사용 (Gemini에서 직접 계산)
            double geminiConfidence = analysisResult.optDouble("confidence", 0.5);
            result.put("confidenceScore", Math.min(0.99, geminiConfidence)); // 최대 0.99로 제한
            
            // 상세 분석 결과 저장
            if (analysisResult.has("analysis_details")) {
                result.put("analysisDetails", analysisResult.getJSONObject("analysis_details").toString());
            }
            
            // 요약 정보 생성 (더 상세한 정보 포함)
            String language = analysisResult.optString("language", "Java");
            String summary = String.format("weaveType: %s (%s, 개발스타일: %s %d점, 협업선호: %s %d점, 신뢰도: %.1f%%)", 
                analysisResult.getString("type_code"), 
                language,
                devType, devScore,
                collabType, collabScore,
                geminiConfidence * 100);
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
     * 상세한 분석 프롬프트 생성
     */
    private String buildAnalysisPrompt(String language) {
        StringBuilder prompt = new StringBuilder();
        
        // 역할 및 목적 정의
        prompt.append("당신은 Developer Personality Analysis Expert입니다.\n");
        prompt.append("\" + language + \" 코드를 분석하여 개발자의 2차원 weaveType을 판단하는 전문가입니다.\n\n");
        
        prompt.append("** 목적 **\n");
        prompt.append("코딩 패턴, 구조적 사고, 협업 성향을 분석하여 2글자 Developer MBTI 코드(AI, AT, BI, BT)를 도출합니다.\n\n");
        
        // MBTI 차원 정의
        prompt.append("** MBTI 차원 **\n");
        prompt.append("1. 개발 스타일: A(Architect) vs B(Builder)\n");
        prompt.append("2. 협업 선호: I(Individual) vs T(Team)\n\n");
        
        // 코드 검사 포커스
        prompt.append("** 코드 검사 포커스 **\n");
        prompt.append("- 코드 구조와 아키텍처 패턴\n");
        prompt.append("- 변수, 함수, 클래스 명명 규칙\n");
        prompt.append("- 주석 및 문서화 스타일\n");
        prompt.append("- 에러 처리 및 예외 관리\n");
        prompt.append("- 테스트 코드 존재 여부 및 품질\n");
        prompt.append("- 사용된 라이브러리와 프레임워크\n");
        prompt.append("- 코드 중복 및 리팩토링 흔적\n");
        prompt.append("- 성능 최적화 시도\n");
        prompt.append("- 신기술이나 패턴 도입\n\n");
        
        // A vs B 차원 상세 분석 기준
        prompt.append("** A vs B 차원 분석 기준 **\n");
        prompt.append("A(Architect) 지표들:\n");
        prompt.append("1. 추상화 수준 (가중치 0.30):\n");
        prompt.append("   - 인터페이스, 추상 클래스, 제네릭 사용\n");
        prompt.append("   - 계산: (추상화_요소_수 / 전체_클래스_수) * 100\n");
        prompt.append("   - 범위: 0-100점\n\n");
        
        prompt.append("2. 디자인 패턴 (가중치 0.25):\n");
        prompt.append("   - Singleton, Factory, Observer, Strategy 패턴 사용\n");
        prompt.append("   - 계산: (디자인_패턴_사용_수 / 전체_클래스_수) * 100\n");
        prompt.append("   - 범위: 0-100점\n\n");
        
        prompt.append("3. 확장성 고려 (가중치 0.20):\n");
        prompt.append("   - 설정 분리, 플러그인 구조, 확장 포인트\n");
        prompt.append("   - 계산: (확장성_요소_수 / 전체_모듈_수) * 100\n");
        prompt.append("   - 범위: 0-100점\n\n");
        
        prompt.append("4. 아키텍처 복잡성 (가중치 0.25):\n");
        prompt.append("   - 계층 구조, 의존성 주입, 모듈 분리\n");
        prompt.append("   - 계산: (아키텍처_레이어_수 + 모듈_분리_정도) * 10\n");
        prompt.append("   - 범위: 0-100점\n\n");
        
        prompt.append("B(Builder) 지표들:\n");
        prompt.append("1. 빠른 구현 (가중치 0.25):\n");
        prompt.append("   - 단순하고 직접적인 구현, 빠른 프로토타입\n");
        prompt.append("   - 계산: (구현된_기능_수 / 전체_코드_라인_수) * 1000\n");
        prompt.append("   - 범위: 0-100점\n\n");
        
        prompt.append("2. 프로토타입 패턴 (가중치 0.20):\n");
        prompt.append("   - TODO, FIXME, 임시 코드 빈도\n");
        prompt.append("   - 계산: (임시_패턴_수 / 전체_라인_수) * 100\n");
        prompt.append("   - 범위: 0-100점\n\n");
        
        prompt.append("3. 단순 해결책 (가중치 0.15):\n");
        prompt.append("   - if-else 체인, 단순 루프, 직접 로직\n");
        prompt.append("   - 계산: 100 - (복잡_패턴_사용 / 전체_패턴) * 100\n");
        prompt.append("   - 범위: 0-100점\n\n");
        
        prompt.append("4. 하드코딩 빈도 (가중치 0.15):\n");
        prompt.append("   - 매직 넘버, 문자열 리터럴, 직접 설정값\n");
        prompt.append("   - 계산: (하드코딩_수 / 전체_값_참조) * 100\n");
        prompt.append("   - 범위: 0-100점\n\n");
        
        // I vs T 차원 상세 분석 기준
        prompt.append("** I vs T 차원 분석 기준 **\n");
        prompt.append("I(Individual) 지표들:\n");
        prompt.append("1. 개인적 명명 스타일 (가중치 0.20):\n");
        prompt.append("   - 축약된 변수명, 개인적 축약어, 불명확한 명명\n");
        prompt.append("   - 계산: (개인적_명명_수 / 전체_식별자_수) * 100\n");
        prompt.append("   - 범위: 0-100점\n\n");
        
        prompt.append("2. 독립적 모듈 (가중치 0.25):\n");
        prompt.append("   - 독립적이고 낮은 결합도의 모듈 설계\n");
        prompt.append("   - 계산: 100 - (모듈간_의존성 / 전체_모듈) * 10\n");
        prompt.append("   - 범위: 0-100점\n\n");
        
        prompt.append("3. 큰 커밋 (가중치 0.15):\n");
        prompt.append("   - 대용량 단위 작업 패턴\n");
        prompt.append("   - 계산: (큰_변경_패턴 / 전체_패턴) * 100\n");
        prompt.append("   - 범위: 0-100점\n\n");
        
        prompt.append("4. 최소 문서화 (가중치 0.20):\n");
        prompt.append("   - 간결한 주석, 자체 문서화 코드\n");
        prompt.append("   - 계산: 100 - (주석_라인 / 코드_라인) * 100\n");
        prompt.append("   - 범위: 0-100점\n\n");
        
        prompt.append("T(Team) 지표들:\n");
        prompt.append("1. 협업적 명명 (가중치 0.25):\n");
        prompt.append("   - 명확하고 직관적인 명명\n");
        prompt.append("   - 계산: (명확한_명명_수 / 전체_식별자_수) * 100\n");
        prompt.append("   - 범위: 0-100점\n\n");
        
        prompt.append("2. 상세 문서화 (가중치 0.30):\n");
        prompt.append("   - 풍부한 주석과 문서화\n");
        prompt.append("   - 계산: (주석_라인 / 코드_라인) * 100\n");
        prompt.append("   - 범위: 0-100점\n\n");
        
        prompt.append("3. 작은 커밋 (가중치 0.15):\n");
        prompt.append("   - 점진적이고 작은 단위 작업\n");
        prompt.append("   - 계산: (작은_변경_패턴 / 전체_패턴) * 100\n");
        prompt.append("   - 범위: 0-100점\n\n");
        
        prompt.append("4. 인터페이스 설계 (가중치 0.20):\n");
        prompt.append("   - 인터페이스 중심 설계\n");
        prompt.append("   - 계산: (인터페이스_정의 / 전체_클래스) * 100\n");
        prompt.append("   - 범위: 0-100점\n\n");
        
        prompt.append("5. 코드 리뷰 패턴 (가중치 0.10):\n");
        prompt.append("   - 리뷰 친화적 코드\n");
        prompt.append("   - 계산: (리뷰_친화_패턴 / 전체_패턴) * 100\n");
        prompt.append("   - 범위: 0-100점\n\n");
        
        // 점수 계산 방법론
        prompt.append("** 점수 계산 방법론 **\n");
        prompt.append("1단계: 각 지표 점수 계산 (0-100)\n");
        prompt.append("2단계: 지표 가중치를 적용하여 A_total, B_total, I_total, T_total 계산\n");
        prompt.append("3단계: 개발 스타일 = max(A_total, B_total)의 우세한 쪽\n");
        prompt.append("4단계: 협업 선호 = max(I_total, T_total)의 우세한 쪽\n");
        prompt.append("5단계: 차원별 신뢰도 = |우세_총점 - 상대_총점| / 100\n");
        prompt.append("6단계: 전체 신뢰도 = 두 차원 신뢰도의 평균\n\n");
        
        // Java 특화 고려사항
        prompt.append("** Java 특화 고려사항 **\n");
        prompt.append("- OOP 패턴 및 인터페이스 사용\n");
        prompt.append("- 패키지 계층구조\n");
        prompt.append("- Builder 지표: Main 메소드 중심 코드, 최소 클래스\n");
        prompt.append("- Architect 지표: 인터페이스, 추상 클래스, 디자인 패턴\n\n");
        
        // 분석 지시사항
        prompt.append("** 분석 지시사항 **\n");
        prompt.append("1. 전체 코드 구조와 아키텍처 이해\n");
        prompt.append("2. 주요 함수와 클래스 식별\n");
        prompt.append("3. 명명 규칙과 주석 검토\n");
        prompt.append("4. 에러 처리와 테스트 코드 검사\n");
        prompt.append("5. 라이브러리와 기술 스택 확인\n");
        prompt.append("6. A_vs_B와 I_vs_T 지표 정량화\n");
        prompt.append("7. 대조 점수를 계산하고 우세 타입 결정\n");
        prompt.append("8. 신뢰도 값 계산\n");
        prompt.append("9. 우세 점수만 반환\n");
        prompt.append("10. 최종 2글자 MBTI 코드 생성\n");
        prompt.append("11. 간소화된 JSON 결과 출력\n\n");
        
        // 출력 형식
        prompt.append("** 출력 형식 **\n");
        prompt.append("다음 JSON 형식으로만 응답하세요:\n");
        prompt.append("{\n");
        prompt.append("  \"language\": \"Java Spring Boot\",\n");
        prompt.append("  \"dev_style\": \"A\",\n");
        prompt.append("  \"dev_score\": 78,\n");
        prompt.append("  \"collab_style\": \"T\",\n");
        prompt.append("  \"collab_score\": 72,\n");
        prompt.append("  \"type_code\": \"AT\",\n");
        prompt.append("  \"confidence\": 0.75,\n");
        prompt.append("  \"analysis_details\": {\n");
        prompt.append("    \"architect_indicators\": {\n");
        prompt.append("      \"abstraction_level\": 85,\n");
        prompt.append("      \"design_patterns\": 70,\n");
        prompt.append("      \"scalability_consideration\": 75,\n");
        prompt.append("      \"architecture_complexity\": 80\n");
        prompt.append("    },\n");
        prompt.append("    \"team_indicators\": {\n");
        prompt.append("      \"collaborative_naming\": 80,\n");
        prompt.append("      \"detailed_documentation\": 75,\n");
        prompt.append("      \"small_commits\": 65,\n");
        prompt.append("      \"interface_design\": 70,\n");
        prompt.append("      \"code_review_patterns\": 60\n");
        prompt.append("    }\n");
        prompt.append("  }\n");
        prompt.append("}\n\n");
        
        // 주의사항
        prompt.append("** 주의사항 **\n");
        prompt.append("- 반드시 JSON 형식으로만 응답\n");
        prompt.append("- dev_style은 A 또는 B만\n");
        prompt.append("- collab_style은 I 또는 T만\n");
        prompt.append("- 점수는 50-100 사이의 정수\n");
        prompt.append("- type_code는 2글자 조합 (AI, AT, BI, BT)\n");
        prompt.append("- confidence는 0-1 사이의 소수\n");
        prompt.append("- 객관적이고 일관된 기준 적용\n");
        prompt.append("- 패턴의 질을 양보다 우세시\n");
        prompt.append("- 언어 규칙 존중\n");
        prompt.append("- 간결하고 집중적인 출력 유지\n");
        
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