package com.demo.proworks.common.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.inswave.elfw.annotation.OpenApi;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "이메일 정보")
public class EmailVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public EmailVo(){
        // 초기화 시 role을 빈 문자열로 설정
        this.role = "";
    }

    @ElDtoField(logicalName = "이메일", physicalName = "email", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String email;

    @ElDtoField(logicalName = "상태", physicalName = "role", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "이메일 중복 확인 결과 상태 (AVAILABLE/DUPLICATE)", attr = "")
    private String role;

    @ElVoField(physicalName = "email")
    public String getEmail(){
        String ret = this.email;
        return ret;
    }

    @ElVoField(physicalName = "email")
    public void setEmail(String email){
        this.email = email;
    }

    @ElVoField(physicalName = "role")
    public String getRole(){
        String ret = this.role;
        return ret;
    }

    @ElVoField(physicalName = "role")
    public void setRole(String role){
        this.role = role;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EmailVo [");
        sb.append("email").append("=").append(email);
        sb.append(", role").append("=").append(role);
        sb.append("]");
        return sb.toString();
    }

    public boolean isFixedLengthVo() {
        return false;
    }

    @Override
    public void _xStreamEnc() {
    }

    @Override
    public void _xStreamDec() {
    }
}
