package org.union.persistence;


import java.sql.SQLException;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.union.domain.SearchCriteria;


@Repository
public class EtDAOImpl implements EtDAO{

	
	@Autowired
	@Resource(name="oneSqlSession")
	private SqlSession session1;
	
	@Autowired
	@Resource(name="twoSqlSession")
	private SqlSession session2;
	
	private final static String namespace  = "org.union.mappers.EtMapper.";
	

	@Override
	public Integer getSearchCount1(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getSearchCount1", cri);
	}

	@Override
	public Integer getSearchCount2(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getSearchCount2", cri);
	}

	@Override
	public Integer getSearchCount3(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getSearchCount3", cri);
	}

	@Override
	public Integer getSearchCountAll(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getSearchCountAll", cri);
	}


}
