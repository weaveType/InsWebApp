package com.demo.proworks.domain.corporate.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "회사정보")
public class CorporateListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "회사정보List", physicalName = "corporateVoList", type = "com.demo.proworks.domain.corporate.CorporateVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.domain.corporate.vo.CorporateVo> corporateVoList;

    public java.util.List<com.demo.proworks.domain.corporate.vo.CorporateVo> getCorporateVoList(){
        return corporateVoList;
    }

    public void setCorporateVoList(java.util.List<com.demo.proworks.domain.corporate.vo.CorporateVo> corporateVoList){
        this.corporateVoList = corporateVoList;
    }

    @Override
    public String toString() {
        return "CorporateListVo [corporateVoList=" + corporateVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}
