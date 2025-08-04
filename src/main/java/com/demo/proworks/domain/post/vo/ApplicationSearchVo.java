package com.demo.proworks.domain.post.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "이력서 지원한 공고 호출 Vo")
public class ApplicationSearchVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public ApplicationSearchVo(){
    }

    @ElDtoField(logicalName = "페이지번호", physicalName = "pageIndex", type = "long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private long pageIndex;

    @ElDtoField(logicalName = "페이지크기", physicalName = "pageSize", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int pageSize;

    @ElDtoField(logicalName = "사용자ID", physicalName = "userId", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int userId;

    @ElDtoField(logicalName = "이력서_상태", physicalName = "applicationStatus", type = "String", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private String applicationStatus;

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

    @ElVoField(physicalName = "userId")
    public int getUserId(){
        return userId;
    }

    @ElVoField(physicalName = "userId")
    public void setUserId(int userId){
        this.userId = userId;
    }

    @ElVoField(physicalName = "applicationStatus")
    public String getApplicationStatus(){
        String ret = this.applicationStatus;
        return ret;
    }

    @ElVoField(physicalName = "applicationStatus")
    public void setApplicationStatus(String applicationStatus){
        this.applicationStatus = applicationStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ApplicationSearchVo [");
        sb.append("pageIndex").append("=").append(pageIndex).append(",");
        sb.append("pageSize").append("=").append(pageSize).append(",");
        sb.append("userId").append("=").append(userId).append(",");
        sb.append("applicationStatus").append("=").append(applicationStatus);
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
