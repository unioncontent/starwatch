package org.union.persistence;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.union.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO{

	
	@Autowired
	@Resource(name="oneSqlSession")
	private SqlSession session1;
	
	@Autowired
	@Resource(name="twoSqlSession")
	private SqlSession session2;
	
	private static final String namespace = "org.union.mappers.UserMapper.";
	
	
	@Override
	public void create(UserVO vo) throws SQLException {
		try {
			session1.insert(namespace + "create", vo);
			session2.insert(namespace + "create", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public UserVO read(Integer user_idx) throws SQLException {

		return session1.selectOne(namespace + "read", user_idx);
	}

	
	@Override
	public void update(UserVO vo) throws SQLException {
		try {
			session1.update(namespace + "update", vo);
			session2.update(namespace + "update", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void delete(Integer user_idx) throws SQLException {
		try {
			session1.delete(namespace + "delete", user_idx);
			session2.delete(namespace + "delete", user_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public UserVO login(UserVO vo) throws SQLException {

		return session1.selectOne(namespace + "login", vo);
	}


	@Override
	public UserVO readById(String user_id) throws SQLException {

		return session1.selectOne(namespace + "readById", user_id);
	}


	@Override
	public List<UserVO> listAll() throws SQLException {

		return session1.selectList(namespace + "listAll");
	}


	@Override
	public UserVO readByName(String user_name) throws SQLException {

		return session1.selectOne(namespace + "readByName", user_name);
	}

}
