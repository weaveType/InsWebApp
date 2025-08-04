package com.demo.proworks.domain.corporate.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "회사정보")
public class CorporateSearchVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    public CorporateSearchVo(){
    }

    @ElDtoField(logicalName = "권한ID", physicalName = "roleId", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int roleId;

    @ElDtoField(logicalName = "페이지번호", physicalName = "pageIndex", type = "long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private long pageIndex;

    @ElDtoField(logicalName = "페이지 크기", physicalName = "pageSize", type = "int", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private int pageSize;

    @ElDtoField(logicalName = "오프셋", physicalName = "offset", type = "long", typeKind = "", fldYn = "", delimeterYn = "", cryptoGbn = "", cryptoKind = "", length = 0, dotLen = 0, baseValue = "", desc = "", attr = "")
    private long offset;

    @ElVoField(physicalName = "roleId")
    public int getRoleId(){
        return roleId;
    }

    @ElVoField(physicalName = "roleId")
    public void setRoleId(int roleId){
        this.roleId = roleId;
    }

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

    @ElVoField(physicalName = "offset")
    public long getOffset(){
        return offset;
    }

    @ElVoField(physicalName = "offset")
    public void setOffset(long offset){
        this.offset = offset;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CorporateSearchVo [");
        sb.append("roleId").append("=").append(roleId).append(",");
        sb.append("pageIndex").append("=").append(pageIndex).append(",");
        sb.append("pageSize").append("=").append(pageSize).append(",");
        sb.append("offset").append("=").append(offset);
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
