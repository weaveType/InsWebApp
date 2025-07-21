package com.demo.proworks.domain.post.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "스카웃 유저 정보")
public class ScoutUserVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public ScoutUserVo(){
    }

    @ElDtoField(logicalName = "공고ID", physicalName = "jobPostingId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String jobPostingId;

    @ElDtoField(logicalName = "스카웃 된 유저 List", physicalName = "accountIds", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String accountIds;

    @ElVoField(physicalName = "jobPostingId")
    public String getJobPostingId(){
        String ret = this.jobPostingId;
        return ret;
    }

    @ElVoField(physicalName = "jobPostingId")
    public void setJobPostingId(String jobPostingId){
        this.jobPostingId = jobPostingId;
    }

    @ElVoField(physicalName = "accountIds")
    public String getAccountIds(){
        String ret = this.accountIds;
        return ret;
    }

    @ElVoField(physicalName = "accountIds")
    public void setAccountIds(String accountIds){
        this.accountIds = accountIds;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ScoutUserVo [");
        sb.append("jobPostingId").append("=").append(jobPostingId).append(",");
        sb.append("accountIds").append("=").append(accountIds);
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
