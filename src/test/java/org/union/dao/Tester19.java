package org.union.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.union.domain.MediaVO;
import org.union.domain.SearchCriteria;
import org.union.persistence.MediaDAO;
import org.union.persistence.PortalDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class Tester19 {

	@Autowired
	MediaDAO dao;
	
	@Autowired
	PortalDAO portalDAO;
	
	MediaVO vo;
	SearchCriteria cri;
	
	@Before
	public void setUp() throws Exception {
		cri = new SearchCriteria();
	}

	@Test
	private void testExtract() throws Exception{
		
		cri.setStartDate("2017-12-18 00:00:00");
		cri.setEndDate("2017-12-18 23:59:59");
		cri.setCompany("NEW");
		cri.setSelectKey("강철비");
		
		portalDAO.getExtractCount(cri);
		
	}
}
