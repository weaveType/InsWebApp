package com.demo.proworks.domain.user.dao;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.domain.user.vo.ApplicantDetailVo;
import com.demo.proworks.domain.user.vo.ApplicantListVo;
import com.demo.proworks.domain.user.vo.ApplicantVo;
import com.demo.proworks.domain.user.vo.MatchingCheckedVo;
import com.demo.proworks.domain.user.vo.ScoutDetailVo;
import com.demo.proworks.domain.user.vo.ScoutListVo;
import com.demo.proworks.domain.user.vo.ScoutVo;
import com.demo.proworks.domain.user.vo.UserInfoVo;
import com.demo.proworks.domain.user.vo.UserVo;
import com.demo.proworks.common.enumType.DevMbti;
import com.demo.proworks.domain.user.dao.UserDAO;

/**
 * @subject : 일반회원 관련 처리를 담당하는 DAO
 * @description : 일반회원 관련 처리를 담당하는 DAO
 * @author : Inswave
 * @since : 2025/07/03
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/03 Inswave 최초 생성
 * 
 */
@Repository("userDAO")
public class UserDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

	/**
	 * 일반회원 상세 조회한다.
	 * 
	 * @param UserVo 일반회원
	 * @return UserVo 일반회원
	 * @throws ElException
	 */
	public UserVo selectUser(UserVo vo) throws ElException {
		return (UserVo) selectByPk("com.demo.proworks.domain.user.selectUser", vo);
	}

	/**
	 * 페이징을 처리하여 일반회원 목록조회를 한다.
	 * 
	 * @param UserVo 일반회원
	 * @return List<UserVo> 일반회원
	 * @throws ElException
	 */
	public List<UserVo> selectListUser(UserVo vo) throws ElException {
		return (List<UserVo>) list("com.demo.proworks.domain.user.selectListUser", vo);
	}

	/**
	 * 일반회원 목록 조회의 전체 카운트를 조회한다.
	 * 
	 * @param UserVo 일반회원
	 * @return 일반회원 조회의 전체 카운트
	 * @throws ElException
	 */
	public long selectListCountUser(UserVo vo) throws ElException {
		return (Long) selectByPk("com.demo.proworks.domain.user.selectListCountUser", vo);
	}

	/**
	 * 일반회원를 등록한다.
	 * 
	 * @param UserVo 일반회원
	 * @return 번호
	 * @throws ElException
	 */
	public int insertUser(UserVo vo) throws ElException {

		// 암호화 처리
		String hashedPassword = BCrypt.hashpw(vo.getPassword(), BCrypt.gensalt(12));
		vo.setPassword(hashedPassword);
		if (vo.getRole().equals("USER")) {
			vo.setRoleId(1);
		} else if (vo.getRole().equals("COMPANY")) {
			vo.setRoleId(2);
		} else if (vo.getRole().equals("ADMIN")) {
			vo.setRoleId(3);
		}

		return insert("com.demo.proworks.domain.user.insertUser", vo);
	}

	/**
	 * 일반회원를 갱신한다.
	 * 
	 * @param UserVo 일반회원
	 * @return 번호
	 * @throws ElException
	 */
	public int updateUser(UserVo vo) throws ElException {
		return update("com.demo.proworks.domain.user.updateUser", vo);
	}

	/**
	 * 일반회원를 삭제한다.
	 * 
	 * @param UserVo 일반회원
	 * @return 번호
	 * @throws ElException
	 */
	public int deleteUser(UserVo vo) throws ElException {
		return delete("com.demo.proworks.domain.user.deleteUser", vo);
	}

	/**
	 * 이메일로 일반회원을 상세 조회한다.
	 * 
	 * @param UserVo 일반회원
	 * @return UserVo 일반회원
	 * @throws ElException
	 */
	public UserVo selectUserByEmail(UserVo vo) throws ElException {
		return (UserVo) selectByPk("com.demo.proworks.domain.user.selectUserByEmail", vo);
	}

	/**
	 * 이메일로 일반회원을 상세 조회한다.
	 * 
	 * @param UserVo 일반회원
	 * @return UserVo 일반회원
	 * @throws ElException
	 */
	public DevMbti selectDevMbti(int userId) throws ElException {
		return (DevMbti) selectByPk("com.demo.proworks.domain.user.selectDevMbti", userId);
	}

	/**
	 * 이메일 중복 여부를 확인한다.
	 * 
	 * @param email 확인할 이메일
	 * @return 중복 여부 (true: 중복, false: 사용 가능)
	 * @throws ElException
	 */
	public boolean checkEmailDuplicate(String email) throws ElException {
		System.out.println("DEBUG - checkEmailDuplicate: 실제 DB 조회 - " + email);

		// 실제 DB에서 이메일 중복 개수 조회 (MariaDB COUNT()는 Long 반환)
		Long count = (Long) selectByPk("com.demo.proworks.domain.user.checkEmailDuplicateCount", email);

		// count가 0보다 크면 이미 존재하는 이메일 (중복)
		return count != null && count > 0;
	}

	/**
	 * 일반 사용자 회원가입을 처리한다.
	 * 
	 * @param UserVo 일반회원
	 * @return 가입된 사용자 ID
	 * @throws ElException
	 */
	public int registerUser(UserVo vo) throws ElException {
		// 비밀번호 암호화
		String hashedPassword = BCrypt.hashpw(vo.getPassword(), BCrypt.gensalt(12));
		vo.setPassword(hashedPassword);

		// 일반 사용자는 개발자 권한(role_id = 1)으로 설정
		vo.setRoleId(1);

		// INSERT 실행 (useGeneratedKeys="true"로 설정되어 있어서 vo.userId에 생성된 ID가 설정됨)
		insert("com.demo.proworks.domain.user.registerUser", vo);

		// 생성된 사용자 ID 반환
		return vo.getUserId();
	}

	/**
	 * 사용자의 프로필 이미지를 업데이트한다.
	 * 
	 * @param userId           사용자 ID
	 * @param profileImageName 프로필 이미지 파일명
	 * @return 업데이트 결과 (성공 시 1, 실패 시 0)
	 * @throws ElException
	 */
	public int updateProfileImage(Long userId, String profileImageName) throws ElException {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("profileImageName", profileImageName);

		return update("com.demo.proworks.domain.user.updateProfileImage", params);
	}

	/**
	 * 사용자 ID로 users_info 테이블의 정보를 조회한다.
	 * 
	 * @param UserVo 사용자 정보
	 * @return UserVo 사용자 추가 정보 (없으면 null)
	 * @throws ElException
	 */
	public UserVo selectUserInfoByUserId(UserVo vo) throws ElException {
		return (UserVo) selectByPk("com.demo.proworks.domain.user.selectUserInfoByUserId", vo);
	}

	/**
	 * users_info 테이블에 새로운 사용자 정보를 삽입한다.
	 * 
	 * @param UserVo 사용자 정보
	 * @return 삽입 결과 (성공 시 1, 실패 시 0)
	 * @throws ElException
	 */
	public int insertUserInfo(UserVo vo) throws ElException {
		return insert("com.demo.proworks.domain.user.insertUserInfo", vo);
	}

	/**
	 * users_info 테이블의 사용자 정보를 업데이트한다.
	 * 
	 * @param UserVo 사용자 정보
	 * @return 업데이트 결과 (성공 시 1, 실패 시 0)
	 * @throws ElException
	 */
	public int updateUserInfo(UserVo vo) throws ElException {
		return update("com.demo.proworks.domain.user.updateUserInfo", vo);
	}

	public UserInfoVo selectUserDetail(UserInfoVo vo) throws Exception {
		UserInfoVo test = (UserInfoVo) selectByPk("com.demo.proworks.domain.user.selectUserDetail", vo);
		return (UserInfoVo) selectByPk("com.demo.proworks.domain.user.selectUserDetail", vo);
	}

	/**
	 * 공고에 이력서 지원처리를 한다.
	 *
	 * @param ApplicantVo 페이징 정보, 공고 ID
	 * @return 등록된 행의 수
	 * @throws Exception
	 */
	public List<ApplicantDetailVo> selectUsersByjobPostingId(ApplicantVo vo) throws Exception {
		return (List<ApplicantDetailVo>) list("com.demo.proworks.domain.user.selectUsersByjobPostingId", vo);
	}

	/**
	 * 기업의 매칭유저 검색을 조회한다.
	 *
	 * @param ScoutVo 페이징 정보, 공고 ID
	 * @return ScoutListVo 유저 목록
	 * @throws Exception
	 */

	public List<ScoutDetailVo> getScoutUsersByPostId(ScoutVo vo) throws Exception {
		return (List<ScoutDetailVo>) list("com.demo.proworks.domain.user.getScoutUsersByPostId", vo);
	}
	
	
	/**
	 * 유저의 성향검사 및 코드검사 여부를 가져온다.
	 *
	 * @param MatchingCheckedVo 유저 ID
	 * @return 유저의 성향검사 및 코드검사 여부
	 * @throws Exception
	 */
	public MatchingCheckedVo selectMatchingChecked(MatchingCheckedVo vo) throws Exception {
		return (MatchingCheckedVo) selectByPk("com.demo.proworks.domain.user.selectMatchingChecked", vo);
	}
}
