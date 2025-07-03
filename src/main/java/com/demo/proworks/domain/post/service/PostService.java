package com.demo.proworks.domain.post.service;

import java.util.List;

import com.demo.proworks.domain.post.vo.PostVo;

/**  
 * @subject     : 공고정보 관련 처리를 담당하는 인터페이스
 * @description : 공고정보 관련 처리를 담당하는 인터페이스
 * @author      : Inswave
 * @since       : 2025/07/03
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/03			 Inswave	 		최초 생성
 * 
 */
public interface PostService {
	
    /**
     * 공고정보 페이징 처리하여 목록을 조회한다.
     *
     * @param  postVo 공고정보 PostVo
     * @return 공고정보 목록 List<PostVo>
     * @throws Exception
     */
	public List<PostVo> selectListPost(PostVo postVo) throws Exception;
	
    /**
     * 조회한 공고정보 전체 카운트
     * 
     * @param  postVo 공고정보 PostVo
     * @return 공고정보 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountPost(PostVo postVo) throws Exception;
	
    /**
     * 공고정보를 상세 조회한다.
     *
     * @param  postVo 공고정보 PostVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public PostVo selectPost(PostVo postVo) throws Exception;
		
    /**
     * 공고정보를 등록 처리 한다.
     *
     * @param  postVo 공고정보 PostVo
     * @return 번호
     * @throws Exception
     */
	public int insertPost(PostVo postVo) throws Exception;
	
    /**
     * 공고정보를 갱신 처리 한다.
     *
     * @param  postVo 공고정보 PostVo
     * @return 번호
     * @throws Exception
     */
	public int updatePost(PostVo postVo) throws Exception;
	
    /**
     * 공고정보를 삭제 처리 한다.
     *
     * @param  postVo 공고정보 PostVo
     * @return 번호
     * @throws Exception
     */
	public int deletePost(PostVo postVo) throws Exception;
	
}
