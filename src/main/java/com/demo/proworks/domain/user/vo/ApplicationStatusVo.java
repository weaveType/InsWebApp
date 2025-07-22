package com.demo.proworks.domain.user.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "이력서 카운트")
public class ApplicationStatusVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public ApplicationStatusVo(){
    }

    @ElDtoField(logicalName = "상태타입", physicalName = "statusType", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String statusType;

    @ElDtoField(logicalName = "상태개수", physicalName = "statusCount", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int statusCount;

    @ElVoField(physicalName = "statusType")
    public String getStatusType(){
        String ret = this.statusType;
        return ret;
    }

    @ElVoField(physicalName = "statusType")
    public void setStatusType(String statusType){
        this.statusType = statusType;
    }

    @ElVoField(physicalName = "statusCount")
    public int getStatusCount(){
        return statusCount;
    }

    @ElVoField(physicalName = "statusCount")
    public void setStatusCount(int statusCount){
        this.statusCount = statusCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ApplicationStatusVo [");
        sb.append("statusType").append("=").append(statusType).append(",");
        sb.append("statusCount").append("=").append(statusCount);
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
