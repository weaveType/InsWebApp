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
import com.demo.proworks.domain.user.vo.ApplicationHistoryVo;
import com.demo.proworks.domain.user.vo.ApplicationStatsListVo;
import com.demo.proworks.domain.user.vo.ApplicationStatusVo;
import com.demo.proworks.common.enumType.DevMbti;
import com.demo.proworks.common.vo.AccountIdVo;
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
	 * 일반회원 비밀번호를 갱신한다.
	 * 
	 * @param UserVo 일반회원
	 * @return 번호
	 * @throws ElException
	 */
	public int updatePassword(UserVo vo) throws ElException {
		return update("com.demo.proworks.domain.user.updatePassword", vo);
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

	/**
	 * 사용자의 이력서 파일명을 업데이트한다.
	 * 
	 * @param userVo 사용자 정보 UserVo (userId와 resumeFileName 필드 필수)
	 * @return 업데이트 결과 (성공 시 1, 실패 시 0)
	 * @throws ElException
	 */
	public int updateResumeFileName(UserVo vo) throws ElException {
		return update("com.demo.proworks.domain.user.updateResumeFileName", vo);
	}

	/**
	 * 지원현황 목록을 조회한다.
	 *
	 * @param applicationHistoryVo 지원현황 조회 조건
	 * @return 지원현황 목록
	 * @throws Exception
	 */
	public List<ApplicationHistoryVo> selectApplicationHistoryList(ApplicationHistoryVo vo) throws Exception {
		return (List<ApplicationHistoryVo>) list("com.demo.proworks.domain.user.selectApplicationHistoryList", vo);
	}

	/**
	 * 지원현황 통계를 조회한다. (상태별 GROUP BY)
	 *
	 * @param applicationHistoryVo 지원현황 조회 조건
	 * @return 지원현황 통계 목록
	 * @throws Exception
	 */
	public List<ApplicationHistoryVo> selectApplicationHistoryStats(ApplicationHistoryVo vo) throws Exception {
		return (List<ApplicationHistoryVo>) list("com.demo.proworks.domain.user.selectApplicationHistoryStats", vo);
	}

	/**
	 * 지원현황 총 개수를 조회한다.
	 *
	 * @param applicationHistoryVo 지원현황 조회 조건
	 * @return 지원현황 총 개수
	 * @throws Exception
	 */
	public long selectApplicationHistoryCount(ApplicationHistoryVo vo) throws Exception {
		return (Long) selectByPk("com.demo.proworks.domain.user.selectApplicationHistoryCount", vo);
	}

	/**
	 * 지원현황 상세정보를 조회한다.
	 *
	 * @param applicationHistoryVo 지원현황 조회 조건
	 * @return 지원현황 상세정보
	 * @throws Exception
	 */
	public ApplicationHistoryVo selectApplicationHistoryDetail(ApplicationHistoryVo vo) throws Exception {
		return (ApplicationHistoryVo) selectByPk("com.demo.proworks.domain.user.selectApplicationHistoryDetail", vo);
	}

	/**
	 * 지원현황을 등록한다.
	 *
	 * @param applicationHistoryVo 지원현황 정보
	 * @return 등록 결과
	 * @throws Exception
	 */
	public int insertApplicationHistory(ApplicationHistoryVo vo) throws Exception {
		return (Integer) insert("com.demo.proworks.domain.user.insertApplicationHistory", vo);
	}

	/**
	 * 지원현황을 수정한다.
	 *
	 * @param applicationHistoryVo 지원현황 정보
	 * @return 수정 결과
	 * @throws Exception
	 */
	public int updateApplicationHistory(ApplicationHistoryVo vo) throws Exception {
		return (Integer) update("com.demo.proworks.domain.user.updateApplicationHistory", vo);
	}

	/**
	 * 지원현황을 삭제한다.
	 *
	 * @param applicationHistoryVo 지원현황 정보
	 * @return 삭제 결과
	 * @throws Exception
	 */
	public int deleteApplicationHistory(ApplicationHistoryVo vo) throws Exception {
		return (Integer) delete("com.demo.proworks.domain.user.deleteApplicationHistory", vo);
	}

	/**
	 * 기술스택 매핑 정보를 삭제한다.
	 *
	 * @param vo userId 만 채워진 UserVo
	 * @return 삭제된 행 수
	 * @throws Exception
	 */
	public int deleteUserTechStacks(UserVo vo) throws Exception {
		return (Integer) delete("com.demo.proworks.domain.user.deleteUserTechStacks", vo);
	}

	/**
	 * 
	 * 기술스택 매핑 정보를 일괄 저장한다.
	 * 
	 * ※ `param` 구성 └ userId : Long / Integer └ techStackIdList : List<Long> (예:[36,
	 * 7, 14])
	 * 
	 * @param param 매핑 파라미터
	 * @return 삽입된 행 수
	 * @throws Exception
	 */
	public int insertUserTechStacks(UserVo vo) throws Exception {
		return (Integer) insert("com.demo.proworks.domain.user.insertUserTechStacks", vo);
	}

	/**
	 * 사용자-기술스택 매핑 개수를 구한다.
	 *
	 * @param vo userId 필수
	 * @return 매핑 행 수
	 */
	public int countUserTechStacks(UserVo vo) throws Exception {
		return (Integer) selectByPk("com.demo.proworks.domain.user.countUserTechStacks", vo);
	}

	/**
	 * 기업의 매칭유저를 count 한다.
	 *
	 * @param ScoutVo 페이징 정보, 공고 ID
	 * @return ScoutListVo 유저 목록
	 * @throws Exception
	 */
	public int getScoutUserCount(ScoutVo vo) throws Exception {
		return (Integer) selectByPk("com.demo.proworks.domain.user.getScoutUserCount", vo);
	}

	/**
	 * 지원현황 count를 가져온다.
	 *
	 * @param AccountIdVo 로그인한 User Id
	 * @return 총 지원, 진행중, 합격, 불합격 공고 Count
	 * @throws Exception
	 */
	public List<ApplicationStatusVo> getApplicationStats(AccountIdVo vo) throws Exception {
		return (List<ApplicationStatusVo>) list("com.demo.proworks.domain.user.getApplicationStats", vo);

	}
}
