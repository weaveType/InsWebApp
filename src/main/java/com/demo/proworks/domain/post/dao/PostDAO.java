package com.demo.proworks.domain.post.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.inswave.elfw.exception.ElException;
import com.demo.proworks.domain.post.vo.JobApplicationVo;
import com.demo.proworks.domain.post.vo.MainPostingListVo;
import com.demo.proworks.domain.post.vo.MainPostingVo;
import com.demo.proworks.domain.post.vo.PostMatchVo;
import com.demo.proworks.domain.post.vo.PostVo;
import com.demo.proworks.domain.post.vo.ScoutUserVo;
import com.demo.proworks.domain.post.vo.TechStackVo;
import com.demo.proworks.domain.post.dao.PostDAO;

/**
 * @subject : 공고정보 관련 처리를 담당하는 DAO
 * @description : 공고정보 관련 처리를 담당하는 DAO
 * @author : Inswave
 * @since : 2025/07/03
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/03 Inswave 최초 생성
 * 
 */
@Repository("postDAO")
public class PostDAO extends com.demo.proworks.cmmn.dao.ProworksDefaultAbstractDAO {

	/**
	 * 공고정보 상세 조회한다.
	 * 
	 * @param PostVo 공고정보
	 * @return PostVo 공고정보
	 * @throws ElException
	 */
	public PostVo selectPost(PostVo vo) throws ElException {
		return (PostVo) selectByPk("com.demo.proworks.domain.post.selectPost", vo);
	}

	/**
	 * 페이징을 처리하여 공고정보 목록조회를 한다.
	 * 
	 * @param PostVo 공고정보
	 * @return List<PostVo> 공고정보
	 * @throws ElException
	 */
	public List<PostVo> selectListPost(PostVo vo) throws ElException {
		return (List<PostVo>) list("com.demo.proworks.domain.post.selectListPost", vo);
	}

	/**
	 * 공고정보 목록 조회의 전체 카운트를 조회한다.
	 * 
	 * @param PostVo 공고정보
	 * @return 공고정보 조회의 전체 카운트
	 * @throws ElException
	 */
	public long selectListCountPost(PostVo vo) throws ElException {
		return (Long) selectByPk("com.demo.proworks.domain.post.selectListCountPost", vo);
	}

	/**
	 * 공고정보를 등록한다.
	 * 
	 * @param PostVo 공고정보
	 * @return 번호
	 * @throws ElException
	 */
	public int insertPost(PostVo vo) throws ElException {
		return insert("com.demo.proworks.domain.post.insertPost", vo);
	}

	/**
	 * 공고정보를 갱신한다.
	 * 
	 * @param PostVo 공고정보
	 * @return 번호
	 * @throws ElException
	 */
	public int updatePost(PostVo vo) throws ElException {
		return update("com.demo.proworks.domain.post.updatePost", vo);
	}

	/**
	 * 공고정보를 삭제한다.
	 * 
	 * @param PostVo 공고정보
	 * @return 번호
	 * @throws ElException
	 */
	public int deletePost(PostVo vo) throws ElException {
		return delete("com.demo.proworks.domain.post.deletePost", vo);
	}

	/**
	 * 기술스택 목록을 조회한다.
	 * 
	 * @return List<TechStackVo> 기술스택 목록
	 * @throws ElException
	 */
	public List<TechStackVo> selectListTechStack() throws ElException {
		return (List<TechStackVo>) list("com.demo.proworks.domain.post.selectListTechStack", null);
	}

	/**
	 * 공고-기술스택 관계를 저장한다.
	 * 
	 * @param Map 공고ID와 기술스택ID
	 * @return 번호
	 * @throws ElException
	 */
	public int insertCompanyTechStackRelation(Map<String, Object> params) throws ElException {
		return insert("com.demo.proworks.domain.post.insertCompanyTechStackRelation", params);
	}

	/**
	 * 특정 공고의 기술스택 관계를 삭제한다.
	 * 
	 * @param String 공고ID
	 * @return 번호
	 * @throws ElException
	 */
	public int deleteCompanyTechStackRelationByJobId(String jobPostingId) throws ElException {
		return delete("com.demo.proworks.domain.post.deleteCompanyTechStackRelationByJobId", jobPostingId);
	}

	/**
	 * 특정 공고의 기술스택 목록을 조회한다.
	 * 
	 * @param String 공고ID
	 * @return List<TechStackVo> 기술스택 목록
	 * @throws ElException
	 */
	public List<TechStackVo> selectTechStacksByJobId(String jobPostingId) throws ElException {
		return (List<TechStackVo>) list("com.demo.proworks.domain.post.selectTechStacksByJobId", jobPostingId);
	}

	/**
	 * 특정 공고의 기술스택 목록을 조회한다. (PostService 인터페이스 호환)
	 * 
	 * @param String 공고ID
	 * @return List<TechStackVo> 기술스택 목록
	 * @throws ElException
	 */
	public List<TechStackVo> selectTechStacksByPostId(String jobPostingId) throws ElException {
		return selectTechStacksByJobId(jobPostingId);
	}

	/**
	 * 사용자 ID로 회사 ID를 조회한다.
	 * 
	 * @param String 사용자ID
	 * @return String 회사ID
	 * @throws ElException
	 */
	public String selectCompanyIdByUserId(String userId) throws ElException {
		return (String) selectByPk("com.demo.proworks.domain.post.selectCompanyIdByUserId", userId);
	}

	public String selectPreferredDeveloperTypesByPostId(int jobPostingId) throws ElException {
		return (String) selectByPk("com.demo.proworks.domain.post.selectPreferredDeveloperTypesByPostId", jobPostingId);
	}

	public List<PostVo> findPostsByMbti(PostMatchVo postMatchVo) throws ElException {
		return (List<PostVo>) list("com.demo.proworks.domain.post.findPostsByMbti", postMatchVo);
	}

	public long findPostsByMbtiCount(PostMatchVo postMatchVo) throws ElException {
		return (long) selectByPk("com.demo.proworks.domain.post.findPostsByMbtiCount", postMatchVo);
	}

	/**
	 * 공고에 이력서 지원처리를 한다.
	 *
	 * @param sendEmailVo 합불여부, 메일 전송할 email, 메일 내용
	 * @return 이메일 전송 실패 ID
	 * @throws Exception
	 */
	/**
	 * 이력서 지원 정보를 등록한다.
	 *
	 * @param jobApplicationVo 이력서 지원 정보
	 * @return 등록된 행의 수
	 * @throws ElException
	 */
	public int insertJobApplication(JobApplicationVo jobApplicationVo) throws ElException {
		return insert("com.demo.proworks.domain.post.insertJobApplication", jobApplicationVo);
	}

	/**
	 * 지원자의 상태를 갱신한다.
	 *
	 * @param jobApplicationVo 지원자 정보 (accountId, applicationStatus)
	 * @return 갱신된 행의 수
	 * @throws ElException
	 */
	public int updateApplicationStatus(JobApplicationVo jobApplicationVo) throws ElException {
		return update("com.demo.proworks.domain.post.updateApplicationStatus", jobApplicationVo);
	}

	/**
	 * 메인 페이지에서 출력할 공고목록을 조회한다.
	 *
	 * @return 기술스택 목록 조회 결과
	 * @throws Exception
	 */
	public List<MainPostingVo> selectPostingList() throws Exception {
		return (List<MainPostingVo>) list("com.demo.proworks.domain.post.selectPostingList");
	}

	/**
	 * 기업이 스카웃한 유저를 저장한다. 
	 *
	 * @param vo 스카웃 정보
	 * @throws ElException
	 */
	public void insertScoutRequest(ScoutUserVo vo) throws ElException {
		insert("com.demo.proworks.domain.post.insertScoutRequest", vo);
	}

	/**
	 * 사용자의 특정 공고 지원 여부를 확인한다.
	 *
	 * @param jobApplicationVo 지원 정보 (jobPostingId, accountId 포함)
	 * @return 지원 횟수 (0: 지원하지 않음, 1 이상: 지원함)
	 * @throws ElException
	 */
	public int selectApplicationCount(JobApplicationVo jobApplicationVo) throws ElException {
		return (Integer) selectByPk("com.demo.proworks.domain.post.selectApplicationCount", jobApplicationVo);
	}

}
