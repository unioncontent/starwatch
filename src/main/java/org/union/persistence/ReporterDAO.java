package org.union.persistence;

import java.sql.SQLException;
import java.util.List;

import org.union.domain.ReporterVO;
import org.union.domain.SearchCriteria;

public interface ReporterDAO {

	
	public void create(ReporterVO vo) throws SQLException;
	
	public ReporterVO read(Integer reporter_idx) throws SQLException;
	
	public ReporterVO readByName(String reporter_name) throws SQLException;
	
	public void update(ReporterVO vo) throws SQLException;
	
	public void delete(Integer reporter_idx) throws SQLException;
	
	public List<ReporterVO> listSearch(SearchCriteria cri) throws SQLException;
	public Integer getSearchCount(SearchCriteria cri) throws SQLException;
	
	public List<ReporterVO> listAll() throws SQLException;
	
	public List<ReporterVO> listChecked() throws SQLException;
	
}
