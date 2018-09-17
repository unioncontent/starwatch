package org.union.persistence;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.union.domain.GraphVO;
import org.union.domain.SearchCriteria;
import org.union.domain.ViralVO;

@Repository
public class ViralDAOImpl implements ViralDAO{

	
	@Autowired
	@Resource(name="oneSqlSession")
	private SqlSession session1;
	
	@Autowired
	@Resource(name="twoSqlSession")
	private SqlSession session2;
	
	private static final String namespace = "org.union.mapper.ViralMapper.";
								
	
	@Override
	public void create(ViralVO vo){
		
		try {
			session1.insert(namespace + "create", vo);
			session2.insert(namespace + "create", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	
	@Override
	public ViralVO read(Integer biral_idx){
		
		return session1.selectOne(namespace + "read", biral_idx);
		
	}

	
	@Override
	public void update(ViralVO vo){
		
		try {
			session1.update(namespace + "update", vo);
			session2.update(namespace + "update", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}	

	}
	

	@Override
	public void delete(Integer biral_idx){
		
		try {
			session1.delete(namespace + "delete", biral_idx);
			session2.delete(namespace + "delete", biral_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}


	@Override
	public List<ViralVO> searchInList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "searchInList", cri);
	}


	@Override
	public List<ViralVO> searchOutList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "searchOutList", cri);
	}

	
	@Override
	public List<ViralVO> searchAllList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "searchAllList", cri);
	}

	
	@Override
	public Integer getSearchInCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getSearchInCount", cri);
	}


	@Override
	public Integer getSearchOutCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getSearchOutCount", cri);
	}


	@Override
	public Integer getHistoryCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getHistoryCount", cri);
	}


	@Override
	public List<GraphVO> getHistoryRank(String url) throws SQLException {

		return session1.selectList(namespace + "getHistoryRank", url);
	}


	@Override
	public List<ViralVO> historyPage(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "historyPage", cri);
	}

}
