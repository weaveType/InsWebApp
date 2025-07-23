package com.demo.proworks.domain.codeanalysis.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Date;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "코드분석결과")
public class CodeAnalysisResultVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public CodeAnalysisResultVo(){
    }

    @ElDtoField(logicalName = "분석ID", physicalName = "analysisId", type = "Long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private Long analysisId;

    @ElDtoField(logicalName = "타입ID", physicalName = "typeId", type = "Long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private Long typeId;

    @ElDtoField(logicalName = "사용자ID", physicalName = "userId", type = "Long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private Long userId;

    @ElDtoField(logicalName = "분석결과", physicalName = "analysisResult", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String analysisResult;

    @ElDtoField(logicalName = "타입코드", physicalName = "typeCode", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String typeCode;

    @ElDtoField(logicalName = "개발스타일점수", physicalName = "developmentStyleScore", type = "Integer", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private Integer developmentStyleScore;

    @ElDtoField(logicalName = "개발자선호도점수", physicalName = "developerPreferenceScore", type = "Integer", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private Integer developerPreferenceScore;

    @ElDtoField(logicalName = "신뢰도", physicalName = "confidenceScore", type = "Double", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private Double confidenceScore;

    @ElDtoField(logicalName = "생성시간", physicalName = "createdAt", type = "Date", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private Date createdAt;

    @ElDtoField(logicalName = "언어", physicalName = "language", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String language;

    @ElDtoField(logicalName = "코멘트", physicalName = "comment", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String comment;

    @ElVoField(physicalName = "analysisId")
    public Long getAnalysisId(){
        return this.analysisId;
    }

    @ElVoField(physicalName = "analysisId")
    public void setAnalysisId(Long analysisId){
        this.analysisId = analysisId;
    }

    @ElVoField(physicalName = "typeId")
    public Long getTypeId(){
        return this.typeId;
    }

    @ElVoField(physicalName = "typeId")
    public void setTypeId(Long typeId){
        this.typeId = typeId;
    }

    @ElVoField(physicalName = "userId")
    public Long getUserId(){
        return this.userId;
    }

    @ElVoField(physicalName = "userId")
    public void setUserId(Long userId){
        this.userId = userId;
    }

    @ElVoField(physicalName = "analysisResult")
    public String getAnalysisResult(){
        return this.analysisResult;
    }

    @ElVoField(physicalName = "analysisResult")
    public void setAnalysisResult(String analysisResult){
        this.analysisResult = analysisResult;
    }

    @ElVoField(physicalName = "typeCode")
    public String getTypeCode(){
        return this.typeCode;
    }

    @ElVoField(physicalName = "typeCode")
    public void setTypeCode(String typeCode){
        this.typeCode = typeCode;
    }

    @ElVoField(physicalName = "developmentStyleScore")
    public Integer getDevelopmentStyleScore(){
        return this.developmentStyleScore;
    }

    @ElVoField(physicalName = "developmentStyleScore")
    public void setDevelopmentStyleScore(Integer developmentStyleScore){
        this.developmentStyleScore = developmentStyleScore;
    }

    @ElVoField(physicalName = "developerPreferenceScore")
    public Integer getDeveloperPreferenceScore(){
        return this.developerPreferenceScore;
    }

    @ElVoField(physicalName = "developerPreferenceScore")
    public void setDeveloperPreferenceScore(Integer developerPreferenceScore){
        this.developerPreferenceScore = developerPreferenceScore;
    }

    @ElVoField(physicalName = "confidenceScore")
    public Double getConfidenceScore(){
        return this.confidenceScore;
    }

    @ElVoField(physicalName = "confidenceScore")
    public void setConfidenceScore(Double confidenceScore){
        this.confidenceScore = confidenceScore;
    }

    @ElVoField(physicalName = "createdAt")
    public Date getCreatedAt(){
        return this.createdAt;
    }

    @ElVoField(physicalName = "createdAt")
    public void setCreatedAt(Date createdAt){
        this.createdAt = createdAt;
    }

    @ElVoField(physicalName = "language")
    public String getLanguage(){
        return this.language;
    }

    @ElVoField(physicalName = "language")
    public void setLanguage(String language){
        this.language = language;
    }

    @ElVoField(physicalName = "comment")
    public String getComment(){
        return this.comment;
    }

    @ElVoField(physicalName = "comment")
    public void setComment(String comment){
        this.comment = comment;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CodeAnalysisResultVo [");
        sb.append("analysisId").append("=").append(analysisId).append(",");
        sb.append("typeId").append("=").append(typeId).append(",");
        sb.append("userId").append("=").append(userId).append(",");
        sb.append("analysisResult").append("=").append(analysisResult).append(",");
        sb.append("typeCode").append("=").append(typeCode).append(",");
        sb.append("developmentStyleScore").append("=").append(developmentStyleScore).append(",");
        sb.append("developerPreferenceScore").append("=").append(developerPreferenceScore).append(",");
        sb.append("confidenceScore").append("=").append(confidenceScore).append(",");
        sb.append("createdAt").append("=").append(createdAt).append(",");
        sb.append("language").append("=").append(language).append(",");
        sb.append("comment").append("=").append(comment);
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