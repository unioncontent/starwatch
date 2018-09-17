package org.union.dao;


import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.union.domain.RelationVO;
import org.union.persistence.RelationDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class RelationDAOTester {

	
	@Autowired
	RelationDAO dao;
	
	RelationVO vo;
	
	@Before
	public void setUp() throws Exception {
		vo = new RelationVO();
	}

	
	@Test
	public void testCreate() throws SQLException {
		
		vo.setRelation_keyword("강철비");
		vo.setRelation_word("곽도원");
		
		dao.create(vo);
	}

	
	@Test
	public void testRead() throws SQLException {
		
		dao.read(1);
	}
	
	
	@Test
	public void testUpdate() throws SQLException {
		
		vo.setRelation_keyword("업데이트강철비");
		vo.setRelation_word("곽도원");
		vo.setRelation_idx(1);
		
		dao.update(vo);
		
	}
	
	
	@Test
	public void testDelete() throws SQLException {
		
		dao.delete(1);
	}
}
