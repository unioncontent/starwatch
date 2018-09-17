package org.union.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.union.domain.SearchCriteria;
import org.union.persistence.EtDAO;

@Service
public class EtServiceImpl implements EtService{

	
	@Autowired
	private EtDAO etDAO;
	
	
	@Override
	public Integer getSearchCount1(SearchCriteria cri) throws SQLException {
	
		return etDAO.getSearchCount1(cri);
	}


	@Override
	public Integer getSearchCount2(SearchCriteria cri) throws SQLException {
		
		return etDAO.getSearchCount2(cri);
	}


	@Override
	public Integer getSearchCount3(SearchCriteria cri) throws SQLException {
	
		return etDAO.getSearchCount3(cri);
	}


	@Override
	public Integer getSearchCountAll(SearchCriteria cri) throws SQLException {

		return etDAO.getSearchCountAll(cri);
	}

}
