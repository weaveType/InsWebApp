package com.demo.proworks.domain.user.service;

import java.util.List;

import com.demo.proworks.common.enumType.DevMbti;
import com.demo.proworks.domain.user.vo.UserVo;

/**
 * @subject : 일반회원 관련 처리를 담당하는 인터페이스
 * @description : 일반회원 관련 처리를 담당하는 인터페이스
 * @author : Inswave
 * @since : 2025/07/03
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/03 Inswave 최초 생성
 * 
 */
public interface UserService {

	/**
	 * 일반회원 페이징 처리하여 목록을 조회한다.
	 *
	 * @param userVo 일반회원 UserVo
	 * @return 일반회원 목록 List<UserVo>
	 * @throws Exception
	 */
	public List<UserVo> selectListUser(UserVo userVo) throws Exception;

	/**
	 * 조회한 일반회원 전체 카운트
	 * 
	 * @param userVo 일반회원 UserVo
	 * @return 일반회원 목록 전체 카운트
	 * @throws Exception
	 */
	public long selectListCountUser(UserVo userVo) throws Exception;

	/**
	 * 일반회원를 상세 조회한다.
	 *
	 * @param userVo 일반회원 UserVo
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	public UserVo selectUser(UserVo userVo) throws Exception;

	/**
	 * 일반회원를 등록 처리 한다.
	 *
	 * @param userVo 일반회원 UserVo
	 * @return 번호
	 * @throws Exception
	 */
	public int insertUser(UserVo userVo) throws Exception;

	/**
	 * 일반회원를 갱신 처리 한다.
	 *
	 * @param userVo 일반회원 UserVo
	 * @return 번호
	 * @throws Exception
	 */
	public int updateUser(UserVo userVo) throws Exception;

	/**
	 * 일반회원를 삭제 처리 한다.
	 *
	 * @param userVo 일반회원 UserVo
	 * @return 번호
	 * @throws Exception
	 */
	public int deleteUser(UserVo userVo) throws Exception;

	/**
	 * 일반회원을 이메일로 상세조회한다.
	 *
	 * @param userVo 일반회원 UserVo
	 * @return 번호
	 * @throws Exception
	 */
	public UserVo selectUserByEmail(UserVo userVo) throws Exception;

	/**
	 * userId를 통해 개발자용 MBTI를 가져온다
	 *
	 * @param userId 유저의 id
	 * @return MBTI 종류
	 * @throws Exception
	 */
	public DevMbti selectDevMbti(int userId) throws Exception;

}
