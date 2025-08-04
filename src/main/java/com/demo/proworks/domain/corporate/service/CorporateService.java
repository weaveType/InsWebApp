package com.demo.proworks.domain.corporate.service;

import java.util.List;
import java.util.Map;

import com.demo.proworks.common.vo.EmailVo;
import com.demo.proworks.domain.corporate.vo.CorporateListVo;
import com.demo.proworks.domain.corporate.vo.CorporateMainListVo;
import com.demo.proworks.domain.corporate.vo.CorporateSearchVo;
import com.demo.proworks.domain.corporate.vo.CorporateVo;
import com.demo.proworks.domain.corporate.vo.IndusrtyVoList;

/**
 * @subject : 회사정보 관련 처리를 담당하는 인터페이스
 * @description : 회사정보 관련 처리를 담당하는 인터페이스
 * @author : Inswave
 * @since : 2025/07/03
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/03 Inswave 최초 생성
 * 
 */
public interface CorporateService {

	/**
	 * 회사정보 페이징 처리하여 목록을 조회한다.
	 *
	 * @param corporateVo 회사정보 CorporateVo
	 * @return 회사정보 목록 List<CorporateVo>
	 * @throws Exception
	 */
	public CorporateListVo selectListCorporate(CorporateVo corporateVo) throws Exception;

	/**
	 * 조회한 회사정보 전체 카운트
	 * 
	 * @param corporateVo 회사정보 CorporateVo
	 * @return 회사정보 목록 전체 카운트
	 * @throws Exception
	 */
	public long selectListCountCorporate(CorporateVo corporateVo) throws Exception;

	/**
	 * 회사정보를 상세 조회한다.
	 *
	 * @param corporateVo 회사정보 CorporateVo
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	public CorporateVo selectCorporate(CorporateVo corporateVo) throws Exception;

	/**
	 * 이메일로 회사정보를 상세 조회한다.
	 *
	 * @param corporateVo 회사정보 CorporateVo
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	public CorporateVo selectCorporateByEmail(EmailVo email) throws Exception;

	/**
	 * 회사정보를 등록 처리 한다.
	 *
	 * @param corporateVo 회사정보 CorporateVo
	 * @return 번호
	 * @throws Exception
	 */
	public int insertCorporate(CorporateVo corporateVo) throws Exception;

	/**
	 * 회사정보를 갱신 처리 한다.
	 *
	 * @param corporateVo 회사정보 CorporateVo
	 * @return 번호
	 * @throws Exception
	 */
	public int updateCorporate(CorporateVo corporateVo) throws Exception;

	/**
	 * 회사정보를 삭제 처리 한다.
	 *
	 * @param corporateVo 회사정보 CorporateVo
	 * @return 번호
	 * @throws Exception
	 */
	public int deleteCorporate(CorporateVo corporateVo) throws Exception;

	/**
	 * 산업정보 목록을 조회 한다.
	 *
	 * @param corporateVo 회사정보 CorporateVo
	 * @return 번호
	 * @throws Exception
	 */
	public IndusrtyVoList industryList() throws Exception;

	/**
	 * 이메일로 회사정보를 갱신한다.
	 * 
	 * @param CorporateVo 회사정보
	 * @return 번호
	 * @throws ElException
	 */
	public int updateCorporateByEmail(CorporateVo vo) throws Exception;

	/**
	 * 권한 ID로 회사이름을 가져온다.
	 *
	 * @param corporateVo 회사정보 CorporateVo
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	public CorporateMainListVo selectCorporateMainList(CorporateSearchVo vo) throws Exception;

	/**
	 * 유저 ID로 회사 ID를 가져온다.
	 *
	 * @param userId 유저 ID
	 * @return 회사 ID
	 * @throws Exception
	 */
	public Long getCompanyIdByUserId(int userId) throws Exception;
}
