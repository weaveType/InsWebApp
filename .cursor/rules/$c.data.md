```
{
  "DATA_PREFIX": "dlt_commonCode",
  "COMMON_CODE_INFO": {
    "LABEL": "CODE_NM",
    "VALUE": "COM_CD",
    "FILED_ARR": ["GRP_CD", "COM_CD", "CODE_NM"]
  },
  "methods": [
    {
      "name": "getValResultMsg",
      "purpose": "값에 대한 유효성 검사 결과 메시지 생성",
      "signature": "getValResultMsg(valInfo, value, dataCollectionObj?, rowIndex?)",
      "returns": "msgInfo { msgType: \"1\"|\"2\", message: String }",
      "mainParams": {
        "valInfo": "검사 옵션 Object",
        "value": "검사 대상 값",
        "dataCollectionObj": "DataCollection (선택)",
        "rowIndex": "행 인덱스 (선택)"
      }
    },
    {
      "name": "setDownloadGridViewOption",
      "purpose": "GridView->Excel 다운로드 옵션 보정",
      "signature": "setDownloadGridViewOption(grdObj, options?)",
      "returns": "void",
      "mainParams": {
        "grdObj": "GridView 객체",
        "options": "엑셀 옵션(JSON) (선택)"
      }
    },
    {
      "name": "loadMessage",
      "purpose": "다국어 메시지 전역 캐시에 로드",
      "signature": "loadMessage()",
      "returns": "void"
    },
    {
      "name": "initChangeCheckedDc",
      "purpose": "화면 변경 감지용 DataCollection 리스트 초기화",
      "signature": "initChangeCheckedDc()",
      "returns": "void"
    },
    {
      "name": "setChangeCheckedDc",
      "purpose": "변경 여부 확인 대상 DataCollection 등록",
      "signature": "setChangeCheckedDc(dcObjArr)",
      "returns": "void",
      "mainParams": {
        "dcObjArr": "DataCollection 또는 배열"
      }
    },
    {
      "name": "getChangeCheckedMainFrame",
      "purpose": "변경 감지용 DataCollection 리스트를 가진 frame 반환",
      "signature": "getChangeCheckedMainFrame(scopeApi?)",
      "returns": "Object | null"
    },
    {
      "name": "setCommonCode",
      "purpose": "공통코드 로딩 및 컴포넌트 nodeSet 바인딩",
      "signature": "setCommonCode(codeOptions, callbackFunc?)",
      "returns": "void",
      "mainParams": {
        "codeOptions": "Array<{code, compID, useLocalCache?}>",
        "callbackFunc": "완료 콜백 (선택)"
      }
    },
    {
      "name": "getCommonCodeDataList",
      "purpose": "특정 코드그룹의 DataList 객체 반환",
      "signature": "getCommonCodeDataList(cdGrp)",
      "returns": "DataList"
    },
    {
      "name": "executeCommonCode",
      "purpose": "공통코드 조회 Submission 실행",
      "signature": "executeCommonCode()",
      "returns": "void"
    },
    {
      "name": "getParameter",
      "purpose": "window / URL 파라미터 조회",
      "signature": "getParameter(paramKey?, scopeObj?, scopeApi?)",
      "returns": "any"
    },
    {
      "name": "getColumnName",
      "purpose": "컴포넌트 바인딩 컬럼 표시명 반환",
      "signature": "getColumnName(comObj)",
      "returns": "String"
    },
    {
      "name": "getDataCollection",
      "purpose": "컴포넌트와 연계된 DataCollection 정보 반환",
      "signature": "getDataCollection(comObj)",
      "returns": "{runtimeDataCollectionId, dataCollectionId, columnId} | null"
    },
    {
      "name": "getMessage",
      "purpose": "공통 메시지 다국어 치환",
      "signature": "getMessage(msgId, ...replaces)",
      "returns": "String"
    },
    {
      "name": "isModified",
      "purpose": "DataCollection 변경 유무 확인",
      "signature": "isModified(dcObjArr)",
      "returns": "Boolean"
    },
    {
      "name": "downloadMultipleDataList",
      "purpose": "여러 DataList 엑셀 다운로드",
      "signature": "downloadMultipleDataList(options, infoArr?)",
      "returns": "file"
    },
    {
      "name": "downloadMultipleGridView",
      "purpose": "여러 GridView 엑셀 다운로드",
      "signature": "downloadMultipleGridView(options, infoArr?)",
      "returns": "file"
    },
    {
      "name": "downloadGridViewExcel",
      "purpose": "GridView 단일 엑셀 다운로드",
      "signature": "downloadGridViewExcel(grdObj, options?, infoArr?)",
      "returns": "file"
    },
    {
      "name": "downloadGridViewCSV",
      "purpose": "GridView 단일 CSV 다운로드",
      "signature": "downloadGridViewCSV(grdObj, options?)",
      "returns": "file"
    },
    {
      "name": "uploadGridViewExcel",
      "purpose": "엑셀 업로드 → GridView",
      "signature": "uploadGridViewExcel(grdObj, options?)",
      "returns": "void"
    },
    {
      "name": "uploadGridViewCSV",
      "purpose": "CSV 업로드 → GridView",
      "signature": "uploadGridViewCSV(grdObj, options?)",
      "returns": "void"
    },
    {
      "name": "validateGroup",
      "purpose": "그룹 컴포넌트 유효성 검사",
      "signature": "validateGroup(grpObj, valInfoArr?, tacObj?, tabId?)",
      "returns": "Boolean"
    },
    {
      "name": "validateGridView",
      "purpose": "GridView 입력 데이터 유효성 검사",
      "signature": "validateGridView(gridViewObj, valInfoArr, tacObj?, tabId?)",
      "returns": "Boolean"
    },
    {
      "name": "createDataList",
      "purpose": "DataList 동적 생성",
      "signature": "createDataList(dsId, colArr, typeArr?, options?)",
      "returns": "DataList"
    },
    {
      "name": "createDataMap",
      "purpose": "DataMap 동적 생성",
      "signature": "createDataMap(dsId, colArr, typeArr?, options?)",
      "returns": "DataMap"
    },
    {
      "name": "undoAll",
      "purpose": "DataList 전체 변경 취소",
      "signature": "undoAll(dltId)",
      "returns": "void"
    },
    {
      "name": "undoRow",
      "purpose": "선택(chk) Row 취소",
      "signature": "undoRow(dltId)",
      "returns": "void"
    },
    {
      "name": "undoGridView",
      "purpose": "GridView 데이터 전체 취소",
      "signature": "undoGridView(grdId)",
      "returns": "Promise<void>"
    },
    {
      "name": "deleteRow",
      "purpose": "선택(chk) Row 삭제",
      "signature": "deleteRow(dltId)",
      "returns": "void"
    },
    {
      "name": "insertRow",
      "purpose": "GridView 새 Row 추가",
      "signature": "insertRow(grdId)",
      "returns": "Number (rowIndex)"
    },
    {
      "name": "getMatchedJSON",
      "purpose": "조건식으로 DataList 검색",
      "signature": "getMatchedJSON(dataListObj, condArr)",
      "returns": "Array<rowJSON>"
    },
    {
      "name": "setUserData",
      "purpose": "컴포넌트 사용자 데이터 세팅",
      "signature": "setUserData(comObj, key, value)",
      "returns": "void"
    },
    {
      "name": "getUserData",
      "purpose": "컴포넌트 사용자 데이터 조회",
      "signature": "getUserData(comObj, key)",
      "returns": "any"
    },
    {
      "name": "createHolidayDataList",
      "purpose": "공휴일 DataList 생성 및 전역 바인딩",
      "signature": "createHolidayDataList()",
      "returns": "void"
    },
    {
      "name": "loadHoliday",
      "purpose": "공휴일 데이터 조회 → dlt_holiday",
      "signature": "loadHoliday()",
      "returns": "void"
    }
  ]
}

```