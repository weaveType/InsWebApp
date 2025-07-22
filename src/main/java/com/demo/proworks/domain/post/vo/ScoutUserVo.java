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

    @ElDtoField(logicalName = "공고_ID", physicalName = "jobPostingId", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int jobPostingId;

    @ElDtoField(logicalName = "유저 ID_List", physicalName = "accountIdVo", type = "", typeKind = "List", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private java.util.List<com.demo.proworks.common.vo.AccountIdVo> accountIdVo;

    @ElVoField(physicalName = "jobPostingId")
    public int getJobPostingId(){
        return jobPostingId;
    }

    @ElVoField(physicalName = "jobPostingId")
    public void setJobPostingId(int jobPostingId){
        this.jobPostingId = jobPostingId;
    }

    @ElVoField(physicalName = "accountIdVo")
    public java.util.List<com.demo.proworks.common.vo.AccountIdVo> getAccountIdVo(){
        return accountIdVo;
    }

    @ElVoField(physicalName = "accountIdVo")
    public void setAccountIdVo(java.util.List<com.demo.proworks.common.vo.AccountIdVo> accountIdVo){
        this.accountIdVo = accountIdVo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ScoutUserVo [");
        sb.append("jobPostingId").append("=").append(jobPostingId).append(",");
        sb.append("accountIdVo").append("=").append(accountIdVo);
        sb.append("]");
        return sb.toString();

    }

    public boolean isFixedLengthVo() {
        return false;
    }

    @Override
    public void _xStreamEnc() {
        for( int i=0 ; accountIdVo != null && i < accountIdVo.size() ; i++ ) {
            com.demo.proworks.common.vo.AccountIdVo vo = (com.demo.proworks.common.vo.AccountIdVo)accountIdVo.get(i);
            vo._xStreamEnc();	 
        }
    }


    @Override
    public void _xStreamDec() {
        for( int i=0 ; accountIdVo != null && i < accountIdVo.size() ; i++ ) {
            com.demo.proworks.common.vo.AccountIdVo vo = (com.demo.proworks.common.vo.AccountIdVo)accountIdVo.get(i);
            vo._xStreamDec();	 
        }
    }


}
