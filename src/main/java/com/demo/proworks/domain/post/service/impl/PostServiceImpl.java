package com.demo.proworks.domain.post.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.domain.post.service.PostService;
import com.demo.proworks.domain.post.vo.PostVo;
import com.demo.proworks.domain.post.dao.PostDAO;

/**  
 * @subject     : 공고정보 관련 처리를 담당하는 ServiceImpl
 * @description	: 공고정보 관련 처리를 담당하는 ServiceImpl
 * @author      : Inswave
 * @since       : 2025/07/03
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/03			 Inswave	 		최초 생성
 * 
 */
@Service("postServiceImpl")
public class PostServiceImpl implements PostService {

    @Resource(name="postDAO")
    private PostDAO postDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;

    /**
     * 공고정보 목록을 조회합니다.
     *
     * @process
     * 1. 공고정보 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<PostVo>을(를) 리턴한다.
     * 
     * @param  postVo 공고정보 PostVo
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
     * @process
     * 1. 공고정보 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  postVo 공고정보 PostVo
     * @return 공고정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountPost(PostVo postVo) throws Exception {
		return postDAO.selectListCountPost(postVo);
	}

    /**
     * 공고정보를 상세 조회한다.
     *
     * @process
     * 1. 공고정보를 상세 조회한다.
     * 2. 결과 PostVo을(를) 리턴한다.
     * 
     * @param  postVo 공고정보 PostVo
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
     * @process
     * 1. 공고정보를 등록 처리 한다.
     * 
     * @param  postVo 공고정보 PostVo
     * @return 번호
     * @throws Exception
     */
	public int insertPost(PostVo postVo) throws Exception {
		return postDAO.insertPost(postVo);	
	}
	
    /**
     * 공고정보를 갱신 처리 한다.
     *
     * @process
     * 1. 공고정보를 갱신 처리 한다.
     * 
     * @param  postVo 공고정보 PostVo
     * @return 번호
     * @throws Exception
     */
	public int updatePost(PostVo postVo) throws Exception {				
		return postDAO.updatePost(postVo);	   		
	}

    /**
     * 공고정보를 삭제 처리 한다.
     *
     * @process
     * 1. 공고정보를 삭제 처리 한다.
     * 
     * @param  postVo 공고정보 PostVo
     * @return 번호
     * @throws Exception
     */
	public int deletePost(PostVo postVo) throws Exception {
		return postDAO.deletePost(postVo);
	}
	
}
