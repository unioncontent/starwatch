package org.union.persistence;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.union.domain.RelationVO;

@Repository
public class RelationDAOImpl implements RelationDAO {

	
	@Autowired
	@Resource(name="oneSqlSession")
	private SqlSession session1;
	
	@Autowired
	@Resource(name="twoSqlSession")
	private SqlSession session2;
	
	private static final String namespace = "org.union.mappers.RelationMapper.";
	
	
	@Override
	public void create(RelationVO vo) throws SQLException {
		try {
			session1.insert(namespace + "create", vo);
			session2.insert(namespace + "create", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public RelationVO read(Integer relation_idx) throws SQLException {

		return session1.selectOne(namespace + "read", relation_idx);
	}

	
	@Override
	public void update(RelationVO vo) throws SQLException {
		try {
			session1.update(namespace + "update", vo);
			session2.update(namespace + "update", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void delete(Integer relation_idx) throws SQLException {
		try {
			session1.delete(namespace + "delete", relation_idx);
			session2.delete(namespace + "delete", relation_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public Integer todayCount() throws SQLException {

		return session1.selectOne(namespace + "todayCount");
	}

}
