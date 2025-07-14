package com.demo.proworks.domain.post.service;

import java.util.List;
import java.util.Map;

import com.demo.proworks.domain.post.vo.PostMatchVo;
import com.demo.proworks.domain.post.vo.PostVo;
import com.demo.proworks.domain.post.vo.TechStackVo;

/**
 * @subject : 공고정보 관련 처리를 담당하는 인터페이스
 * @description : 공고정보 관련 처리를 담당하는 인터페이스
 * @author : Inswave
 * @since : 2025/07/03
 * @modification ===========================================================
 *               DATE AUTHOR DESC
 *               ===========================================================
 *               2025/07/03 Inswave 최초 생성
 * 
 */
public interface PostService {

	/**
	 * 공고정보 페이징 처리하여 목록을 조회한다.
	 *
	 * @param postVo 공고정보 PostVo
	 * @return 공고정보 목록 List<PostVo>
	 * @throws Exception
	 */
	public List<PostVo> selectListPost(PostVo postVo) throws Exception;

	/**
	 * 조회한 공고정보 전체 카운트
	 * 
	 * @param postVo 공고정보 PostVo
	 * @return 공고정보 목록 전체 카운트
	 * @throws Exception
	 */
	public long selectListCountPost(PostVo postVo) throws Exception;

	/**
	 * 공고정보를 상세 조회한다.
	 *
	 * @param postVo 공고정보 PostVo
	 * @return 단건 조회 결과
	 * @throws Exception
	 */
	public PostVo selectPost(PostVo postVo) throws Exception;

	/**
	 * 공고정보를 등록 처리 한다.
	 *
	 * @param postVo 공고정보 PostVo
	 * @return 번호
	 * @throws Exception
	 */
	public int insertPost(PostVo postVo) throws Exception;

	/**
	 * 공고정보를 갱신 처리 한다.
	 *
	 * @param postVo 공고정보 PostVo
	 * @return 번호
	 * @throws Exception
	 */
	public int updatePost(PostVo postVo) throws Exception;

	/**
	 * 공고정보를 삭제 처리 한다.
	 *
	 * @param postVo 공고정보 PostVo
	 * @return 번호
	 * @throws Exception
	 */
	public int deletePost(PostVo postVo) throws Exception;

	/**
	 * 기술스택 목록을 조회한다.
	 *
	 * @return 기술스택 목록 List<TechStackVo>
	 * @throws Exception
	 */
	public List<TechStackVo> selectListTechStack() throws Exception;

	/**
	 * 공고 등록 시 선택된 기술스택들을 company_tech_stack_relation 테이블에 저장한다.
	 *
	 * @param postVo       공고정보 PostVo (jobPostingId 포함)
	 * @param techStackIds 선택된 기술스택 ID 목록
	 * @throws Exception
	 */
	public void saveTechStackRelations(PostVo postVo, List<String> techStackIds) throws Exception;

	/**
	 * 특정 공고의 기술스택 관계를 모두 삭제한다.
	 *
	 * @param jobPostingId 공고 ID
	 * @throws Exception
	 */
	public void deleteTechStackRelationsByPostId(String jobPostingId) throws Exception;

	/**
	 * 특정 공고의 기술스택 목록을 조회한다.
	 *
	 * @param jobPostingId 공고 ID
	 * @return 기술스택 목록 List<TechStackVo>
	 * @throws Exception
	 */
	public List<TechStackVo> selectTechStacksByPostId(String jobPostingId) throws Exception;

	/**
	 * 사용자 ID로 회사 ID를 조회한다.
	 *
	 * @param userId 사용자 ID
	 * @return 회사 ID String
	 * @throws Exception
	 */
	public String selectCompanyIdByUserId(String userId) throws Exception;

	/**
	 * 사용자 mbti, mbti 별 갯수 필터를 통해 회사 list를 정렬한다
	 *
	 * @param PostMatchVo = PostVo + 사용자 mbti, mbti 갯수 
	 * @return 회사 ID String
	 * @throws Exception
	 */
	public List<PostVo> findPostsByMbti(PostVo postVo) throws Exception;


}
