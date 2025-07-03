package com.demo.proworks.domain.user.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "일반회원")
public class UserListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public UserListVo(){
    }

    @ElDtoField(logicalName = "일반회원List", physicalName = "userVoList", type = "", typeKind = "List", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private java.util.List<com.demo.proworks.domain.user.vo.UserVo> userVoList;

    @ElVoField(physicalName = "userVoList")
    public java.util.List<com.demo.proworks.domain.user.vo.UserVo> getUserVoList(){
        return userVoList;
    }

    @ElVoField(physicalName = "userVoList")
    public void setUserVoList(java.util.List<com.demo.proworks.domain.user.vo.UserVo> userVoList){
        this.userVoList = userVoList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserListVo [");
        sb.append("userVoList").append("=").append(userVoList);
        sb.append("]");
        return sb.toString();

    }

    public boolean isFixedLengthVo() {
        return false;
    }

    @Override
    public void _xStreamEnc() {
        for( int i=0 ; userVoList != null && i < userVoList.size() ; i++ ) {
            com.demo.proworks.domain.user.vo.UserVo vo = (com.demo.proworks.domain.user.vo.UserVo)userVoList.get(i);
            vo._xStreamEnc();	 
        }
    }


    @Override
    public void _xStreamDec() {
        for( int i=0 ; userVoList != null && i < userVoList.size() ; i++ ) {
            com.demo.proworks.domain.user.vo.UserVo vo = (com.demo.proworks.domain.user.vo.UserVo)userVoList.get(i);
            vo._xStreamDec();	 
        }
    }


}
