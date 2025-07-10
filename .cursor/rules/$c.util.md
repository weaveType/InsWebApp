```
{
  "UTIL_LIBRARY": {
    "methods": [
      {
        "name": "getUserAgent",
        "description": "현재 브라우저 종류 반환",
        "signature": "getUserAgent() -> String"
      },
      {
        "name": "getParameter",
        "description": "URL QueryString 파라미터 값 조회",
        "signature": "getParameter(param, url?) -> String",
        "parameters": {
          "param": "조회 키",
          "url": "대상 URL (생략 시 location.href)"
        }
      },
      {
        "name": "getObject",
        "description": "컴포넌트·스코프 객체 조회",
        "signature": "getObject(objectId, scopeObj?) -> Component|null"
      },
      {
        "name": "getFunction",
        "description": "문자열 함수명으로 Function 객체 찾기",
        "signature": "getFunction(funcStr, scopeId?) -> Function|undefined"
      },
      {
        "name": "getCallBackFunction",
        "description": "메시지박스 등에서 전달된 콜백문자열을 실제 함수로 변환",
        "signature": "getCallBackFunction(callbackFnStr) -> Function|undefined"
      },
      {
        "name": "getJSON",
        "description": "문자열/XML을 안전하게 JSON 객체로 변환",
        "signature": "getJSON(value) -> any"
      },
      {
        "name": "isJSON",
        "description": "값이 순수 JSON Object/Array 인지 여부",
        "signature": "isJSON(json) -> Boolean"
      },
      {
        "name": "isArray",
        "description": "Array 여부 판별(타입·생성자 검사)",
        "signature": "isArray(obj) -> Boolean"
      },
      {
        "name": "isEmpty",
        "description": "null/undefined/''/[]/{} 확인",
        "signature": "isEmpty(value) -> Boolean"
      },
      {
        "name": "getObjectType",
        "description": "세분화된 타입(string|array|json|xml|null…) 반환",
        "signature": "getObjectType(obj) -> String"
      },
      {
        "name": "isPlainObject",
        "description": "{} 리터럴 기반의 ‘순수’ 객체인지 검사",
        "signature": "isPlainObject(obj) -> Boolean"
      },
      {
        "name": "isMobile",
        "description": "모바일 브라우저 여부(WebSquare util 사용)",
        "signature": "isMobile() -> Boolean"
      },
      {
        "name": "isXmlDoc",
        "description": "DOM Document or Element 판별",
        "signature": "isXmlDoc(data) -> Boolean"
      },
      {
        "name": "setGridViewDelCheckBox",
        "description": "GridView ‘chk’ 컬럼 삭제 처리(행 체크·delete/undelete)",
        "signature": "setGridViewDelCheckBox(gridViewObjArr)"
      },
      {
        "name": "getChildren",
        "description": "컴포넌트 하위 자식 요소 탐색",
        "signature": "getChildren(comObj, options?) -> Component[]"
      },
      {
        "name": "getGridViewDataList",
        "description": "GridView에 바인딩된 DataList 반환",
        "signature": "getGridViewDataList(gridViewObj) -> DataList|null"
      },
      {
        "name": "getComponent",
        "description": "ID 기반 컴포넌트 조회(존재 안하면 null)",
        "signature": "getComponent(compId) -> Component|null"
      },
      {
        "name": "createComponent",
        "description": "동적 컴포넌트 생성 및 반환",
        "signature": "createComponent(id, tagName, option?, parent?, itemSet?) -> Component|null"
      },
      {
        "name": "setInterval",
        "description": "SPA-안전 setInterval (페이지 unload 자동 clear)",
        "signature": "setInterval(func, options)"
      },
      {
        "name": "clearInterval",
        "description": "setInterval로 등록한 키 타이머 해제",
        "signature": "clearInterval(key, options?)"
      },
      {
        "name": "setTimeout",
        "description": "SPA-안전 setTimeout",
        "signature": "setTimeout(func, options)"
      },
      {
        "name": "clearTimeout",
        "description": "setTimeout으로 등록한 키 타이머 해제",
        "signature": "clearTimeout(key, options?)"
      },
      {
        "name": "copyClipboard",
        "description": "입력컴포넌트 값을 클립보드에 복사 (async/IE fallback)",
        "signature": "copyClipboard(comObj) -> Promise<void>"
      }
    ]
  }
}

```