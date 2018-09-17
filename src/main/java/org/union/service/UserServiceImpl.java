package org.union.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.union.domain.UserVO;
import org.union.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	private UserDAO userDAO;

	@Override
	public UserVO login(UserVO vo) throws SQLException {

		return userDAO.login(vo);
	}

	@Override
	public UserVO viewById(String user_id) throws SQLException {

		return userDAO.readById(user_id);
	}

	@Override
	public UserVO view(Integer user_idx) throws SQLException {

		return userDAO.read(user_idx);
	}

	@Override
	public List<UserVO> listAll() throws SQLException {

		return userDAO.listAll();
	}

	@Override
	public UserVO viewByName(String user_name) throws SQLException {

		return userDAO.readByName(user_name);
	}

	@Override
	public void insert(UserVO vo) throws SQLException {

		try {
			userDAO.create(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
