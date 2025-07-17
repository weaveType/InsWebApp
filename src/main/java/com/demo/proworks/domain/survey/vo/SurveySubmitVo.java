package com.demo.proworks.domain.survey.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.List;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "설문 제출")
public class SurveySubmitVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public SurveySubmitVo(){
    }

    @ElDtoField(logicalName = "사용자 ID", physicalName = "userId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "설문 제출 사용자 ID", attr = "")
    private String userId;

    @ElDtoField(logicalName = "타입 ID", physicalName = "typeId", type = "Long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "사용자 MBTI 타입 ID", attr = "")
    private Long typeId;

    @ElDtoField(logicalName = "설문 응답", physicalName = "answers", type = "com.demo.proworks.domain.survey.vo.QuestionAnswerVo", typeKind = "List", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "질문별 응답 목록", attr = "")
    private List<QuestionAnswerVo> answers;

    @ElVoField(physicalName = "userId")
    public String getUserId(){
        String ret = this.userId;
        return ret;
    }

    @ElVoField(physicalName = "userId")
    public void setUserId(String userId){
        this.userId = userId;
    }

    @ElVoField(physicalName = "typeId")
    public Long getTypeId(){
        return typeId;
    }

    @ElVoField(physicalName = "typeId")
    public void setTypeId(Long typeId){
        this.typeId = typeId;
    }

    @ElVoField(physicalName = "answers")
    public List<QuestionAnswerVo> getAnswers(){
        return answers;
    }

    @ElVoField(physicalName = "answers")
    public void setAnswers(List<QuestionAnswerVo> answers){
        this.answers = answers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SurveySubmitVo [");
        sb.append("userId").append("=").append(userId).append(",");
        sb.append("typeId").append("=").append(typeId).append(",");
        sb.append("answers").append("=").append(answers);
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
