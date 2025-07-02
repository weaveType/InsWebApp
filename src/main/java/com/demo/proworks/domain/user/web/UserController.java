package com.demo.proworks.domain.user.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.domain.user.service.UserService;
import com.demo.proworks.domain.user.vo.UserVo;
import com.demo.proworks.domain.user.vo.UserListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;

/**  
 * @subject     : 일반유저 관련 처리를 담당하는 컨트롤러
 * @description : 일반유저 관련 처리를 담당하는 컨트롤러
 * @author      : Inswave
 * @since       : 2025/07/02
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/07/02			 Inswave	 		최초 생성
 * 
 */
@Controller
public class UserController {
	
    /** UserService */
    @Resource(name = "userServiceImpl")
    private UserService userService;
	
    
    /**
     * 일반유저 목록을 조회합니다.
     *
     * @param  userVo 일반유저
     * @return 목록조회 결과
     * @throws Exception
     */
    @ElService(key="US0001List")
    @RequestMapping(value="US0001List")    
    @ElDescription(sub="일반유저 목록조회",desc="페이징을 처리하여 일반유저 목록 조회를 한다.")               
    public UserListVo selectListUser(UserVo userVo) throws Exception {    	   	

        List<UserVo> userList = userService.selectListUser(userVo);                  
        long totCnt = userService.selectListCountUser(userVo);
	
		UserListVo retUserList = new UserListVo();
		retUserList.setUserVoList(userList); 
		retUserList.setTotalCount(totCnt);
		retUserList.setPageSize(userVo.getPageSize());
		retUserList.setPageIndex(userVo.getPageIndex());

        return retUserList;            
    }  
        
    /**
     * 일반유저을 단건 조회 처리 한다.
     *
     * @param  userVo 일반유저
     * @return 단건 조회 결과
     * @throws Exception
     */
    @ElService(key = "US0001UpdView")    
    @RequestMapping(value="US0001UpdView") 
    @ElDescription(sub = "일반유저 갱신 폼을 위한 조회", desc = "일반유저 갱신 폼을 위한 조회를 한다.")    
    public UserVo selectUser(UserVo userVo) throws Exception {
    	UserVo selectUserVo = userService.selectUser(userVo);    	    
		
        return selectUserVo;
    } 
 
    /**
     * 일반유저를 등록 처리 한다.
     *
     * @param  userVo 일반유저
     * @throws Exception
     */
    @ElService(key="US0001Ins")    
    @RequestMapping(value="US0001Ins")
    @ElDescription(sub="일반유저 등록처리",desc="일반유저를 등록 처리 한다.")
    public void insertUser(UserVo userVo) throws Exception {    	 
    	userService.insertUser(userVo);   
    }
       
    /**
     * 일반유저를 갱신 처리 한다.
     *
     * @param  userVo 일반유저
     * @throws Exception
     */
    @ElService(key="US0001Upd")    
    @RequestMapping(value="US0001Upd")    
    @ElValidator(errUrl="/user/userRegister", errContinue=true)
    @ElDescription(sub="일반유저 갱신처리",desc="일반유저를 갱신 처리 한다.")    
    public void updateUser(UserVo userVo) throws Exception {  
 
    	userService.updateUser(userVo);                                            
    }

    /**
     * 일반유저를 삭제 처리한다.
     *
     * @param  userVo 일반유저    
     * @throws Exception
     */
    @ElService(key = "US0001Del")    
    @RequestMapping(value="US0001Del")
    @ElDescription(sub = "일반유저 삭제처리", desc = "일반유저를 삭제 처리한다.")    
    public void deleteUser(UserVo userVo) throws Exception {
        userService.deleteUser(userVo);
    }
   
}
