package org.union.dao;


import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.union.domain.UserVO;
import org.union.persistence.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class UserDAOTester {

	@Autowired
	UserDAO dao;
	
	UserVO vo;
	
	@Before
	public void setUp() throws Exception {
		vo = new UserVO();
	}
	

	@Test
	public void testCreate() throws SQLException {
		
		vo.setUser_type(1);
		vo.setUser_ID("ID");
		vo.setUser_PW("PW");
		vo.setUser_name("name");
		vo.setUser_phoneNum("01010101010");
		vo.setUser_email("@@@@@");
		vo.setCompany_name("Cname");
		vo.setCompany_type("영화");
		vo.setCompany_licensee("김창수");
		vo.setCompany_location("봉천동");
		
		dao.create(vo);
	}
	
	
	@Test
	public void testRead() throws SQLException {
		
		dao.read(7);
	}

	
	@Test
	public void testUpdate() throws SQLException {
		
		vo.setUser_type(1);
		vo.setUser_ID("ID");
		vo.setUser_PW("PW");
		vo.setUser_name("uuuuname");
		vo.setUser_phoneNum("01010101010");
		vo.setUser_email("@@@@@");
		vo.setCompany_name("Cname");
		vo.setCompany_type("영화");
		vo.setCompany_licensee("김창수");
		vo.setCompany_location("봉천동");
		vo.setUser_idx(1);
		
		dao.update(vo);
	}
	
	
	@Test
	public void testDelete() throws SQLException {
		
		dao.delete(1);
	}
	
}
