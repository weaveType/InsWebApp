package com.demo.proworks.domain.post.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", logicalName = "공고정보")
public class PostListVo extends com.demo.proworks.cmmn.ProworksCommVO {
    private static final long serialVersionUID = 1L;

    @ElDtoField(logicalName = "공고정보List", physicalName = "postVoList", type = "com.demo.proworks.domain.post.PostVo", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private java.util.List<com.demo.proworks.domain.post.vo.PostVo> postVoList;

    @ElDtoField(logicalName = "총 개수", physicalName = "totalCount", type = "long", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
    private long totalCount;

    public java.util.List<com.demo.proworks.domain.post.vo.PostVo> getPostVoList(){
        return postVoList;
    }

    public void setPostVoList(java.util.List<com.demo.proworks.domain.post.vo.PostVo> postVoList){
        this.postVoList = postVoList;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "PostListVo [postVoList=" + postVoList + ", totalCount=" + totalCount + "]";
    }

    public boolean isFixedLengthVo() {
        return false;
    }

}