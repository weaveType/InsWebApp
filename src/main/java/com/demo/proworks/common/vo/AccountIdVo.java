package com.demo.proworks.common.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "유저 ID")
public class AccountIdVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public AccountIdVo(){
    }

    @ElDtoField(logicalName = "유저 ID", physicalName = "accountId", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int accountId;

    @ElVoField(physicalName = "accountId")
    public int getAccountId(){
        return accountId;
    }

    @ElVoField(physicalName = "accountId")
    public void setAccountId(int accountId){
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AccountIdVo [");
        sb.append("accountId").append("=").append(accountId);
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
