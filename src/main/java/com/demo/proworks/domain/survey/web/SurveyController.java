package com.demo.proworks.domain.survey.web;

import com.demo.proworks.cmmn.ProworksUserHeader;
import com.demo.proworks.domain.survey.service.SurveyService;
import com.demo.proworks.domain.survey.vo.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.core.UserHeader;
import com.inswave.elfw.log.AppLog;
import com.inswave.elfw.util.ControllerContextUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

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
    
    // ObjectMapper를 static으로 설정하여 안전하게 처리
    private static final ObjectMapper objectMapper;
    
    static {
        objectMapper = new ObjectMapper();
        // ProWorks5 프레임워크의 elExcludeFilter를 위한 FilterProvider 설정
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter("elExcludeFilter", SimpleBeanPropertyFilter.serializeAll());
        filterProvider.setFailOnUnknownId(false); // 알 수 없는 필터 ID에 대해 실패하지 않도록 설정
        objectMapper.setFilterProvider(filterProvider);
        AppLog.debug("SurveyController ObjectMapper FilterProvider 설정 완료");
    }
    
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
            // === 다양한 방법으로 사용자 정보 가져오기 시도 ===
            Long actualUserId = null;
            AppLog.debug("=== 사용자 정보 가져오기 시도 시작 ===");
            
            // 방법1: ControllerContextUtil.getUserHeader() 시도 (SessionCheckController 방식)
            try {
                ProworksUserHeader userHeader = (ProworksUserHeader) ControllerContextUtil.getUserHeader();
                if (userHeader != null) {
                    AppLog.debug("방법1 성공 - ProworksUserHeader 획득");
                    AppLog.debug("사용자 정보 - userId: " + userHeader.getUserId() + ", accountId: " + userHeader.getAccountId());
                    
                    // accountId가 실제 DB의 숫자 user_id
                    if (userHeader.getAccountId() > 0) {
                        actualUserId = (long) userHeader.getAccountId();
                        AppLog.debug("방법1 성공 - accountId 사용: " + actualUserId);
                    } else {
                        AppLog.debug("방법1 실패 - accountId가 유효하지 않음: " + userHeader.getAccountId());
                    }
                } else {
                    AppLog.debug("방법1 실패 - ProworksUserHeader가 null");
                }
            } catch (ClassCastException e) {
                AppLog.warn("방법1 실패 - ProworksUserHeader 캐스팅 실패: " + e.getMessage());
            } catch (Exception e) {
                AppLog.warn("방법1 실패 - UserHeader 처리 중 오류: " + e.getMessage());
            }
            
            // 방법 2: HttpSession에서 userHeader 객체 가져오기
            if (actualUserId == null) {
                try {
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        AppLog.debug("방법2 시도 - HttpSession 존재, ID: " + session.getId());
                        
                        // 다양한 세션 속성에서 사용자 ID 찾기
                        AppLog.debug("=== 세션 속성 전체 탐색 ===");
                        java.util.Enumeration<String> attributeNames = session.getAttributeNames();
                        while (attributeNames.hasMoreElements()) {
                            String attrName = attributeNames.nextElement();
                            Object attrValue = session.getAttribute(attrName);
                            AppLog.debug("세션 속성: " + attrName + " = " + attrValue);
                        }
                        
                        // 방법2a: userId 직접 조회
                        Integer sessionUserId = (Integer) session.getAttribute("userId");
                        if (sessionUserId != null) {
                            actualUserId = sessionUserId.longValue();
                            AppLog.debug("방법2a 성공 - HttpSession userId: " + actualUserId);
                        } else {
                            // 방법2b: userHeader에서 accountId 가져오기
                            try {
                                ProworksUserHeader sessionUserHeader = (ProworksUserHeader) session.getAttribute("userHeader");
                                if (sessionUserHeader != null && sessionUserHeader.getAccountId() > 0) {
                                    actualUserId = (long) sessionUserHeader.getAccountId();
                                    AppLog.debug("방법2b 성공 - HttpSession userHeader.accountId: " + actualUserId);
                                } else {
                                    AppLog.debug("방법2b 실패 - userHeader null이거나 accountId 없음");
                                }
                            } catch (ClassCastException e) {
                                AppLog.debug("방법2b 실패 - userHeader 캐스팅 실패: " + e.getMessage());
                            }
                        }
                    } else {
                        AppLog.warn("방법2 실패 - HttpSession이 null");
                    }
                } catch (Exception e) {
                    AppLog.warn("방법2 실패 - HttpSession 오류: " + e.getMessage());
                }
            }
            
            // 방법 3: 쿠키에서 직접 파싱 (안전한 방식)
            if (actualUserId == null) {
                try {
                    javax.servlet.http.Cookie[] cookies = request.getCookies();
                    if (cookies != null) {
                        for (javax.servlet.http.Cookie cookie : cookies) {
                            if ("userInfo".equals(cookie.getName())) {
                                try {
                                    // URL 디코딩 시도 (강력한 방식)
                                    String userInfoJson = cookie.getValue();
                                    AppLog.debug("방법3 시도 - 원본 쿠키 값: " + userInfoJson);
                                    
                                    // URL 인코딩된 데이터 강제 디코딩
                                    if (userInfoJson.startsWith("%7B")) {
                                        // %7B는 {를 의미 - 확실히 URL 인코딩됨
                                        userInfoJson = java.net.URLDecoder.decode(userInfoJson, "UTF-8");
                                        AppLog.debug("방법3 - URL 디코딩 성공: " + userInfoJson);
                                    }
                                    
                                    ObjectMapper cookieMapper = new ObjectMapper();
                                    JsonNode userInfoNode = cookieMapper.readTree(userInfoJson);
                                    if (userInfoNode.has("accountId")) {
                                        actualUserId = userInfoNode.get("accountId").asLong();
                                        AppLog.debug("방법3 성공 - 쿠키에서 accountId: " + actualUserId);
                                    } else {
                                        AppLog.debug("방법3 실패 - 쿠키에 accountId 없음");
                                        AppLog.debug("쿠키 JSON 구조: " + userInfoNode.toString());
                                    }
                                } catch (Exception parseEx) {
                                    AppLog.warn("방법3 실패 - JSON 파싱 오류: " + parseEx.getMessage());
                                    AppLog.warn("파싱 실패한 JSON: " + cookie.getValue());
                                }
                                break;
                            }
                        }
                    } else {
                        AppLog.debug("방법3 실패 - 쿠키가 없음");
                    }
                } catch (Exception e) {
                    AppLog.warn("방법3 실패 - 쿠키 처리 오류: " + e.getMessage());
                }
            }
            
            // 사용자 인증 확인
            if (actualUserId == null) {
                AppLog.error("사용자 정보를 가져올 수 없습니다. 로그인이 필요합니다.");
                returnMap.put("success", false);
                returnMap.put("message", "로그인이 필요합니다. 사용자 정보를 확인할 수 없습니다.");
                returnMap.put("errorCode", "AUTH_REQUIRED");
                return returnMap;
            }
            
            // 사용자 권한 확인 (기업 사용자 접근 차단)
            String userRole = getUserRoleFromCookie(request);
            if ("COMPANY".equals(userRole)) {
                AppLog.error("기업 사용자의 MBTI 테스트 접근 차단: " + actualUserId);
                returnMap.put("success", false);
                returnMap.put("message", "죄송합니다. 개발자 유형 검사는 개인 사용자만 이용 가능합니다.");
                returnMap.put("errorCode", "ACCESS_DENIED");
                return returnMap;
            }
            
            AppLog.debug("=== 최종 사용자 ID: " + actualUserId + " ===");
            
            // JSON 데이터 읽기
            String jsonData = getJsonDataFromRequest(request);
            AppLog.debug("설문 제출 요청 수신: " + jsonData);
            
            // JSON 파싱
            JsonNode jsonNode = objectMapper.readTree(jsonData);
            
            // dma_surveySubmit 객체에서 실제 데이터 추출
            JsonNode surveyData = null;
            if (jsonNode.has("dma_surveySubmit")) {
                surveyData = jsonNode.get("dma_surveySubmit");
            } else {
                surveyData = jsonNode; // 직접 구조인 경우
            }
            
            // SurveySubmitVo 생성
            SurveySubmitVo submitVo = new SurveySubmitVo();
            
            // 세션에서 가져온 실제 사용자 ID 사용 (우선순위 높음)
            if (actualUserId != null) {
                submitVo.setUserId(String.valueOf(actualUserId));
                AppLog.debug("세션에서 사용자 ID 설정: " + actualUserId);
            } else if (surveyData.has("userId")) {
                // Fallback: JSON에서 사용자 ID 시도
                submitVo.setUserId(surveyData.get("userId").asText());
                AppLog.debug("JSON에서 사용자 ID 설정: " + submitVo.getUserId());
            } else {
                AppLog.warn("사용자 ID를 찾을 수 없습니다.");
            }
            
            if (surveyData.has("typeId")) {
                submitVo.setTypeId(surveyData.get("typeId").asLong());
            }
            
            // 응답 파싱 - answers가 JSON 문자열인 경우 처리
            List<QuestionAnswerVo> answers = new ArrayList<>();
            if (surveyData.has("answers")) {
                JsonNode answersNode = surveyData.get("answers");
                AppLog.debug("answers 노드 타입: " + answersNode.getNodeType() + ", 값: " + answersNode.toString());
                
                if (answersNode.isArray()) {
                    // answers가 배열인 경우
                    AppLog.debug("answers가 배열 형태입니다.");
                    for (JsonNode answerNode : answersNode) {
                        QuestionAnswerVo answer = new QuestionAnswerVo();
                        answer.setQuestionId(answerNode.get("questionId").asLong());
                        answer.setAnswerValue(answerNode.get("answerValue").asInt());
                        answers.add(answer);
                    }
                } else if (answersNode.isTextual()) {
                    // answers가 JSON 문자열인 경우
                    String answersJsonString = answersNode.asText();
                    AppLog.debug("answers JSON 문자열 파싱 시도: " + answersJsonString);
                    
                    try {
                        JsonNode answersArray = objectMapper.readTree(answersJsonString);
                        if (answersArray.isArray()) {
                            AppLog.debug("JSON 문자열에서 배열 파싱 성공, 크기: " + answersArray.size());
                            for (JsonNode answerNode : answersArray) {
                                QuestionAnswerVo answer = new QuestionAnswerVo();
                                answer.setQuestionId(answerNode.get("questionId").asLong());
                                answer.setAnswerValue(answerNode.get("answerValue").asInt());
                                answers.add(answer);
                                AppLog.debug("답변 추가: 질문 " + answer.getQuestionId() + " = " + answer.getAnswerValue());
                            }
                        } else {
                            AppLog.error("파싱된 JSON이 배열이 아닙니다: " + answersArray.toString());
                        }
                    } catch (Exception e) {
                        AppLog.error("JSON 문자열 파싱 중 오류", e);
                    }
                } else {
                    AppLog.error("answers 노드가 배열도 문자열도 아닙니다: " + answersNode.toString());
                }
            } else {
                AppLog.error("surveyData에 answers 필드가 없습니다: " + surveyData.toString());
            }
            submitVo.setAnswers(answers);
            
            AppLog.debug("=== 최종 설문 응답 파싱 결과 ===");
            AppLog.debug("응답 수: " + answers.size());
            AppLog.debug("사용자 ID: " + submitVo.getUserId()); 
            AppLog.debug("타입 ID: " + submitVo.getTypeId());
            AppLog.debug("================================");
            
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
    
    /**
     * HttpServletRequest의 쿠키에서 사용자 role 정보를 가져옵니다.
     * 
     * @param request HttpServletRequest
     * @return 사용자 role (예: "COMPANY", "USER") 또는 null
     */
    private String getUserRoleFromCookie(HttpServletRequest request) {
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("userInfo".equals(cookie.getName())) {
                        try {
                            String userInfoJson = cookie.getValue();
                            
                            // URL 디코딩이 필요한지 확인
                            if (userInfoJson.contains("%7B")) {
                                userInfoJson = java.net.URLDecoder.decode(userInfoJson, "UTF-8");
                            }
                            
                            ObjectMapper mapper = new ObjectMapper();
                            JsonNode userInfoNode = mapper.readTree(userInfoJson);
                            
                            if (userInfoNode.has("role")) {
                                String role = userInfoNode.get("role").asText();
                                AppLog.debug("쿠키에서 가져온 사용자 role: " + role);
                                return role;
                            } else {
                                AppLog.debug("쿠키에 role 정보가 없습니다.");
                            }
                        } catch (Exception parseEx) {
                            AppLog.warn("쿠키에서 role 파싱 중 오류: " + parseEx.getMessage());
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
            AppLog.warn("쿠키에서 role 정보 가져오는 중 오류: " + e.getMessage());
        }
        
        return null; // role 정보를 찾을 수 없음
    }
}
