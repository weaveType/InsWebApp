package com.demo.proworks.domain.survey.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "MBTI 계산 결과")
public class MbtiCalculationResultVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public MbtiCalculationResultVo(){
    }

    @ElDtoField(logicalName = "타입 ID", physicalName = "typeId", type = "Long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "사용자 MBTI 타입 ID", attr = "")
    private Long typeId;

    @ElDtoField(logicalName = "타입 코드", physicalName = "typeCode", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "4자리 MBTI 타입 (예: BRSD)", attr = "")
    private String typeCode;

    @ElDtoField(logicalName = "A/B 점수", physicalName = "aBScore", type = "double", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "Architect(100) vs Builder(0)", attr = "")
    private double aBScore;

    @ElDtoField(logicalName = "R/I 점수", physicalName = "rIScore", type = "double", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "Refactor(100) vs Innovate(0)", attr = "")
    private double rIScore;

    @ElDtoField(logicalName = "S/T 점수", physicalName = "sTScore", type = "double", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "Solo(100) vs Team(0)", attr = "")
    private double sTScore;

    @ElDtoField(logicalName = "D/F 점수", physicalName = "dFScore", type = "double", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "Debug(100) vs Feature(0)", attr = "")
    private double dFScore;

    @ElDtoField(logicalName = "타입 이름", physicalName = "typeName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "타입 한글 이름 (예: 실용적 안정성 추구자)", attr = "")
    private String typeName;

    @ElDtoField(logicalName = "타입 설명", physicalName = "typeDescription", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "타입 상세 설명", attr = "")
    private String typeDescription;

    @ElVoField(physicalName = "typeId")
    public Long getTypeId(){
        return typeId;
    }

    @ElVoField(physicalName = "typeId")
    public void setTypeId(Long typeId){
        this.typeId = typeId;
    }

    @ElVoField(physicalName = "typeCode")
    public String getTypeCode(){
        String ret = this.typeCode;
        return ret;
    }

    @ElVoField(physicalName = "typeCode")
    public void setTypeCode(String typeCode){
        this.typeCode = typeCode;
    }

    @ElVoField(physicalName = "aBScore")
    public double getABScore(){
        return aBScore;
    }

    @ElVoField(physicalName = "aBScore")
    public void setABScore(double aBScore){
        this.aBScore = aBScore;
    }

    @ElVoField(physicalName = "rIScore")
    public double getRIScore(){
        return rIScore;
    }

    @ElVoField(physicalName = "rIScore")
    public void setRIScore(double rIScore){
        this.rIScore = rIScore;
    }

    @ElVoField(physicalName = "sTScore")
    public double getSTScore(){
        return sTScore;
    }

    @ElVoField(physicalName = "sTScore")
    public void setSTScore(double sTScore){
        this.sTScore = sTScore;
    }

    @ElVoField(physicalName = "dFScore")
    public double getDFScore(){
        return dFScore;
    }

    @ElVoField(physicalName = "dFScore")
    public void setDFScore(double dFScore){
        this.dFScore = dFScore;
    }

    @ElVoField(physicalName = "typeName")
    public String getTypeName(){
        String ret = this.typeName;
        return ret;
    }

    @ElVoField(physicalName = "typeName")
    public void setTypeName(String typeName){
        this.typeName = typeName;
    }

    @ElVoField(physicalName = "typeDescription")
    public String getTypeDescription(){
        String ret = this.typeDescription;
        return ret;
    }

    @ElVoField(physicalName = "typeDescription")
    public void setTypeDescription(String typeDescription){
        this.typeDescription = typeDescription;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MbtiCalculationResultVo [");
        sb.append("typeId").append("=").append(typeId).append(",");
        sb.append("typeCode").append("=").append(typeCode).append(",");
        sb.append("aBScore").append("=").append(aBScore).append(",");
        sb.append("rIScore").append("=").append(rIScore).append(",");
        sb.append("sTScore").append("=").append(sTScore).append(",");
        sb.append("dFScore").append("=").append(dFScore).append(",");
        sb.append("typeName").append("=").append(typeName).append(",");
        sb.append("typeDescription").append("=").append(typeDescription);
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
