package com.demo.proworks.domain.post.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.domain.post.service.PostService;
import com.demo.proworks.domain.post.vo.PostVo;
import com.demo.proworks.domain.post.vo.PostListVo;

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
    	postService.insertPost(postVo);   
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
   
}
