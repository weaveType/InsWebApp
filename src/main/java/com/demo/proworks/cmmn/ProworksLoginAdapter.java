package com.demo.proworks.cmmn;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;

import com.demo.proworks.domain.user.service.UserService;
import com.demo.proworks.domain.user.vo.UserVo;
import com.inswave.elfw.exception.ElException;
import com.inswave.elfw.log.AppLog;
import com.inswave.elfw.login.DefaultLoginAdapter;
import com.inswave.elfw.login.LoginAdapter;
import com.inswave.elfw.login.LoginException;
import com.inswave.elfw.login.LoginInfo;
import com.inswave.elfw.util.ElBeanUtils;

/**
 * @subject : ProworksLoginAdapter.java
 * @description : 프로젝트 로그인 어댑터
 * @author : 개발팀
 * @since : 2025/05/19
 * @modification ===========================================================
 *               DATE AUTHOR NOTE
 *               ===========================================================
 *               2025/05/19 샘플개발팀 최초 생성
 * 
 */
public class ProworksLoginAdapter extends LoginAdapter {

	/**
	 * 데모용 로그인 어댑터의 생성자
	 * 
	 * @param adapterInfoMap Adapter 정보
	 */
	public ProworksLoginAdapter(Map<String, Object> adapterInfoMap) {
		super(adapterInfoMap);
	}

	/**
	 * 데모용 로그인 처리를 담당하는 구현체 메소드. 프레임워크 DefaultLoginAdapter 추상클래스의 로그인 구현체 메소드
	 * 
	 * @param request
	 * @param id
	 * @param params  기타 동적 파라미터에 추가할 수 있다.(ex. 서비스 구현체 )
	 * @return LoginInfo
	 * @author : 김지훈
	 * @throws LoginException
	 */
	@Override
	public LoginInfo login(HttpServletRequest request, String email, Object... params) throws LoginException {
		// 로그인 체크 수행
		try {
			String pw = (String) params[0];
			UserService userService = (UserService) ElBeanUtils.getBean("userServiceImpl");
			UserVo userVo = new UserVo();

			userVo.setEmail(email);
			UserVo resUserVo = userService.selectUserByEmail(userVo);
			if (resUserVo == null) {
				System.out.println("[DEBUG] 사용자 조회 결과 없음 (null)");
				throw new LoginException("EL.ERROR.LOGIN.0001");
			}
			// 비밀번호 확인
			String resPw = String.valueOf(resUserVo.getPassword());
			if (pw == null || !BCrypt.checkpw(pw, resPw)) {
				throw new LoginException("EL.ERROR.LOGIN.0002");
			}

		} catch (NumberFormatException e) {
			AppLog.error("login Error1", e);
			throw new LoginException("EL.ERROR.LOGIN.0001");
		} catch (ElException e) {
			AppLog.error("login Error2", e);
			throw e;
		} catch (Exception e) {
			AppLog.error("login Error3", e);
			throw new LoginException("EL.ERROR.LOGIN.0003");
		}

		// 3. 로그인 성공 설정
		LoginInfo info = new LoginInfo();
		info.setSuc(true);

		return info;
	}

	/**
	 * 데모용 로그아웃 처리를 담당하는 구현체 메소드. 프레임워크 DefaultLoginAdapter 추상클래스의 로그아웃 구현체 메소드
	 * 
	 * @param request
	 * @param id
	 * @param params  기타 동적 파라미터에 추가할 수 있다.
	 * @return LoginInfo
	 * @author : 김지훈
	 * @throws LoginException
	 */
	@Override
	public LoginInfo logout(HttpServletRequest request, String email, Object... params) throws LoginException {
		LoginInfo info = new LoginInfo();
		try {
			// 1. 세션 무효화
			if (request.getSession(false) != null) {
				request.getSession(false).invalidate();
				AppLog.debug("[Logout] 세션이 성공적으로 무효화되었습니다.");
			} else {
				AppLog.debug("[Logout] 활성화된 세션이 없습니다.");
			}

			// 2. 로그아웃 성공 설정
			info.setSuc(true);
			AppLog.debug("[Logout] Proworks Logout 성공.....");

		} catch (Exception e) {
			AppLog.error("[Logout] 로그아웃 중 오류 발생", e);
			throw new LoginException("EL.ERROR.LOGOUT.0001", e);
		}
		return info;
	}

}
