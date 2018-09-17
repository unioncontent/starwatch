package org.union.persistence;

import java.sql.SQLException;
import java.util.List;

import org.union.domain.UserVO;

public interface UserDAO {

	
	public void create(UserVO vo) throws SQLException;
	
	public UserVO read(Integer user_idx) throws SQLException;
	
	public void update(UserVO vo) throws SQLException;
	
	public void delete(Integer user_idx) throws SQLException;
	
	public List<UserVO> listAll() throws SQLException;
	
	public UserVO readById(String user_id) throws SQLException;
	
	public UserVO readByName(String user_name) throws SQLException;
	
	public UserVO login(UserVO vo) throws SQLException;
	
	
	
}
