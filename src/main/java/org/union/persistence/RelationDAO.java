package org.union.persistence;

import java.sql.SQLException;

import org.union.domain.RelationVO;

public interface RelationDAO {

	
	public void create(RelationVO vo) throws SQLException;
	
	public RelationVO read(Integer relation_idx) throws SQLException;
	
	public void update(RelationVO vo) throws SQLException;
	
	public void delete(Integer relation_idx) throws SQLException;
	
	public Integer todayCount() throws SQLException;
	
}
