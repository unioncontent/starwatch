package org.union.dao;

import java.sql.SQLException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.union.domain.NaverMovieVO;
import org.union.domain.SearchCriteria;
import org.union.persistence.NaverMovieDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class NaverMovieDAOTester {


    @Autowired
    NaverMovieDAO dao;

    NaverMovieVO vo;


    @Before
    public void setUp() throws Exception {
	vo = new NaverMovieVO();
    }


    @Test
    public void test() throws SQLException {
	SearchCriteria cri = new SearchCriteria();
	cri.setCompany(null);
	cri.setSelectKey(null);
	cri.setStartDate(null);
	cri.setEndDate(null);
	System.out.println(dao.searchAllList(cri).size());
    }
}
