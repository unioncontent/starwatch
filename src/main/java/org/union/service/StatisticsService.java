package org.union.service;

import java.sql.SQLException;
import java.util.List;

import org.union.domain.SKeywordVO;
import org.union.domain.SearchCriteria;

public interface StatisticsService {

	public List<SKeywordVO> searchkeywordList(SearchCriteria cri) throws SQLException;
	
	public Integer searchkeywordCount(SearchCriteria cri) throws SQLException;
	
	public Integer searchkeywordBadCount(SearchCriteria cri) throws SQLException;
}
