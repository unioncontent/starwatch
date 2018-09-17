package org.union.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.union.domain.MediaVO;
import org.union.domain.SearchCriteria;
import org.union.persistence.MediaDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class MediaDAOTester {

	
	@Autowired
	MediaDAO dao;
	
	MediaVO vo;
	SearchCriteria cri;
	
	@Before
	public void setUp() throws Exception {
		vo = new MediaVO();
		cri = new SearchCriteria();
	}

	
	@Test
	public void testCreate() throws ParseException, SQLException{
		
		vo.setMedia_name("유니온스포츠");
		vo.setMedia_title("강철비 흥행하나 기사");
		vo.setMedia_content("강철비가 ~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		vo.setReporter_name("김동진");
		vo.setKeyword("강철비");
		vo.setUrl("urlurlurlurlurlurlurlurlurl");
		
		dao.create(vo);
	}
	
	
	@Test
	public void testRead() throws SQLException {
		
		dao.read(1);
	}
	
	
	@Test
	public void testUpdate() throws SQLException {
		
		vo.setMedia_name("유uuuu니온스포츠");
		vo.setMedia_title("강철비 흥행하나 기사");
		vo.setMedia_content("강철비가 ~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		vo.setReporter_name("김동진");
		vo.setKeyword("강철비");
		vo.setUrl("urlurlurlurlurlurlurlurlurl");
		vo.setMedia_idx(1);
		
		dao.update(vo);
	}

	
	@Test
	public void testDelete() throws SQLException {
		
		dao.delete(1);
	}
	
	
	@Test
	private void testSearchList() throws Exception{
		
		cri.setSearchType("t");
		cri.setSelectKey("꾼");
		cri.setTextType("좋은글");
		
		dao.listSearch(cri);
	}

	
	@Test
	private void testAllPageList() throws Exception{
		
		cri.setSearchType("t");
		cri.setSelectKey("꾼");
		cri.setStartDate("2017-11-23 00:00:00");
		cri.setEndDate("2017-11-24 23:59:59");
		
		dao.allPageList(cri);
	}
	
	@Test
	private void testGetTotalCount() throws Exception{
		
		cri.setSelectKey("신과함께");
		cri.setStartDate("2017-11-14 00:00:00");
		cri.setEndDate("2017-12-14 00:00:00");
		
		dao.getTotalCount(cri);
	}
	
	@Test
	private void testExtract() throws Exception{
		
		cri.setStartDate("2017-12-18 00:00:00");
		cri.setEndDate("2017-12-18 23:59:59");
		cri.setCompany("NEW");
		cri.setSelectKey("강철비");
		
		dao.getExtractCount(cri);
		
	}
}
