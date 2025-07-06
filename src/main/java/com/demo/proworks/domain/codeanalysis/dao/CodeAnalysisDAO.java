package com.demo.proworks.domain.codeanalysis.dao;

import org.springframework.stereotype.Repository;
import com.inswave.elfw.exception.ElException;
import com.demo.proworks.domain.codeanalysis.vo.CodeAnalysisResultVo;

/**
 * @subject : 코드분석 DAO
 * @description : 코드분석 관련 데이터베이스 처리
 * @author : Inswave
 * @since : 2025/07/04
 */
@Repository("codeAnalysisDAO")
public class CodeAnalysisDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {
    
    /**
     * 코드 분석 결과를 저장합니다.
     * 
     * @param resultVo 분석 결과 정보
     * @return 저장 결과
     * @throws ElException
     */
    public int insertCodeAnalysisResult(CodeAnalysisResultVo resultVo) throws ElException {
        return insert("com.demo.proworks.domain.codeanalysis.dao.CodeAnalysisDAO.insertCodeAnalysisResult", resultVo);
    }
    
    /**
     * 사용자의 최신 코드 분석 결과를 조회합니다.
     * 
     * @param userId 사용자 ID
     * @return 분석 결과
     * @throws ElException
     */
    public CodeAnalysisResultVo selectLatestCodeAnalysisResult(String userId) throws ElException {
        return (CodeAnalysisResultVo) selectByPk("com.demo.proworks.domain.codeanalysis.dao.CodeAnalysisDAO.selectLatestCodeAnalysisResult", userId);
    }
    
    /**
     * 코드 분석 결과를 업데이트합니다.
     * 
     * @param resultVo 분석 결과 정보
     * @return 업데이트 결과
     * @throws ElException
     */
    public int updateCodeAnalysisResult(CodeAnalysisResultVo resultVo) throws ElException {
        return update("com.demo.proworks.domain.codeanalysis.dao.CodeAnalysisDAO.updateCodeAnalysisResult", resultVo);
    }
    
    /**
     * 코드 분석 결과를 삭제합니다.
     * 
     * @param codeAnalysisId 코드분석ID
     * @return 삭제 결과
     * @throws ElException
     */
    public int deleteCodeAnalysisResult(Long codeAnalysisId) throws ElException {
        return delete("com.demo.proworks.domain.codeanalysis.dao.CodeAnalysisDAO.deleteCodeAnalysisResult", codeAnalysisId);
    }
} 