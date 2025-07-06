package com.demo.proworks.cmmn;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.inswave.elfw.adapter.AdapterException;
import com.inswave.elfw.exception.ElException;
import com.inswave.elfw.log.AppLog;
import com.inswave.elfw.session.SessionDataAdapter;
import com.inswave.elfw.util.ElBeanUtils;
import com.demo.proworks.domain.user.service.UserService;
import com.demo.proworks.domain.user.vo.UserVo;
import com.demo.proworks.emp.service.EmpService;
import com.demo.proworks.emp.vo.EmpVo;

/**
 * @Class Name : ProworksSessionDataAdapter.java
 * @Description : 프로젝트 세션 데이터 어댑터 - 로그인 후 사용자 헤더 정보를 Setting 한다.
 * @Modification Information
 * @ @ 수정일 수정자 수정내용 @ --------- --------- ------------------------------- @
 *   2019.08.01 EL개발팀 최초생성
 * 
 * @author EL개발팀
 * @since 2013.08.01
 * @version 1.0
 * @see
 * 
 *      Copyright (C) by Inswave All right reserved.
 */
public class ProworksSessionDataAdapter extends SessionDataAdapter {
	/**
	 * SessionAdapter 생성자이다.
	 * 
	 * @param adapterInfoMap Adapter 정보
	 */
	public ProworksSessionDataAdapter(Map<String, Object> adapterInfoMap) {
		super(adapterInfoMap);
	}

	/**
	 * 데모용 세션 터이터의 로드를 담당하는 구현체 메소드. - 프레임워크 SessionDataAdapter 추상클래스의 세션 데이터를 Set
	 * 하는 구현체 메소드 - 프로젝트에 필요한 헤더 정보를 세팅한다. - 해당 헤더 정보는 로그인 후에 사용가능하다.
	 * 
	 * @param request HttpServletRequest
	 * @param id
	 * @param obj     기타 동적 파라미터에 추가할 수 있다.(ex. 서비스 구현체 )
	 * @return ProworksUserHeader
	 * @throws AdapterException
	 */
	@Override
	public ProworksUserHeader setSessionData(HttpServletRequest request, String email, Object... obj)
			throws AdapterException {

		// 로그인 후에 id 기반으로 세션 정보를 세팅하여 반환한다.
		ProworksUserHeader userHeader = new ProworksUserHeader();
		userHeader.setEmail(email);
		try {
			UserService userService = (UserService) ElBeanUtils.getBean("userServiceImpl");
			UserVo userVo = new UserVo();

			userVo.setEmail(email);
			UserVo resUserVo = userService.selectUserByEmail(userVo);

			if (resUserVo == null) {
				throw new AdapterException("EL.ERROR.LOGIN.0004", new String[] { email });
			}

			// 필요정보 추가영역
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> getUserId : " + resUserVo.getUserId());
			userHeader.setUserId(resUserVo.getUserId());
			
			int roleId = resUserVo.getRoleId();

			switch (roleId) {
			case 1:
				userHeader.setRole("USER");
				break;
			case 2:
				userHeader.setRole("COMPANY");
				break;
			case 3:
				userHeader.setRole("ADMIN");
				break;
			default:
				userHeader.setRole("UNKNOWN"); // 예외 처리: 유효하지 않은 roleId 값에 대해 처리
				break;
			}

			// 세션 데이터 설정 완료 로그
			AppLog.debug("=== 세션 데이터 설정 완료 ===");
			AppLog.debug("사용자 이메일: " + email);
			AppLog.debug("권한 ID: " + resUserVo.getRole());
			AppLog.debug("설정된 UserHeader: " + userHeader.toString());

		} catch (ElException e) {
			AppLog.error("setSessionData Error1", e);
			throw e;
		} catch (Exception e) {
			AppLog.error("setSessionData Error2", e);
			throw new AdapterException("EL.ERROR.LOGIN.0005");
		}

		return userHeader;
	}

}
