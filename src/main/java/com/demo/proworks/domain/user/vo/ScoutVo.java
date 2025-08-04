package com.demo.proworks.domain.user.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "일반회원")
public class ScoutVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public ScoutVo(){
    }

    @ElDtoField(logicalName = "페이지_번호", physicalName = "pageIndex", type = "long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private long pageIndex;

    @ElDtoField(logicalName = "페이지_건수", physicalName = "pageSize", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int pageSize;

    @ElDtoField(logicalName = "공고_id", physicalName = "jobPostingId", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int jobPostingId;

    @ElDtoField(logicalName = "스카웃_상태", physicalName = "scoutStatus", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String scoutStatus;

    @ElDtoField(logicalName = "기업_mbti", physicalName = "companyMbti", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String companyMbti;

    @ElDtoField(logicalName = "mbti_필터", physicalName = "mbtiMatchFilter", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String mbtiMatchFilter;

    @ElVoField(physicalName = "pageIndex")
    public long getPageIndex(){
        return pageIndex;
    }

    @ElVoField(physicalName = "pageIndex")
    public void setPageIndex(long pageIndex){
        this.pageIndex = pageIndex;
    }

    @ElVoField(physicalName = "pageSize")
    public int getPageSize(){
        return pageSize;
    }

    @ElVoField(physicalName = "pageSize")
    public void setPageSize(int pageSize){
        this.pageSize = pageSize;
    }

    @ElVoField(physicalName = "jobPostingId")
    public int getJobPostingId(){
        return jobPostingId;
    }

    @ElVoField(physicalName = "jobPostingId")
    public void setJobPostingId(int jobPostingId){
        this.jobPostingId = jobPostingId;
    }

    @ElVoField(physicalName = "scoutStatus")
    public String getScoutStatus(){
        String ret = this.scoutStatus;
        return ret;
    }

    @ElVoField(physicalName = "scoutStatus")
    public void setScoutStatus(String scoutStatus){
        this.scoutStatus = scoutStatus;
    }

    @ElVoField(physicalName = "companyMbti")
    public String getCompanyMbti(){
        String ret = this.companyMbti;
        return ret;
    }

    @ElVoField(physicalName = "companyMbti")
    public void setCompanyMbti(String companyMbti){
        this.companyMbti = companyMbti;
    }

    @ElVoField(physicalName = "mbtiMatchFilter")
    public String getMbtiMatchFilter(){
        String ret = this.mbtiMatchFilter;
        return ret;
    }

    @ElVoField(physicalName = "mbtiMatchFilter")
    public void setMbtiMatchFilter(String mbtiMatchFilter){
        this.mbtiMatchFilter = mbtiMatchFilter;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ScoutVo [");
        sb.append("pageIndex").append("=").append(pageIndex).append(",");
        sb.append("pageSize").append("=").append(pageSize).append(",");
        sb.append("jobPostingId").append("=").append(jobPostingId).append(",");
        sb.append("scoutStatus").append("=").append(scoutStatus).append(",");
        sb.append("companyMbti").append("=").append(companyMbti).append(",");
        sb.append("mbtiMatchFilter").append("=").append(mbtiMatchFilter);
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
