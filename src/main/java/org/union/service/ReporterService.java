package org.union.service;


import java.sql.SQLException;
import java.util.List;

import org.union.domain.ReporterVO;
import org.union.domain.SearchCriteria;

public interface ReporterService {

	
	public void insert(ReporterVO vo) throws SQLException;
	
	public List<ReporterVO> listSearch(SearchCriteria cri) throws SQLException;
	
	public Integer getSearchCount(SearchCriteria cri) throws SQLException;
	
	public ReporterVO readByName(String reporter_name) throws SQLException;
}
