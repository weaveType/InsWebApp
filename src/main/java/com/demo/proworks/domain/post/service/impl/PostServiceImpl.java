package com.demo.proworks.domain.post.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.domain.post.service.PostService;
import com.demo.proworks.domain.post.vo.JobApplicationVo;
import com.demo.proworks.domain.post.vo.PostMatchVo;
import com.demo.proworks.domain.post.vo.PostVo;
import com.demo.proworks.domain.post.vo.ScoutUserVo;
import com.demo.proworks.domain.post.vo.TechStackVo;
import com.demo.proworks.domain.post.vo.MainPostingListVo;
import com.demo.proworks.domain.post.vo.MainPostingVo;
import com.demo.proworks.domain.post.vo.SendEmailVo;
import com.demo.proworks.domain.post.dao.PostDAO;
import com.demo.proworks.common.service.EmailService;

/**
 * @subject : 공고정보 관련 처리를 담당하는 ServiceImpl
 * @description : 공고정보 관련 처리를 담당하는 ServiceImpl
 * @author : Inswave
 * @since : 2025/07/03
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/03 Inswave 최초 생성
 * 
 */
@Service("postServiceImpl")
public class PostServiceImpl implements PostService {

	@Resource(name = "postDAO")
	private PostDAO postDAO;

	@Resource(name = "messageSource")
	private MessageSource messageSource;

	@Resource(name = "emailService")
	private EmailService emailService;

	/**
	 * 공고정보 목록을 조회합니다.
	 *
	 * @process 1. 공고정보 목록을 조회한다. 2. 결과 List<PostVo>을(를) 리턴한다.
	 * 
	 * @param postVo 공고정보 PostVo
	 * @return 공고정보 목록 List<PostVo>
	 * @throws Exception
	 */
	public List<PostVo> selectListPost(PostVo postVo) throws Exception {
		List<PostVo> list = postDAO.selectListPost(postVo);
		return list;
	}

	/**
	 * 조회한 공고정보 전체 카운트
	 *
	 * @process 1. 공고정보 조회하여 전체 카운트를 리턴한다.
	 * 
	 * @param postVo 공고정보 PostVo
	 * @return 공고정보 목록 전체 카운트
	 * @throws Exception
	 */
	public long selectListCountPost(PostVo postVo) throws Exception {
		return postDAO.selectListCountPost(postVo);
	}

	/**
	 * 공고정보를 상세 조회한다.
	 *
	 * @process 1. 공고정보를 상세 조회한다. 2. 결과 PostVo을(를) 리턴한다.
	 * 
	 * @param postVo 공고정보 PostVo
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	public PostVo selectPost(PostVo postVo) throws Exception {
		PostVo resultVO = postDAO.selectPost(postVo);

		return resultVO;
	}

	/**
	 * 공고정보를 등록 처리 한다.
	 *
	 * @process 1. 공고정보를 등록 처리 한다.
	 * 
	 * @param postVo 공고정보 PostVo
	 * @return 번호
	 * @throws Exception
	 */
	public int insertPost(PostVo postVo) throws Exception {
		return postDAO.insertPost(postVo);
	}

	/**
	 * 공고정보를 갱신 처리 한다.
	 *
	 * @process 1. 공고정보를 갱신 처리 한다.
	 * 
	 * @param postVo 공고정보 PostVo
	 * @return 번호
	 * @throws Exception
	 */
	public int updatePost(PostVo postVo) throws Exception {
		return postDAO.updatePost(postVo);
	}

	/**
	 * 공고정보를 삭제 처리 한다.
	 *
	 * @process 1. 공고정보를 삭제 처리 한다.
	 * 
	 * @param postVo 공고정보 PostVo
	 * @return 번호
	 * @throws Exception
	 */
	public int deletePost(PostVo postVo) throws Exception {
		return postDAO.deletePost(postVo);
	}

	/**
	 * 기술스택 목록을 조회한다.
	 *
	 * @process 1. 기술스택 목록을 조회한다.
	 * 
	 * @return 기술스택 목록 List<TechStackVo>
	 * @throws Exception
	 */
	public List<TechStackVo> selectListTechStack() throws Exception {
		return postDAO.selectListTechStack();
	}

	/**
	 * 공고 등록 시 선택된 기술스택들을 company_tech_stack_relation 테이블에 저장한다.
	 *
	 * @process 1. 기존 관계 데이터 삭제 (수정 시를 위해) 2. 선택된 기술스택들을 순회하며 관계 데이터 저장
	 * 
	 * @param postVo       공고정보 PostVo (jobPostingId 포함)
	 * @param techStackIds 선택된 기술스택 ID 목록
	 * @throws Exception
	 */
	public void saveTechStackRelations(PostVo postVo, List<String> techStackIds) throws Exception {
		String jobPostingId = postVo.getJobPostingId();

		System.out.println("=== 🔥 강화된 기술스택 관계 저장 서비스 시작 ===");
		System.out.println("Job Posting ID: '" + jobPostingId + "'");
		System.out.println("기술스택 ID 목록: " + techStackIds);
		System.out.println("입력 검증:");
		System.out.println("  - postVo null 여부: " + (postVo == null));
		System.out.println("  - jobPostingId null 여부: " + (jobPostingId == null));
		System.out.println("  - jobPostingId 길이: " + (jobPostingId != null ? jobPostingId.length() : 0));
		System.out.println("  - techStackIds null 여부: " + (techStackIds == null));
		System.out.println("  - techStackIds 크기: " + (techStackIds != null ? techStackIds.size() : 0));

		// 🔥 강화된 입력 검증
		if (jobPostingId == null || jobPostingId.trim().isEmpty()) {
			String errorMsg = "공고 ID가 없습니다. jobPostingId: '" + jobPostingId + "'";
			System.err.println("❌❌❌ " + errorMsg);
			throw new Exception(errorMsg);
		}

		if (techStackIds == null || techStackIds.isEmpty()) {
			String errorMsg = "저장할 기술스택이 없습니다. techStackIds: " + techStackIds;
			System.err.println("❌❌❌ " + errorMsg);
			throw new Exception(errorMsg);
		}

		// 🔥 DB 연결 상태 확인
		System.out.println("=== DB 연결 및 DAO 상태 확인 ===");
		if (postDAO == null) {
			System.err.println("❌❌❌ postDAO가 null입니다!");
			throw new Exception("DAO 객체가 초기화되지 않았습니다.");
		}
		System.out.println("✅ postDAO 정상 확인");

		// 기존 관계 데이터 삭제 (수정 시를 위해)
		System.out.println("=== 🗑️ 기존 관계 데이터 삭제 시도 ===");
		try {
			int deletedCount = postDAO.deleteCompanyTechStackRelationByJobId(jobPostingId);
			System.out.println("✅ 삭제된 기존 관계 데이터: " + deletedCount + "개");
		} catch (Exception e) {
			System.err.println("⚠️ 기존 관계 데이터 삭제 중 오류 (계속 진행): " + e.getMessage());
			e.printStackTrace();
		}

		// 🔥 각 기술스택 ID 상세 검증 및 저장
		System.out.println("=== 💾 개별 기술스택 관계 저장 시작 ===");
		int successCount = 0;
		int failureCount = 0;
		List<String> failedTechStacks = new ArrayList<>();

		for (int i = 0; i < techStackIds.size(); i++) {
			String techStackId = techStackIds.get(i);

			System.out.println("--- 기술스택 " + (i + 1) + "/" + techStackIds.size() + " 처리 시작 ---");
			System.out.println("기술스택 ID: '" + techStackId + "'");

			// 개별 ID 검증
			if (techStackId == null || techStackId.trim().isEmpty()) {
				failureCount++;
				failedTechStacks.add("null_or_empty_" + i);
				System.err.println("❌ 잘못된 기술스택 ID (null 또는 빈 문자열): 인덱스 " + i);
				continue;
			}

			try {
				// 파라미터 맵 생성
				Map<String, Object> params = new HashMap<>();
				params.put("jobPostingId", jobPostingId);
				params.put("techStackId", techStackId);

				System.out.println("DB 삽입 파라미터:");
				System.out.println("  - jobPostingId: '" + params.get("jobPostingId") + "'");
				System.out.println("  - techStackId: '" + params.get("techStackId") + "'");

				// 🔥 실제 DB 삽입 실행
				System.out.println("DAO.insertCompanyTechStackRelation 호출...");
				int insertResult = postDAO.insertCompanyTechStackRelation(params);

				System.out.println("삽입 결과: " + insertResult);

				if (insertResult > 0) {
					successCount++;
					System.out.println("✅ 관계 데이터 저장 성공: Job ID=" + jobPostingId + ", Tech Stack ID=" + techStackId);
				} else {
					failureCount++;
					failedTechStacks.add(techStackId);
					System.err.println("❌ 관계 데이터 저장 실패 (결과: " + insertResult + "): " + techStackId);
				}

			} catch (Exception e) {
				failureCount++;
				failedTechStacks.add(techStackId);
				System.err.println("❌ 관계 데이터 저장 중 예외 발생: " + techStackId);
				System.err.println("예외 클래스: " + e.getClass().getSimpleName());
				System.err.println("예외 메시지: " + e.getMessage());
				e.printStackTrace();
			}

			System.out.println("--- 기술스택 " + (i + 1) + " 처리 완료 ---");
		}

		// 🔥 최종 결과 분석
		System.out.println("=== 📊 최종 처리 결과 ===");
		System.out.println("총 처리 대상: " + techStackIds.size() + "개");
		System.out.println("성공: " + successCount + "개");
		System.out.println("실패: " + failureCount + "개");
		System.out.println("성공률: " + (techStackIds.size() > 0 ? (successCount * 100 / techStackIds.size()) : 0) + "%");

		if (!failedTechStacks.isEmpty()) {
			System.err.println("실패한 기술스택 ID들: " + failedTechStacks);
		}

		// 🔥 저장 후 즉시 검증
		System.out.println("=== 🔍 저장 후 검증 ===");
		try {
			List<TechStackVo> savedTechStacks = postDAO.selectTechStacksByPostId(jobPostingId);
			System.out.println("DB에서 실제 조회된 기술스택 개수: " + savedTechStacks.size());

			if (savedTechStacks.size() != successCount) {
				System.err.println("⚠️ 예상 저장 개수와 실제 조회 개수가 다릅니다!");
				System.err.println("예상: " + successCount + ", 실제: " + savedTechStacks.size());
			}

			for (TechStackVo savedTech : savedTechStacks) {
				System.out.println("  ✓ " + savedTech.getTechStackName() + " (ID: " + savedTech.getTechStackId() + ")");
			}
		} catch (Exception verifyError) {
			System.err.println("❌ 저장 후 검증 중 오류: " + verifyError.getMessage());
			verifyError.printStackTrace();
		}

		// 🔥 실패 시 예외 발생
		if (successCount == 0 && !techStackIds.isEmpty()) {
			String errorMsg = "모든 기술스택 관계 저장에 실패했습니다. 실패 개수: " + failureCount;
			System.err.println("❌❌❌ " + errorMsg);
			throw new Exception(errorMsg);
		}

		if (failureCount > 0) {
			String warningMsg = failureCount + "개의 기술스택 관계 저장에 실패했습니다.";
			System.err.println("⚠️ " + warningMsg);
			// 일부 실패는 경고로만 처리 (전체가 실패한 게 아니므로)
		}

		System.out.println("=== ✅ 기술스택 관계 저장 서비스 완료 ===");
	}

	/**
	 * 특정 공고의 기술스택 관계를 모두 삭제한다.
	 *
	 * @process 1. 해당 공고의 기술스택 관계를 모두 삭제한다.
	 * 
	 * @param jobPostingId 공고 ID
	 * @throws Exception
	 */
	public void deleteTechStackRelationsByPostId(String jobPostingId) throws Exception {
		if (jobPostingId != null && !jobPostingId.trim().isEmpty()) {
			postDAO.deleteCompanyTechStackRelationByJobId(jobPostingId);
		}
	}

	/**
	 * 특정 공고의 기술스택 목록을 조회한다.
	 *
	 * @process 1. 특정 공고의 기술스택 목록을 조회한다.
	 * 
	 * @param jobPostingId 공고 ID
	 * @return 기술스택 목록 List<TechStackVo>
	 * @throws Exception
	 */
	public List<TechStackVo> selectTechStacksByPostId(String jobPostingId) throws Exception {
		return postDAO.selectTechStacksByPostId(jobPostingId);
	}

	/**
	 * 사용자 ID로 회사 ID를 조회한다.
	 *
	 * @process 1. 사용자 ID로 companys 테이블에서 회사 ID를 조회한다.
	 * 
	 * @param userId 사용자 ID
	 * @return 회사 ID String
	 * @throws Exception
	 */
	public String selectCompanyIdByUserId(String userId) throws Exception {
		System.out.println("=== 사용자 ID로 회사 ID 조회 서비스 시작 ===");
		System.out.println("입력 사용자 ID: '" + userId + "'");

		if (userId == null || userId.trim().isEmpty()) {
			System.err.println("❌ 사용자 ID가 null 또는 빈 문자열입니다.");
			throw new Exception("사용자 ID가 올바르지 않습니다.");
		}

		try {
			String companyId = postDAO.selectCompanyIdByUserId(userId);
			System.out.println("조회된 회사 ID: '" + companyId + "'");

			if (companyId == null || companyId.trim().isEmpty()) {
				System.err.println("❌ 해당 사용자와 연결된 회사가 없습니다. 사용자 ID: " + userId);
				throw new Exception("해당 사용자와 연결된 회사 정보를 찾을 수 없습니다.");
			}

			System.out.println("✅ 회사 ID 조회 성공: " + companyId);
			return companyId;

		} catch (Exception e) {
			System.err.println("❌ 회사 ID 조회 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
			throw new Exception("회사 정보 조회 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

	/**
	 * 사용자 mbti, mbti 별 갯수 필터를 통해 회사 list를 정렬한다
	 *
	 * @param PostMatchVo = PostVo + 사용자 mbti, mbti 갯수
	 * @return 회사 ID String
	 * @throws Exception
	 */
	public List<PostVo> findPostsByMbti(PostMatchVo postMatchVo) throws Exception {
		PostMatchVo vo = new PostMatchVo();
		vo.setUserMbti(postMatchVo.getUserMbti()); // 로그인 사용자 MBTI
		vo.setMbtiMatchFilter(postMatchVo.getMbtiMatchFilter()); // 몇개의 mbti가 일치하여야 하는지..
		vo.setLimit(20); // 20건만
		return postDAO.findPostsByMbti(vo);
	};

	/**
	 * 사용자 mbti, mbti 별 갯수 필터를 통해 회사 list의 갯수를 가져온다
	 *
	 * @param PostMatchVo = PostVo + 사용자 mbti, mbti 갯수
	 * @return 회사 ID String
	 * @throws Exception
	 */
	public long findPostsByMbtiCount(PostMatchVo postMatchVo) throws Exception {
		return postDAO.findPostsByMbtiCount(postMatchVo);
	};

	/**
	 * 이메일을 일괄전송 처리한다
	 *
	 * @process 1. Controller에서 전달받은 이메일 정보를 확인한다. 2. EmailService를 통해 비동기로 이메일을 일괄
	 *          전송한다. 3. 전송 결과를 로깅한다.
	 * 
	 * @throws Exception
	 */
	@Override
	public void sendToEmails(SendEmailVo sendEmailVo) throws Exception {
		if (sendEmailVo == null) {
			throw new Exception("이메일 전송 정보가 없습니다.");
		}

		try {
			// 비동기로 이메일 일괄 전송
			CompletableFuture<Integer> future = emailService.sendToEmails(sendEmailVo);

			// 전송 결과 확인 (선택사항 - 비동기이므로 대기하지 않아도 됨)
			Integer successCount = future.get();
			System.out.println("✅ 이메일 전송 완료: " + successCount + "건 성공");

		} catch (Exception e) {
			System.err.println("❌ 이메일 전송 중 오류: " + e.getMessage());
			throw new Exception("이메일 전송에 실패했습니다: " + e.getMessage());
		}

		System.out.println("=== 이메일 일괄전송 서비스 완료 ===");
	}

	/**
	 * 공고에 이력서 지원처리를 한다.
	 *
	 * @param sendEmailVo 합불여부, 메일 전송할 email, 메일 내용
	 * @return 등록된 행의 수
	 * @throws Exception
	 */
	public int insertJobApplication(JobApplicationVo jobApplicationVo) throws Exception {
		return postDAO.insertJobApplication(jobApplicationVo);
	}

	/**
	 * 메인 페이지에서 출력할 공고목록을 조회한다.
	 *
	 * @return 기술스택 목록 조회 결과
	 * @throws Exception
	 */
	public MainPostingListVo selectPostingList() throws Exception {
		List<MainPostingVo> resultList = postDAO.selectPostingList();
		MainPostingListVo resultVo = new MainPostingListVo();
		resultVo.setMainPostingVo(resultList);
		return resultVo;
	}

	/**
	 * 기업이 스카웃한 유저를 저장한다.
	 * 
	 * @param jobPostingId 공고 ID, accountIds 스카웃 된 User ID List
	 * @throws Exception
	 */
	public void insertScoutRequest(ScoutUserVo scoutUserVo) throws Exception {
		postDAO.insertScoutRequest(scoutUserVo);
	}

	/**
	 * 사용자의 특정 공고 지원 상태를 확인한다.
	 *
	 * @process 1. JobApplicationVo를 생성하여 파라미터 설정
	 *          2. DAO를 통해 지원 여부 확인
	 *          3. 결과 반환 (0: 지원하지 않음, 1 이상: 지원함)
	 * 
	 * @param jobPostingId 공고 ID
	 * @param accountId 사용자 계정 ID  
	 * @return 지원 여부 (true: 지원함, false: 지원하지 않음)
	 * @throws Exception
	 */
	@Override
	public boolean checkApplicationStatus(int jobPostingId, int accountId) throws Exception {
		System.out.println("=== 지원 상태 확인 서비스 시작 ===");
		System.out.println("공고 ID: " + jobPostingId);
		System.out.println("사용자 ID: " + accountId);
		
		try {
			// JobApplicationVo 생성하여 파라미터 설정
			JobApplicationVo jobApplicationVo = new JobApplicationVo();
			jobApplicationVo.setJobPostingId(jobPostingId);
			jobApplicationVo.setAccountId(accountId);
			
			// DAO를 통해 지원 여부 확인
			int applicationCount = postDAO.selectApplicationCount(jobApplicationVo);
			
			boolean isApplied = applicationCount > 0;
			
			System.out.println("지원 건수: " + applicationCount);
			System.out.println("지원 여부: " + isApplied);
			System.out.println("✅ 지원 상태 확인 완료");
			
			return isApplied;
			
		} catch (Exception e) {
			System.err.println("❌ 지원 상태 확인 중 오류 - jobPostingId: " + jobPostingId + ", accountId: " + accountId);
			System.err.println("오류 내용: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

}
