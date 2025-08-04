{
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
      "",
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
      "  ",
      "  // FLD 전문 사용 시 길이 설정",
      "  // @FLDLength(20)",
      "  private String sessionKey;",
      "  ",
      "  // getter/setter",
      "}"
    ]
  },

  "로그인처리": {
    "LoginProcess": {
      "설명": "프레임워크에서 제공하는 로그인 처리 클래스",
      "메서드": "processLogin",
      
      "파라미터": {
        "HttpServletRequest_request": {
          "설명": "HttpServletRequest 객체",
          "필수여부": "필수"
        },
        "String_id": {
          "설명": "로그인 ID에 해당하는 값",
          "필수여부": "필수"
        },
        "Object_args": {
          "설명": "로그인 처리를 위한 서비스 또는 객체",
          "필수여부": "선택",
          "전달대상": ["LoginAdapter", "SessionDataAdapter", "SessionAdapter"]
        }
      },

      "설정": {
        "EL_WAR_LOGIN_ONLY_CHECK": "프레임워크 EL_USER 테이블 체크 안할 시 'Y' 설정"
      }
    },

    "LoginAdapter": {
      "설명": "로그인 처리를 담당하는 클래스",
      "상속클래스": "com.inswave.elfw.login.LoginAdapter",
      
      "구현메서드": {
        "login": "로그인 처리 메서드",
        "logout": "로그아웃 처리 메서드"
      },

      "구현패턴": [
        "ElBeanUtil.getBean을 사용하여 서비스 객체 획득",
        "로그인/아웃 실패시 LoginException 발생",
        "로그인/아웃 성공시 LoginInfo에 값 설정"
      ],

      "설정": {
        "메뉴경로": "[운영관리] - [프로퍼티관리]",
        "프로퍼티키": "LOGIN_ADAPTER_CLASS_NAME",
        "설정값": "LoginAdapter 구현체 Full Class명"
      },

      "예시": [
        "public class ProjectLoginAdapter extends LoginAdapter {",
        "  @Override",
        "  public LoginInfo login(HttpServletRequest request, String userId, Object... params) throws LoginException {",
        "    try {",
        "      UserService userService = ElBeanUtil.getBean(UserService.class);",
        "      UserVo userVo = userService.authenticateUser(userId);",
        "      ",
        "      if (userVo != null) {",
        "        LoginInfo loginInfo = new LoginInfo();",
        "        loginInfo.setUserId(userId);",
        "        loginInfo.setSuccess(true);",
        "        return loginInfo;",
        "      } else {",
        "        throw new LoginException(\"인증 실패\");",
        "      }",
        "    } catch (Exception e) {",
        "      throw new LoginException(\"로그인 처리 중 오류\", e);",
        "    }",
        "  }",
        "}"
      ]
    },

    "SessionDataAdapter": {
      "설명": "세션에 저장할 UserHeader를 설정하는 클래스",
      "상속클래스": "com.inswave.elfw.session.SessionDataAdapter",
      "구현메서드": "setSessionData",

      "설정": {
        "메뉴경로": "[운영관리] - [프로퍼티관리]",
        "프로퍼티키": "SESSION_DATA_ADAPTER_CLASS_NAME",
        "설정값": "SessionDataAdapter 구현체 Full Class명"
      },

      "예시": [
        "public class ProjectSessionDataAdapter extends SessionDataAdapter {",
        "  @Override",
        "  public UserHeader setSessionData(HttpServletRequest request, String userId, Object... params) {",
        "    ProjectUserHeader userHeader = new ProjectUserHeader();",
        "    ",
        "    // 사용자 정보 조회 및 설정",
        "    UserService userService = ElBeanUtil.getBean(UserService.class);",
        "    UserVo userVo = userService.getUserInfo(userId);",
        "    ",
        "    userHeader.setUserId(userId);",
        "    userHeader.setUserGroupNm(userVo.getUserGroupNm());",
        "    userHeader.setDeptCode(userVo.getDeptCode());",
        "    ",
        "    return userHeader;",
        "  }",
        "}"
      ]
    },

    "SessionAdapter": {
      "설명": "UserHeader 내용을 저장할 위치를 정의하는 어댑터",
      "기본제공": "HttpSessionAdapter",
      "상속클래스": "com.inswave.elfw.session.SessionAdapter",
      
      "구현메서드": {
        "setSession": "세션 저장 메서드",
        "getSession": "세션 조회 메서드"
      },

      "설정": {
        "메뉴경로": "[운영관리] - [프로퍼티관리]",
        "프로퍼티키": "SESSION_ADAPTER_CLASS_NAME",
        "기본값": "com.inswave.elfw.session.HttpSessionAdapter"
      },

      "확장예시": [
        "// Redis 기반 세션 어댑터 예시",
        "public class RedisSessionAdapter extends SessionAdapter {",
        "  @Override",
        "  public void setSession(HttpServletRequest request, UserHeader userHeader) {",
        "    String sessionKey = generateSessionKey();",
        "    redisTemplate.opsForValue().set(sessionKey, userHeader, Duration.ofMinutes(30));",
        "    // 클라이언트에 세션키 전달",
        "  }",
        "  ",
        "  @Override",
        "  public UserHeader getSession(HttpServletRequest request) {",
        "    String sessionKey = extractSessionKey(request);",
        "    return redisTemplate.opsForValue().get(sessionKey);",
        "  }",
        "}"
      ]
    }
  },

  "사용자헤더설정어댑터": {
    "설명": "Request/Response 시점에 프로젝트 UserHeader 값을 별도 설정",
    "상속클래스": "com.inswave.elfw.session.UserHeaderSetAdapter",
    
    "구현메서드": {
      "setInputUserHeader": {
        "설명": "클라이언트 요청 후 업무 Controller 호출 직전 동작",
        "용도": "프로젝트 UserHeader 값 설정하여 업무에 전달"
      },
      "getOutputUserHeader": {
        "설명": "Controller 업무 처리 정상 완료된 경우",
        "용도": "클라이언트에 보낼 프로젝트 UserHeader 값 설정"
      },
      "getExceptionOutputUserHeader": {
        "설명": "Controller 처리 중 에러 발생한 경우",
        "용도": "에러 관련 UserHeader 값 설정"
      }
    },

    "설정": {
      "메뉴경로": "[운영관리] - [프로퍼티관리]",
      "프로퍼티키": "HEADER_SET_ADAPTER_CLASS_NAME",
      "설정값": "UserHeaderSetAdapter 구현체 Full Class명"
    },

    "예시": [
      "public class ProjectUserHeaderSetAdapter extends UserHeaderSetAdapter {",
      "  @Override",
      "  public void setInputUserHeader(HttpServletRequest request, UserHeader userHeader) {",
      "    ProjectUserHeader projectHeader = (ProjectUserHeader) userHeader;",
      "    // 요청 시점 헤더 설정",
      "    projectHeader.setRequestTime(new Date());",
      "  }",
      "  ",
      "  @Override",
      "  public UserHeader getOutputUserHeader(HttpServletRequest request, UserHeader userHeader, Object resultData) {",
      "    ProjectUserHeader projectHeader = (ProjectUserHeader) userHeader;",
      "    projectHeader.setResultCode(\"SUCCESS\");",
      "    projectHeader.setResponseTime(new Date());",
      "    return projectHeader;",
      "  }",
      "}"
    ]
  },

  "시스템핸들어댑터": {
    "ElSystemHandleAdapter": {
      "설명": "전체 시스템의 선/후에서 공통으로 처리해야 하는 요건",
      "상속클래스": "com.inswave.elfw.intercept.service.ElSystemHandleAdapter",
      
      "구현메서드": {
        "preHandle": {
          "설명": "시스템 선처리 메서드",
          "호출시점": "클라이언트 요청 시 최초 호출",
          "파라미터": [
            "HttpServletRequest request",
            "String inputData"
          ]
        },
        "postHandle": {
          "설명": "시스템 후처리 메서드",
          "호출시점": "클라이언트 최종 결과 전달 직전",
          "파라미터": [
            "ElHeader elHeader",
            "UserHeader userHeader",
            "Object[] serviceParams",
            "Model resultModel"
          ]
        }
      },

      "설정": {
        "메뉴경로": "[운영관리] - [프로퍼티관리]",
        "프로퍼티키": "SYSTEM_HANDLE_CLASS_NAME",
        "적용범위": "해당 프로젝트의 모든 요청"
      }
    },

    "ElServiceHandleAdapter": {
      "설명": "업무 파트별 선/후에서 공통으로 처리해야 하는 요건",
      "상속클래스": "com.inswave.elfw.intercept.service.ElServiceHandleAdapter",
      
      "설정": {
        "메뉴경로": "[서비스관리] - [서비스제어관리]",
        "설정항목": "해당 서비스의 '선/후처리 클래스명'",
        "적용범위": "해당 서비스만"
      }
    }
  },

  "서비스제어어댑터": {
    "설명": "프레임워크 기본 기능 외 추가적 거래 제어 요건",
    "상속클래스": "com.inswave.elfw.intercept.service.ElServiceUserControlAdapter",
    
    "구현메서드": {
      "load": {
        "설명": "사용자 정의 거래 제어 정보 Load",
        "호출시점": "WAS 기동 시 최초 한번 또는 Cache Reload 시",
        "구현방법": "DBUtil.getConnection을 사용하여 직접 Load 구현"
      },
      "control": {
        "설명": "사용자 정의 거래 제어 처리",
        "호출시점": "서비스 호출 시",
        "예외처리": "거래제어 시 UserException 발생"
      }
    },

    "설정": {
      "메뉴경로": "[운영관리] - [프로퍼티관리]",
      "프로퍼티키": "SERVICE_USER_CONTROL_CLASS_NAME"
    },

    "예시": [
      "public class ProjectServiceControlAdapter extends ElServiceUserControlAdapter {",
      "  private Map<String, ServiceControlInfo> controlInfoMap = new HashMap<>();",
      "  ",
      "  @Override",
      "  public void load() {",
      "    Connection conn = DBUtil.getConnection();",
      "    // DB에서 서비스 제어 정보 조회",
      "    // controlInfoMap에 정보 저장",
      "  }",
      "  ",
      "  @Override",
      "  public void control(Object controlInfo) throws UserException {",
      "    ServiceControlInfo info = (ServiceControlInfo) controlInfo;",
      "    if (!info.isServiceEnabled()) {",
      "      throw new UserException(\"서비스가 제한되었습니다.\");",
      "    }",
      "  }",
      "}"
    ]
  },

  "암호화어댑터": {
    "설명": "프로젝트 필드 암호화 구현체",
    "상속클래스": "com.inswave.elfw.security.ElAbstractCrypto",
    
    "구현메서드": {
      "getEncrypt": "암호화 처리 메서드",
      "getDecrypt": "복호화 처리 메서드"
    },

    "설정": {
      "메뉴경로": "[운영관리] - [프로퍼티관리]",
      "프로퍼티키": "CRYPTO_CASS_NAME",
      "활용": "IO 에디터에서 해당 필드의 암호화 여부 설정"
    },

    "예시": [
      "public class ProjectCryptoAdapter extends ElAbstractCrypto {",
      "  @Override",
      "  public String getEncrypt(String plainText) {",
      "    try {",
      "      // AES 암호화 구현",
      "      Cipher cipher = Cipher.getInstance(\"AES/CBC/PKCS5Padding\");",
      "      cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);",
      "      byte[] encrypted = cipher.doFinal(plainText.getBytes());",
      "      return Base64.getEncoder().encodeToString(encrypted);",
      "    } catch (Exception e) {",
      "      throw new RuntimeException(\"암호화 실패\", e);",
      "    }",
      "  }",
      "  ",
      "  @Override",
      "  public String getDecrypt(String encryptedText) {",
      "    try {",
      "      // AES 복호화 구현",
      "      Cipher cipher = Cipher.getInstance(\"AES/CBC/PKCS5Padding\");",
      "      cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);",
      "      byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedText));",
      "      return new String(decrypted);",
      "    } catch (Exception e) {",
      "      throw new RuntimeException(\"복호화 실패\", e);",
      "    }",
      "  }",
      "}"
    ]
  },

  "배포관련어댑터": {
    "DevSvrScmHandleAdapter": {
      "설명": "서버 반영 선/후 처리 어댑터",
      "상속클래스": "com.inswave.elfw.deploy.DevSvrScmHandleAdapter",
      
      "구현메서드": {
        "preHandle": "서버 반영 전 처리",
        "postHandle": "서버 반영 후 처리"
      },

      "설정": {
        "메뉴경로": "[운영관리] - [프로퍼티관리]",
        "그룹ID": "DEV_SVR_SCM_HANDLE_CLS_NAME",
        "프로퍼티키": "어플리케이션ID"
      }
    },

    "ResourceDeployAdapter": {
      "설명": "타 형상관리와 연계를 위한 리소스 배포 어댑터",
      "상속클래스": "com.inswave.elfw.deploy.ResourceDeployAdapter",
      "구현메서드": "deploy",

      "설정": {
        "메뉴경로": "[배포관리] - [배포노드관리]",
        "배포구현체클래스": "Custom",
        "클래스명": "구현체 Full Class명"
      }
    }
  },

  "로그관련어댑터": {
    "AppLogFormatter": {
      "설명": "프로젝트 로그 포맷 변경",
      "상속클래스": "com.inswave.elfw.log.AppLogFormatter",
      "구현메서드": "format",

      "설정": {
        "메뉴경로": "[운영관리] - [프로퍼티관리]",
        "프로퍼티키": "DEFAULT_LOG_FORMATTER_NAME"
      }
    },

    "ImageLogger": {
      "설명": "이미지 로그 커스터마이징",
      "구현클래스": [
        "ElAbstractDBImageLogProcess",
        "ElAbstractDBImageLogger"
      ],

      "설정": {
        "IMAGE_LOG_USE_YN": "이미지 로그 사용 유무",
        "IMAGE_LOGGER_CLS_NAME": "ElAbstractDBImageLogger 구현체",
        "IMAGE_LOG_PROCESS_CLS_NAME": "ElAbstractDBImageLogProcess 구현체",
        "IMAGE_LOG_QUEUE_SIZE": "이미지로그 Queue 사이즈",
        "IMAGE_LOG_TIMER_SEC": "이미지로그 타이머 시간"
      }
    }
  },

  "캐시관리": {
    "CustomCacheManager": {
      "설명": "프로젝트 캐시 구현",
      "상속클래스": "ElAbstractCacheManager",
      "구현패턴": "싱글턴 패턴으로 구현",

      "Notify방법": [
        "ElCustomNotifyUtil.reloadCustomCacheManager({해당클래스명}.class)",
        "ElCustomNotifyUtil.reloadCustomCacheManager({앱ID}, {해당클래스명}.class)",
        "ElCustomNotifyUtil.reloadCustomCacheManager({앱ID}, {해당클래스명_Full_Name})"
      ]
    }
  },

  "DB기반메시지": {
    "설명": "기본 파일 Properties 기반에서 DB 기반 메시지 처리로 변경",
    
    "구현클래스": {
      "ElMsgVO": "메시지 처리를 위한 추가 필드가 필요한 경우",
      "ElAbstractMsgManager": "DB 메시지를 Cache하여 처리 (싱글턴 패턴)",
      "ElMsgConvert": "메시지 VO에 기타 값을 설정하는 로직"
    },

    "설정": {
      "EL_DB_MSG_USE_YN": "DB 기반 메시지 처리 사용 여부",
      "EL_MSG_VO_CLASS_NAME": "DB 기반 메시지를 담을 VO 클래스명",
      "EL_MSG_VO_CONVERT_CLASS_NAME": "DB 기반 메시지 변환 처리 클래스명",
      "EL_MSG_MANAGER_CLASS_NAME": "DB 기반 메시지 데이터 캐시 처리 클래스명"
    }
  },

  "확장기능": {
    "메뉴API활용": {
      "설명": "웹어드민의 메뉴그룹관리 및 메뉴관리를 통해 정의된 메뉴 트리 정보 가져오기",
      
      "주요API": [
        "ControllerContextUtil.getUserHeader().getUserGroupNo()",
        "ElUserGroupManager.getInstance().getUserGroupVo(userGroupNo)",
        "ElMenuGroupManager.getInstance().getElMenuGroupList(parentMenuGroupNo)"
      ]
    },

    "SiteJson어댑터": {
      "설명": "고객사 InBound Json 어댑터 추가 확장",
      
      "설정요구사항": [
        "Site Json 사용 유무 설정",
        "web.xml 항목 추가 (*.sitejson)",
        "dispatcher-servlet.xml 항목 추가",
        "context-inswave.xml 항목 추가"
      ],

      "구현어댑터": [
        "Site Json 입력 매핑 어댑터",
        "Site Json 출력 매핑 어댑터"
      ]
    },

    "IO속성처리확장": {
      "설명": "IO의 속성 처리를 위한 확장 기능",
      
      "설정": {
        "EL_IO_FIELD_ATTR_LIST": "프로젝트에서 사용할 속성 문자열 목록",
        "EL_IO_FIELD_ATTR_LIST_PRE_STR": "원본 필드값을 갖고 있을 변수의 Prefix명"
      },

      "구현어댑터": "ElIoFieldApplyAttributeAdapter"
    }
  },

  "배치확장": {
    "시스템선후처리": {
      "설명": "모든 배치 선/후에서 공통으로 처리해야 하는 요건",
      "상속클래스": "com.inswave.elfw.batch.process.ElBatchJobProcess",
      "설정위치": "Inc/inswaveInvokeBatch.sh의 SYS_PRE_POST_PROCESS"
    },

    "업무선후처리": {
      "설명": "각 업무 파트별 배치 선/후에서 공통 처리",
      "상속클래스": "com.inswave.elfw.batch.process.ElBatchJobProcess",
      "설정위치": "각 업무 Shell의 BIZ_PRE_POST_PROCESS"
    },

    "배치파라미터": {
      "설명": "배치 파라미터의 특정값 반영을 위한 구현체",
      "상속클래스": "ElAbstractBatchParamValue",
      "구현메서드": "getParamValue",
      "설정키": "EL_BATCH_PARAM_VALUE_CLASS"
    }
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
      "localhost 8088 ElAdmin TestAppId \"/tmp/proworks5/deploy/uuid_1234566\""
    ],

    "처리결과": {
      "성공": "Success",
      "실패": "Fail + 상세 오류 메시지",
      "성공시처리": [
        "Class 컴파일: 배포 소스 Path 밑의 classes에 생성",
        "메타(DB): Controller 존재 시 서비스 정보 대상 시스템에 등록"
      ]
    }
  },

  "주요특징": [
    "프레임워크 Core 기능 확장 지원",
    "프로젝트별 요구사항에 맞춘 커스터마이징",
    "어댑터 패턴을 통한 유연한 확장성",
    "웹 어드민을 통한 설정 기반 적용",
    "배치 시스템까지 포괄하는 확장 지원",
    "형상관리 시스템과의 연계 지원"
  ],

  "개발가이드라인": [
    "프레임워크 Core 클래스는 수정 금지",
    "상속을 통한 확장 구현 권장",
    "싱글턴 패턴 적용 시 thread-safe 고려",
    "예외 처리 시 프레임워크 Exception 활용",
    "설정 변경 후 서버 캐시 리로드 필수",
    "확장 구현체는 프로젝트 패키지에 배치"
  ]
}