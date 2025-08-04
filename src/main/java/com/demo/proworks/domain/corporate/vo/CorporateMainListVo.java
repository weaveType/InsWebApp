package com.demo.proworks.domain.corporate.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "회사정보")
public class CorporateMainListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public CorporateMainListVo(){
    }

    @ElDtoField(logicalName = "회사정보", physicalName = "corporateMainVo", type = "", typeKind = "List", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private java.util.List<com.demo.proworks.domain.corporate.vo.CorporateMainVo> corporateMainVo;

    @ElDtoField(logicalName = "등록된 회사 수", physicalName = "totalCount", type = "long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private long totalCount;

    @ElVoField(physicalName = "corporateMainVo")
    public java.util.List<com.demo.proworks.domain.corporate.vo.CorporateMainVo> getCorporateMainVo(){
        return corporateMainVo;
    }

    @ElVoField(physicalName = "corporateMainVo")
    public void setCorporateMainVo(java.util.List<com.demo.proworks.domain.corporate.vo.CorporateMainVo> corporateMainVo){
        this.corporateMainVo = corporateMainVo;
    }

    @ElVoField(physicalName = "totalCount")
    public long getTotalCount(){
        return totalCount;
    }

    @ElVoField(physicalName = "totalCount")
    public void setTotalCount(long totalCount){
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CorporateMainListVo [");
        sb.append("corporateMainVo").append("=").append(corporateMainVo).append(",");
        sb.append("totalCount").append("=").append(totalCount);
        sb.append("]");
        return sb.toString();

    }

    public boolean isFixedLengthVo() {
        return false;
    }

    @Override
    public void _xStreamEnc() {
        for( int i=0 ; corporateMainVo != null && i < corporateMainVo.size() ; i++ ) {
            com.demo.proworks.domain.corporate.vo.CorporateMainVo vo = (com.demo.proworks.domain.corporate.vo.CorporateMainVo)corporateMainVo.get(i);
            vo._xStreamEnc();	 
        }
    }


    @Override
    public void _xStreamDec() {
        for( int i=0 ; corporateMainVo != null && i < corporateMainVo.size() ; i++ ) {
            com.demo.proworks.domain.corporate.vo.CorporateMainVo vo = (com.demo.proworks.domain.corporate.vo.CorporateMainVo)corporateMainVo.get(i);
            vo._xStreamDec();	 
        }
    }


}
