package org.union.persistence;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.union.domain.MediaVO;
import org.union.domain.ReplyVO;
import org.union.domain.SearchCriteria;
import org.union.domain.TextTypeVO;

@Repository
public class MainDAOImpl implements MainDAO{

	@Autowired
	@Resource(name="oneSqlSession")
	private SqlSession session1;
	
	@Autowired
	@Resource(name="twoSqlSession")
	private SqlSession session2;
	
	private static final String namespace = "org.union.mappers.MainMapper.";

	@Override
	public Integer portalSearchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "portalSearchCount", cri);
	}

	@Override
	public Integer communitySearchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "communitySearchCount", cri);
	}

	@Override
	public Integer snsSearchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "snsSearchCount", cri);
	}

	@Override
	public Integer youtubeTotalCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "youtubeTotalCount", cri);
	}

	@Override
	public Integer mediaSearchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "mediaSearchCount", cri);
	}
	
	@Override
	public Integer replySearchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "replySearchCount", cri);
	}
	
	@Override
	public Integer mediaBadSearchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "mediaBadSearchCount", cri);
	}

	@Override
	public Integer portalBadSearchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "portalBadSearchCount", cri);
	}

	@Override
	public Integer communityBadSearchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "communityBadSearchCount", cri);
	}

	@Override
	public Integer replyBadSearchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "replyBadSearchCount", cri);
	}

	@Override
	public List<TextTypeVO> mediaTextTypeCount(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "mediaTextTypeCount", cri);
	}

	@Override
	public List<TextTypeVO> replyTextTypeCount(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "replyTextTypeCount", cri);
	}

	@Override
	public List<TextTypeVO> writeTextTypeCount(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "writeTextTypeCount", cri);
	}

	@Override
	public List<MediaVO> detailMediaList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "detailMediaList", cri);
	}

	@Override
	public List<MediaVO> detailwriteList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "detailwriteList", cri);
	}

	@Override
	public Integer detailwriteCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "detailwriteCount", cri);
	}

	@Override
	public List<ReplyVO> detailReplyList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "detailReplyList", cri);
	}


}
