package org.union.dao;

import java.sql.SQLException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.union.domain.CommunityVO;
import org.union.domain.SearchCriteria;
import org.union.persistence.CommunityDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class CommunityDAOTester {

	
	@Autowired
	CommunityDAO dao;
	
	CommunityVO vo;
	SearchCriteria svo;
	
	@Before
	public void setUp() throws Exception {
		vo = new CommunityVO();
		svo = new SearchCriteria();
	}


	@Test
	public void testCreate() throws SQLException {
		
		
		
		vo.setCommunity_name("새로운" + "디씨");
		vo.setCommunity_title("강철비 본사람");
		vo.setCommunity_content("강철비 ~~~~~~~~~~~~~~~~");
		vo.setCommunity_writer("익명134");
		vo.setCommunity_writer_IP("102.168.0.1");
		vo.setKeyword("강철비");
		vo.setUrl("url");
		vo.setTextType("호감");
		
		dao.create(vo);
		
	}
	
	
	@Test
	public void testRead() throws SQLException {
		
		dao.read(3);
	}
	
	@Test
	public void testUpdate() throws SQLException {
		
		vo.setCommunity_name("100100100디씨");
		/*vo.setCommunity_title("u강철비 본사람");
		vo.setCommunity_content("u강철비 ~~~~~~~~~~~~~~~~");
		vo.setCommunity_writer("u익명134");
		vo.setCommunity_writer_IP("u102.168.0.1");
		vo.setWriteDate(new Date());
		vo.setKeyword("uu강철비");
		vo.setKeyword_type(2);
		vo.setUrl("uuurl");*/
		vo.setCommunity_idx(100);
		
		dao.update(vo);
	}
	
	
	@Test
	public void testDelete() throws SQLException {
		
		dao.delete(1);
	}
	
	
	@Test
	public void testListExtract() throws SQLException {
		
		svo.setPage(2);
		svo.setPerPageNum(30);
		svo.setSelectKey("강철비");
		
		dao.listExtract(svo);
	}
	
	
	@Test
	public void testGetExtractCount() throws SQLException {
		svo.setPage(2);
		svo.setPerPageNum(30);
		
		dao.getExtractCount(svo);
	}
	
	
	@Test
	public void testListSearch() throws SQLException {
		
		svo.setKeyword("title");
		
		dao.listSearch(svo);
	}
	
	
	@Test
	public void testListAll() throws SQLException {
		
		svo.setSearchType("t");
		svo.setKeyword("나나");
		dao.listAll(svo);
	}
	
	
	@Test
	public void testUpdateTextType() throws SQLException {
		
		vo.setCommunity_idx(3);
		vo.setTextType("호감");
		
		dao.updateTextType(vo);
	}
	
	
	@Test
	public void testUpdateThumbnail() throws SQLException {
		
		vo.setCommunity_idx(3);
		vo.setThumbnail("1234132131231TESTTEST");
		
		dao.updateThumbnail(vo);
	}
}
