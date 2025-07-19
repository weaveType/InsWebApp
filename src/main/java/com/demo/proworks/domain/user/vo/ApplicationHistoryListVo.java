package com.demo.proworks.domain.user.vo;

import java.util.List;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "지원현황목록")
public class ApplicationHistoryListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public ApplicationHistoryListVo(){
    }

    @ElDtoField(logicalName = "지원현황리스트", physicalName = "applicationHistoryList", type = "java.util.List", typeKind = "Vo", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private List<ApplicationHistoryVo> applicationHistoryList;

    @ElDtoField(logicalName = "지원현황통계리스트", physicalName = "applicationStatsist", type = "java.util.List", typeKind = "Vo", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private List<ApplicationHistoryVo> applicationStatsList;

    @ElDtoField(logicalName = "totalCount", physicalName = "totalCount", type = "long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private long totalCount;

    @ElDtoField(logicalName = "pageSize", physicalName = "pageSize", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int pageSize;

    @ElDtoField(logicalName = "pageIndex", physicalName = "pageIndex", type = "long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private long pageIndex;

    @ElVoField(physicalName = "applicationHistoryList")
    public List<ApplicationHistoryVo> getApplicationHistoryList(){
        return applicationHistoryList;
    }

    @ElVoField(physicalName = "applicationHistoryList")
    public void setApplicationHistoryList(List<ApplicationHistoryVo> applicationHistoryList){
        this.applicationHistoryList = applicationHistoryList;
    }

    @ElVoField(physicalName = "applicationStatsList")
    public List<ApplicationHistoryVo> getApplicationStatsList(){
        return applicationStatsList;
    }

    @ElVoField(physicalName = "applicationStatsList")
    public void setApplicationStatsList(List<ApplicationHistoryVo> applicationStatsList){
        this.applicationStatsList = applicationStatsList;
    }

    @ElVoField(physicalName = "totalCount")
    public long getTotalCount(){
        return totalCount;
    }

    @ElVoField(physicalName = "totalCount")
    public void setTotalCount(long totalCount){
        this.totalCount = totalCount;
    }

    @ElVoField(physicalName = "pageSize")
    public int getPageSize(){
        return pageSize;
    }

    @ElVoField(physicalName = "pageSize")
    public void setPageSize(int pageSize){
        this.pageSize = pageSize;
    }

    @ElVoField(physicalName = "pageIndex")
    public long getPageIndex(){
        return pageIndex;
    }

    @ElVoField(physicalName = "pageIndex")
    public void setPageIndex(long pageIndex){
        this.pageIndex = pageIndex;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ApplicationHistoryListVo [");
        sb.append("applicationHistoryList").append("=").append(applicationHistoryList).append(",");
        sb.append("applicationStatsList").append("=").append(applicationStatsList).append(",");
        sb.append("totalCount").append("=").append(totalCount).append(",");
        sb.append("pageSize").append("=").append(pageSize).append(",");
        sb.append("pageIndex").append("=").append(pageIndex);
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