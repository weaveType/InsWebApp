package com.demo.proworks.domain.corporate.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "회사정보")
public class CorporateVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "id", physicalName = "id", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String id;

    @ElDtoField(logicalName = "business_number", physicalName = "businessNumber", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String businessNumber;

    @ElDtoField(logicalName = "industry", physicalName = "industry", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String industry;

    @ElDtoField(logicalName = "emp_count", physicalName = "empCount", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String empCount;

    @ElDtoField(logicalName = "description", physicalName = "description", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String description;

    @ElDtoField(logicalName = "company_name", physicalName = "companyName", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String companyName;

    @ElDtoField(logicalName = "company_logo", physicalName = "companyLogo", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String companyLogo;

    @ElDtoField(logicalName = "company_image", physicalName = "companyImage", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String companyImage;

    @ElDtoField(logicalName = "search_business_number", physicalName = "scBusinessNumber", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String scBusinessNumber;

    @ElDtoField(logicalName = "search_industry", physicalName = "scIndustry", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String scIndustry;

    @ElDtoField(logicalName = "search_emp_count", physicalName = "scEmpCount", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String scEmpCount;

    @ElDtoField(logicalName = "search_description", physicalName = "scDescription", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String scDescription;

    @ElDtoField(logicalName = "search_company_name", physicalName = "scCompanyName", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String scCompanyName;

    @ElDtoField(logicalName = "search_company_logo", physicalName = "scCompanyLogo", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String scCompanyLogo;

    @ElDtoField(logicalName = "search_company_image", physicalName = "scCompanyImage", type = "String", typeKind = "", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private String scCompanyImage;

    @ElVoField(physicalName = "id")
    public String getId(){
        return id;
    }

    @ElVoField(physicalName = "id")
    public void setId(String id){
        this.id = id;
    }

    @ElVoField(physicalName = "businessNumber")
    public String getBusinessNumber(){
        return businessNumber;
    }

    @ElVoField(physicalName = "businessNumber")
    public void setBusinessNumber(String businessNumber){
        this.businessNumber = businessNumber;
    }

    @ElVoField(physicalName = "industry")
    public String getIndustry(){
        return industry;
    }

    @ElVoField(physicalName = "industry")
    public void setIndustry(String industry){
        this.industry = industry;
    }

    @ElVoField(physicalName = "empCount")
    public String getEmpCount(){
        return empCount;
    }

    @ElVoField(physicalName = "empCount")
    public void setEmpCount(String empCount){
        this.empCount = empCount;
    }

    @ElVoField(physicalName = "description")
    public String getDescription(){
        return description;
    }

    @ElVoField(physicalName = "description")
    public void setDescription(String description){
        this.description = description;
    }

    @ElVoField(physicalName = "companyName")
    public String getCompanyName(){
        return companyName;
    }

    @ElVoField(physicalName = "companyName")
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    @ElVoField(physicalName = "companyLogo")
    public String getCompanyLogo(){
        return companyLogo;
    }

    @ElVoField(physicalName = "companyLogo")
    public void setCompanyLogo(String companyLogo){
        this.companyLogo = companyLogo;
    }

    @ElVoField(physicalName = "companyImage")
    public String getCompanyImage(){
        return companyImage;
    }

    @ElVoField(physicalName = "companyImage")
    public void setCompanyImage(String companyImage){
        this.companyImage = companyImage;
    }

    @ElVoField(physicalName = "scBusinessNumber")
    public String getScBusinessNumber(){
        return scBusinessNumber;
    }

    @ElVoField(physicalName = "scBusinessNumber")
    public void setScBusinessNumber(String scBusinessNumber) {
        this.scBusinessNumber = scBusinessNumber;
    }

    @ElVoField(physicalName = "scIndustry")
    public String getScIndustry(){
        return scIndustry;
    }

    @ElVoField(physicalName = "scIndustry")
    public void setScIndustry(String scIndustry) {
        this.scIndustry = scIndustry;
    }

    @ElVoField(physicalName = "scEmpCount")
    public String getScEmpCount(){
        return scEmpCount;
    }

    @ElVoField(physicalName = "scEmpCount")
    public void setScEmpCount(String scEmpCount) {
        this.scEmpCount = scEmpCount;
    }

    @ElVoField(physicalName = "scDescription")
    public String getScDescription(){
        return scDescription;
    }

    @ElVoField(physicalName = "scDescription")
    public void setScDescription(String scDescription) {
        this.scDescription = scDescription;
    }

    @ElVoField(physicalName = "scCompanyName")
    public String getScCompanyName(){
        return scCompanyName;
    }

    @ElVoField(physicalName = "scCompanyName")
    public void setScCompanyName(String scCompanyName) {
        this.scCompanyName = scCompanyName;
    }

    @ElVoField(physicalName = "scCompanyLogo")
    public String getScCompanyLogo(){
        return scCompanyLogo;
    }

    @ElVoField(physicalName = "scCompanyLogo")
    public void setScCompanyLogo(String scCompanyLogo) {
        this.scCompanyLogo = scCompanyLogo;
    }

    @ElVoField(physicalName = "scCompanyImage")
    public String getScCompanyImage(){
        return scCompanyImage;
    }

    @ElVoField(physicalName = "scCompanyImage")
    public void setScCompanyImage(String scCompanyImage) {
        this.scCompanyImage = scCompanyImage;
    }

    @Override
    public String toString() {
        return "CorporateVo [id=" + id + ",businessNumber=" + businessNumber + ",industry=" + industry + ",empCount=" + empCount + ",description=" + description + ",companyName=" + companyName + ",companyLogo=" + companyLogo + ",companyImage=" + companyImage + ",scBusinessNumber=" + scBusinessNumber + ",scIndustry=" + scIndustry + ",scEmpCount=" + scEmpCount + ",scDescription=" + scDescription + ",scCompanyName=" + scCompanyName + ",scCompanyLogo=" + scCompanyLogo + ",scCompanyImage=" + scCompanyImage + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}
