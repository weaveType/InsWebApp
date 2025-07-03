package com.demo.proworks.domain.corporate.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "회사정보")
public class CorporateVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public CorporateVo(){
    }

    @ElDtoField(logicalName = "기업ID", physicalName = "companyId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String companyId;

    @ElDtoField(logicalName = "이메일", physicalName = "email", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String email;

    @ElDtoField(logicalName = "비밀번호", physicalName = "password", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String password;

    @ElDtoField(logicalName = "회사명", physicalName = "name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String name;

    @ElDtoField(logicalName = "사업자등록번호", physicalName = "businessNumber", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String businessNumber;

    @ElDtoField(logicalName = "사업자등록증 파일경로", physicalName = "businessCertificate", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String businessCertificate;

    @ElDtoField(logicalName = "업종", physicalName = "industry", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String industry;

    @ElDtoField(logicalName = "직원 수", physicalName = "empCount", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String empCount;

    @ElDtoField(logicalName = "회사 설명", physicalName = "description", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String description;

    @ElDtoField(logicalName = "로고 파일명", physicalName = "logoFileName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String logoFileName;

    @ElDtoField(logicalName = "권한", physicalName = "roleId", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String roleId;

    @ElVoField(physicalName = "companyId")
    public String getCompanyId(){
        String ret = this.companyId;
        return ret;
    }

    @ElVoField(physicalName = "companyId")
    public void setCompanyId(String companyId){
        this.companyId = companyId;
    }

    @ElVoField(physicalName = "email")
    public String getEmail(){
        String ret = this.email;
        return ret;
    }

    @ElVoField(physicalName = "email")
    public void setEmail(String email){
        this.email = email;
    }

    @ElVoField(physicalName = "password")
    public String getPassword(){
        String ret = this.password;
        return ret;
    }

    @ElVoField(physicalName = "password")
    public void setPassword(String password){
        this.password = password;
    }

    @ElVoField(physicalName = "name")
    public String getName(){
        String ret = this.name;
        return ret;
    }

    @ElVoField(physicalName = "name")
    public void setName(String name){
        this.name = name;
    }

    @ElVoField(physicalName = "businessNumber")
    public String getBusinessNumber(){
        String ret = this.businessNumber;
        return ret;
    }

    @ElVoField(physicalName = "businessNumber")
    public void setBusinessNumber(String businessNumber){
        this.businessNumber = businessNumber;
    }

    @ElVoField(physicalName = "businessCertificate")
    public String getBusinessCertificate(){
        String ret = this.businessCertificate;
        return ret;
    }

    @ElVoField(physicalName = "businessCertificate")
    public void setBusinessCertificate(String businessCertificate){
        this.businessCertificate = businessCertificate;
    }

    @ElVoField(physicalName = "industry")
    public String getIndustry(){
        String ret = this.industry;
        return ret;
    }

    @ElVoField(physicalName = "industry")
    public void setIndustry(String industry){
        this.industry = industry;
    }

    @ElVoField(physicalName = "empCount")
    public String getEmpCount(){
        String ret = this.empCount;
        return ret;
    }

    @ElVoField(physicalName = "empCount")
    public void setEmpCount(String empCount){
        this.empCount = empCount;
    }

    @ElVoField(physicalName = "description")
    public String getDescription(){
        String ret = this.description;
        return ret;
    }

    @ElVoField(physicalName = "description")
    public void setDescription(String description){
        this.description = description;
    }

    @ElVoField(physicalName = "logoFileName")
    public String getLogoFileName(){
        String ret = this.logoFileName;
        return ret;
    }

    @ElVoField(physicalName = "logoFileName")
    public void setLogoFileName(String logoFileName){
        this.logoFileName = logoFileName;
    }

    @ElVoField(physicalName = "roleId")
    public String getRoleId(){
        String ret = this.roleId;
        return ret;
    }

    @ElVoField(physicalName = "roleId")
    public void setRoleId(String roleId){
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CorporateVo [");
        sb.append("companyId").append("=").append(companyId).append(",");
        sb.append("email").append("=").append(email).append(",");
        sb.append("password").append("=").append(password).append(",");
        sb.append("name").append("=").append(name).append(",");
        sb.append("businessNumber").append("=").append(businessNumber).append(",");
        sb.append("businessCertificate").append("=").append(businessCertificate).append(",");
        sb.append("industry").append("=").append(industry).append(",");
        sb.append("empCount").append("=").append(empCount).append(",");
        sb.append("description").append("=").append(description).append(",");
        sb.append("logoFileName").append("=").append(logoFileName).append(",");
        sb.append("roleId").append("=").append(roleId);
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
