package com.demo.proworks.domain.survey.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "설문 질문")
public class SurveyQuestionVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public SurveyQuestionVo(){
    }

    @ElDtoField(logicalName = "질문 ID", physicalName = "questionId", type = "Long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "질문 고유 식별자", attr = "")
    private Long questionId;

    @ElDtoField(logicalName = "응답 ID", physicalName = "responseId", type = "Long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "연관된 응답 식별자", attr = "")
    private Long responseId;

    @ElDtoField(logicalName = "축 구분", physicalName = "axis", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "B_A, R_I, S_T, D_F", attr = "")
    private String axis;

    @ElDtoField(logicalName = "질문 내용", physicalName = "questionText", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "설문 질문 텍스트", attr = "")
    private String questionText;

    @ElDtoField(logicalName = "옵션 개수", physicalName = "options", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "응답 옵션 개수", attr = "")
    private int options;

    @ElDtoField(logicalName = "활성화 여부", physicalName = "isActive", type = "boolean", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "질문 활성화 상태", attr = "")
    private boolean isActive;

    @ElVoField(physicalName = "questionId")
    public Long getQuestionId(){
        return questionId;
    }

    @ElVoField(physicalName = "questionId")
    public void setQuestionId(Long questionId){
        this.questionId = questionId;
    }

    @ElVoField(physicalName = "responseId")
    public Long getResponseId(){
        return responseId;
    }

    @ElVoField(physicalName = "responseId")
    public void setResponseId(Long responseId){
        this.responseId = responseId;
    }

    @ElVoField(physicalName = "axis")
    public String getAxis(){
        String ret = this.axis;
        return ret;
    }

    @ElVoField(physicalName = "axis")
    public void setAxis(String axis){
        this.axis = axis;
    }

    @ElVoField(physicalName = "questionText")
    public String getQuestionText(){
        String ret = this.questionText;
        return ret;
    }

    @ElVoField(physicalName = "questionText")
    public void setQuestionText(String questionText){
        this.questionText = questionText;
    }

    @ElVoField(physicalName = "options")
    public int getOptions(){
        return options;
    }

    @ElVoField(physicalName = "options")
    public void setOptions(int options){
        this.options = options;
    }

    @ElVoField(physicalName = "isActive")
    public boolean isIsActive(){
        return isActive;
    }

    @ElVoField(physicalName = "isActive")
    public void setIsActive(boolean isActive){
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SurveyQuestionVo [");
        sb.append("questionId").append("=").append(questionId).append(",");
        sb.append("responseId").append("=").append(responseId).append(",");
        sb.append("axis").append("=").append(axis).append(",");
        sb.append("questionText").append("=").append(questionText).append(",");
        sb.append("options").append("=").append(options).append(",");
        sb.append("isActive").append("=").append(isActive);
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
