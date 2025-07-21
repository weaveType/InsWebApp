package com.demo.proworks.domain.post.web;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.cmmn.ProworksUserHeader;
import com.demo.proworks.domain.post.service.PostService;
import com.demo.proworks.domain.post.vo.PostVo;
import com.demo.proworks.domain.post.vo.ScoutUserVo;
import com.demo.proworks.domain.post.vo.SendEmailInfoListVo;
import com.demo.proworks.domain.post.vo.SendEmailVo;
import com.demo.proworks.domain.post.vo.JobApplicationVo;
import com.demo.proworks.domain.post.vo.PostListVo;
import com.demo.proworks.domain.post.vo.PostMatchVo;
import com.demo.proworks.domain.post.vo.TechStackVo;
import com.demo.proworks.domain.post.vo.TechStackListVo;
import com.demo.proworks.domain.post.vo.MainPostingListVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;
import com.inswave.elfw.core.UserHeader;
import com.inswave.elfw.util.ControllerContextUtil;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @subject : 공고정보 관련 처리를 담당하는 컨트롤러
 * @description : 공고정보 관련 처리를 담당하는 컨트롤러
 * @author : Inswave
 * @since : 2025/07/03
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/03 Inswave 최초 생성
 * 
 */
@Controller
public class PostController {

	/** PostService */
	@Resource(name = "postServiceImpl")
	private PostService postService;

	/**
	 * 현재 사용자의 Company ID 권한을 검증합니다.
	 *
	 * @param companyId 검증할 Company ID
	 * @return 권한 있으면 true
	 */
	private boolean hasCompanyPermission(String companyId) {
//        String currentCompanyId = getCurrentUserCompanyId();
//        return currentCompanyId != null && currentCompanyId.equals(companyId);
		return true;
	}

	/**
	 * 공고정보 목록을 조회합니다. (일반 사용자용 - 모든 공고 조회)
	 *
	 * @param postVo 공고정보
	 * @return 목록조회 결과
	 * @throws Exception
	 */
	@ElService(key = "POS0001List")
	@RequestMapping(value = "POS0001List")
	@ElDescription(sub = "공고정보 목록조회", desc = "모든 공고정보 목록 조회를 한다.")
	public Map<String, Object> selectListPost(PostVo postVo) throws Exception {

		System.out.println("=== 일반 사용자용 공고 목록 조회 ===");

		// ✅ 일반 사용자용: companyId 필터링 제거 (모든 공고 조회)
		postVo.setCompanyId(null);
		System.out.println("모든 공고 조회 (companyId 필터 제거)");

		List<PostVo> postList = postService.selectListPost(postVo);
		long totCnt = postService.selectListCountPost(postVo);

		System.out.println("조회된 공고 수: " + postList.size());
		System.out.println("전체 공고 수: " + totCnt);

		// 회사명 정보 로깅
		for (int i = 0; i < Math.min(postList.size(), 3); i++) {
			PostVo post = postList.get(i);
			System.out.println("공고 " + (i + 1) + ": " + post.getTitle() + " - 회사: " + post.getCompanyName());
		}

		PostListVo retPostList = new PostListVo();
		retPostList.setPostVoList(postList);
		retPostList.setTotalCount(totCnt);
		Map<String, Object> response = new HashMap<>();
		response.put("elData", retPostList);
		return response;
	}

	/**
	 * 기업 회원용 공고정보 목록을 조회합니다. (자신의 공고만)
	 *
	 * @param postVo 공고정보
	 * @return 목록조회 결과
	 * @throws Exception
	 */
	@ElService(key = "POS0005List")
	@RequestMapping(value = "POS0005List")
	@ElDescription(sub = "기업용 공고정보 목록조회", desc = "현재 기업의 공고정보 목록만 조회한다.")
	public Map<String, Object> selectListPostForCompany(PostVo postVo) throws Exception {

		System.out.println("=== 기업 회원용 공고 목록 조회 ===");

		// 현재 사용자의 Company ID로 필터링
		String currentCompanyId = postVo.getCompanyId();

		// 보안상 중요: 현재 로그인된 사용자의 회사 공고만 조회
		postVo.setCompanyId(currentCompanyId);
		System.out.println("설정된 Company ID 필터: " + postVo.getCompanyId());

		List<PostVo> postList = postService.selectListPost(postVo);
		long totCnt = postService.selectListCountPost(postVo);

		System.out.println("조회된 공고 수: " + postList.size());
		System.out.println("전체 공고 수: " + totCnt);

		PostListVo retPostList = new PostListVo();
		retPostList.setPostVoList(postList);
		retPostList.setTotalCount(totCnt);
		Map<String, Object> response = new HashMap<>();
		response.put("elData", retPostList);
		return response;
	}

	/**
	 * 공고정보 목록을 조회합니다.
	 *
	 * @param postMatchVo PostVo + 유저의 mbti, mbti 필터 갯수 추가
	 * @return 목록조회 결과
	 * @throws Exception
	 */
	@ElService(key = "POS0004List")
	@RequestMapping(value = "POS0004List")
	@ElDescription(sub = "공고정보 목록조회", desc = "유저의 기준에서 공고정보 목록 조회를 한다.")
	public Map<String, Object> findPostsByMbti(PostMatchVo postMatchVo) throws Exception {
		List<PostVo> postList = postService.findPostsByMbti(postMatchVo);
		long totCnt = postService.findPostsByMbtiCount(postMatchVo);

		PostListVo retPostList = new PostListVo();
		retPostList.setPostVoList(postList);
		retPostList.setTotalCount(totCnt);
		Map<String, Object> response = new HashMap<>();
		response.put("elData", retPostList);
		return response;
	}

	/**
	 * 공고정보을 단건 조회 처리 한다.
	 *
	 * @param postVo 공고정보
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	@ElService(key = "POS0001UpdView")
	@RequestMapping(value = "POS0001UpdView")
	@ElDescription(sub = "공고정보 갱신 폼을 위한 조회", desc = "공고정보 갱신 폼을 위한 조회를 한다.")
	public PostVo selectPost(PostVo postVo) throws Exception {

		// 권한 검증: 해당 공고가 현재 사용자의 회사 것인지 확인
		PostVo selectPostVo = postService.selectPost(postVo);

		if (selectPostVo != null) {
			if (!hasCompanyPermission(selectPostVo.getCompanyId())) {
				throw new RuntimeException("해당 공고에 대한 권한이 없습니다.");
			}

			// 해당 공고의 기술스택 목록도 함께 조회하여 JSON으로 설정
			try {
				List<TechStackVo> techStacks = postService.selectTechStacksByPostId(selectPostVo.getJobPostingId());
				if (techStacks != null && !techStacks.isEmpty()) {
					// 기술스택 이름들을 배열로 변환
					String[] techStackNames = new String[techStacks.size()];
					for (int i = 0; i < techStacks.size(); i++) {
						techStackNames[i] = techStacks.get(i).getTechStackName();
					}

					// JSON 문자열로 변환하여 설정
					ObjectMapper objectMapper = new ObjectMapper();
					selectPostVo.setSelectedTechStackNames(objectMapper.writeValueAsString(techStackNames));
				}
			} catch (Exception e) {
				System.err.println("기술스택 조회 중 오류: " + e.getMessage());
				// 기술스택 조회 실패 시에도 공고 정보는 반환
			}
		}

		return selectPostVo;
	}

	/**
	 * 공고정보를 등록 처리 한다.
	 *
	 * @param postVo 공고정보
	 * @throws Exception
	 */
	@ElService(key = "POS0001Ins")
	@RequestMapping(value = "POS0001Ins")
	@ElDescription(sub = "공고정보 등록처리", desc = "공고정보를 등록 처리 한다.")
	public void insertPost(PostVo postVo) throws Exception {

		System.out.println("=== 🔥 강화된 공고 등록 처리 시작 ===");
		System.out.println("입력받은 PostVo: " + postVo.toString());

		// 🔥 중요한 필드들 개별 검증
		System.out.println("=== 🔍 프론트엔드에서 받은 데이터 상세 검증 ===");
		System.out.println("제목: '" + postVo.getTitle() + "'");
		System.out.println("지역: '" + postVo.getLocation() + "'");
		System.out.println("근무형태: '" + postVo.getWorkType() + "'");
		System.out.println("기술스택 JSON: '" + postVo.getSelectedTechStackNames() + "'");
		System.out.println("성향 JSON: '" + postVo.getPreferredDeveloperTypes() + "'");
		System.out.println("상태: '" + postVo.getStatus() + "'");

		// 🔥 기술스택 JSON 즉시 검증
		String selectedTechStackNames = postVo.getSelectedTechStackNames();
		if (selectedTechStackNames == null || selectedTechStackNames.trim().isEmpty()) {
			System.err.println("❌❌❌ 중대한 문제: 기술스택 JSON이 비어있습니다!");
			System.err.println("받은 기술스택 데이터: " + selectedTechStackNames);
			throw new RuntimeException("기술스택이 선택되지 않았습니다. 최소 1개 이상 선택해야 합니다.");
		}

		// 🔥 JSON 파싱 미리 테스트
		try {
			ObjectMapper testMapper = new ObjectMapper();
			String[] testArray = testMapper.readValue(selectedTechStackNames, String[].class);
			if (testArray == null || testArray.length == 0) {
				throw new RuntimeException("기술스택 배열이 비어있습니다");
			}
			System.out.println("✅ 기술스택 JSON 사전 검증 통과: " + java.util.Arrays.toString(testArray));
		} catch (Exception e) {
			System.err.println("❌❌❌ 기술스택 JSON 파싱 사전 검증 실패!");
			System.err.println("오류: " + e.getMessage());
			System.err.println("JSON: " + selectedTechStackNames);
			throw new RuntimeException("기술스택 데이터 형식이 올바르지 않습니다: " + e.getMessage());
		}

		// 현재 사용자의 Company ID 설정 (세션에서 가져오기)
		String currentCompanyId = postVo.getCompanyId();
		if (currentCompanyId == null) {
			throw new RuntimeException("로그인 정보를 확인할 수 없습니다.");
		}

		System.out.println("=== 회사 ID 설정 ===");
		System.out.println("세션에서 조회한 현재 사용자 회사 ID: " + currentCompanyId);
		System.out.println("프론트에서 전달받은 회사 ID: " + postVo.getCompanyId());

		// 🔥 보안상 중요: 세션에서 조회한 회사 ID로 강제 설정 (프론트에서 전달받은 값 무시)
		postVo.setCompanyId(currentCompanyId);
		System.out.println("최종 설정된 회사 ID: " + postVo.getCompanyId());

		// 1. 공고 정보 등록 (job_posting_id가 자동 생성됨)
		System.out.println("=== 📝 공고 정보 등록 시작 ===");
		int result = postService.insertPost(postVo);
		System.out.println("공고 등록 결과: " + result);

		// ✅ 등록 후 생성된 jobPostingId 확인 (중요!)
		String generatedJobPostingId = postVo.getJobPostingId();
		System.out.println("생성된 Job Posting ID: " + generatedJobPostingId);

		if (result > 0 && generatedJobPostingId != null && !generatedJobPostingId.trim().isEmpty()) {
			// 2. 🔥 기술스택 관계 데이터 저장 (강화된 버전)
			System.out.println("=== 💻 기술스택 관계 저장 시작 ===");
			System.out.println("처리할 기술스택 JSON: '" + selectedTechStackNames + "'");

			// 🔥 기술스택이 확실히 있는지 재검증
			if (selectedTechStackNames != null && !selectedTechStackNames.trim().isEmpty()
					&& !selectedTechStackNames.equals("[]") && !selectedTechStackNames.equals("null")) {

				try {
					System.out.println("=== JSON 파싱 시작 ===");

					// JSON 문자열을 파싱하여 기술스택 이름들 추출
					ObjectMapper objectMapper = new ObjectMapper();
					String[] techStackNames = objectMapper.readValue(selectedTechStackNames, String[].class);
					System.out.println("✅ JSON 파싱 성공!");
					System.out.println("파싱된 기술스택 개수: " + techStackNames.length);
					System.out.println("파싱된 기술스택 이름들: " + java.util.Arrays.toString(techStackNames));

					if (techStackNames.length == 0) {
						throw new RuntimeException("파싱된 기술스택 배열이 비어있습니다");
					}

					// 기술스택 이름으로 ID 찾아서 관계 테이블에 저장
					List<TechStackVo> allTechStacks = postService.selectListTechStack();
					System.out.println("DB에서 조회한 전체 기술스택 개수: " + allTechStacks.size());

					List<String> techStackIds = new ArrayList<>();

					for (String techStackName : techStackNames) {
						System.out.println("🔍 기술스택 이름 매핑 시도: '" + techStackName + "'");
						boolean found = false;

						if (techStackName == null || techStackName.trim().isEmpty()) {
							System.err.println("⚠️ 빈 기술스택 이름 건너뜀");
							continue;
						}

						for (TechStackVo techStack : allTechStacks) {
							if (techStack.getTechStackName().trim().equals(techStackName.trim())) {
								techStackIds.add(techStack.getTechStackId());
								System.out.println(
										"✅ 매핑 성공: '" + techStackName + "' -> ID: " + techStack.getTechStackId());
								found = true;
								break;
							}
						}

						if (!found) {
							System.err.println("❌ 매핑 실패: '" + techStackName + "' (DB에 존재하지 않음)");
							// 유사한 이름들 찾아보기
							System.err.println("🔍 유사한 기술스택 찾기:");
							for (TechStackVo techStack : allTechStacks) {
								if (techStack.getTechStackName().toLowerCase().contains(techStackName.toLowerCase())
										|| techStackName.toLowerCase()
												.contains(techStack.getTechStackName().toLowerCase())) {
									System.err.println("  유사: '" + techStack.getTechStackName() + "' (ID: "
											+ techStack.getTechStackId() + ")");
								}
							}
						}
					}

					System.out.println("=== 매핑 결과 ===");
					System.out.println("매핑된 기술스택 ID들: " + techStackIds);
					System.out.println("매핑 성공 개수: " + techStackIds.size() + " / " + techStackNames.length);

					// 관계 데이터 저장
					if (!techStackIds.isEmpty()) {
						System.out.println("=== 💾 관계 테이블 저장 시작 ===");
						System.out.println("Job Posting ID: " + generatedJobPostingId);
						System.out.println("저장할 기술스택 ID들: " + techStackIds);

						postService.saveTechStackRelations(postVo, techStackIds);

						System.out.println("✅✅✅ 기술스택 관계 데이터 저장 완료!");

						// 🔥 저장 후 즉시 검증
						System.out.println("=== 저장 후 검증 ===");
						try {
							List<TechStackVo> savedTechStacks = postService
									.selectTechStacksByPostId(generatedJobPostingId);
							System.out.println("저장 후 조회된 기술스택 개수: " + savedTechStacks.size());
							for (TechStackVo savedTech : savedTechStacks) {
								System.out.println("  - " + savedTech.getTechStackName() + " (ID: "
										+ savedTech.getTechStackId() + ")");
							}
						} catch (Exception verifyError) {
							System.err.println("저장 후 검증 중 오류: " + verifyError.getMessage());
						}

					} else {
						System.err.println("❌❌❌ 치명적 오류: 저장할 기술스택 ID가 없습니다!");
						System.err.println("원인 분석:");
						System.err.println("  - 파싱된 기술스택 이름들: " + java.util.Arrays.toString(techStackNames));
						System.err.println("  - DB 기술스택 개수: " + allTechStacks.size());
						throw new RuntimeException("기술스택 매핑에 실패했습니다. 선택한 기술스택이 DB에 존재하지 않습니다.");
					}

				} catch (Exception e) {
					// JSON 파싱 실패 시 상세 로그 및 예외 처리
					System.err.println("❌❌❌ 기술스택 처리 중 치명적 오류 발생!");
					System.err.println("- 오류 클래스: " + e.getClass().getSimpleName());
					System.err.println("- 오류 메시지: " + e.getMessage());
					System.err.println("- 입력된 JSON: '" + selectedTechStackNames + "'");
					System.err.println("- JSON 길이: " + selectedTechStackNames.length());
					System.err.println("- JSON 첫 문자: "
							+ (selectedTechStackNames.length() > 0 ? selectedTechStackNames.charAt(0) : "없음"));
					System.err.println("- JSON 마지막 문자: " + (selectedTechStackNames.length() > 0
							? selectedTechStackNames.charAt(selectedTechStackNames.length() - 1)
							: "없음"));
					e.printStackTrace();

					// 🔥 기술스택 저장 실패는 치명적 오류로 처리
					throw new RuntimeException("기술스택 저장 중 오류가 발생했습니다: " + e.getMessage());
				}
			} else {
				System.err.println("❌❌❌ 치명적 오류: 기술스택 JSON이 비어있거나 null입니다!");
				System.err.println("받은 데이터: '" + selectedTechStackNames + "'");
				throw new RuntimeException("기술스택이 선택되지 않았습니다. 최소 1개 이상 선택해야 합니다.");
			}
		} else {
			System.err.println("❌❌❌ 공고 등록 실패 또는 Job Posting ID 생성 실패");
			System.err.println("- 등록 결과: " + result);
			System.err.println("- 생성된 ID: " + generatedJobPostingId);
			throw new RuntimeException("공고 등록에 실패했습니다.");
		}

		System.out.println("=== ✅ 공고 등록 처리 완료 ===");
	}

	/**
	 * 공고정보를 갱신 처리 한다.
	 *
	 * @param postVo 공고정보
	 * @throws Exception
	 */
	@ElService(key = "POS0001Upd")
	@RequestMapping(value = "POS0001Upd")
	@ElValidator(errUrl = "/post/postRegister", errContinue = true)
	@ElDescription(sub = "공고정보 갱신처리", desc = "공고정보를 갱신 처리 한다.")
	public PostVo updatePost(PostVo postVo) throws Exception {

		System.out.println("=== 공고 수정 처리 시작 ===");
		System.out.println("수정할 공고 ID: " + postVo.getJobPostingId());

		// 권한 검증: 기존 공고 조회해서 현재 사용자 회사 것인지 확인
		PostVo existingPost = postService.selectPost(postVo);
		if (existingPost == null) {
			throw new RuntimeException("존재하지 않는 공고입니다.");
		}

		if (!hasCompanyPermission(existingPost.getCompanyId())) {
			throw new RuntimeException("해당 공고를 수정할 권한이 없습니다.");
		}

		// Company ID는 변경되지 않도록 기존 값 유지
		postVo.setCompanyId(existingPost.getCompanyId());

		// 기존 기술스택 관계 삭제
		postService.deleteTechStackRelationsByPostId(postVo.getJobPostingId());

		// 공고 정보 수정
		postService.updatePost(postVo);

		// 새로운 기술스택 관계 추가
		String selectedTechStackNames = postVo.getSelectedTechStackNames();
		if (selectedTechStackNames != null && !selectedTechStackNames.trim().isEmpty()) {
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				String[] techStackNames = objectMapper.readValue(selectedTechStackNames, String[].class);

				List<TechStackVo> allTechStacks = postService.selectListTechStack();
				List<String> techStackIds = new ArrayList<>();

				for (String techStackName : techStackNames) {
					for (TechStackVo techStack : allTechStacks) {
						if (techStack.getTechStackName().equals(techStackName)) {
							techStackIds.add(techStack.getTechStackId());
							break;
						}
					}
				}

				if (!techStackIds.isEmpty()) {
					postService.saveTechStackRelations(postVo, techStackIds);
				}

			} catch (Exception e) {
				System.err.println("기술스택 수정 중 오류: " + e.getMessage());
			}
		}

		// 수정된 공고 정보 다시 조회해서 반환
		PostVo updatedPost = postService.selectPost(postVo);
		
		// 기술스택 정보도 함께 설정
		try {
			List<TechStackVo> techStacks = postService.selectTechStacksByPostId(updatedPost.getJobPostingId());
			if (techStacks != null && !techStacks.isEmpty()) {
				String[] techStackNames = new String[techStacks.size()];
				for (int i = 0; i < techStacks.size(); i++) {
					techStackNames[i] = techStacks.get(i).getTechStackName();
				}
				
				ObjectMapper objectMapper = new ObjectMapper();
				updatedPost.setSelectedTechStackNames(objectMapper.writeValueAsString(techStackNames));
			}
		} catch (Exception e) {
			System.err.println("수정 후 기술스택 조회 중 오류: " + e.getMessage());
		}

		System.out.println("=== 공고 수정 처리 완료 ===");
		return updatedPost;
	}

	/**
	 * 공고정보를 삭제 처리한다.
	 *
	 * @param postVo 공고정보
	 * @throws Exception
	 */
	@ElService(key = "POS0001Del")
	@RequestMapping(value = "POS0001Del")
	@ElDescription(sub = "공고정보 삭제처리", desc = "공고정보를 삭제 처리한다.")
	public void deletePost(PostVo postVo) throws Exception {

		System.out.println("=== 공고 삭제 처리 시작 ===");
		System.out.println("삭제할 공고 ID: " + postVo.getJobPostingId());

		// 권한 검증: 기존 공고 조회해서 현재 사용자 회사 것인지 확인
		PostVo existingPost = postService.selectPost(postVo);
		if (existingPost == null) {
			throw new RuntimeException("존재하지 않는 공고입니다.");
		}

		System.out
				.println("기존 공고 확인 - 제목: " + existingPost.getTitle() + ", Company ID: " + existingPost.getCompanyId());

		if (!hasCompanyPermission(existingPost.getCompanyId())) {
			throw new RuntimeException("해당 공고를 삭제할 권한이 없습니다.");
		}

		// ✅ 소프트 삭제 방식: 기술스택 관계는 그대로 두고 공고만 is_deleted = 1로 설정
		// 공고가 is_deleted = 1이 되면 목록 조회 시 제외되므로 기술스택 관계도 자연스럽게 보이지 않음
		// 나중에 공고를 복구할 때 기술스택 관계도 함께 복구됨

		System.out.println("공고 소프트 삭제 실행...");
		postService.deletePost(postVo);
		System.out.println("=== 공고 삭제 처리 완료 ===");
	}

	/**
	 * 공고정보 상세 조회 (유저용 - 권한 검증 없음)
	 *
	 * @param postVo 공고정보
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	@ElService(key = "POS0001Detail")
	@RequestMapping(value = "POS0001Detail")
	@ElDescription(sub = "공고정보 상세 조회 (유저용)", desc = "유저가 공고 상세정보를 조회한다.")
	public PostVo selectPostDetail(PostVo postVo) throws Exception {

		System.out.println("=== 공고 상세 조회 (유저용) ===");
		System.out.println("조회할 공고 ID: " + postVo.getJobPostingId());

		// 공고 기본 정보 조회
		PostVo selectPostVo = postService.selectPost(postVo);

		if (selectPostVo != null) {
			System.out.println("조회된 공고 제목: " + selectPostVo.getTitle());
			System.out.println("조회된 공고 회사: " + selectPostVo.getCompanyId());

			// 해당 공고의 기술스택 목록도 함께 조회하여 JSON으로 설정
			try {
				List<TechStackVo> techStacks = postService.selectTechStacksByPostId(selectPostVo.getJobPostingId());
				if (techStacks != null && !techStacks.isEmpty()) {
					// 기술스택 이름들을 배열로 변환
					String[] techStackNames = new String[techStacks.size()];
					for (int i = 0; i < techStacks.size(); i++) {
						techStackNames[i] = techStacks.get(i).getTechStackName();
					}

					// JSON 문자열로 변환하여 설정
					ObjectMapper objectMapper = new ObjectMapper();
					selectPostVo.setSelectedTechStackNames(objectMapper.writeValueAsString(techStackNames));

					System.out.println("기술스택 정보 설정 완료: " + selectPostVo.getSelectedTechStackNames());
				}
			} catch (Exception e) {
				System.err.println("기술스택 조회 중 오류: " + e.getMessage());
				// 기술스택 조회 실패 시에도 공고 정보는 반환
			}
		} else {
			System.out.println("공고를 찾을 수 없습니다. ID: " + postVo.getJobPostingId());
		}

		System.out.println("=== 공고 상세 조회 완료 ===");
		return selectPostVo;
	}

	/**
	 * 기술스택 목록을 조회한다.
	 *
	 * @return 기술스택 목록 조회 결과
	 * @throws Exception
	 */
	@ElService(key = "POS0002List")
	@RequestMapping(value = "POS0002List")
	@ElDescription(sub = "기술스택 목록조회", desc = "기술스택 목록을 조회한다.")
	public List<TechStackVo> selectListTechStack() throws Exception {

		List<TechStackVo> techStackList = postService.selectListTechStack();

		return techStackList;
	}

	/**
	 * 공고에 이력서 지원처리를 한다.
	 *
	 * @param sendEmailVo 합불여부, 메일 전송할 email, 메일 내용
	 * @return 등록된 행의 수
	 * @throws Exception
	 */
	@ElService(key = "POS0001JAPL")
	@RequestMapping(value = "POS0001JAPL")
	@ElDescription(sub = "이력서 지원처리", desc = "공고에 이력서 지원 처리를 한다")
	public void insertJobApplication(JobApplicationVo jobApplicationVo) throws Exception {
		ProworksUserHeader userHeader = (ProworksUserHeader) ControllerContextUtil.getUserHeader();
		int currentId = userHeader.getAccountId();
		int userId = jobApplicationVo.getAccountId();
		if (currentId == userId) {
			postService.insertJobApplication(jobApplicationVo);
		}
	}

	/**
	 * 지원자에게 이메일을 일괄전송 처리한다
	 *
	 * @param sendEmailVo 합불여부, 메일 전송할 email, 메일 내용
	 * @return 이메일 전송 실패 ID
	 * @throws Exception
	 */
	@ElService(key = "POS0001Send")
	@RequestMapping(value = "POS0001Send")
	@ElDescription(sub = "이메일 일괄전송", desc = "이메일을 일괄전송 처리한다")
	public void sendToEmails(SendEmailVo sendEmailVo) throws Exception {
		postService.sendToEmails(sendEmailVo);
	}

	/**
	 * 메인 페이지에서 출력할 공고목록을 조회한다".
	 *
	 * @return 기술스택 목록 조회 결과
	 * @throws Exception
	 */
	@ElService(key = "POS0003List")
	@RequestMapping(value = "POS0003List")
	@ElDescription(sub = "메인 페이지 공고목록 조회", desc = "메인 페이지에서 출력할 공고목록을 조회한다")
	public MainPostingListVo selectPostingList() throws Exception {
		return postService.selectPostingList();
	}

	// 공고 이미지 업로드 - WebSquare 기본 업로드 시스템 사용 (기업 업로드 방식과 동일)
	
	/**
	 * 기업이 스카웃한 유저를 저장한다.
	 * @param jobPostingId 공고 ID, accountIds 스카웃 된 User ID List
	 * @throws Exception
	 */
	@ElService(key = "POS0006List")
	@RequestMapping(value = "POS0006List")
	@ElDescription(sub = "스카웃 유저 저장", desc = "기업이 스카웃한 유저를 저장한다")
	public void insertScoutRequest(ScoutUserVo scoutUserVo) throws Exception {
		postService.insertScoutRequest(scoutUserVo);
	}
}
