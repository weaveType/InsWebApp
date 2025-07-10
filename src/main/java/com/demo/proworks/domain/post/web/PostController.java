package com.demo.proworks.domain.post.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.domain.post.service.PostService;
import com.demo.proworks.domain.post.vo.PostVo;
import com.demo.proworks.domain.post.vo.PostListVo;
import com.demo.proworks.domain.post.vo.TechStackVo;
import com.demo.proworks.domain.post.vo.TechStackListVo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;
import com.inswave.elfw.core.UserHeader;
import com.inswave.elfw.util.ControllerContextUtil;

/**  
 * @subject     : 공고정보 관련 처리를 담당하는 컨트롤러
 * @description : 공고정보 관련 처리를 담당하는 컨트롤러
 * @author      : Inswave
 * @since       : 2025/07/03
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/03			 Inswave	 		최초 생성
 * 
 */
@Controller
public class PostController {
	
    /** PostService */
    @Resource(name = "postServiceImpl")
    private PostService postService;
	
    /**
     * 세션에서 현재 로그인한 사용자의 Company ID를 가져옵니다.
     * 
     * @return Company ID (없으면 null)
     */
    private String getCurrentUserCompanyId() {
        try {
            // 프로웍스 세션 유틸리티 사용
            UserHeader userHeader = ControllerContextUtil.getUserHeader();
            if (userHeader != null) {
                String userId = userHeader.getUserId();
                System.out.println("=== 세션 디버깅 ===");
                System.out.println("UserHeader 존재: " + (userHeader != null));
                System.out.println("UserID: " + userId);
                
                // TODO: 실제 환경에서는 userId로 companys 테이블 조회하여 company_id 가져오기
                // 현재는 모든 로그인 사용자를 company_id = 1로 처리 (개발 중)
                if (userId != null) {
                    System.out.println("Company ID 반환: 1 (임시 고정값)");
                    return "1";
                }
            }
            
            System.out.println("=== 세션 정보 없음 - 기본값 반환 ===");
            // 세션 정보가 없는 경우에도 기본값 반환 (개발 중)
            return "1";
            
        } catch (Exception e) {
            // 로그 남기고 기본값 반환
            System.err.println("세션에서 Company ID 조회 실패: " + e.getMessage());
            e.printStackTrace();
            return "1";
        }
    }
    
    /**
     * 현재 사용자의 Company ID 권한을 검증합니다.
     * 
     * @param companyId 검증할 Company ID
     * @return 권한 있으면 true
     */
    private boolean hasCompanyPermission(String companyId) {
        String currentCompanyId = getCurrentUserCompanyId();
        return currentCompanyId != null && currentCompanyId.equals(companyId);
    }
	
    
    /**
     * 공고정보 목록을 조회합니다.
     *
     * @param  postVo 공고정보
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="POS0001List")
    @RequestMapping(value="POS0001List")    
    @ElDescription(sub="공고정보 목록조회",desc="페이징을 처리하여 공고정보 목록 조회를 한다.")               
    public PostListVo selectListPost(PostVo postVo) throws Exception {    	   	
        
        System.out.println("=== 공고 목록 조회 시작 ===");
        System.out.println("입력 받은 PostVo: " + postVo.toString());
        
        // 임시로 모든 공고 조회 (디버깅용)
        // 현재 사용자의 Company ID로 필터링
        String currentCompanyId = getCurrentUserCompanyId();
        System.out.println("현재 사용자 Company ID: " + currentCompanyId);
        
        // 임시로 Company ID 필터링 제거 (디버깅용)
        System.out.println("⚠️ 디버깅 모드: 모든 공고를 조회합니다.");
        // postVo.setCompanyId(currentCompanyId);
        
        // 페이징 기본값 설정
        if (postVo.getPageSize() <= 0) {
            postVo.setPageSize(10);
            System.out.println("기본 페이지 사이즈 설정: 10");
        }
        if (postVo.getPageIndex() <= 0) {
            postVo.setPageIndex(1);
            System.out.println("기본 페이지 인덱스 설정: 1");
        }
        
        // 페이징 정보 로그
        System.out.println("최종 페이지 사이즈: " + postVo.getPageSize());
        System.out.println("최종 페이지 인덱스: " + postVo.getPageIndex());

        List<PostVo> postList = postService.selectListPost(postVo);                  
        long totCnt = postService.selectListCountPost(postVo);
        
        System.out.println("조회된 공고 개수: " + postList.size());
        System.out.println("전체 공고 개수: " + totCnt);
        
        // 조회된 공고들 로그
        for (int i = 0; i < postList.size(); i++) {
            PostVo post = postList.get(i);
            System.out.println("공고 " + (i+1) + ": ID=" + post.getJobPostingId() + 
                             ", 제목=" + post.getTitle() + 
                             ", 회사ID=" + post.getCompanyId() +
                             ", 상태=" + post.getStatus());
        }
	
		PostListVo retPostList = new PostListVo();
		retPostList.setPostVoList(postList); 
		retPostList.setTotalCount(totCnt);
		retPostList.setPageSize(postVo.getPageSize());
		retPostList.setPageIndex(postVo.getPageIndex());
		
		System.out.println("=== 반환할 PostListVo ===");
		System.out.println("PostVoList 크기: " + retPostList.getPostVoList().size());
		System.out.println("TotalCount: " + retPostList.getTotalCount());
		System.out.println("=== 공고 목록 조회 완료 ===");

        return retPostList;            
    }  
        
    /**
     * 공고정보을 단건 조회 처리 한다.
     *
     * @param  postVo 공고정보
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "POS0001UpdView")    
    @RequestMapping(value="POS0001UpdView") 
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
     * @param  postVo 공고정보
     * @throws Exception
     */
    @ElService(key="POS0001Ins")    
    @RequestMapping(value="POS0001Ins")
    @ElDescription(sub="공고정보 등록처리",desc="공고정보를 등록 처리 한다.")
    public void insertPost(PostVo postVo) throws Exception {    	 
        
        // 현재 사용자의 Company ID 설정 (세션에서 가져오기)
        String currentCompanyId = getCurrentUserCompanyId();
        if (currentCompanyId == null) {
            throw new RuntimeException("로그인 정보를 확인할 수 없습니다.");
        }
        postVo.setCompanyId(currentCompanyId);
        
    	// 1. 공고 정보 등록 (job_posting_id가 자동 생성됨)
    	int result = postService.insertPost(postVo);
    	
    	if (result > 0) {
    	    // 2. 기술스택 관계 데이터 저장
    	    String selectedTechStackNames = postVo.getSelectedTechStackNames();
    	    if (selectedTechStackNames != null && !selectedTechStackNames.trim().isEmpty()) {
    	        try {
    	            // JSON 문자열을 파싱하여 기술스택 이름들 추출
    	            ObjectMapper objectMapper = new ObjectMapper();
    	            String[] techStackNames = objectMapper.readValue(selectedTechStackNames, String[].class);
    	            
    	            // 기술스택 이름으로 ID 찾아서 관계 테이블에 저장
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
    	            
    	            // 관계 데이터 저장
    	            if (!techStackIds.isEmpty()) {
    	                postService.saveTechStackRelations(postVo, techStackIds);
    	            }
    	            
    	        } catch (Exception e) {
    	            // JSON 파싱 실패 시 로그 남기고 계속 진행
    	            System.err.println("기술스택 JSON 파싱 오류: " + e.getMessage());
    	        }
    	    }
    	}
    }
       
    /**
     * 공고정보를 갱신 처리 한다.
     *
     * @param  postVo 공고정보
     * @throws Exception
     */
    @ElService(key="POS0001Upd")    
    @RequestMapping(value="POS0001Upd")    
    @ElValidator(errUrl="/post/postRegister", errContinue=true)
    @ElDescription(sub="공고정보 갱신처리",desc="공고정보를 갱신 처리 한다.")    
    public void updatePost(PostVo postVo) throws Exception {  
        
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
    }

    /**
     * 공고정보를 삭제 처리한다.
     *
     * @param  postVo 공고정보    
     * @throws Exception
     */
    @ElService(key = "POS0001Del")    
    @RequestMapping(value="POS0001Del")
    @ElDescription(sub = "공고정보 삭제처리", desc = "공고정보를 삭제 처리한다.")    
    public void deletePost(PostVo postVo) throws Exception {
        
        // 권한 검증: 기존 공고 조회해서 현재 사용자 회사 것인지 확인
        PostVo existingPost = postService.selectPost(postVo);
        if (existingPost == null) {
            throw new RuntimeException("존재하지 않는 공고입니다.");
        }
        
        if (!hasCompanyPermission(existingPost.getCompanyId())) {
            throw new RuntimeException("해당 공고를 삭제할 권한이 없습니다.");
        }
        
        // 기술스택 관계 먼저 삭제
        postService.deleteTechStackRelationsByPostId(postVo.getJobPostingId());
        
        // 공고 삭제 (소프트 삭제로 is_deleted = 1 설정)
        postService.deletePost(postVo);
    }

    /**
     * 기술스택 목록을 조회한다.
     *
     * @return 기술스택 목록 조회 결과
     * @throws Exception
     */
    @ElService(key="POS0002List")
    @RequestMapping(value="POS0002List")    
    @ElDescription(sub="기술스택 목록조회",desc="기술스택 목록을 조회한다.")               
    public List<TechStackVo> selectListTechStack() throws Exception {    	   	

        List<TechStackVo> techStackList = postService.selectListTechStack();

        return techStackList;            
    }
   
}
