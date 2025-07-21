package com.demo.proworks.domain.user.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.proworks.domain.user.service.UserService;
import com.demo.proworks.domain.user.vo.UserVo;
import com.demo.proworks.domain.user.vo.LoginVo;
import com.demo.proworks.domain.user.vo.MatchingCheckedVo;
import com.demo.proworks.domain.user.vo.UserInfoVo;
import com.demo.proworks.domain.user.vo.UserListVo;
import com.demo.proworks.domain.user.vo.ApplicantVo;
import com.demo.proworks.domain.user.vo.ApplicantListVo;
import com.demo.proworks.domain.user.vo.ApplicantDetailVo;
import com.demo.proworks.domain.user.vo.ScoutVo;
import com.demo.proworks.domain.user.vo.TechStackVo;
import com.demo.proworks.domain.user.vo.ScoutListVo;
import com.demo.proworks.domain.user.vo.ApplicationHistoryVo;
import com.demo.proworks.domain.user.vo.ApplicationHistoryListVo;
import com.demo.proworks.cmmn.ProworksUserHeader;
import com.inswave.elfw.util.ControllerContextUtil;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;
import com.inswave.elfw.log.AppLog;
import com.inswave.elfw.login.LoginInfo;
import com.inswave.elfw.login.LoginProcessor;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 * @subject : 일반회원 관련 처리를 담당하는 컨트롤러
 * @description : 일반회원 관련 처리를 담당하는 컨트롤러
 * @author : Inswave
 * @since : 2025/07/03
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/03 Inswave 최초 생성
 * 
 */
@Controller
public class UserController {

	private static final int BCRYPT_ROUNDS = 12;
	
	// 파일 업로드 관련 상수
	private static final String UPLOAD_ROOT_DIR = "C:/upload";
	private static final String PROFILE_UPLOAD_DIR = "/images/profile";
	private static final String RESUME_UPLOAD_DIR = "/resume";
	private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB

	/** UserService */
	@Resource(name = "userServiceImpl")
	private UserService userService;

	@Resource(name = "loginProcess")
	protected LoginProcessor loginProcess;

	/**
	 * 로그인을 처리한다.
	 * 
	 * @param loginVo 로그인 정보 LoginVo
	 * @param request 요청 정보 HttpServletRequest
	 * @throws Exception
	 */
	@ElService(key = "USLogin")
	@RequestMapping(value = "USLogin")
	@ElDescription(sub = "로그인", desc = "로그인을 처리한다.")
	public void login(LoginVo loginVo, HttpServletRequest request) throws Exception {
		String email = loginVo.getEmail();
		String password = loginVo.getPassword();

		LoginInfo info = loginProcess.processLogin(request, email, password);

		AppLog.debug("- Login 정보 : " + info.toString());
	}

	/**
	 * 일반회원 목록을 조회합니다.
	 *
	 * @param userVo 일반회원
	 * @return 목록조회 결과
	 * @throws Exception
	 */
	@ElService(key = "US0001List")
	@RequestMapping(value = "US0001List")
	@ElDescription(sub = "일반회원 목록조회", desc = "페이징을 처리하여 일반회원 목록 조회를 한다.")
	public UserListVo selectListUser(UserVo userVo) throws Exception {

		List<UserVo> userList = userService.selectListUser(userVo);
		long totCnt = userService.selectListCountUser(userVo);

		UserListVo retUserList = new UserListVo();
		retUserList.setUserVoList(userList);
		retUserList.setTotalCount(totCnt);
		retUserList.setPageSize(userVo.getPageSize());
		retUserList.setPageIndex(userVo.getPageIndex());

		return retUserList;
	}

	/**
	 * 일반회원을 단건 조회 처리 한다.
	 *
	 * @param userVo 일반회원
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	@ElService(key = "US0001UpdView")
	@RequestMapping(value = "US0001UpdView")
	@ElDescription(sub = "일반회원 갱신 폼을 위한 조회", desc = "일반회원 갱신 폼을 위한 조회를 한다.")
	public UserVo selectUser(UserVo userVo) throws Exception {
		UserVo selectUserVo = userService.selectUser(userVo);

		return selectUserVo;
	}

	/**
	 * 일반회원를 등록 처리 한다.
	 *
	 * @param userVo 일반회원
	 * @throws Exception
	 */
	@ElService(key = "US0001Ins")
	@RequestMapping(value = "US0001Ins")
	@ElDescription(sub = "일반회원 등록처리", desc = "일반회원를 등록 처리 한다.")
	public void insertUser(UserVo userVo) throws Exception {
		userService.insertUser(userVo);
	}

	/**
	 * 일반회원를 갱신 처리 한다.
	 *
	 * @param userVo 일반회원
	 * @throws Exception
	 */
	@ElService(key = "US0001Upd")
	@RequestMapping(value = "US0001Upd")
	@ElValidator(errUrl = "/user/userRegister", errContinue = true)
	@ElDescription(sub = "일반회원 갱신처리", desc = "일반회원를 갱신 처리 한다.")
	public void updateUser(UserVo userVo) throws Exception {

		userService.updateUser(userVo);
	}

	/**
	 * 일반회원를 삭제 처리한다.
	 *
	 * @param userVo 일반회원
	 * @throws Exception
	 */
	@ElService(key = "US0001Del")
	@RequestMapping(value = "US0001Del")
	@ElDescription(sub = "일반회원 삭제처리", desc = "일반회원를 삭제 처리한다.")
	public void deleteUser(UserVo userVo) throws Exception {
		userService.deleteUser(userVo);
	}

	/**
	 * 이메일 중복 여부를 확인한다.
	 *
	 * @param request HttpServletRequest 요청 객체
	 * @return 중복 확인 결과
	 * @throws Exception
	 */
	@ElService(key = "USCheckEmail")
	@RequestMapping(value = { "USCheckEmail", "USCheckEmail.pwkjson" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	@ElDescription(sub = "이메일 중복 확인", desc = "회원가입 시 이메일 중복 여부를 확인한다.")
	public Map<String, Object> checkEmailDuplicate(HttpServletRequest request) throws Exception {
		String email = null;

		try {
			// JSON 문자열 읽기
			BufferedReader reader = request.getReader();
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			String jsonString = sb.toString();

			System.out.println("=== 요청 JSON 원본 ===");
			System.out.println(jsonString);

			if (jsonString == null || jsonString.trim().isEmpty()) {
				// JSON이 비어있으면 query parameter에서 확인
				email = request.getParameter("email");
				System.out.println("Query Parameter에서 이메일 추출: " + email);
			} else {
				// JSON 파싱
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode rootNode = objectMapper.readTree(jsonString);

				// {"dma_email_check":{"email":"..."}} 구조 처리
				if (rootNode.has("dma_email_check")) {
					JsonNode emailCheckNode = rootNode.get("dma_email_check");
					if (emailCheckNode.has("email")) {
						email = emailCheckNode.get("email").asText();
					}
				} else if (rootNode.has("email")) {
					// 직접 {"email":"..."} 구조인 경우
					email = rootNode.get("email").asText();
				}

				System.out.println("JSON에서 이메일 추출: " + email);
			}

		} catch (IOException e) {
			System.out.println("JSON 파싱 실패: " + e.getMessage());
			// JSON 파싱 실패시 query parameter 확인
			email = request.getParameter("email");
			System.out.println("Fallback - Query Parameter 이메일: " + email);
		}

		System.out.println("=== ProWorks5 이메일 중복 확인 호출됨 ===");
		System.out.println("입력 이메일: " + email);

		// ProWorks5 표준 응답 구조에 맞게 elData 필드 사용
		Map<String, Object> emailResult = new HashMap<>();

		if (email == null || email.trim().isEmpty()) {
			// 이메일이 비어있는 경우 - 오류로 처리
			System.out.println("오류: 이메일이 비어있음");
			emailResult.put("email", "");
			emailResult.put("role", "ERROR");
		} else {
			// 이메일 설정
			emailResult.put("email", email);

			try {
				boolean isDuplicate = userService.checkEmailDuplicate(email);

				if (isDuplicate) {
					// 중복된 이메일인 경우
					System.out.println("결과: 이메일 중복");
					emailResult.put("role", "DUPLICATE");
				} else {
					// 사용 가능한 이메일인 경우
					System.out.println("결과: 이메일 사용 가능");
					emailResult.put("role", "AVAILABLE");
				}

			} catch (Exception e) {
				// 오류 발생시
				System.out.println("오류 발생: " + e.getMessage());
				emailResult.put("role", "ERROR");
			}
		}

		// ProWorks5 표준 응답 구조로 변경
		Map<String, Object> response = new HashMap<>();
		response.put("elData", emailResult);

		System.out.println("최종 응답: " + response);
		return response;
	}

	/**
	 * 일반 사용자 회원가입을 처리한다.
	 *
	 * @param request HttpServletRequest 요청 객체
	 * @return 가입 결과
	 * @throws Exception
	 */
	@ElService(key = "USRegister")
	@RequestMapping(value = { "USRegister", "USRegister.pwkjson" })
	@ElDescription(sub = "일반 사용자 회원가입", desc = "일반 사용자의 회원가입을 처리한다.")
	public UserVo registerUser(HttpServletRequest request) throws Exception {
		System.out.println("=== ProWorks5 회원가입 요청 시작 ===");

		UserVo userVo = new UserVo();

		try {
			// JSON 문자열 읽기
			BufferedReader reader = request.getReader();
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			String jsonString = sb.toString();

			System.out.println("=== 요청 JSON 원본 ===");
			System.out.println(jsonString);

			if (jsonString == null || jsonString.trim().isEmpty()) {
				System.out.println("JSON이 비어있음 - Query Parameter 확인");
				// Query parameter로 fallback
				userVo.setName(request.getParameter("name"));
				userVo.setEmail(request.getParameter("email"));
				userVo.setPassword(request.getParameter("password"));
				userVo.setBirthDate(request.getParameter("birthDate"));
				userVo.setGender(request.getParameter("gender"));
				userVo.setPhoneNumber(request.getParameter("phoneNumber"));
				userVo.setEmailConsent(request.getParameter("emailConsent"));
			} else {
				// JSON 파싱
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode rootNode = objectMapper.readTree(jsonString);

				System.out.println("JSON 파싱 성공");

				// {"dma_register_request":{"name":"...", "email":"...", ...}} 구조 처리
				JsonNode requestNode = null;
				if (rootNode.has("dma_register_request")) {
					requestNode = rootNode.get("dma_register_request");
					System.out.println("dma_register_request 노드 발견");
				} else {
					// 직접 필드들이 있는 경우
					requestNode = rootNode;
					System.out.println("직접 필드 구조");
				}

				if (requestNode != null) {
					if (requestNode.has("name")) {
						userVo.setName(requestNode.get("name").asText());
					}
					if (requestNode.has("email")) {
						userVo.setEmail(requestNode.get("email").asText());
					}
					if (requestNode.has("password")) {
						userVo.setPassword(requestNode.get("password").asText());
					}
					if (requestNode.has("birthDate")) {
						userVo.setBirthDate(requestNode.get("birthDate").asText());
					}
					if (requestNode.has("gender")) {
						userVo.setGender(requestNode.get("gender").asText());
					}
					if (requestNode.has("phoneNumber")) {
						userVo.setPhoneNumber(requestNode.get("phoneNumber").asText());
					}
					if (requestNode.has("emailConsent")) {
						userVo.setEmailConsent(requestNode.get("emailConsent").asText());
					}
				}
			}

		} catch (IOException e) {
			System.out.println("JSON 파싱 실패: " + e.getMessage());
			e.printStackTrace();
		}

		// 파싱된 데이터 로그
		System.out.println("=== 파싱된 회원가입 정보 ===");
		System.out.println("이름: " + userVo.getName());
		System.out.println("이메일: " + userVo.getEmail());
		System.out.println("생년월일: " + userVo.getBirthDate());
		System.out.println("성별: " + userVo.getGender());
		System.out.println("휴대폰: " + userVo.getPhoneNumber());
		System.out.println("이메일수신동의: " + userVo.getEmailConsent());

		// 유효성 검사
		if (userVo.getName() == null || userVo.getName().trim().isEmpty()) {
			System.out.println("오류: 이름이 비어있음");
			throw new IllegalArgumentException("이름은 필수입니다.");
		}

		if (userVo.getEmail() == null || userVo.getEmail().trim().isEmpty()) {
			System.out.println("오류: 이메일이 비어있음");
			throw new IllegalArgumentException("이메일은 필수입니다.");
		}

		if (userVo.getPassword() == null || userVo.getPassword().trim().isEmpty()) {
			System.out.println("오류: 비밀번호가 비어있음");
			throw new IllegalArgumentException("비밀번호는 필수입니다.");
		}

		// 이메일 수신동의를 boolean 값으로 변환
		if ("true".equals(userVo.getEmailConsent()) || "1".equals(userVo.getEmailConsent())) {
			userVo.setEmailConsent("1");
		} else {
			userVo.setEmailConsent("0");
		}

		// 기본 역할 설정 (DEVELOPER = 1)
		userVo.setUserRole("developer");

		System.out.println("=== 서비스 호출 시작 ===");

		try {
			int userId = userService.registerUser(userVo);
			System.out.println("=== 회원가입 성공 ===");
			System.out.println("생성된 사용자 ID: " + userId);

			// users_info 테이블에 기본 정보 저장
			System.out.println("=== users_info 테이블에 기본 정보 저장 시작 ===");

			// 기존 사용자 정보 조회 (회원가입 플로우에서 입력한 정보 보존)
			UserVo queryVo = new UserVo();
			queryVo.setUserId(userId);
			UserVo existingUserInfo = userService.selectUserInfoByUserId(queryVo);

			// 기존 데이터가 있으면 병합, 없으면 새로 생성
			UserVo userInfoVo = existingUserInfo != null ? existingUserInfo : new UserVo();
			userInfoVo.setUserId(userId);

			// 생년월일 파싱 및 저장
			if (userVo.getBirthDate() != null && !userVo.getBirthDate().trim().isEmpty()) {
				try {
					String birthDateStr = userVo.getBirthDate();
					if (birthDateStr.length() == 8) {
						// YYYYMMDD 형태를 YYYY-MM-DD로 변환
						String formattedDate = birthDateStr.substring(0, 4) + "-" + birthDateStr.substring(4, 6) + "-"
								+ birthDateStr.substring(6, 8);
						userInfoVo.setBirthDate(formattedDate);
						System.out.println("생년월일 저장: " + formattedDate);
					}
				} catch (Exception e) {
					System.out.println("생년월일 파싱 오류: " + e.getMessage());
				}
			}

			// 성별 저장
			if (userVo.getGender() != null && !userVo.getGender().trim().isEmpty()) {
				String gender = userVo.getGender();
				if ("남자".equals(gender)) {
					userInfoVo.setGender("MAN");
				} else if ("여자".equals(gender)) {
					userInfoVo.setGender("WOMAN");
				}
				System.out.println("성별 저장: " + userInfoVo.getGender());
			}

			// 휴대폰번호는 현재 users_info에 필드가 없으므로 생략
			// 향후 필요시 필드 추가 가능

			// users_info 테이블에 기본 정보 저장 (기존 데이터와 병합)
			try {
				userService.insertOrUpdateUserInfo(userInfoVo);
				System.out.println("=== users_info 테이블에 기본 정보 저장 완료 (기존 데이터 보존) ===");

				// 저장된 데이터 확인을 위한 로그
				if (existingUserInfo != null) {
					System.out.println("기존 연봉 정보 보존: " + existingUserInfo.getYearSalary());
					System.out.println("기존 경력 정보 보존: " + existingUserInfo.getCareer());
					System.out.println("기존 지역 정보 보존: " + existingUserInfo.getPreferredLocations());
				}
			} catch (Exception e) {
				System.out.println("users_info 저장 오류: " + e.getMessage());
				e.printStackTrace();
			}

			// 세션에 사용자 ID 저장 (Integer 타입으로 저장)
			HttpSession session = request.getSession();
			session.setAttribute("userId", Integer.valueOf(userId));
			session.setAttribute("userEmail", userVo.getEmail());
			session.setAttribute("userName", userVo.getName());
			System.out.println("세션에 사용자 정보 저장 완료: userId=" + userId + " (Integer 타입)");

			UserVo result = new UserVo();
			result.setUserId(userId);
			result.setEmail(userVo.getEmail());
			result.setName(userVo.getName());
			result.setRole("SUCCESS"); // 성공 표시

			AppLog.debug("- 회원가입 완료 : 사용자 ID " + userId);

			return result;

		} catch (Exception e) {
			System.out.println("=== 회원가입 실패 ===");
			System.out.println("오류 메시지: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 프로필 이미지를 업로드한다.
	 *
	 * @param request MultipartHttpServletRequest 요청 객체
	 * @return 업로드 결과
	 * @throws Exception
	 */
	@ElService(key = "USProfileImageUpload")
	@RequestMapping(value = { "USProfileImageUpload", "USProfileImageUpload.pwkjson" })
	@ElDescription(sub = "프로필 이미지 업로드", desc = "사용자의 프로필 이미지를 업로드하고 서버에 저장한다.")
	public Map<String, Object> uploadProfileImage(MultipartHttpServletRequest request) throws Exception {
		System.out.println("=== ProWorks5 프로필 이미지 업로드 요청 시작 ===");

		Map<String, Object> result = new HashMap<>();

		try {
			// 사용자 ID 추출
			String userIdStr = request.getParameter("userId");
			if (userIdStr == null || userIdStr.trim().isEmpty()) {
				throw new IllegalArgumentException("사용자 ID가 필요합니다.");
			}

			Long userId = Long.parseLong(userIdStr);
			System.out.println("사용자 ID: " + userId);

			// 업로드된 파일 추출
			MultipartFile file = request.getFile("profileImage");
			if (file == null || file.isEmpty()) {
				throw new IllegalArgumentException("업로드할 파일이 없습니다.");
			}

			// 파일 유효성 검사
			String originalFileName = file.getOriginalFilename();
			String contentType = file.getContentType();
			long fileSize = file.getSize();

			System.out.println("업로드 파일 정보:");
			System.out.println("- 원본 파일명: " + originalFileName);
			System.out.println("- Content Type: " + contentType);
			System.out.println("- 파일 크기: " + fileSize + " bytes");

			// 파일 타입 검사
			if (!contentType.startsWith("image/")) {
				throw new IllegalArgumentException("이미지 파일만 업로드 가능합니다.");
			}

			// 파일 크기 검사 (5MB = 5 * 1024 * 1024 bytes)
			if (fileSize > 5 * 1024 * 1024) {
				throw new IllegalArgumentException("파일 크기는 5MB 이하여야 합니다.");
			}

			// 파일 확장자 추출
			String fileExtension = "";
			if (originalFileName != null && originalFileName.contains(".")) {
				fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
			}

			// 새로운 파일명 생성 (profile_userId_timestamp.확장자)
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			String timestamp = dateFormat.format(new Date());
			String newFileName = "profile_" + userId + "_" + timestamp + fileExtension;

			// 저장 디렉토리 설정
			String uploadDir = request.getSession().getServletContext().getRealPath("/images/profile/");
			System.out.println("===== 업로드 디렉토리 경로 상세 정보 =====");
			System.out.println("getRealPath(\"/images/profile/\"): " + uploadDir);
			System.out.println("ServletContext 경로: " + request.getSession().getServletContext().getContextPath());
			System.out.println("작업 디렉토리: " + System.getProperty("user.dir"));
			System.out.println("==========================================");

			File uploadDirectory = new File(uploadDir);
			if (!uploadDirectory.exists()) {
				uploadDirectory.mkdirs();
				System.out.println("업로드 디렉토리 생성: " + uploadDir);
			} else {
				System.out.println("업로드 디렉토리 이미 존재함: " + uploadDir);
			}

			// 파일 저장
			File savedFile = new File(uploadDirectory, newFileName);
			try (InputStream inputStream = file.getInputStream();
					FileOutputStream outputStream = new FileOutputStream(savedFile)) {

				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
			}

			System.out.println("파일 저장 완료: " + savedFile.getAbsolutePath());

			// 데이터베이스에 파일명 저장
			userService.updateProfileImage(userId, newFileName);

			// 성공 응답
			result.put("success", true);
			result.put("fileName", newFileName);
			result.put("filePath", "/InsWebApp/images/profile/" + newFileName);
			result.put("message", "프로필 이미지가 성공적으로 업로드되었습니다.");

			System.out.println("=== 프로필 이미지 업로드 성공 ===");
			System.out.println("저장된 파일명: " + newFileName);

		} catch (Exception e) {
			System.out.println("=== 프로필 이미지 업로드 실패 ===");
			System.out.println("오류 메시지: " + e.getMessage());
			e.printStackTrace();

			result.put("success", false);
			result.put("message", e.getMessage());
		}

		return result;
	}

	/**
	 * 사용자 추가 정보를 저장한다.
	 *
	 * @param request 요청 정보 HttpServletRequest
	 * @return 저장 결과
	 * @throws Exception
	 */
	@ElService(key = "USUpdateAdditionalInfo")
	@RequestMapping(value = { "USUpdateAdditionalInfo", "USUpdateAdditionalInfo.pwkjson" })
	@ElDescription(sub = "사용자 프로필 정보 저장", desc = "ProfileAdditional에서 입력된 자기소개와 프로필 이미지 정보를 users_info 테이블에 저장한다. 기존 데이터는 유지하고 전달된 필드만 업데이트한다.")
	public Map<String, Object> updateAdditionalInfo(HttpServletRequest request) throws Exception {
		Map<String, Object> result = new HashMap<>();

		try {
			// JSON 문자열 읽기
			BufferedReader reader = request.getReader();
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			String jsonString = sb.toString();

			System.out.println("=== 프로필 정보 저장 요청 JSON ===");
			System.out.println(jsonString);

			if (jsonString == null || jsonString.trim().isEmpty()) {
				result.put("success", false);
				result.put("message", "요청 데이터가 비어있습니다.");
				return result;
			}

			// JSON 파싱
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(jsonString);

			// 세션에서 사용자 ID 가져오기
			HttpSession session = request.getSession();
			Integer sessionUserId = (Integer) session.getAttribute("userId");
			if (sessionUserId == null) {
				System.out.println("세션에서 사용자 ID를 찾을 수 없음");
				result.put("success", false);
				result.put("message", "로그인이 필요합니다.");
				return result;
			}

			int userId = sessionUserId.intValue();
			System.out.println("세션에서 가져온 사용자 ID: " + userId);

			// 기존 사용자 정보 조회 (기존 데이터 유지를 위해)
			UserVo queryVo = new UserVo();
			queryVo.setUserId(userId);
			UserVo existingUserVo = userService.selectUser(queryVo);

			// 기존 데이터가 있으면 사용, 없으면 새로 생성
			UserVo userVo = existingUserVo != null ? existingUserVo : new UserVo();
			userVo.setUserId(userId);

			// 현재 페이지에서 전달된 데이터만 업데이트 (빈 값은 무시)
			if (rootNode.has("bio")) {
				String bio = rootNode.get("bio").asText();
				if (bio != null && !bio.trim().isEmpty()) {
					userVo.setBio(bio);
					System.out.println("자기소개 업데이트: " + bio);
				}
			}

			if (rootNode.has("profileImageName")) {
				String profileImageName = rootNode.get("profileImageName").asText();
				if (profileImageName != null && !profileImageName.trim().isEmpty()) {
					userVo.setProfileImageName(profileImageName);
					System.out.println("프로필 이미지 업데이트: " + profileImageName);
				}
			}

			// users_info 테이블에 데이터 저장 또는 업데이트 (기존 데이터 보존)
			userService.insertOrUpdateUserInfo(userVo);

			result.put("success", true);
			result.put("message", "프로필 정보가 성공적으로 저장되었습니다.");
			result.put("userId", userId);

			System.out.println("프로필 정보 저장 완료 (기존 데이터 보존): userId=" + userId);

		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("message", "서버 오류가 발생했습니다: " + e.getMessage());
		}

		return result;
	}

	/**
	 * 사용자 정보를 전체적으로 수정한다.
	 *
	 * @param request 요청 정보 HttpServletRequest
	 * @return 수정 결과
	 * @throws Exception
	 */
	@ElService(key = "USUpdateUserInfo")
	@RequestMapping(value = { "USUpdateUserInfo", "USUpdateUserInfo.pwkjson" }, method = RequestMethod.POST)
	@ElDescription(sub = "사용자 정보 수정", desc = "개인정보 수정 페이지에서 사용자 정보를 전체적으로 수정한다.")
	public Map<String, Object> updateUserInfo(HttpServletRequest request) throws Exception {
		Map<String, Object> result = new HashMap<>();

		try {
			// JSON 문자열 읽기
			BufferedReader reader = request.getReader();
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			String jsonString = sb.toString();

			System.out.println("=== 사용자 정보 수정 요청 JSON ===");
			System.out.println(jsonString);

			if (jsonString == null || jsonString.trim().isEmpty()) {
				result.put("result", "fail");
				result.put("message", "요청 데이터가 비어있습니다.");
				return result;
			}

			// JSON 파싱
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(jsonString);

			// 세션에서 사용자 ID 가져오기
			ProworksUserHeader userHeader = null;
			try {
				userHeader = (ProworksUserHeader) ControllerContextUtil.getUserHeader();
			} catch (Exception e) {
				System.out.println("사용자 헤더 조회 중 오류: " + e.getMessage());
			}

			if (userHeader == null) {
				System.out.println("사용자 헤더를 찾을 수 없음");
				result.put("result", "fail");
				result.put("message", "로그인이 필요합니다.");
				return result;
			}

			int userId = userHeader.getAccountId();
			System.out.println("사용자 헤더에서 가져온 사용자 ID: " + userId);

			// 기존 사용자 정보 조회
			UserVo queryVo = new UserVo();
			queryVo.setUserId(userId);
			UserVo existingUserVo = userService.selectUser(queryVo);

			if (existingUserVo == null) {
				result.put("result", "fail");
				result.put("message", "사용자 정보를 찾을 수 없습니다.");
				return result;
			}

			// 수정할 데이터 추출 (dma_userInfoVo 노드에서)
			JsonNode userInfoNode = rootNode.has("dma_userInfoVo") ? rootNode.get("dma_userInfoVo") : rootNode;

			// users 테이블 정보 수정
			if (userInfoNode.has("name")) {
				String name = userInfoNode.get("name").asText();
				if (name != null && !name.trim().isEmpty()) {
					existingUserVo.setName(name);
					userService.updateUser(existingUserVo);
					System.out.println("이름 업데이트: " + name);
				}
			}

			// users_info 테이블 정보 수정
			UserVo userInfoVo = new UserVo();
			userInfoVo.setUserId(userId);

			// 경력
			if (userInfoNode.has("career")) {
				String career = userInfoNode.get("career").asText();
				if (career != null && !career.trim().isEmpty()) {
					userInfoVo.setCareer(career);
					System.out.println("경력 업데이트: " + career);
				}
			}

			// 직무분야
			if (userInfoNode.has("currentPosition")) {
				String currentPosition = userInfoNode.get("currentPosition").asText();
				if (currentPosition != null && !currentPosition.trim().isEmpty()) {
					userInfoVo.setCurrentPosition(currentPosition);
					System.out.println("직무분야 업데이트: " + currentPosition);
				}
			}

			// 선호 지역
			if (userInfoNode.has("preferredLocations")) {
				String preferredLocations = userInfoNode.get("preferredLocations").asText();
				if (preferredLocations != null && !preferredLocations.trim().isEmpty()) {
					userInfoVo.setPreferredLocations(preferredLocations);
					System.out.println("선호 지역 업데이트: " + preferredLocations);
				}
			}

			// 희망연봉
			if (userInfoNode.has("yearSalary")) {
				int yearSalary = userInfoNode.get("yearSalary").asInt();
				userInfoVo.setYearSalary(yearSalary);
				System.out.println("희망연봉 업데이트: " + yearSalary);
			}

			// 자기소개
			if (userInfoNode.has("bio")) {
				String bio = userInfoNode.get("bio").asText();
				if (bio != null) {
					userInfoVo.setBio(bio);
					System.out.println("자기소개 업데이트: " + bio);
				}
			}

			// 기술스택
			if (userInfoNode.has("techStack") && !userInfoNode.get("techStack").isNull()) {

				JsonNode techNode = userInfoNode.get("techStack");
				List<Long> idList = new ArrayList<>();

				/* ① JSON 배열 처리 */
				if (techNode.isArray()) {
					for (JsonNode n : techNode) {
						if (n.isNumber() || n.isTextual()) { // 36 또는 "36"
							idList.add(n.asLong());
						} else if (n.isObject() && n.has("id")) { // { "id": 36 }
							idList.add(n.get("id").asLong());
						}
					}

					/* ② CSV 문자열 처리 (예: "36,7") */
				} else if (techNode.isTextual()) {
					String csv = techNode.asText();
					Arrays.stream(csv.split(",")).map(String::trim).filter(s -> !s.isEmpty()).map(Long::valueOf)
							.forEach(idList::add);
				}

				/* ③ 중복 제거 후 TechStackVo 리스트로 변환 */
				if (!idList.isEmpty()) {

					List<TechStackVo> tsVoList = idList.stream().distinct().map(id -> {
						TechStackVo ts = new TechStackVo();
						ts.setTechStackId(id); // ✅ 핵심 수정
						return ts;
					}).collect(Collectors.toList());

					userInfoVo.setTechStackVo(tsVoList); // ✅ user.vo 패키지 타입으로 전달
					System.out.println("techStackVo 업데이트: " + tsVoList);
				}
			}
			// users_info 테이블 업데이트
			userService.insertOrUpdateUserInfo(userInfoVo);

			result.put("result", "success");
			result.put("message", "사용자 정보가 성공적으로 수정되었습니다.");

			System.out.println("=== 사용자 정보 수정 완료 ===");

		} catch (Exception e) {
			System.out.println("=== 사용자 정보 수정 실패 ===");
			System.out.println("오류 메시지: " + e.getMessage());
			e.printStackTrace();
			result.put("result", "error");
			result.put("message", "시스템 오류가 발생했습니다: " + e.getMessage());
		}

		return result;
	}

	/**
	 * 사용자의 비밀번호를 변경한다.
	 *
	 * @param request HttpServletRequest 요청 객체
	 * @return 변경 결과
	 * @throws Exception
	 */
	@ElService(key = "USUpdatePassword")
	@RequestMapping(value = { "USUpdatePassword", "USUpdatePassword.pwkjson" }, method = RequestMethod.POST)
	@ElDescription(sub = "비밀번호 변경", desc = "사용자의 비밀번호를 변경한다.")
	public Map<String, Object> updatePassword(HttpServletRequest request) throws Exception {
		Map<String, Object> result = new HashMap<>();

		try {
			// JSON 문자열 읽기
			BufferedReader reader = request.getReader();
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			String jsonString = sb.toString();

			System.out.println("=== 비밀번호 변경 요청 JSON ===");
			System.out.println(jsonString);

			if (jsonString == null || jsonString.trim().isEmpty()) {
				result.put("result", "fail");
				result.put("message", "요청 데이터가 비어있습니다.");
				return result;
			}

			// JSON 파싱
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(jsonString);

			// 세션에서 사용자 ID 가져오기
			ProworksUserHeader userHeader = null;
			try {
				userHeader = (ProworksUserHeader) ControllerContextUtil.getUserHeader();
			} catch (Exception e) {
				System.out.println("사용자 헤더 조회 중 오류: " + e.getMessage());
			}

			if (userHeader == null) {
				System.out.println("사용자 헤더를 찾을 수 없음");
				result.put("result", "fail");
				result.put("message", "로그인이 필요합니다.");
				return result;
			}

			int userId = userHeader.getAccountId();

			// 비밀번호 데이터 추출
			JsonNode passwordNode = rootNode.has("dma_passwordChangeVo") ? rootNode.get("dma_passwordChangeVo")
					: rootNode;
			String currentPassword = passwordNode.has("currentPassword") ? passwordNode.get("currentPassword").asText()
					: null;
			String newPassword = passwordNode.has("newPassword") ? passwordNode.get("newPassword").asText() : null;
			String confirmPassword = passwordNode.has("confirmPassword") ? passwordNode.get("confirmPassword").asText()
					: null;

			if (currentPassword == null || newPassword == null || confirmPassword == null) {
				result.put("result", "fail");
				result.put("message", "필수 정보가 누락되었습니다.");
				return result;
			}

			if (!newPassword.equals(confirmPassword)) {
				result.put("result", "fail");
				result.put("message", "새 비밀번호가 일치하지 않습니다.");
				return result;
			}

			// 서비스 호출
			boolean success = userService.updatePassword(userId, currentPassword, newPassword);

			if (success) {
				result.put("result", "success");
				result.put("message", "비밀번호가 성공적으로 변경되었습니다.");
			} else {
				result.put("result", "fail");
				result.put("message", "현재 비밀번호가 일치하지 않습니다.");
			}

		} catch (Exception e) {
			System.out.println("=== 비밀번호 변경 실패 ===");
			System.out.println("오류 메시지: " + e.getMessage());
			e.printStackTrace();
			result.put("result", "error");
			result.put("message", "시스템 오류가 발생했습니다: " + e.getMessage());
		}

		return result;
	}

	/**
	 * 연봉 범위 문자열을 숫자로 변환한다.
	 * 
	 * @param salaryRange 연봉 범위 (예: "3,000만원 ~ 4,000만원")
	 * @return 연봉 중간값 (만원 단위)
	 */
	private int parseSalaryRange(String salaryRange) {
		if (salaryRange == null || salaryRange.trim().isEmpty()) {
			return 0;
		}

		try {
			// 전체 텍스트 매칭
			if (salaryRange.contains("2,000만원 ~ 2,500만원")) {
				return 2250; // 중간값
			} else if (salaryRange.contains("2,500만원 ~ 3,000만원")) {
				return 2750;
			} else if (salaryRange.contains("3,000만원 ~ 4,000만원")) {
				return 3500;
			} else if (salaryRange.contains("4,000만원 ~ 5,000만원")) {
				return 4500;
			} else if (salaryRange.contains("5,000만원 이상")) {
				return 5500;
			}
			// 축약된 값 매칭 추가
			else if (salaryRange.equals("2000-2500")) {
				return 2250;
			} else if (salaryRange.equals("2500-3000")) {
				return 2750;
			} else if (salaryRange.equals("3000-4000")) {
				return 3500;
			} else if (salaryRange.equals("4000-5000")) {
				return 4500;
			} else if (salaryRange.equals("5000+")) {
				return 5500;
			}
		} catch (Exception e) {
			System.out.println("연봉 범위 파싱 오류: " + e.getMessage());
		}

		return 0;
	}

	/**
	 * 사용자 지역 정보 업데이트
	 */
	@ElService(key = "USUpdateLocation")
	@RequestMapping(value = { "USUpdateLocation", "USUpdateLocation.pwkjson" }, method = RequestMethod.POST)
	public Map<String, Object> updateUserLocation(HttpServletRequest request) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			// JSON 문자열 읽기
			BufferedReader reader = request.getReader();
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			String jsonString = sb.toString();

			System.out.println("=== 지역 정보 업데이트 요청 JSON ===");
			System.out.println(jsonString);

			if (jsonString == null || jsonString.trim().isEmpty()) {
				resultMap.put("result", "fail");
				resultMap.put("message", "요청 데이터가 비어있습니다.");
				return resultMap;
			}

			// JSON 파싱
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(jsonString);

			// 세션에서 사용자 ID 가져오기
			HttpSession session = request.getSession();
			Integer sessionUserId = (Integer) session.getAttribute("userId");

			Integer userId;
			if (sessionUserId != null) {
				userId = sessionUserId;
				System.out.println("*** 세션에서 사용자 ID 사용: " + userId + " ***");
			} else {
				System.out.println("사용자 ID를 찾을 수 없음");
				resultMap.put("result", "fail");
				resultMap.put("message", "사용자 정보를 찾을 수 없습니다.");
				return resultMap;
			}

			// dma_location 노드에서 locations 추출
			String locations = "";
			if (rootNode.has("dma_location")) {
				JsonNode locationNode = rootNode.get("dma_location");
				if (locationNode.has("locations")) {
					locations = locationNode.get("locations").asText();
				} else if (locationNode.has("selectedLocations")) {
					locations = locationNode.get("selectedLocations").asText();
				}
			}

			System.out.println("지역 업데이트 - 최종 사용자 ID: " + userId + ", 지역: " + locations);

			// 기존 사용자 정보 조회 후 병합
			UserVo queryVo = new UserVo();
			queryVo.setUserId(userId);
			UserVo existingUserVo = userService.selectUser(queryVo);

			// 기존 데이터가 있으면 병합, 없으면 새로 생성
			UserVo userVo = existingUserVo != null ? existingUserVo : new UserVo();
			userVo.setUserId(userId);
			userVo.setPreferredLocations(locations);

			// 사용자 정보 업데이트 (insertOrUpdateUserInfo 사용)
			userService.insertOrUpdateUserInfo(userVo);

			resultMap.put("result", "success");
			resultMap.put("message", "지역 정보가 성공적으로 저장되었습니다.");

		} catch (Exception e) {
			System.out.println("updateUserLocation error: " + e.getMessage());
			e.printStackTrace();
			resultMap.put("result", "error");
			resultMap.put("message", "시스템 오류가 발생했습니다.");
		}

		return resultMap;
	}

	/**
	 * 사용자 연봉 정보 업데이트
	 */
	@ElService(key = "USUpdateSalary")
	@RequestMapping(value = { "USUpdateSalary", "USUpdateSalary.pwkjson" }, method = RequestMethod.POST)
	public Map<String, Object> updateUserSalary(HttpServletRequest request) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			// JSON 문자열 읽기
			BufferedReader reader = request.getReader();
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			String jsonString = sb.toString();

			System.out.println("=== 연봉 정보 업데이트 요청 JSON ===");
			System.out.println(jsonString);

			if (jsonString == null || jsonString.trim().isEmpty()) {
				resultMap.put("result", "fail");
				resultMap.put("message", "요청 데이터가 비어있습니다.");
				return resultMap;
			}

			// JSON 파싱
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(jsonString);

			// 세션에서 사용자 ID 가져오기
			HttpSession session = request.getSession();
			Integer sessionUserId = (Integer) session.getAttribute("userId");

			Integer userId;
			if (sessionUserId != null) {
				userId = sessionUserId;
				System.out.println("*** 세션에서 사용자 ID 사용: " + userId + " ***");
			} else {
				System.out.println("사용자 ID를 찾을 수 없음");
				resultMap.put("result", "fail");
				resultMap.put("message", "사용자 정보를 찾을 수 없습니다.");
				return resultMap;
			}

			// dma_salary 노드에서 salary 추출
			String salary = "";
			if (rootNode.has("dma_salary")) {
				JsonNode salaryNode = rootNode.get("dma_salary");
				if (salaryNode.has("salary")) {
					salary = salaryNode.get("salary").asText();
				} else if (salaryNode.has("selectedSalary")) {
					salary = salaryNode.get("selectedSalary").asText();
				}
			}

			System.out.println("연봉 업데이트 - 최종 사용자 ID: " + userId + ", 연봉: " + salary);

			// 기존 사용자 정보 조회 후 병합
			UserVo queryVo = new UserVo();
			queryVo.setUserId(userId);
			UserVo existingUserVo = userService.selectUser(queryVo);

			// 기존 데이터가 있으면 병합, 없으면 새로 생성
			UserVo userVo = existingUserVo != null ? existingUserVo : new UserVo();
			userVo.setUserId(userId);
			userVo.setSalaryRange(salary);
			// 연봉 범위를 숫자로 변환하여 저장
			int yearSalary = parseSalaryRange(salary);
			userVo.setYearSalary(yearSalary);

			// 사용자 정보 업데이트 (insertOrUpdateUserInfo 사용)
			userService.insertOrUpdateUserInfo(userVo);

			resultMap.put("result", "success");
			resultMap.put("message", "연봉 정보가 성공적으로 저장되었습니다.");

		} catch (Exception e) {
			System.out.println("updateUserSalary error: " + e.getMessage());
			e.printStackTrace();
			resultMap.put("result", "error");
			resultMap.put("message", "시스템 오류가 발생했습니다.");
		}

		return resultMap;
	}

	/**
	 * 사용자 경력 정보 업데이트
	 */
	@ElService(key = "USUpdateCareer")
	@RequestMapping(value = { "USUpdateCareer", "USUpdateCareer.pwkjson" }, method = RequestMethod.POST)
	public Map<String, Object> updateUserCareer(HttpServletRequest request) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			// JSON 문자열 읽기
			BufferedReader reader = request.getReader();
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			String jsonString = sb.toString();

			System.out.println("=== 경력 정보 업데이트 요청 JSON ===");
			System.out.println(jsonString);

			if (jsonString == null || jsonString.trim().isEmpty()) {
				resultMap.put("result", "fail");
				resultMap.put("message", "요청 데이터가 비어있습니다.");
				return resultMap;
			}

			// JSON 파싱
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(jsonString);

			// 세션에서 사용자 ID 가져오기
			HttpSession session = request.getSession();
			Integer sessionUserId = (Integer) session.getAttribute("userId");

			Integer userId;
			if (sessionUserId != null) {
				userId = sessionUserId;
				System.out.println("*** 세션에서 사용자 ID 사용: " + userId + " ***");
			} else {
				System.out.println("사용자 ID를 찾을 수 없음");
				resultMap.put("result", "fail");
				resultMap.put("message", "사용자 정보를 찾을 수 없습니다.");
				return resultMap;
			}

			// dma_career 노드에서 데이터 추출
			String career = "";
			String careerType = "";
			String careerPeriod = "";
			String bio = "";

			if (rootNode.has("dma_career")) {
				JsonNode careerNode = rootNode.get("dma_career");

				if (careerNode.has("career")) {
					career = careerNode.get("career").asText();
				}
				if (careerNode.has("careerType")) {
					careerType = careerNode.get("careerType").asText();
				}
				if (careerNode.has("careerPeriod")) {
					careerPeriod = careerNode.get("careerPeriod").asText();
				}
				if (careerNode.has("bio")) {
					bio = careerNode.get("bio").asText();
				} else if (careerNode.has("introduction")) {
					bio = careerNode.get("introduction").asText();
				}
			}

			System.out.println("경력 업데이트 - 최종 사용자 ID: " + userId + ", 경력: " + career + ", 타입: " + careerType + ", 기간: "
					+ careerPeriod);

			// 기존 사용자 정보 조회 후 병합
			UserVo queryVo = new UserVo();
			queryVo.setUserId(userId);
			UserVo existingUserVo = userService.selectUser(queryVo);

			// 기존 데이터가 있으면 병합, 없으면 새로 생성
			UserVo userVo = existingUserVo != null ? existingUserVo : new UserVo();
			userVo.setUserId(userId);

			// career 필드 값 설정 - 프론트에서 보낸 값 그대로 사용
			if (!career.trim().isEmpty()) {
				userVo.setCareer(career); // 프론트에서 설정한 career 값을 그대로 사용
			}

			// 자기소개 설정
			if (!bio.trim().isEmpty()) {
				userVo.setBio(bio);
			}

			// 사용자 정보 업데이트 (insertOrUpdateUserInfo 사용)
			userService.insertOrUpdateUserInfo(userVo);

			resultMap.put("result", "success");
			resultMap.put("message", "경력 정보가 성공적으로 저장되었습니다.");

		} catch (Exception e) {
			System.out.println("updateUserCareer error: " + e.getMessage());
			e.printStackTrace();
			resultMap.put("result", "error");
			resultMap.put("message", "시스템 오류가 발생했습니다.");
		}

		return resultMap;
	}

	/**
	 * 일반회원을 단건 조회 처리 한다.
	 *
	 * @param UserInfoVo userInfoVo 유저 상세페이지에서 사용할 값
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	@ElService(key = "US0002UpdView")
	@RequestMapping(value = "US0002UpdView")
	@ElDescription(sub = "일반회원 갱신 폼을 위한 조회", desc = "일반회원 상세보기 페이지의 조회를 담당한다.")
	public UserInfoVo selectUserDetail(UserInfoVo userInfoVo) throws Exception {
		UserInfoVo selectUserVo = userService.selectUserDetail(userInfoVo);
		return selectUserVo;
	}

	/**
	 * 공고에 이력서 지원처리를 한다.
	 *
	 * @param ApplicantVo 페이징 정보, 공고 ID
	 * @return 등록된 행의 수
	 * @throws Exception
	 */
	@ElService(key = "US0002List")
	@RequestMapping(value = "US0002List")
	@ElDescription(sub = "공고에 지원한 유저 출력", desc = "공고에 지원한 유저를 출력한다")
	public ApplicantListVo selectUsersByjobPostingId(ApplicantVo applicantVo) throws Exception {
		List<ApplicantDetailVo> detailList = userService.selectUsersByjobPostingId(applicantVo);
		ApplicantListVo resultListVo = new ApplicantListVo();
		resultListVo.setApplicantDetailVo(detailList);
		return resultListVo;
	}

	/**
	 * 기업의 매칭유저 검색을 조회한다.
	 *
	 * @param ApplicantVo 페이징 정보, 공고 ID
	 * @return 등록된 행의 수
	 * @throws Exception
	 */
	@ElService(key = "US0003List")
	@RequestMapping(value = "US0003List")
	@ElDescription(sub = "공고에 지원한 유저 출력", desc = "공고에 지원한 유저를 출력한다")
	public ScoutListVo getScoutUsersByPostId(ScoutVo scoutVo) throws Exception {
		return userService.getScoutUsersByPostId(scoutVo);
	}

	/**
	 * 유저의 성향검사 및 코드검사 여부를 가져온다.
	 *
	 * @param MatchingCheckedVo 유저 ID
	 * @return 유저의 성향검사 및 코드검사 여부
	 * @throws Exception
	 */
	@ElService(key = "US0001Match")
	@RequestMapping(value = "US0001Match")
	@ElDescription(sub = "해당 유저의 성향검사 및 코드검사 여부 확인", desc = "해당 유저의 성향검사 및 코드검사 여부를 가져온다")
	public MatchingCheckedVo selectMatchingChecked(MatchingCheckedVo matchingCheckedVo) throws Exception {
		return userService.selectMatchingChecked(matchingCheckedVo);
	}

	/**
	 * 이력서 PDF 파일을 업로드한다.
	 * 
	 * @param request MultipartHttpServletRequest
	 * @return 업로드 결과
	 * @throws Exception
	 */
	@ElService(key = "USUploadResume")
	@RequestMapping(value = { "USUploadResume", "USUploadResume.pwkjson" })
	@ElDescription(sub = "이력서 업로드", desc = "사용자의 이력서 PDF 파일을 업로드하고 서버에 저장한다.")
	public Map<String, Object> uploadResumeFile(MultipartHttpServletRequest request) throws Exception {
		System.out.println("=== ProWorks5 이력서 업로드 요청 시작 ===");

		Map<String, Object> result = new HashMap<>();

		try {
			// 사용자 ID 가져오기 - ProworksUserHeader 사용
			ProworksUserHeader userHeader = null;
			try {
				userHeader = (ProworksUserHeader) ControllerContextUtil.getUserHeader();
			} catch (Exception e) {
				System.out.println("사용자 헤더 조회 중 오류: " + e.getMessage());
			}
			
			// 헤더가 없는 경우 파라미터에서 확인 (폼 전송 방식 지원)
			int userId;
			if (userHeader != null) {
				userId = userHeader.getAccountId();
				System.out.println("사용자 헤더에서 가져온 사용자 ID: " + userId);
			} else {
				String userIdStr = request.getParameter("userId");
				if (userIdStr == null || userIdStr.trim().isEmpty()) {
					throw new IllegalArgumentException("사용자 ID가 필요합니다.");
				}
				userId = Integer.parseInt(userIdStr);
				System.out.println("파라미터에서 가져온 사용자 ID: " + userId);
			}

			// 업로드된 파일 추출
			MultipartFile file = request.getFile("resumeFile");
			if (file == null || file.isEmpty()) {
				throw new IllegalArgumentException("업로드할 파일이 없습니다.");
			}

			// 파일 유효성 검사
			String originalFileName = file.getOriginalFilename();
			String contentType = file.getContentType();
			long fileSize = file.getSize();

			System.out.println("업로드 파일 정보:");
			System.out.println("- 원본 파일명: " + originalFileName);
			System.out.println("- Content Type: " + contentType);
			System.out.println("- 파일 크기: " + fileSize + " bytes");

			// PDF 파일 검증 (확장자 및 MIME 타입 확인)
			String fileExt = "";
			if (originalFileName != null && originalFileName.lastIndexOf(".") > -1) {
				fileExt = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();
			}
			
			boolean isPdfByExt = "pdf".equals(fileExt);
			boolean isPdfByMime = (contentType != null) && (contentType.toLowerCase().contains("pdf"));
			
			if (!isPdfByExt && !isPdfByMime) {
				throw new IllegalArgumentException("PDF 파일만 업로드 가능합니다.");
			}

			// 파일 크기 검사 (5MB)
			if (fileSize > MAX_FILE_SIZE) {
				throw new IllegalArgumentException("파일 크기는 5MB 이하여야 합니다.");
			}

			// 저장 디렉토리 설정 (톰캣 서버 내부 경로)
			String uploadDir = request.getSession().getServletContext().getRealPath(RESUME_UPLOAD_DIR);
			System.out.println("===== 업로드 디렉토리 경로 상세 정보 =====");
			System.out.println("getRealPath(\"" + RESUME_UPLOAD_DIR + "\"): " + uploadDir);
			System.out.println("ServletContext 경로: " + request.getSession().getServletContext().getContextPath());
			System.out.println("작업 디렉토리: " + System.getProperty("user.dir"));
			System.out.println("==========================================");

			// 사용자별 디렉토리 생성 
			String userDirPath = uploadDir + "/" + userId;
			File userDir = new File(userDirPath);
			if (!userDir.exists()) {
				boolean success = userDir.mkdirs();
				if(!success) {
					System.out.println("디렉토리 생성 실패: " + userDirPath);
				} else {
					System.out.println("사용자 디렉토리 생성 성공: " + userDirPath);
				}
			}

			// 저장할 파일명 생성 (사용자ID_타임스탬프.pdf)
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String savedFileName = userId + "_" + timestamp + ".pdf";
			File savedFile = new File(userDir, savedFileName);

			// 파일 저장
			try {
				file.transferTo(savedFile);
				System.out.println("파일 저장 완료: " + savedFile.getAbsolutePath());
			} catch (Exception e) {
				System.out.println("파일 저장 실패: " + e.getMessage());
				throw e;
			}
			
			// 상대 경로 저장 (데이터베이스에 저장하기 위한 경로)
			String relativePath = RESUME_UPLOAD_DIR + "/" + userId + "/" + savedFileName;
			
			System.out.println("파일 저장 완료: " + savedFile.getAbsolutePath());
			System.out.println("상대 경로: " + relativePath);

			// 사용자 정보 업데이트 (resume_file_name 필드)
			UserVo userVo = new UserVo();
			userVo.setUserId(userId);
			userVo.setResumeFileName(relativePath);
			
			// 사용자 정보 업데이트
			userService.updateResumeFileName(userVo);
			
			// 결과 반환 - 경로 대신 원본 파일명만 반환
			result.put("result", "success");
			result.put("fileName", relativePath); // DB에 저장된 경로는 그대로 전달 (클라이언트에서 가공)
			result.put("originalFileName", originalFileName); // 원본 파일명 추가
			result.put("message", "이력서가 성공적으로 업로드되었습니다.");
			
		} catch (IllegalArgumentException e) {
			System.out.println("이력서 업로드 유효성 검사 오류: " + e.getMessage());
			result.put("result", "fail");
			result.put("message", e.getMessage());
		} catch (Exception e) {
			System.out.println("이력서 업로드 처리 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
			result.put("result", "fail");
			result.put("message", "이력서 업로드 중 오류가 발생했습니다.");
		}
		
	 * 사용자의 지원현황 목록을 조회한다.
	 *
	 * @param applicationHistoryVo 지원현황 조회 조건
	 * @return 지원현황 목록
	 * @throws Exception
	 */
	@ElService(key = "USApplicationHistoryList")
	@RequestMapping(value = "USApplicationHistoryList")
	@ElDescription(sub = "지원현황 목록조회", desc = "사용자의 지원현황 목록을 조회한다.")
	public ApplicationHistoryListVo selectApplicationHistoryList(ApplicationHistoryVo applicationHistoryVo)
			throws Exception {
		// 세션에서 사용자 ID 가져오기
		ProworksUserHeader userHeader = null;
		try {
			userHeader = (ProworksUserHeader) ControllerContextUtil.getUserHeader();
		} catch (Exception e) {
			System.out.println("사용자 헤더 조회 중 오류: " + e.getMessage());
		}

		if (userHeader != null) {
			applicationHistoryVo.setUserId(userHeader.getAccountId());
		}

		List<ApplicationHistoryVo> applicationList = userService.selectApplicationHistoryList(applicationHistoryVo);
		List<ApplicationHistoryVo> statsList = userService.selectApplicationHistoryStats(applicationHistoryVo);
		long totalCount = userService.selectApplicationHistoryCount(applicationHistoryVo);

		ApplicationHistoryListVo result = new ApplicationHistoryListVo();
		result.setApplicationHistoryList(applicationList);
		result.setApplicationStatsList(statsList);
		result.setTotalCount(totalCount);
		result.setPageSize(applicationHistoryVo.getPageSize());
		result.setPageIndex(applicationHistoryVo.getPageIndex());

		return result;
	}

	/**
	 * 이력서 PDF 파일을 조회한다.
	 * 
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws Exception
	 */
	@ElService(key = "USViewResume")
	@RequestMapping(value = { "USViewResume", "USViewResume.pwkjson" })
	@ElDescription(sub = "이력서 조회", desc = "사용자의 이력서 PDF 파일을 조회한다.")
	public void viewResumeFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("=== ProWorks5 이력서 조회 요청 시작 ===");
		
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		try {
			// 요청 파라미터 확인
			String filePath = request.getParameter("filePath");
			
			// 파라미터 유효성 검사
			if (filePath == null || filePath.trim().isEmpty()) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "파일 경로가 필요합니다.");
				return;
			}
			
			// 파일 경로 확인 및 보안 검사
			if (!filePath.startsWith(RESUME_UPLOAD_DIR)) {
				System.out.println("잘못된 파일 경로: " + filePath);
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 파일 경로입니다.");
				return;
			}
			
			// 파일 경로에서 상대 경로만 추출
			if (filePath.startsWith("/")) {
				filePath = filePath.substring(1);
			}
			
			// 실제 파일 경로 생성
			String realPath = request.getSession().getServletContext().getRealPath(filePath);
			File file = new File(realPath);
			
			// 파일 존재 여부 확인
			if (!file.exists() || !file.isFile()) {
				System.out.println("파일을 찾을 수 없음: " + realPath);
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "파일을 찾을 수 없습니다.");
				return;
			}
			
			// 파일 유형 및 헤더 설정
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "inline; filename=\"resume.pdf\"");
			response.setHeader("Content-Length", String.valueOf(file.length()));
			
			// 파일 전송
			inputStream = new FileInputStream(file);
			outputStream = response.getOutputStream();
			
			byte[] buffer = new byte[8192];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			
			System.out.println("이력서 파일 전송 완료: " + filePath);
		} catch (Exception e) {
			System.out.println("이력서 조회 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "파일을 처리하는 중 오류가 발생했습니다.");
		} finally {
			// 리소스 정리
			if (inputStream != null) {
				try { inputStream.close(); } catch (Exception e) {}
			}
			if (outputStream != null) {
				try { outputStream.close(); } catch (Exception e) {}
			}
		}
	}
	
	/**
	 * 이력서 PDF 파일 정보를 조회한다.
	 * 
	 * @param request HttpServletRequest
	 * @return 이력서 파일 정보
	 * @throws Exception
	 */
	@ElService(key = "USGetResumeInfo")
	@RequestMapping(value = { "USGetResumeInfo", "USGetResumeInfo.pwkjson" })
	@ElDescription(sub = "이력서 정보 조회", desc = "사용자의 이력서 PDF 파일 정보를 조회한다.")
	public Map<String, Object> getResumeInfo(HttpServletRequest request) throws Exception {
		System.out.println("=== ProWorks5 이력서 정보 조회 요청 시작 ===");
		
		Map<String, Object> result = new HashMap<>();
		
		try {
			// 사용자 ID 가져오기 - URL 파라미터에서 확인
			int userId = 0;
			String userIdParam = request.getParameter("userId");
			
			if (userIdParam != null && !userIdParam.trim().isEmpty()) {
				try {
					userId = Integer.parseInt(userIdParam);
					System.out.println("파라미터에서 가져온 사용자 ID: " + userId);
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException("유효하지 않은 사용자 ID 형식입니다: " + userIdParam);
				}
			} else {
				// POST 요청 본문에서도 확인 시도
				try {
					BufferedReader reader = request.getReader();
					StringBuilder sb = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						sb.append(line);
					}
					String body = sb.toString();
					System.out.println("요청 본문: " + body);
					
					// JSON 형태인지 확인하고 파싱 시도 (간소화된 방식)
					if (body != null && !body.trim().isEmpty() && body.contains("userId")) {
						String userIdStart = "\"userId\"";
						int startIdx = body.indexOf(userIdStart);
						if (startIdx > 0) {
							int valueStart = body.indexOf(":", startIdx);
							if (valueStart > 0) {
								int valueEnd = body.indexOf(",", valueStart);
								if (valueEnd < 0) valueEnd = body.indexOf("}", valueStart);
								if (valueEnd > 0) {
									String value = body.substring(valueStart + 1, valueEnd).trim();
									// 숫자만 추출
									value = value.replaceAll("[^0-9]", "");
									if (!value.isEmpty()) {
										userId = Integer.parseInt(value);
										System.out.println("요청 본문에서 가져온 사용자 ID: " + userId);
									}
								}
							}
						}
				}
			} catch (Exception e) {
					System.out.println("요청 본문 처리 중 예외 발생: " + e.getMessage());
					// 요청 본문 처리 중 예외가 발생해도 계속 진행
				}
			}
			
			// 사용자 ID가 없는 경우 오류 반환
			if (userId == 0) {
				throw new IllegalArgumentException("사용자 ID가 필요합니다. URL 파라미터로 userId를 전달해 주세요.");
			}
			
			// 사용자 정보 조회
			UserInfoVo userInfoVo = new UserInfoVo();
			userInfoVo.setAccountId(userId);
			UserInfoVo userInfo = userService.selectUserDetail(userInfoVo);
			
			if (userInfo == null) {
				throw new IllegalArgumentException("사용자 ID " + userId + "에 대한 정보를 찾을 수 없습니다.");
			}
			
			String resumeFileName = userInfo.getResumeFileName();
			System.out.println("사용자 " + userId + "의 이력서 파일명: " + (resumeFileName != null ? resumeFileName : "없음"));
			
			// 이력서 파일 정보 설정
			if (resumeFileName != null && !resumeFileName.trim().isEmpty()) {
				// 파일 경로에서 파일명 추출
				String fileName = resumeFileName;
				if (resumeFileName.contains("/")) {
					fileName = resumeFileName.substring(resumeFileName.lastIndexOf("/") + 1);
				}
				
				// 원본 파일명 생성 (userId_timestamp.pdf 형식에서 timestamp.pdf 형식으로)
				String displayName = fileName;
				if (fileName.contains("_")) {
					displayName = fileName.substring(fileName.indexOf("_") + 1);
				}
				
				// 결과 설정 (필수 필드만 포함)
				result.put("result", "success");
				result.put("hasResume", true);
				result.put("resumeFileName", resumeFileName);
				result.put("displayName", "이력서_" + displayName);
				result.put("viewUrl", request.getContextPath() + "/USViewResume.pwkjson?filePath=" + resumeFileName);
				
				System.out.println("이력서 정보 조회 성공");
			} else {
				result.put("result", "success");
				result.put("hasResume", false);
				result.put("message", "등록된 이력서가 없습니다.");
				
				System.out.println("이력서가 등록되지 않은 사용자");
			}
			
		} catch (IllegalArgumentException e) {
			System.out.println("이력서 정보 조회 유효성 검사 오류: " + e.getMessage());
			result.put("result", "fail");
			result.put("message", e.getMessage());
		} catch (Exception e) {
			System.out.println("이력서 정보 조회 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
			result.put("result", "fail");
			result.put("message", "이력서 정보 조회 중 서버 오류가 발생했습니다: " + e.getMessage());
		}
		
		System.out.println("=== ProWorks5 이력서 정보 조회 요청 종료 ===");
		System.out.println("응답 결과: " + result);
		return result;
   
   /*
	 * 지원현황 상세정보를 조회한다.
	 *
	 * @param applicationHistoryVo 지원현황 조회 조건
	 * @return 지원현황 상세정보
	 * @throws Exception
	 */
	@ElService(key = "USApplicationHistoryDetail")
	@RequestMapping(value = "USApplicationHistoryDetail")
	@ElDescription(sub = "지원현황 상세조회", desc = "지원현황의 상세정보를 조회한다.")
	public ApplicationHistoryVo selectApplicationHistoryDetail(ApplicationHistoryVo applicationHistoryVo)
			throws Exception {
		return userService.selectApplicationHistoryDetail(applicationHistoryVo);
	}

	/**
	 * 지원현황을 등록한다.
	 *
	 * @param applicationHistoryVo 지원현황 정보
	 * @return 등록 결과
	 * @throws Exception
	 */
	@ElService(key = "USApplicationHistoryInsert")
	@RequestMapping(value = "USApplicationHistoryInsert")
	@ElDescription(sub = "지원현황 등록", desc = "새로운 지원현황을 등록한다.")
	public int insertApplicationHistory(ApplicationHistoryVo applicationHistoryVo) throws Exception {
		// 세션에서 사용자 ID 가져오기
		ProworksUserHeader userHeader = null;
		try {
			userHeader = (ProworksUserHeader) ControllerContextUtil.getUserHeader();
		} catch (Exception e) {
			System.out.println("사용자 헤더 조회 중 오류: " + e.getMessage());
		}

		if (userHeader != null) {
			applicationHistoryVo.setUserId(userHeader.getAccountId());
		}

		return userService.insertApplicationHistory(applicationHistoryVo);
	}

	/**
	 * 지원현황을 수정한다.
	 *
	 * @param applicationHistoryVo 지원현황 정보
	 * @return 수정 결과
	 * @throws Exception
	 */
	@ElService(key = "USApplicationHistoryUpdate")
	@RequestMapping(value = "USApplicationHistoryUpdate")
	@ElDescription(sub = "지원현황 수정", desc = "지원현황 정보를 수정한다.")
	public int updateApplicationHistory(ApplicationHistoryVo applicationHistoryVo) throws Exception {
		return userService.updateApplicationHistory(applicationHistoryVo);
	}

	/**
	 * 지원현황을 삭제한다.
	 *
	 * @param applicationHistoryVo 지원현황 정보
	 * @return 삭제 결과
	 * @throws Exception
	 */
	@ElService(key = "USApplicationHistoryDelete")
	@RequestMapping(value = "USApplicationHistoryDelete")
	@ElDescription(sub = "지원현황 삭제", desc = "지원현황을 삭제한다.")
	public int deleteApplicationHistory(ApplicationHistoryVo applicationHistoryVo) throws Exception {
		return userService.deleteApplicationHistory(applicationHistoryVo);
	}
}
