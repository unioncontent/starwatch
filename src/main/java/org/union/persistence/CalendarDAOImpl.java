package org.union.persistence;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.union.domain.CalendarVO;

@Repository
public class CalendarDAOImpl implements CalendarDAO{

	@Autowired
	@Resource(name="oneSqlSession")
	private SqlSession session1;
	
	@Autowired
	@Resource(name="twoSqlSession")
	private SqlSession session2;
	
	private static final String namespace = "org.union.mapper.CalendarMapper.";

	@Override
	public void create(CalendarVO vo) throws SQLException {
		
		session1.insert(namespace + "create", vo);
		session2.insert(namespace + "create", vo);
	}

	@Override
	public void delete(String title) throws SQLException {

		session1.delete(namespace + "delete", title);
		session2.delete(namespace + "delete", title);
	}

	@Override
	public List<CalendarVO> listDate() throws SQLException {

		return session1.selectList(namespace + "listDate" );
	}
	
	
	
}
