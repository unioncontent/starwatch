package org.union.persistence;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.union.domain.CommunityVO;
import org.union.domain.SearchCriteria;
import org.union.domain.TextTypeDateVO;
import org.union.domain.TextTypeVO;

@Repository
public class CommunityDAOImpl implements CommunityDAO {

	@Autowired
	@Resource(name="oneSqlSession")
	private SqlSession session1;
	
	@Autowired
	@Resource(name="twoSqlSession")
	private SqlSession session2;
	
	
	private static final String namespace = "org.union.mapper.CommunityMapper.";
	
	
	@Override
	public void create(CommunityVO vo) throws SQLException {
		
		try {
			session1.insert(namespace + "create", vo);
			session2.insert(namespace + "create", vo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	@Override
	public CommunityVO read(Integer community_idx) throws SQLException {
		
		return session1.selectOne(namespace + "read", community_idx);
	}
	

	@Override
	public void update(CommunityVO vo) throws SQLException {
		
		try {
			session1.update(namespace + "update", vo);
			session2.update(namespace + "update", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	@Override
	public void delete(Integer community_idx) throws SQLException {
		
		try {
			session1.delete(namespace + "delete", community_idx);
			session2.delete(namespace + "delete", community_idx);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public List<CommunityVO> listExtract(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "listExtract", cri);
	}

	
	@Override
	public Integer getExtractCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getExtractCount", cri);
	}



	@Override
	public List<CommunityVO> listSearch(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "listSearch", cri); 
	}

	
	@Override
	public Integer getSearchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getSearchCount", cri);
	}
	
	@Override
	public Integer getSearchCount2(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getSearchCount2", cri);
	}
	
	@Override
	public List<CommunityVO> wlistSearch(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "wlistSearch", cri); 
	}

	
	@Override
	public Integer wgetSearchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "wgetSearchCount", cri);
	}
	
	@Override
	public List<CommunityVO> listAll(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "listAll", cri);
	}
	

	@Override
	public void updateTextType(CommunityVO vo) throws SQLException {
		try {
			session1.update(namespace + "updateTextType", vo);
			session2.update(namespace + "updateTextType", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void updateThumbnail(CommunityVO vo) throws SQLException {
		try {
			session1.update(namespace + "updateThumbnail", vo);
			session2.update(namespace + "updateThumbnail", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public List<CommunityVO> listComplete(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "listComplete", cri);
	}


	@Override
	public Integer getCompleteCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getCompleteCount", cri);
	}


	@Override
	public TextTypeVO textTypeCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "textTypeCount" ,cri);
	}


	@Override
	public Integer countAll(Date date) throws SQLException {

		return session1.selectOne(namespace  +"countAll", date);
	}

	@Override
	public Integer showboxCountAll(Date date) throws SQLException {

		return session1.selectOne(namespace  +"showboxCountAll", date);
	}

	@Override
	public List<CommunityVO> allPageList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "allPageList", cri);
	}
	
	@Override
	public List<CommunityVO> TotalAllPageList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "TotalAllPageList", cri);
	}


	@Override
	public Integer allPageCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "allPageCount", cri);
	}
	
	@Override
	public Integer TotalAllPageCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "TotalAllPageCount", cri);
	}

	@Override
	public List<CommunityVO> wPageSearch(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "wPageSearch", cri);
	}


	@Override
	public List<CommunityVO> allPage(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "allPage", cri);
	}


	@Override
	public List<TextTypeDateVO> textTypeCount2(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "textTypeCount2", cri);
	}


	@Override
	public Integer communityTextcnt(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "communityTextcnt", cri);
	}


	@Override
	public Integer communityTextcnt2(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "communityTextcnt2", cri);
	}


	@Override
	public Integer graphSearchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "graphSearchCount", cri);
	}


	@Override
	public List<CommunityVO> totalAllPageex(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "totalAllPageex", cri);
	}


	@Override
	public List<CommunityVO> dashListAll(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "dashListAll", cri);
	}


	@Override
	public Integer periodWgetSearchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "periodWgetSearchCount", cri);
	}


	@Override
	public List<CommunityVO> alllistExtract(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "alllistExtract", cri);
	}


	@Override
	public Integer allgetExtractCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "allgetExtractCount", cri);
	}


	@Override
	public List<CommunityVO> alllistSearch(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "alllistSearch", cri);
	}


	@Override
	public Integer allgetSearchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "allgetSearchCount", cri);
	}


	@Override
	public List<CommunityVO> allPageallList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "allPageallList", cri);
	}


	@Override
	public Integer allPageallCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "allPageallCount", cri);
	}


	@Override
	public List<CommunityVO> totalallPageallList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "totalallPageallList", cri);
	}


	@Override
	public Integer totalallPageallCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "totalallPageallCount", cri);
	}


	@Override
	public List<CommunityVO> listAllEx(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "listAllEx", cri);
	}

}
