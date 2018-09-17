package org.union.dao;


import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.union.domain.PortalVO;
import org.union.persistence.PortalDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class PortalDAOTester {

	
	@Autowired
	PortalDAO dao;
	
	PortalVO vo;
	
	@Before
	public void setUp() throws Exception {
		vo = new PortalVO();
	}

	
	@Test
	public void testCreate() throws ParseException, SQLException {
		
		vo.setPortal_type("카페");
		vo.setPortal_name("네이버");
		vo.setPortal_title("title");
		vo.setDeviceType(1);
		
		String writeDate = "2017-10-25";

		Date date =  new SimpleDateFormat("yyyy-mm-dd").parse(writeDate);
		System.out.println(date);
		
		vo.setKeyword("keyword");
		vo.setUrl("url");
	
		dao.create(vo);
	}

	
	@Test
	public void testRead() throws SQLException {
		
		dao.read(1);
	}
	
	
	@Test
	public void testUpdate() throws SQLException {
		
		vo.setPortal_type("카페");
		vo.setPortal_name("네이버");
		vo.setPortal_title("uuuuuuutitle");
		vo.setDeviceType(1);
		vo.setKeyword("keyword");
		vo.setUrl("url");
		vo.setPortal_idx(1);
		
		dao.update(vo);
	}
	
	
	@Test
	public void testDelete() throws SQLException {
		
		dao.delete(1);
	}
}
