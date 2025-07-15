{
  "proWorksWebSquareDevelopmentSuite": {
    "frameworkArchitecture": {
      "architectureDefinition": {
        "documentInfo": {
          "title": "ProWorks 프레임워크 구성",
          "description": "어플리케이션 개발에 효율적인 개발도구 및 공통 기능을 제공하는 유연한 구조의 프레임워크",
          "features": [
            "다양한 프로젝트 적용 용이성",
            "개발/운영 효과적 관리 기능",
            "고성능/안정성 보장"
          ]
        },
        "architecture": {
          "executionArchitecture": {
            "structure": "3-Tier / 3-Layer 구조",
            "features": [
              "유연한 아키텍처",
              "효율적 자원 사용",
              "고성능/안정성 보장"
            ],
            "layers": {
              "Presentation": "사용자 인터페이스 계층",
              "Business": "비즈니스 로직 계층",
              "Data": "데이터 액세스 계층"
            }
          },
          "developmentArchitecture": {
            "environment": "IDE를 통한 로컬 환경 개발",
            "mainFeatures": [
              "서버 템플릿 자동 생성",
              "프로젝트 활용 지원",
              "실시간 권한 및 설정 정보 반영"
            ]
          }
        },
        "developmentToolSupport": {
          "eclipsePlugin": {
            "format": "Eclipse Add-On Plugin",
            "tools": [
              "Controller Editor",
              "Validator Editor",
              "전문 기반 테스트",
              "테스트 케이스 저장 및 활용"
            ],
            "mainFeatures": [
              "Resource Pool",
              "Resource Search",
              "Drag & Drop 소스 생성"
            ],
            "effect": "생산성 향상"
          },
          "templateAutoGeneration": {
            "location": "Admin",
            "functions": [
              "프로젝트 맞춤 템플릿 생성",
              "DB 테이블별 CRUD 템플릿 생성"
            ],
            "purpose": "표준화된 소스 개발 지향",
            "example": "테이블 정보로 DAO, Service, Controller, VO 자동 생성"
          },
          "realtimeReflection": {
            "targets": [
              "권한 정보",
              "시스템 설정"
            ],
            "advantage": "WAS 재시작 없이 변경 사항 즉시 적용"
          }
        },
        "underlyingTechnologies": {
          "webFramework": {
            "name": "WebSquare5",
            "description": "UI 개발 및 화면 연동",
            "features": [
              "Single Page Application (SPA)",
              "DataCollection / Submission 기반 데이터 처리",
              "XML 기반 UI 구성"
            ]
          },
          "serverFramework": {
            "name": "ProWorks5",
            "description": "비즈니스 로직 처리 및 데이터 연동",
            "base": "Spring Framework",
            "coreComponents": [
              "Controller",
              "Service",
              "DAO"
            ]
          },
          "batchFramework": {
            "name": "Spring Batch",
            "description": "대용량 데이터 일괄 처리",
            "usage": "ProWorks5 배치 확장 모듈"
          },
          "ide": {
            "name": "Eclipse IDE",
            "description": "개발 생산성 향상 도구",
            "plugin": "ProWorks Eclipse Plugin"
          }
        },
        "securityArchitecture": {
          "authentication": {
            "description": "사용자 신원 확인",
            "methods": [
              "ID/PW 기반 로그인",
              "SSO 연동"
            ],
            "implementation": "ProWorks5 Security 모듈"
          },
          "authorization": {
            "description": "자원 접근 권한 확인",
            "methods": [
              "역할 기반 접근 제어 (RBAC)",
              "메뉴/서비스 권한 관리"
            ],
            "implementation": "ProWorks5 Security 모듈"
          },
          "secureCoding": {
            "principles": [
              "시큐어 코딩 가이드라인 준수",
              "OWASP Top 10 취약점 방어"
            ],
            "support": "보안 취약점 점검 도구 연동"
          },
          "sqlInjectionDefense": {
            "method": "PreparedStatement 사용",
            "examples": {
              "safe": "<select id=\"selectUser\">\n  SELECT * FROM users WHERE user_id = #{userId}\n</select>",
              "unsafe": "<select id=\"selectUser\">\n  SELECT * FROM users WHERE user_id = '${userId}'\n</select>"
            }
          },
          "inputValidation": {
            "location": "Server Side",
            "technology": "Jakarta Commons Validator",
            "processingLayer": "El Interceptor",
            "responseFormat": {
              "JSP": "Http Request Attribute 헤더 결과정보",
              "XML": "결과 XML 전문 헤더정보",
              "JSON": "결과 JSON 전문 헤더정보"
            },
            "validationTypes": {
              "required": "Required",
              "length": "MinLength, MaxLength",
              "dataType": [
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
              "range": "Range, intRange, floatRange",
              "date": "날짜 포맷 지정",
              "format": "Mask"
            },
            "ideSupport": "ProWorks IDE Editor를 통한 손쉬운 Validation 작성",
            "exampleConfig": "@Valid\npublic class UserVo {\n  @Required(message=\"사용자 ID는 필수입니다\")\n  @MinLength(value=5, message=\"최소 5자 이상 입력하세요\")\n  private String userId;\n}"
          }
        }
      }
    },
    "developmentGuides": {
      "onlineGuide": {
        "documentInfo": {
          "title": "ProWorks5 온라인 개발 가이드",
          "description": "ProWorks5 프레임워크를 이용한 시스템 구축 및 응용프로그램 개발 가이드",
          "targetAudience": "서버 측 Service 개발자",
          "users": [
            "시스템 개발자",
            "시스템 설계자",
            "아키텍처 관리자"
          ]
        },
        "introduction": {
          "base": "Spring 프레임워크 기반 통합 개발 플랫폼",
          "features": [
            "특화된 개발도구",
            "공통 서비스",
            "템플릿 생성 및 관리기능"
          ],
          "purpose": "효과적인 개발 진행 지원"
        },
        "onlineArchitecture": {
          "structure": "3-Tier / 3-Layer 구조",
          "features": [
            "유연한 아키텍처",
            "효율적 자원 사용",
            "고성능/안정성 제공"
          ],
          "developmentScope": "Business Tier의 업무계 Layer 개발",
          "layers": {
            "Presentation_Tier": {
              "role": "사용자 인터페이스 처리",
              "components": [
                "웹 브라우저",
                "모바일 앱"
              ]
            },
            "Business_Tier": {
              "role": "업무 로직 처리",
              "components": [
                "Controller",
                "Service",
                "DAO"
              ],
              "isDevelopmentTarget": true
            },
            "Data_Tier": {
              "role": "데이터 저장 및 관리",
              "components": [
                "Database",
                "File System"
              ]
            }
          }
        },
        "developmentComponents": {
          "Controller": {
            "description": "외부 호출의 최초 접점",
            "roles": [
              "입력 값 검증",
              "Service 호출 분기 처리"
            ],
            "example": "@Controller\n@RequestMapping(\"/api/user\")\npublic class UserController {\n  @RequestMapping(\"/list\")\n  public String getUserList() {\n    // Service 호출\n  }\n}"
          },
          "ServiceInterface": {
            "description": "업무 로직 구현체를 위한 인터페이스",
            "feature": "트랜잭션의 단위",
            "example": "public interface UserService {\n  List<UserVo> getUserList(UserVo userVo);\n  int insertUser(UserVo userVo);\n  int updateUser(UserVo userVo);\n  int deleteUser(UserVo userVo);\n}"
          },
          "Service": {
            "description": "ServiceInterface의 구현체",
            "roles": [
              "비즈니스 로직 처리",
              "트랜잭션 제어",
              "DAO 호출"
            ],
            "example": "@Service(\"userService\")\npublic class UserServiceImpl implements UserService {\n  @Resource(name=\"userDAO\")\n  private UserDAO userDAO;\n  @Override\n  public List<UserVo> getUserList(UserVo userVo) {\n    return userDAO.selectList(\"user.selectListUser\", userVo);\n  }\n}"
          },
          "DAO": {
            "description": "DB 접근 객체",
            "roles": [
              "SQL 호출 및 결과 매핑",
              "MyBatis 기반 개발"
            ],
            "example": "@Repository(\"userDAO\")\npublic class UserDAO extends ElAbstractDAO {\n  public List<UserVo> getUserList(UserVo userVo) {\n    return selectList(\"user.selectListUser\", userVo);\n  }\n  // ... other methods\n}"
          }
        }
      },
      "batchGuide": {
        "documentInfo": {
          "title": "ProWorks5 프레임워크 배치 개발 가이드",
          "description": "ProWorks5 프레임워크를 활용한 배치 개발 방법 및 구현 가이드",
          "users": [
            "시스템 관리자",
            "시스템 개발자"
          ]
        },
        "introduction": {
          "overview": "Spring 배치 기반 아키텍처 위에 ProWorks5 특화 구조와 IO에디터 연계 Reader/Writer 제공",
          "features": [
            "빠르고 구조화된 배치 개발 지원",
            "IO에디터와 연계된 Reader/Writer 제공",
            "Spring 배치 기반 아키텍처",
            "전용 Launcher 제공"
          ]
        },
        "batchArchitecture": {
          "jobDefinition": {
            "description": "일괄처리를 위한 아키텍처",
            "characteristics": [
              "대량의 데이터 처리",
              "특정시간/데몬 형태 실행",
              "일괄적 처리"
            ]
          },
          "architectureStructure": {
            "base": "Spring 배치 아키텍처 + ProWorks 전용 Launcher",
            "operationFlow": [
              "외부 스케줄러 → 업무Shell 호출",
              "Shell → 표준화된 실행 결과 리턴",
              "Job Invoke Shell을 통한 실행 환경 구성",
              "IO 에디터 작성 VO를 통한 ItemReader/Writer Mapper 기능"
            ],
            "specializedFunctions": [
              "VO, FLD(Fixed Length), Delimiter 자동 변환",
              "표준화된 실행 결과 처리",
              "환경 변수 설정 분리"
            ]
          }
        },
        "components": {
          "springBatchElements": {
            "Job": {
              "description": "배치 처리 과정을 하나의 단위로 정의",
              "composition": "Step들의 집합"
            },
            "Step": {
              "description": "Job의 독립적인 순차적 페이즈",
              "types": [
                "TaskletStep: 단일 작업 실행 (SQL, Shell)",
                "ChunkOrientedStep: ItemReader, ItemProcessor, ItemWriter 기반 청크 처리"
              ]
            },
            "ItemReader": "데이터 읽기 (DB, File, XML 등)",
            "ItemProcessor": "데이터 가공/변환 (선택적)",
            "ItemWriter": "데이터 쓰기 (DB, File, XML 등)",
            "JobLauncher": "Job 실행 및 관리",
            "JobRepository": "Job 실행 메타데이터 저장"
          },
          "proWorks5Extensions": {
            "BatchLauncher": {
              "description": "ProWorks5에 특화된 배치 Job 실행기",
              "features": [
                "JobParameter 동적 구성",
                "환경 변수 주입",
                "표준화된 결과 리턴"
              ],
              "execution": "별도 Shell Script로 실행 (e.g., java -jar batch-launcher.jar myJob -param1 value1)"
            },
            "BatchIOAdapter": {
              "description": "IO 에디터로 작성된 VO를 사용하여 ItemReader/Writer 연동",
              "function": "별도 개발 없이 데이터 매핑 자동화",
              "supportedTypes": [
                "VO to VO (DB to DB)",
                "FLD to VO (Fixed Length File to DB)",
                "VO to FLD (DB to Fixed Length File)",
                "DEL to VO (Delimiter File to DB)",
                "VO to DEL (DB to Delimiter File)"
              ]
            }
          }
        },
        "exampleShellScripts": {
          "Db_To_Db_Basic": "가장 기본적인 DB to DB 배치 (01_Basic_DbToDbJob.sh)",
          "Fld_To_File": "FLD 파일에서 읽어서 FLD 파일로 기록 (01_Basic_FldToFldJob.sh)",
          "Header_Tail_Delimiter": "Header/Tail 처리 포함, 헤더 라인수 설정 및 body 카운트로 Tail 시작 판단 (02_HeadTail_1_DelToDelJob.sh)",
          "Header_Tail_FLD": "Header/Tail 처리 포함하는 FLD 예제 (02_HeadTail_1_FldToFldJob.sh)",
          "Header_Tail_Prefix": "라인의 Prefix를 통해 처리하는 Delimiter 예제 (02_HeadTail_2_DelToDelJob.sh)",
          "DB_To_DB_Parameter": "Shell로부터 파라미터를 전달받아 처리 (03_Param_DbToDbJob.sh)",
          "Delimiter_To_DB": "헤더와 테일 처리를 포함하는 예제 (04_HeadTail_DelToDbJob.sh)",
          "DB_To_FLD_Multi_Step": "다단계 Job 실행 (05_MultiStep_DbToFldJob.sh)"
        }
      },
      "webServiceGuide": {
        "documentInfo": {
          "title": "ProWorks5 웹서비스 가이드",
          "description": "ProWorks5 프레임워크에서 웹서비스 이용을 위한 개발자 가이드",
          "targetAudience": "웹서비스를 이용하고자 하는 개발자"
        },
        "introduction": {
          "base": "ProWorks5 프레임워크 + CXF",
          "features": [
            "SOAP Provider 기능",
            "CXF를 이용한 SOAP 개발",
            "웹서비스 서버/클라이언트 통합 지원"
          ],
          "purpose": "효과적인 SOAP 개발 진행 지원"
        },
        "serverConfiguration": {
          "overview": "어노테이션과 스프링빈 설정파일을 통한 웹서비스 설정",
          "procedure": [
            "웹서비스 적용할 서비스 인터페이스 선택",
            "서비스 인터페이스에 @WebService 어노테이션 추가",
            "웹서비스 인터페이스 구현 서비스 선택",
            "서비스 구현체에 @WebService(endpointInterface=...) 어노테이션 설정",
            "스프링 설정 파일(context-webservice.xml)에 EndPoint 설정",
            "웹서비스 호출 및 테스트"
          ],
          "example": {
            "interface": "@WebService(...)\npublic interface UserService { ... }",
            "implementation": "@Service(...)\n@WebService(endpointInterface = \"...\")\npublic class UserServiceImpl implements UserService { ... }",
            "xmlConfig": "<jaxws:endpoint id=\"userServiceEndpoint\" implementor=\"...\" address=\"/UserService\" />"
          }
        },
        "clientConfiguration": {
          "overview": "스프링빈 설정파일을 통한 웹서비스 클라이언트 설정",
          "procedure": [
            "WSDL 파일 확보",
            "CXF WSDL-to-Java 코드 생성",
            "스프링 설정 파일(context-webservice-client.xml)에 클라이언트 프록시 설정",
            "생성된 서비스 인터페이스를 통해 웹서비스 호출"
          ],
          "example": {
            "xmlConfig": "<jaxws:client id=\"userServiceClient\" serviceClass=\"...\" address=\"...\" />",
            "clientCall": "@Service\npublic class UserWebServiceClient {\n  @Resource(name=\"userServiceClient\")\n  private UserService userService;\n  // ...\n}"
          }
        },
        "exceptionHandling": {
          "responseTypes": {
            "success": "SUCCESS",
            "error": [
              "VALIDATION_ERROR",
              "BUSINESS_ERROR",
              "SYSTEM_ERROR"
            ]
          },
          "errorInfo": [
            "errorCode",
            "errorMessage",
            "errorDetail"
          ],
          "pattern": "try { ... } catch (ValidationException e) { ... } catch (BusinessException e) { ... } catch (Exception e) { ... }"
        },
        "securityConsiderations": [
          "HTTPS 사용으로 데이터 암호화",
          "인증/인가 메커니즘 적용",
          "입력값 검증 및 SQL Injection 방지",
          "적절한 로깅으로 보안 감사 지원",
          "민감한 데이터 마스킹 처리"
        ],
        "monitoring": {
          "loggingConfig": [
            "웹서비스 호출 로그",
            "성능 측정 로그",
            "에러 발생 로그",
            "보안 관련 로그"
          ]
        }
      },
      "customizationGuide": {
        "documentInfo": {
          "title": "ProWorks5 커스터마이징 확장 가이드",
          "description": "프로젝트 상황에 맞춘 ProWorks5 커스터마이징 방법 및 적용 방법",
          "targetAudience": "프레임워크 커스터마이징 담당자",
          "users": [
            "공통 개발자",
            "시스템 관리자",
            "프레임워크 담당자",
            "형상관리 담당자"
          ]
        },
        "projectVO": {
          "overview": "Controller 파라미터는 공통 VO(CommVO)를 상속받은 VO 형태 사용",
          "constraint": "프레임워크의 com.inswave.elfw.core.CommVO는 수정 불가",
          "structure": "업무VO extends 프로젝트공통VO extends com.inswave.elfw.core.CommVO",
          "example": {
            "projectCommVO": "public class ProjectCommVO extends CommVO { ... }",
            "userVO": "public class UserVo extends ProjectCommVO { ... }"
          }
        },
        "userHeader": {
          "definition": "세션에 저장될 항목으로 표준헤더에 해당하는 프로젝트별 특성 반영",
          "implementation": "com.inswave.elfw.core.UserHeader 상속",
          "configuration": "운영관리 > 프로퍼티관리에서 'USER_HEADER_CLASS_NAME' 키에 Full Class명 설정"
        },
        "messageHandler": {
          "description": "ProWorks5 프레임워크의 메시지 처리 로직 확장",
          "implementation": "com.inswave.elfw.base.MessageCallback 상속",
          "configurationKey": "EL_MESSAGECALLBACK_CLASS_NAME"
        },
        "transactionMonitoring": {
          "description": "WAS별 트랜잭션 모니터링 연동",
          "methods": [
            "startTx",
            "endTx",
            "errorTx"
          ],
          "configurationKey": "EL_APM_ADAPTER_CLASS_NAME"
        },
        "batchProcess": {
          "description": "배치 업무 파트별 배치 선/후에서 공통 처리",
          "implementation": "com.inswave.elfw.batch.process.ElBatchJobProcess 상속"
        },
        "batchParameter": {
          "description": "배치 파라미터의 특정값 반영을 위한 구현체",
          "implementation": "ElAbstractBatchParamValue 상속",
          "configurationKey": "EL_BATCH_PARAM_VALUE_CLASS"
        },
        "scmIntegration": {
          "description": "ProWorks5와 형상관리 시스템 연계",
          "api": "ScmHttpClientUtil",
          "targets": [
            "Java를 통한 class 컴파일",
            "Controller에 포함된 서비스 메타 정보 등록"
          ]
        }
      },
      "webAdminGuide": {
        "documentInfo": {
          "title": "ProWorks5 웹 어드민 가이드",
          "description": "ProWorks5 프레임워크 시스템 전반 설정 제어를 위한 관리자 가이드",
          "targetAudience": "어플리케이션 운영 관리자"
        },
        "login": {
          "url": "http://[개발서버IP]:[포트]/ElAdmin/websquare/websquare.html",
          "authentication": "개인 ProWorks5 계정"
        },
        "userManagement": {
          "userGroup": {
            "purpose": "사용자 유형 구분을 위한 그룹 생성 및 메뉴 그룹 할당",
            "path": "[사용자관리] - [사용자그룹관리]"
          },
          "user": {
            "purpose": "사용자그룹에 포함시킬 사용자 등록",
            "path": "[사용자관리] - [사용자관리]"
          }
        },
        "menuManagement": {
          "menuGroup": {
            "purpose": "메뉴 접근 권한 부여를 위한 그룹 생성",
            "path": "[메뉴관리] - [메뉴그룹관리]"
          },
          "menu": {
            "purpose": "메뉴그룹에 포함시킬 메뉴 등록 (계층형 트리 구조)",
            "path": "[메뉴관리] - [메뉴관리]"
          }
        },
        "templateManagement": {
          "purpose": "웹 어드민을 통해 서비스 템플릿(Controller, Service, DAO, VO, SQL 등) 생성 및 관리",
          "path": "[템플릿관리] - [템플릿관리]"
        },
        "mainFeatures": [
          "Spring 프레임워크 기반 통합 관리 플랫폼",
          "웹 어드민을 통한 통합 설정 관리",
          "사용자/서비스/메뉴 권한 체계적 관리",
          "실시간 서버 캐시 리로드 지원",
          "프로퍼티 기반 다중 환경 설정"
        ]
      }
    },
    "codingStandards": {
      "namingConventionGuide": {
        "documentInfo": {
          "title": "온라인 어플리케이션 개발 명명 규칙",
          "description": "ProWorks5 제품 기반 온라인 어플리케이션 개발 시 개발자가 준수해야 할 명명 규칙"
        },
        "purpose": "공통된 Naming Rule 적용을 통한 어플리케이션 가독성 및 유지보수 효율성 향상",
        "casingStyles": {
          "PascalCasing": "각 단어의 첫 자를 대문자로 사용 (e.g., UserController)",
          "CamelCasing": "첫 단어는 소문자, 다음 단어부터 첫 자는 대문자 사용 (e.g., backColor)",
          "UpperCasing": "모든 문자를 대문자와 언더스코어로 사용 (e.g., CONSTANT_VALUE)",
          "LowerCasing": "모든 문자를 소문자로 사용 (e.g., tablename)"
        },
        "rulesByTarget": {
          "Package": "소문자로만 구성 (e.g., com.inswave.elfw.sample.web)",
          "Class": "PascalCasing (e.g., UserService, UserVo)",
          "Method": "CamelCasing (e.g., getUserList)",
          "Variable": "CamelCasing (e.g., userName)",
          "Constant": "UpperCasing + 언더스코어 (e.g., DEFAULT_PAGE_SIZE)",
          "ScreenFile": "소문자로만 구성 + .xml (e.g., userlist.xml)",
          "DataCollection": "dlt/dmp + Entity명 + 역할 (Pascal) (e.g., dltUserList, dmpSearchParam)",
          "Submission": "sbm + 요청명 + 역할 (Pascal) (e.g., sbmSearchUserList)",
          "JavaScript": "CamelCasing (e.g., initPage, onGridClick)",
          "SessionKey": "대분류.중분류.소분류 (소문자) (e.g., nh.global.user.info)",
          "SQL_ID": "처리종류 + 오브젝트명(Pascal) (e.g., selectListLoan, insertCustomer)"
        },
        "messageFile": {
          "fileNameRule": "message-[대분류코드]_[언어].properties (e.g., message-loan_ko.properties)",
          "messageIdRule": "속성.대분류.중분류.소분류.메시지설명 (e.g., c.common.button.save, e.user.login.fail)"
        }
      }
    },
    "componentReference": {
      "generator": {
        "metadata": {
          "componentName": "generator",
          "version": "WebSquare.uiplugin.generator6.0_0.1248B.20250421.101646",
          "description": "특정 컴포넌트를 반복적으로 추가/삭제가 가능한 컴포넌트입니다.",
          "category": "UI Plugin Component"
        },
        "properties": [
          { "name": "id", "type": "String", "description": "컴포넌트 ID. 필수.", "required": true },
          { "name": "class", "type": "String", "description": "HTML의 class 속성과 동일한 기능." },
          { "name": "tagname", "type": "String", "description": "사용할 태그를 지정 (기본값: div)." },
          { "name": "userData1", "type": "String", "description": "사용자 정의 데이터 속성 1." },
          { "name": "userData2", "type": "String", "description": "사용자 정의 데이터 속성 2." },
          { "name": "userData3", "type": "String", "description": "사용자 정의 데이터 속성 3." }
        ],
        "methods": {
          "specific": [
            { "name": "getChild(index, id)", "description": "반복부의 index와 컴포넌트 ID로 컴포넌트 객체 반환" },
            { "name": "insertChild(index)", "description": "index 위치에 반복부 추가 (생략 시 마지막에 추가)" },
            { "name": "insertChildAsync(index)", "description": "insertChild를 비동기로 수행" },
            { "name": "removeChild(index)", "description": "index 위치의 반복부 삭제 (생략 시 마지막 삭제)" },
            { "name": "removeAll()", "description": "모든 반복부 삭제" },
            { "name": "getLength()", "description": "생성된 반복부의 개수 반환" }
          ],
          "common": [
            "addClass", "removeClass", "toggleClass", "show", "hide", "setUserData", "getUserData"
          ]
        },
        "usagePatterns": {
          "dataBinding": "generator의 반복부 내부에 DataMap/DataList의 데이터 바인딩 패턴",
          "dynamicCreation": "외부 데이터에 따른 동적 반복부 생성 패턴",
          "dynamicRemoval": "조건에 따른 동적 반복부 제거 패턴",
          "validationAllRows": "모든 반복부에 대한 유효성 검사 패턴"
        }
      },
      "pageFrame": {
        "component": "pageFrame",
        "version": "6.0_0.1248B.20250421.101646",
        "description": "wframe의 기능과 UDC 기능을 함께 사용할 수 있는 컴포넌트.",
        "properties": [
          { "name": "id", "type": "string", "description": "컴포넌트 ID." },
          { "name": "src", "type": "string", "description": "로드할 화면의 URL." },
          { "name": "wframeType", "type": "string", "description": "생성될 pageFrame의 type (e.g., 'xml', 'html', 'img')." },
          { "name": "class", "type": "string", "description": "CSS class 지정." },
          { "name": "style", "type": "string", "description": "CSS style 지정." }
        ],
        "events": {
          "onpageFrameLoad": "pageFrame의 내용 로드가 완료된 후에 발생하는 이벤트."
        },
        "methods": {
          "setSrc(src, options)": "pageFrame의 src 속성을 새로 설정하고 해당 화면을 로드. Promise<boolean> 반환."
        },
        "utilities": {
          "$p.parent()": "호출한 화면의 부모 페이지 Scope 객체를 반환."
        }
      }
    },
    "coreFrameworkPatterns": {
      "boardCRUDExample": {
        "dataCollection": {
          "searchMap": {
            "id": "dmp_{entityName}Search",
            "description": "검색 조건",
            "keys": ["pageIndex", "pageSize", "searchKeyword"]
          },
          "listData": {
            "id": "dlt_{entityName}List",
            "description": "목록 데이터",
            "columns": ["id", "name", "regDate"]
          },
          "detailMap": {
            "id": "dmp_{entityName}Detail",
            "description": "상세 정보",
            "keys": ["id", "name", "description"]
          }
        },
        "submissions": {
          "select": { "id": "sbm_select{EntityName}List", "action": "biz/select{EntityName}List.do" },
          "view": { "id": "sbm_select{EntityName}", "action": "biz/select{EntityName}.do" },
          "insert": { "id": "sbm_insert{EntityName}", "action": "biz/insert{EntityName}.do" },
          "update": { "id": "sbm_update{EntityName}", "action": "biz/update{EntityName}.do" },
          "delete": { "id": "sbm_delete{EntityName}", "action": "biz/delete{EntityName}.do" }
        },
        "binding": {
          "dataList": {
            "id": "dlt_boardList",
            "columns": [
              { "id": "id", "name": "게시글 ID", "control": "게시글 목록 gridView의 'ID' 컬럼" },
              { "id": "name", "name": "게시글 제목", "control": "게시글 목록 gridView의 '제목' 컬럼" },
              { "id": "regDate", "name": "등록일", "control": "게시글 목록 gridView의 '등록일' 컬럼" }
            ]
          },
          "dataMap": {
            "id": "dmp_boardDetail",
            "keys": [
              { "id": "id", "name": "게시글 ID", "control": "게시글 상세 폼의 'ID' 입력 필드" },
              { "id": "name", "name": "게시글 제목", "control": "게시글 상세 폼의 '제목' 입력 필드" },
              { "id": "description", "name": "내용", "control": "게시글 상세 폼의 '내용' textarea" }
            ]
          }
        },
        "scenario": {
          "pageName": "게시판 기능 (Board CRUD)",
          "dataCollections": {
            "search": "dmp_boardSearch",
            "list": "dlt_boardList",
            "detail": "dmp_boardDetail"
          },
          "submissions": {
            "list": "sbm_selectBoardList",
            "view": "sbm_selectBoard",
            "insert": "sbm_insertBoard",
            "update": "sbm_updateBoard",
            "delete": "sbm_deleteBoard"
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
      }
    }
  }
}