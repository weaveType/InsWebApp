package com.demo.proworks.domain.user.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "일반회원")
public class MatchingCheckedVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public MatchingCheckedVo(){
    }

    @ElDtoField(logicalName = "사용자 계정 ID", physicalName = "accountId", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int accountId;

    @ElDtoField(logicalName = "성향분석 체크여부", physicalName = "isMbtiChecked", type = "boolean", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private boolean isMbtiChecked;

    @ElDtoField(logicalName = "코드분석 진행여부", physicalName = "isCodeChecked", type = "boolean", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private boolean isCodeChecked;

    @ElDtoField(logicalName = "매칭제안수", physicalName = "matchingProposalCount", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int matchingProposalCount;

    @ElDtoField(logicalName = "지원현황수", physicalName = "applicationStatusCount", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int applicationStatusCount;

    @ElVoField(physicalName = "accountId")
    public int getAccountId(){
        return accountId;
    }

    @ElVoField(physicalName = "accountId")
    public void setAccountId(int accountId){
        this.accountId = accountId;
    }

    @ElVoField(physicalName = "isMbtiChecked")
    public boolean isIsMbtiChecked(){
        return isMbtiChecked;
    }

    @ElVoField(physicalName = "isMbtiChecked")
    public void setIsMbtiChecked(boolean isMbtiChecked){
        this.isMbtiChecked = isMbtiChecked;
    }

    @ElVoField(physicalName = "isCodeChecked")
    public boolean isIsCodeChecked(){
        return isCodeChecked;
    }

    @ElVoField(physicalName = "isCodeChecked")
    public void setIsCodeChecked(boolean isCodeChecked){
        this.isCodeChecked = isCodeChecked;
    }

    @ElVoField(physicalName = "matchingProposalCount")
    public int getMatchingProposalCount(){
        return matchingProposalCount;
    }

    @ElVoField(physicalName = "matchingProposalCount")
    public void setMatchingProposalCount(int matchingProposalCount){
        this.matchingProposalCount = matchingProposalCount;
    }

    @ElVoField(physicalName = "applicationStatusCount")
    public int getApplicationStatusCount(){
        return applicationStatusCount;
    }

    @ElVoField(physicalName = "applicationStatusCount")
    public void setApplicationStatusCount(int applicationStatusCount){
        this.applicationStatusCount = applicationStatusCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MatchingCheckedVo [");
        sb.append("accountId").append("=").append(accountId).append(",");
        sb.append("isMbtiChecked").append("=").append(isMbtiChecked).append(",");
        sb.append("isCodeChecked").append("=").append(isCodeChecked).append(",");
        sb.append("matchingProposalCount").append("=").append(matchingProposalCount).append(",");
        sb.append("applicationStatusCount").append("=").append(applicationStatusCount);
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
