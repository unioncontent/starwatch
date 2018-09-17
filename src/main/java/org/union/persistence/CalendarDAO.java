package org.union.persistence;

import java.sql.SQLException;
import java.util.List;

import org.union.domain.CalendarVO;

public interface CalendarDAO {

	
	public void create(CalendarVO vo) throws SQLException;
	
	public void delete(String title) throws SQLException;
	
	public List<CalendarVO> listDate() throws SQLException;
}
