package org.union.web;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class DBTester {

	
	
	// 기본 DataSource
	@Autowired
	private DataSource ds;
	
	@Test
	public void dbTester() throws Exception {

		Connection con = ds.getConnection();
		System.out.println(con);
		con.close();
	}

}
