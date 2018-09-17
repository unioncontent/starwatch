package org.union.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.union.domain.GraphVO;
import org.union.domain.SearchCriteria;
import org.union.domain.ViralVO;
import org.union.persistence.ViralDAO;

@Service
public class ViralServiceImpl implements ViralService {

	
	@Autowired
	private ViralDAO viralDAO;
	
	
	@Override
	public List<ViralVO> searchInList(SearchCriteria cri) throws SQLException {

		return viralDAO.searchInList(cri);
	}

	@Override
	public List<ViralVO> searchOutList(SearchCriteria cri) throws SQLException {

		return viralDAO.searchOutList(cri);
	}

	@Override
	public List<ViralVO> searchAllList(SearchCriteria cri) throws SQLException {

		return viralDAO.searchAllList(cri);
	}
	
	@Override
	public Integer getSearchInCount(SearchCriteria cri) throws SQLException {

		return viralDAO.getSearchInCount(cri);
	}

	@Override
	public Integer getSearchOutCount(SearchCriteria cri) throws SQLException {

		return viralDAO.getSearchOutCount(cri);
	}

	@Override
	public Integer getHistoryCount(SearchCriteria cri) throws SQLException {

		return viralDAO.getHistoryCount(cri);
	}

	@Override
	public List<GraphVO> getHistoryRank(String url) throws SQLException {

		return viralDAO.getHistoryRank(url);
	}

	@Override
	public List<ViralVO> historyPage(SearchCriteria cri) throws SQLException {

		return viralDAO.historyPage(cri);
	}

}
