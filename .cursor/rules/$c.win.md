```
{
  "WIN_UTILS": {
    "constants": {
      "MESSAGE_IDX": 1,
      "CB_FUNCTION_MANAGER": {
        "cbFuncIdx": 0,
        "cbFuncSave": {}
      }
    },
    "methods": [
      {
        "name": "__getI18NUrl",
        "description": "다국어 리소스 URL 생성",
        "signature": "__getI18NUrl(xmlUrl)",
        "parameters": { "xmlUrl": "String (w2xPath 이하 경로)" },
        "returns": "String (I18N URL)"
      },
      {
        "name": "getScope",
        "description": "컴포넌트의 현재 $p Scope 반환",
        "signature": "getScope(comObj?)",
        "returns": "$p Scope | null"
      },
      {
        "name": "getActiveWindowInfo",
        "description": "현재 활성화된 업무창·팝업 정보",
        "signature": "getActiveWindowInfo(scopeApi?)",
        "returns": "{ type: 'P|T|M|S|W', window: Window, programCd: String }"
      },
      {
        "name": "showToastMessage",
        "description": "푸터 영역 토스트 메시지 표시",
        "signature": "showToastMessage(messageType, message)",
        "parameters": {
          "messageType": "STATUS_ERROR|STATUS_SUCCESS|STATUS_WARNING|STATUS_INFO",
          "message": "String"
        }
      },
      {
        "name": "openMenu",
        "description": "메뉴(업무 화면) 열기",
        "signature": "openMenu(menuNm, url, menuCode, paramObj?, option?)",
        "parameters": {
          "menuNm": "String",
          "url": "String",
          "menuCode": "String",
          "paramObj": "Object (전달 데이터)",
          "option": "{ menuType, closable, fixed, isHistory }"
        },
        "returns": "Promise<Boolean>"
      },
      {
        "name": "openPopup",
        "description": "팝업 열기 (Promise 지원)",
        "signature": "openPopup(url, opts?, data?)",
        "returns": "Promise<any>"
      },
      {
        "name": "closePopup",
        "description": "현재 팝업 닫기 & 부모콜백",
        "signature": "closePopup(callbackParam?, callbackFnStr?)"
      },
      {
        "name": "closeAllPopup",
        "description": "모든 팝업 닫기",
        "signature": "closeAllPopup()"
      },
      {
        "name": "isPopup",
        "description": "현재 화면이 팝업 여부",
        "signature": "isPopup()",
        "returns": "Boolean"
      },
      {
        "name": "messagBox",
        "description": "공통 메시지(alert/confirm) 팝업",
        "signature": "messagBox(messageType, messageStr, closeCallbackFncName?, opts?)"
      },
      {
        "name": "setProgramAuthority",
        "description": "사용자 권한에 따른 UI 버튼/컴포넌트 제어",
        "signature": "setProgramAuthority()"
      },
      {
        "name": "processCommonData",
        "description": "공통코드·권한 등 Submission workflow 실행",
        "signature": "processCommonData()"
      },
      {
        "name": "setHistoryBackEvent",
        "description": "브라우저 popstate 이벤트 등록",
        "signature": "setHistoryBackEvent()"
      },
      {
        "name": "pushState",
        "description": "history.pushState 로 화면 히스토리 저장",
        "signature": "pushState(data)"
      },
      {
        "name": "changePageState",
        "description": "popstate 발생 시 메뉴 재오픈",
        "signature": "changePageState()"
      },
      {
        "name": "addEventOnBeforeUnload",
        "description": "beforeunload 이벤트 핸들러 추가",
        "signature": "addEventOnBeforeUnload()"
      },
      {
        "name": "removeEventOnBeforeUnload",
        "description": "beforeunload 이벤트 제거",
        "signature": "removeEventOnBeforeUnload()"
      },
      {
        "name": "errorHandler",
        "description": "페이지 호출 404 등 오류 처리",
        "signature": "errorHandler(e)"
      },
      {
        "name": "reload",
        "description": "전체 페이지 강제 새로고침",
        "signature": "reload()"
      },
      {
        "name": "getProgramId",
        "description": "현재 화면 프로그램(스크린) ID 반환",
        "signature": "getProgramId(scopeApi?)",
        "returns": "String"
      },
      {
        "name": "goHome",
        "description": "홈(/) 이동",
        "signature": "goHome(menuCd?)"
      },
      {
        "name": "logout",
        "description": "세션 종료 및 홈 이동",
        "signature": "logout()"
      },
      {
        "name": "isAdmin",
        "description": "로그인 사용자가 시스템 관리자 여부",
        "signature": "isAdmin()",
        "returns": "Boolean"
      },
      {
        "name": "getFullPath",
        "description": "ContextPath 포함 URL 반환",
        "signature": "getFullPath(path)",
        "returns": "String"
      },
      {
        "name": "setEnterKeyEvent",
        "description": "그룹 내 컴포넌트 엔터 → 입력값 반영 & 함수 호출",
        "signature": "setEnterKeyEvent(grpObj, objFunc)"
      },
      {
        "name": "alert",
        "description": "Promise 기반 Alert 팝업",
        "signature": "alert(messageStr, closeCallbackFncName?, opts?)",
        "returns": "Promise<void>"
      },
      {
        "name": "confirm",
        "description": "Promise 기반 Confirm 팝업",
        "signature": "confirm(messageStr, closeCallbackFncName?, opts?)",
        "returns": "Promise<Boolean>"
      },
      {
        "name": "getLanguage",
        "description": "브라우저/쿠키 기준 2자리 언어코드",
        "signature": "getLanguage()",
        "returns": "String"
      },
      {
        "name": "getPopupId",
        "description": "현재 팝업 ID 반환",
        "signature": "getPopupId()",
        "returns": "String"
      },
      {
        "name": "moveUrl",
        "description": "현재 WFrame → 다른 XML 이동",
        "signature": "moveUrl(moveUrl, paramObj?)",
        "returns": "Promise"
      },
      {
        "name": "setWFrameSrc",
        "description": "특정 WFrame 의 src 변경",
        "signature": "setWFrameSrc(wframeObj, moveUrl, paramObj?)"
      },
      {
        "name": "getFrame",
        "description": "현재 스크립트가 속한 WFrame 객체",
        "signature": "getFrame(scopeApi?)",
        "returns": "WFrame | null"
      },
      {
        "name": "getParent",
        "description": "부모 WFrame 객체 반환",
        "signature": "getParent()",
        "returns": "WFrame"
      },
      {
        "name": "setLangCode",
        "description": "쿠키에 언어코드 저장",
        "signature": "setLangCode(langCode)"
      },
      {
        "name": "getLangCode",
        "description": "쿠키에 저장된 언어코드 반환",
        "signature": "getLangCode()",
        "returns": "String"
      },
      {
        "name": "getCbFunctionManager",
        "description": "메시지창 콜백함수 관리자 객체 반환",
        "signature": "getCbFunctionManager()",
        "returns": "Object"
      }
    ]
  }
}

```