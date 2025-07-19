package com.demo.proworks.domain.post.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "공고정보")
public class PostVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public PostVo(){
    }

    @ElDtoField(logicalName = "job_posting_id", physicalName = "jobPostingId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String jobPostingId;

    @ElDtoField(logicalName = "company_id", physicalName = "companyId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String companyId;

    @ElDtoField(logicalName = "title", physicalName = "title", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String title;

    @ElDtoField(logicalName = "description", physicalName = "description", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String description;

    @ElDtoField(logicalName = "selected_tech_stack_names", physicalName = "selectedTechStackNames", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String selectedTechStackNames;

    @ElDtoField(logicalName = "experience_level", physicalName = "experienceLevel", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String experienceLevel;

    @ElDtoField(logicalName = "salary_range", physicalName = "salaryRange", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String salaryRange;

    @ElDtoField(logicalName = "location", physicalName = "location", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String location;

    @ElDtoField(logicalName = "work_type", physicalName = "workType", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String workType;

    @ElDtoField(logicalName = "preferred_developer_types", physicalName = "preferredDeveloperTypes", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String preferredDeveloperTypes;

    @ElDtoField(logicalName = "expires_at", physicalName = "expiresAt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String expiresAt;

    @ElDtoField(logicalName = "posted_at", physicalName = "postedAt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String postedAt;

    @ElDtoField(logicalName = "status", physicalName = "status", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String status;

    @ElDtoField(logicalName = "MBTI_매칭_필터", physicalName = "mbtiMatchFilter", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String mbtiMatchFilter;

    @ElDtoField(logicalName = "사용자_MBTI", physicalName = "userMbti", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String userMbti;

    @ElDtoField(logicalName = "company_name", physicalName = "companyName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String companyName;

    @ElDtoField(logicalName = "industry", physicalName = "industry", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String industry;

    @ElDtoField(logicalName = "emp_count", physicalName = "empCount", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String empCount;

    @ElDtoField(logicalName = "job_image_file_name", physicalName = "jobImageFileName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String jobImageFileName;

    @ElVoField(physicalName = "jobPostingId")
    public String getJobPostingId(){
        String ret = this.jobPostingId;
        return ret;
    }

    @ElVoField(physicalName = "jobPostingId")
    public void setJobPostingId(String jobPostingId){
        this.jobPostingId = jobPostingId;
    }

    @ElVoField(physicalName = "companyId")
    public String getCompanyId(){
        String ret = this.companyId;
        return ret;
    }

    @ElVoField(physicalName = "companyId")
    public void setCompanyId(String companyId){
        this.companyId = companyId;
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

    @ElVoField(physicalName = "description")
    public String getDescription(){
        String ret = this.description;
        return ret;
    }

    @ElVoField(physicalName = "description")
    public void setDescription(String description){
        this.description = description;
    }

    @ElVoField(physicalName = "selectedTechStackNames")
    public String getSelectedTechStackNames(){
        String ret = this.selectedTechStackNames;
        return ret;
    }

    @ElVoField(physicalName = "selectedTechStackNames")
    public void setSelectedTechStackNames(String selectedTechStackNames){
        this.selectedTechStackNames = selectedTechStackNames;
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

    @ElVoField(physicalName = "salaryRange")
    public String getSalaryRange(){
        String ret = this.salaryRange;
        return ret;
    }

    @ElVoField(physicalName = "salaryRange")
    public void setSalaryRange(String salaryRange){
        this.salaryRange = salaryRange;
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

    @ElVoField(physicalName = "workType")
    public String getWorkType(){
        String ret = this.workType;
        return ret;
    }

    @ElVoField(physicalName = "workType")
    public void setWorkType(String workType){
        this.workType = workType;
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

    @ElVoField(physicalName = "expiresAt")
    public String getExpiresAt(){
        String ret = this.expiresAt;
        return ret;
    }

    @ElVoField(physicalName = "expiresAt")
    public void setExpiresAt(String expiresAt){
        this.expiresAt = expiresAt;
    }

    @ElVoField(physicalName = "postedAt")
    public String getPostedAt(){
        String ret = this.postedAt;
        return ret;
    }

    @ElVoField(physicalName = "postedAt")
    public void setPostedAt(String postedAt){
        this.postedAt = postedAt;
    }

    @ElVoField(physicalName = "status")
    public String getStatus(){
        String ret = this.status;
        return ret;
    }

    @ElVoField(physicalName = "status")
    public void setStatus(String status){
        this.status = status;
    }

    @ElVoField(physicalName = "mbtiMatchFilter")
    public String getMbtiMatchFilter(){
        String ret = this.mbtiMatchFilter;
        return ret;
    }

    @ElVoField(physicalName = "mbtiMatchFilter")
    public void setMbtiMatchFilter(String mbtiMatchFilter){
        this.mbtiMatchFilter = mbtiMatchFilter;
    }

    @ElVoField(physicalName = "userMbti")
    public String getUserMbti(){
        String ret = this.userMbti;
        return ret;
    }

    @ElVoField(physicalName = "userMbti")
    public void setUserMbti(String userMbti){
        this.userMbti = userMbti;
    }

    @ElVoField(physicalName = "companyName")
    public String getCompanyName(){
        String ret = this.companyName;
        return ret;
    }

    @ElVoField(physicalName = "companyName")
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    @ElVoField(physicalName = "industry")
    public String getIndustry(){
        String ret = this.industry;
        return ret;
    }

    @ElVoField(physicalName = "industry")
    public void setIndustry(String industry){
        this.industry = industry;
    }

    @ElVoField(physicalName = "empCount")
    public String getEmpCount(){
        String ret = this.empCount;
        return ret;
    }

    @ElVoField(physicalName = "empCount")
    public void setEmpCount(String empCount){
        this.empCount = empCount;
    }

    @ElVoField(physicalName = "jobImageFileName")
    public String getJobImageFileName(){
        String ret = this.jobImageFileName;
        return ret;
    }

    @ElVoField(physicalName = "jobImageFileName")
    public void setJobImageFileName(String jobImageFileName){
        this.jobImageFileName = jobImageFileName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PostVo [");
        sb.append("jobPostingId").append("=").append(jobPostingId).append(",");
        sb.append("companyId").append("=").append(companyId).append(",");
        sb.append("title").append("=").append(title).append(",");
        sb.append("description").append("=").append(description).append(",");
        sb.append("selectedTechStackNames").append("=").append(selectedTechStackNames).append(",");
        sb.append("experienceLevel").append("=").append(experienceLevel).append(",");
        sb.append("salaryRange").append("=").append(salaryRange).append(",");
        sb.append("location").append("=").append(location).append(",");
        sb.append("workType").append("=").append(workType).append(",");
        sb.append("preferredDeveloperTypes").append("=").append(preferredDeveloperTypes).append(",");
        sb.append("expiresAt").append("=").append(expiresAt).append(",");
        sb.append("postedAt").append("=").append(postedAt).append(",");
        sb.append("status").append("=").append(status).append(",");
        sb.append("mbtiMatchFilter").append("=").append(mbtiMatchFilter).append(",");
        sb.append("userMbti").append("=").append(userMbti).append(",");
        sb.append("companyName").append("=").append(companyName).append(",");
        sb.append("industry").append("=").append(industry).append(",");
        sb.append("empCount").append("=").append(empCount).append(",");
        sb.append("jobImageFileName").append("=").append(jobImageFileName);
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
