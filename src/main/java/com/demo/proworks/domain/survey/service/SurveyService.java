package com.demo.proworks.domain.survey.service;

import com.demo.proworks.domain.survey.vo.SurveyQuestionVo;
import com.demo.proworks.domain.survey.vo.SurveySubmitVo;
import com.demo.proworks.domain.survey.vo.MbtiCalculationResultVo;

import java.util.List;

/**
 * 설문 관련 서비스 인터페이스
 * 
 * @author ProWorks
 * @since 2025/07/15
 */
public interface SurveyService {
    
    /**
     * 활성화된 설문 질문 목록 조회
     * 
     * @return 설문 질문 목록
     */
    List<SurveyQuestionVo> getActiveQuestions();
    
    /**
     * 설문 응답 제출 및 MBTI 타입 계산
     * 
     * @param submitVo 설문 제출 정보
     * @return MBTI 계산 결과
     * @throws Exception
     */
    MbtiCalculationResultVo submitSurveyAndCalculateMbti(SurveySubmitVo submitVo) throws Exception;
    
    /**
     * 사용자의 최종 MBTI 타입 조회
     * 
     * @param typeId 타입 ID
     * @return MBTI 타입 정보
     */
    MbtiCalculationResultVo getUserMbtiType(Long typeId);
    
    /**
     * 설문 완료 여부 확인
     * 
     * @param typeId 타입 ID
     * @return 설문 완료 여부
     */
    boolean isSurveyCompleted(Long typeId);
}
