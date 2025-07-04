package com.demo.proworks.cmmn;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.log.AppLog;
import com.inswave.elfw.util.ControllerContextUtil;

/**
 * 세션 확인용 컨트롤러
 * @author 개발팀
 * @since 2025/07/04
 */
@Controller
public class SessionCheckController {

    /**
     * 현재 세션 상태를 확인한다
     * @param request HttpServletRequest
     * @return 세션 상태 정보
     * @throws Exception
     */
    @ElService(key = "CheckSession")
    @RequestMapping(value = "CheckSession")
    @ElDescription(sub = "세션 상태 확인", desc = "현재 세션 상태를 확인한다.")
    @ResponseBody
    public String checkSession(HttpServletRequest request) throws Exception {
        StringBuilder result = new StringBuilder();
        
        try {
            // 1. HTTP 세션 확인
            HttpSession httpSession = request.getSession(false);
            result.append("=== HTTP 세션 확인 ===\n");
            if (httpSession != null) {
                result.append("세션 ID: ").append(httpSession.getId()).append("\n");
                result.append("세션 생성 시간: ").append(new java.util.Date(httpSession.getCreationTime())).append("\n");
                result.append("마지막 접근 시간: ").append(new java.util.Date(httpSession.getLastAccessedTime())).append("\n");
                result.append("세션 유효 시간: ").append(httpSession.getMaxInactiveInterval()).append("초\n");
            } else {
                result.append("HTTP 세션이 존재하지 않습니다.\n");
            }
            
            // 2. ProWorks 사용자 헤더 확인
            result.append("\n=== ProWorks 사용자 헤더 확인 ===\n");
            try {
                ProworksUserHeader userHeader = (ProworksUserHeader) ControllerContextUtil.getUserHeader();
                if (userHeader != null) {
                    result.append("사용자 ID: ").append(userHeader.getUserId()).append("\n");
                    result.append("이메일: ").append(userHeader.getEmail()).append("\n");
                    result.append("권한 ID: ").append(userHeader.getRole()).append("\n");
                    result.append("사용자 그룹 번호: ").append(userHeader.getUserGroupNo()).append("\n");
                    result.append("전체 헤더 정보: ").append(userHeader.toString()).append("\n");
                } else {
                    result.append("ProWorks 사용자 헤더가 존재하지 않습니다.\n");
                }
            } catch (Exception e) {
                result.append("ProWorks 사용자 헤더 조회 중 오류 발생: ").append(e.getMessage()).append("\n");
            }
            
            // 3. 세션 속성 확인
            result.append("\n=== 세션 속성 확인 ===\n");
            if (httpSession != null) {
                java.util.Enumeration<String> attributeNames = httpSession.getAttributeNames();
                while (attributeNames.hasMoreElements()) {
                    String attributeName = attributeNames.nextElement();
                    Object attributeValue = httpSession.getAttribute(attributeName);
                    result.append("속성명: ").append(attributeName).append(", 값: ").append(attributeValue).append("\n");
                }
            }
            
            AppLog.debug("세션 상태 확인 결과: " + result.toString());
            
        } catch (Exception e) {
            result.append("세션 확인 중 오류 발생: ").append(e.getMessage()).append("\n");
            AppLog.error("세션 확인 중 오류", e);
        }
        
        return result.toString();
    }
}
