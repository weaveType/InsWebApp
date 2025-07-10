```
{
  "scwin": {
    "onpageload": function() {},
    "round": {
      "description": "반올림 처리를 한다.",
      "params": [
        { "value": "반올림 처리를 할 값 (String|Number)" },
        { "point": "반올림 처리를 할 소수점 자리 수 (Default : 0)" }
      ],
      "returns": "반올림 처리를 한 숫자 값 (Number)",
      "example": [
        "$c.num.round(23.4567); // return 예시) 23",
        "$c.num.round(23.5567); // return 예시) 24",
        "$c.num.round(23.5567, 2); // return 예시) 23.56",
        "$c.num.round(23.5564, 3); // return 예시) 23.556"
      ]
    },
    "floor": {
      "description": "내림 처리를 한다.",
      "params": [
        { "value": "내림 처리를 할 값 (String|Number)" },
        { "point": "내림 처리를 할 소수점 자리 수 (Default : 0)" }
      ],
      "returns": "내림 처리를 한 숫자 값 (Number)",
      "example": [
        "$c.num.floor(23.4567); // return 예시) 23",
        "$c.num.floor(23.5567); // return 예시) 23",
        "$c.num.floor(23.5567, 2); // return 예시) 23.55",
        "$c.num.floor(23.5564, 3); // return 예시) 23.556"
      ]
    },
    "ceil": {
      "description": "올림 처리를 한다.",
      "params": [
        { "value": "올림 처리를 할 값 (String 또는 Number)" },
        { "point": "올림 처리를 할 소수점 자리 수 (Default : 0)" }
      ],
      "returns": "올림 처리를 한 숫자 값 (Number)",
      "example": [
        "$c.num.ceil(23.5567); // return 예시) 24",
        "$c.num.ceil(23.5567, 2); // return 예시) 23.56"
      ]
    },
    "formatMoney": {
      "description": "세번째자리마다 콤마 표시, 금액, setDisplayFormat('#,###&#46##0', 'fn_userFormatter2')",
      "params": [
        { "value": "포멧터를 적용할 값 (String|Number)" },
        { "type": "적용할 포멧터 형식 (Default: null, dollar, plusZero, won)" }
      ],
      "returns": "포멧터가 적용된 문자열 (String)",
      "example": [
        "$c.num.formatMoney('12345'); // 12,345",
        "$c.num.formatMoney('12345', 'dollar'); // $12,345",
        "$c.num.formatMoney('12345', 'plusZero'); // 123,450",
        "$c.num.formatMoney('12345', 'won'); // 12,345원"
      ]
    },
    "formatNumber": {
      "description": "셋째자리마다 콤마를 추가해서 반환한다.",
      "params": [
        { "value": "포멧터를 적용할 값 (String|Number)" }
      ],
      "returns": "포멧터가 적용된 문자열 (String)",
      "example": [
        "$c.num.formatNumber('12345677'); // '12,345,677'",
        "$c.num.formatNumber(12345677); // '12,345,677'",
        "$c.num.formatNumber(-12345677); // '-12,345,677'"
      ]
    },
    "isNumber": {
      "description": "숫자가 맞는지 여부를 검사한다.",
      "params": [
        { "value": "검사할 숫자 값 (String|Number)" }
      ],
      "returns": "숫자이면 true, 아니면 false 반환 (Boolean)",
      "example": [
        "$c.num.isNumber('12345677'); // true",
        "$c.num.isNumber('abc'); // false"
      ]
    },
    "isOdd": {
      "description": "홀수가 맞는지 여부를 검사한다.",
      "params": [
        { "value": "검사할 값 (String|Number)" }
      ],
      "returns": "홀수이면 true, 아니면 false 반환 (Boolean)",
      "example": [
        "$c.num.isOdd('123'); // true",
        "$c.num.isOdd(123); // true",
        "$c.num.isOdd('122'); // false",
        "$c.num.isOdd(122); // false"
      ]
    },
    "parseInt": {
      "description": "문자열을 정수형으로 변환한다.",
      "params": [
        { "value": "정수 문자열 (String)" },
        { "defaultValue": "결과가 NaN일때 반환할 기본 값 (Number)" }
      ],
      "returns": "변환된 정수형 값 (Number)",
      "example": [
        "$c.num.parseInt('5231'); // 5231"
      ]
    },
    "parseFloat": {
      "description": "문자열을 실수형으로 변환한다.",
      "params": [
        { "value": "실수 문자열 (String)" },
        { "defaultValue": "결과가 NaN일때 반환할 기본 값 (Number)" }
      ],
      "returns": "변환된 실수형 값 (Number)",
      "example": [
        "$c.num.parseFloat('5231.22'); // 5231.22"
      ]
    },
    "formatByte": {
      "description": "바이트 값을 적절한 단위(KB, MB, GB)를 변환해서 반환한다.",
      "params": [
        { "value": "변환할 값 (Number)" }
      ],
      "returns": "적절한 단위(KB, MB, GB 등)로 변환된 바이트 값 (String)",
      "example": [
        "$c.num.formatByte(32132); // '31.4 KB'"
      ]
    }
  }
}

```