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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MatchingCheckedVo [");
        sb.append("accountId").append("=").append(accountId).append(",");
        sb.append("isMbtiChecked").append("=").append(isMbtiChecked).append(",");
        sb.append("isCodeChecked").append("=").append(isCodeChecked);
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
