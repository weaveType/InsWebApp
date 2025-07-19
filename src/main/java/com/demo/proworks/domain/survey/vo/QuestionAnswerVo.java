package com.demo.proworks.domain.survey.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "질문 응답")
public class QuestionAnswerVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public QuestionAnswerVo(){
    }

    @ElDtoField(logicalName = "질문 ID", physicalName = "questionId", type = "Long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "질문 식별자", attr = "")
    private Long questionId;

    @ElDtoField(logicalName = "답변 값", physicalName = "answerValue", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "선택한 답변 (1-5점)", attr = "")
    private int answerValue;

    @ElVoField(physicalName = "questionId")
    public Long getQuestionId(){
        return questionId;
    }

    @ElVoField(physicalName = "questionId")
    public void setQuestionId(Long questionId){
        this.questionId = questionId;
    }

    @ElVoField(physicalName = "answerValue")
    public int getAnswerValue(){
        return answerValue;
    }

    @ElVoField(physicalName = "answerValue")
    public void setAnswerValue(int answerValue){
        this.answerValue = answerValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("QuestionAnswerVo [");
        sb.append("questionId").append("=").append(questionId).append(",");
        sb.append("answerValue").append("=").append(answerValue);
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
