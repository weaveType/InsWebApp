package com.demo.proworks.domain.survey.service.impl;

import com.demo.proworks.domain.survey.dao.SurveyDAO;
import com.demo.proworks.domain.survey.service.SurveyService;
import com.demo.proworks.domain.survey.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.inswave.elfw.log.AppLog;
import com.demo.proworks.domain.codeanalysis.dao.CodeAnalysisDAO;
import com.demo.proworks.domain.codeanalysis.vo.CodeAnalysisResultVo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
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
    
    @Resource(name = "codeAnalysisDAO")
    private CodeAnalysisDAO codeAnalysisDAO;
    
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
    
    // 설문 점수 계산 상수 (균등 배분 방식)
    private static final int OPTION_COUNT = 7;           // 7지선다
    private static final int CENTER_OPTION = 4;          // 중간값 (4번 = 0점)
    private static final int TARGET_RANGE = 50;          // 목표 범위 (±50점)
    private static final int QUESTIONS_PER_AXIS = 5;     // 축당 문제 수
    private static final int MAX_DEVIATION = (OPTION_COUNT - 1) / 2; // 최대 편차 (3)
    private static final double UNIT_SCORE = (double) TARGET_RANGE / (QUESTIONS_PER_AXIS * MAX_DEVIATION); // 3.333...
    
    // 가중치 설정 (설문:코드분석)
    private static final double WEIGHT_BA_SURVEY = 0.6;
    private static final double WEIGHT_BA_CODE = 0.4;
    private static final double WEIGHT_RI_SURVEY = 0.75;
    private static final double WEIGHT_RI_CODE = 0.25;
    private static final double WEIGHT_ST_SURVEY = 1.0;
    private static final double WEIGHT_ST_CODE = 0.0;
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
            // 1. 사용자 ID 먼저 계산
            Long userId;
            try {
                if (submitVo.getUserId() != null && !submitVo.getUserId().trim().isEmpty()) {
                    userId = Long.parseLong(submitVo.getUserId());
                    AppLog.debug("설정된 사용자 ID: " + userId);
                } else {
                    throw new Exception("사용자 ID가 제공되지 않았습니다.");
                }
            } catch (NumberFormatException e) {
                AppLog.error("사용자 ID 파싱 실패: " + submitVo.getUserId());
                throw new Exception("유효하지 않은 사용자 ID입니다: " + submitVo.getUserId());
            }
            
            // 2. 코드분석 선행 체크 - 코드분석이 완료되어야만 설문조사 가능
            try {
                Map<String, Object> codeAnalysisCheck = surveyDAO.checkCodeAnalysisExists(userId);
                if (codeAnalysisCheck == null || codeAnalysisCheck.isEmpty()) {
                    AppLog.warn("코드분석이 완료되지 않은 사용자의 설문조사 시도: " + userId);
                    throw new Exception("설문조사를 진행하기 전에 먼저 코드분석을 완료해주세요.");
                }
                AppLog.debug("코드분석 선행 조건 확인 완료 - 사용자ID: " + userId);
            } catch (Exception codeCheckEx) {
                AppLog.error("코드분석 존재 여부 체크 중 오류", codeCheckEx);
                throw new Exception("코드분석 선행 조건 확인 중 오류가 발생했습니다: " + codeCheckEx.getMessage());
            }
            
            // 3. 설문 응답 저장
            SurveyResponseVo responseVo = new SurveyResponseVo();
            responseVo.setUserId(userId);  // userId 설정
            AppLog.debug("=== 설문 응답 VO 생성 완료, userId: " + userId + " ===");
            
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
            responseVo.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            responseVo.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            
            try {
                AppLog.debug("=== DB 저장 직전 responseVo 상태: userId=" + responseVo.getUserId() + " ===");
                surveyDAO.insertSurveyResponse(responseVo);
            } catch (Exception ex) {
                AppLog.error("설문 응답 저장 중 오류", ex);
                throw new Exception("설문 응답 저장 중 오류가 발생했습니다: " + ex.getMessage());
            }
            AppLog.debug("설문 응답 저장 완료");
            
            // 2. 설문 점수 계산 (축별로 계산, 0점 기준 -50~+50)
            Map<String, Double> surveyScores;
            try {
                if (submitVo.getAnswers() == null || submitVo.getAnswers().isEmpty()) {
                    AppLog.warn("설문 응답이 비어있습니다. 기본값으로 진행합니다.");
                    surveyScores = new HashMap<>();
                    surveyScores.put("B_A", 0.0);
                    surveyScores.put("R_I", 0.0);
                    surveyScores.put("S_T", 0.0);
                    surveyScores.put("D_F", 0.0);
                } else {
                    surveyScores = calculateSurveyScores(submitVo.getAnswers());
                    if (surveyScores == null) {
                        surveyScores = new HashMap<>();
                        surveyScores.put("B_A", 0.0);
                        surveyScores.put("R_I", 0.0);
                        surveyScores.put("S_T", 0.0);
                        surveyScores.put("D_F", 0.0);
                    }
                }
            } catch (Exception ex) {
                AppLog.error("설문 점수 계산 중 오류", ex);
                surveyScores = new HashMap<>();
                surveyScores.put("B_A", 0.0);
                surveyScores.put("R_I", 0.0);
                surveyScores.put("S_T", 0.0);
                surveyScores.put("D_F", 0.0);
            }
            AppLog.debug("설문 점수 계산 완료: " + surveyScores);
            
            // 3. 코드 분석 점수 조회
            Map<String, Object> codeScores;
            try {
                // 사용자 ID를 Long으로 변환
                Long userIdLong = null;
                if (submitVo.getUserId() != null && !submitVo.getUserId().trim().isEmpty()) {
                    try {
                        userIdLong = Long.parseLong(submitVo.getUserId());
                    } catch (NumberFormatException e) {
                        AppLog.warn("사용자 ID 변환 실패, 코드 분석 조회 스킵: " + submitVo.getUserId());
                        userIdLong = null;
                    }
                }
                if (userIdLong != null) {
                    codeScores = surveyDAO.selectCodeAnalysisScores(userIdLong);
                    if (codeScores == null) {
                        codeScores = new HashMap<>();
                        AppLog.debug("사용자 ID " + userIdLong + "의 코드 분석 데이터가 없습니다. 설문만으로 진행합니다.");
                    }
                } else {
                    codeScores = new HashMap<>();
                    AppLog.debug("사용자 ID가 없어서 코드 분석을 생략하고 설문만으로 진행합니다.");
                }
            } catch (Exception ex) {
                AppLog.error("코드 분석 점수 조회 중 오류", ex);
                // 코드 분석이 없어도 설문만으로 진행 가능
                codeScores = new HashMap<>();
            }
            AppLog.debug("코드 분석 점수 조회 완료: " + codeScores);
            
            // 4. 가중치 적용하여 최종 점수 계산 (userId는 이미 위에서 계산됨)
            
            MbtiCalculationResultVo result = calculateFinalMbtiType(surveyScores, codeScores, userId);
            
            // 코드 분석 결과 조회
            CodeAnalysisResultVo codeResult = codeAnalysisDAO.selectLatestCodeAnalysisResult(userId);
            String codeComment = codeResult != null ? codeResult.getComment() : null;
            String codeDetail = codeResult != null ? codeResult.getAnalysisResult() : null;
            result.setCodeAnalysisComment(codeComment);
            result.setCodeAnalysisDetail(codeDetail);
            
            // 5. 결과 저장
            try {
                AppLog.debug("MBTI 결과 저장 시도 - 사용자 ID: " + result.getUserId() + ", 타입: " + result.getTypeCode());
                surveyDAO.upsertMbtiType(result);
                AppLog.debug("MBTI 결과 저장 성공");
                
                // 6. 설문 응답과 MBTI 결과가 모두 user_id로 연결되어 처리 완료
                AppLog.debug("설문 응답과 MBTI 결과 저장 완료 - 사용자 ID: " + result.getUserId());
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
     * 설문 응답으로부터 축별 점수 계산 (균등 배분 방식, 0점 기준 -50 ~ +50)
     * 
     * @param answers 질문별 응답 리스트
     * @return 축별 점수 맵 (+값: A/I/T/F, -값: B/R/S/D)
     */
    private Map<String, Double> calculateSurveyScores(List<QuestionAnswerVo> answers) {
        Map<String, Double> scores = new HashMap<>();
        Map<String, List<Double>> axisScores = new HashMap<>();
        
        // null 체크
        if (answers == null) {
            AppLog.warn("설문 응답이 null입니다.");
            answers = new ArrayList<>();
        }
        
        // 축별 점수 리스트 초기화
        for (String axis : new String[]{"B_A", "R_I", "S_T", "D_F"}) {
            axisScores.put(axis, new ArrayList<>());
        }
        
        AppLog.debug("균등 배분 설정: UNIT_SCORE = " + UNIT_SCORE + " (7지선다, ±" + TARGET_RANGE + "점)");
        
        // 질문별 점수 계산 (완벽하게 균등한 간격)
        for (QuestionAnswerVo answer : answers) {
            Long questionId = answer.getQuestionId();
            int answerValue = answer.getAnswerValue();
            
            // 균등 배분 공식: (선택지 - 중간값) × 단위점수
            double questionScore = (answerValue - CENTER_OPTION) * UNIT_SCORE;
            
            AppLog.debug("문제 " + questionId + ": 답변(" + answerValue + ") → 점수(" + questionScore + ")");
            
            // 축별 점수 배정 (방향성 설정 제거 - 모든 문제 동일하게 처리)
            if (questionId >= 1 && questionId <= 5) {
                // B_A 축
                axisScores.get("B_A").add(questionScore);
                AppLog.debug("  → B_A축: " + questionScore);
                
            } else if (questionId >= 6 && questionId <= 10) {
                // R_I 축
                axisScores.get("R_I").add(questionScore);
                AppLog.debug("  → R_I축: " + questionScore);
                
            } else if (questionId >= 11 && questionId <= 15) {
                // S_T 축
                axisScores.get("S_T").add(questionScore);
                AppLog.debug("  → S_T축: " + questionScore);
                
            } else if (questionId >= 16 && questionId <= 20) {
                // D_F 축
                axisScores.get("D_F").add(questionScore);
                AppLog.debug("  → D_F축: " + questionScore);
            }
        }
        
        // 축별 점수 합계 계산 (5문제 합계)
        for (String axis : new String[]{"B_A", "R_I", "S_T", "D_F"}) {
            List<Double> scoreList = axisScores.get(axis);
            if (!scoreList.isEmpty()) {
                double totalScore = scoreList.stream().mapToDouble(Double::doubleValue).sum();
                scores.put(axis, totalScore);
                AppLog.debug(axis + " 축 최종 점수: " + scoreList.size() + "문항 합계 = " + totalScore);
                AppLog.debug("  세부점수: " + scoreList);
            } else {
                scores.put(axis, 0.0); // 기본값 (중립)
                AppLog.debug(axis + " 축 응답 없음, 기본값 0.0 설정");
            }
        }
        
        AppLog.debug("설문 점수 계산 완료 (균등배분): " + scores);
        return scores;
    }
    
    /**
     * 가중치를 적용하여 최종 MBTI 타입 계산 (0점 기준)
     * 
     * @param typeId 타입 ID
     * @param surveyScores 설문 점수 (-50~+50)
     * @param codeScores 코드 분석 점수 (-50~+50)
     * @return MBTI 계산 결과
     */
    private MbtiCalculationResultVo calculateFinalMbtiType(Map<String, Double> surveyScores, Map<String, Object> codeScores, Long userId) {
        MbtiCalculationResultVo result = new MbtiCalculationResultVo();
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
        
        // 안전한 double 값 추출을 위한 헬퍼 메서드 사용 (기본값 0.0)
        double codeStyleScore = safeDoubleFromMap(codeScores, "development_style_score", 0.0);
        double codeCollabScore = safeDoubleFromMap(codeScores, "collaboration_score", 0.0);
        
        AppLog.debug("추출된 코드 분석 점수 - 스타일: " + codeStyleScore + ", 협업: " + codeCollabScore);
        
        // B/A 축 계산 (+값: Architect, -값: Builder)
        double surveyBA = surveyScores.getOrDefault("B_A", 0.0);
        double finalBA = (surveyBA * WEIGHT_BA_SURVEY) + (codeStyleScore * WEIGHT_BA_CODE);
        result.setABScore(finalBA);
        
        // R/I 축 계산 (+값: Innovate, -값: Refactor)
        double surveyRI = surveyScores.getOrDefault("R_I", 0.0);
        double finalRI = (surveyRI * WEIGHT_RI_SURVEY) + (0.0 * WEIGHT_RI_CODE); // 코드 분석에서 R/I 구분 불가
        result.setRIScore(finalRI);
        
        // S/T 축 계산 (+값: Team, -값: Solo)
        double surveyST = surveyScores.getOrDefault("S_T", 0.0);
        double finalST = (surveyST * WEIGHT_ST_SURVEY) + (codeCollabScore * WEIGHT_ST_CODE);
        result.setSTScore(finalST);
        
        // D/F 축 계산 (+값: Feature, -값: Debug)
        double surveyDF = surveyScores.getOrDefault("D_F", 0.0);
        double finalDF = (surveyDF * WEIGHT_DF_SURVEY) + (0.0 * WEIGHT_DF_CODE); // 코드 분석으로 D/F 구분 불가
        result.setDFScore(finalDF);
        
        AppLog.debug("최종 계산 점수 - BA: " + finalBA + ", RI: " + finalRI + ", ST: " + finalST + ", DF: " + finalDF);
        
        // 타입 코드 결정 (0점 기준으로 분류)
        StringBuilder typeCode = new StringBuilder();
        typeCode.append(finalBA >= 0 ? "A" : "B");  // +: Architect, -: Builder
        typeCode.append(finalRI >= 0 ? "I" : "R");  // +: Innovate, -: Refactor
        typeCode.append(finalST >= 0 ? "T" : "S");  // +: Team, -: Solo
        typeCode.append(finalDF >= 0 ? "F" : "D");  // +: Feature, -: Debug
        
        result.setTypeCode(typeCode.toString());
        result.setTypeName(TYPE_NAMES.getOrDefault(typeCode.toString(), "알 수 없는 유형"));
        result.setTypeDescription(generateTypeDescription(typeCode.toString()));
        
        AppLog.debug("최종 결정된 타입: " + typeCode.toString());
        
        return result;
    }
    
    /**
     * Map에서 안전하게 double 값을 추출하는 헬퍼 메서드 (0점 기준)
     * 
     * @param map 대상 맵
     * @param key 키
     * @param defaultValue 기본값 (0.0 권장)
     * @return double 값 (-50 ~ +50 범위)
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
            double result;
            if (value instanceof Number) {
                result = ((Number) value).doubleValue();
            } else if (value instanceof String) {
                result = Double.parseDouble((String) value);
            } else {
                AppLog.warn("키 '" + key + "'의 값이 예상치 못한 타입입니다: " + value.getClass().getSimpleName() + ", 값: " + value);
                return defaultValue;
            }
            
            return result;
            
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
