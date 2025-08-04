{
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
        "구성요소": ["웹 브라우저", "모바일 앱"]
      },
      "Business_Tier": {
        "역할": "업무 로직 처리",
        "구성요소": ["Controller", "Service", "DAO"],
        "개발대상": true
      },
      "Data_Tier": {
        "역할": "데이터 저장 및 관리",
        "구성요소": ["Database", "File System"]
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
    "ServiceImplement": {
      "설명": "업무 로직의 실제 구현체",
      "특징": "트랜잭션의 단위",
      "예시": [
        "@Service(\"userService\")",
        "@Transactional",
        "public class UserServiceImpl implements UserService {",
        "  @Autowired",
        "  private UserDAO userDAO;",
        "  ",
        "  public List<UserVo> getUserList(UserVo userVo) {",
        "    return userDAO.selectUserList(userVo);",
        "  }",
        "}"
      ]
    },
    "DAO": {
      "설명": "DB 데이터 접근 객체",
      "역할": "SQL Mapper 구문 호출",
      "예시": [
        "@Repository(\"userDAO\")",
        "public class UserDAO extends AbstractDAO {",
        "  public List<UserVo> selectUserList(UserVo userVo) {",
        "    return selectList(\"user.selectUserList\", userVo);",
        "  }",
        "}"
      ]
    },
    "VO": {
      "설명": "모듈 간 데이터 전달 객체",
      "타입": {
        "Vo": "단건 데이터 조회/조작 객체",
        "ListVo": "다건 데이터 조회/조작 객체"
      }
    },
    "SQLMap": {
      "설명": "MyBatis Mapper 리소스",
      "형식": "XML 기반",
      "역할": "파라미터와 결과 값 매핑 처리"
    }
  },

  "개발환경": {
    "특징": [
      "로컬 개발 가능",
      "개발 서버 자동 반영",
      "서비스 메타 정보 자동 등록"
    ],
    "제공도구": [
      "IDE 툴",
      "IO 개발 도구",
      "SQL 개발 도구",
      "업무 개발 도구",
      "관리자 설정 기능"
    ]
  },

  "스튜디오설치": {
    "설치방법": "InswaveToolSP1.zip 파일을 로컬 디스크에 압축 해제",
    "실행방법": "eclipse.exe 바로가기 클릭",
    "JDK": "스튜디오에 포함되어 별도 설치 불필요",
    
    "디렉토리구성": {
      "docs": "가이드 문서",
      "logs": "로컬 개발 컨텍스트 로그",
      "maven": "디펜던시 관리 메이븐 저장소",
      "tools": "이클립스, JDK, 톰캣, 테스트 도구(JMeter)",
      "workspace": "워크스페이스"
    },
    
    "기본환경": {
      "IDE": "Eclipse 기반 스튜디오",
      "Perspective": "WebSquare Developer"
    }
  },

  "SVN연동": {
    "연동절차": [
      "Open Perspective 버튼 클릭",
      "SVN Repository Exploring 선택",
      "New - Repository Location 실행",
      "SVN 저장소 정보 입력"
    ],
    "접속정보": {
      "URL": "svn://[개발서버IP]/[프로젝트명]",
      "User": "부여받은 개인 ProWorks5 아이디",
      "Password": "부여받은 개인 ProWorks5 패스워드"
    },
    "체크아웃": "프로젝트 폴더에서 Check Out 실행"
  },

  "디렉토리구조": {
    "기반": "Maven 프로젝트",
    "주요폴더": ["java", "resources", "webapp"],
    
    "src/main/java": {
      "설명": "자바 소스 위치 폴더"
    },
    "src/main/resources": {
      "설명": "어플리케이션 리소스 위치 폴더",
      "하위구조": {
        "message": "Exception 발생 시 메시지 정의 프로퍼티",
        "properties": "실행에 필요한 프로퍼티",
        "spring": "컨테이너 설정 환경설정 파일",
        "sqlmap": "MyBatis 매퍼파일 (자바 소스 패키지 구조와 동일)",
        "validator": "서비스 Validation 체크 설정 XML 파일"
      }
    },
    "src/main/webapp": {
      "설명": "웹 자원 위치 폴더",
      "하위구조": {
        "common": "공통 JSP 페이지",
        "css": "CSS 리소스",
        "images": "이미지 리소스",
        "js": "자바스크립트 리소스",
        "WEB-INF/lib": "폐쇄망 환경 고려 외부 자원 lib"
      }
    }
  },

  "개발절차": {
    "순서": [
      "CRUD 템플릿 생성",
      "VO 작성",
      "SQL Mapper 작성",
      "DAO 작성",
      "Service Interface 작성",
      "Service Implement 작성",
      "Controller 작성",
      "리소스 커밋",
      "테스트"
    ],
    "참고문서": "ProWorks5_표준개발_가이드.doc"
  },

  "CRUD템플릿생성": {
    "목적": "업무 DB 테이블의 CRUD 기능 일련의 개발 Object 구성",
    "특징": [
      "웹 어드민을 통한 완성된 컴포넌트 생성",
      "편의 기능으로 필수 절차 아님",
      "직접 Eclipse에서 개발 가능"
    ],
    
    "접속정보": {
      "URL": "http://[개발서버IP]:[포트]/ElAdmin/websquare/websquare.html",
      "메뉴": "기타 – CRUD 템플릿 생성"
    },
    
    "생성절차": [
      "DataSource Name 입력 및 DB종류 선택",
      "테이블 선택 및 검색 조건 필드 체크",
      "패키지명, 기본서비스명, 오브젝트명 등 정보 입력",
      "전체다운로드 선택하여 ZIP 파일 다운로드"
    ],
    
    "입력항목": {
      "패키지명": {
        "설명": "자바 소스 코드 생성 패키지명",
        "참고": "Java Package 명명 규칙 참조"
      },
      "기본서비스명": {
        "설명": "서비스 ID Prefix 제외한 나머지 서비스명",
        "예시": "Emp 입력 → EmpList (리스트 조회 서비스 ID명)"
      },
      "오브젝트명": {
        "설명": "생성 리소스 Prefix명, 프로젝트 내 유일값"
      },
      "공통VO명": {
        "설명": "프로젝트 정의 최상위 공통VO",
        "기본값": "nh.global.common.cmmn.CommonCommVO"
      },
      "추상DAO": {
        "설명": "프로젝트 정의 DB 접속 정보",
        "기본값": "nh.global.common.cmmn.dao.CommonDefaultAbstractDAO"
      },
      "개발자명": "주석에 들어갈 개발자명",
      "테이블명설명": "테이블에 대한 설명"
    },
    
    "생성파일": {
      "SQL": "매핑구문이 작성된 SQL 매퍼",
      "Vo": "단건 데이터 I/O Vo 객체 소스코드",
      "ListVo": "다건 데이터 I/O ListVo 객체 소스코드",
      "DAO": "DB 접근 객체 소스코드",
      "Service": "서비스 인터페이스 소스코드",
      "ServiceImpl": "서비스 인터페이스 구현체 소스코드",
      "Controller": "클라이언트 요청 처리 객체 소스코드"
    },
    
    "파일구조": {
      "java": "패키지명에 따른 폴더 (4 Depth 아래 dao, service, vo, web)",
      "resources": "sqlmap 아래 패키지 구조와 동일한 구조의 매퍼파일"
    }
  },

  "SQLMapper위저드": {
    "목적": "SQL 파라미터와 결과값 매핑을 위한 MyBatis 지원",
    "파일위치": "src/main/resource/inswave/sqlmap/",
    "namespace": "SQL Mapper 네임스페이스 규칙에 따라 생성",
    
    "파일생성": {
      "위저드": "eGovFrame mapper 위저드 사용",
      "파일명규칙": "_DB명_MyBatis.xml로 끝나야 함",
      "예시": "User_SQL_mssql_MyBatis.xml"
    },
    
    "Query추가": {
      "Add Select Query": "SELECT FROM SQL 명령어 상태로 추가",
      "Add Insert Query": "INSERT SQL 명령어 상태로 추가",
      "Add Update Query": "UPDATE Query 명령어 상태로 추가",
      "Add Delete Query": "DELETE Query 명령어 상태로 추가"
    },
    
    "SQL작성": {
      "Parameter": "Query 바인드 변수 값 데이터 타입 지정",
      "Result": "Query 실행결과 반환 데이터 타입 지정"
    },
    
    "SQL테스트": {
      "DB접속설정": "DataSource Explorer를 통한 DB Connection 추가",
      "Query실행": "Query 테스트 선택하여 실행 및 결과 확인"
    }
  },

  "VO작성": {
    "정의": "서비스, DB, 파일 접근을 위한 I/O 데이터",
    "타입": {
      "Vo": "단건 데이터용",
      "ListVo": "다건 데이터용"
    },
    "생성방법": "vo 패키지에서 New - Class 선택",
    
    "기본정보": {
      "논리명": "VO의 논리명(한글명)",
      "상위클래스명": {
        "설명": "공통 필드 정의된 상위클래스",
        "필수값": "nh.global.common.cmmn.CommonCommVO",
        "목적": "페이징 및 프로젝트 공통 항목 처리"
      },
      "FLD전문사용": "고정 길이 데이터(Fixed Length Data) 사용 여부",
      "Delimeter전문사용": "Delimeter 기반 VO 사용 여부 (배치 등에서 활용)"
    },
    
    "필드정의": {
      "선택": "필드 선택 영역",
      "논리명": "VO 필드의 논리명",
      "물리명": "VO 필드의 물리명",
      "타입": {
        "기본타입": ["String", "int", "long", "double", "BigInteger", "BigDecimal", "boolean"],
        "특수타입": ["Map", "byte[]", "MultipartFile"],
        "객체타입": ["Vo", "ListVo와 같은 Object 타입"]
      },
      "타입속성": "Object 타입 필드 시 활성화되는 Vo 또는 ListVo",
      "기본값": "필드의 기본 값",
      "암호화구분": {
        "IO": "네트워크 구간 암호화(서비스, 파일 접근 I/O 데이터)",
        "IO+DB": "네트워크 구간 + DB 저장 암호화(모든 I/O 데이터)",
        "DB": "DB 저장 암호화(DB 접근 I/O 데이터)"
      },
      "FLD전문": "고정길이 전문 포함 여부",
      "길이": "필드의 길이",
      "Del전문": "Delimeter 데이터 전문 매핑 여부",
      "설명": "필드에 대한 설명"
    },
    
    "필드길이정의": {
      "FLD전문": "각 필드의 길이 정보 정의 필요",
      "List타입": "Count 값을 가지는 필드가 VO 내부에 함께 존재해야 함"
    },
    
    "데이터타입별특성": {
      "String": {
        "길이": "제한없음",
        "특성": "설정 길이보다 작은 경우 뒤쪽에 스페이스 채움"
      },
      "int": {
        "길이": "10자리",
        "특성": "음수 시 맨 앞에 '-' 포함, 작은 경우 앞에 0 채움"
      },
      "long": {
        "길이": "19자리", 
        "특성": "부호비트 포함 19자리까지 표현 가능"
      },
      "double": {
        "길이": "17자리",
        "특성": "부호비트, 소수점 포함 17자리까지 표현"
      },
      "BigInteger": {
        "길이": "40자리",
        "특성": "부호비트 포함 40자리까지 표현 가능"
      },
      "BigDecimal": {
        "길이": "42자리",
        "특성": "부호비트, 소수점 포함 42자리까지 표현"
      }
    },
    
    "예시코드": [
      "public class UserVo extends CommonCommVO {",
      "  private String userId;    // 사용자 ID",
      "  private String userName;  // 사용자명",
      "  private String deptName;  // 부서명",
      "  private String email;     // 이메일",
      "  ",
      "  // Getter/Setter 자동 생성",
      "  public String getUserId() { return userId; }",
      "  public void setUserId(String userId) { this.userId = userId; }",
      "}"
    ]
  },

  "DAO작성": {
    "정의": "DB 데이터 접근 트랜잭션 객체",
    "역할": [
      "데이터 조회",
      "데이터 조작"
    ],
    "생성방법": "dao 패키지에서 클래스 생성",
    
    "어노테이션": {
      "필수": "@Repository",
      "값": "DAO 클래스명을 Camel Casing으로 입력",
      "예시": "@Repository(\"userDAO\")"
    },
    
    "메서드작성": {
      "규칙": "Namespace + \".\" + SQL ID 형태로 MyBatis 매퍼 호출",
      "예시코드": [
        "@Repository(\"userDAO\")",
        "public class UserDAO extends CommonDefaultAbstractDAO {",
        "  ",
        "  public List<UserVo> selectUserList(UserVo userVo) {",
        "    return selectList(\"user.selectUserList\", userVo);",
        "  }",
        "  ",
        "  public int insertUser(UserVo userVo) {",
        "    return insert(\"user.insertUser\", userVo);",
        "  }",
        "  ",
        "  public int updateUser(UserVo userVo) {",
        "    return update(\"user.updateUser\", userVo);",
        "  }",
        "  ",
        "  public int deleteUser(UserVo userVo) {",
        "    return delete(\"user.deleteUser\", userVo);",
        "  }",
        "}"
      ]
    }
  },

  "ServiceInterface작성": {
    "정의": "업무 로직 구현체를 위한 기능 정의",
    "특징": "트랜잭션의 단위",
    "생성방법": "service 패키지에서 인터페이스 생성",
    
    "예시코드": [
      "public interface UserService {",
      "  ",
      "  /**",
      "   * 사용자 목록 조회",
      "   * @param userVo 조회 조건",
      "   * @return 사용자 목록",
      "   */",
      "  List<UserVo> getUserList(UserVo userVo);",
      "  ",
      "  /**",
      "   * 사용자 등록",
      "   * @param userVo 사용자 정보",
      "   * @return 등록 건수",
      "   */",
      "  int insertUser(UserVo userVo);",
      "  ",
      "  /**",
      "   * 사용자 수정",
      "   * @param userVo 사용자 정보", 
      "   * @return 수정 건수",
      "   */",
      "  int updateUser(UserVo userVo);",
      "  ",
      "  /**",
      "   * 사용자 삭제",
      "   * @param userVo 사용자 정보",
      "   * @return 삭제 건수",
      "   */",
      "  int deleteUser(UserVo userVo);",
      "}"
    ]
  },

  "주요특징": [
    "Spring 프레임워크 기반 통합 개발 플랫폼",
    "3-Tier/3-Layer 아키텍처로 유연성 확보",
    "Eclipse 기반 통합 개발 환경",
    "CRUD 템플릿을 통한 자동 코드 생성",
    "웹 어드민을 통한 편리한 관리",
    "MyBatis 기반 SQL 매핑",
    "SVN 연동을 통한 형상 관리",
    "FLD/Delimiter 전문 처리 지원"
  ],

  "개발팁": [
    "CRUD 템플릿 활용으로 개발 시간 단축",
    "공통 VO 상속을 통한 표준화",
    "명명 규칙 준수로 일관성 유지",
    "SQL 테스트 기능 활용으로 품질 향상",
    "VO 에디터 활용으로 생산성 증대"
  ]
}