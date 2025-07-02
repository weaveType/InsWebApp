package com.demo.proworks.domain.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.domain.user.service.UserService;
import com.demo.proworks.domain.user.vo.UserVo;
import com.demo.proworks.domain.user.dao.UserDAO;

/**  
 * @subject     : 일반유저 관련 처리를 담당하는 ServiceImpl
 * @description	: 일반유저 관련 처리를 담당하는 ServiceImpl
 * @author      : Inswave
 * @since       : 2025/07/02
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/02			 Inswave	 		최초 생성
 * 
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Resource(name="userDAO")
    private UserDAO userDAO;
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;

    /**
     * 일반유저 목록을 조회합니다.
     *
     * @process
     * 1. 일반유저 페이징 처리하여 목록을 조회한다.
     * 2. 결과 List<UserVo>을(를) 리턴한다.
     * 
     * @param  userVo 일반유저 UserVo
     * @return 일반유저 목록 List<UserVo>
     * @throws Exception
     */
	public List<UserVo> selectListUser(UserVo userVo) throws Exception {
		List<UserVo> list = userDAO.selectListUser(userVo);	
	
		return list;
	}

    /**
     * 조회한 일반유저 전체 카운트
     *
     * @process
     * 1. 일반유저 조회하여 전체 카운트를 리턴한다.
     * 
     * @param  userVo 일반유저 UserVo
     * @return 일반유저 목록 전체 카운트
     * @throws Exception
     */
	public long selectListCountUser(UserVo userVo) throws Exception {
		return userDAO.selectListCountUser(userVo);
	}

    /**
     * 일반유저를 상세 조회한다.
     *
     * @process
     * 1. 일반유저를 상세 조회한다.
     * 2. 결과 UserVo을(를) 리턴한다.
     * 
     * @param  userVo 일반유저 UserVo
     * @return 단건 조회 결과
     * @throws Exception
     */
	public UserVo selectUser(UserVo userVo) throws Exception {
		UserVo resultVO = userDAO.selectUser(userVo);			
        
        return resultVO;
	}

    /**
     * 일반유저를 등록 처리 한다.
     *
     * @process
     * 1. 일반유저를 등록 처리 한다.
     * 
     * @param  userVo 일반유저 UserVo
     * @return 번호
     * @throws Exception
     */
	public int insertUser(UserVo userVo) throws Exception {
		return userDAO.insertUser(userVo);	
	}
	
    /**
     * 일반유저를 갱신 처리 한다.
     *
     * @process
     * 1. 일반유저를 갱신 처리 한다.
     * 
     * @param  userVo 일반유저 UserVo
     * @return 번호
     * @throws Exception
     */
	public int updateUser(UserVo userVo) throws Exception {				
		return userDAO.updateUser(userVo);	   		
	}

    /**
     * 일반유저를 삭제 처리 한다.
     *
     * @process
     * 1. 일반유저를 삭제 처리 한다.
     * 
     * @param  userVo 일반유저 UserVo
     * @return 번호
     * @throws Exception
     */
	public int deleteUser(UserVo userVo) throws Exception {
		return userDAO.deleteUser(userVo);
	}
	
}
