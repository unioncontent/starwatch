package org.union.persistence;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.union.domain.ReporterVO;
import org.union.domain.SearchCriteria;

@Repository
public class ReporterDAOImpl implements ReporterDAO	{
	
	
	@Autowired
	@Resource(name="oneSqlSession")
	private SqlSession session1;
	
	@Autowired
	@Resource(name="twoSqlSession")
	private SqlSession session2;
	
	private static final String namespace = "org.union.mappers.ReporterMapper.";
	
	
	@Override
	public void create(ReporterVO vo) throws SQLException {
		try {
			session1.insert(namespace + "create", vo);
			session2.insert(namespace + "create", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public ReporterVO read(Integer reporter_idx) throws SQLException {
		
		return session1.selectOne(namespace + "read", reporter_idx);
	}

	
	@Override
	public void update(ReporterVO vo) throws SQLException {
		try {
			session1.update(namespace + "update", vo);
			session2.update(namespace + "update", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void delete(Integer reporter_idx) throws SQLException {
		try {
			session1.delete(namespace + "delete", reporter_idx);
			session2.delete(namespace + "delete", reporter_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public List<ReporterVO> listAll() throws SQLException {

		return session1.selectList(namespace + "listAll");
	}


	@Override
	public List<ReporterVO> listChecked() throws SQLException {

		return session1.selectList(namespace  +"listChecked");
	}


	@Override
	public List<ReporterVO> listSearch(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "listSearch", cri);
	}


	@Override
	public Integer getSearchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getSearchCount", cri);
	}


	@Override
	public ReporterVO readByName(String reporter_name) throws SQLException {

		return session1.selectOne(namespace + "readByName", reporter_name);
	}

}
