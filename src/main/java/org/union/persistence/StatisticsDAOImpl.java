package org.union.persistence;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.union.domain.SKeywordVO;
import org.union.domain.SearchCriteria;

@Repository
public class StatisticsDAOImpl implements StatisticsDAO{
	
	@Autowired
	@Resource(name="oneSqlSession")
	private SqlSession session1;
	
	@Autowired
	@Resource(name="twoSqlSession")
	private SqlSession session2;
	
	private static final String namespace = "org.union.mappers.StatisticsMapper.";

	@Override
	public List<SKeywordVO> searchkeywordList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "searchkeywordList", cri);
	}

	@Override
	public Integer searchkeywordCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "searchkeywordCount", cri);
	}

	@Override
	public Integer searchkeywordBadCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "searchkeywordBadCount", cri);
	}
	
	

}
