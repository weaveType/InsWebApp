package com.demo.proworks.common.service;

import com.demo.proworks.domain.post.vo.SendEmailVo;
import java.util.concurrent.CompletableFuture;

/**
 * 이메일 전송 서비스 인터페이스
 * 
 * @author system
 * @author : 김지훈
 * @since 2025.01
 */
public interface EmailService {

    /**
     * 단일 이메일을 비동기로 전송합니다.
     * 
     * @param to 수신자 이메일
     * @param subject 제목
     * @param content 내용
	 * @author : 김지훈
     * @return CompletableFuture<Boolean> 전송 성공 여부
     */
    CompletableFuture<Boolean> sendEmailAsync(String to, String subject, String content);

    /**
     * 여러 이메일을 일괄 비동기 전송합니다.
     * 
     * @param sendEmailVo 이메일 전송 정보
     * @return CompletableFuture<Integer> 성공한 이메일 전송 개수
	 * @author : 김지훈
     */
    CompletableFuture<Integer> sendToEmails(SendEmailVo sendEmailVo);
}
