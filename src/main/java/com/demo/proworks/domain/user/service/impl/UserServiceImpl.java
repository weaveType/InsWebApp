package com.demo.proworks.domain.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.domain.user.service.UserService;
import com.demo.proworks.domain.user.vo.ApplicantDetailVo;
import com.demo.proworks.domain.user.vo.ApplicantVo;
import com.demo.proworks.domain.user.vo.MatchingCheckedVo;
import com.demo.proworks.domain.user.vo.ScoutDetailVo;
import com.demo.proworks.domain.user.vo.ScoutListVo;
import com.demo.proworks.domain.user.vo.ScoutVo;
import com.demo.proworks.domain.user.vo.UserInfoVo;
import com.demo.proworks.domain.user.vo.UserVo;
import com.demo.proworks.domain.user.vo.ApplicationHistoryVo;
import com.demo.proworks.common.enumType.DevMbti;
import com.demo.proworks.domain.user.dao.UserDAO;
import org.mindrot.jbcrypt.BCrypt;

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

	/**
	 * userId를 통해 개발자용 MBTI를 가져온다
	 *
	 * @param userId 유저의 id
	 * @return MBTI 종류
	 * @throws Exception
	 */
	public DevMbti selectDevMbti(int userId) throws Exception {
		return userDAO.selectDevMbti(userId);
	}

	/**
	 * 이메일 중복 여부를 확인한다.
	 *
	 * @param email 확인할 이메일
	 * @return 중복 여부 (true: 중복, false: 사용 가능)
	 * @throws Exception
	 */
	public boolean checkEmailDuplicate(String email) throws Exception {
		return userDAO.checkEmailDuplicate(email);
	}

	/**
	 * 일반 사용자 회원가입을 처리한다.
	 *
	 * @param userVo 회원가입 정보 UserVo
	 * @return 가입된 사용자 ID
	 * @throws Exception
	 */
	public int registerUser(UserVo userVo) throws Exception {
		// 이메일 중복 확인
		if (checkEmailDuplicate(userVo.getEmail())) {
			throw new Exception("이미 사용 중인 이메일입니다.");
		}

		return userDAO.registerUser(userVo);
	}

	/**
	 * 사용자의 프로필 이미지를 업데이트한다.
	 *
	 * @param userId           사용자 ID
	 * @param profileImageName 프로필 이미지 파일명
	 * @return 업데이트 결과 (성공 시 1, 실패 시 0)
	 * @throws Exception
	 */
	public int updateProfileImage(Long userId, String profileImageName) throws Exception {
		return userDAO.updateProfileImage(userId, profileImageName);
	}

	/**
	 * 사용자의 추가 정보를 users_info 테이블에 저장하거나 업데이트한다.
	 *
	 * @param userVo 사용자 추가 정보 UserVo
	 * @return 처리 결과 (성공 시 1, 실패 시 0)
	 * @throws Exception
	 */
	public int insertOrUpdateUserInfo(UserVo userVo) throws Exception {
		// 기존 데이터 존재 여부 확인
		UserVo existingInfo = userDAO.selectUserInfoByUserId(userVo);

		if (existingInfo != null) {
			// 기존 데이터가 있으면 업데이트
			userDAO.updateUserInfo(userVo);
		} else {
			// 기존 데이터가 없으면 신규 삽입
			userDAO.insertUserInfo(userVo);
		}

		if (userVo.getTechStackVo() != null) {

			// ① 현재 매핑 개수 조회
			int curCnt = userDAO.countUserTechStacks(userVo);

			if (curCnt != 0) { // 기존의 값이 있다면 삭제
				userDAO.deleteUserTechStacks(userVo);
			}

			// 추가
			userDAO.insertUserTechStacks(userVo);
		}
		return 1;
	}

	/**
	 * 사용자의 users_info 정보를 업데이트한다.
	 *
	 * @param userVo 사용자 정보 UserVo
	 * @return 업데이트 결과 (성공 시 1, 실패 시 0)
	 * @throws Exception
	 */
	public int updateUserInfo(UserVo userVo) throws Exception {
		return userDAO.updateUserInfo(userVo);
	}

	/**
	 * 사용자 ID로 users_info 테이블의 정보를 조회한다.
	 *
	 * @param userVo 사용자 정보 UserVo
	 * @return UserVo 사용자 추가 정보 (없으면 null)
	 * @throws Exception
	 */
	public UserVo selectUserInfoByUserId(UserVo userVo) throws Exception {
		return userDAO.selectUserInfoByUserId(userVo);
	}

	/**
	 * 사용자 ID로 users_info 테이블 및 users 의 정보를 조회한다.
	 *
	 * @param UserInfoVo 사용자 상세정보에 필요한 Vo
	 * @return UserInfoVo 사용자 추가 정보 (없으면 null)
	 * @throws Exception
	 */
	public UserInfoVo selectUserDetail(UserInfoVo userInfoVo) throws Exception {
		return userDAO.selectUserDetail(userInfoVo);
	}

	/**
	 * 공고에 이력서 지원처리를 한다.
	 *
	 * @param ApplicantVo 페이징 정보, 공고 ID
	 * @return 등록된 행의 수
	 * @throws Exception
	 */
	public List<ApplicantDetailVo> selectUsersByjobPostingId(ApplicantVo applicantVo) throws Exception {
		return userDAO.selectUsersByjobPostingId(applicantVo);
	}

	/**
	 * 사용자의 비밀번호를 변경한다.
	 *
	 * @param userId          사용자 ID
	 * @param currentPassword 현재 비밀번호
	 * @param newPassword     새로운 비밀번호
	 * @return 변경 성공 여부 (true: 성공, false: 실패)
	 * @throws Exception
	 */
	@Override
	public boolean updatePassword(int userId, String currentPassword, String newPassword) throws Exception {
		// 현재 사용자 정보 가져오기
		UserVo userVo = new UserVo();
		userVo.setUserId(userId);
		UserVo currentUser = selectUser(userVo);

		if (currentUser == null) {
			return false;
		}

		// 현재 비밀번호 검증
		if (!BCrypt.checkpw(currentPassword, currentUser.getPassword())) {
			return false;
		}

		// 새 비밀번호 해싱
		String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));

		// 비밀번호 업데이트
		userVo.setPassword(hashedPassword);
		int result = userDAO.updatePassword(userVo);

		return result > 0;
	}

	/**
	 * 기업의 매칭유저 검색을 조회한다.
	 *
	 * @param ScoutVo 페이징 정보, 공고 ID
	 * @return ScoutListVo 유저 목록
	 * @throws Exception
	 */
	@Override
	public ScoutListVo getScoutUsersByPostId(ScoutVo scoutVo) throws Exception {
		List<ScoutDetailVo> detailList = userDAO.getScoutUsersByPostId(scoutVo);
//		int totalCount = userDAO.getScoutUserCount(scoutVo);

		ScoutListVo resultVo = new ScoutListVo();
		resultVo.setScoutDetailVo(detailList);
//		resultVo.setTotalCount(totalCount);
		resultVo.setPageIndex(scoutVo.getPageIndex());
		resultVo.setPageSize(scoutVo.getPageSize());

		return resultVo;
	}

	/**
	 * 유저의 성향검사 및 코드검사 여부를 가져온다.
	 *
	 * @param MatchingCheckedVo 유저 ID
	 * @return 유저의 성향검사 및 코드검사 여부
	 * @throws Exception
	 */
	@Override
	public MatchingCheckedVo selectMatchingChecked(MatchingCheckedVo matchingCheckedVo) throws Exception {
		return userDAO.selectMatchingChecked(matchingCheckedVo);
	}

	/**
	 * 사용자의 이력서 파일명을 업데이트한다.
	 *
	 * @param userVo 사용자 정보 UserVo (userId와 resumeFileName 필드 필수)
	 * @return 업데이트 결과 (성공 시 1, 실패 시 0)
	 * @throws Exception
	 */
	@Override
	public int updateResumeFileName(UserVo userVo) throws Exception {
		// users_info 테이블의 resume_file_name 필드 업데이트
		return userDAO.updateResumeFileName(userVo);
	}

	/**
	 * 지원현황 목록을 조회한다.
	 *
	 * @param applicationHistoryVo 지원현황 조회 조건
	 * @return 지원현황 목록
	 * @throws Exception
	 */
	public List<ApplicationHistoryVo> selectApplicationHistoryList(ApplicationHistoryVo applicationHistoryVo)
			throws Exception {
		return userDAO.selectApplicationHistoryList(applicationHistoryVo);
	}

	/**
	 * 지원현황 통계를 조회한다. (상태별 GROUP BY)
	 *
	 * @param applicationHistoryVo 지원현황 조회 조건
	 * @return 지원현황 통계 목록
	 * @throws Exception
	 */
	public List<ApplicationHistoryVo> selectApplicationHistoryStats(ApplicationHistoryVo applicationHistoryVo)
			throws Exception {
		return userDAO.selectApplicationHistoryStats(applicationHistoryVo);
	}

	/**
	 * 지원현황 총 개수를 조회한다.
	 *
	 * @param applicationHistoryVo 지원현황 조회 조건
	 * @return 지원현황 총 개수
	 * @throws Exception
	 */
	public long selectApplicationHistoryCount(ApplicationHistoryVo applicationHistoryVo) throws Exception {
		return userDAO.selectApplicationHistoryCount(applicationHistoryVo);
	}

	/**
	 * 지원현황 상세정보를 조회한다.
	 *
	 * @param applicationHistoryVo 지원현황 조회 조건
	 * @return 지원현황 상세정보
	 * @throws Exception
	 */
	public ApplicationHistoryVo selectApplicationHistoryDetail(ApplicationHistoryVo applicationHistoryVo)
			throws Exception {
		return userDAO.selectApplicationHistoryDetail(applicationHistoryVo);
	}

	/**
	 * 지원현황을 등록한다.
	 *
	 * @param applicationHistoryVo 지원현황 정보
	 * @return 등록 결과
	 * @throws Exception
	 */
	public int insertApplicationHistory(ApplicationHistoryVo applicationHistoryVo) throws Exception {
		return userDAO.insertApplicationHistory(applicationHistoryVo);
	}

	/**
	 * 지원현황을 수정한다.
	 *
	 * @param applicationHistoryVo 지원현황 정보
	 * @return 수정 결과
	 * @throws Exception
	 */
	public int updateApplicationHistory(ApplicationHistoryVo applicationHistoryVo) throws Exception {
		return userDAO.updateApplicationHistory(applicationHistoryVo);
	}

	/**
	 * 지원현황을 삭제한다.
	 *
	 * @param applicationHistoryVo 지원현황 정보
	 * @return 삭제 결과
	 * @throws Exception
	 */
	public int deleteApplicationHistory(ApplicationHistoryVo applicationHistoryVo) throws Exception {
		return userDAO.deleteApplicationHistory(applicationHistoryVo);
	}
}
