

### DataCollection 템플릿

```json
{
  "dataCollection": {
    "searchMap": {
      "id": "dmp_{entityName}Search",
      "type": "DataMap", 
      "description": "검색 조건",
      "keys": [
        {"id": "pageIndex", "name": "페이지번호", "defaultValue": "1"},
        {"id": "pageSize", "name": "페이지사이즈", "defaultValue": "10"},
        {"id": "searchKeyword", "name": "검색어", "defaultValue": ""}
      ]
    },
    "listData": {
      "id": "dlt_{entityName}List",
      "type": "DataList",
      "description": "목록 데이터",
      "columns": [
        {"id": "id", "name": "ID", "dataType": "text"},
        {"id": "name", "name": "이름", "dataType": "text"},
        {"id": "regDate", "name": "등록일", "dataType": "text"}
      ]
    },
    "detailMap": {
      "id": "dmp_{entityName}Detail", 
      "type": "DataMap",
      "description": "상세 정보",
      "keys": [
        {"id": "id", "name": "ID"},
        {"id": "name", "name": "이름"},
        {"id": "description", "name": "설명"}
      ]
    }
  }
}
```

### Submission 템플릿

```json
{
  "submissions": {
    "select": {
      "id": "sbm_select{EntityName}List",
      "description": "목록 조회",
      "ref": "data:json,{\"id\":\"dmp_{entityName}Search\",\"key\":\"{entityName}Search\"}",
      "target": "data:json,{\"id\":\"dlt_{entityName}List\",\"key\":\"elData.{entityName}List\"}",
      "action": "/api/{entityName}/list",
      "method": "post",
      "mode": "asynchronous",
      "mediatype": "application/json",
      "callback": "scwin.sbm_{entityName}List_submitdone"
    },
    "insert": {
      "id": "sbm_insert{EntityName}",
      "description": "등록",
      "ref": "data:json,[\"dmp_{entityName}Detail\",{\"id\":\"dmp_{entityName}Detail\",\"key\":\"{entityName}\"}]",
      "target": "",
      "action": "/api/{entityName}/insert",
      "method": "post",
      "callback": "scwin.sbm_insert{EntityName}_submitdone"
    },
    "update": {
      "id": "sbm_update{EntityName}",
      "description": "수정",
      "ref": "data:json,[\"dmp_{entityName}Detail\",{\"id\":\"dmp_{entityName}Detail\",\"key\":\"{entityName}\"}]",
      "target": "",
      "action": "/api/{entityName}/update",
      "method": "post",
      "callback": "scwin.sbm_update{EntityName}_submitdone"
    },
    "delete": {
      "id": "sbm_delete{EntityName}",
      "description": "삭제",
      "ref": "data:json,{\"id\":\"dmp_{entityName}Detail\",\"key\":\"{entityName}\"}",
      "target": "",
      "action": "/api/{entityName}/delete",
      "method": "post",
      "callback": "scwin.sbm_delete{EntityName}_submitdone"
    }
  }
}
```

### 조회(Read) 패턴

```json
{
  "readPattern": {
    "dataFlow": [
      "검색조건 설정 → Submission 실행 → 서버 응답 → DataList 바인딩 → GridView 표시"
    ],
    "implementation": {
      "javascript": {
        "searchFunction": {
          "name": "btn_search_onclick",
          "code": "function() { $c.sbm.execute($p, sbm_select{EntityName}List); }"
        },
        "callback": {
          "name": "sbm_{entityName}List_submitdone", 
          "errorHandling": {
            "check": "elHeader == null || elHeader.resSuc == false",
            "action": "$c.win.alert(`에러: ${elHeader.resMsg}`);"
          },
          "successHandling": {
            "setCount": "spn_listCnt.setLabel(elData.totalCount);",
            "setData": "dlt_{entityName}List.setJSON(elData.{entityName}List);",
            "setPaging": "pgl_{entityName}List.setCount(totalPageCount);"
          }
        }
      },
      "uiBinding": {
        "gridView": {
          "dataList": "data:dlt_{entityName}List",
          "event": "ev:oncellclick=\"scwin.grd_list_oncellclick\""
        }
      }
    }
  }
}
```

### 등록(Create) 패턴

```json
{
  "createPattern": {
    "dataFlow": [
      "입력폼 작성 → 유효성 검사 → 확인창 → Submission 실행 → 성공메시지 → 목록 새로고침"
    ],
    "implementation": {
      "validation": {
        "function": "$c.data.validateGroup",
        "rules": [
          {"id": "name", "mandatory": true},
          {"id": "email", "mandatory": false, "validationFunc": "isEmail"}
        ]
      },
      "confirmDialog": {
        "function": "$c.win.confirm",
        "message": "저장하시겠습니까?",
        "callback": "result => { if(result) $c.sbm.execute(sbm_insert{EntityName}); }"
      },
      "callback": {
        "successMessage": "$c.win.alert('등록되었습니다.');",
        "refresh": "scwin.btn_search_onclick();"
      }
    }
  }
}
```

### 수정(Update) 패턴

```json
{
  "updatePattern": {
    "dataFlow": [
      "GridView 행 선택 → 상세정보 로드 → 수정폼 활성화 → 저장 → 목록 갱신"
    ],
    "implementation": {
      "rowSelection": {
        "event": "grd_list_oncellclick",
        "action": "dmp_{entityName}Detail.setJSON(dlt_{entityName}List.getRowJSON(row));"
      },
      "uiState": {
        "editMode": {
          "saveButton": "btn_save.hide();",
          "editButton": "btn_edit.show();",
          "deleteButton": "btn_delete.show();",
          "formReadOnly": "grp_{entityName}Info.setReadOnly(false);"
        }
      }
    }
  }
}
```

### 삭제(Delete) 패턴

```json
{
  "deletePattern": {
    "dataFlow": [
      "행 선택 → 삭제 확인 → Submission 실행 → 성공메시지 → 목록 새로고침"
    ],
    "implementation": {
      "confirmation": {
        "message": "삭제하시겠습니까?",
        "action": "$c.sbm.execute(sbm_delete{EntityName});"
      },
      "callback": {
        "successMessage": "$c.win.alert('삭제되었습니다.');",
        "refresh": "scwin.btn_search_onclick();"
      }
    }
  }
}
```

### Input 컴포넌트 바인딩

```json
{
  "inputBinding": {
    "textInput": {
      "xml": "<xf:input ref=\"data:dmp_{entityName}Detail.name\" style=\"width:100%;\"/>",
      "description": "DataMap의 name 키와 양방향 바인딩"
    },
    "selectBox": {
      "xml": "<xf:select1 ref=\"data:dmp_{entityName}Detail.category\"/>",
      "description": "선택값이 DataMap에 자동 저장"
    },
    "calendar": {
      "xml": "<w2:calendar ref=\"data:dmp_{entityName}Detail.regDate\"/>",
      "description": "날짜 선택시 DataMap에 자동 반영"
    }
  }
}
```

### GridView 바인딩

```json
{
  "gridBinding": {
    "dataSource": {
      "attribute": "dataList=\"data:dlt_{entityName}List\"",
      "description": "DataList와 연결하여 자동 행 생성"
    },
    "columnBinding": {
      "header": "<w2:column value=\"이름\" displayMode=\"label\"/>",
      "body": "<w2:column id=\"name\" inputType=\"text\" displayMode=\"label\"/>",
      "description": "컬럼 ID가 DataList의 컬럼과 매핑"
    },
    "events": {
      "cellClick": "ev:oncellclick=\"scwin.grd_list_oncellclick\"",
      "description": "행 클릭시 상세정보를 DataMap에 로드"
    }
  }
}
```

### 표준 응답 구조

```json
{
  "standardResponse": {
    "structure": {
      "elHeader": {
        "resSuc": "boolean - 성공여부",
        "resCode": "string - 응답코드", 
        "resMsg": "string - 응답메시지"
      },
      "elData": {
        "totalCount": "number - 전체건수",
        "{entityName}List": "array - 목록데이터",
        "{entityName}": "object - 단일데이터"
      }
    },
    "errorHandling": {
      "check": "if(elHeader == null || elHeader.resSuc == false)",
      "action": "$c.win.alert(`에러코드: ${elHeader.resCode}\\n에러메시지: ${elHeader.resMsg}`);"
    }
  }
}
```

### $c.sbm 활용 패턴

```json
{
  "modernPattern": {
    "description": "공통 라이브러리 활용한 개선된 패턴",
    "execution": {
      "basic": "$c.sbm.execute(sbm_{entityName}List)",
      "withCallback": "$c.sbm.execute(sbm_{entityName}List).then(result => { /* 처리 */ })"
    },
    "errorHandling": {
      "automatic": "라이브러리에서 자동 에러 처리",
      "custom": "필요시 추가 처리 로직 구현"
    }
  }
}
```

### 페이지 네비게이션

```json
{
  "pagingPattern": {
    "dataMap": {
      "pageIndex": "현재 페이지 번호",
      "pageSize": "페이지당 건수", 
      "totalPageCount": "전체 페이지 수"
    },
    "component": {
      "xml": "<w2:pageList id=\"pgl_{entityName}List\" ev:onclick=\"scwin.pgl_{entityName}List_onclick\"/>",
      "event": "function(idx) { dmp_{entityName}Search.set('pageIndex', idx); }"
    },
    "calculation": {
      "totalPages": "Math.ceil(elData.totalCount / dmp_{entityName}Search.get('pageSize'))",
      "update": "pgl_{entityName}List.setCount(totalPageCount);"
    }
  }
}
```

## 실제 구현 예시 (게시판)

### 전체 구조

```json
{
  "boardExample": {
    "dataCollection": {
      "searchMap": "dmp_boardSearch - 검색조건",
      "listData": "dlt_boardList - 게시글 목록",
      "detailMap": "dmp_boardDetail - 게시글 상세"
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
  }
}
```

## 7. 응용 팁

### 동적 DataCollection 생성

```json
{
  "dynamicCreation": {
    "dataList": "$c.data.createDataList('dlt_dynamic', ['id', 'name', 'value'])",
    "dataMap": "$c.data.createDataMap('dmp_dynamic', ['searchKey', 'searchValue'])"
  }
}
```

### 데이터 변경 감지

```json
{
  "changeDetection": {
    "setup": "$c.data.setChangeCheckedDc([dlt_boardList, dmp_boardDetail])",
    "check": "$c.data.isModified([dlt_boardList, dmp_boardDetail])"
  }
}
```

### 유효성 검사 고급 활용

```json
{
  "advancedValidation": {
    "groupValidation": {
      "function": "$c.data.validateGroup",
      "rules": [
        {"id": "title", "mandatory": true, "minByteLength": 5},
        {"id": "email", "mandatory": false, "validationFunc": "$c.str.isEmail"}
      ]
    },
    "gridValidation": {
      "function": "$c.data.validateGridView",
      "rules": [
        {"id": "productName", "mandatory": true},
        {"id": "price", "mandatory": true, "dataType": "number"}
      ]
    }
  }
}
```
