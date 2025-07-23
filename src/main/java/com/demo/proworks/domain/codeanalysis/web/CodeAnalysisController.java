package com.demo.proworks.domain.codeanalysis.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.domain.codeanalysis.service.CodeAnalysisService;
import com.demo.proworks.domain.codeanalysis.vo.CodeAnalysisRequestVo;
import com.demo.proworks.domain.codeanalysis.vo.CodeAnalysisResultVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.log.AppLog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @subject : 코드 분석 관련 처리를 담당하는 컨트롤러
 * @description : Gemini API를 통한 코드 분석 관련 처리를 담당하는 컨트롤러
 * @author : Inswave
 * @since : 2025/07/04
 */
@Controller
public class CodeAnalysisController {

    /** CodeAnalysisService */
    @Resource(name = "codeAnalysisServiceImpl")
    private CodeAnalysisService codeAnalysisService;

    /**
     * 코드 분석을 수행합니다.
     * 
     * @param request 요청 정보 HttpServletRequest
     * @return 코드 분석 결과
     * @throws Exception
     */
    @ElService(key = "CA0001Analyze")
    @RequestMapping(value = "CA0001Analyze")
    @ElDescription(sub = "코드 분석 수행", desc = "업로드된 Java 코드를 Gemini API로 분석하여 개발자 타입을 분석합니다.")
    public Map<String, Object> analyzeCode(HttpServletRequest request) throws Exception {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        
        try {
            AppLog.debug("코드 분석 요청 수신");
            
            // HttpServletRequest에서 직접 JSON 데이터 읽기
            String jsonData = getJsonDataFromRequest(request);
            AppLog.debug("수신된 JSON 데이터: " + jsonData);
            
            // JSON 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonData);
            
            // CodeAnalysisRequestVo 생성 및 데이터 설정
            CodeAnalysisRequestVo requestVo = new CodeAnalysisRequestVo();
            
            if (jsonNode.has("userId")) {
                requestVo.setTypeId(jsonNode.get("userId").asLong());
            }
            if (jsonNode.has("modelFile")) {
                requestVo.setModelFile(jsonNode.get("modelFile").asText());
            }
            if (jsonNode.has("controllerFile")) {
                requestVo.setControllerFile(jsonNode.get("controllerFile").asText());
            }
            if (jsonNode.has("serviceFile")) {
                requestVo.setServiceFile(jsonNode.get("serviceFile").asText());
            }
            if (jsonNode.has("repositoryFile")) {
                requestVo.setRepositoryFile(jsonNode.get("repositoryFile").asText());
            }
            if (jsonNode.has("modelFileName")) {
                requestVo.setModelFileName(jsonNode.get("modelFileName").asText());
            }
            if (jsonNode.has("controllerFileName")) {
                requestVo.setControllerFileName(jsonNode.get("controllerFileName").asText());
            }
            if (jsonNode.has("serviceFileName")) {
                requestVo.setServiceFileName(jsonNode.get("serviceFileName").asText());
            }
            if (jsonNode.has("repositoryFileName")) {
                requestVo.setRepositoryFileName(jsonNode.get("repositoryFileName").asText());
            }
            
            AppLog.debug("파싱된 RequestVo: " + requestVo.toString());
            AppLog.debug("코드 분석 시작 - 타입ID: " + requestVo.getTypeId());
            
            // 코드 분석 수행
            CodeAnalysisResultVo result = codeAnalysisService.analyzeCode(requestVo);
            
            AppLog.debug("코드 분석 완료 - 결과 타입: " + result.getTypeCode());
            
            // 결과를 Map으로 변환
            returnMap.put("analysisId", result.getAnalysisId());
            returnMap.put("typeId", result.getTypeId());
            returnMap.put("analysisResult", result.getAnalysisResult());
            returnMap.put("typeCode", result.getTypeCode());
            returnMap.put("developmentStyleScore", result.getDevelopmentStyleScore());
            returnMap.put("developerPreferenceScore", result.getDeveloperPreferenceScore());
            returnMap.put("confidenceScore", result.getConfidenceScore());
            returnMap.put("createdAt", result.getCreatedAt());
            returnMap.put("language", result.getLanguage());
            
            return returnMap;
            
        } catch (Exception e) {
            AppLog.error("코드 분석 중 오류 발생", e);
            throw new Exception("코드 분석 처리 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    /**
     * 개발자 유형의 최신 코드 분석 결과를 조회합니다.
     * 
     * @param request 요청 정보 HttpServletRequest
     * @return 코드 분석 결과
     * @throws Exception
     */
    @ElService(key = "CA0001Result")
    @RequestMapping(value = "CA0001Result")
    @ElDescription(sub = "코드 분석 결과 조회", desc = "개발자 유형의 최신 코드 분석 결과를 조회합니다.")
    public Map<String, Object> getAnalysisResult(HttpServletRequest request) throws Exception {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        
        try {
            // HttpServletRequest에서 직접 JSON 데이터 읽기
            String jsonData = getJsonDataFromRequest(request);
            
            // JSON 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonData);
            
            Long typeId = null;
            if (jsonNode.has("userId")) {
                typeId = jsonNode.get("userId").asLong();
            }
            
            AppLog.debug("코드 분석 결과 조회 - 사용자ID: " + typeId);
            
            CodeAnalysisResultVo result = codeAnalysisService.getAnalysisResult(typeId);
            
            if (result == null) {
                AppLog.debug("분석 결과가 없습니다 - 타입ID: " + typeId);
                returnMap.put("hasResult", false);
            } else {
                AppLog.debug("분석 결과 조회 완료 - 타입: " + result.getTypeCode());
                
                // 결과를 Map으로 변환
                returnMap.put("hasResult", true);
                returnMap.put("analysisId", result.getAnalysisId());
                returnMap.put("typeId", result.getTypeId());
                returnMap.put("analysisResult", result.getAnalysisResult());
                returnMap.put("typeCode", result.getTypeCode());
                returnMap.put("developmentStyleScore", result.getDevelopmentStyleScore());
                returnMap.put("developerPreferenceScore", result.getDeveloperPreferenceScore());
                returnMap.put("confidenceScore", result.getConfidenceScore());
                returnMap.put("createdAt", result.getCreatedAt());
            }
            
            return returnMap;
            
        } catch (Exception e) {
            AppLog.error("코드 분석 결과 조회 중 오류 발생", e);
            throw new Exception("코드 분석 결과 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
    
    /**
     * HttpServletRequest에서 JSON 데이터를 읽어옵니다.
     * 
     * @param request HttpServletRequest
     * @return JSON 문자열
     * @throws IOException
     */
    private String getJsonDataFromRequest(HttpServletRequest request) throws IOException {
        StringBuilder jsonData = new StringBuilder();
        String line;
        
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }
        }
        
        return jsonData.toString();
    }
} 