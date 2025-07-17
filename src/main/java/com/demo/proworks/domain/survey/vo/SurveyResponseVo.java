package com.demo.proworks.domain.survey.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.sql.Timestamp;

/**
 * 설문 응답 VO
 * 
 * @author ProWorks
 * @since 2025/07/15
 */
@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "설문 응답")
public class SurveyResponseVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;
    
    public SurveyResponseVo() {
    }
    
    /** 응답 ID */
    @ElDtoField(logicalName = "응답 ID", physicalName = "responseId", type = "Long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "응답 고유 식별자", attr = "")
    private Long responseId;
    
    /** 타입 ID */
    @ElDtoField(logicalName = "타입 ID", physicalName = "typeId", type = "Long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "사용자 MBTI 타입 ID", attr = "")
    private Long typeId;
    
    /** 응답 데이터 */
    @ElDtoField(logicalName = "응답 데이터", physicalName = "responses", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "JSON 형식의 응답 데이터", attr = "")
    private String responses;
    
    /** 생성일시 */
    @ElDtoField(logicalName = "생성일시", physicalName = "createAt", type = "Timestamp", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "응답 생성 일시", attr = "")
    private Timestamp createAt;
    
    /** 수정일시 */
    @ElDtoField(logicalName = "수정일시", physicalName = "updateAt", type = "Timestamp", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "응답 수정 일시", attr = "")
    private Timestamp updateAt;

    @ElVoField(physicalName = "responseId")
    public Long getResponseId() {
        return responseId;
    }

    @ElVoField(physicalName = "responseId")
    public void setResponseId(Long responseId) {
        this.responseId = responseId;
    }

    @ElVoField(physicalName = "typeId")
    public Long getTypeId() {
        return typeId;
    }

    @ElVoField(physicalName = "typeId")
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    @ElVoField(physicalName = "responses")
    public String getResponses() {
        String ret = this.responses;
        return ret;
    }

    @ElVoField(physicalName = "responses")
    public void setResponses(String responses) {
        this.responses = responses;
    }

    @ElVoField(physicalName = "createAt")
    public Timestamp getCreateAt() {
        return createAt;
    }

    @ElVoField(physicalName = "createAt")
    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    @ElVoField(physicalName = "updateAt")
    public Timestamp getUpdateAt() {
        return updateAt;
    }

    @ElVoField(physicalName = "updateAt")
    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SurveyResponseVo [");
        sb.append("responseId").append("=").append(responseId).append(",");
        sb.append("typeId").append("=").append(typeId).append(",");
        sb.append("responses").append("=").append(responses).append(",");
        sb.append("createAt").append("=").append(createAt).append(",");
        sb.append("updateAt").append("=").append(updateAt);
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
