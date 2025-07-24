package com.demo.proworks.domain.survey.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "답변 분석")
public class AnswerAnalysisVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public AnswerAnalysisVo() {
    }

    @ElDtoField(logicalName = "질문 ID", physicalName = "questionId", type = "Long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "질문 고유 식별자", attr = "")
    private Long questionId;

    @ElDtoField(logicalName = "질문 내용", physicalName = "questionText", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "설문 질문 텍스트", attr = "")
    private String questionText;

    @ElDtoField(logicalName = "답변 값", physicalName = "answerValue", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "선택한 답변 (1-7점)", attr = "")
    private int answerValue;

    @ElDtoField(logicalName = "축 구분", physicalName = "axis", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "B_A, R_I, S_T, D_F", attr = "")
    private String axis;

    @ElDtoField(logicalName = "기여도", physicalName = "contribution", type = "double", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "해당 축에 미친 영향도", attr = "")
    private double contribution;

    @ElDtoField(logicalName = "분석 근거", physicalName = "reasoning", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "답변 분석 근거", attr = "")
    private String reasoning;

    @ElDtoField(logicalName = "영향도", physicalName = "impact", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "영향도 수준 (높음/보통/낮음)", attr = "")
    private String impact;

    @ElDtoField(logicalName = "축 설명", physicalName = "axisDescription", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "축별 설명", attr = "")
    private String axisDescription;

    @ElVoField(physicalName = "questionId")
    public Long getQuestionId() {
        return questionId;
    }

    @ElVoField(physicalName = "questionId")
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @ElVoField(physicalName = "questionText")
    public String getQuestionText() {
        String ret = this.questionText;
        return ret;
    }

    @ElVoField(physicalName = "questionText")
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    @ElVoField(physicalName = "answerValue")
    public int getAnswerValue() {
        return answerValue;
    }

    @ElVoField(physicalName = "answerValue")
    public void setAnswerValue(int answerValue) {
        this.answerValue = answerValue;
    }

    @ElVoField(physicalName = "axis")
    public String getAxis() {
        String ret = this.axis;
        return ret;
    }

    @ElVoField(physicalName = "axis")
    public void setAxis(String axis) {
        this.axis = axis;
    }

    @ElVoField(physicalName = "contribution")
    public double getContribution() {
        return contribution;
    }

    @ElVoField(physicalName = "contribution")
    public void setContribution(double contribution) {
        this.contribution = contribution;
    }

    @ElVoField(physicalName = "reasoning")
    public String getReasoning() {
        String ret = this.reasoning;
        return ret;
    }

    @ElVoField(physicalName = "reasoning")
    public void setReasoning(String reasoning) {
        this.reasoning = reasoning;
    }

    @ElVoField(physicalName = "impact")
    public String getImpact() {
        String ret = this.impact;
        return ret;
    }

    @ElVoField(physicalName = "impact")
    public void setImpact(String impact) {
        this.impact = impact;
    }

    @ElVoField(physicalName = "axisDescription")
    public String getAxisDescription() {
        String ret = this.axisDescription;
        return ret;
    }

    @ElVoField(physicalName = "axisDescription")
    public void setAxisDescription(String axisDescription) {
        this.axisDescription = axisDescription;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AnswerAnalysisVo [");
        sb.append("questionId").append("=").append(questionId).append(",");
        sb.append("questionText").append("=").append(questionText).append(",");
        sb.append("answerValue").append("=").append(answerValue).append(",");
        sb.append("axis").append("=").append(axis).append(",");
        sb.append("contribution").append("=").append(contribution).append(",");
        sb.append("reasoning").append("=").append(reasoning).append(",");
        sb.append("impact").append("=").append(impact).append(",");
        sb.append("axisDescription").append("=").append(axisDescription);
        sb.append("]");
        return sb.toString();
    }
} 