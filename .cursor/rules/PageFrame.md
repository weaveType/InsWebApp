```
{
  "component": "pageFrame",
  "version": "6.0_0.1248B.20250421.101646",
  "description": "pageFrame 컴포넌트는 기본적으로 wframe과 동일한 기능과 API를 제공하면서, UDC 기능을 함께 사용할 수 있는 컴포넌트입니다.",
  "differences": {
    "from_wframe": "컴포넌트 명이 다르므로 config에서 설정 시 별도의 pageFrame 태그 안에 설정을 넣어야 하며, 이벤트 이름도 onwframe 대신 onpageFrame을 사용합니다.",
    "from_udc": "팔레트 등록을 하지 않고도 사용할 수 있고, $p.dynamicCreate를 통해 동적으로 생성할 수 있습니다."
  },
  "properties": {
    "class": {
      "type": "string",
      "description": "pageFrame에 적용할 CSS class를 지정합니다. 여러 개의 class를 공백으로 구분하여 적용할 수 있습니다."
    },
    "id": {
      "type": "string",
      "description": "컴포넌트 ID로, 전역 객체로 할당되어 Script에서 접근할 수 있습니다."
    },
    "initScript": {
      "type": "string",
      "description": "pageFrame별 개별 초기화 스크립트. 문자열로 작성된 함수 실행문을 eval하여 실행합니다."
    },
    "nextTabID": {
      "type": "string",
      "description": "Tab 키 이동 시 다음으로 포커스를 받을 컴포넌트의 ID입니다."
    },
    "postScript": {
      "type": "string",
      "description": "pageFrame별 개별 마무리 스크립트. 문자열로 작성된 함수 실행문을 eval하여 실행합니다."
    },
    "src": {
      "type": "string",
      "description": "pageFrame에 연결할 웹스퀘어 XML 또는 UDC 파일의 경로입니다."
    },
    "tabIndex": {
      "type": "integer",
      "description": "Tab 키를 이용해 포커스가 이동할 때의 순서를 지정합니다."
    },
    "tooltip": {
      "type": "string",
      "description": "컴포넌트에 마우스를 올렸을 때 표시되는 툴팁 텍스트입니다."
    },
    "tooltipLocaleRef": {
      "type": "string",
      "description": "다국어 적용 시 툴팁 문자열의 다국어 키입니다."
    },
    "userData1": {
      "type": "string",
      "description": "사용자 정의 데이터 속성 1입니다. getUserData(\"userData1\")로 추출할 수 있습니다."
    },
    "userData2": {
      "type": "string",
      "description": "사용자 정의 데이터 속성 2입니다. getUserData(\"userData2\")로 추출할 수 있습니다."
    },
    "userData3": {
      "type": "string",
      "description": "사용자 정의 데이터 속성 3입니다. getUserData(\"userData3\")로 추출할 수 있습니다."
    }
  },
  "events": {
    "onbeforepageframeunload": {
      "description": "pageFrame이 setSrc에 의해 전환되거나 닫히며 제거되기 직전에 발생하는 이벤트입니다.",
      "configExample": "<pageFrame><ev:onbeforepageframeunload value=\"com.clearPage\"/></pageFrame>"
    },
    "onpageframeload": {
      "description": "pageFrame 내부 영역이 모두 렌더링된 후 발생하는 이벤트입니다."
    }
  },
  "methods": {
    "setSrc": {
      "description": "src 속성을 설정하고 해당 페이지를 렌더링합니다. 비동기 함수이므로 await를 사용해야 합니다.",
      "parameters": {
        "src": {
          "type": "string",
          "required": true,
          "description": "새로 설정할 pageFrame의 src 값"
        },
        "options": {
          "type": "object",
          "required": false,
          "description": "dataObject 등 페이지에 전달할 추가 옵션"
        }
      },
      "returns": {
        "type": "Promise<boolean>",
        "description": "setSrc 성공 여부를 포함하는 Promise 객체를 반환합니다."
      }
    }
  },
  "utilities": {
    "$p.parent": {
      "description": "호출한 화면의 부모 페이지 Scope 객체를 반환합니다.",
      "behavior": {
        "default": "부모 페이지 Scope 객체를 반환합니다.",
        "iframe_or_browser_popup": "팝업 유형이 'iframePopup' 또는 'browserPopup'일 경우, 팝업을 호출한 화면의 최상위 페이지 Scope 객체를 반환합니다.",
        "non_scoped_wframe": "호출하는 화면의 WFrame의 scope 속성이 true가 아니면 부모 화면의 최상위 페이지 Scope 객체를 반환합니다."
      },
      "returns": "Object - 전역 Window 객체 또는 WFrame Scope 객체",
      "since": "5.0_3.3377A.20181128.161740",
      "samples": [
        "$p.parent().ibx_exam1.setValue(\"Example\");",
        "$p.parent().scwin.getTopPageInfo();"
      ]
    }
  }
}

```







페이지 이동방식 
1. 
   scwin.weaveTypeLogo_onclick = function () {

    pf_main.setSrc("파일명.xml");

};
 2. try {

        if ($p.parent().pf_main) {

            $p.parent().pf_main.setSrc("../폴더명/파일명.xml");

        } else {

            console.warn("부모 페이지에 pf_main이 없습니다.");

            $p.url("../폴더명/파일명.xml");

        }

    } catch (e) {

        console.error("페이지 이동 중 오류:", e);

        $p.url("..../폴더명/파일명.xml");

    }