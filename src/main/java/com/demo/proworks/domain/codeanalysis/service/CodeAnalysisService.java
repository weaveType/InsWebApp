package com.demo.proworks.domain.codeanalysis.service;

import com.demo.proworks.domain.codeanalysis.vo.CodeAnalysisRequestVo;
import com.demo.proworks.domain.codeanalysis.vo.CodeAnalysisResultVo;

/**
 * @subject : 코드 분석 서비스 인터페이스
 * @description : Gemini API를 통한 코드 분석 관련 서비스 인터페이스
 * @author : Inswave
 * @since : 2025/07/04
 */
public interface CodeAnalysisService {
    
    /**
     * 코드 분석을 수행합니다.
     * 
     * @param requestVo 코드 분석 요청 정보
     * @return 코드 분석 결과
     * @throws Exception
     */
    CodeAnalysisResultVo analyzeCode(CodeAnalysisRequestVo requestVo) throws Exception;
    
    /**
     * 코드 분석 결과를 저장합니다.
     * 
     * @param resultVo 코드 분석 결과
     * @throws Exception
     */
    void saveAnalysisResult(CodeAnalysisResultVo resultVo) throws Exception;
    
    /**
     * 개발자 유형의 최신 코드 분석 결과를 조회합니다.
     * 
     * @param typeId 개발자 유형 ID
     * @return 코드 분석 결과
     * @throws Exception
     */
    CodeAnalysisResultVo getAnalysisResult(Long typeId) throws Exception;
} 