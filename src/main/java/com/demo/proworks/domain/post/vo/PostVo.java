package com.demo.proworks.domain.post.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "공고정보")
public class PostVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "job_posting_id", physicalName = "jobPostingId", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String jobPostingId;

    @ElDtoField(logicalName = "company_id", physicalName = "companyId", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String companyId;

    @ElDtoField(logicalName = "title", physicalName = "title", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String title;

    @ElDtoField(logicalName = "description", physicalName = "description", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String description;

    @ElDtoField(logicalName = "required_skills", physicalName = "requiredSkills", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String requiredSkills;

    @ElDtoField(logicalName = "experience_level", physicalName = "experienceLevel", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String experienceLevel;

    @ElDtoField(logicalName = "salary_range", physicalName = "salaryRange", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String salaryRange;

    @ElDtoField(logicalName = "location", physicalName = "location", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String location;

    @ElDtoField(logicalName = "work_type", physicalName = "workType", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String workType;

    @ElDtoField(logicalName = "preferred_developer_types", physicalName = "preferredDeveloperTypes", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String preferredDeveloperTypes;

    @ElDtoField(logicalName = "expires_at", physicalName = "expiresAt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String expiresAt;

    @ElDtoField(logicalName = "posted_at", physicalName = "postedAt", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String postedAt;

    @ElDtoField(logicalName = "status", physicalName = "status", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String status;

    @ElVoField(physicalName = "jobPostingId")
    public String getJobPostingId(){
        return jobPostingId;
    }

    @ElVoField(physicalName = "jobPostingId")
    public void setJobPostingId(String jobPostingId){
        this.jobPostingId = jobPostingId;
    }

    @ElVoField(physicalName = "companyId")
    public String getCompanyId(){
        return companyId;
    }

    @ElVoField(physicalName = "companyId")
    public void setCompanyId(String companyId){
        this.companyId = companyId;
    }

    @ElVoField(physicalName = "title")
    public String getTitle(){
        return title;
    }

    @ElVoField(physicalName = "title")
    public void setTitle(String title){
        this.title = title;
    }

    @ElVoField(physicalName = "description")
    public String getDescription(){
        return description;
    }

    @ElVoField(physicalName = "description")
    public void setDescription(String description){
        this.description = description;
    }

    @ElVoField(physicalName = "requiredSkills")
    public String getRequiredSkills(){
        return requiredSkills;
    }

    @ElVoField(physicalName = "requiredSkills")
    public void setRequiredSkills(String requiredSkills){
        this.requiredSkills = requiredSkills;
    }

    @ElVoField(physicalName = "experienceLevel")
    public String getExperienceLevel(){
        return experienceLevel;
    }

    @ElVoField(physicalName = "experienceLevel")
    public void setExperienceLevel(String experienceLevel){
        this.experienceLevel = experienceLevel;
    }

    @ElVoField(physicalName = "salaryRange")
    public String getSalaryRange(){
        return salaryRange;
    }

    @ElVoField(physicalName = "salaryRange")
    public void setSalaryRange(String salaryRange){
        this.salaryRange = salaryRange;
    }

    @ElVoField(physicalName = "location")
    public String getLocation(){
        return location;
    }

    @ElVoField(physicalName = "location")
    public void setLocation(String location){
        this.location = location;
    }

    @ElVoField(physicalName = "workType")
    public String getWorkType(){
        return workType;
    }

    @ElVoField(physicalName = "workType")
    public void setWorkType(String workType){
        this.workType = workType;
    }

    @ElVoField(physicalName = "preferredDeveloperTypes")
    public String getPreferredDeveloperTypes(){
        return preferredDeveloperTypes;
    }

    @ElVoField(physicalName = "preferredDeveloperTypes")
    public void setPreferredDeveloperTypes(String preferredDeveloperTypes){
        this.preferredDeveloperTypes = preferredDeveloperTypes;
    }

    @ElVoField(physicalName = "expiresAt")
    public String getExpiresAt(){
        return expiresAt;
    }

    @ElVoField(physicalName = "expiresAt")
    public void setExpiresAt(String expiresAt){
        this.expiresAt = expiresAt;
    }

    @ElVoField(physicalName = "postedAt")
    public String getPostedAt(){
        return postedAt;
    }

    @ElVoField(physicalName = "postedAt")
    public void setPostedAt(String postedAt){
        this.postedAt = postedAt;
    }

    @ElVoField(physicalName = "status")
    public String getStatus(){
        return status;
    }

    @ElVoField(physicalName = "status")
    public void setStatus(String status){
        this.status = status;
    }

    @Override
    public String toString() {
        return "PostVo [jobPostingId=" + jobPostingId + ",companyId=" + companyId + ",title=" + title + ",description=" + description + ",requiredSkills=" + requiredSkills + ",experienceLevel=" + experienceLevel + ",salaryRange=" + salaryRange + ",location=" + location + ",workType=" + workType + ",preferredDeveloperTypes=" + preferredDeveloperTypes + ",expiresAt=" + expiresAt + ",postedAt=" + postedAt + ",status=" + status + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}
