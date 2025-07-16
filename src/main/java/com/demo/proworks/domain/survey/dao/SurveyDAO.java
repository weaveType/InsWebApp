package com.demo.proworks.domain.survey.dao;

import com.demo.proworks.domain.survey.vo.SurveyQuestionVo;
import com.demo.proworks.domain.survey.vo.SurveyResponseVo;
import com.demo.proworks.domain.survey.vo.MbtiCalculationResultVo;
import com.inswave.elfw.exception.ElException;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 * 설문 관련 DAO
 * 
 * @author ProWorks
 * @since 2025/07/15
 */
@Repository("surveyDAO")
public class SurveyDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {
    
    /**
     * 활성화된 설문 질문 목록 조회
     * 
     * @return 설문 질문 목록
     * @throws ElException
     */
    public List<SurveyQuestionVo> selectActiveQuestions() throws ElException {
        return (List<SurveyQuestionVo>) list("com.demo.proworks.domain.survey.selectActiveQuestions");
    }
    
    /**
     * 설문 응답 저장
     * 
     * @param responseVo 설문 응답 정보
     * @return 저장된 행 수
     * @throws ElException
     */
    public int insertSurveyResponse(SurveyResponseVo responseVo) throws ElException {
        return insert("com.demo.proworks.domain.survey.insertSurveyResponse", responseVo);
    }
    
    /**
     * 설문 응답의 type_id 업데이트
     * 
     * @param responseVo 설문 응답 정보 (response_id와 type_id 포함)
     * @return 업데이트된 행 수
     * @throws ElException
     */
    public int updateSurveyResponseTypeId(SurveyResponseVo responseVo) throws ElException {
        return update("com.demo.proworks.domain.survey.updateSurveyResponseTypeId", responseVo);
    }
    
    /**
     * 사용자의 최신 설문 응답 조회
     * 
     * @param typeId 타입 ID
     * @return 설문 응답 정보
     * @throws ElException
     */
    public SurveyResponseVo selectLatestResponse(Long typeId) throws ElException {
        return (SurveyResponseVo) selectByPk("com.demo.proworks.domain.survey.selectLatestResponse", typeId);
    }
    
    /**
     * MBTI 타입 정보 저장/업데이트
     * 
     * @param resultVo MBTI 계산 결과
     * @return 처리된 행 수
     * @throws ElException
     */
    public int upsertMbtiType(MbtiCalculationResultVo resultVo) throws ElException {
        return update("com.demo.proworks.domain.survey.upsertMbtiType", resultVo);
    }
    
    /**
     * 사용자의 MBTI 타입 조회
     * 
     * @param typeId 타입 ID
     * @return MBTI 타입 정보
     * @throws ElException
     */
    public MbtiCalculationResultVo selectMbtiType(Long typeId) throws ElException {
        return (MbtiCalculationResultVo) selectByPk("com.demo.proworks.domain.survey.selectMbtiType", typeId);
    }
    
    /**
     * 코드 분석 결과로 MBTI 점수 조회
     * 
     * @param typeId 타입 ID
     * @return 코드 분석 점수 맵
     * @throws ElException
     */
    public Map<String, Object> selectCodeAnalysisScores(Long typeId) throws ElException {
        return (Map<String, Object>) selectByPk("com.demo.proworks.domain.survey.selectCodeAnalysisScores", typeId);
    }
}
