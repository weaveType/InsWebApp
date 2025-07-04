package com.demo.proworks.cmmn;

import javax.servlet.http.HttpServletRequest;

import com.demo.proworks.domain.user.service.UserService;
import com.inswave.elfw.core.UserHeader;
import com.inswave.elfw.exception.UserException;
import com.inswave.elfw.log.AppLog;
import com.inswave.elfw.util.ControllerContextUtil;
import com.inswave.elfw.util.ElBeanUtils;

public class ProworksAuthProcess {

	public ProworksAuthProcess() {

	}

	// 권한 체크 로직
//	public void checkAuth(HttpServletRequest request, String svcId, String inputData) throws UserException{
//	
//		UserHeader userHeader = ControllerContextUtil.getUserHeader();
//		
//		try{
//			if( userHeader != null ) {  // 세션이 존재함 
//				if( userHeader instanceof ProworksUserHeader ) {
//					ProworksUserHeader siteUserHeader = (ProworksUserHeader)userHeader;
//					String email = siteUserHeader.getEmail();
//					
//					// userId 기반으로   ServiceImple 을 사용하여 권한등 체크로직 수행 -> 아래는 ElBeanUtils 를 사용해서 권한 체크 하라는 예시 
//					UserService userService = (UserService)ElBeanUtils.getBean("userServiceImpl");  
//					boolean bCheck = false;
//					
//					//..... 권한 체크 로직 수행 
//					
//					if( false == bCheck) { // 권한 체크 결과 
//					    throw new UserException("ERR.USER.0003");  // 권한이 존재하지 않습니다.
//					}
//				} else {
//					throw new UserException("ERR.USER.0002");  // 세션이 존재하지 않습니다.
//				}
//			} else { /// 세션이 존재하지 않으면 
//				throw new UserException("ERR.USER.0002");  // 세션이 존재하지 않습니다.
//			}
//		}catch(UserException ue){
//			AppLog.error("CommAuthProcess-UserException", ue);
//			throw ue;
//		}catch(Exception e){
//			AppLog.error("CommAuthProcess-Exception", e);
//			throw new UserException("ERR.USER.0003");  // 기타 에러 메시지....
//		}
//	
//	}

	public void checkAuth(HttpServletRequest request, String svcId, String inputData) throws UserException {

		UserHeader userHeader = ControllerContextUtil.getUserHeader();

//    try {
//        if (userHeader != null) {  // 세션이 존재함
//            if (userHeader instanceof ProworksUserHeader) {
//                ProworksUserHeader siteUserHeader = (ProworksUserHeader) userHeader;
//                String email = siteUserHeader.getEmail();
//                String role = siteUserHeader.getRole(); // 가정: ProworksUserHeader에 roleId가 존재한다고 가정
//
//                String requestURI = request.getRequestURI();  // 요청 URL 추출
//
//                boolean bCheck = true;
//
//                // URL별 권한 체크
//                if ("/InsWebApp/CP0001List.pwkjson".equals(requestURI)) {
////                    if (roleId != 2) {
//                        bCheck = false;
////                    }
//                }
//
//                if (!bCheck) {
//                    throw new UserException("ERR.USER.0003"); // 권한이 존재하지 않습니다.
//                }
//
//            } else {
//                throw new UserException("ERR.USER.0002"); // 세션이 존재하지 않습니다.
//            }
//        } else {
//            throw new UserException("ERR.USER.0002"); // 세션이 존재하지 않습니다.
//        }
//    } catch (UserException ue) {
//        AppLog.error("CommAuthProcess-UserException", ue);
//        throw ue;
//    } catch (Exception e) {
//        AppLog.error("CommAuthProcess-Exception", e);
//        throw new UserException("ERR.USER.0003"); // 기타 에러 메시지
//    }
	}

}
