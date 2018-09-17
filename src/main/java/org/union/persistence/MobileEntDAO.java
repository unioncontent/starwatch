package org.union.persistence;

import java.sql.SQLException;
import java.util.List;

import org.union.domain.MobileEntVO;
import org.union.domain.SearchCriteria;

public interface MobileEntDAO {
	
    public Integer getTypeOfMovieCount(SearchCriteria cri) throws SQLException;
    	
	public Integer getTypeOfActorCount(SearchCriteria cri) throws SQLException;
	
	public Integer getMatchCount(SearchCriteria cri) throws SQLException;
	
	public MobileEntVO read(Integer NM_idx) throws SQLException;
	
	public List<MobileEntVO> searchList(SearchCriteria cri) throws SQLException;
	
	public List<MobileEntVO> showSearchList(SearchCriteria cri) throws SQLException;
	
	public List<MobileEntVO> searchAllList(SearchCriteria cri) throws SQLException;
	
	public List<MobileEntVO> showSearchAllList(SearchCriteria cri) throws SQLException;
	
	public Integer getSearchCount(SearchCriteria cri) throws SQLException;
	
	
	public Integer MgetTypeOfMovieCount(SearchCriteria cri) throws SQLException;
	
	public Integer MgetTypeOfActorCount(SearchCriteria cri) throws SQLException;
	
	public List<MobileEntVO> MsearchList(SearchCriteria cri) throws SQLException;
	
	public List<MobileEntVO> showMsearchList(SearchCriteria cri) throws SQLException;
	
	public List<MobileEntVO> MsearchAllList(SearchCriteria cri) throws SQLException;
	
	public List<MobileEntVO> showMsearchAllList(SearchCriteria cri) throws SQLException;
	
	public Integer MgetSearchCount(SearchCriteria cri) throws SQLException;
	
	public Integer MgetMatchCount(SearchCriteria cri) throws SQLException;
	
	public Integer getTypeOfMovieCountGraph(SearchCriteria cri) throws SQLException;
	
	public Integer getTypeOfActorCountGraph(SearchCriteria cri) throws SQLException;
	
	public Integer getMatchCountGraph(SearchCriteria cri) throws SQLException;
	
	public Integer MgetTypeOfMovieCountGraph(SearchCriteria cri) throws SQLException;
	
	public Integer MgetTypeOfActorCountGraph(SearchCriteria cri) throws SQLException;
	
	public Integer MgetMatchCountGraph(SearchCriteria cri) throws SQLException;
}
