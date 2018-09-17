package org.union.service;

import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class KeywordServiceTester {
	
	@Autowired
	private KeywordService keywordService;

	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void test() throws SQLException {

		keywordService.view("현빈");
	}

}
