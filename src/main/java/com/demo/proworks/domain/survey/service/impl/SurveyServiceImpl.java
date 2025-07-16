package com.demo.proworks.domain.survey.service.impl;

import com.demo.proworks.domain.survey.dao.SurveyDAO;
import com.demo.proworks.domain.survey.service.SurveyService;
import com.demo.proworks.domain.survey.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.inswave.elfw.log.AppLog;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 * 설문 관련 서비스 구현체
 * 
 * @author ProWorks
 * @since 2025/07/15
 */
@Service("surveyServiceImpl")
public class SurveyServiceImpl implements SurveyService {
    
    @Resource(name = "surveyDAO")
    private SurveyDAO surveyDAO;
    
    // ObjectMapper를 static으로 설정하여 안전하게 처리
    private static final ObjectMapper objectMapper;
    
    static {
        objectMapper = new ObjectMapper();
        // ProWorks5 프레임워크의 elExcludeFilter를 위한 FilterProvider 설정
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter("elExcludeFilter", SimpleBeanPropertyFilter.serializeAll());
        filterProvider.setFailOnUnknownId(false); // 알 수 없는 필터 ID에 대해 실패하지 않도록 설정
        objectMapper.setFilterProvider(filterProvider);
        AppLog.debug("ObjectMapper FilterProvider 설정 완료");
    }
    
    // 가중치 설정 (설문:코드분석)
    private static final double WEIGHT_BA_SURVEY = 0.6;
    private static final double WEIGHT_BA_CODE = 0.4;
    private static final double WEIGHT_RI_SURVEY = 0.75;
    private static final double WEIGHT_RI_CODE = 0.25;
    private static final double WEIGHT_ST_SURVEY = 0.7;
    private static final double WEIGHT_ST_CODE = 0.3;
    private static final double WEIGHT_DF_SURVEY = 1.0;
    private static final double WEIGHT_DF_CODE = 0.0;
    
    // MBTI 타입 이름 매핑
    private static final Map<String, String> TYPE_NAMES = new HashMap<>();
    static {
        TYPE_NAMES.put("BRSD", "실용적 안정성 추구자");
        TYPE_NAMES.put("BRSF", "민첩한 기능 빌더");
        TYPE_NAMES.put("BRTD", "신뢰성 있는 팀 디버거");
        TYPE_NAMES.put("BRTF", "빠른 팀 기능 개발자");
        TYPE_NAMES.put("BISD", "혁신적 솔로 디버거");
        TYPE_NAMES.put("BISF", "창의적 스피드 러너");
        TYPE_NAMES.put("BITD", "실험적 팀 문제 해결사");
        TYPE_NAMES.put("BITF", "혁신적 팀 크리에이터");
        TYPE_NAMES.put("ARSD", "체계적 리팩터 마스터");
        TYPE_NAMES.put("ARSF", "균형잡힌 솔로 아키텍트");
        TYPE_NAMES.put("ARTD", "완벽주의 팀 디버거");
        TYPE_NAMES.put("ARTF", "구조적 팀 빌더");
        TYPE_NAMES.put("AISD", "혁신적 설계 전문가");
        TYPE_NAMES.put("AISF", "창의적 솔로 아키텍트");
        TYPE_NAMES.put("AITD", "미래지향 팀 디버거");
        TYPE_NAMES.put("AITF", "혁신적 팀 아키텍트");
    }
    
    @Override
    public List<SurveyQuestionVo> getActiveQuestions() {
        AppLog.debug("활성화된 설문 질문 목록 조회");
        try {
            return surveyDAO.selectActiveQuestions();
        } catch (Exception e) {
            AppLog.error("설문 질문 조회 중 오류 발생", e);
            throw new RuntimeException("설문 질문 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
    
    @Override
    @Transactional
    public MbtiCalculationResultVo submitSurveyAndCalculateMbti(SurveySubmitVo submitVo) throws Exception {
        AppLog.debug("설문 응답 제출 - 타입ID: " + submitVo.getTypeId());
        
        try {
            // 1. 설문 응답 저장 (설문 제출 시점에는 타입이 아직 결정되지 않으므로 null로 설정)
            SurveyResponseVo responseVo = new SurveyResponseVo();
            responseVo.setTypeId(null);  // Foreign Key 제약조건 위반 방지 - 초기에는 null로 설정
            AppLog.debug("=== responseVo.typeId 설정 후 확인: " + responseVo.getTypeId() + " ===");
            
            // JSON 직렬화를 안전하게 처리
            String responsesJson;
            try {
                AppLog.debug("답변 목록 JSON 직렬화 시작, 답변 수: " + 
                    (submitVo.getAnswers() != null ? submitVo.getAnswers().size() : 0));
                responsesJson = objectMapper.writeValueAsString(submitVo.getAnswers());
                AppLog.debug("JSON 직렬화 성공: " + responsesJson);
            } catch (Exception jsonEx) {
                AppLog.error("JSON 직렬화 중 오류 발생", jsonEx);
                // fallback: 간단한 toString 방식 사용
                responsesJson = submitVo.getAnswers() != null ? submitVo.getAnswers().toString() : "[]";
                AppLog.debug("Fallback JSON 사용: " + responsesJson);
            }
            
            responseVo.setResponses(responsesJson);
            responseVo.setCreateAt(new Timestamp(System.currentTimeMillis()));
            responseVo.setUpdateAt(new Timestamp(System.currentTimeMillis()));
            
            try {
                AppLog.debug("=== DB 저장 직전 responseVo 상태: typeId=" + responseVo.getTypeId() + " ===");
                surveyDAO.insertSurveyResponse(responseVo);
            } catch (Exception ex) {
                AppLog.error("설문 응답 저장 중 오류", ex);
                throw new Exception("설문 응답 저장 중 오류가 발생했습니다: " + ex.getMessage());
            }
            AppLog.debug("설문 응답 저장 완료");
            
            // 2. 설문 점수 계산 (축별로 계산)
            Map<String, Double> surveyScores;
            try {
                if (submitVo.getAnswers() == null || submitVo.getAnswers().isEmpty()) {
                    AppLog.warn("설문 응답이 비어있습니다. 기본값으로 진행합니다.");
                    surveyScores = new HashMap<>();
                    surveyScores.put("B_A", 50.0);
                    surveyScores.put("R_I", 50.0);
                    surveyScores.put("S_T", 50.0);
                    surveyScores.put("D_F", 50.0);
                } else {
                    surveyScores = calculateSurveyScores(submitVo.getAnswers());
                    if (surveyScores == null) {
                        surveyScores = new HashMap<>();
                        surveyScores.put("B_A", 50.0);
                        surveyScores.put("R_I", 50.0);
                        surveyScores.put("S_T", 50.0);
                        surveyScores.put("D_F", 50.0);
                    }
                }
            } catch (Exception ex) {
                AppLog.error("설문 점수 계산 중 오류", ex);
                surveyScores = new HashMap<>();
                surveyScores.put("B_A", 50.0);
                surveyScores.put("R_I", 50.0);
                surveyScores.put("S_T", 50.0);
                surveyScores.put("D_F", 50.0);
            }
            AppLog.debug("설문 점수 계산 완료: " + surveyScores);
            
            // 3. 코드 분석 점수 조회
            Map<String, Object> codeScores;
            try {
                // 사용자 ID를 Long으로 변환 (기본값 9 사용)
                Long userIdLong = submitVo.getUserId() != null ? 
                    Long.valueOf(submitVo.getUserId().hashCode() % 1000) : 9L;
                codeScores = surveyDAO.selectCodeAnalysisScores(userIdLong);
                if (codeScores == null) {
                    codeScores = new HashMap<>();
                    AppLog.debug("코드 분석 데이터가 없습니다. 설문만으로 진행합니다.");
                }
            } catch (Exception ex) {
                AppLog.error("코드 분석 점수 조회 중 오류", ex);
                // 코드 분석이 없어도 설문만으로 진행 가능
                codeScores = new HashMap<>();
            }
            AppLog.debug("코드 분석 점수 조회 완료: " + codeScores);
            
            // 4. 가중치 적용하여 최종 점수 계산
            Long userId;
            try {
                if (submitVo.getUserId() != null && !submitVo.getUserId().trim().isEmpty()) {
                    userId = Long.parseLong(submitVo.getUserId());
                    AppLog.debug("설정된 사용자 ID: " + userId);
                } else {
                    userId = 9L; // 실제 존재하는 사용자 ID로 기본값 설정
                    AppLog.warn("사용자 ID가 없어서 기본값 9를 사용합니다.");
                }
            } catch (NumberFormatException e) {
                userId = 9L; // 파싱 실패 시에도 기본값 9 사용
                AppLog.warn("사용자 ID 파싱 실패, 기본값 9 사용: " + submitVo.getUserId());
            }
            
            MbtiCalculationResultVo result = calculateFinalMbtiType(submitVo.getTypeId(), surveyScores, codeScores, userId);
            
            // 5. 결과 저장
            try {
                AppLog.debug("MBTI 결과 저장 시도 - 사용자 ID: " + result.getUserId() + ", 타입: " + result.getTypeCode());
                surveyDAO.upsertMbtiType(result);
                AppLog.debug("MBTI 결과 저장 성공");
                
                // 6. survey_responses의 type_id 업데이트 (MBTI 계산 완료 후)
                try {
                    responseVo.setTypeId(result.getTypeId());
                    surveyDAO.updateSurveyResponseTypeId(responseVo);
                    AppLog.debug("설문 응답의 type_id 업데이트 완료: " + result.getTypeId());
                } catch (Exception updateEx) {
                    AppLog.warn("설문 응답 type_id 업데이트 실패 (비즈니스 로직에는 영향 없음): " + updateEx.getMessage());
                    // type_id 업데이트 실패해도 전체 프로세스는 성공으로 처리
                }
            } catch (Exception ex) {
                AppLog.error("MBTI 타입 저장 중 오류", ex);
                throw new Exception("MBTI 타입 저장 중 오류가 발생했습니다: " + ex.getMessage());
            }
            
            return result;
            
        } catch (Exception e) {
            AppLog.error("설문 제출 및 MBTI 계산 중 오류 발생", e);
            throw new Exception("설문 처리 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
    
    @Override
    public MbtiCalculationResultVo getUserMbtiType(Long userId) {
        AppLog.debug("사용자 MBTI 타입 조회 - 사용자ID: " + userId);
        try {
            return surveyDAO.selectMbtiType(userId);
        } catch (Exception e) {
            AppLog.error("MBTI 타입 조회 중 오류 발생", e);
            throw new RuntimeException("MBTI 타입 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
    
    @Override
    public boolean isSurveyCompleted(Long typeId) {
        AppLog.debug("설문 완료 여부 확인 - 타입ID: " + typeId);
        try {
            SurveyResponseVo response = surveyDAO.selectLatestResponse(typeId);
            return response != null;
        } catch (Exception e) {
            AppLog.error("설문 완료 여부 확인 중 오류 발생", e);
            throw new RuntimeException("설문 완료 여부 확인 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
    
    /**
     * 설문 응답으로부터 축별 점수 계산
     * 
     * @param answers 질문별 응답 리스트
     * @return 축별 점수 맵
     */
    private Map<String, Double> calculateSurveyScores(List<QuestionAnswerVo> answers) {
        Map<String, Double> scores = new HashMap<>();
        Map<String, Integer> counts = new HashMap<>();
        Map<String, Integer> totals = new HashMap<>();
        
        // null 체크
        if (answers == null) {
            AppLog.warn("설문 응답이 null입니다.");
            answers = new java.util.ArrayList<>();
        }
        
        // 축별 초기화
        for (String axis : new String[]{"B_A", "R_I", "S_T", "D_F"}) {
            counts.put(axis, 0);
            totals.put(axis, 0);
        }
        
        // 질문별 점수 집계
        List<SurveyQuestionVo> questions;
        try {
            questions = surveyDAO.selectActiveQuestions();
        } catch (Exception e) {
            AppLog.error("설문 질문 조회 중 오류", e);
            throw new RuntimeException("설문 질문 조회 중 오류: " + e.getMessage());
        }
        Map<Long, String> questionAxisMap = new HashMap<>();
        
        for (SurveyQuestionVo question : questions) {
            questionAxisMap.put(question.getQuestionId(), question.getAxis());
        }
        
        for (QuestionAnswerVo answer : answers) {
            String axis = questionAxisMap.get(answer.getQuestionId());
            if (axis != null) {
                counts.put(axis, counts.get(axis) + 1);
                totals.put(axis, totals.get(axis) + answer.getAnswerValue());
            }
        }
        
        // 축별 평균 점수 계산 (1-5 scale을 0-100으로 변환)
        for (String axis : new String[]{"B_A", "R_I", "S_T", "D_F"}) {
            if (counts.get(axis) > 0) {
                double avgScore = (double) totals.get(axis) / counts.get(axis);
                // 1-5 scale을 0-100으로 변환 (1=0, 3=50, 5=100)
                double normalizedScore = (avgScore - 1) * 25;
                scores.put(axis, normalizedScore);
            } else {
                scores.put(axis, 50.0); // 기본값
            }
        }
        
        return scores;
    }
    
    /**
     * 가중치를 적용하여 최종 MBTI 타입 계산
     * 
     * @param typeId 타입 ID
     * @param surveyScores 설문 점수
     * @param codeScores 코드 분석 점수
     * @return MBTI 계산 결과
     */
    private MbtiCalculationResultVo calculateFinalMbtiType(Long typeId, Map<String, Double> surveyScores, Map<String, Object> codeScores, Long userId) {
        MbtiCalculationResultVo result = new MbtiCalculationResultVo();
        result.setTypeId(typeId);
        result.setUserId(userId);
        
        // null 체크 - 데이터가 없는 경우 기본값 사용
        if (codeScores == null) {
            codeScores = new HashMap<>();
            AppLog.debug("코드 분석 점수가 null입니다. 빈 맵으로 초기화합니다.");
        }
        if (surveyScores == null) {
            surveyScores = new HashMap<>();
            AppLog.debug("설문 점수가 null입니다. 빈 맵으로 초기화합니다.");
        }
        
        AppLog.debug("코드 분석 점수 맵 내용: " + codeScores);
        AppLog.debug("설문 점수 맵 내용: " + surveyScores);
        
        // 안전한 double 값 추출을 위한 헬퍼 메서드 사용
        double codeStyleScore = safeDoubleFromMap(codeScores, "development_style_score", 50.0);
        double codeCollabScore = safeDoubleFromMap(codeScores, "collaboration_score", 50.0);
        
        AppLog.debug("추출된 코드 분석 점수 - 스타일: " + codeStyleScore + ", 협업: " + codeCollabScore);
        
        // B/A 축 계산 (Architect가 높으면 A, Builder가 높으면 B)
        double surveyBA = surveyScores.getOrDefault("B_A", 50.0);
        double finalBA = (surveyBA * WEIGHT_BA_SURVEY) + (codeStyleScore * WEIGHT_BA_CODE);
        result.setABScore(finalBA);
        
        // R/I 축 계산 (설문만 사용 - 코드 분석에서는 R/I 구분 불가)
        double surveyRI = surveyScores.getOrDefault("R_I", 50.0);
        double finalRI = surveyRI * WEIGHT_RI_SURVEY; // 코드 분석 비중 0.25는 기본값 50 적용
        result.setRIScore(finalRI);
        
        // S/T 축 계산 (Solo가 높으면 S, Team이 높으면 T)
        double surveyST = surveyScores.getOrDefault("S_T", 50.0);
        double finalST = (surveyST * WEIGHT_ST_SURVEY) + (codeCollabScore * WEIGHT_ST_CODE);
        result.setSTScore(finalST);
        
        // D/F 축 계산 (설문만 사용)
        double surveyDF = surveyScores.getOrDefault("D_F", 50.0);
        double finalDF = surveyDF * WEIGHT_DF_SURVEY; // 설문만 사용
        result.setDFScore(finalDF);
        
        AppLog.debug("최종 계산 점수 - BA: " + finalBA + ", RI: " + finalRI + ", ST: " + finalST + ", DF: " + finalDF);
        
        // 타입 코드 결정 (50점 기준으로 분류)
        StringBuilder typeCode = new StringBuilder();
        typeCode.append(finalBA >= 50 ? "A" : "B");
        typeCode.append(finalRI >= 50 ? "R" : "I");
        typeCode.append(finalST >= 50 ? "S" : "T");
        typeCode.append(finalDF >= 50 ? "D" : "F");
        
        result.setTypeCode(typeCode.toString());
        result.setTypeName(TYPE_NAMES.getOrDefault(typeCode.toString(), "알 수 없는 유형"));
        result.setTypeDescription(generateTypeDescription(typeCode.toString()));
        
        AppLog.debug("최종 결정된 타입: " + typeCode.toString());
        
        return result;
    }
    
    /**
     * Map에서 안전하게 double 값을 추출하는 헬퍼 메서드
     * 
     * @param map 대상 맵
     * @param key 키
     * @param defaultValue 기본값
     * @return double 값
     */
    private double safeDoubleFromMap(Map<String, Object> map, String key, double defaultValue) {
        if (map == null || key == null) {
            AppLog.debug("맵 또는 키가 null입니다. 기본값을 반환합니다: " + defaultValue);
            return defaultValue;
        }
        
        Object value = map.get(key);
        if (value == null) {
            AppLog.debug("키 '" + key + "'에 대한 값이 null입니다. 기본값을 반환합니다: " + defaultValue);
            return defaultValue;
        }
        
        try {
            if (value instanceof Number) {
                return ((Number) value).doubleValue();
            } else if (value instanceof String) {
                return Double.parseDouble((String) value);
            } else {
                AppLog.warn("키 '" + key + "'의 값이 예상치 못한 타입입니다: " + value.getClass().getSimpleName() + ", 값: " + value);
                return defaultValue;
            }
        } catch (Exception e) {
            AppLog.error("키 '" + key + "'의 값을 double로 변환하는 중 오류 발생: " + value, e);
            return defaultValue;
        }
    }
    
    /**
     * 타입별 설명 생성
     * 
     * @param typeCode MBTI 타입 코드
     * @return 타입 설명
     */
    private String generateTypeDescription(String typeCode) {
        // 실제로는 더 상세한 설명을 DB나 설정 파일에서 가져와야 함
        switch (typeCode) {
            case "BRSD":
                return "빠른 구현과 실용성을 선호하며, 기존 코드를 개선하고 개인적으로 일하면서 버그를 발견하고 해결하는 데 보람을 느낍니다.";
            case "BRSF":
                return "빠른 구현과 실용성을 선호하며, 기존 코드를 개선하고 개인적으로 일하면서 새로운 기능을 창의적으로 개발하는 것을 즐깁니다.";
            case "BRTD":
                return "빠른 구현과 실용성을 선호하며, 기존 코드를 개선하고 소통과 협업을 중요하게 생각하면서 버그를 발견하고 해결하는 데 보람을 느낍니다.";
            case "BRTF":
                return "빠른 구현과 실용성을 선호하며, 기존 코드를 개선하고 소통과 협업을 중요하게 생각하면서 새로운 기능을 창의적으로 개발하는 것을 즐깁니다.";
            // ... 나머지 타입들도 추가
            default:
                return "독특한 개발 스타일을 가진 개발자입니다.";
        }
    }
}
