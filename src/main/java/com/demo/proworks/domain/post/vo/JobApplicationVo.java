package com.demo.proworks.domain.post.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "공고정보")
public class JobApplicationVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public JobApplicationVo(){
    }

    @ElDtoField(logicalName = "공고ID", physicalName = "jobPostingId", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int jobPostingId;

    @ElDtoField(logicalName = "지원자ID", physicalName = "accountId", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int accountId;

    @ElDtoField(logicalName = "상태값", physicalName = "applicationStatus", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String applicationStatus;

    @ElVoField(physicalName = "jobPostingId")
    public int getJobPostingId(){
        return jobPostingId;
    }

    @ElVoField(physicalName = "jobPostingId")
    public void setJobPostingId(int jobPostingId){
        this.jobPostingId = jobPostingId;
    }

    @ElVoField(physicalName = "accountId")
    public int getAccountId(){
        return accountId;
    }

    @ElVoField(physicalName = "accountId")
    public void setAccountId(int accountId){
        this.accountId = accountId;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("JobApplicationVo [");
        sb.append("jobPostingId").append("=").append(jobPostingId).append(",");
        sb.append("accountId").append("=").append(accountId).append(",");
        sb.append("applicationStatus").append("=").append(applicationStatus);
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
