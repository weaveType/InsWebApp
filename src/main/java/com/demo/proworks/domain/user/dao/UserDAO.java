package com.demo.proworks.domain.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.domain.user.vo.UserVo;
import com.demo.proworks.domain.user.dao.UserDAO;

/**  
 * @subject     : 일반회원 관련 처리를 담당하는 DAO
 * @description : 일반회원 관련 처리를 담당하는 DAO
 * @author      : Inswave
 * @since       : 2025/07/03
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/03			 Inswave	 		최초 생성
 * 
 */
@Repository("userDAO")
public class UserDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 일반회원 상세 조회한다.
     *  
     * @param  UserVo 일반회원
     * @return UserVo 일반회원
     * @throws ElException
     */
    public UserVo selectUser(UserVo vo) throws ElException {
        return (UserVo) selectByPk("com.demo.proworks.domain.user.selectUser", vo);
    }

    /**
     * 페이징을 처리하여 일반회원 목록조회를 한다.
     *  
     * @param  UserVo 일반회원
     * @return List<UserVo> 일반회원
     * @throws ElException
     */
    public List<UserVo> selectListUser(UserVo vo) throws ElException {      	
        return (List<UserVo>)list("com.demo.proworks.domain.user.selectListUser", vo);
    }

    /**
     * 일반회원 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  UserVo 일반회원
     * @return 일반회원 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountUser(UserVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.domain.user.selectListCountUser", vo);
    }
        
    /**
     * 일반회원를 등록한다.
     *  
     * @param  UserVo 일반회원
     * @return 번호
     * @throws ElException
     */
    public int insertUser(UserVo vo) throws ElException {    	
        return insert("com.demo.proworks.domain.user.insertUser", vo);
    }

    /**
     * 일반회원를 갱신한다.
     *  
     * @param  UserVo 일반회원
     * @return 번호
     * @throws ElException
     */
    public int updateUser(UserVo vo) throws ElException {
        return update("com.demo.proworks.domain.user.updateUser", vo);
    }

    /**
     * 일반회원를 삭제한다.
     *  
     * @param  UserVo 일반회원
     * @return 번호
     * @throws ElException
     */
    public int deleteUser(UserVo vo) throws ElException {
        return delete("com.demo.proworks.domain.user.deleteUser", vo);
    }

}
