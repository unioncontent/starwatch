package org.union.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.union.domain.CalendarVO;
import org.union.persistence.CalendarDAO;

@Service
public class CalendarServiceImpl implements CalendarService{
	
	
	@Autowired
	private CalendarDAO calendarDAO;

	@Override
	public void insert(CalendarVO vo) throws SQLException {

		calendarDAO.create(vo);
	}

	@Override
	public void remove(String title) throws SQLException {

		calendarDAO.delete(title);
	}

	@Override
	public List<CalendarVO> listDate() throws SQLException {

		return calendarDAO.listDate();
	}

}
