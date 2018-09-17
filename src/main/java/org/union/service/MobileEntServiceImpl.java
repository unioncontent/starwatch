package org.union.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.union.domain.MobileEntVO;
import org.union.domain.SearchCriteria;
import org.union.persistence.MobileEntDAO;

@Service
public class MobileEntServiceImpl implements MobileEntService{

	
	@Autowired
	private MobileEntDAO mobileEntDAO;
		
	@Override
	public Integer getTypeOfMovieCount(SearchCriteria cri) throws SQLException {

		return mobileEntDAO.getTypeOfMovieCount(cri);
	}

	@Override
	public Integer getTypeOfActorCount(SearchCriteria cri) throws SQLException {

		return mobileEntDAO.getTypeOfActorCount(cri);
	}
	
	@Override
	public List<MobileEntVO> searchList(SearchCriteria cri) throws SQLException {

		return mobileEntDAO.searchList(cri);
	}
	
	
	@Override
	public List<MobileEntVO> searchAllList(SearchCriteria cri) throws SQLException {

		return mobileEntDAO.searchAllList(cri);
	}

	@Override
	public Integer getSearchCount(SearchCriteria cri) throws SQLException {

		return mobileEntDAO.getSearchCount(cri);
	}

	@Override
	public Integer MgetTypeOfMovieCount(SearchCriteria cri) throws SQLException {

		return mobileEntDAO.MgetTypeOfMovieCount(cri);
	}

	@Override
	public Integer MgetTypeOfActorCount(SearchCriteria cri) throws SQLException {

		return mobileEntDAO.MgetTypeOfActorCount(cri);
	}

	@Override
	public List<MobileEntVO> MsearchList(SearchCriteria cri) throws SQLException {

		return mobileEntDAO.MsearchList(cri);
	}

	@Override
	public List<MobileEntVO> MsearchAllList(SearchCriteria cri) throws SQLException {

		return mobileEntDAO.MsearchAllList(cri);
	}

	@Override
	public Integer MgetSearchCount(SearchCriteria cri) throws SQLException {

		return mobileEntDAO.MgetSearchCount(cri);
	}

	@Override
	public Integer getMatchCount(SearchCriteria cri) throws SQLException {

		return mobileEntDAO.getMatchCount(cri);
	}

	@Override
	public Integer MgetMatchCount(SearchCriteria cri) throws SQLException {

		return mobileEntDAO.MgetMatchCount(cri);
	}

	@Override
	public Integer getTypeOfMovieCountGraph(SearchCriteria cri) throws SQLException {

		return mobileEntDAO.getTypeOfMovieCountGraph(cri);
	}

	@Override
	public Integer getTypeOfActorCountGraph(SearchCriteria cri) throws SQLException {

		return mobileEntDAO.getTypeOfActorCountGraph(cri);
	}

	@Override
	public Integer getMatchCountGraph(SearchCriteria cri) throws SQLException {

		return mobileEntDAO.getMatchCountGraph(cri);
	}

	@Override
	public Integer MgetTypeOfMovieCountGraph(SearchCriteria cri) throws SQLException {

		return mobileEntDAO.MgetTypeOfMovieCountGraph(cri);
	}

	@Override
	public Integer MgetTypeOfActorCountGraph(SearchCriteria cri) throws SQLException {

		return mobileEntDAO.MgetTypeOfActorCountGraph(cri);
	}

	@Override
	public Integer MgetMatchCountGraph(SearchCriteria cri) throws SQLException {

		return mobileEntDAO.MgetMatchCountGraph(cri);
	}

	@Override
	public List<MobileEntVO> showSearchList(SearchCriteria cri) throws SQLException {

		return mobileEntDAO.showSearchList(cri);
	}

	@Override
	public List<MobileEntVO> showSearchAllList(SearchCriteria cri) throws SQLException {

		return mobileEntDAO.showSearchAllList(cri);
	}

	@Override
	public List<MobileEntVO> showMsearchList(SearchCriteria cri) throws SQLException {

		return mobileEntDAO.showMsearchList(cri);
	}

	@Override
	public List<MobileEntVO> showMsearchAllList(SearchCriteria cri) throws SQLException {

		return mobileEntDAO.showMsearchAllList(cri);
	}

}
