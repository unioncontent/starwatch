package org.union.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.union.domain.SKeywordVO;
import org.union.domain.SearchCriteria;
import org.union.persistence.StatisticsDAO;

@Service
public class StatisticsServiceImpl implements StatisticsService {
	
	@Autowired
	private StatisticsDAO statisticsDAO;

	@Override
	public List<SKeywordVO> searchkeywordList(SearchCriteria cri) throws SQLException {

		return statisticsDAO.searchkeywordList(cri);
	}

	@Override
	public Integer searchkeywordCount(SearchCriteria cri) throws SQLException {

		return statisticsDAO.searchkeywordCount(cri);
	}

	@Override
	public Integer searchkeywordBadCount(SearchCriteria cri) throws SQLException {

		return statisticsDAO.searchkeywordBadCount(cri);
	}

}
