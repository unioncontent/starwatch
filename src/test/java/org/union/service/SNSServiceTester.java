package org.union.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.union.domain.SNSVO;
import org.union.domain.SearchCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class SNSServiceTester {

	
	@Autowired
	SNSService service;
	
	SNSVO vo;
	SearchCriteria cri;
	
	
	@Before
	public void setUp() throws Exception {
		
		vo = new SNSVO();
		cri = new SearchCriteria();
	}

	@Test
	public void test() throws SQLException {

		cri.setPage(1);
		cri.setPerPageNum(100);
		cri.setKeyword("택시");
	
		List<SNSVO> list = new ArrayList<SNSVO>();
		list = service.facebookList(cri);
		System.out.println(list);
		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-mm-dd");
		
		
		
		for (SNSVO snsvo : list) {
			System.out.println(date.format(snsvo.getWriteDate()));
		}
	}

}
