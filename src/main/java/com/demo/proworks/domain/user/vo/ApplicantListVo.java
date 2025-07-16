package com.demo.proworks.domain.user.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "일반회원")
public class ApplicantListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public ApplicantListVo(){
    }

    @ElDtoField(logicalName = "일반회원", physicalName = "applicantDetailVo", type = "", typeKind = "List", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private java.util.List<com.demo.proworks.domain.user.vo.ApplicantDetailVo> applicantDetailVo;

    @ElDtoField(logicalName = "회사명", physicalName = "name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String name;

    @ElVoField(physicalName = "applicantDetailVo")
    public java.util.List<com.demo.proworks.domain.user.vo.ApplicantDetailVo> getApplicantDetailVo(){
        return applicantDetailVo;
    }

    @ElVoField(physicalName = "applicantDetailVo")
    public void setApplicantDetailVo(java.util.List<com.demo.proworks.domain.user.vo.ApplicantDetailVo> applicantDetailVo){
        this.applicantDetailVo = applicantDetailVo;
    }

    @ElVoField(physicalName = "name")
    public String getName(){
        String ret = this.name;
        return ret;
    }

    @ElVoField(physicalName = "name")
    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ApplicantListVo [");
        sb.append("applicantDetailVo").append("=").append(applicantDetailVo).append(",");
        sb.append("name").append("=").append(name);
        sb.append("]");
        return sb.toString();

    }

    public boolean isFixedLengthVo() {
        return false;
    }

    @Override
    public void _xStreamEnc() {
        for( int i=0 ; applicantDetailVo != null && i < applicantDetailVo.size() ; i++ ) {
            com.demo.proworks.domain.user.vo.ApplicantDetailVo vo = (com.demo.proworks.domain.user.vo.ApplicantDetailVo)applicantDetailVo.get(i);
            vo._xStreamEnc();	 
        }
    }


    @Override
    public void _xStreamDec() {
        for( int i=0 ; applicantDetailVo != null && i < applicantDetailVo.size() ; i++ ) {
            com.demo.proworks.domain.user.vo.ApplicantDetailVo vo = (com.demo.proworks.domain.user.vo.ApplicantDetailVo)applicantDetailVo.get(i);
            vo._xStreamDec();	 
        }
    }


}
