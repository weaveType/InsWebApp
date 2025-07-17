package com.demo.proworks.domain.user.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "일반회원")
public class ScoutListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public ScoutListVo(){
    }

    @ElDtoField(logicalName = "일반회원", physicalName = "scoutDetailVo", type = "", typeKind = "List", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private java.util.List<com.demo.proworks.domain.user.vo.ScoutDetailVo> scoutDetailVo;

    @ElVoField(physicalName = "scoutDetailVo")
    public java.util.List<com.demo.proworks.domain.user.vo.ScoutDetailVo> getScoutDetailVo(){
        return scoutDetailVo;
    }

    @ElVoField(physicalName = "scoutDetailVo")
    public void setScoutDetailVo(java.util.List<com.demo.proworks.domain.user.vo.ScoutDetailVo> scoutDetailVo){
        this.scoutDetailVo = scoutDetailVo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ScoutListVo [");
        sb.append("scoutDetailVo").append("=").append(scoutDetailVo);
        sb.append("]");
        return sb.toString();

    }

    public boolean isFixedLengthVo() {
        return false;
    }

    @Override
    public void _xStreamEnc() {
        for( int i=0 ; scoutDetailVo != null && i < scoutDetailVo.size() ; i++ ) {
            com.demo.proworks.domain.user.vo.ScoutDetailVo vo = (com.demo.proworks.domain.user.vo.ScoutDetailVo)scoutDetailVo.get(i);
            vo._xStreamEnc();	 
        }
    }


    @Override
    public void _xStreamDec() {
        for( int i=0 ; scoutDetailVo != null && i < scoutDetailVo.size() ; i++ ) {
            com.demo.proworks.domain.user.vo.ScoutDetailVo vo = (com.demo.proworks.domain.user.vo.ScoutDetailVo)scoutDetailVo.get(i);
            vo._xStreamDec();	 
        }
    }


}
