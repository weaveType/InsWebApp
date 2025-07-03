package com.demo.proworks.domain.post.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.domain.post.vo.PostVo;
import com.demo.proworks.domain.post.dao.PostDAO;

/**  
 * @subject     : 공고정보 관련 처리를 담당하는 DAO
 * @description : 공고정보 관련 처리를 담당하는 DAO
 * @author      : Inswave
 * @since       : 2025/07/03
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/03			 Inswave	 		최초 생성
 * 
 */
@Repository("postDAO")
public class PostDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 공고정보 상세 조회한다.
     *  
     * @param  PostVo 공고정보
     * @return PostVo 공고정보
     * @throws ElException
     */
    public PostVo selectPost(PostVo vo) throws ElException {
        return (PostVo) selectByPk("com.demo.proworks.domain.post.selectPost", vo);
    }

    /**
     * 페이징을 처리하여 공고정보 목록조회를 한다.
     *  
     * @param  PostVo 공고정보
     * @return List<PostVo> 공고정보
     * @throws ElException
     */
    public List<PostVo> selectListPost(PostVo vo) throws ElException {      	
        return (List<PostVo>)list("com.demo.proworks.domain.post.selectListPost", vo);
    }

    /**
     * 공고정보 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  PostVo 공고정보
     * @return 공고정보 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountPost(PostVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.domain.post.selectListCountPost", vo);
    }
        
    /**
     * 공고정보를 등록한다.
     *  
     * @param  PostVo 공고정보
     * @return 번호
     * @throws ElException
     */
    public int insertPost(PostVo vo) throws ElException {    	
        return insert("com.demo.proworks.domain.post.insertPost", vo);
    }

    /**
     * 공고정보를 갱신한다.
     *  
     * @param  PostVo 공고정보
     * @return 번호
     * @throws ElException
     */
    public int updatePost(PostVo vo) throws ElException {
        return update("com.demo.proworks.domain.post.updatePost", vo);
    }

    /**
     * 공고정보를 삭제한다.
     *  
     * @param  PostVo 공고정보
     * @return 번호
     * @throws ElException
     */
    public int deletePost(PostVo vo) throws ElException {
        return delete("com.demo.proworks.domain.post.deletePost", vo);
    }

}
