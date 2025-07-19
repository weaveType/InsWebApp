package com.demo.proworks.common.enumType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum IndustryType {

	SOFTWARE_DEVELOPMENT("SOFTWARE_DEVELOPMENT", "소프트웨어 개발"), WEB_SERVICE("WEB_SERVICE", "웹 서비스"),
	FINTECH("FINTECH", "핀테크"), GAME_DEVELOPMENT("GAME_DEVELOPMENT", "게임 개발"), ECOMMERCE("ECOMMERCE", "전자상거래"),
	AI_ML("AI_ML", "인공지능/머신러닝"), OTHER("OTHER", "기타");

	private final String code;
	private final String description;

	IndustryType(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() { 
		return description;
	}

	/**
	 * SelectBox용 전체 산업 목록을 Map 형태로 반환 WebSquare의 DataList에서 사용하기 적합한 형태
	 * 
	 * @return List<Map<String, String>> 형태의 산업 목록
	 */
	public static List<Map<String, String>> getAllIndustriesForSelectBox() {
		return Arrays.stream(values()).map(industry -> {
			Map<String, String> item = new HashMap<>();
			item.put("industryCode", industry.getCode());
			item.put("industryName", industry.getDescription());
			return item;
		}).collect(Collectors.toList());
	}
}