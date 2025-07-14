package com.demo.proworks.domain.user.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "일반회원")
public class UserInfoVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public UserInfoVo(){
    }

    @ElDtoField(logicalName = "계정 ID", physicalName = "accountId", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int accountId;

    @ElDtoField(logicalName = "사용자명", physicalName = "name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String name;

    @ElDtoField(logicalName = "경력수준", physicalName = "career", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String career;

    @ElDtoField(logicalName = "지역", physicalName = "preferred_locations", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String preferred_locations;

    @ElDtoField(logicalName = "직무분야", physicalName = "current_position", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String current_position;

    @ElDtoField(logicalName = "희망연봉", physicalName = "year_salary", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int year_salary;

    @ElDtoField(logicalName = "MBTI완료여부", physicalName = "is_mbti_checked", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String is_mbti_checked;

    @ElDtoField(logicalName = "코드분석완료여부", physicalName = "is_code_checked", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String is_code_checked;

    @ElVoField(physicalName = "accountId")
    public int getAccountId(){
        return accountId;
    }

    @ElVoField(physicalName = "accountId")
    public void setAccountId(int accountId){
        this.accountId = accountId;
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

    @ElVoField(physicalName = "career")
    public String getCareer(){
        String ret = this.career;
        return ret;
    }

    @ElVoField(physicalName = "career")
    public void setCareer(String career){
        this.career = career;
    }

    @ElVoField(physicalName = "preferred_locations")
    public String getPreferred_locations(){
        String ret = this.preferred_locations;
        return ret;
    }

    @ElVoField(physicalName = "preferred_locations")
    public void setPreferred_locations(String preferred_locations){
        this.preferred_locations = preferred_locations;
    }

    @ElVoField(physicalName = "current_position")
    public String getCurrent_position(){
        String ret = this.current_position;
        return ret;
    }

    @ElVoField(physicalName = "current_position")
    public void setCurrent_position(String current_position){
        this.current_position = current_position;
    }

    @ElVoField(physicalName = "year_salary")
    public int getYear_salary(){
        return year_salary;
    }

    @ElVoField(physicalName = "year_salary")
    public void setYear_salary(int year_salary){
        this.year_salary = year_salary;
    }

    @ElVoField(physicalName = "is_mbti_checked")
    public String getIs_mbti_checked(){
        String ret = this.is_mbti_checked;
        return ret;
    }

    @ElVoField(physicalName = "is_mbti_checked")
    public void setIs_mbti_checked(String is_mbti_checked){
        this.is_mbti_checked = is_mbti_checked;
    }

    @ElVoField(physicalName = "is_code_checked")
    public String getIs_code_checked(){
        String ret = this.is_code_checked;
        return ret;
    }

    @ElVoField(physicalName = "is_code_checked")
    public void setIs_code_checked(String is_code_checked){
        this.is_code_checked = is_code_checked;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserInfoVo [");
        sb.append("accountId").append("=").append(accountId).append(",");
        sb.append("name").append("=").append(name).append(",");
        sb.append("career").append("=").append(career).append(",");
        sb.append("preferred_locations").append("=").append(preferred_locations).append(",");
        sb.append("current_position").append("=").append(current_position).append(",");
        sb.append("year_salary").append("=").append(year_salary).append(",");
        sb.append("is_mbti_checked").append("=").append(is_mbti_checked).append(",");
        sb.append("is_code_checked").append("=").append(is_code_checked);
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
