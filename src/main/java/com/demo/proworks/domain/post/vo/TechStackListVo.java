package com.demo.proworks.domain.post.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "기술스택정보")
public class TechStackListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "기술스택정보List", physicalName = "techStackVoList", type = "com.demo.proworks.domain.post.vo.TechStackVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.domain.post.vo.TechStackVo> techStackVoList;

    public java.util.List<com.demo.proworks.domain.post.vo.TechStackVo> getTechStackVoList(){
        return techStackVoList;
    }

    public void setTechStackVoList(java.util.List<com.demo.proworks.domain.post.vo.TechStackVo> techStackVoList){
        this.techStackVoList = techStackVoList;
    }

    @Override
    public String toString() {
        return "TechStackListVo [techStackVoList=" + techStackVoList+ "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }
}
