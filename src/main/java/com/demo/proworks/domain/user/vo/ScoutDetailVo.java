package com.demo.proworks.domain.user.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "일반회원")
public class ScoutDetailVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public ScoutDetailVo(){
    }

    @ElDtoField(logicalName = "체크박스", physicalName = "chk", type = "boolean", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private boolean chk;

    @ElDtoField(logicalName = "이름", physicalName = "name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String name;

    @ElDtoField(logicalName = "경력", physicalName = "career", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String career;

    @ElDtoField(logicalName = "포지션", physicalName = "currentPosition", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String currentPosition;

    @ElDtoField(logicalName = "기술스택", physicalName = "techStack", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String techStack;

    @ElDtoField(logicalName = "mbti", physicalName = "typeCode", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String typeCode;

    @ElDtoField(logicalName = "이력서", physicalName = "resumeFileName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String resumeFileName;

    @ElDtoField(logicalName = "상태", physicalName = "applicationStatus", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String applicationStatus;

    @ElDtoField(logicalName = "이메일", physicalName = "email", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String email;

    @ElDtoField(logicalName = "유저 ID", physicalName = "accountId", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int accountId;

    @ElVoField(physicalName = "chk")
    public boolean isChk(){
        return chk;
    }

    @ElVoField(physicalName = "chk")
    public void setChk(boolean chk){
        this.chk = chk;
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

    @ElVoField(physicalName = "currentPosition")
    public String getCurrentPosition(){
        String ret = this.currentPosition;
        return ret;
    }

    @ElVoField(physicalName = "currentPosition")
    public void setCurrentPosition(String currentPosition){
        this.currentPosition = currentPosition;
    }

    @ElVoField(physicalName = "techStack")
    public String getTechStack(){
        String ret = this.techStack;
        return ret;
    }

    @ElVoField(physicalName = "techStack")
    public void setTechStack(String techStack){
        this.techStack = techStack;
    }

    @ElVoField(physicalName = "typeCode")
    public String getTypeCode(){
        String ret = this.typeCode;
        return ret;
    }

    @ElVoField(physicalName = "typeCode")
    public void setTypeCode(String typeCode){
        this.typeCode = typeCode;
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

    @ElVoField(physicalName = "applicationStatus")
    public String getApplicationStatus(){
        String ret = this.applicationStatus;
        return ret;
    }

    @ElVoField(physicalName = "applicationStatus")
    public void setApplicationStatus(String applicationStatus){
        this.applicationStatus = applicationStatus;
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

    @ElVoField(physicalName = "accountId")
    public int getAccountId(){
        return accountId;
    }

    @ElVoField(physicalName = "accountId")
    public void setAccountId(int accountId){
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ScoutDetailVo [");
        sb.append("chk").append("=").append(chk).append(",");
        sb.append("name").append("=").append(name).append(",");
        sb.append("career").append("=").append(career).append(",");
        sb.append("currentPosition").append("=").append(currentPosition).append(",");
        sb.append("techStack").append("=").append(techStack).append(",");
        sb.append("typeCode").append("=").append(typeCode).append(",");
        sb.append("resumeFileName").append("=").append(resumeFileName).append(",");
        sb.append("applicationStatus").append("=").append(applicationStatus).append(",");
        sb.append("email").append("=").append(email).append(",");
        sb.append("accountId").append("=").append(accountId);
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