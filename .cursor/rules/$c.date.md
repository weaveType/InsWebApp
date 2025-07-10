```
{
  "DATE_UTILS": {
    "methods": [
      {
        "name": "addMinute",
        "description": "지정한 분(offset)을 더한 날짜/시간 문자열 반환",
        "signature": "addMinute(pYmd, offset)",
        "parameters": {
          "pYmd": "String (yyyyMMdd | yyyyMMddHHmmss)",
          "offset": "Number (분)"
        },
        "returns": "String"
      },
      {
        "name": "addHour",
        "description": "지정한 시간(offset)을 더한 날짜/시간 문자열 반환",
        "signature": "addHour(pYmd, offset)",
        "parameters": {
          "pYmd": "String (yyyyMMdd | yyyyMMddHHmmss)",
          "offset": "Number (시간)"
        },
        "returns": "String"
      },
      {
        "name": "addDate",
        "description": "지정한 일(offset)을 더한 날짜 문자열 반환",
        "signature": "addDate(pYmd, offset)",
        "parameters": {
          "pYmd": "String (yyyyMMdd | yyyyMMddHHmmss)",
          "offset": "Number (일)"
        },
        "returns": "String"
      },
      {
        "name": "addMonth",
        "description": "지정한 개월(offset)을 더한 날짜 문자열 반환",
        "signature": "addMonth(pYmd, offset)",
        "parameters": {
          "pYmd": "String (yyyyMMdd | yyyyMMddHHmmss)",
          "offset": "Number (개월)"
        },
        "returns": "String"
      },
      {
        "name": "getServerDateTime",
        "description": "서버 기준 현재 날짜/시간 문자열 반환",
        "signature": "getServerDateTime(sDateFormat?)",
        "parameters": {
          "sDateFormat": "String (선택·default: \"yyyyMMdd\")"
        },
        "returns": "String"
      },
      {
        "name": "formatDate",
        "description": "yyyyMMdd 문자열을 다양한 포맷으로 변환",
        "signature": "formatDate(str, type?)",
        "parameters": {
          "str": "String | Number (yyyyMMdd)",
          "type": "String (slash | date | colon | custom 구분자)"
        },
        "returns": "String"
      },
      {
        "name": "formatTime",
        "description": "HHmmss → HH:mm:ss 포맷 변환",
        "signature": "formatTime(value)",
        "parameters": {
          "value": "String | Number (HHmmss)"
        },
        "returns": "String"
      },
      {
        "name": "formatDateTime",
        "description": "Date 객체를 지정 포맷 문자열로 변환",
        "signature": "formatDateTime(value, sDateFormat?)",
        "parameters": {
          "value": "Date",
          "sDateFormat": "String (default: \"yyyyMMdd\")"
        },
        "returns": "String"
      },
      {
        "name": "isDate",
        "description": "문자열이 유효한 날짜(및 시간)인지 검사",
        "signature": "isDate(sDate, timeChk?)",
        "parameters": {
          "sDate": "String | Number (yyyyMMdd[HHmmss])",
          "timeChk": "Boolean (선택) – 24:00 허용 여부"
        },
        "returns": "Boolean"
      },
      {
        "name": "diffDate",
        "description": "두 날짜 간 일(day) 차이 반환",
        "signature": "diffDate(fromDate, toDate)",
        "parameters": {
          "fromDate": "String (yyyyMMdd | yyyyMMddHHmmss)",
          "toDate": "String (yyyyMMdd | yyyyMMddHHmmss)"
        },
        "returns": "Number"
      },
      {
        "name": "isLeafYear",
        "description": "윤년 여부 반환",
        "signature": "isLeafYear(pYmd)",
        "parameters": {
          "pYmd": "String (yyyyMMdd)"
        },
        "returns": "Boolean"
      },
      {
        "name": "getDay",
        "description": "요일 반환 (한글 또는 숫자)",
        "signature": "getDay(value, type?)",
        "parameters": {
          "value": "String (yyyyMMdd)",
          "type": "String ('num' 사용 시 1–7)"
        },
        "returns": "String"
      },
      {
        "name": "getLunar",
        "description": "양력 날짜를 음력(yyyyMMdd)로 변환",
        "signature": "getLunar(value)",
        "parameters": {
          "value": "String (yyyyMMdd)"
        },
        "returns": "String"
      },
      {
        "name": "getLastDateOfMonth",
        "description": "해당 월 마지막 일(숫자) 반환",
        "signature": "getLastDateOfMonth(yearMonth)",
        "parameters": {
          "yearMonth": "String (yyyyMM)"
        },
        "returns": "Number"
      }
    ]
  }
}

```