package com.demo.proworks.domain.post.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "공고정보")
public class MainPostingVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public MainPostingVo(){
    }

    @ElDtoField(logicalName = "기업명", physicalName = "name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String name;

    @ElDtoField(logicalName = "원하는 성향", physicalName = "preferredDeveloperTypes", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String preferredDeveloperTypes;

    @ElDtoField(logicalName = "지역", physicalName = "location", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String location;

    @ElDtoField(logicalName = "경력", physicalName = "experienceLevel", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String experienceLevel;

    @ElDtoField(logicalName = "공고 ID", physicalName = "jobPostingId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String jobPostingId;

    @ElVoField(physicalName = "name")
    public String getName(){
        String ret = this.name;
        return ret;
    }

    @ElVoField(physicalName = "name")
    public void setName(String name){
        this.name = name;
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

    @ElVoField(physicalName = "location")
    public String getLocation(){
        String ret = this.location;
        return ret;
    }

    @ElVoField(physicalName = "location")
    public void setLocation(String location){
        this.location = location;
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

    @ElVoField(physicalName = "jobPostingId")
    public String getJobPostingId(){
        String ret = this.jobPostingId;
        return ret;
    }

    @ElVoField(physicalName = "jobPostingId")
    public void setJobPostingId(String jobPostingId){
        this.jobPostingId = jobPostingId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MainPostingVo [");
        sb.append("name").append("=").append(name).append(",");
        sb.append("preferredDeveloperTypes").append("=").append(preferredDeveloperTypes).append(",");
        sb.append("location").append("=").append(location).append(",");
        sb.append("experienceLevel").append("=").append(experienceLevel).append(",");
        sb.append("jobPostingId").append("=").append(jobPostingId);
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
