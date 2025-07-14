package com.demo.proworks.common.enumType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 사용 예시
//DevMbti mbti = DevMbti.fromCode("BRSD");
//System.out.println(mbti.getName()); // "빌더 리팩터"
//System.out.println(mbti.getDescription()); // "실용적 구현과 리팩토링을 중시하며..."

public enum DevMbti {

	// Architect + Refactor + Solo + Debug
	ARSD("ARSD", "아키텍트 디버거", "설계와 리팩토링을 중시하며, 혼자서 디버깅에 집중하는 타입"),

	// Architect + Refactor + Solo + Feature
	ARSF("ARSF", "아키텍트 솔로", "설계와 리팩토링을 중시하며, 혼자서 새로운 기능 개발을 선호하는 타입"),

	// Architect + Refactor + Team + Debug
	ARTD("ARTD", "아키텍트 팀 디버거", "설계와 리팩토링을 중시하며, 팀과 함께 디버깅을 하는 타입"),

	// Architect + Refactor + Team + Feature
	ARTF("ARTF", "아키텍트 팀 리더", "설계와 리팩토링을 중시하며, 팀과 함께 새로운 기능을 개발하는 타입"),

	// Architect + Implement + Solo + Debug
	AISD("AISD", "설계자 솔로 디버거", "설계와 신규 구현을 중시하며, 혼자서 디버깅에 집중하는 타입"),

	// Architect + Implement + Solo + Feature
	AISF("AISF", "설계자 신속 구현", "설계와 신규 구현을 중시하며, 혼자서 빠르게 기능을 만드는 타입"),

	// Architect + Implement + Team + Debug
	AITD("AITD", "설계자 팀 구현", "설계와 신규 구현을 중시하며, 팀과 함께 디버깅을 하는 타입"),

	// Architect + Implement + Team + Feature
	AITF("AITF", "아키텍트 이노베이터", "설계와 신규 구현을 중시하며, 팀과 함께 혁신적인 기능을 만드는 타입"),

	// Builder + Refactor + Solo + Debug
	BRSD("BRSD", "빌더 리팩터", "실용적 구현과 리팩토링을 중시하며, 혼자서 디버깅에 집중하는 타입"),

	// Builder + Refactor + Solo + Feature
	BRSF("BRSF", "빌더 솔로 크래프터", "실용적 구현과 리팩토링을 중시하며, 혼자서 기능을 만드는 타입"),

	// Builder + Refactor + Team + Debug
	BRTD("BRTD", "빌더 팀 리팩터", "실용적 구현과 리팩토링을 중시하며, 팀과 함께 디버깅하는 타입"),

	// Builder + Refactor + Team + Feature
	BRTF("BRTF", "빌더 팀 크래프터", "실용적 구현과 리팩토링을 중시하며, 팀과 함께 기능을 개발하는 타입"),

	// Builder + Implement + Solo + Debug
	BISD("BISD", "빌더 솔로 디버거", "실용적이고 빠른 구현을 중시하며, 혼자서 디버깅에 집중하는 타입"),

	// Builder + Implement + Solo + Feature
	BISF("BISF", "빌더 스프린터", "실용적이고 빠른 구현을 중시하며, 혼자서 빠르게 기능을 만드는 타입"),

	// Builder + Implement + Team + Debug
	BITD("BITD", "빌더 팀 구현", "실용적이고 빠른 구현을 중시하며, 팀과 함께 디버깅하는 타입"),

	// Builder + Implement + Team + Feature
	BITF("BITF", "빌더 팀 스프린터", "실용적이고 빠른 구현을 중시하며, 팀과 함께 빠르게 기능을 개발하는 타입");

	private final String code;
	private final String name;
	private final String description;

	DevMbti(String code, String name, String description) {
		this.code = code;
		this.name = name;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	/**
	 * 코드로 DevMbti를 찾는 메서드
	 */
	public static DevMbti fromCode(String code) {
		if (code == null) {
			return null;
		}

		for (DevMbti mbti : DevMbti.values()) {
			if (mbti.code.equals(code)) {
				return mbti;
			}
		}
		return null;
	}

	/**
	 * Architect 성향인지 확인
	 */
	public boolean isArchitect() {
		return code.charAt(0) == 'A';
	}

	/**
	 * Builder 성향인지 확인
	 */
	public boolean isBuilder() {
		return code.charAt(0) == 'B';
	}

	/**
	 * Refactor 성향인지 확인
	 */
	public boolean isRefactor() {
		return code.charAt(1) == 'R';
	}

	/**
	 * Implement 성향인지 확인
	 */
	public boolean isImplement() {
		return code.charAt(1) == 'I';
	}

	/**
	 * Solo 성향인지 확인
	 */
	public boolean isSolo() {
		return code.charAt(2) == 'S';
	}

	/**
	 * Team 성향인지 확인
	 */
	public boolean isTeam() {
		return code.charAt(2) == 'T';
	}

	/**
	 * Debug 성향인지 확인
	 */
	public boolean isDebug() {
		return code.charAt(3) == 'D';
	}

	/**
	 * Feature 성향인지 확인
	 */
	public boolean isFeature() {
		return code.charAt(3) == 'F';
	}

	/**
	 * 각 차원별 성향 문자열 반환
	 */
	public String getDimensions() {
		StringBuilder sb = new StringBuilder();
		sb.append(isArchitect() ? "Architect" : "Builder").append(" + ");
		sb.append(isRefactor() ? "Refactor" : "Implement").append(" + ");
		sb.append(isSolo() ? "Solo" : "Team").append(" + ");
		sb.append(isDebug() ? "Debug" : "Feature");
		return sb.toString();
	}

	@Override
	public String toString() {
		return String.format("%s(%s): %s", name, code, description);
	}

	public static List<String> getAllCodes() {
		return Arrays.stream(DevMbti.values()) // 모든 enum 값들을 스트림으로 변환
				.map(DevMbti::getCode) // 각 enum의 code 값만 추출
				.collect(Collectors.toList()); // 리스트로 반환
	}
}