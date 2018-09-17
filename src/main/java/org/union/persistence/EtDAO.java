package org.union.persistence;

import java.sql.SQLException;

import org.union.domain.SearchCriteria;

public interface EtDAO {

	
	public Integer getSearchCount1(SearchCriteria cri) throws SQLException;
	
	public Integer getSearchCount2(SearchCriteria cri) throws SQLException;
	
	public Integer getSearchCount3(SearchCriteria cri) throws SQLException;
	
	public Integer getSearchCountAll(SearchCriteria cri) throws SQLException;
	
	
	
}
