package com.demo.proworks.domain.post.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "공고정보")
public class MainPostingListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public MainPostingListVo(){
    }

    @ElDtoField(logicalName = "공고정보", physicalName = "mainPostingVo", type = "", typeKind = "List", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private java.util.List<com.demo.proworks.domain.post.vo.MainPostingVo> mainPostingVo;

    @ElVoField(physicalName = "mainPostingVo")
    public java.util.List<com.demo.proworks.domain.post.vo.MainPostingVo> getMainPostingVo(){
        return mainPostingVo;
    }

    @ElVoField(physicalName = "mainPostingVo")
    public void setMainPostingVo(java.util.List<com.demo.proworks.domain.post.vo.MainPostingVo> mainPostingVo){
        this.mainPostingVo = mainPostingVo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("mainPostingListVo [");
        sb.append("mainPostingVo").append("=").append(mainPostingVo);
        sb.append("]");
        return sb.toString();

    }

    public boolean isFixedLengthVo() {
        return false;
    }

    @Override
    public void _xStreamEnc() {
        for( int i=0 ; mainPostingVo != null && i < mainPostingVo.size() ; i++ ) {
            com.demo.proworks.domain.post.vo.MainPostingVo vo = (com.demo.proworks.domain.post.vo.MainPostingVo)mainPostingVo.get(i);
            vo._xStreamEnc();	 
        }
    }


    @Override
    public void _xStreamDec() {
        for( int i=0 ; mainPostingVo != null && i < mainPostingVo.size() ; i++ ) {
            com.demo.proworks.domain.post.vo.MainPostingVo vo = (com.demo.proworks.domain.post.vo.MainPostingVo)mainPostingVo.get(i);
            vo._xStreamDec();	 
        }
    }


}
