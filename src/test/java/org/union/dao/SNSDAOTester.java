package org.union.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.union.domain.GraphVO;
import org.union.domain.SNSVO;
import org.union.domain.SearchCriteria;
import org.union.persistence.SNSDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class SNSDAOTester {

	
	@Autowired
	SNSDAO dao;
	
	SNSVO vo;
	SearchCriteria cri;
	
	
	@Before
	public void setUp() throws Exception {
		vo = new SNSVO();
		cri = new SearchCriteria();
	}

	
	@Test
	public void testCreate() throws SQLException {
		
		for(int i = 0; i < 50; i++) {
		vo.setSns_name("facebook");
		vo.setSns_title(i + "택시운전사");
		vo.setSns_content(i + "content");
		vo.setSns_writer(i + "writer");
		vo.setLike_cnt(5);
		vo.setReply_cnt(3);
		vo.setShare_cnt(8);
		vo.setKeyword(i + "범죄도시");
		vo.setKeyword_type("영화");
		vo.setUrl("url");
		vo.setTextType("호감");
		dao.create(vo);
		}
	}

	
	@Test
	public void testRead() throws SQLException {
		
		vo = dao.read(1);
		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		
		System.out.println((date.format(vo.getWriteDate())));
		
		
	}
	
	@Test
	public void testGetDateCount() throws ParseException, SQLException {
		
		String startDate = "2017-10-22 00:00:00";
		String endDate = "2017-10-25 23:59:59";
		
		GraphVO graph = new GraphVO();
		
		graph.setStartDate(startDate);
		graph.setEndDate(endDate);
		graph.setSns_name("facebook");
		
		//System.out.println(dao.getDateCount(graph));
		
		
		
	}
	
	@Test
	public void testUpdate() throws SQLException {
		
		vo.setSns_name("UUUUUUUUUname");
		vo.setSns_title("title");
		vo.setSns_content("content");
		vo.setSns_writer("writer");
		vo.setLike_cnt(5);
		vo.setReply_cnt(3);
		vo.setShare_cnt(8);
		vo.setKeyword("강철비");
		vo.setKeyword_type("영화");
		vo.setUrl("url");
		vo.setSns_idx(1);
		
		dao.update(vo);
	}
	
	
	@Test
	public void testDelete() throws SQLException {
		
		dao.delete(1);
		
	}
	
	
	@Test
	public void listSearch() throws SQLException {
		
		cri.setPage(2);
		cri.setSearchType("t");
		cri.setKeyword("강철비");
		cri.setPerPageNum(20);
		cri.setSelectKey("택시");
		System.out.println(dao.facebookList(cri));
		System.out.println(cri);
		System.out.println("page: " + cri.getPage());
		System.out.println("startPage: " + cri.getStartPage());
		System.out.println("pageUnit: " + cri.getPerPageNum());
	}
	
	@Test
	public void facebookTotalCount() throws SQLException {
		
		cri.setKeyword("강철비");
		cri.setSearchType("t");
		
		System.out.println(dao.facebookTotalCount(cri));
	}
	
	@Test
	public void instaList() throws SQLException {
		
		cri.setPage(1);
		cri.setSearchType("t");
		cri.setKeyword("택시");
		cri.setPerPageNum(5);

		System.out.println(dao.instaList(cri));
		System.out.println(cri);
		System.out.println("page: " + cri.getPage());
		System.out.println("startPage: " + cri.getStartPage());
		System.out.println("pageUnit: " + cri.getPerPageNum());
	}
	
	@Test
	public void instaTotalCount() throws SQLException {
		
		cri.setKeyword("강철비");
		cri.setSearchType("t");
		
		System.out.println(dao.instaTotalCount(cri));
	}
	
	
	@Test
	public void twitterList() throws SQLException {
		
		cri.setPage(2);
		cri.setSearchType("t");
		cri.setKeyword("택시");
		cri.setPerPageNum(20);

		System.out.println(dao.twitterList(cri));
		System.out.println(cri);
		System.out.println("page: " + cri.getPage());
		System.out.println("startPage: " + cri.getStartPage());
		System.out.println("pageUnit: " + cri.getPerPageNum());
	}
	
	@Test
	public void twitterTotalCount() throws SQLException {
		
		cri.setKeyword("강철비");
		cri.setSearchType("t");
		
		System.out.println(dao.twitterTotalCount(cri));
	}
}
