package org.union.service;

import java.sql.SQLException;
import java.util.List;

import org.union.domain.GraphVO;
import org.union.domain.SearchCriteria;
import org.union.domain.ViralVO;

public interface ViralService {
	

	public List<ViralVO> searchInList(SearchCriteria cri) throws SQLException;

	public List<ViralVO> searchOutList(SearchCriteria cri) throws SQLException;
	
	public List<ViralVO> searchAllList(SearchCriteria cri) throws SQLException;

	public Integer getSearchInCount(SearchCriteria cri) throws SQLException;

	public Integer getSearchOutCount(SearchCriteria cri) throws SQLException;
	
	public List<ViralVO> historyPage(SearchCriteria cri) throws SQLException;
	
	public Integer getHistoryCount(SearchCriteria cri) throws SQLException;
	
	public List<GraphVO> getHistoryRank(String url) throws SQLException;
}
