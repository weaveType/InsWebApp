package com.demo.proworks.domain.corporate.web;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.proworks.common.enumType.IndustryType;
import com.demo.proworks.common.vo.EmailVo;
import com.demo.proworks.domain.corporate.service.CorporateService;
import com.demo.proworks.domain.corporate.vo.CorporateVo;
import com.demo.proworks.domain.corporate.vo.IndusrtyVoList;
import com.demo.proworks.domain.corporate.vo.CorporateListVo;
import com.demo.proworks.domain.corporate.vo.CorporateSearchVo;
import com.demo.proworks.domain.corporate.vo.CorporateMainListVo;

import com.inswave.elfw.annotation.ElDescription;
import com.inswave.elfw.annotation.ElService;
import com.inswave.elfw.annotation.ElValidator;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @subject : 회사정보 관련 처리를 담당하는 컨트롤러
 * @description : 회사정보 관련 처리를 담당하는 컨트롤러
 * @author : Inswave
 * @since : 2025/07/03
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/03 Inswave 최초 생성
 * 
 */
@Controller
public class CorporateController {

	/** CorporateService */
	@Resource(name = "corporateServiceImpl")
	private CorporateService corporateService;

	/**
	 * 회사정보 목록을 조회합니다.
	 *
	 * @param corporateVo 회사정보
	 * @return 목록조회 결과
	 * @throws Exception
	 */
	@ElService(key = "CP0001List")
	@RequestMapping(value = "CP0001List")
	@ElDescription(sub = "회사정보 목록조회", desc = "페이징을 처리하여 회사정보 목록 조회를 한다.")
	public CorporateListVo selectListCorporate(CorporateVo corporateVo) throws Exception {

		List<CorporateVo> corporateList = corporateService.selectListCorporate(corporateVo);
		long totCnt = corporateService.selectListCountCorporate(corporateVo);

		CorporateListVo retCorporateList = new CorporateListVo();
		retCorporateList.setCorporateVoList(corporateList);
		retCorporateList.setTotalCount(totCnt);
		retCorporateList.setPageSize(corporateVo.getPageSize());
		retCorporateList.setPageIndex(corporateVo.getPageIndex());

		return retCorporateList;
	}

	/**
	 * 회사정보을 단건 조회 처리 한다.
	 *
	 * @param corporateVo 회사정보
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	@ElService(key = "CP0001UpdView")
	@RequestMapping(value = "CP0001UpdView")
	@ElDescription(sub = "회사정보 갱신 폼을 위한 조회", desc = "회사정보 갱신 폼을 위한 조회를 한다.")
	public CorporateVo selectCorporate(CorporateVo corporateVo) throws Exception {
		CorporateVo selectCorporateVo = corporateService.selectCorporate(corporateVo);

		return selectCorporateVo;
	}

	/**
	 * 이메일로 회사정보를 단건 조회 처리 한다.
	 *
	 * @param corporateVo 회사정보
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	@ElService(key = "CP0002UpdView")
	@RequestMapping(value = "CP0002UpdView")
	@ElDescription(sub = "회사정보 갱신 폼을 위한 조회", desc = "회사정보 갱신 폼을 위한 조회를 한다.")
	public CorporateVo selectCorporateByEmail(EmailVo emailVo) throws Exception {

		CorporateVo selectCorporateVo = corporateService.selectCorporateByEmail(emailVo);

		if (selectCorporateVo == null) {
			throw new IllegalArgumentException("입력하신 이메일에 해당하는 회사 정보가 없습니다.");
		}

		return selectCorporateVo;
	}

	/**
	 * 회사정보를 등록 처리 한다.
	 *
	 * @param corporateVo 회사정보
	 * @throws Exception
	 */
	@ElService(key = "CP0001Ins")
	@RequestMapping(value = "CP0001Ins")
	@ElDescription(sub = "회사정보 등록처리", desc = "회사정보를 등록 처리 한다.")
	public void insertCorporate(CorporateVo corporateVo) throws Exception {
		corporateService.insertCorporate(corporateVo);
	}

	/**
	 * 회사정보를 갱신 처리 한다.
	 *
	 * @param corporateVo 회사정보
	 * @throws Exception
	 */
	@ElService(key = "CP0001Upd")
	@RequestMapping(value = "CP0001Upd")
	@ElValidator(errUrl = "/corporate/corporateRegister", errContinue = true)
	@ElDescription(sub = "회사정보 갱신처리", desc = "회사정보를 갱신 처리 한다.")
	public void updateCorporate(CorporateVo corporateVo) throws Exception {
		corporateService.updateCorporate(corporateVo);
	}

	/**
	 * 회사정보를 삭제 처리한다.
	 *
	 * @param corporateVo 회사정보
	 * @throws Exception
	 */
	@ElService(key = "CP0001Del")
	@RequestMapping(value = "CP0001Del")
	@ElDescription(sub = "회사정보 삭제처리", desc = "회사정보를 삭제 처리한다.")
	public void deleteCorporate(CorporateVo corporateVo) throws Exception {
		corporateService.deleteCorporate(corporateVo);
	}

	/**
	 * 산업 목록을 조회합니다.
	 *
	 * @return 산업 목록 조회 결과
	 * @throws Exception
	 */
	@ElService(key = "CP0001INDList")
	@RequestMapping(value = "CP0001INDList")
	@ElDescription(sub = "산업 목록조회", desc = "산업 목록 조회를 한다.")
	public IndusrtyVoList industryList() throws Exception {
		return corporateService.industryList();
	}

	/**
	 * 회사정보를 갱신 처리 한다.
	 *
	 * @param corporateVo 회사정보
	 * @throws Exception
	 */
	@ElService(key = "CP0002Upd")
	@RequestMapping(value = "CP0002Upd")
	@ElDescription(sub = "회사정보 갱신처리", desc = "이메일로 회사정보를 갱신 처리 한다.")
	public void updateCorporateByEmail(CorporateVo corporateVo) throws Exception {
		corporateService.updateCorporateByEmail(corporateVo);
	}

	/**
	 * 기업정보 목록을 조회합니다.
	 *
	 * @param corporateVo 회사정보
	 * @return 목록조회 결과
	 * @throws Exception
	 */
	@ElService(key = "CP0003List")
	@RequestMapping(value = "CP0003List")
	@ElDescription(sub = "기업정보 목록조회", desc = "메인에 출력할 기업정보 목록 조회를 한다.")
	public CorporateMainListVo selectCorporateMainList(CorporateSearchVo corporateSearchVo) throws Exception {
       return corporateService.selectCorporateMainList(corporateSearchVo);
            
    }
}
