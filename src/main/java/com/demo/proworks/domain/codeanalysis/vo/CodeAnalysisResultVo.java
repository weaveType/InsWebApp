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

    @ElDtoField(logicalName = "코드분석ID", physicalName = "codeAnalysisId", type = "Long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private Long codeAnalysisId;

    @ElDtoField(logicalName = "사용자ID", physicalName = "userId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String userId;

    @ElDtoField(logicalName = "프로그램언어", physicalName = "programmingLanguage", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String programmingLanguage;

    @ElDtoField(logicalName = "분석결과", physicalName = "analysisResult", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String analysisResult;

    @ElDtoField(logicalName = "개발자타입코드", physicalName = "typeCode", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String typeCode;

    @ElDtoField(logicalName = "아키텍트점수", physicalName = "architectScore", type = "Integer", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private Integer architectScore;

    @ElDtoField(logicalName = "빌더점수", physicalName = "builderScore", type = "Integer", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private Integer builderScore;

    @ElDtoField(logicalName = "개인작업점수", physicalName = "individualScore", type = "Integer", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private Integer individualScore;

    @ElDtoField(logicalName = "팀작업점수", physicalName = "teamScore", type = "Integer", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private Integer teamScore;

    @ElDtoField(logicalName = "신뢰도", physicalName = "confidenceScore", type = "Double", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private Double confidenceScore;

    @ElDtoField(logicalName = "생성시간", physicalName = "createdAt", type = "Date", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private Date createdAt;

    @ElVoField(physicalName = "codeAnalysisId")
    public Long getCodeAnalysisId(){
        return this.codeAnalysisId;
    }

    @ElVoField(physicalName = "codeAnalysisId")
    public void setCodeAnalysisId(Long codeAnalysisId){
        this.codeAnalysisId = codeAnalysisId;
    }

    @ElVoField(physicalName = "userId")
    public String getUserId(){
        return this.userId;
    }

    @ElVoField(physicalName = "userId")
    public void setUserId(String userId){
        this.userId = userId;
    }

    @ElVoField(physicalName = "programmingLanguage")
    public String getProgrammingLanguage(){
        return this.programmingLanguage;
    }

    @ElVoField(physicalName = "programmingLanguage")
    public void setProgrammingLanguage(String programmingLanguage){
        this.programmingLanguage = programmingLanguage;
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

    @ElVoField(physicalName = "architectScore")
    public Integer getArchitectScore(){
        return this.architectScore;
    }

    @ElVoField(physicalName = "architectScore")
    public void setArchitectScore(Integer architectScore){
        this.architectScore = architectScore;
    }

    @ElVoField(physicalName = "builderScore")
    public Integer getBuilderScore(){
        return this.builderScore;
    }

    @ElVoField(physicalName = "builderScore")
    public void setBuilderScore(Integer builderScore){
        this.builderScore = builderScore;
    }

    @ElVoField(physicalName = "individualScore")
    public Integer getIndividualScore(){
        return this.individualScore;
    }

    @ElVoField(physicalName = "individualScore")
    public void setIndividualScore(Integer individualScore){
        this.individualScore = individualScore;
    }

    @ElVoField(physicalName = "teamScore")
    public Integer getTeamScore(){
        return this.teamScore;
    }

    @ElVoField(physicalName = "teamScore")
    public void setTeamScore(Integer teamScore){
        this.teamScore = teamScore;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CodeAnalysisResultVo [");
        sb.append("codeAnalysisId").append("=").append(codeAnalysisId).append(",");
        sb.append("userId").append("=").append(userId).append(",");
        sb.append("programmingLanguage").append("=").append(programmingLanguage).append(",");
        sb.append("analysisResult").append("=").append(analysisResult).append(",");
        sb.append("typeCode").append("=").append(typeCode).append(",");
        sb.append("architectScore").append("=").append(architectScore).append(",");
        sb.append("builderScore").append("=").append(builderScore).append(",");
        sb.append("individualScore").append("=").append(individualScore).append(",");
        sb.append("teamScore").append("=").append(teamScore).append(",");
        sb.append("confidenceScore").append("=").append(confidenceScore).append(",");
        sb.append("createdAt").append("=").append(createdAt);
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