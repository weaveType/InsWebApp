package com.demo.proworks.domain.user.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "지원현황")
public class ApplicationHistoryVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public ApplicationHistoryVo(){
    }

    @ElDtoField(logicalName = "application_id", physicalName = "applicationId", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int applicationId;

    @ElDtoField(logicalName = "user_id", physicalName = "userId", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int userId;

    @ElDtoField(logicalName = "company", physicalName = "company", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String company;

    @ElDtoField(logicalName = "position", physicalName = "position", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String position;

    @ElDtoField(logicalName = "application_date", physicalName = "applicationDate", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String applicationDate;

    @ElDtoField(logicalName = "status", physicalName = "status", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String status;

    @ElDtoField(logicalName = "result", physicalName = "result", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String result;

    @ElDtoField(logicalName = "logo", physicalName = "logo", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String logo;

    @ElDtoField(logicalName = "job_posting_id", physicalName = "jobPostingId", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int jobPostingId;

    // 통계용 필드들
    @ElDtoField(logicalName = "status_count", physicalName = "statusCount", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int statusCount;

    @ElDtoField(logicalName = "status_type", physicalName = "statusType", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String statusType;

    @ElVoField(physicalName = "applicationId")
    public int getApplicationId(){
        return applicationId;
    }

    @ElVoField(physicalName = "applicationId")
    public void setApplicationId(int applicationId){
        this.applicationId = applicationId;
    }

    @ElVoField(physicalName = "userId")
    public int getUserId(){
        return userId;
    }

    @ElVoField(physicalName = "userId")
    public void setUserId(int userId){
        this.userId = userId;
    }

    @ElVoField(physicalName = "company")
    public String getCompany(){
        String ret = this.company;
        return ret;
    }

    @ElVoField(physicalName = "company")
    public void setCompany(String company){
        this.company = company;
    }

    @ElVoField(physicalName = "position")
    public String getPosition(){
        String ret = this.position;
        return ret;
    }

    @ElVoField(physicalName = "position")
    public void setPosition(String position){
        this.position = position;
    }

    @ElVoField(physicalName = "applicationDate")
    public String getApplicationDate(){
        String ret = this.applicationDate;
        return ret;
    }

    @ElVoField(physicalName = "applicationDate")
    public void setApplicationDate(String applicationDate){
        this.applicationDate = applicationDate;
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

    @ElVoField(physicalName = "result")
    public String getResult(){
        String ret = this.result;
        return ret;
    }

    @ElVoField(physicalName = "result")
    public void setResult(String result){
        this.result = result;
    }

    @ElVoField(physicalName = "logo")
    public String getLogo(){
        String ret = this.logo;
        return ret;
    }

    @ElVoField(physicalName = "logo")
    public void setLogo(String logo){
        this.logo = logo;
    }

    @ElVoField(physicalName = "jobPostingId")
    public int getJobPostingId(){
        return jobPostingId;
    }

    @ElVoField(physicalName = "jobPostingId")
    public void setJobPostingId(int jobPostingId){
        this.jobPostingId = jobPostingId;
    }

    @ElVoField(physicalName = "statusCount")
    public int getStatusCount(){
        return statusCount;
    }

    @ElVoField(physicalName = "statusCount")
    public void setStatusCount(int statusCount){
        this.statusCount = statusCount;
    }

    @ElVoField(physicalName = "statusType")
    public String getStatusType(){
        String ret = this.statusType;
        return ret;
    }

    @ElVoField(physicalName = "statusType")
    public void setStatusType(String statusType){
        this.statusType = statusType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ApplicationHistoryVo [");
        sb.append("applicationId").append("=").append(applicationId).append(",");
        sb.append("userId").append("=").append(userId).append(",");
        sb.append("company").append("=").append(company).append(",");
        sb.append("position").append("=").append(position).append(",");
        sb.append("applicationDate").append("=").append(applicationDate).append(",");
        sb.append("status").append("=").append(status).append(",");
        sb.append("result").append("=").append(result).append(",");
        sb.append("logo").append("=").append(logo).append(",");
        sb.append("jobPostingId").append("=").append(jobPostingId).append(",");
        sb.append("statusCount").append("=").append(statusCount).append(",");
        sb.append("statusType").append("=").append(statusType);
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