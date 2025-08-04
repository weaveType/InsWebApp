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
    
    /** 사용자 ID */
    @ElDtoField(logicalName = "사용자 ID", physicalName = "userId", type = "Long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "사용자 고유 식별자", attr = "")
    private Long userId;
    
    /** 응답 데이터 */
    @ElDtoField(logicalName = "응답 데이터", physicalName = "responses", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "JSON 형식의 응답 데이터", attr = "")
    private String responses;
    
    /** 생성일시 */
    @ElDtoField(logicalName = "생성일시", physicalName = "createdAt", type = "Timestamp", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "응답 생성 일시", attr = "")
    private Timestamp createdAt;
    
    /** 수정일시 */
    @ElDtoField(logicalName = "수정일시", physicalName = "updatedAt", type = "Timestamp", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "응답 수정 일시", attr = "")
    private Timestamp updatedAt;

    @ElVoField(physicalName = "responseId")
    public Long getResponseId() {
        return responseId;
    }

    @ElVoField(physicalName = "responseId")
    public void setResponseId(Long responseId) {
        this.responseId = responseId;
    }

    @ElVoField(physicalName = "userId")
    public Long getUserId() {
        return userId;
    }

    @ElVoField(physicalName = "userId")
    public void setUserId(Long userId) {
        this.userId = userId;
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

    @ElVoField(physicalName = "createdAt")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @ElVoField(physicalName = "createdAt")
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @ElVoField(physicalName = "updatedAt")
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    @ElVoField(physicalName = "updatedAt")
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SurveyResponseVo [");
        sb.append("responseId").append("=").append(responseId).append(",");
        sb.append("userId").append("=").append(userId).append(",");
        sb.append("responses").append("=").append(responses).append(",");
        sb.append("createdAt").append("=").append(createdAt).append(",");
        sb.append("updatedAt").append("=").append(updatedAt);
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
