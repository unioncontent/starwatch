package org.union.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.union.domain.GraphVO;
import org.union.domain.MobileEntVO;
import org.union.domain.SearchCriteria;
import org.union.persistence.MobileEntDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class MobileDAOTester {


    @Autowired
    MobileEntDAO dao;

    MobileEntVO vo;


    @Before
    public void setUp() throws Exception {
	vo = new MobileEntVO();
    }

    @Test
    public void test() throws SQLException {
	SearchCriteria cri = new SearchCriteria();
	cri.setCompany(null);
	cri.setSelectKey(null);
	cri.setStartDate("2018-02-01 00:00:00");
	cri.setEndDate("2018-02-01 23:59:59");
	for (MobileEntVO vo : dao.searchList(cri)){
	    System.out.println(vo);
	}
	
//	List<GraphVO> graphList = new ArrayList<GraphVO>();
//	for(int i = 0; i < 16; i++) {
//		
//		cri.setHour(String.valueOf(i));
//		
//		Integer m = dao.getTypeOfMovieCount(cri);
//		Integer a = dao.getTypeOfActorCount(cri);
//		
//		GraphVO graphVO = new GraphVO();
//		graphVO.setWriteDate(String.valueOf(i));
//		graphVO.setType1(m);
//		graphVO.setType2(a);
//		
//		graphList.add(graphVO);
//	    }
//	for(GraphVO vo:graphList) {
//	    System.out.println(vo.getWriteDate()+":"+vo.getType1()+"/"+vo.getType2());
//	}
    }
}
