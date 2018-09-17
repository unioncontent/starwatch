package org.union.service;

import java.sql.SQLException;
import java.util.List;

import org.union.domain.UserVO;

public interface UserService {

	
	public void insert(UserVO vo) throws SQLException;
	
	public UserVO view(Integer user_idx) throws SQLException;
	
	public UserVO login(UserVO vo) throws SQLException;
	
	public UserVO viewById(String user_id) throws SQLException;
	
	public UserVO viewByName(String user_name) throws SQLException;
	
	public List<UserVO> listAll() throws SQLException;
}
