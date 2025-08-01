package com.demo.proworks.domain.codeanalysis.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.domain.codeanalysis.service.CodeAnalysisService;
import com.demo.proworks.domain.codeanalysis.vo.CodeAnalysisRequestVo;
import com.demo.proworks.domain.codeanalysis.vo.CodeAnalysisResultVo;
import com.demo.proworks.cmmn.ProworksUserHeader;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.log.AppLog;
import com.inswave.elfw.util.ControllerContextUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @subject : 코드 분석 관련 처리를 담당하는 컨트롤러
 * @description : Gemini API를 통한 코드 분석 관련 처리를 담당하는 컨트롤러
 * @author : 이재성
 * @since : 2025/07/04
 */
@Controller
public class CodeAnalysisController {

    /** CodeAnalysisService */
    @Resource(name = "codeAnalysisServiceImpl")
    private CodeAnalysisService codeAnalysisService;

    /**
     * 코드 분석을 수행합니다.
     * 
     * @param request 요청 정보 HttpServletRequest
     * @return 코드 분석 결과
     * @throws Exception
     */
    @ElService(key = "CA0001Analyze")
    @RequestMapping(value = "CA0001Analyze")
    @ElDescription(sub = "코드 분석 수행", desc = "업로드된 Java 코드를 Gemini API로 분석하여 개발자 타입을 분석합니다.")
    public Map<String, Object> analyzeCode(HttpServletRequest request) throws Exception {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        
        try {
            AppLog.debug("코드 분석 요청 수신");
            
            // 사용자 인증 체크
            Long actualUserId = getUserIdFromRequest(request);
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
                AppLog.error("기업 사용자의 코드 분석 접근 차단: " + actualUserId);
                returnMap.put("success", false);
                returnMap.put("message", "죄송합니다. 코드 분석은 개인 사용자만 이용 가능합니다.");
                returnMap.put("errorCode", "ACCESS_DENIED");
                return returnMap;
            }
            
            AppLog.debug("=== 인증된 사용자 ID: " + actualUserId + " ===");
            
            // HttpServletRequest에서 직접 JSON 데이터 읽기
            String jsonData = getJsonDataFromRequest(request);
            AppLog.debug("수신된 JSON 데이터: " + jsonData);
            
            // JSON 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonData);
            
            // CodeAnalysisRequestVo 생성 및 데이터 설정
            CodeAnalysisRequestVo requestVo = new CodeAnalysisRequestVo();
            
            // 인증된 사용자 ID 사용 (클라이언트에서 온 userId 대신)
            requestVo.setTypeId(actualUserId);
            
            if (jsonNode.has("modelFile")) {
                requestVo.setModelFile(jsonNode.get("modelFile").asText());
            }
            if (jsonNode.has("controllerFile")) {
                requestVo.setControllerFile(jsonNode.get("controllerFile").asText());
            }
            if (jsonNode.has("serviceFile")) {
                requestVo.setServiceFile(jsonNode.get("serviceFile").asText());
            }
            if (jsonNode.has("repositoryFile")) {
                requestVo.setRepositoryFile(jsonNode.get("repositoryFile").asText());
            }
            if (jsonNode.has("modelFileName")) {
                requestVo.setModelFileName(jsonNode.get("modelFileName").asText());
            }
            if (jsonNode.has("controllerFileName")) {
                requestVo.setControllerFileName(jsonNode.get("controllerFileName").asText());
            }
            if (jsonNode.has("serviceFileName")) {
                requestVo.setServiceFileName(jsonNode.get("serviceFileName").asText());
            }
            if (jsonNode.has("repositoryFileName")) {
                requestVo.setRepositoryFileName(jsonNode.get("repositoryFileName").asText());
            }
            
            AppLog.debug("파싱된 RequestVo: " + requestVo.toString());
            AppLog.debug("코드 분석 시작 - 타입ID: " + requestVo.getTypeId());
            
            // 코드 분석 수행
            CodeAnalysisResultVo result = codeAnalysisService.analyzeCode(requestVo);
            
            AppLog.debug("코드 분석 완료 - 결과 타입: " + result.getTypeCode());
            
            // 결과를 Map으로 변환
            returnMap.put("analysisId", result.getAnalysisId());
            returnMap.put("typeId", result.getTypeId());
            returnMap.put("analysisResult", result.getAnalysisResult());
            returnMap.put("typeCode", result.getTypeCode());
            returnMap.put("developmentStyleScore", result.getDevelopmentStyleScore());
            returnMap.put("developerPreferenceScore", result.getDeveloperPreferenceScore());
            returnMap.put("confidenceScore", result.getConfidenceScore());
            returnMap.put("createdAt", result.getCreatedAt());
            returnMap.put("language", result.getLanguage());
            
            return returnMap;
            
        } catch (Exception e) {
            AppLog.error("코드 분석 중 오류 발생", e);
            throw new Exception("코드 분석 처리 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    /**
     * 개발자 유형의 최신 코드 분석 결과를 조회합니다.
     * 
     * @param request 요청 정보 HttpServletRequest
     * @return 코드 분석 결과
     * @throws Exception
     */
    @ElService(key = "CA0001Result")
    @RequestMapping(value = "CA0001Result")
    @ElDescription(sub = "코드 분석 결과 조회", desc = "개발자 유형의 최신 코드 분석 결과를 조회합니다.")
    public Map<String, Object> getAnalysisResult(HttpServletRequest request) throws Exception {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        
        try {
            // 사용자 인증 체크
            Long actualUserId = getUserIdFromRequest(request);
            if (actualUserId == null) {
                AppLog.error("사용자 정보를 가져올 수 없습니다. 로그인이 필요합니다.");
                returnMap.put("success", false);
                returnMap.put("message", "로그인이 필요합니다. 사용자 정보를 확인할 수 없습니다.");
                returnMap.put("errorCode", "AUTH_REQUIRED");
                return returnMap;
            }
            
            AppLog.debug("코드 분석 결과 조회 - 인증된 사용자ID: " + actualUserId);
            
            CodeAnalysisResultVo result = codeAnalysisService.getAnalysisResult(actualUserId);
            
            if (result == null) {
                AppLog.debug("분석 결과가 없습니다 - 타입ID: " + actualUserId);
                returnMap.put("hasResult", false);
            } else {
                AppLog.debug("분석 결과 조회 완료 - 타입: " + result.getTypeCode());
                
                // 결과를 Map으로 변환
                returnMap.put("hasResult", true);
                returnMap.put("analysisId", result.getAnalysisId());
                returnMap.put("typeId", result.getTypeId());
                returnMap.put("analysisResult", result.getAnalysisResult());
                returnMap.put("typeCode", result.getTypeCode());
                returnMap.put("developmentStyleScore", result.getDevelopmentStyleScore());
                returnMap.put("developerPreferenceScore", result.getDeveloperPreferenceScore());
                returnMap.put("confidenceScore", result.getConfidenceScore());
                returnMap.put("createdAt", result.getCreatedAt());
            }
            
            return returnMap;
            
        } catch (Exception e) {
            AppLog.error("코드 분석 결과 조회 중 오류 발생", e);
            throw new Exception("코드 분석 결과 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
    
    /**
     * HttpServletRequest에서 사용자 ID를 가져옵니다.
     * ProWorks5의 다양한 방법을 시도합니다.
     * 
     * @param request HttpServletRequest
     * @return 사용자 ID 또는 null
     */
    private Long getUserIdFromRequest(HttpServletRequest request) {
        Long actualUserId = null;
        
        // 방법 1: ProWorks 헤더에서 가져오기
        try {
            ProworksUserHeader userHeader = (ProworksUserHeader) ControllerContextUtil.getUserHeader();
            if (userHeader != null && userHeader.getAccountId() > 0) {
                actualUserId = (long) userHeader.getAccountId();
                AppLog.debug("방법1 성공 - ProWorks 헤더에서 accountId: " + actualUserId);
                return actualUserId;
            } else {
                AppLog.debug("방법1 실패 - ProWorks 헤더 null이거나 accountId 없음");
            }
        } catch (Exception e) {
            AppLog.debug("방법1 실패 - ProWorks 헤더 조회 오류: " + e.getMessage());
        }
        
        // 방법 2: HttpSession에서 가져오기
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                // 방법2a: userId 직접 조회
                Integer sessionUserId = (Integer) session.getAttribute("userId");
                if (sessionUserId != null) {
                    actualUserId = sessionUserId.longValue();
                    AppLog.debug("방법2a 성공 - HttpSession userId: " + actualUserId);
                    return actualUserId;
                } else {
                    // 방법2b: userHeader에서 accountId 가져오기
                    try {
                        ProworksUserHeader sessionUserHeader = (ProworksUserHeader) session.getAttribute("userHeader");
                        if (sessionUserHeader != null && sessionUserHeader.getAccountId() > 0) {
                            actualUserId = (long) sessionUserHeader.getAccountId();
                            AppLog.debug("방법2b 성공 - HttpSession userHeader.accountId: " + actualUserId);
                            return actualUserId;
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
        
        // 방법 3: 쿠키에서 직접 파싱
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("userInfo".equals(cookie.getName())) {
                        try {
                            String userInfoJson = cookie.getValue();
                            AppLog.debug("방법3 시도 - 원본 쿠키 값: " + userInfoJson);
                            
                            // URL 인코딩된 데이터 강제 디코딩
                            if (userInfoJson.startsWith("%7B")) {
                                userInfoJson = java.net.URLDecoder.decode(userInfoJson, "UTF-8");
                                AppLog.debug("방법3 - URL 디코딩 성공: " + userInfoJson);
                            }
                            
                            ObjectMapper cookieMapper = new ObjectMapper();
                            JsonNode userInfoNode = cookieMapper.readTree(userInfoJson);
                            if (userInfoNode.has("accountId")) {
                                actualUserId = userInfoNode.get("accountId").asLong();
                                AppLog.debug("방법3 성공 - 쿠키에서 accountId: " + actualUserId);
                                return actualUserId;
                            } else {
                                AppLog.debug("방법3 실패 - 쿠키에 accountId 없음");
                            }
                        } catch (Exception parseEx) {
                            AppLog.warn("방법3 실패 - JSON 파싱 오류: " + parseEx.getMessage());
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
        
        return null; // 모든 방법 실패
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
} 