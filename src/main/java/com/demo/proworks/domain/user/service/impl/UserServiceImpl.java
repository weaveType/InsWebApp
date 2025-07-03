package com.demo.proworks.domain.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.domain.user.service.UserService;
import com.demo.proworks.domain.user.vo.UserVo;
import com.demo.proworks.domain.user.dao.UserDAO;

/**
 * @subject : 일반회원 관련 처리를 담당하는 ServiceImpl
 * @description : 일반회원 관련 처리를 담당하는 ServiceImpl
 * @author : Inswave
 * @since : 2025/07/03
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/03 Inswave 최초 생성
 * 
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Resource(name = "userDAO")
	private UserDAO userDAO;

	@Resource(name = "messageSource")
	private MessageSource messageSource;

	/**
	 * 일반회원 목록을 조회합니다.
	 *
	 * @process 1. 일반회원 페이징 처리하여 목록을 조회한다. 2. 결과 List<UserVo>을(를) 리턴한다.
	 * 
	 * @param userVo 일반회원 UserVo
	 * @return 일반회원 목록 List<UserVo>
	 * @throws Exception
	 */
	public List<UserVo> selectListUser(UserVo userVo) throws Exception {
		List<UserVo> list = userDAO.selectListUser(userVo);

		return list;
	}

	/**
	 * 조회한 일반회원 전체 카운트
	 *
	 * @process 1. 일반회원 조회하여 전체 카운트를 리턴한다.
	 * 
	 * @param userVo 일반회원 UserVo
	 * @return 일반회원 목록 전체 카운트
	 * @throws Exception
	 */
	public long selectListCountUser(UserVo userVo) throws Exception {
		return userDAO.selectListCountUser(userVo);
	}

	/**
	 * 일반회원를 상세 조회한다.
	 *
	 * @process 1. 일반회원를 상세 조회한다. 2. 결과 UserVo을(를) 리턴한다.
	 * 
	 * @param userVo 일반회원 UserVo
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	public UserVo selectUser(UserVo userVo) throws Exception {
		UserVo resultVO = userDAO.selectUser(userVo);

		return resultVO;
	}

	/**
	 * 일반회원를 등록 처리 한다.
	 *
	 * @process 1. 일반회원를 등록 처리 한다.
	 * 
	 * @param userVo 일반회원 UserVo
	 * @return 번호
	 * @throws Exception
	 */
	public int insertUser(UserVo userVo) throws Exception {
		return userDAO.insertUser(userVo);
	}

	/**
	 * 일반회원를 갱신 처리 한다.
	 *
	 * @process 1. 일반회원를 갱신 처리 한다.
	 * 
	 * @param userVo 일반회원 UserVo
	 * @return 번호
	 * @throws Exception
	 */
	public int updateUser(UserVo userVo) throws Exception {
		return userDAO.updateUser(userVo);
	}

	/**
	 * 일반회원를 삭제 처리 한다.
	 *
	 * @process 1. 일반회원를 삭제 처리 한다.
	 * 
	 * @param userVo 일반회원 UserVo
	 * @return 번호
	 * @throws Exception
	 */
	public int deleteUser(UserVo userVo) throws Exception {
		return userDAO.deleteUser(userVo);
	}

	/**
	 * 이메일로 일반회원을 상세조회한다.
	 *
	 * @param userVo 일반회원 UserVo
	 * @return 번호
	 * @throws Exception
	 */
	public UserVo selectUserByEmail(UserVo userVo) throws Exception {
		return userDAO.selectUserByEmail(userVo);

	}
}
