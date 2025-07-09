package com.demo.proworks.domain.corporate.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.demo.proworks.domain.corporate.service.CorporateService;
import com.demo.proworks.domain.corporate.vo.CorporateMainListVo;
import com.demo.proworks.domain.corporate.vo.CorporateSearchVo;
import com.demo.proworks.domain.corporate.vo.CorporateVo;
import com.demo.proworks.domain.corporate.vo.IndusrtyVoList;
import com.demo.proworks.domain.user.dao.UserDAO;
import com.demo.proworks.domain.user.vo.UserVo;
import com.demo.proworks.common.enumType.IndustryType;
import com.demo.proworks.common.vo.EmailVo;
import com.demo.proworks.domain.corporate.dao.CorporateDAO;

/**
 * @subject : 회사정보 관련 처리를 담당하는 ServiceImpl
 * @description : 회사정보 관련 처리를 담당하는 ServiceImpl
 * @author : Inswave
 * @since : 2025/07/03
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/03 Inswave 최초 생성
 * 
 */
@Service("corporateServiceImpl")
public class CorporateServiceImpl implements CorporateService {

	@Resource(name = "corporateDAO")
	private CorporateDAO corporateDAO;

	@Resource(name = "userDAO")
	private UserDAO userDAO;

	@Resource(name = "messageSource")
	private MessageSource messageSource;

	/**
	 * 회사정보 목록을 조회합니다.
	 *
	 * @process 1. 회사정보 페이징 처리하여 목록을 조회한다. 2. 결과 List<CorporateVo>을(를) 리턴한다.
	 * 
	 * @param corporateVo 회사정보 CorporateVo
	 * @return 회사정보 목록 List<CorporateVo>
	 * @throws Exception
	 */
	public List<CorporateVo> selectListCorporate(CorporateVo corporateVo) throws Exception {
		List<CorporateVo> list = corporateDAO.selectListCorporate(corporateVo);

		return list;
	}

	/**
	 * 조회한 회사정보 전체 카운트
	 *
	 * @process 1. 회사정보 조회하여 전체 카운트를 리턴한다.
	 * 
	 * @param corporateVo 회사정보 CorporateVo
	 * @return 회사정보 목록 전체 카운트
	 * @throws Exception
	 */
	public long selectListCountCorporate(CorporateVo corporateVo) throws Exception {
		return corporateDAO.selectListCountCorporate(corporateVo);
	}

	/**
	 * 회사정보를 상세 조회한다.
	 *
	 * @process 1. 회사정보를 상세 조회한다. 2. 결과 CorporateVo을(를) 리턴한다.
	 * 
	 * @param corporateVo 회사정보 CorporateVo
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	public CorporateVo selectCorporate(CorporateVo corporateVo) throws Exception {
		CorporateVo resultVO = corporateDAO.selectCorporate(corporateVo);

		return resultVO;
	}

	/**
	 * 이메일로 회사정보를 상세 조회한다.
	 *
	 * @process 1. 회사정보를 상세 조회한다. 2. 결과 CorporateVo을(를) 리턴한다.
	 * 
	 * @param corporateVo 회사정보 CorporateVo
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	public CorporateVo selectCorporateByEmail(EmailVo emailVo) throws Exception {
		CorporateVo resultVO = corporateDAO.selectCorporateByEmail(emailVo);

		return resultVO;
	}

	/**
	 * 회사정보를 등록 처리 한다.
	 *
	 * @process 1. 회사정보를 등록 처리 한다.
	 * 
	 * @param corporateVo 회사정보 CorporateVo
	 * @return 번호
	 * @throws Exception
	 */
	public int insertCorporate(CorporateVo corporateVo) throws Exception {

		// 기업 유저의 회원가입
		UserVo userVo = new UserVo();
		userVo.setEmail(corporateVo.getEmail());
		userVo.setPassword(corporateVo.getPassword());
		userVo.setRole(corporateVo.getRole());
		userVo.setName(corporateVo.getName());

		// 기업 - 유저간 userId로 mapping
		userDAO.insertUser(userVo);

		corporateVo.setUserId(userVo.getUserId());
		return corporateDAO.insertCorporate(corporateVo);
	}

	/**
	 * 회사정보를 갱신 처리 한다.
	 *
	 * @process 1. 회사정보를 갱신 처리 한다.
	 * 
	 * @param corporateVo 회사정보 CorporateVo
	 * @return 번호
	 * @throws Exception
	 */
	public int updateCorporate(CorporateVo corporateVo) throws Exception {
		return corporateDAO.updateCorporate(corporateVo);
	}

	/**
	 * 회사정보를 삭제 처리 한다.
	 *
	 * @process 1. 회사정보를 삭제 처리 한다.
	 * 
	 * @param corporateVo 회사정보 CorporateVo
	 * @return 번호
	 * @throws Exception
	 */
	public int deleteCorporate(CorporateVo corporateVo) throws Exception {
		return corporateDAO.deleteCorporate(corporateVo);
	}

	/**
	 * 산업정보 목록을 조회 한다.
	 *
	 * @process 산업정보 목록을 조회 한다.
	 * 
	 */
	public IndusrtyVoList industryList() throws Exception {

		List<Map<String, String>> industryList = new ArrayList<>();

		for (IndustryType industry : IndustryType.values()) {
			Map<String, String> item = new HashMap<>();
			item.put("industryCode", industry.getCode());
			item.put("industryName", industry.getDescription());
			industryList.add(item);
		}

		IndusrtyVoList retIndustryList = new IndusrtyVoList();
		retIndustryList.setIndustryList(industryList);

		return retIndustryList;
	}

	/**
	 * 이메일로 회사정보를 갱신한다.
	 * 
	 * @param CorporateVo 회사정보
	 * @return 번호
	 * @throws ElException
	 */
	public int updateCorporateByEmail(CorporateVo vo) throws Exception {
		String hashedPassword = BCrypt.hashpw(vo.getPassword(), BCrypt.gensalt(12));
		vo.setPassword(hashedPassword);
		return corporateDAO.updateCorporateByEmail(vo);
	}

	/**
	 * roleId로 회사 이름을 가져온다.
	 *
	 * @process 1. 회사정보를 상세 조회한다. 2. 결과 CorporateVo을(를) 리턴한다.
	 * 
	 * @param corporateVo 회사정보 CorporateVo
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	public CorporateMainListVo selectCorporateMainList(CorporateSearchVo vo) throws Exception {
		CorporateMainListVo resultVO = new CorporateMainListVo();
		resultVO.setCorporateMainVo(corporateDAO.selectCorporateMainList(vo));
		resultVO.setTotalCount(corporateDAO.selectListCountCorporate(new CorporateVo()));
		
		return resultVO;
	}

}
