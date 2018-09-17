package org.union.dao;


import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.union.domain.ReporterVO;
import org.union.persistence.ReporterDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class ReporterDAOTester {

	
	@Autowired
	ReporterDAO dao;
	
	ReporterVO vo;
	
	
	@Before
	public void setUp() throws Exception {
		vo = new ReporterVO();
	}

	
	@Test
	public void testCreate() throws SQLException {
		
		vo.setReporter_media_name("조선");
		vo.setReporter_name("이상화");
		vo.setReporter_ID("ID");
		vo.setReporter_email("이메일");
		vo.setReporter_phoneNum("01032824539");
		vo.setReporter_part_name("스포츠부");
		
		dao.create(vo);
	}

	
	@Test
	public void testRead() throws SQLException {
		
		dao.read(1);
	}
	
	
	@Test
	public void testUpdate() throws SQLException {
		
		vo.setReporter_media_name("업데이트조선");
		vo.setReporter_name("이상화");
		vo.setReporter_ID("ID");
		vo.setReporter_email("이메일");
		vo.setReporter_phoneNum("01032824539");
		vo.setReporter_part_name("스포츠부");
		vo.setReporter_idx(1);
		
		dao.update(vo);
	}
	
	
	@Test
	public void testDelete() throws SQLException {
		
		dao.delete(1);
	}
}
