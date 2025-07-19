package com.demo.proworks.domain.user.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "기술스택")
public class TechStackVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public TechStackVo(){
    }

    @ElDtoField(logicalName = "기술스택 ID", physicalName = "techStackId", type = "long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private long techStackId;

    @ElVoField(physicalName = "techStackId")
    public long getTechStackId(){
        return techStackId;
    }

    @ElVoField(physicalName = "techStackId")
    public void setTechStackId(long techStackId){
        this.techStackId = techStackId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TechStackVo [");
        sb.append("techStackId").append("=").append(techStackId);
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
