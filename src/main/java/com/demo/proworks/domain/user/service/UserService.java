package com.demo.proworks.domain.user.service;

import java.util.List;

import com.demo.proworks.common.enumType.DevMbti;
import com.demo.proworks.domain.user.vo.ApplicantDetailVo;
import com.demo.proworks.domain.user.vo.ApplicantListVo;
import com.demo.proworks.domain.user.vo.ApplicantVo;
import com.demo.proworks.domain.user.vo.ScoutListVo;
import com.demo.proworks.domain.user.vo.ScoutVo;
import com.demo.proworks.domain.user.vo.UserInfoVo;
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

	/**
	 * 이메일 중복 여부를 확인한다.
	 *
	 * @param email 확인할 이메일
	 * @return 중복 여부 (true: 중복, false: 사용 가능)
	 * @throws Exception
	 */
	public boolean checkEmailDuplicate(String email) throws Exception;

	/**
	 * 일반 사용자 회원가입을 처리한다.
	 *
	 * @param userVo 회원가입 정보 UserVo
	 * @return 가입된 사용자 ID
	 * @throws Exception
	 */
	public int registerUser(UserVo userVo) throws Exception;

	/**
	 * 사용자의 프로필 이미지를 업데이트한다.
	 *
	 * @param userId           사용자 ID
	 * @param profileImageName 프로필 이미지 파일명
	 * @return 업데이트 결과 (성공 시 1, 실패 시 0)
	 * @throws Exception
	 */
	public int updateProfileImage(Long userId, String profileImageName) throws Exception;

	/**
	 * 사용자의 추가 정보를 users_info 테이블에 저장하거나 업데이트한다.
	 *
	 * @param userVo 사용자 추가 정보 UserVo
	 * @return 처리 결과 (성공 시 1, 실패 시 0)
	 * @throws Exception
	 */
	public int insertOrUpdateUserInfo(UserVo userVo) throws Exception;

	/**
	 * 사용자의 users_info 정보를 업데이트한다.
	 *
	 * @param userVo 사용자 정보 UserVo
	 * @return 업데이트 결과 (성공 시 1, 실패 시 0)
	 * @throws Exception
	 */
	public int updateUserInfo(UserVo userVo) throws Exception;

	/**
	 * 사용자 ID로 users_info 테이블의 정보를 조회한다.
	 *
	 * @param userVo 사용자 정보 UserVo
	 * @return UserVo 사용자 추가 정보 (없으면 null)
	 * @throws Exception
	 */
	public UserVo selectUserInfoByUserId(UserVo userVo) throws Exception;

	/**
	 * 사용자 ID로 users_info 테이블 및 users 의 정보를 조회한다.
	 *
	 * @param UserInfoVo 사용자 상세정보에 필요한 Vo
	 * @return UserInfoVo 사용자 추가 정보 (없으면 null)
	 * @throws Exception
	 */
	public UserInfoVo selectUserDetail(UserInfoVo userInfoVo) throws Exception;

	/**
	 * 공고에 이력서 지원처리를 한다.
	 *
	 * @param ApplicantVo 페이징 정보, 공고 ID
	 * @return 등록된 행의 수
	 * @throws Exception
	 */
	public List<ApplicantDetailVo> selectUsersByjobPostingId(ApplicantVo applicantVo) throws Exception;

	/**
	 * 기업의 매칭유저 검색을 조회한다.
	 *
	 * @param ScoutVo 페이징 정보, 공고 ID
	 * @return ScoutListVo 유저 목록
	 * @throws Exception
	 */
	public ScoutListVo getScoutUsersByPostId(ScoutVo scoutVo) throws Exception;

}
