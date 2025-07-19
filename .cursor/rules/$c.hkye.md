```
{
  "scwin": {
    "IS_USE_BROWSER_SHORTCUT": true,
    "onpageload": function () {},
    "init": {
      "description": "사용자 지정 단축키 기능을 초기화한다.",
      "hidden": false,
      "exception": false
    },
    "isUseShortCut": {
      "description": "단축키 사용 여부를 설정한다.",
      "params": {
        "shortcutFlag": "단축키 사용 여부 (Y: 사용 , N: 미사용)"
      },
      "hidden": false,
      "exception": false
    },
    "setEventPause": {
      "description": "컴포넌트에 설정된 이벤트를 중지시킨다.",
      "params": {
        "_targetComp": "설정 컴포넌트 객체ID",
        "_flag": "이벤트 설정 여부 값 [default: false(실행), true(중지)]",
        "_eventList": "중지 이벤트 리스트 값(배열) [default:null (모든 이벤트)]"
      },
      "hidden": false,
      "exception": false
    },
    "setShortKey": {
      "description": "단축키 데이터를 조회하기 위한 Submission을 생성한다.",
      "params": {
        "frame": "화면 scope정보가 담긴 객체"
      },
      "hidden": false,
      "exception": false
    },
    "setCkEditorShortKeyDownAction": {
      "description": "CkEditor4 (iframe) 내의 Document 객체에 keyDown 이벤트를 바인딩 시킨다.",
      "hidden": false,
      "exception": false
    },
    "loadingEvent": "Y",
    "__keydownEvent": {
      "description": "단축키 keydownEvent",
      "params": {
        "e": "이벤트 객체"
      },
      "hidden": true,
      "exception": false
    },
    "__checkEvent": {
      "description": "단축키를 이벤트를 확인한다.",
      "params": {
        "e": "이벤트 객체"
      },
      "hidden": true,
      "exception": false
    },
    "__runEvent": {
      "description": "단축키를 실행한다.",
      "params": {
        "complexKey": "복합키 정보 (alt, shift, ctrl)",
        "eventKey": "이벤트 키 값"
      },
      "returns": "boolean successFlag의 boolean 값",
      "hidden": true,
      "exception": false
    },
    "addEvent": {
      "description": "단축키를 추가한다.",
      "params": {
        "object": "단축키 추가 정보 객체"
      },
      "returns": "boolean successFlag의 boolean 값",
      "hidden": false,
      "exception": false
    },
    "keyToken": {
      "description": "키 토큰",
      "params": {
        "keyCode": "입력한 키의 Code 값"
      },
      "returns": "입력된 키의 복합키와 조합키 객체",
      "hidden": false,
      "exception": false
    },
    "delEvent": {
      "description": "단축키 등록 삭제 함수",
      "params": {
        "keyCode": "keyCode 값",
        "options": "options값이 들어있는 객체"
      },
      "returns": "boolean rtnValue 값, 삭제 여부",
      "hidden": false,
      "exception": false
    },
    "__isPreventKey": {
      "description": "단축키 실행을 막을 대상 Key인지 검사를 수행한다.",
      "params": {
        "complexKey": "복합키 값(alt, shift 등)",
        "lastKey": "마지막 키 값"
      },
      "returns": "boolean 단축키 실행을 막을 대상이면 false, 아닌 경우 true",
      "hidden": true,
      "exception": false
    },
    "__runGlobalEvent": {
      "description": "전역 단축키 실행 함수.",
      "params": {
        "complexKey": "복합키 (ctrlKey, altKey, ctrlAltKey, ctrlShiftKey, altShiftKey)",
        "eventKey": "이벤트 키 (F1, F2, F3, ..., a-z, 0-9, 특수문자 등)"
      },
      "hidden": true,
      "exception": false
    }
  }
}

```