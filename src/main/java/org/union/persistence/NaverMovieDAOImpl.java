package org.union.persistence;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.union.domain.NaverMovieVO;
import org.union.domain.SearchCriteria;

@Repository
public class NaverMovieDAOImpl implements NaverMovieDAO {

	
	@Autowired
	@Resource(name="oneSqlSession")
	private SqlSession session1;
	
	@Autowired
	@Resource(name="twoSqlSession")
	private SqlSession session2;
	
	private static final String namespace = "org.union.mappers.NaverMovieMapper.";
	
	
	@Override
	public void create(NaverMovieVO vo) throws SQLException {
		try {
			session1.insert(namespace + "create", vo);
			session2.insert(namespace + "create", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public NaverMovieVO read(Integer NM_idx) throws SQLException {

		return session1.selectOne(namespace + "read", NM_idx);
	}

	@Override
	public List<NaverMovieVO> searchAllList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "searchAllList", cri);
	}
	
	@Override
	public List<NaverMovieVO> searchList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "searchList", cri);
	}


	@Override
	public Integer getSearchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getSearchCount", cri);
	}


	@Override
	public List<NaverMovieVO> showSearchList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "showSearchList", cri);
	}


	@Override
	public List<NaverMovieVO> showSearchAllList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "showSearchAllList", cri);
	}

}
