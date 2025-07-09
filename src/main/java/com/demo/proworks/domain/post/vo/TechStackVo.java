package com.demo.proworks.domain.post.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "기술스택정보")
public class TechStackVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "tech_stack_id", physicalName = "techStackId", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String techStackId;

    @ElDtoField(logicalName = "tech_stack_name", physicalName = "techStackName", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String techStackName;

    @ElVoField(physicalName = "techStackId")
    public String getTechStackId(){
        return techStackId;
    }

    @ElVoField(physicalName = "techStackId")
    public void setTechStackId(String techStackId){
        this.techStackId = techStackId;
    }

    @ElVoField(physicalName = "techStackName")
    public String getTechStackName(){
        return techStackName;
    }

    @ElVoField(physicalName = "techStackName")
    public void setTechStackName(String techStackName){
        this.techStackName = techStackName;
    }

    @Override
    public String toString() {
        return "TechStackVo [techStackId=" + techStackId + ",techStackName=" + techStackName + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }
}
