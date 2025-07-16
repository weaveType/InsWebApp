package com.demo.proworks.domain.survey.web;

import com.demo.proworks.domain.survey.service.SurveyService;
import com.demo.proworks.domain.survey.vo.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.log.AppLog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 설문 관련 처리를 담당하는 컨트롤러
 * 
 * @author ProWorks
 * @since 2025/07/15
 */
@Controller
public class SurveyController {
    
    @Resource(name = "surveyServiceImpl")
    private SurveyService surveyService;
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    /**
     * 활성화된 설문 질문 목록 조회
     * 
     * @param request HttpServletRequest
     * @return 설문 질문 목록
     * @throws Exception
     */
    @ElService(key = "SV0001Questions")
    @RequestMapping(value = "SV0001Questions")
    @ElDescription(sub = "설문 질문 조회", desc = "개발자 MBTI 설문 질문 목록을 조회합니다.")
    public Map<String, Object> getQuestions(HttpServletRequest request) throws Exception {
        Map<String, Object> returnMap = new HashMap<>();
        
        try {
            AppLog.debug("설문 질문 목록 조회 요청");
            
            List<SurveyQuestionVo> questions = surveyService.getActiveQuestions();
            
            // 질문을 축별로 그룹핑
            Map<String, List<Map<String, Object>>> groupedQuestions = new HashMap<>();
            
            for (SurveyQuestionVo question : questions) {
                String axis = question.getAxis();
                if (!groupedQuestions.containsKey(axis)) {
                    groupedQuestions.put(axis, new ArrayList<>());
                }
                
                Map<String, Object> questionMap = new HashMap<>();
                questionMap.put("questionId", question.getQuestionId());
                questionMap.put("questionText", question.getQuestionText());
                questionMap.put("options", question.getOptions());
                
                groupedQuestions.get(axis).add(questionMap);
            }
            
            returnMap.put("questions", groupedQuestions);
            returnMap.put("totalCount", questions.size());
            
            AppLog.debug("설문 질문 조회 완료 - 총 " + questions.size() + "개");
            
            return returnMap;
            
        } catch (Exception e) {
            AppLog.error("설문 질문 조회 중 오류 발생", e);
            throw new Exception("설문 질문 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
    
    /**
     * 설문 응답 제출 및 MBTI 타입 계산
     * 
     * @param request HttpServletRequest
     * @return MBTI 계산 결과
     * @throws Exception
     */
    @ElService(key = "SV0001Submit")
    @RequestMapping(value = "SV0001Submit")
    @ElDescription(sub = "설문 제출", desc = "설문 응답을 제출하고 최종 MBTI 타입을 계산합니다.")
    public Map<String, Object> submitSurvey(HttpServletRequest request) throws Exception {
        Map<String, Object> returnMap = new HashMap<>();
        
        try {
            // JSON 데이터 읽기
            String jsonData = getJsonDataFromRequest(request);
            AppLog.debug("설문 제출 요청 수신: " + jsonData);
            
            // JSON 파싱
            JsonNode jsonNode = objectMapper.readTree(jsonData);
            
            // SurveySubmitVo 생성
            SurveySubmitVo submitVo = new SurveySubmitVo();
            
            if (jsonNode.has("userId")) {
                submitVo.setUserId(jsonNode.get("userId").asText());
            }
            if (jsonNode.has("typeId")) {
                submitVo.setTypeId(jsonNode.get("typeId").asLong());
            }
            
            // 응답 파싱
            List<QuestionAnswerVo> answers = new ArrayList<>();
            if (jsonNode.has("answers") && jsonNode.get("answers").isArray()) {
                for (JsonNode answerNode : jsonNode.get("answers")) {
                    QuestionAnswerVo answer = new QuestionAnswerVo();
                    answer.setQuestionId(answerNode.get("questionId").asLong());
                    answer.setAnswerValue(answerNode.get("answerValue").asInt());
                    answers.add(answer);
                }
            }
            submitVo.setAnswers(answers);
            
            AppLog.debug("설문 응답 파싱 완료 - 응답 수: " + answers.size());
            
            // 설문 제출 및 MBTI 계산
            MbtiCalculationResultVo result = surveyService.submitSurveyAndCalculateMbti(submitVo);
            
            // 결과 반환
            returnMap.put("typeId", result.getTypeId());
            returnMap.put("typeCode", result.getTypeCode());
            returnMap.put("typeName", result.getTypeName());
            returnMap.put("typeDescription", result.getTypeDescription());
            returnMap.put("scores", new HashMap<String, Object>() {{
                put("A_B", result.getABScore());
                put("R_I", result.getRIScore());
                put("S_T", result.getSTScore());
                put("D_F", result.getDFScore());
            }});
            
            AppLog.debug("설문 제출 완료 - 최종 타입: " + result.getTypeCode());
            
            return returnMap;
            
        } catch (Exception e) {
            AppLog.error("설문 제출 중 오류 발생", e);
            throw new Exception("설문 제출 처리 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
    
    /**
     * 사용자의 MBTI 타입 조회
     * 
     * @param request HttpServletRequest
     * @return MBTI 타입 정보
     * @throws Exception
     */
    @ElService(key = "SV0001Result")
    @RequestMapping(value = "SV0001Result")
    @ElDescription(sub = "MBTI 타입 조회", desc = "사용자의 최종 MBTI 타입을 조회합니다.")
    public Map<String, Object> getMbtiType(HttpServletRequest request) throws Exception {
        Map<String, Object> returnMap = new HashMap<>();
        
        try {
            // JSON 데이터 읽기
            String jsonData = getJsonDataFromRequest(request);
            JsonNode jsonNode = objectMapper.readTree(jsonData);
            
            Long userId = null;
            if (jsonNode.has("userId")) {
                userId = jsonNode.get("userId").asLong();
            } else if (jsonNode.has("typeId")) {
                // 하위 호환성을 위해 typeId를 userId로 사용
                userId = jsonNode.get("typeId").asLong();
            }
            
            AppLog.debug("MBTI 타입 조회 - 사용자ID: " + userId);
            
            MbtiCalculationResultVo result = surveyService.getUserMbtiType(userId);
            
            if (result == null) {
                returnMap.put("hasResult", false);
                AppLog.debug("MBTI 타입 정보가 없습니다 - 사용자ID: " + userId);
            } else {
                returnMap.put("hasResult", true);
                returnMap.put("typeId", result.getTypeId());
                returnMap.put("typeCode", result.getTypeCode());
                returnMap.put("typeName", result.getTypeName());
                returnMap.put("typeDescription", result.getTypeDescription());
                returnMap.put("scores", new HashMap<String, Object>() {{
                    put("A_B", result.getABScore());
                    put("R_I", result.getRIScore());
                    put("S_T", result.getSTScore());
                    put("D_F", result.getDFScore());
                }});
                
                AppLog.debug("MBTI 타입 조회 완료 - 타입: " + result.getTypeCode());
            }
            
            return returnMap;
            
        } catch (Exception e) {
            AppLog.error("MBTI 타입 조회 중 오류 발생", e);
            throw new Exception("MBTI 타입 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
    
    /**
     * 설문 완료 여부 확인
     * 
     * @param request HttpServletRequest
     * @return 설문 완료 여부
     * @throws Exception
     */
    @ElService(key = "SV0001Check")
    @RequestMapping(value = "SV0001Check")
    @ElDescription(sub = "설문 완료 확인", desc = "사용자의 설문 완료 여부를 확인합니다.")
    public Map<String, Object> checkSurveyCompleted(HttpServletRequest request) throws Exception {
        Map<String, Object> returnMap = new HashMap<>();
        
        try {
            // JSON 데이터 읽기
            String jsonData = getJsonDataFromRequest(request);
            JsonNode jsonNode = objectMapper.readTree(jsonData);
            
            Long typeId = null;
            if (jsonNode.has("typeId")) {
                typeId = jsonNode.get("typeId").asLong();
            }
            
            AppLog.debug("설문 완료 여부 확인 - 타입ID: " + typeId);
            
            boolean isCompleted = surveyService.isSurveyCompleted(typeId);
            returnMap.put("isCompleted", isCompleted);
            
            AppLog.debug("설문 완료 여부: " + isCompleted);
            
            return returnMap;
            
        } catch (Exception e) {
            AppLog.error("설문 완료 확인 중 오류 발생", e);
            throw new Exception("설문 완료 확인 중 오류가 발생했습니다: " + e.getMessage());
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
