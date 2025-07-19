package com.demo.proworks.domain.corporate.vo;

import com.inswave.elfw.annotation.ElDto;
import com.inswave.elfw.annotation.ElDtoField;
import com.inswave.elfw.annotation.ElVoField;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("elExcludeFilter")
@ElDto(FldYn = "", delimeterYn = "", logicalName = "기업정보")
public class IndusrtyVoList extends com.demo.proworks.cmmn.ProworksCommVO {
	private static final long serialVersionUID = 1L;

	public IndusrtyVoList() {
	}

	@ElDtoField(logicalName = "산업정보List", physicalName = "industryList", type = "java.util.Map", typeKind = "List", fldYn = "", length = 0, dotLen = 0, baseValue = "", desc = "")
	private java.util.List<java.util.Map<String, String>> industryList;

	public java.util.List<java.util.Map<String, String>> getIndustryList() {
		return industryList;
	}

	public void setIndustryList(java.util.List<java.util.Map<String, String>> industryList) {
		this.industryList = industryList;
	}

	@Override
	public String toString() {
		return "IndustryListVo [industryList=" + industryList + "]";
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