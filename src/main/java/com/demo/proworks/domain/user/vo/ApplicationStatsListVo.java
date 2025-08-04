package com.demo.proworks.domain.user.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "이력서 카운트 리스트")
public class ApplicationStatsListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public ApplicationStatsListVo(){
    }

    @ElDtoField(logicalName = "이력서 카운트", physicalName = "applicationStatusVo", type = "", typeKind = "List", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private java.util.List<com.demo.proworks.domain.user.vo.ApplicationStatusVo> applicationStatusVo;

    @ElVoField(physicalName = "applicationStatusVo")
    public java.util.List<com.demo.proworks.domain.user.vo.ApplicationStatusVo> getApplicationStatusVo(){
        return applicationStatusVo;
    }

    @ElVoField(physicalName = "applicationStatusVo")
    public void setApplicationStatusVo(java.util.List<com.demo.proworks.domain.user.vo.ApplicationStatusVo> applicationStatusVo){
        this.applicationStatusVo = applicationStatusVo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ApplicationStatsListVo [");
        sb.append("applicationStatusVo").append("=").append(applicationStatusVo);
        sb.append("]");
        return sb.toString();

    }

    public boolean isFixedLengthVo() {
        return false;
    }

    @Override
    public void _xStreamEnc() {
        for( int i=0 ; applicationStatusVo != null && i < applicationStatusVo.size() ; i++ ) {
            com.demo.proworks.domain.user.vo.ApplicationStatusVo vo = (com.demo.proworks.domain.user.vo.ApplicationStatusVo)applicationStatusVo.get(i);
            vo._xStreamEnc();	 
        }
    }


    @Override
    public void _xStreamDec() {
        for( int i=0 ; applicationStatusVo != null && i < applicationStatusVo.size() ; i++ ) {
            com.demo.proworks.domain.user.vo.ApplicationStatusVo vo = (com.demo.proworks.domain.user.vo.ApplicationStatusVo)applicationStatusVo.get(i);
            vo._xStreamDec();	 
        }
    }


}
