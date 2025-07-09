package com.demo.proworks.domain.post.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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

        List<PostVo> postList = postService.selectListPost(postVo);                  
        long totCnt = postService.selectListCountPost(postVo);
	
		PostListVo retPostList = new PostListVo();
		retPostList.setPostVoList(postList); 
		retPostList.setTotalCount(totCnt);
		retPostList.setPageSize(postVo.getPageSize());
		retPostList.setPageIndex(postVo.getPageIndex());

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
    	PostVo selectPostVo = postService.selectPost(postVo);    	    
		
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
 
    	postService.updatePost(postVo);                                            
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
