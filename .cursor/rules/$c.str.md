```
{
  "STR_LIBRARY": {
    "methods": [
      {
        "name": "serialize",
        "description": "JSON/XML → String 안전 직렬화",
        "signature": "serialize(object, replacer?, space?) -> String"
      },
      {
        "name": "lpad",
        "description": "지정 길이까지 왼쪽을 char로 채우기",
        "signature": "lpad(str|number, length, char) -> String"
      },
      {
        "name": "rpad",
        "description": "지정 길이까지 오른쪽을 char로 채우기",
        "signature": "rpad(str, length, char) -> String"
      },
      {
        "name": "formatSSN",
        "description": "주민번호 13자리 → ######-#######",
        "signature": "formatSSN(str) -> String"
      },
      {
        "name": "formatPhone",
        "description": "전화번호 하이픈 자동 포매팅",
        "signature": "formatPhone(str) -> String"
      },
      {
        "name": "formatTime",
        "description": "HHmmss(또는 HHmm) → HH:mm(:ss)",
        "signature": "formatTime(str) -> String"
      },
      {
        "name": "getLocale",
        "description": "문자 1글자의 유니코드 카테고리 반환",
        "signature": "getLocale(char) -> Number"
      },
      {
        "name": "existKorean",
        "description": "문자열에 한글이 ‘포함’돼 있는지 여부",
        "signature": "existKorean(value) -> Boolean"
      },
      {
        "name": "isKorean",
        "description": "문자열이 전부 한글인지 검사",
        "signature": "isKorean(str) -> Boolean"
      },
      {
        "name": "isFinalConsonant",
        "description": "마지막 글자에 받침(종성) 존재 여부",
        "signature": "isFinalConsonant(str) -> Boolean"
      },
      {
        "name": "attachPostposition",
        "description": "‘은/는’ 조사 자동 부착(한국어 환경 한정)",
        "signature": "attachPostposition(str) -> String"
      },
      {
        "name": "isBizID",
        "description": "10자리 사업자등록번호 유효성 검사",
        "signature": "isBizID(str) -> Boolean"
      },
      {
        "name": "isSSN",
        "description": "13자리 내·외국인 주민번호 유효성",
        "signature": "isSSN(str) -> Boolean"
      },
      {
        "name": "isEmail",
        "description": "이메일 주소 정규식 검증",
        "signature": "isEmail(str) -> Boolean"
      },
      {
        "name": "isPhone",
        "description": "국내 전화번호 포맷 유효성 검사",
        "signature": "isPhone(str) -> Boolean"
      },
      {
        "name": "replaceAll",
        "description": "모든 orgStr를 repStr로 치환",
        "signature": "replaceAll(str, orgStr, repStr) -> String"
      },
      {
        "name": "trim",
        "description": "좌‧우 공백 제거(String.prototype.trim wrapper)",
        "signature": "trim(str) -> String"
      },
      {
        "name": "getByteLength",
        "description": "문자열의 바이트 길이 반환(멀티바이트 고려)",
        "signature": "getByteLength(str, ignoreChar?) -> Number"
      }
    ]
  }
}

```