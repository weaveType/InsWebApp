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

    @ElDtoField(logicalName = "id", physicalName = "id", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String id;

    @ElDtoField(logicalName = "business_number", physicalName = "businessNumber", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String businessNumber;

    @ElDtoField(logicalName = "industry", physicalName = "industry", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String industry;

    @ElDtoField(logicalName = "emp_count", physicalName = "empCount", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String empCount;

    @ElDtoField(logicalName = "description", physicalName = "description", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String description;

    @ElDtoField(logicalName = "company_name", physicalName = "companyName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String companyName;

    @ElDtoField(logicalName = "company_logo", physicalName = "companyLogo", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String companyLogo;

    @ElDtoField(logicalName = "company_image", physicalName = "companyImage", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String companyImage;

    @ElDtoField(logicalName = "email", physicalName = "email", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String email;

    @ElDtoField(logicalName = "password", physicalName = "password", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String password;

    @ElDtoField(logicalName = "business_certificate", physicalName = "businessCertificate", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String businessCertificate;

    @ElDtoField(logicalName = "logo_file_name", physicalName = "logoFileName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String logoFileName;

    @ElDtoField(logicalName = "search_business_number", physicalName = "scBusinessNumber", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String scBusinessNumber;

    @ElDtoField(logicalName = "search_industry", physicalName = "scIndustry", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String scIndustry;

    @ElDtoField(logicalName = "search_emp_count", physicalName = "scEmpCount", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String scEmpCount;

    @ElDtoField(logicalName = "search_description", physicalName = "scDescription", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String scDescription;

    @ElDtoField(logicalName = "search_company_name", physicalName = "scCompanyName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String scCompanyName;

    @ElDtoField(logicalName = "search_company_logo", physicalName = "scCompanyLogo", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String scCompanyLogo;

    @ElDtoField(logicalName = "search_company_image", physicalName = "scCompanyImage", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String scCompanyImage;

    @ElVoField(physicalName = "id")
    public String getId(){
        String ret = this.id;
        return ret;
    }

    @ElVoField(physicalName = "id")
    public void setId(String id){
        this.id = id;
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

    @ElVoField(physicalName = "companyName")
    public String getCompanyName(){
        String ret = this.companyName;
        return ret;
    }

    @ElVoField(physicalName = "companyName")
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    @ElVoField(physicalName = "companyLogo")
    public String getCompanyLogo(){
        String ret = this.companyLogo;
        return ret;
    }

    @ElVoField(physicalName = "companyLogo")
    public void setCompanyLogo(String companyLogo){
        this.companyLogo = companyLogo;
    }

    @ElVoField(physicalName = "companyImage")
    public String getCompanyImage(){
        String ret = this.companyImage;
        return ret;
    }

    @ElVoField(physicalName = "companyImage")
    public void setCompanyImage(String companyImage){
        this.companyImage = companyImage;
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

    @ElVoField(physicalName = "businessCertificate")
    public String getBusinessCertificate(){
        String ret = this.businessCertificate;
        return ret;
    }

    @ElVoField(physicalName = "businessCertificate")
    public void setBusinessCertificate(String businessCertificate){
        this.businessCertificate = businessCertificate;
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

    @ElVoField(physicalName = "scBusinessNumber")
    public String getScBusinessNumber(){
        String ret = this.scBusinessNumber;
        return ret;
    }

    @ElVoField(physicalName = "scBusinessNumber")
    public void setScBusinessNumber(String scBusinessNumber){
        this.scBusinessNumber = scBusinessNumber;
    }

    @ElVoField(physicalName = "scIndustry")
    public String getScIndustry(){
        String ret = this.scIndustry;
        return ret;
    }

    @ElVoField(physicalName = "scIndustry")
    public void setScIndustry(String scIndustry){
        this.scIndustry = scIndustry;
    }

    @ElVoField(physicalName = "scEmpCount")
    public String getScEmpCount(){
        String ret = this.scEmpCount;
        return ret;
    }

    @ElVoField(physicalName = "scEmpCount")
    public void setScEmpCount(String scEmpCount){
        this.scEmpCount = scEmpCount;
    }

    @ElVoField(physicalName = "scDescription")
    public String getScDescription(){
        String ret = this.scDescription;
        return ret;
    }

    @ElVoField(physicalName = "scDescription")
    public void setScDescription(String scDescription){
        this.scDescription = scDescription;
    }

    @ElVoField(physicalName = "scCompanyName")
    public String getScCompanyName(){
        String ret = this.scCompanyName;
        return ret;
    }

    @ElVoField(physicalName = "scCompanyName")
    public void setScCompanyName(String scCompanyName){
        this.scCompanyName = scCompanyName;
    }

    @ElVoField(physicalName = "scCompanyLogo")
    public String getScCompanyLogo(){
        String ret = this.scCompanyLogo;
        return ret;
    }

    @ElVoField(physicalName = "scCompanyLogo")
    public void setScCompanyLogo(String scCompanyLogo){
        this.scCompanyLogo = scCompanyLogo;
    }

    @ElVoField(physicalName = "scCompanyImage")
    public String getScCompanyImage(){
        String ret = this.scCompanyImage;
        return ret;
    }

    @ElVoField(physicalName = "scCompanyImage")
    public void setScCompanyImage(String scCompanyImage){
        this.scCompanyImage = scCompanyImage;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CorporateVo [");
        sb.append("id").append("=").append(id).append(",");
        sb.append("businessNumber").append("=").append(businessNumber).append(",");
        sb.append("industry").append("=").append(industry).append(",");
        sb.append("empCount").append("=").append(empCount).append(",");
        sb.append("description").append("=").append(description).append(",");
        sb.append("companyName").append("=").append(companyName).append(",");
        sb.append("companyLogo").append("=").append(companyLogo).append(",");
        sb.append("companyImage").append("=").append(companyImage).append(",");
        sb.append("email").append("=").append(email).append(",");
        sb.append("password").append("=").append(password).append(",");
        sb.append("businessCertificate").append("=").append(businessCertificate).append(",");
        sb.append("logoFileName").append("=").append(logoFileName).append(",");
        sb.append("scBusinessNumber").append("=").append(scBusinessNumber).append(",");
        sb.append("scIndustry").append("=").append(scIndustry).append(",");
        sb.append("scEmpCount").append("=").append(scEmpCount).append(",");
        sb.append("scDescription").append("=").append(scDescription).append(",");
        sb.append("scCompanyName").append("=").append(scCompanyName).append(",");
        sb.append("scCompanyLogo").append("=").append(scCompanyLogo).append(",");
        sb.append("scCompanyImage").append("=").append(scCompanyImage);
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
