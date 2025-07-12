{
  "DataCollection_Submission_Binding": {
    "dataCollection": {
      "searchMap": {
        "id": "dmp_{entityName}Search",
        "type": "DataMap",
        "description": "검색 조건",
        "keys": [
          {
            "id": "pageIndex",
            "name": "페이지번호",
            "defaultValue": "1"
          },
          {
            "id": "pageSize",
            "name": "페이지사이즈",
            "defaultValue": "10"
          },
          {
            "id": "searchKeyword",
            "name": "검색어",
            "defaultValue": ""
          }
        ]
      },
      "listData": {
        "id": "dlt_{entityName}List",
        "type": "DataList",
        "description": "목록 데이터",
        "columns": [
          {
            "id": "id",
            "name": "ID",
            "dataType": "text"
          },
          {
            "id": "name",
            "name": "이름",
            "dataType": "text"
          },
          {
            "id": "regDate",
            "name": "등록일",
            "dataType": "text"
          }
        ]
      },
      "detailMap": {
        "id": "dmp_{entityName}Detail",
        "type": "DataMap",
        "description": "상세 정보",
        "keys": [
          {
            "id": "id",
            "name": "ID"
          },
          {
            "id": "name",
            "name": "이름"
          },
          {
            "id": "description",
            "name": "설명"
          }
        ]
      }
    },
    "submissions": {
      "select": {
        "id": "sbm_select{EntityName}List",
        "targetData": "dlt_{entityName}List",
        "callData": "dmp_{entityName}Search",
        "action": "biz/select{EntityName}List.do",
        "mediator": "biz",
        "type": "json",
        "method": "post",
        "mode": "asynchronous",
        "viewSource": "요청 URL: /biz/selectBoardList.do, 요청 데이터: dmp_boardSearch, 응답 데이터: dlt_boardList"
      },
      "view": {
        "id": "sbm_select{EntityName}",
        "targetData": "dmp_{entityName}Detail",
        "callData": "dmp_{entityName}Detail",
        "action": "biz/select{EntityName}.do",
        "mediator": "biz",
        "type": "json",
        "method": "post",
        "mode": "asynchronous",
        "viewSource": "요청 URL: /biz/selectBoard.do, 요청 데이터: dmp_boardDetail, 응답 데이터: dmp_boardDetail"
      },
      "insert": {
        "id": "sbm_insert{EntityName}",
        "callData": "dmp_{entityName}Detail",
        "action": "biz/insert{EntityName}.do",
        "mediator": "biz",
        "type": "json",
        "method": "post",
        "mode": "asynchronous",
        "viewSource": "요청 URL: /biz/insertBoard.do, 요청 데이터: dmp_boardDetail"
      },
      "update": {
        "id": "sbm_update{EntityName}",
        "callData": "dmp_{entityName}Detail",
        "action": "biz/update{EntityName}.do",
        "mediator": "biz",
        "type": "json",
        "method": "post",
        "mode": "asynchronous",
        "viewSource": "요청 URL: /biz/updateBoard.do, 요청 데이터: dmp_boardDetail"
      },
      "delete": {
        "id": "sbm_delete{EntityName}",
        "callData": "dmp_{entityName}Detail",
        "action": "biz/delete{EntityName}.do",
        "mediator": "biz",
        "type": "json",
        "method": "post",
        "mode": "asynchronous",
        "viewSource": "요청 URL: /biz/deleteBoard.do, 요청 데이터: dmp_boardDetail"
      }
    },
    "binding": {
      "dataList": {
        "id": "dlt_boardList",
        "columns": [
          {
            "id": "id",
            "name": "게시글 ID",
            "control": "게시글 목록 gridView의 'ID' 컬럼"
          },
          {
            "id": "name",
            "name": "게시글 제목",
            "control": "게시글 목록 gridView의 '제목' 컬럼"
          },
          {
            "id": "regDate",
            "name": "등록일",
            "control": "게시글 목록 gridView의 '등록일' 컬럼"
          }
        ]
      },
      "dataMap": {
        "id": "dmp_boardDetail",
        "keys": [
          {
            "id": "id",
            "name": "게시글 ID",
            "control": "게시글 상세 폼의 'ID' 입력 필드"
          },
          {
            "id": "name",
            "name": "게시글 제목",
            "control": "게시글 상세 폼의 '제목' 입력 필드"
          },
          {
            "id": "description",
            "name": "내용",
            "control": "게시글 상세 폼의 '내용' textarea"
          }
        ]
      }
    },
    "scenario": {
      "pageName": "게시판 기능 (Board CRUD)",
      "dataCollections": {
        "search": "dmp_boardSearch (검색 조건)",
        "list": "dlt_boardList (게시글 목록)",
        "detail": "dmp_boardDetail (게시글 상세)"
      },
      "submissions": {
        "list": "sbm_selectBoardList - 목록조회",
        "view": "sbm_selectBoard - 상세조회",
        "insert": "sbm_insertBoard - 등록",
        "update": "sbm_updateBoard - 수정",
        "delete": "sbm_deleteBoard - 삭제"
      },
      "uiComponents": {
        "searchForm": "검색 조건 입력",
        "gridView": "게시글 목록 표시",
        "detailForm": "게시글 상세/입력 폼",
        "pagination": "페이지 네비게이션"
      },
      "workflow": [
        "1. 페이지 로드시 목록 조회",
        "2. 검색시 조건에 따른 목록 갱신",
        "3. 행 클릭시 상세정보 로드",
        "4. 등록/수정/삭제 후 목록 새로고침"
      ]
    },
    "applicationTips": {
      "dynamicCreation": {
        "dataList": "$c.data.createDataList('dlt_dynamic', ['id', 'name', 'value'])",
        "dataMap": "$c.data.createDataMap('dmp_dynamic', ['searchKey', 'searchValue'])"
      },
      "changeDetection": {
        "setup": "$c.data.setChangeCheckedDc([dlt_boardList, dmp_boardDetail])",
        "check": "$c.data.isModified([dlt_boardList, dmp_boardDetail])"
      }
    }
  },
  "제너레이터": {
    "metadata": {
      "componentName": "generator",
      "version": "WebSquare.uiplugin.generator6.0_0.1248B.20250421.101646",
      "description": "특정 컴포넌트를 반복적으로 추가/삭제가 가능한 컴포넌트입니다. generator 컴포넌트의 하위에 정의된 영역이 반복되며 script를 통해 제어 가능합니다.",
      "category": "UI Plugin Component",
      "documentation": "PART III. Components - 23. generator"
    },
    "properties": [
      {
        "name": "class",
        "type": "String",
        "description": "HTML의 class 속성과 동일한 기능을 제공. CSS 파일이나 style 블럭에 정의한 여러 개의 class를 공백으로 구분하여 적용 가능.",
        "required": false
      },
      {
        "name": "id",
        "type": "String",
        "description": "컴포넌트 ID. 컴포넌트 ID는 전역 객체로 할당됨. 각 컴포넌트 ID를 통해 Script에서 해당 컴포넌트에 접근 가능.",
        "required": true
      },
      {
        "name": "tagname",
        "type": "String",
        "description": "사용할 태그를 지정. <div>가 아닌 다른 태그를 사용. 자식 태그(Element)를 가질 수 있는 태그를 정의.",
        "required": false
      },
      {
        "name": "userData1",
        "type": "String",
        "description": "사용자 정의 데이터 속성. getUserData('userData1') API를 통해 설정한 값을 얻어올 수 있음.",
        "required": false
      },
      {
        "name": "userData2",
        "type": "String",
        "description": "사용자 정의 데이터 속성. getUserData('userData2') API를 통해 설정한 값을 얻어올 수 있음.",
        "required": false
      },
      {
        "name": "userData3",
        "type": "String",
        "description": "사용자 정의 데이터 속성. getUserData('userData3') API를 통해 설정한 값을 얻어올 수 있음.",
        "required": false
      }
    ],
    "methods": {
      "generator_specific": [
        {
          "name": "getChild",
          "description": "컴포넌트의 ID와 해당 컴포넌트가 속해 있는 반복부의 index를 기반으로 컴포넌트 객체를 반환",
          "parameters": [
            {
              "name": "index",
              "type": "Number",
              "required": true,
              "description": "반환 받을 컴포넌트가 속해 있는 반복부의 인덱스 (보이는 순서대로)"
            },
            {
              "name": "id",
              "type": "String",
              "required": true,
              "description": "반환받을 컴포넌트의 초기 설정 ID"
            }
          ],
          "returnType": "Object",
          "returnDescription": "반복부에서 찾은 웹스퀘어 컴포넌트",
          "example": "var tmpChild = generator1.getChild(i, 'input1');"
        },
        {
          "name": "insertChild",
          "description": "index에 해당하는 위치에 반복부를 추가. index가 생략된 경우 마지막에 추가됨",
          "parameters": [
            {
              "name": "index",
              "type": "Number",
              "required": false,
              "description": "반복부를 추가할 위치"
            }
          ],
          "returnType": "Number",
          "returnDescription": "반복부가 생성된 순서. 인덱스와는 별개",
          "example": "generator1.insertChild(0); // 첫 번째 위치에 추가\ngenerator1.insertChild(); // 마지막에 추가"
        },
        {
          "name": "insertChildAsync",
          "description": "insertChild를 비동기로 수행하는 함수. generator 내부에 비동기로 생성되는 컴포넌트를 사용할 경우 insertChild 대신 이 API를 호출해야 함",
          "parameters": [
            {
              "name": "index",
              "type": "Number",
              "required": false,
              "description": "반복부를 추가할 위치"
            }
          ],
          "returnType": "Promise",
          "returnDescription": "반복부가 생성된 순서를 포함하는 Promise 객체 반환",
          "example": "var idx = await generator1.insertChildAsync(0);"
        },
        {
          "name": "removeChild",
          "description": "index에 해당하는 반복부를 삭제하며 index가 없을 경우 마지막 반복부를 삭제",
          "parameters": [
            {
              "name": "index",
              "type": "Number",
              "required": false,
              "description": "삭제할 반복부의 인덱스"
            }
          ],
          "returnType": "Number",
          "returnDescription": "삭제된 반복부의 생성 번호(인덱스 아님)가 담긴 배열",
          "example": "generator1.removeChild(tmpIdx);"
        },
        {
          "name": "removeAll",
          "description": "모든 반복부를 삭제",
          "parameters": [],
          "returnType": "void",
          "returnDescription": "없음",
          "example": "generator1.removeAll();"
        },
        {
          "name": "setChild",
          "description": "index에 해당하는 반복부의 내부 HTML을 설정",
          "parameters": [
            {
              "name": "index",
              "type": "Number",
              "required": true,
              "description": "HTML을 설정할 반복부의 인덱스"
            },
            {
              "name": "html",
              "type": "String",
              "required": true,
              "description": "설정할 HTML 문자열"
            }
          ],
          "returnType": "void",
          "returnDescription": "없음",
          "example": "generator1.setChild(0, '<div>새로운 내용</div>');"
        },
        {
          "name": "reset",
          "description": "generator의 모든 반복부 및 관련 데이터를 초기화",
          "parameters": [],
          "returnType": "void",
          "returnDescription": "없음",
          "example": "generator1.reset();"
        },
        {
          "name": "getLength",
          "description": "현재 생성된 반복부의 개수를 반환",
          "parameters": [],
          "returnType": "Number",
          "returnDescription": "생성된 반복부의 개수",
          "example": "var len = generator1.getLength();"
        }
      ],
      "common": [
        {
          "name": "addClass",
          "description": "컴포넌트에 class를 추가",
          "parameters": [
            {
              "name": "className",
              "type": "String",
              "required": true,
              "description": "추가할 class 이름"
            }
          ],
          "returnType": "void",
          "example": "generator1.addClass('myClass');"
        },
        {
          "name": "removeClass",
          "description": "컴포넌트에서 class를 제거",
          "parameters": [
            {
              "name": "className",
              "type": "String",
              "required": true,
              "description": "제거할 class 이름"
            }
          ],
          "returnType": "void",
          "example": "generator1.removeClass('myClass');"
        },
        {
          "name": "toggleClass",
          "description": "컴포넌트에 class를 토글 (있으면 제거, 없으면 추가)",
          "parameters": [
            {
              "name": "className",
              "type": "String",
              "required": true,
              "description": "토글할 class 이름"
            }
          ],
          "returnType": "void",
          "example": "generator1.toggleClass('myClass');"
        },
        {
          "name": "changeClass",
          "description": "컴포넌트의 class를 변경",
          "parameters": [
            {
              "name": "newClassName",
              "type": "String",
              "required": true,
              "description": "새로운 class 이름"
            }
          ],
          "returnType": "void",
          "example": "generator1.changeClass('newClass');"
        },
        {
          "name": "show",
          "description": "컴포넌트를 보이게 함",
          "parameters": [],
          "returnType": "void",
          "example": "generator1.show();"
        },
        {
          "name": "hide",
          "description": "컴포넌트를 숨김",
          "parameters": [],
          "returnType": "void",
          "example": "generator1.hide();"
        },
        {
          "name": "setUserData",
          "description": "사용자 정의 데이터를 설정",
          "parameters": [
            {
              "name": "key",
              "type": "String",
              "required": true,
              "description": "데이터 키 (userData1, userData2, userData3)"
            },
            {
              "name": "value",
              "type": "String",
              "required": true,
              "description": "설정할 값"
            }
          ],
          "returnType": "void",
          "example": "generator1.setUserData('userData1', 'value1');"
        },
        {
          "name": "getUserData",
          "description": "설정된 사용자 정의 데이터를 반환",
          "parameters": [
            {
              "name": "key",
              "type": "String",
              "required": true,
              "description": "데이터 키 (userData1, userData2, userData3)"
            }
          ],
          "returnType": "String",
          "example": "var data = generator1.getUserData('userData1');"
        }
      ]
    },
    "usage_patterns": {
      "data_binding": {
        "description": "generator의 반복부 내부에 DataMap/DataList의 데이터 바인딩 패턴",
        "code": "function bindDataToGenerator() {\n    var dataArray = [\"Item A\", \"Item B\", \"Item C\"];\n    for(var i = 0; i < dataArray.length; i++) {\n        generator1.insertChild(i);\n        var inputComponent = generator1.getChild(i, 'input1');\n        inputComponent.setValue(dataArray[i]);\n    }\n}"
      },
      "dynamic_creation": {
        "description": "외부 데이터에 따른 동적 반복부 생성 패턴",
        "code": "function createGeneratorFromArray(dataArray) {\n    for(var i = 0; i < dataArray.length; i++) {\n        generator1.insertChild(i);\n        var inputComponent = generator1.getChild(i, 'input1');\n        inputComponent.setValue(dataArray[i]);\n    }\n}"
      },
      "dynamic_removal": {
        "description": "조건에 따른 동적 반복부 제거 패턴",
        "code": "function removeEmptyRows() {\n    var length = generator1.getLength();\n    for(var i = length - 1; i >= 0; i--) {\n        var inputComponent = generator1.getChild(i, 'input1');\n        if(!inputComponent.getValue()) {\n            generator1.removeChild(i);\n        }\n    }\n}"
      },
      "validation_all_rows": {
        "description": "모든 반복부에 대한 유효성 검사 패턴",
        "code": "function validateAllRows() {\n    var length = generator1.getLength();\n    var isValid = true;\n    for(var i = 0; i < length; i++) {\n        var inputComponent = generator1.getChild(i, 'input1');\n        if(!inputComponent.getValue()) {\n            inputComponent.addClass('error');\n            isValid = false;\n        }\n    }\n    return isValid;\n}"
      }
    },
    "notes": {
      "config_requirements": [
        "class 관련 메소드(removeClass, changeClass, toggleClass)에서 컴포넌트 속성으로 직접 정의된 class를 조작하려면 먼저 해당 class를 컴포넌트의 style 속성에 정의해야 함."
      ]
    }
  },
  "개발표준_가이드": {
    "문서정보": {
      "제목": "온라인 어플리케이션 개발 명명 규칙",
      "설명": "ProWorks5 제품 기반 온라인 어플리케이션 개발 시 개발자가 준수해야 할 명명 규칙",
      "적용범위": "ProWorks5 제품의 필수 제약 사항 포함"
    },
    "목적": {
      "주요목표": "공통된 Naming Rule 적용을 통한 어플리케이션 가독성 및 유지보수 효율성 향상",
      "세부목적": [
        "표준화의 인식 확대",
        "개발의 용이성 제공",
        "프로그램 표준화 및 일관성 유지",
        "프로그램 품질 향상",
        "운영, 유지, 보수의 편의성 제공"
      ]
    },
    "명명규칙종류": {
      "PascalCasing": {
        "설명": "각 단어의 첫 자를 대문자로 사용하는 방법",
        "사용예": "BackColor",
        "추가예시": [
          "UserController",
          "CustomerService",
          "OrderVo",
          "ProductDAO"
        ]
      },
      "CamelCasing": {
        "설명": "첫 단어의 첫 자는 소문자, 다음 단어의 첫 자는 대문자 사용",
        "사용예": "backColor",
        "추가예시": [
          "userName",
          "customerName",
          "orderDate",
          "productCode"
        ]
      },
      "UpperCasing": {
        "설명": "모든 문자를 대문자로 사용하는 방법",
        "사용예": "CONSTANT_VALUE",
        "추가예시": [
          "DEFAULT_PAGE_SIZE",
          "MAX_FILE_SIZE"
        ]
      },
      "LowerCasing": {
        "설명": "모든 문자를 소문자로 사용하는 방법",
        "사용예": "tablename",
        "추가예시": [
          "itemcode",
          "description"
        ]
      }
    },
    "적용대상": {
      "패키지": {
        "규칙": "소문자로만 구성 (대분류.중분류.소분류)",
        "예시": [
          "com.inswave.elfw.sample.web",
          "com.inswave.elfw.sample.service",
          "com.inswave.elfw.sample.dao"
        ]
      },
      "클래스": {
        "규칙": "Pascal Casing",
        "예시": [
          "UserController",
          "UserListController",
          "UserService",
          "UserDAO",
          "UserVo",
          "UserListVo",
          "SampleUtil"
        ]
      },
      "메소드": {
        "규칙": "Camel Casing",
        "예시": [
          "getUserList",
          "insertUser",
          "updateUser",
          "deleteUser",
          "initData"
        ]
      },
      "변수": {
        "규칙": "Camel Casing",
        "예시": [
          "userName",
          "userAge",
          "searchKeyword",
          "pageIndex"
        ]
      },
      "상수": {
        "규칙": "Upper Casing + 언더스코어 조합",
        "예시": [
          "DEFAULT_PAGE_SIZE",
          "MAX_COUNT",
          "SYSTEM_ERROR_CODE"
        ]
      },
      "화면_파일": {
        "규칙": "소문자로만 구성 + .xml",
        "예시": [
          "userlist.xml",
          "userdetail.xml",
          "boardview.xml"
        ]
      },
      "데이터컬렉션": {
        "규칙": "dlt(DataList) / dmp(DataMap) + Entity명 + 역할 (Pascal)",
        "예시": [
          "dltUserList",
          "dmpUserDetail",
          "dmpSearchParam"
        ]
      },
      "서브미션": {
        "규칙": "sbm(Submission) + 요청명 + 역할 (Pascal)",
        "예시": [
          "sbmSearchUserList",
          "sbmInsertUser",
          "sbmDeleteUser"
        ]
      },
      "자바스크립트_변수_및_함수": {
        "규칙": "Camel Casing",
        "예시": [
          "initPage",
          "onGridClick",
          "validationCheck",
          "utilFunction"
        ]
      },
      "세션키": {
        "규칙": "대분류코드.중분류코드.소분류코드 (소문자)",
        "예시": [
          "nh.global.user.info",
          "nh.global.order.process",
          "nh.global.product.manage"
        ]
      },
      "SQLID": {
        "규칙": "처리종류 + 오브젝트명(Pascal)",
        "처리종류": {
          "insert": "입력처리",
          "update": "수정처리",
          "delete": "삭제처리",
          "select": "단건조회",
          "selectList": "다건조회",
          "selectListCount": "카운트 조회"
        },
        "예시": [
          "selectListLoan",
          "insertCustomer",
          "updateOrder",
          "deleteProduct",
          "selectEmployee",
          "selectListCountDepartment"
        ]
      }
    },
    "메시지파일": {
      "파일명": {
        "규칙": "message- + [대분류코드] + _ + [언어] + .properties",
        "위치": "src/main/resource/inswave/message",
        "구성요소": {
          "대분류코드": "패키지의 대분류 코드",
          "언어": "다국어 처리용 (기본: ko)"
        },
        "예시": [
          "message-loan_ko.properties",
          "message-customer_ko.properties",
          "message-order_en.properties",
          "message-product_ja.properties"
        ]
      },
      "메시지ID": {
        "규칙": "속성(1자리) + . + 대분류코드 + . + 중분류코드 + . + 소분류코드 + . + 메시지설명",
        "속성": {
          "c": "Common (공통)",
          "e": "Error (오류)",
          "i": "Information (정보)",
          "w": "Warning (경고)"
        },
        "예시": [
          "c.common.button.save",
          "e.user.login.fail",
          "i.product.regist.success",
          "w.order.cancel.confirm"
        ]
      }
    }
  },
  "ProWorks5_커스터마이징_확장_가이드": {
    "문서정보": {
      "제목": "ProWorks5 커스터마이징 확장 가이드",
      "설명": "프로젝트 상황에 맞춘 ProWorks5 커스터마이징 방법 및 적용 방법",
      "대상": "프레임워크 커스터마이징 담당자",
      "활용대상자": [
        "공통 개발자",
        "시스템 관리자",
        "프레임워크 담당자",
        "형상관리 담당자"
      ]
    },
    "프로젝트VO": {
      "개요": "ProWorks5에서 Controller 파라미터는 공통 VO(CommVO) 상속받은 VO 형태 사용",
      "제약사항": "CommVO는 수정 불가",
      "구조": {
        "프레임워크CommVO": {
          "클래스": "com.inswave.elfw.core.CommVO",
          "특징": "수정 불가한 프레임워크 기본 VO"
        },
        "프로젝트공통VO": {
          "상속": "com.inswave.elfw.core.CommVO",
          "용도": "프로젝트 표준 VO, 업무 VO에서 상속받아 사용"
        },
        "업무VO": {
          "상속": "프로젝트 공통 VO",
          "용도": "각 업무 개발자가 생성하는 VO"
        }
      },
      "예시": [
        "// 프로젝트 공통 VO",
        "public class ProjectCommVO extends CommVO {",
        "  private String projectCode;",
        "  private String systemId;",
        "  // getter/setter",
        "}",
        "// 업무 VO",
        "public class UserVo extends ProjectCommVO {",
        "  private String userId;",
        "  private String userName;",
        "  // getter/setter",
        "}"
      ]
    },
    "사용자헤더": {
      "정의": "세션에 저장될 항목으로 표준헤더에 해당하는 프로젝트별 특성 반영",
      "작성방법": {
        "상속클래스": "com.inswave.elfw.core.UserHeader",
        "설정방법": "프로젝트 표준헤더에 맞게 항목 정의",
        "FLD전문": "Fixed Length 전문 사용 시 'FLD전문사용' 체크 및 길이 정보 입력"
      },
      "설정": {
        "메뉴경로": "[운영관리] - [프로퍼티관리]",
        "프로퍼티키": "USER_HEADER_CLASS_NAME",
        "설정값": "작성된 사용자 헤더의 Full Class명"
      },
      "예시": [
        "public class ProjectUserHeader extends UserHeader {",
        "  private String userGroupNm;",
        "  private String deptCode;",
        "  private String authLevel;",
        "  // FLD 전문 사용 시 길이 설정",
        "  // @FLDLength(20)",
        "  private String sessionKey;",
        "  // getter/setter",
        "}"
      ]
    },
    "메시지핸들러": {
      "설명": "ProWorks5 프레임워크의 메시지 처리 로직 확장",
      "상속클래스": "com.inswave.elfw.base.MessageCallback",
      "주요메소드": {
        "setMessage": "메시지 설정 (코드, 유형, 값)",
        "setMessageResult": "메시지 결과 설정 (JSON 형식)"
      },
      "설정키": "EL_MESSAGECALLBACK_CLASS_NAME",
      "예시": [
        "public class ProjectMessageCallback extends MessageCallback {",
        "  @Override",
        "  public void setMessage(String messageCode, String messageType, String messageValue) {",
        "    // 프로젝트 특화 메시지 처리 로직",
        "  }",
        "  @Override",
        "  public void setMessageResult(JSONObject jsonObject) {",
        "    // JSON 결과에 메시지 추가",
        "  }",
        "}"
      ]
    },
    "트랜잭션모니터링": {
      "설명": "WAS별 트랜잭션 모니터링 연동",
      "구현메서드": "startTx, endTx, errorTx",
      "설정키": "EL_APM_ADAPTER_CLASS_NAME",
      "예시": [
        "public class ProjectApmAdapter implements ApmAdapter {",
        "  @Override",
        "  public void startTx(HttpServletRequest request, HttpServletResponse response) {",
        "    // 트랜잭션 시작 로직",
        "  }",
        "  @Override",
        "  public void endTx(HttpServletRequest request, HttpServletResponse response) {",
        "    // 트랜잭션 종료 로직",
        "  }",
        "  @Override",
        "  public void errorTx(HttpServletRequest request, HttpServletResponse response, Throwable throwable) {",
        "    // 트랜잭션 오류 처리 로직",
        "  }",
        "}"
      ]
    },
    "배치프로세스": {
      "설명": "배치 업무 파트별 배치 선/후에서 공통 처리",
      "상속클래스": "com.inswave.elfw.batch.process.ElBatchJobProcess",
      "설정위치": "각 업무 Shell의 BIZ_PRE_POST_PROCESS"
    },
    "배치파라미터": {
      "설명": "배치 파라미터의 특정값 반영을 위한 구현체",
      "상속클래스": "ElAbstractBatchParamValue",
      "구현메서드": "getParamValue",
      "설정키": "EL_BATCH_PARAM_VALUE_CLASS"
    },
    "형상관리연계": {
      "설명": "ProWorks5와 형상관리 시스템 연계",
      "제공API": "ScmHttpClientUtil",
      "적용대상": [
        "Java를 통한 class 컴파일",
        "Controller에 포함된 서비스 메타 정보 등록"
      ],
      "API파라미터": [
        "대상시스템 ElAdmin 서버 IP",
        "대상시스템 ElAdmin 서버 PORT",
        "대상시스템 ElAdmin Context명",
        "배포할 어플리케이션 ID",
        "배포할 소스 Path",
        "컴파일 포함 여부 (Y/N)"
      ],
      "실행예시": [
        "java -classpath /tmp/inswave-spring-1.0.jar:/tmp/javaee-api-7.0.jar",
        "com.inswave.elfw.util.ScmHttpClientUtil",
        "localhost 8088 ElAdmin TestApp"
      ]
    }
  },
  "ProWorks5_온라인_개발_가이드": {
    "문서정보": {
      "제목": "ProWorks5 온라인 개발 가이드",
      "설명": "ProWorks5 프레임워크를 이용한 시스템 구축 및 응용프로그램 개발 가이드",
      "대상": "서버 측 Service 개발자",
      "활용대상자": [
        "시스템 개발자",
        "시스템 설계자",
        "아키텍처 관리자"
      ]
    },
    "소개": {
      "기반": "Spring 프레임워크 기반 통합 개발 플랫폼",
      "제공기능": [
        "특화된 개발도구",
        "공통 서비스",
        "템플릿 생성 및 관리기능"
      ],
      "목적": "효과적인 개발 진행 지원"
    },
    "온라인아키텍처": {
      "구조": "3-Tier / 3-Layer 구조",
      "특징": [
        "유연한 아키텍처",
        "효율적 자원 사용",
        "고성능/안정성 제공"
      ],
      "개발영역": "Business Tier의 업무계 Layer 개발",
      "계층구조": {
        "Presentation_Tier": {
          "역할": "사용자 인터페이스 처리",
          "구성요소": [
            "웹 브라우저",
            "모바일 앱"
          ]
        },
        "Business_Tier": {
          "역할": "업무 로직 처리",
          "구성요소": [
            "Controller",
            "Service",
            "DAO"
          ],
          "개발대상": true
        },
        "Data_Tier": {
          "역할": "데이터 저장 및 관리",
          "구성요소": [
            "Database",
            "File System"
          ]
        }
      }
    },
    "개발구성요소": {
      "Controller": {
        "설명": "외부 호출의 최초 접점",
        "역할": [
          "입력 값 검증",
          "Service 호출 분기 처리"
        ],
        "예시": [
          "@Controller",
          "@RequestMapping(\"/api/user\")",
          "public class UserController {",
          "  @RequestMapping(\"/list\")",
          "  public String getUserList() {",
          "    // Service 호출",
          "  }",
          "}"
        ]
      },
      "ServiceInterface": {
        "설명": "업무 로직 구현체를 위한 인터페이스",
        "특징": "트랜잭션의 단위",
        "예시": [
          "public interface UserService {",
          "  List<UserVo> getUserList(UserVo userVo);",
          "  int insertUser(UserVo userVo);",
          "  int updateUser(UserVo userVo);",
          "  int deleteUser(UserVo userVo);",
          "}"
        ]
      },
      "Service": {
        "설명": "ServiceInterface의 구현체",
        "역할": [
          "비즈니스 로직 처리",
          "트랜잭션 제어",
          "DAO 호출"
        ],
        "예시": [
          "@Service(\"userService\")",
          "public class UserServiceImpl implements UserService {",
          "  @Resource(name=\"userDAO\")",
          "  private UserDAO userDAO;",
          "  @Override",
          "  public List<UserVo> getUserList(UserVo userVo) {",
          "    return userDAO.selectList(\"user.selectListUser\", userVo);",
          "  }",
          "}"
        ]
      },
      "DAO": {
        "설명": "DB 접근 객체",
        "역할": [
          "SQL 호출 및 결과 매핑",
          "MyBatis 기반 개발"
        ],
        "예시": [
          "@Repository(\"userDAO\")",
          "public class UserDAO extends ElAbstractDAO {",
          "  public List<UserVo> getUserList(UserVo userVo) {",
          "    return selectList(\"user.selectListUser\", userVo);",
          "  }",
          "  public int insertUser(UserVo userVo) {",
          "    return insert(\"user.insertUser\", userVo);",
          "  }",
          "  public int updateUser(UserVo userVo) {",
          "    return update(\"user.updateUser\", userVo);",
          "  }",
          "  public int deleteUser(UserVo userVo) {",
          "    return delete(\"user.deleteUser\", userVo);",
          "  }",
          "}"
        ]
      },
      "ServiceInterface작성": {
        "정의": "업무 로직 구현체를 위한 기능 정의",
        "특징": "트랜잭션의 단위",
        "생성방법": "service 패키지에서 인터페이스 생성",
        "예시코드": [
          "public interface UserService {",
          "  /**",
          "   * 사용자 목록 조회",
          "   * @param userVo 조회 조건",
          "   * @return 사용자 목록",
          "   */",
          "  List<UserVo> getUserList(UserVo userVo);",
          "  /**",
          "   * 사용자 등록",
          "   * @param userVo 사용자 정보",
          "   * @return 등록 건수",
          "   */",
          "  int insertUser(UserVo userVo);",
          "  /**",
          "   * 사용자 수정",
          "   * @param userVo 사용자 정보",
          "   * @return 수정 건수",
          "   */",
          "  int updateUser(UserVo userVo);",
          "  /**",
          "   * 사용자 삭제",
          "   * @param userVo 사용자 정보",
          "   * @return 삭제 건수",
          "   */",
          "  int deleteUser(UserVo userVo);",
          "}"
        ]
      }
    }
  },
  "웹_어드민_가이드": {
    "문서정보": {
      "제목": "ProWorks5 웹 어드민 가이드",
      "설명": "ProWorks5 프레임워크 시스템 전반 설정 제어를 위한 관리자 가이드",
      "대상": "어플리케이션 운영 관리자",
      "활용대상자": [
        "시스템 관리자",
        "시스템 개발자"
      ]
    },
    "소개": {
      "기반": "Spring 프레임워크 기반 통합 개발 플랫폼",
      "제공기능": [
        "특화된 개발도구",
        "공통 서비스",
        "템플릿 생성 및 관리기능",
        "웹 어드민 관리 기능"
      ],
      "목적": "효과적인 개발 및 운영 관리 지원"
    },
    "웹어드민로그인": {
      "접속정보": {
        "URL": "http://[개발서버IP]:[포트]/ElAdmin/websquare/websquare.html",
        "인증": "부여받은 개인 ProWorks5 계정"
      },
      "주요기능": [
        "대상 어플리케이션 선택",
        "프레임워크 Core 설정 제어",
        "개별 어플리케이션 프로퍼티 설정"
      ],
      "권한구분": {
        "프레임워크웹관리자": "모든 설정 제어 (일반 개발자 접근 제한)",
        "어플리케이션명": "개별 어플리케이션 설정"
      }
    },
    "사용자관리": {
      "사용자그룹관리": {
        "목적": "사용자 유형 구분을 위한 그룹 생성",
        "메뉴경로": "[사용자관리] - [사용자그룹관리]",
        "조회조건": {
          "사용자그룹명": "사용자그룹 이름",
          "페이지크기": "리스트 한 페이지 출력 개수"
        },
        "등록정보": {
          "사용자그룹명": "사용자그룹 이름",
          "메뉴그룹": "해당 사용자그룹이 접근할 수 있는 메뉴그룹"
        },
        "예시": [
          {
            "그룹명": "관리자 사용자 그룹",
            "메뉴그룹": "샘플 관리자 메뉴그룹",
            "설명": "관리자가 이용할 수 있는 메뉴그룹 지정"
          },
          {
            "그룹명": "개발자 사용자 그룹",
            "메뉴그룹": "샘플 개발자 메뉴그룹",
            "설명": "개발자가 이용할 수 있는 메뉴그룹 지정"
          }
        ]
      },
      "사용자관리": {
        "목적": "사용자그룹에 포함시킬 사용자 등록",
        "필수등록": "모든 프레임워크 관리자와 프로젝트 개발자",
        "웹어드민사용자등록": {
          "대상어플리케이션": "프레임워크 웹관리자",
          "메뉴경로": "[사용자관리] - [사용자관리]",
          "조회조건": {
            "ID": "사용자 아이디",
            "이름": "사용자 이름",
            "사용자그룹명": "사용자 유형에 따른 사용자그룹",
            "사용여부": "사용자 계정 활성화 여부"
          },
          "등록정보": {
            "ID": "사용자 아이디 (영문 대소문자, 숫자)",
            "이름": "사용자 이름",
            "패스워드": "사용자 비밀번호",
            "사용자그룹": "할당할 사용자그룹 (1개만 선택)",
            "이메일": "사용자 이메일",
            "연락처": "사용자 연락처",
            "설명": "사용자 계정에 대한 설명",
            "사용여부": "사용자 계정 활성화 여부"
          },
          "예시": [
            {
              "ID": "admin",
              "이름": "관리자",
              "사용자그룹": "관리자 사용자 그룹",
              "설명": "프레임워크 관리자 계정"
            },
            {
              "ID": "devuser",
              "이름": "개발자",
              "사용자그룹": "개발자 사용자 그룹",
              "설명": "프로젝트 개발자 계정"
            }
          ]
        },
        "어플리케이션별사용자등록": {
          "대상어플리케이션": "프로젝트 어플리케이션",
          "메뉴경로": "[사용자관리] - [사용자관리]",
          "특징": "사용자그룹은 변경 불가",
          "필수입력": "ID",
          "기본패스워드": "'0000'으로 자동 설정"
        }
      }
    },
    "메뉴관리": {
      "메뉴그룹관리": {
        "목적": "메뉴 접근 권한 부여를 위한 그룹 생성",
        "메뉴경로": "[메뉴관리] - [메뉴그룹관리]",
        "등록정보": {
          "메뉴그룹명": "메뉴그룹 이름"
        },
        "예시": [
          {
            "그룹명": "샘플 관리자 메뉴그룹",
            "설명": "관리자가 접근할 수 있는 메뉴"
          },
          {
            "그룹명": "샘플 개발자 메뉴그룹",
            "설명": "개발자가 접근할 수 있는 메뉴"
          }
        ]
      },
      "메뉴관리": {
        "목적": "메뉴그룹에 포함시킬 메뉴 등록",
        "메뉴경로": "[메뉴관리] - [메뉴관리]",
        "트리구성": "계층형 트리 구조로 메뉴 구성",
        "메뉴항목설정": {
          "메뉴유형": "화면, 외부링크, 그룹 중 선택",
          "메뉴그룹": "속할 메뉴그룹",
          "상위메뉴": "상위 메뉴 지정 (선택사항)",
          "메뉴명": "메뉴에 표시될 이름",
          "메뉴ID": "메뉴 고유 ID",
          "정렬순서": "메뉴 표시 순서",
          "화면URL": "메뉴 클릭 시 연결될 화면 URL (화면 유형 시 필수)",
          "사용여부": "메뉴 활성화 여부"
        },
        "예시": [
          {
            "메뉴명": "사용자 관리",
            "메뉴ID": "MENU_USER",
            "화면URL": "/ui/user/userList.xml",
            "메뉴그룹": "샘플 관리자 메뉴그룹"
          },
          {
            "메뉴명": "게시판",
            "메뉴ID": "MENU_BOARD",
            "화면URL": "/ui/board/boardList.xml",
            "메뉴그룹": "샘플 개발자 메뉴그룹"
          }
        ]
      }
    },
    "템플릿관리": {
      "목적": "웹 어드민을 통해 서비스 템플릿 생성 및 관리",
      "메뉴경로": "[템플릿관리] - [템플릿관리]",
      "생성정보": {
        "템플릿명": "생성될 템플릿의 이름",
        "템플릿설명": "템플릿에 대한 설명",
        "리스트조회서비스ID": {
          "설명": "List 조회를 위한 서비스 ID",
          "조건": "프로젝트 내 유일한 값",
          "예시": "Emp 입력 → EmpList (리스트 조회 서비스 ID명)"
        },
        "오브젝트명": {
          "설명": "생성 리소스 Prefix명",
          "조건": "프로젝트 내 유일한 값"
        },
        "공통VO명": {
          "설명": "VO의 공통 페이징 처리 정의 상위클래스",
          "용도": "해당 프로젝트 표준으로 정의된 공통 VO"
        },
        "추상DAO": {
          "설명": "DB 스키마 선택을 위한 추상 DAO 상속 객체"
        },
        "개발자명": "주석에 들어갈 개발자명",
        "테이블명설명": "테이블에 대한 설명"
      },
      "생성파일": [
        "SQL: 매핑구문이 작성된 SQL 매퍼",
        "Vo: 단건 데이터 I/O Vo 객체 소스코드",
        "ListVo: 다건 데이터 I/O ListVo 객체 소스코드",
        "DAO: DB 접근 객체 소스코드",
        "Service: 서비스 인터페이스 소스코드",
        "ServiceImpl: 서비스 인터페이스 구현체 소스코드",
        "Controller: 클라이언트 요청 처리 객체 소스코드"
      ]
    },
    "주요특징": [
      "Spring 프레임워크 기반 통합 관리 플랫폼",
      "웹 어드민을 통한 통합 설정 관리",
      "사용자/서비스/메뉴 권한 체계적 관리",
      "실시간 서버 캐시 리로드 지원",
      "프로퍼티 기반 다중 환경 설정"
    ]
  },
  "웹_서비스_가이드": {
    "문서정보": {
      "제목": "ProWorks5 웹서비스 가이드",
      "설명": "ProWorks5 프레임워크에서 웹서비스 이용을 위한 개발자 가이드",
      "대상": "웹서비스를 이용하고자 하는 개발자",
      "활용대상자": [
        "시스템 개발자"
      ]
    },
    "소개": {
      "기반": "ProWorks5 프레임워크 + CXF",
      "제공기능": [
        "SOAP Provider 기능",
        "CXF를 이용한 SOAP 개발",
        "웹서비스 서버/클라이언트 통합 지원"
      ],
      "목적": "효과적인 SOAP 개발 진행 지원"
    },
    "웹서비스서버설정": {
      "개요": "어노테이션과 스프링빈 설정파일을 통한 웹서비스 설정",
      "전제조건": "웹서비스 환경 구성 설정 완료",
      "설정절차": [
        {
          "순서": 1,
          "내용": "웹서비스 적용할 서비스 인터페이스 선택",
          "상세": "서버단 웹서비스 설정 대상 인터페이스 결정"
        },
        {
          "순서": 2,
          "내용": "서비스 인터페이스에 @WebService 어노테이션 추가",
          "상세": "인터페이스 명칭 위에 @WebService 어노테이션 입력"
        },
        {
          "순서": 3,
          "내용": "웹서비스 인터페이스 구현 서비스 선택",
          "상세": "어노테이션이 적용된 인터페이스 구현체 선택"
        },
        {
          "순서": 4,
          "내용": "서비스 구현체에 @WebService 어노테이션 설정",
          "상세": "endpointInterface 키와 값 추가 입력"
        },
        {
          "순서": 5,
          "내용": "스프링 설정 파일 구성",
          "상세": "context-webservice.xml 파일에 EndPoint 설정"
        },
        {
          "순서": 6,
          "내용": "웹서비스 호출 및 테스트",
          "상세": "테스트 클라이언트 또는 SOAP UI를 사용하여 서비스 테스트"
        }
      ],
      "예시": [
        "// UserService 인터페이스",
        "@WebService(targetNamespace = \"http://www.inswave.com/services\", serviceName = \"UserService\")",
        "public interface UserService {",
        "  List<UserVo> getUserList(@WebParam(name = \"userVo\") UserVo userVo);",
        "}",
        "// UserServiceImpl 구현체",
        "@Service(\"userService\")",
        "@WebService(endpointInterface = \"com.inswave.elfw.user.service.UserService\")",
        "public class UserServiceImpl implements UserService {",
        "  // ... 구현 내용 ...",
        "}",
        "// context-webservice.xml",
        "<beans xmlns=\"http://www.springframework.org/schema/beans\"",
        "  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"",
        "  xmlns:jaxws=\"http://cxf.apache.org/jaxws\"",
        "  xsi:schemaLocation=\"",
        "    http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd",
        "    http://cxf.apache.org/jaxws http://cxf.apache.org/schemas/jaxws.xsd\">",
        "  <jaxws:endpoint id=\"userServiceEndpoint\" implementor=\"com.inswave.elfw.user.service.impl.UserServiceImpl\" address=\"/UserService\" />",
        "</beans>"
      ]
    },
    "웹서비스클라이언트설정": {
      "개요": "스프링빈 설정파일을 통한 웹서비스 클라이언트 설정",
      "설정절차": [
        {
          "순서": 1,
          "내용": "WSDL 파일 확보",
          "상세": "호출할 웹서비스의 WSDL 파일을 제공받거나 URL을 통해 접근"
        },
        {
          "순서": 2,
          "내용": "CXF WSDL-to-Java 코드 생성",
          "상세": "WSDL 파일을 기반으로 클라이언트 스텁 코드 생성"
        },
        {
          "순서": 3,
          "내용": "스프링 설정 파일 구성",
          "상세": "context-webservice-client.xml 파일에 클라이언트 프록시 설정"
        },
        {
          "순서": 4,
          "내용": "웹서비스 호출",
          "상세": "생성된 서비스 인터페이스를 통해 웹서비스 호출"
        }
      ],
      "예시": [
        "// context-webservice-client.xml",
        "<beans xmlns=\"http://www.springframework.org/schema/beans\"",
        "  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"",
        "  xmlns:jaxws=\"http://cxf.apache.org/jaxws\"",
        "  xsi:schemaLocation=\"",
        "    http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd",
        "    http://cxf.apache.org/jaxws http://cxf.apache.org/schemas/jaxws.xsd\">",
        "  <jaxws:client id=\"userServiceClient\" serviceClass=\"com.inswave.elfw.user.service.UserService\" address=\"http://localhost:8080/ElAdmin/webservice/UserService\" />",
        "</beans>",
        "// 클라이언트 호출 예시",
        "@Service",
        "public class UserWebServiceClient {",
        "  @Resource(name=\"userServiceClient\")",
        "  private UserService userService;",
        "  public List<UserVo> callGetUserList(UserVo userVo) {",
        "    return userService.getUserList(userVo);",
        "  }",
        "}"
      ]
    },
    "예외처리": {
      "응답메시지형식": {
        "성공": "SUCCESS",
        "오류": [
          "VALIDATION_ERROR: 입력값 검증 오류",
          "BUSINESS_ERROR: 비즈니스 로직 오류",
          "SYSTEM_ERROR: 시스템 오류"
        ]
      },
      "에러정보": [
        "errorCode: 오류 코드",
        "errorMessage: 오류 메시지",
        "errorDetail: 상세 오류 정보"
      ],
      "예외처리패턴": [
        "try {",
        "  // 비즈니스 로직 수행",
        "  response.setResultCode(\"SUCCESS\");",
        "} catch (ValidationException e) {",
        "  response.setResultCode(\"VALIDATION_ERROR\");",
        "  response.setErrorMessage(e.getMessage());",
        "} catch (BusinessException e) {",
        "  response.setResultCode(\"BUSINESS_ERROR\");",
        "  response.setErrorMessage(e.getMessage());",
        "} catch (Exception e) {",
        "  response.setResultCode(\"SYSTEM_ERROR\");",
        "  response.setErrorMessage(\"시스템 오류가 발생했습니다.\");",
        "  logger.error(\"Unexpected error\", e);",
        "}"
      ]
    },
    "보안고려사항": [
      "HTTPS 사용으로 데이터 암호화",
      "인증/인가 메커니즘 적용",
      "입력값 검증 및 SQL Injection 방지",
      "적절한 로깅으로 보안 감사 지원",
      "민감한 데이터 마스킹 처리"
    ],
    "모니터링": {
      "로깅설정": [
        "웹서비스 호출 로그",
        "성능 측정 로그",
        "에러 발생 로그",
        "보안 관련 로그"
      ]
    }
  },
  "아키텍처_정의서": {
    "문서정보": {
      "제목": "ProWorks 프레임워크 구성",
      "설명": "어플리케이션 개발에 효율적인 개발도구 및 공통 기능을 제공하는 유연한 구조의 프레임워크",
      "특징": [
        "다양한 프로젝트 적용 용이성",
        "개발/운영 효과적 관리 기능",
        "고성능/안정성 보장"
      ]
    },
    "아키텍처": {
      "실행아키텍처": {
        "구조": "3-Tier / 3-Layer 구조",
        "특징": [
          "유연한 아키텍처",
          "효율적 자원 사용",
          "고성능/안정성 보장"
        ],
        "계층구조": {
          "Presentation": "사용자 인터페이스 계층",
          "Business": "비즈니스 로직 계층",
          "Data": "데이터 액세스 계층"
        }
      },
      "개발아키텍처": {
        "개발환경": "IDE를 통한 로컬 환경 개발",
        "주요기능": [
          "서버 템플릿 자동 생성",
          "프로젝트 활용 지원",
          "실시간 권한 및 설정 정보 반영"
        ]
      }
    },
    "개발도구지원": {
      "Eclipse_Plugin": {
        "제공형태": "Eclipse Add-On Plugin",
        "지원도구": [
          "Controller Editor",
          "Validator Editor",
          "전문 기반 테스트",
          "테스트 케이스 저장 및 활용"
        ],
        "주요기능": [
          "Resource Pool",
          "Resource Search",
          "Drag & Drop 소스 생성"
        ],
        "효과": "생산성 향상"
      },
      "템플릿자동생성": {
        "제공위치": "Admin",
        "기능": [
          "프로젝트 맞춤 템플릿 생성",
          "DB 테이블별 CRUD 템플릿 생성"
        ],
        "목적": "표준화된 소스 개발 지향",
        "예시": "테이블 정보로 DAO, Service, Controller, VO 자동 생성"
      },
      "실시간반영": {
        "대상": [
          "권한 정보",
          "시스템 설정"
        ],
        "장점": "WAS 재시작 없이 변경 사항 즉시 적용"
      }
    },
    "기반기술요소": {
      "웹프레임워크": {
        "명칭": "WebSquare5",
        "설명": "UI 개발 및 화면 연동",
        "특징": [
          "Single Page Application (SPA)",
          "DataCollection / Submission 기반 데이터 처리",
          "XML 기반 UI 구성"
        ]
      },
      "서버프레임워크": {
        "명칭": "ProWorks5",
        "설명": "비즈니스 로직 처리 및 데이터 연동",
        "기반": "Spring Framework",
        "핵심요소": [
          "Controller",
          "Service",
          "DAO"
        ]
      },
      "배치프레임워크": {
        "명칭": "Spring Batch",
        "설명": "대용량 데이터 일괄 처리",
        "활용": "ProWorks5 배치 확장 모듈"
      },
      "통합개발환경": {
        "명칭": "Eclipse IDE",
        "설명": "개발 생산성 향상 도구",
        "플러그인": "ProWorks Eclipse Plugin"
      }
    },
    "보안아키텍처": {
      "인증": {
        "설명": "사용자 신원 확인",
        "방법": [
          "ID/PW 기반 로그인",
          "SSO 연동"
        ],
        "구현": "ProWorks5 Security 모듈"
      },
      "인가": {
        "설명": "자원 접근 권한 확인",
        "방법": [
          "역할 기반 접근 제어 (RBAC)",
          "메뉴/서비스 권한 관리"
        ],
        "구현": "ProWorks5 Security 모듈"
      },
      "보안코딩": {
        "원칙": [
          "시큐어 코딩 가이드라인 준수",
          "OWASP Top 10 취약점 방어"
        ],
        "지원": "보안 취약점 점검 도구 연동"
      },
      "SQLInjection방어": {
        "처리방법": "PreparedStatement 사용",
        "예시": [
          "// 안전한 방식 (PreparedStatement)",
          "<select id=\"selectUser\">",
          "  SELECT * FROM users WHERE user_id = #{userId}",
          "</select>",
          "// 위험한 방식 (사용 금지)",
          "<select id=\"selectUser\">",
          "  SELECT * FROM users WHERE user_id = '${userId}'",
          "</select>"
        ]
      },
      "입력값검증": {
        "처리위치": "Server Side",
        "기반기술": "Jakarta Commons Validator",
        "처리구간": "El Interceptor",
        "응답방식": {
          "JSP": "Http Request Attribute 헤더 결과정보",
          "XML": "결과 XML 전문 헤더정보",
          "JSON": "결과 JSON 전문 헤더정보"
        },
        "Validation종류": {
          "필수여부체크": "Required",
          "길이체크": "MinLength, MaxLength",
          "데이터타입체크": [
            "short",
            "int",
            "long",
            "float",
            "double",
            "이메일",
            "한글",
            "영문",
            "패스워드",
            "주민번호"
          ],
          "범위체크": "Range, intRange, floatRange",
          "날짜형식체크": "날짜 포맷 지정",
          "특정형식체크": "Mask"
        },
        "IDE지원": "ProWorks IDE Editor를 통한 손쉬운 Validation 작성",
        "예시설정": [
          "// Validation 설정 예시",
          "@Valid",
          "public class UserVo {",
          "  @Required(message=\"사용자 ID는 필수입니다\")",
          "  @MinLength(value=5, message=\"최소 5자 이상 입력하세요\")",
          "  private String userId;",
          "}"
        ]
      }
    }
  },
  "배치개발가이드": {
    "문서정보": {
      "제목": "ProWorks5 프레임워크 배치 개발 가이드",
      "설명": "ProWorks5 프레임워크를 활용한 배치 개발 방법 및 구현 가이드",
      "활용대상자": [
        "시스템 관리자",
        "시스템 개발자"
      ]
    },
    "소개": {
      "개요": "Spring 배치 기반 아키텍처 위에 ProWorks5 특화 구조와 IO에디터 연계 Reader/Writer 제공",
      "특징": [
        "빠르고 구조화된 배치 개발 지원",
        "IO에디터와 연계된 Reader/Writer 제공",
        "Spring 배치 기반 아키텍처",
        "전용 Launcher 제공"
      ]
    },
    "배치아키텍처": {
      "배치작업정의": {
        "설명": "일괄처리를 위한 아키텍처",
        "특성": [
          "대량의 데이터 처리",
          "특정시간/데몬 형태 실행",
          "일괄적 처리"
        ]
      },
      "아키텍처구조": {
        "기반": "Spring 배치 아키텍처 + ProWorks 전용 Launcher",
        "동작방식": [
          "외부 스케줄러 → 업무Shell 호출",
          "Shell → 표준화된 실행 결과 리턴",
          "Job Invoke Shell을 통한 실행 환경 구성",
          "IO 에디터 작성 VO를 통한 ItemReader/Writer Mapper 기능"
        ],
        "특화기능": [
          "VO, FLD(Fixed Length), Delimiter 자동 변환",
          "표준화된 실행 결과 처리",
          "환경 변수 설정 분리"
        ]
      }
    },
    "구성요소": {
      "Spring배치기본요소": {
        "Job": {
          "설명": "배치 처리 과정을 하나의 단위로 정의",
          "구성": "Step들의 집합"
        },
        "Step": {
          "설명": "Job의 독립적인 순차적 페이즈",
          "유형": [
            "TaskletStep: 단일 작업 실행 (SQL, Shell)",
            "ChunkOrientedStep: ItemReader, ItemProcessor, ItemWriter 기반 청크 처리"
          ]
        },
        "ItemReader": {
          "설명": "데이터 읽기",
          "기능": "DB, File, XML 등 다양한 소스 지원"
        },
        "ItemProcessor": {
          "설명": "데이터 가공/변환",
          "옵션": "선택적 사용"
        },
        "ItemWriter": {
          "설명": "데이터 쓰기",
          "기능": "DB, File, XML 등 다양한 대상 지원"
        },
        "JobLauncher": {
          "설명": "Job 실행 및 관리",
          "역할": "JobParameter를 포함하여 Job 실행"
        },
        "JobRepository": {
          "설명": "Job 실행 메타데이터 저장",
          "활용": "Job 재시작, 상태 관리"
        }
      },
      "ProWorks5배치확장요소": {
        "BatchLauncher": {
          "설명": "배치 스케줄러와 연동하여 배치 Job을 실행하는 기능",
          "특징": "ProWorks5에 특화된 배치 Job 실행기",
          "기능": [
            "JobParameter 동적 구성",
            "환경 변수 주입",
            "표준화된 결과 리턴"
          ],
          "실행방법": "별도 Shell Script로 실행",
          "예시": "java -jar batch-launcher.jar myJob -param1 value1"
        },
        "BatchIOAdapter": {
          "설명": "IO 에디터로 작성된 VO를 사용하여 ItemReader/Writer 연동",
          "기능": "별도 개발 없이 데이터 매핑 자동화",
          "지원유형": [
            "VO to VO (DB to DB)",
            "FLD to VO (Fixed Length File to DB)",
            "VO to FLD (DB to Fixed Length File)",
            "DEL to VO (Delimiter File to DB)",
            "VO to DEL (DB to Delimiter File)"
          ]
        }
      }
    },
    "예시_Shell_Script": {
      "Db_To_Db_Basic": {
        "항목": "DB To DB (Basic)",
        "Shell": "01_Basic_DbToDbJob.sh",
        "설명": "가장 기본적인 DB to DB 배치"
      },
      "Fld_To_File": {
        "항목": "Fld To File (FLD)",
        "Shell": "01_Basic_FldToFldJob.sh",
        "설명": "FLD 파일에서 읽어서 FLD 파일로 기록"
      },
      "Header_Tail_Delimiter": {
        "항목": "Header/Tail (Delimiter)",
        "Shell": "02_HeadTail_1_DelToDelJob.sh",
        "설명": "Header/Tail 처리 포함, 헤더 라인수 설정 및 body 카운트로 Tail 시작 판단"
      },
      "Header_Tail_FLD": {
        "항목": "Header/Tail (FLD)",
        "Shell": "02_HeadTail_1_FldToFldJob.sh",
        "설명": "Header/Tail 처리 포함하는 FLD 예제"
      },
      "Header_Tail_Prefix": {
        "항목": "Header/Tail (Prefix)",
        "Shell": "02_HeadTail_2_DelToDelJob.sh",
        "설명": "라인의 Prefix를 통해 처리하는 Delimiter 예제"
      },
      "DB_To_DB_Parameter": {
        "항목": "DB To DB (Parameter)",
        "Shell": "03_Param_DbToDbJob.sh",
        "설명": "Shell로부터 파라미터를 전달받아 처리"
      },
      "Delimiter_To_DB": {
        "항목": "Delimiter To DB",
        "Shell": "04_HeadTail_DelToDbJob.sh",
        "설명": "헤더와 테일 처리를 포함하는 예제"
      },
      "DB_To_FLD_Multi_Step": {
        "항목": "DB To FLD (Multi Step)",
        "Shell": "05_MultiStep_DbToFldJob.sh",
        "설명": "다단계 Job 실행"
      }
    }
  },
  "PageFrame": {
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
        "description": "pageFrame별 개별 종료 스크립트. 문자열로 작성된 함수 실행문을 eval하여 실행합니다."
      },
      "style": {
        "type": "string",
        "description": "pageFrame에 적용할 CSS style을 지정합니다. CSS 속성을 세미콜론(;)으로 구분하여 적용할 수 있습니다."
      },
      "tabIndex": {
        "type": "string",
        "description": "Tab 키 이동 시 포커스를 받을 순서를 지정합니다."
      },
      "userData1": {
        "type": "string",
        "description": "사용자 정의 데이터 속성. getUserData('userData1') API를 통해 설정한 값을 얻어올 수 있습니다."
      },
      "userData2": {
        "type": "string",
        "description": "사용자 정의 데이터 속성. getUserData('userData2') API를 통해 설정한 값을 얻어올 수 있습니다."
      },
      "userData3": {
        "type": "string",
        "description": "사용자 정의 데이터 속성. getUserData('userData3') API를 통해 설정한 값을 얻어올 수 있습니다."
      },
      "wframeType": {
        "type": "string",
        "description": "wframe의 type 속성과 동일합니다. 생성될 pageFrame의 type을 지정합니다. (e.g., 'xml', 'html', 'img')"
      }
    },
    "events": {
      "onpageFrameLoad": {
        "description": "pageFrame의 내용(src로 지정된 화면) 로드가 완료된 후에 발생하는 이벤트입니다.",
        "parameters": {
          "data": {
            "type": "object",
            "description": "부가 정보. success (boolean): 로드 성공 여부, message (string): 오류 메시지 (성공 시 undefined)."
          }
        }
      }
    },
    "methods": {
      "setSrc": {
        "description": "pageFrame의 src 속성을 새로 설정하고 해당 화면을 로드합니다. wframe의 setSrc와 동일합니다.",
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
}