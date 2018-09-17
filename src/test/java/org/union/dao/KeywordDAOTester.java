package org.union.dao;


import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.union.domain.KeywordVO;
import org.union.persistence.KeywordDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class KeywordDAOTester {

	
	@Autowired
	KeywordDAO dao;
	
	KeywordVO vo;
	
	@Before
	public void setUp() throws Exception {
		vo = new KeywordVO();
	}

	
	@Test
	public void testCreate() throws SQLException {
		
		vo.setUser_idx(2);
		vo.setKeyword("키워드");
		vo.setKeyword_property("영화");
		
		dao.create(vo);
	}

	
	@Test
	public void testRead() throws SQLException {
		
		dao.read("현빈");
	}


	@Test
	public void testUpdate() throws SQLException {
		
		vo.setUser_idx(2);
		vo.setKeyword("uu키워드");
		vo.setKeyword_property("uu영화");
		vo.setKeyword_idx(1);
		
		dao.update(vo);
	}
	
	
	@Test
	public void testDelete() {
		
		//dao.delete(1);
	}
}
