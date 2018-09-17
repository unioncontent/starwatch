package org.union.persistence;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.union.domain.MobileEntVO;
import org.union.domain.SearchCriteria;

@Repository
public class MobileEntDAOImpl implements MobileEntDAO{
	
	@Autowired
	@Resource(name="oneSqlSession")
	private SqlSession session1;
	
	@Autowired
	@Resource(name="twoSqlSession")
	private SqlSession session2;
	
	private static final String namespace = "org.union.mappers.MobileEntMapper.";
		
	@Override
	public Integer getTypeOfMovieCount(SearchCriteria cri)  throws SQLException {

		return session1.selectOne(namespace + "getTypeOfMovieCount", cri);
	}


	@Override
	public Integer getTypeOfActorCount(SearchCriteria cri)  throws SQLException {

		return session1.selectOne(namespace + "getTypeOfActorCount", cri);
	}
	
	@Override
	public MobileEntVO read(Integer NM_idx)  throws SQLException {

		return session1.selectOne(namespace + "read", NM_idx);
	}

	@Override
	public List<MobileEntVO> searchList(SearchCriteria cri)  throws SQLException {

		return session1.selectList(namespace + "searchList", cri);
	}
	
	@Override
	public List<MobileEntVO> searchAllList(SearchCriteria cri)  throws SQLException {

		return session1.selectList(namespace + "searchAllList", cri);
	}


	@Override
	public Integer getSearchCount(SearchCriteria cri)  throws SQLException {
	    
		return session1.selectOne(namespace + "getSearchCount", cri);
	}


	@Override
	public Integer MgetTypeOfMovieCount(SearchCriteria cri)  throws SQLException {

		return session1.selectOne(namespace + "MgetTypeOfMovieCount", cri);
	}


	@Override
	public Integer MgetTypeOfActorCount(SearchCriteria cri)  throws SQLException {

		return session1.selectOne(namespace + "MgetTypeOfActorCount", cri);
	}


	@Override
	public List<MobileEntVO> MsearchList(SearchCriteria cri)  throws SQLException {

		return session1.selectList(namespace + "MsearchList", cri);
	}


	@Override
	public List<MobileEntVO> MsearchAllList(SearchCriteria cri)  throws SQLException {

		return session1.selectList(namespace + "MsearchAllList", cri);
	}


	@Override
	public Integer MgetSearchCount(SearchCriteria cri)  throws SQLException {
		 
		return session1.selectOne(namespace + "MgetSearchCount", cri);
	}


	@Override
	public Integer getMatchCount(SearchCriteria cri)  throws SQLException {

		return session1.selectOne(namespace + "getMatchCount", cri);
	}


	@Override
	public Integer MgetMatchCount(SearchCriteria cri)  throws SQLException {

		return session1.selectOne(namespace + "MgetMatchCount", cri);
	}


	@Override
	public Integer getTypeOfMovieCountGraph(SearchCriteria cri)  throws SQLException {

		return session1.selectOne(namespace + "getTypeOfMovieCountGraph", cri);
	}


	@Override
	public Integer getTypeOfActorCountGraph(SearchCriteria cri)  throws SQLException {

		return session1.selectOne(namespace + "getTypeOfActorCountGraph", cri);
	}


	@Override
	public Integer getMatchCountGraph(SearchCriteria cri)  throws SQLException {

		return session1.selectOne(namespace + "getMatchCountGraph", cri);
	}


	@Override
	public Integer MgetTypeOfMovieCountGraph(SearchCriteria cri)  throws SQLException {

		return session1.selectOne(namespace + "MgetTypeOfMovieCountGraph", cri);
	}


	@Override
	public Integer MgetTypeOfActorCountGraph(SearchCriteria cri)  throws SQLException {

		return session1.selectOne(namespace + "MgetTypeOfActorCountGraph", cri);
	}


	@Override
	public Integer MgetMatchCountGraph(SearchCriteria cri)  throws SQLException {

		return session1.selectOne(namespace + "MgetMatchCountGraph", cri);
	}


	@Override
	public List<MobileEntVO> showSearchList(SearchCriteria cri)  throws SQLException {

		return session1.selectList(namespace + "showSearchList", cri);
	}


	@Override
	public List<MobileEntVO> showSearchAllList(SearchCriteria cri)  throws SQLException {

		return session1.selectList(namespace + "showSearchAllList", cri);
	}


	@Override
	public List<MobileEntVO> showMsearchList(SearchCriteria cri)  throws SQLException {

		return session1.selectList(namespace + "showMsearchList", cri);
	}


	@Override
	public List<MobileEntVO> showMsearchAllList(SearchCriteria cri)  throws SQLException {

		return session1.selectList(namespace + "showMsearchAllList", cri);
	}

}
