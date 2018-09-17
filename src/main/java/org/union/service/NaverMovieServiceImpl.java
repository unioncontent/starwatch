package org.union.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.union.domain.NaverMovieVO;
import org.union.domain.SearchCriteria;
import org.union.persistence.NaverMovieDAO;

@Service
public class NaverMovieServiceImpl implements NaverMovieService{

	
	@Autowired
	private NaverMovieDAO naverMovieDAO;
	
	@Override
	public List<NaverMovieVO> searchAllList(SearchCriteria cri) throws SQLException {

		return naverMovieDAO.searchAllList(cri);
	}
	
	@Override
	public List<NaverMovieVO> searchList(SearchCriteria cri) throws SQLException {

		List<NaverMovieVO> list = naverMovieDAO.searchList(cri);
		
		return list;
	}

	@Override
	public Integer getSearchCount(SearchCriteria cri) throws SQLException {

		return naverMovieDAO.getSearchCount(cri);
	}

	@Override
	public List<NaverMovieVO> showSearchList(SearchCriteria cri) throws SQLException {

		List<NaverMovieVO> list = naverMovieDAO.showSearchList(cri);
		
		return list;
	}

	@Override
	public List<NaverMovieVO> showSearchAllList(SearchCriteria cri) throws SQLException {

		return naverMovieDAO.showSearchAllList(cri);
	}

}
