package com.demo.proworks.common.service.impl;

import com.demo.proworks.common.service.EmailService;
import com.demo.proworks.domain.post.vo.SendEmailVo;
import com.demo.proworks.domain.post.vo.EmailListVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.List;
import java.util.ArrayList;

/**
 * 이메일 전송 서비스 구현체
 * SMTP를 통한 비동기 이메일 전송 기능을 제공합니다.
 * 
 * @author system
 * @since 2025.01
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 단일 이메일을 비동기로 전송합니다.
     * 
     * @param to 수신자 이메일
     * @param subject 제목
     * @param content 내용
     * @return CompletableFuture<Boolean> 전송 성공 여부
     */
    @Async("emailTaskExecutor")
    @Override
    public CompletableFuture<Boolean> sendEmailAsync(String to, String subject, String content) {
        System.out.println("=== 단일 이메일 전송 시작 ===");
        System.out.println("수신자: " + to);
        System.out.println("제목: " + subject);
        
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content);
            // message.setFrom("your-smtp-username@example.com"); // 실제 발신자 이메일 설정 필요
            
            javaMailSender.send(message);
            
            System.out.println("✅ 이메일 전송 성공: " + to);
            return CompletableFuture.completedFuture(true);
            
        } catch (Exception e) {
            System.err.println("❌ 이메일 전송 실패: " + to);
            System.err.println("오류: " + e.getMessage());
            e.printStackTrace();
            return CompletableFuture.completedFuture(false);
        }
    }

    /**
     * 여러 이메일을 일괄 비동기 전송합니다.
     * 
     * @param sendEmailVo 이메일 전송 정보
     * @return CompletableFuture<Integer> 성공한 이메일 전송 개수
     */
    @Async("emailTaskExecutor")
    @Override
    public CompletableFuture<Integer> sendBulkEmailsAsync(SendEmailVo sendEmailVo) {
        System.out.println("=== 일괄 이메일 전송 시작 ===");
        
        if (sendEmailVo == null) {
            System.err.println("❌ SendEmailVo가 null입니다.");
            return CompletableFuture.completedFuture(0);
        }

        List<EmailListVo> emailList = sendEmailVo.getEmailListVo();
        if (emailList == null || emailList.isEmpty()) {
            System.err.println("❌ 전송할 이메일 목록이 비어있습니다.");
            return CompletableFuture.completedFuture(0);
        }

        String emailContent = sendEmailVo.getEmailContent();
        boolean isPassed = sendEmailVo.isIsPassed();
        
        // 합격/불합격에 따른 제목 설정
        String subject = isPassed ? "[합격] 채용 결과 안내" : "[불합격] 채용 결과 안내";
        
        System.out.println("전송할 이메일 개수: " + emailList.size());
        System.out.println("합불여부: " + (isPassed ? "합격" : "불합격"));
        System.out.println("이메일 내용: " + emailContent);

        int successCount = 0;
        List<CompletableFuture<Boolean>> futures = new ArrayList<>();

        // 각 이메일을 비동기로 전송
        for (EmailListVo emailVo : emailList) {
            if (emailVo != null && emailVo.getEmail() != null && !emailVo.getEmail().trim().isEmpty()) {
                CompletableFuture<Boolean> future = sendEmailAsync(emailVo.getEmail(), subject, emailContent);
                futures.add(future);
            } else {
                System.err.println("⚠️ 잘못된 이메일 주소 건너뜀: " + 
                    (emailVo != null ? emailVo.getEmail() : "null"));
            }
        }

        // 모든 이메일 전송 완료 대기
        try {
            CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
            );
            
            allFutures.get(); // 모든 작업 완료 대기
            
            // 성공 개수 계산
            for (CompletableFuture<Boolean> future : futures) {
                if (future.get()) {
                    successCount++;
                }
            }
            
        } catch (Exception e) {
            System.err.println("❌ 일괄 이메일 전송 중 오류: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("=== 일괄 이메일 전송 완료 ===");
        System.out.println("성공: " + successCount + "/" + futures.size());
        
        return CompletableFuture.completedFuture(successCount);
    }
}
