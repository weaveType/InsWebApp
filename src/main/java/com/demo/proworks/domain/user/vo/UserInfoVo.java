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
    
    @ElDtoField(logicalName = "자기소개", physicalName = "bio", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String bio;
    
    @ElDtoField(logicalName = "테스트완료여부", physicalName = "testChecked", type = "boolean", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private boolean testChecked;

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
    
    @ElVoField(physicalName = "bio")
    public String getBio(){
        String ret = this.bio;
        return ret;
    }

    @ElVoField(physicalName = "bio")
    public void setBio(String bio){
        this.bio = bio;
    }
    
    @ElVoField(physicalName = "testChecked")
    public boolean getTestChecked(){
        return testChecked;
    }

    @ElVoField(physicalName = "testChecked")
    public void setTestChecked(boolean testChecked){
        this.testChecked = testChecked;
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
        sb.append("bio").append("=").append(bio).append(",");
        sb.append("testChecked").append("=").append(testChecked);
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
