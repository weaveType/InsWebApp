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

    @ElDtoField(logicalName = "회사정보", physicalName = "corporateSearchVo", type = "", typeKind = "List", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private java.util.List<com.demo.proworks.domain.corporate.vo.CorporateSearchVo> corporateSearchVo;

    @ElDtoField(logicalName = "등록된 회사 수", physicalName = "totalCount", type = "long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private long totalCount;

    @ElVoField(physicalName = "corporateSearchVo")
    public java.util.List<com.demo.proworks.domain.corporate.vo.CorporateSearchVo> getCorporateSearchVo(){
        return corporateSearchVo;
    }

    @ElVoField(physicalName = "corporateSearchVo")
    public void setCorporateSearchVo(java.util.List<com.demo.proworks.domain.corporate.vo.CorporateSearchVo> corporateSearchVo){
        this.corporateSearchVo = corporateSearchVo;
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
        sb.append("corporateSearchVo").append("=").append(corporateSearchVo).append(",");
        sb.append("totalCount").append("=").append(totalCount);
        sb.append("]");
        return sb.toString();

    }

    public boolean isFixedLengthVo() {
        return false;
    }

    @Override
    public void _xStreamEnc() {
        for( int i=0 ; corporateSearchVo != null && i < corporateSearchVo.size() ; i++ ) {
            com.demo.proworks.domain.corporate.vo.CorporateSearchVo vo = (com.demo.proworks.domain.corporate.vo.CorporateSearchVo)corporateSearchVo.get(i);
            vo._xStreamEnc();	 
        }
    }


    @Override
    public void _xStreamDec() {
        for( int i=0 ; corporateSearchVo != null && i < corporateSearchVo.size() ; i++ ) {
            com.demo.proworks.domain.corporate.vo.CorporateSearchVo vo = (com.demo.proworks.domain.corporate.vo.CorporateSearchVo)corporateSearchVo.get(i);
            vo._xStreamDec();	 
        }
    }


}
