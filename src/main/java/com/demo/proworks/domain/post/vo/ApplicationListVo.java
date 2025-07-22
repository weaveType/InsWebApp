package com.demo.proworks.domain.post.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "지원한 공고 회사 List")
public class ApplicationListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public ApplicationListVo(){
    }

    @ElDtoField(logicalName = "공고ID", physicalName = "jobPostingId", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int jobPostingId;

    @ElDtoField(logicalName = "회사명", physicalName = "name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String name;

    @ElDtoField(logicalName = "공고명", physicalName = "title", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String title;

    @ElDtoField(logicalName = "경력", physicalName = "experienceLevel", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String experienceLevel;

    @ElDtoField(logicalName = "MBTI_JSON_LIST", physicalName = "preferredDeveloperTypes", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String preferredDeveloperTypes;

    @ElVoField(physicalName = "jobPostingId")
    public int getJobPostingId(){
        return jobPostingId;
    }

    @ElVoField(physicalName = "jobPostingId")
    public void setJobPostingId(int jobPostingId){
        this.jobPostingId = jobPostingId;
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

    @ElVoField(physicalName = "title")
    public String getTitle(){
        String ret = this.title;
        return ret;
    }

    @ElVoField(physicalName = "title")
    public void setTitle(String title){
        this.title = title;
    }

    @ElVoField(physicalName = "experienceLevel")
    public String getExperienceLevel(){
        String ret = this.experienceLevel;
        return ret;
    }

    @ElVoField(physicalName = "experienceLevel")
    public void setExperienceLevel(String experienceLevel){
        this.experienceLevel = experienceLevel;
    }

    @ElVoField(physicalName = "preferredDeveloperTypes")
    public String getPreferredDeveloperTypes(){
        String ret = this.preferredDeveloperTypes;
        return ret;
    }

    @ElVoField(physicalName = "preferredDeveloperTypes")
    public void setPreferredDeveloperTypes(String preferredDeveloperTypes){
        this.preferredDeveloperTypes = preferredDeveloperTypes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ApplicationListVo [");
        sb.append("jobPostingId").append("=").append(jobPostingId).append(",");
        sb.append("name").append("=").append(name).append(",");
        sb.append("title").append("=").append(title).append(",");
        sb.append("experienceLevel").append("=").append(experienceLevel).append(",");
        sb.append("preferredDeveloperTypes").append("=").append(preferredDeveloperTypes);
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
