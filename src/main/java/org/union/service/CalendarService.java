package org.union.service;

import java.sql.SQLException;
import java.util.List;

import org.union.domain.CalendarVO;

public interface CalendarService {

	
	public void insert(CalendarVO vo) throws SQLException;
	
	public void remove(String title) throws SQLException;
	
	public List<CalendarVO> listDate() throws SQLException;
}
