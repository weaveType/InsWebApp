package com.demo.proworks.domain.codeanalysis.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "코드분석요청")
public class CodeAnalysisRequestVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public CodeAnalysisRequestVo(){
    }

    @ElDtoField(logicalName = "타입ID", physicalName = "typeId", type = "Long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private Long typeId;

    @ElDtoField(logicalName = "모델파일", physicalName = "modelFile", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String modelFile;

    @ElDtoField(logicalName = "컨트롤러파일", physicalName = "controllerFile", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String controllerFile;

    @ElDtoField(logicalName = "서비스파일", physicalName = "serviceFile", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String serviceFile;

    @ElDtoField(logicalName = "레포지토리파일", physicalName = "repositoryFile", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String repositoryFile;

    @ElDtoField(logicalName = "모델파일명", physicalName = "modelFileName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String modelFileName;

    @ElDtoField(logicalName = "컨트롤러파일명", physicalName = "controllerFileName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String controllerFileName;

    @ElDtoField(logicalName = "서비스파일명", physicalName = "serviceFileName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String serviceFileName;

    @ElDtoField(logicalName = "레포지토리파일명", physicalName = "repositoryFileName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String repositoryFileName;

    @ElVoField(physicalName = "typeId")
    public Long getTypeId(){
        return this.typeId;
    }

    @ElVoField(physicalName = "typeId")
    public void setTypeId(Long typeId){
        this.typeId = typeId;
    }

    @ElVoField(physicalName = "modelFile")
    public String getModelFile(){
        return this.modelFile;
    }

    @ElVoField(physicalName = "modelFile")
    public void setModelFile(String modelFile){
        this.modelFile = modelFile;
    }

    @ElVoField(physicalName = "controllerFile")
    public String getControllerFile(){
        return this.controllerFile;
    }

    @ElVoField(physicalName = "controllerFile")
    public void setControllerFile(String controllerFile){
        this.controllerFile = controllerFile;
    }

    @ElVoField(physicalName = "serviceFile")
    public String getServiceFile(){
        return this.serviceFile;
    }

    @ElVoField(physicalName = "serviceFile")
    public void setServiceFile(String serviceFile){
        this.serviceFile = serviceFile;
    }

    @ElVoField(physicalName = "repositoryFile")
    public String getRepositoryFile(){
        return this.repositoryFile;
    }

    @ElVoField(physicalName = "repositoryFile")
    public void setRepositoryFile(String repositoryFile){
        this.repositoryFile = repositoryFile;
    }

    @ElVoField(physicalName = "modelFileName")
    public String getModelFileName(){
        return this.modelFileName;
    }

    @ElVoField(physicalName = "modelFileName")
    public void setModelFileName(String modelFileName){
        this.modelFileName = modelFileName;
    }

    @ElVoField(physicalName = "controllerFileName")
    public String getControllerFileName(){
        return this.controllerFileName;
    }

    @ElVoField(physicalName = "controllerFileName")
    public void setControllerFileName(String controllerFileName){
        this.controllerFileName = controllerFileName;
    }

    @ElVoField(physicalName = "serviceFileName")
    public String getServiceFileName(){
        return this.serviceFileName;
    }

    @ElVoField(physicalName = "serviceFileName")
    public void setServiceFileName(String serviceFileName){
        this.serviceFileName = serviceFileName;
    }

    @ElVoField(physicalName = "repositoryFileName")
    public String getRepositoryFileName(){
        return this.repositoryFileName;
    }

    @ElVoField(physicalName = "repositoryFileName")
    public void setRepositoryFileName(String repositoryFileName){
        this.repositoryFileName = repositoryFileName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CodeAnalysisRequestVo [");
        sb.append("typeId").append("=").append(typeId).append(",");
        sb.append("modelFile").append("=").append(modelFile).append(",");
        sb.append("controllerFile").append("=").append(controllerFile).append(",");
        sb.append("serviceFile").append("=").append(serviceFile).append(",");
        sb.append("repositoryFile").append("=").append(repositoryFile).append(",");
        sb.append("modelFileName").append("=").append(modelFileName).append(",");
        sb.append("controllerFileName").append("=").append(controllerFileName).append(",");
        sb.append("serviceFileName").append("=").append(serviceFileName).append(",");
        sb.append("repositoryFileName").append("=").append(repositoryFileName);
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