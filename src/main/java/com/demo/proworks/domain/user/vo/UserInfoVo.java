package com.demo.proworks.domain.user.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "일반회원")
public class UserInfoVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public UserInfoVo(){
    }

    @ElDtoField(logicalName = "계정 ID", physicalName = "accountId", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int accountId;

    @ElDtoField(logicalName = "이메일", physicalName = "email", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String email;

    @ElDtoField(logicalName = "사용자명", physicalName = "name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String name;

    @ElDtoField(logicalName = "경력수준", physicalName = "career", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String career;

    @ElDtoField(logicalName = "지역", physicalName = "preferredLocations", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String preferredLocations;

    @ElDtoField(logicalName = "직무분야", physicalName = "currentPosition", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String currentPosition;

    @ElDtoField(logicalName = "희망연봉", physicalName = "yearSalary", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int yearSalary;

    @ElDtoField(logicalName = "MBTI완료여부", physicalName = "isMbtiChecked", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String isMbtiChecked;

    @ElDtoField(logicalName = "코드분석완료여부", physicalName = "isCodeChecked", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String isCodeChecked;

    @ElDtoField(logicalName = "자기소개", physicalName = "bio", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String bio;

    @ElDtoField(logicalName = "이력서 파일명", physicalName = "resumeFileName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String resumeFileName;

    @ElDtoField(logicalName = "프로필 이미지 파일명", physicalName = "profileImageName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String profileImageName;

    @ElVoField(physicalName = "accountId")
    public int getAccountId(){
        return accountId;
    }

    @ElVoField(physicalName = "accountId")
    public void setAccountId(int accountId){
        this.accountId = accountId;
    }

    @ElVoField(physicalName = "email")
    public String getEmail(){
        String ret = this.email;
        return ret;
    }

    @ElVoField(physicalName = "email")
    public void setEmail(String email){
        this.email = email;
    }

    @ElVoField(physicalName = "name")
    public String getName(){
        String ret = this.name;
        return ret;
    }

    @ElVoField(physicalName = "name")
    public void setName(String name){
        this.name = name;
    }

    @ElVoField(physicalName = "career")
    public String getCareer(){
        String ret = this.career;
        return ret;
    }

    @ElVoField(physicalName = "career")
    public void setCareer(String career){
        this.career = career;
    }

    @ElVoField(physicalName = "preferredLocations")
    public String getPreferredLocations(){
        String ret = this.preferredLocations;
        return ret;
    }

    @ElVoField(physicalName = "preferredLocations")
    public void setPreferredLocations(String preferredLocations){
        this.preferredLocations = preferredLocations;
    }

    @ElVoField(physicalName = "currentPosition")
    public String getCurrentPosition(){
        String ret = this.currentPosition;
        return ret;
    }

    @ElVoField(physicalName = "currentPosition")
    public void setCurrentPosition(String currentPosition){
        this.currentPosition = currentPosition;
    }

    @ElVoField(physicalName = "yearSalary")
    public int getYearSalary(){
        return yearSalary;
    }

    @ElVoField(physicalName = "yearSalary")
    public void setYearSalary(int yearSalary){
        this.yearSalary = yearSalary;
    }

    @ElVoField(physicalName = "isMbtiChecked")
    public String getIsMbtiChecked(){
        String ret = this.isMbtiChecked;
        return ret;
    }

    @ElVoField(physicalName = "isMbtiChecked")
    public void setIsMbtiChecked(String isMbtiChecked){
        this.isMbtiChecked = isMbtiChecked;
    }

    @ElVoField(physicalName = "isCodeChecked")
    public String getIsCodeChecked(){
        String ret = this.isCodeChecked;
        return ret;
    }

    @ElVoField(physicalName = "isCodeChecked")
    public void setIsCodeChecked(String isCodeChecked){
        this.isCodeChecked = isCodeChecked;
    }

    @ElVoField(physicalName = "bio")
    public String getBio(){
        String ret = this.bio;
        return ret;
    }

    @ElVoField(physicalName = "bio")
    public void setBio(String bio){
        this.bio = bio;
    }

    @ElVoField(physicalName = "resumeFileName")
    public String getResumeFileName(){
        String ret = this.resumeFileName;
        return ret;
    }

    @ElVoField(physicalName = "resumeFileName")
    public void setResumeFileName(String resumeFileName){
        this.resumeFileName = resumeFileName;
    }

    @ElVoField(physicalName = "profileImageName")
    public String getProfileImageName(){
        String ret = this.profileImageName;
        return ret;
    }

    @ElVoField(physicalName = "profileImageName")
    public void setProfileImageName(String profileImageName){
        this.profileImageName = profileImageName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserInfoVo [");
        sb.append("accountId").append("=").append(accountId).append(",");
        sb.append("email").append("=").append(email).append(",");
        sb.append("name").append("=").append(name).append(",");
        sb.append("career").append("=").append(career).append(",");
        sb.append("preferredLocations").append("=").append(preferredLocations).append(",");
        sb.append("currentPosition").append("=").append(currentPosition).append(",");
        sb.append("yearSalary").append("=").append(yearSalary).append(",");
        sb.append("isMbtiChecked").append("=").append(isMbtiChecked).append(",");
        sb.append("isCodeChecked").append("=").append(isCodeChecked).append(",");
        sb.append("bio").append("=").append(bio).append(",");
        sb.append("resumeFileName").append("=").append(resumeFileName).append(",");
        sb.append("profileImageName").append("=").append(profileImageName);
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
