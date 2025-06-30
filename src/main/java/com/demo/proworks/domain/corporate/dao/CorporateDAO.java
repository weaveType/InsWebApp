package com.demo.proworks.domain.corporate.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.domain.corporate.vo.CorporateVo;
import com.demo.proworks.domain.corporate.dao.CorporateDAO;

/**  
 * @subject     : 회사정보 관련 처리를 담당하는 DAO
 * @description : 회사정보 관련 처리를 담당하는 DAO
 * @author      : Inswave
 * @since       : 2025/06/30
 * @modification
 * ===========================================================
 * DATE				AUTHOR				DESC
 * ===========================================================
 * 2025/06/30			 Inswave	 		최초 생성
 * 
 */
@Repository("corporateDAO")
public class CorporateDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

    /**
     * 회사정보 상세 조회한다.
     *  
     * @param  CorporateVo 회사정보
     * @return CorporateVo 회사정보
     * @throws ElException
     */
    public CorporateVo selectCorporate(CorporateVo vo) throws ElException {
        return (CorporateVo) selectByPk("com.demo.proworks.domain.corporate.selectCorporate", vo);
    }

    /**
     * 페이징을 처리하여 회사정보 목록조회를 한다.
     *  
     * @param  CorporateVo 회사정보
     * @return List<CorporateVo> 회사정보
     * @throws ElException
     */
    public List<CorporateVo> selectListCorporate(CorporateVo vo) throws ElException {      	
        return (List<CorporateVo>)list("com.demo.proworks.domain.corporate.selectListCorporate", vo);
    }

    /**
     * 회사정보 목록 조회의 전체 카운트를 조회한다.
     *  
     * @param  CorporateVo 회사정보
     * @return 회사정보 조회의 전체 카운트
     * @throws ElException
     */
    public long selectListCountCorporate(CorporateVo vo)  throws ElException{               
        return (Long)selectByPk("com.demo.proworks.domain.corporate.selectListCountCorporate", vo);
    }
        
    /**
     * 회사정보를 등록한다.
     *  
     * @param  CorporateVo 회사정보
     * @return 번호
     * @throws ElException
     */
    public int insertCorporate(CorporateVo vo) throws ElException {    	
        return insert("com.demo.proworks.domain.corporate.insertCorporate", vo);
    }

    /**
     * 회사정보를 갱신한다.
     *  
     * @param  CorporateVo 회사정보
     * @return 번호
     * @throws ElException
     */
    public int updateCorporate(CorporateVo vo) throws ElException {
        return update("com.demo.proworks.domain.corporate.updateCorporate", vo);
    }

    /**
     * 회사정보를 삭제한다.
     *  
     * @param  CorporateVo 회사정보
     * @return 번호
     * @throws ElException
     */
    public int deleteCorporate(CorporateVo vo) throws ElException {
        return delete("com.demo.proworks.domain.corporate.deleteCorporate", vo);
    }

}
