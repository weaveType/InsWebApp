package com.demo.proworks.domain.user.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "일반회원")
public class UserVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "user_id", physicalName = "userId", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String userId;

    @ElDtoField(logicalName = "email", physicalName = "email", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String email;

    @ElDtoField(logicalName = "password", physicalName = "password", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String password;

    @ElDtoField(logicalName = "role_id", physicalName = "roleId", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String roleId;

    @ElDtoField(logicalName = "name", physicalName = "name", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String name;

    @ElDtoField(logicalName = "email_consent", physicalName = "emailConsent", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String emailConsent;

    @ElDtoField(logicalName = "created_at", physicalName = "createdAt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String createdAt;

    @ElDtoField(logicalName = "updated_at", physicalName = "updatedAt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String updatedAt;

    @ElVoField(physicalName = "userId")
    public String getUserId(){
        return userId;
    }

    @ElVoField(physicalName = "userId")
    public void setUserId(String userId){
        this.userId = userId;
    }

    @ElVoField(physicalName = "email")
    public String getEmail(){
        return email;
    }

    @ElVoField(physicalName = "email")
    public void setEmail(String email){
        this.email = email;
    }

    @ElVoField(physicalName = "password")
    public String getPassword(){
        return password;
    }

    @ElVoField(physicalName = "password")
    public void setPassword(String password){
        this.password = password;
    }

    @ElVoField(physicalName = "roleId")
    public String getRoleId(){
        return roleId;
    }

    @ElVoField(physicalName = "roleId")
    public void setRoleId(String roleId){
        this.roleId = roleId;
    }

    @ElVoField(physicalName = "name")
    public String getName(){
        return name;
    }

    @ElVoField(physicalName = "name")
    public void setName(String name){
        this.name = name;
    }

    @ElVoField(physicalName = "emailConsent")
    public String getEmailConsent(){
        return emailConsent;
    }

    @ElVoField(physicalName = "emailConsent")
    public void setEmailConsent(String emailConsent){
        this.emailConsent = emailConsent;
    }

    @ElVoField(physicalName = "createdAt")
    public String getCreatedAt(){
        return createdAt;
    }

    @ElVoField(physicalName = "createdAt")
    public void setCreatedAt(String createdAt){
        this.createdAt = createdAt;
    }

    @ElVoField(physicalName = "updatedAt")
    public String getUpdatedAt(){
        return updatedAt;
    }

    @ElVoField(physicalName = "updatedAt")
    public void setUpdatedAt(String updatedAt){
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "UserVo [userId=" + userId + ",email=" + email + ",password=" + password + ",roleId=" + roleId + ",name=" + name + ",emailConsent=" + emailConsent + ",createdAt=" + createdAt + ",updatedAt=" + updatedAt + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}
