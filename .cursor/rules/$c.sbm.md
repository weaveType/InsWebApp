```
{
  "SBM_LIBRARY": {
    "constants": [
      { "name": "CONTEXT_PATH",          "default": "\"\"",  "role": "서버 base-URL prefix" },
      { "name": "SERVICE_URL",           "default": "\"\"",  "role": "실제 REST end-point(root) 하위 경로" },
      { "name": "DEFAULT_OPTIONS_MODE",  "default": "\"asynchronous\"", "role": "submit 기본 동기/비동기 모드" },
      { "name": "DEFAULT_OPTIONS_MEDIATYPE", "default": "\"application/json\"", "role": "submit 기본 Content-Type" },
      { "name": "IS_RESTFUL_URL",        "default": false,   "role": "GET 요청 시 Path-Variable/QueryString 자동 조립 여부" },
      {
        "name": "MESSAGE_CODE",
        "values": { "STATUS_ERROR": "E", "STATUS_SUCCESS": "S", "STATUS_WARNING": "W", "STATUS_INFO": "I" },
        "role": "서버 응답 상태코드 통일 enum"
      }
    ],

    "lifecycle-hooks": [
      { "name": "__preSubmitFunction(sbm)",    "when": "submit 직전",          "task": "CONTEXT_PATH prefix + RESTful URL 보정" },
      { "name": "__callbackSubmitFunction(res, sbm)", "when": "모든 submit 결과", "task": "Promise resolve/reject • 토스트/alert" },
      { "name": "__submitErrorHandler(res)",   "when": "HTTP 5xx 등 서버오류", "task": "표준 에러 팝업/토스트 처리" }
    ],

    "core helpers": [
      {
        "name": "__setActionParam(sbm)",
        "description": "GET & Path-Variable 지원\n1️⃣ /action/{id}/{seq} → 값 치환\n2️⃣ 남은 단일 필드를 QueryString 으로 부착"
      },
      {
        "name": "resultMsg(rsObj)",
        "description": "STATUS_ERROR/SUCCESS/INFO 에 따라 alert 또는 Toast 분기"
      }
    ],

    "public API": [
      {
        "name": "getContextPath()",
        "returns": "string",
        "description": "전역 CONTEXT_PATH 값"
      },
      {
        "name": "execute(sbm, reqData?, disableComp?) -> Promise",
        "description": "등록된 Submission 실행(비/동기 Promise 래핑)"
      },
      {
        "name": "executeDynamic(options, reqData?, disableComp?) -> Promise",
        "description": "옵션으로 즉석 Submission 생성 후 execute"
      },
      {
        "name": "executeWorkflow(workflowObj)",
        "description": "WebSquare workflow 실행"
      },
      {
        "name": "create(options)",
        "description": "Submission 동적 생성. options: id / ref / target / action / method / mode / mediatype …"
      },
      {
        "name": "setAction(sbm, action)",
        "description": "기존 Submission 의 action URL 교체"
      },
      {
        "name": "getResultCode(e) -> \"E|S|W|I\"",
        "description": "submit-callback e 객체에서 상태코드 추출"
      },
      {
        "name": "getStatusMessage(e) -> string",
        "description": "submit-callback e 객체에서 서버 메시지 추출"
      },
      {
        "name": "getServiceURL()",
        "description": "전역 SERVICE_URL 반환"
      },
      {
        "name": "getMessageCode(key) -> string",
        "description": "MESSAGE_CODE enum 값 조회"
      }
    ]
  }
}

```