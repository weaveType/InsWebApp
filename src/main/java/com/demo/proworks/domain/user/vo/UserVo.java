package com.demo.proworks.domain.user.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "일반회원")
public class UserVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public UserVo(){
    }

    @ElDtoField(logicalName = "user_id", physicalName = "userId", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int userId;

    @ElDtoField(logicalName = "email", physicalName = "email", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String email;

    @ElDtoField(logicalName = "password", physicalName = "password", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String password;

    @ElDtoField(logicalName = "role", physicalName = "role", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String role;

    @ElDtoField(logicalName = "name", physicalName = "name", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String name;

    @ElDtoField(logicalName = "email_consent", physicalName = "emailConsent", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String emailConsent;

    @ElDtoField(logicalName = "created_at", physicalName = "createdAt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String createdAt;

    @ElDtoField(logicalName = "updated_at", physicalName = "updatedAt", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String updatedAt;

    @ElDtoField(logicalName = "role_id", physicalName = "roleId", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int roleId;

    @ElDtoField(logicalName = "user_role", physicalName = "userRole", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String userRole;

    @ElDtoField(logicalName = "gender", physicalName = "gender", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String gender;

    @ElDtoField(logicalName = "birth_date", physicalName = "birthDate", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String birthDate;

    @ElDtoField(logicalName = "phone_number", physicalName = "phoneNumber", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String phoneNumber;

    @ElDtoField(logicalName = "year_salary", physicalName = "yearSalary", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int yearSalary;

    @ElDtoField(logicalName = "career", physicalName = "career", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String career;

    @ElDtoField(logicalName = "current_position", physicalName = "currentPosition", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String currentPosition;

    @ElDtoField(logicalName = "bio", physicalName = "bio", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String bio;

    @ElDtoField(logicalName = "resume_file_name", physicalName = "resumeFileName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String resumeFileName;

    @ElDtoField(logicalName = "test_checked", physicalName = "testChecked", type = "boolean", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private boolean testChecked;

    @ElDtoField(logicalName = "profile_image_name", physicalName = "profileImageName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String profileImageName;

    @ElDtoField(logicalName = "preferred_locations", physicalName = "preferredLocations", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String preferredLocations;

    @ElDtoField(logicalName = "salary_range", physicalName = "salaryRange", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String salaryRange;

    @ElDtoField(logicalName = "career_period", physicalName = "careerPeriod", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String careerPeriod;

    @ElDtoField(logicalName = "role_name", physicalName = "roleName", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String roleName;

    @ElDtoField(logicalName = "기술스택", physicalName = "techStackVo", type = "", typeKind = "List", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private java.util.List<com.demo.proworks.domain.user.vo.TechStackVo> techStackVo;

    @ElVoField(physicalName = "userId")
    public int getUserId(){
        return userId;
    }

    @ElVoField(physicalName = "userId")
    public void setUserId(int userId){
        this.userId = userId;
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

    @ElVoField(physicalName = "role")
    public String getRole(){
        String ret = this.role;
        return ret;
    }

    @ElVoField(physicalName = "role")
    public void setRole(String role){
        this.role = role;
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

    @ElVoField(physicalName = "emailConsent")
    public String getEmailConsent(){
        String ret = this.emailConsent;
        return ret;
    }

    @ElVoField(physicalName = "emailConsent")
    public void setEmailConsent(String emailConsent){
        this.emailConsent = emailConsent;
    }

    @ElVoField(physicalName = "createdAt")
    public String getCreatedAt(){
        String ret = this.createdAt;
        return ret;
    }

    @ElVoField(physicalName = "createdAt")
    public void setCreatedAt(String createdAt){
        this.createdAt = createdAt;
    }

    @ElVoField(physicalName = "updatedAt")
    public String getUpdatedAt(){
        String ret = this.updatedAt;
        return ret;
    }

    @ElVoField(physicalName = "updatedAt")
    public void setUpdatedAt(String updatedAt){
        this.updatedAt = updatedAt;
    }

    @ElVoField(physicalName = "roleId")
    public int getRoleId(){
        return roleId;
    }

    @ElVoField(physicalName = "roleId")
    public void setRoleId(int roleId){
        this.roleId = roleId;
    }

    @ElVoField(physicalName = "userRole")
    public String getUserRole(){
        String ret = this.userRole;
        return ret;
    }

    @ElVoField(physicalName = "userRole")
    public void setUserRole(String userRole){
        this.userRole = userRole;
    }

    @ElVoField(physicalName = "gender")
    public String getGender(){
        String ret = this.gender;
        return ret;
    }

    @ElVoField(physicalName = "gender")
    public void setGender(String gender){
        this.gender = gender;
    }

    @ElVoField(physicalName = "birthDate")
    public String getBirthDate(){
        String ret = this.birthDate;
        return ret;
    }

    @ElVoField(physicalName = "birthDate")
    public void setBirthDate(String birthDate){
        this.birthDate = birthDate;
    }

    @ElVoField(physicalName = "phoneNumber")
    public String getPhoneNumber(){
        String ret = this.phoneNumber;
        return ret;
    }

    @ElVoField(physicalName = "phoneNumber")
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    @ElVoField(physicalName = "yearSalary")
    public int getYearSalary(){
        return yearSalary;
    }

    @ElVoField(physicalName = "yearSalary")
    public void setYearSalary(int yearSalary){
        this.yearSalary = yearSalary;
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

    @ElVoField(physicalName = "currentPosition")
    public String getCurrentPosition(){
        String ret = this.currentPosition;
        return ret;
    }

    @ElVoField(physicalName = "currentPosition")
    public void setCurrentPosition(String currentPosition){
        this.currentPosition = currentPosition;
    }

    @ElVoField(physicalName = "bio")
    public String getBio(){
        String ret = this.bio;
        return ret;
    }

    @ElVoField(physicalName = "bio")
    public void setBio(String bio){
        this.bio = bio;
    }

    @ElVoField(physicalName = "resumeFileName")
    public String getResumeFileName(){
        String ret = this.resumeFileName;
        return ret;
    }

    @ElVoField(physicalName = "resumeFileName")
    public void setResumeFileName(String resumeFileName){
        this.resumeFileName = resumeFileName;
    }

    @ElVoField(physicalName = "testChecked")
    public boolean isTestChecked(){
        return testChecked;
    }

    @ElVoField(physicalName = "testChecked")
    public void setTestChecked(boolean testChecked){
        this.testChecked = testChecked;
    }

    @ElVoField(physicalName = "profileImageName")
    public String getProfileImageName(){
        String ret = this.profileImageName;
        return ret;
    }

    @ElVoField(physicalName = "profileImageName")
    public void setProfileImageName(String profileImageName){
        this.profileImageName = profileImageName;
    }

    @ElVoField(physicalName = "preferredLocations")
    public String getPreferredLocations(){
        String ret = this.preferredLocations;
        return ret;
    }

    @ElVoField(physicalName = "preferredLocations")
    public void setPreferredLocations(String preferredLocations){
        this.preferredLocations = preferredLocations;
    }

    @ElVoField(physicalName = "salaryRange")
    public String getSalaryRange(){
        String ret = this.salaryRange;
        return ret;
    }

    @ElVoField(physicalName = "salaryRange")
    public void setSalaryRange(String salaryRange){
        this.salaryRange = salaryRange;
    }

    @ElVoField(physicalName = "careerPeriod")
    public String getCareerPeriod(){
        String ret = this.careerPeriod;
        return ret;
    }

    @ElVoField(physicalName = "careerPeriod")
    public void setCareerPeriod(String careerPeriod){
        this.careerPeriod = careerPeriod;
    }

    @ElVoField(physicalName = "roleName")
    public String getRoleName(){
        String ret = this.roleName;
        return ret;
    }

    @ElVoField(physicalName = "roleName")
    public void setRoleName(String roleName){
        this.roleName = roleName;
    }

    @ElVoField(physicalName = "techStackVo")
    public java.util.List<com.demo.proworks.domain.user.vo.TechStackVo> getTechStackVo(){
        return techStackVo;
    }

    @ElVoField(physicalName = "techStackVo")
    public void setTechStackVo(java.util.List<com.demo.proworks.domain.user.vo.TechStackVo> techStackVo){
        this.techStackVo = techStackVo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserVo [");
        sb.append("userId").append("=").append(userId).append(",");
        sb.append("email").append("=").append(email).append(",");
        sb.append("password").append("=").append(password).append(",");
        sb.append("role").append("=").append(role).append(",");
        sb.append("name").append("=").append(name).append(",");
        sb.append("emailConsent").append("=").append(emailConsent).append(",");
        sb.append("createdAt").append("=").append(createdAt).append(",");
        sb.append("updatedAt").append("=").append(updatedAt).append(",");
        sb.append("roleId").append("=").append(roleId).append(",");
        sb.append("userRole").append("=").append(userRole).append(",");
        sb.append("gender").append("=").append(gender).append(",");
        sb.append("birthDate").append("=").append(birthDate).append(",");
        sb.append("phoneNumber").append("=").append(phoneNumber).append(",");
        sb.append("yearSalary").append("=").append(yearSalary).append(",");
        sb.append("career").append("=").append(career).append(",");
        sb.append("currentPosition").append("=").append(currentPosition).append(",");
        sb.append("bio").append("=").append(bio).append(",");
        sb.append("resumeFileName").append("=").append(resumeFileName).append(",");
        sb.append("testChecked").append("=").append(testChecked).append(",");
        sb.append("profileImageName").append("=").append(profileImageName).append(",");
        sb.append("preferredLocations").append("=").append(preferredLocations).append(",");
        sb.append("salaryRange").append("=").append(salaryRange).append(",");
        sb.append("careerPeriod").append("=").append(careerPeriod).append(",");
        sb.append("roleName").append("=").append(roleName).append(",");
        sb.append("techStackVo").append("=").append(techStackVo);
        sb.append("]");
        return sb.toString();

    }

    public boolean isFixedLengthVo() {
        return false;
    }

    @Override
    public void _xStreamEnc() {
        for( int i=0 ; techStackVo != null && i < techStackVo.size() ; i++ ) {
            com.demo.proworks.domain.user.vo.TechStackVo vo = (com.demo.proworks.domain.user.vo.TechStackVo)techStackVo.get(i);
            vo._xStreamEnc();	 
        }
    }


    @Override
    public void _xStreamDec() {
        for( int i=0 ; techStackVo != null && i < techStackVo.size() ; i++ ) {
            com.demo.proworks.domain.user.vo.TechStackVo vo = (com.demo.proworks.domain.user.vo.TechStackVo)techStackVo.get(i);
            vo._xStreamDec();	 
        }
    }


}
