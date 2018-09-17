package org.union.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.union.domain.MediaVO;
import org.union.domain.ReplyVO;
import org.union.domain.SearchCriteria;
import org.union.domain.TextTypeVO;
import org.union.persistence.MainDAO;

@Service
public class MainServiceImpl implements MainService {
	
	@Autowired
	private MainDAO mainDAO;

	@Override
	public Integer portalSearchCount(SearchCriteria cri) throws SQLException {

		return mainDAO.portalSearchCount(cri);
	}

	@Override
	public Integer communitySearchCount(SearchCriteria cri) throws SQLException {

		return mainDAO.communitySearchCount(cri);
	}

	@Override
	public Integer snsSearchCount(SearchCriteria cri) throws SQLException {

		return mainDAO.snsSearchCount(cri);
	}

	@Override
	public Integer youtubeTotalCount(SearchCriteria cri) throws SQLException {

		return mainDAO.youtubeTotalCount(cri);
	}

	@Override
	public Integer mediaSearchCount(SearchCriteria cri) throws SQLException {

		return mainDAO.mediaSearchCount(cri);
	}
	
	@Override
	public Integer replySearchCount(SearchCriteria cri) throws SQLException {

		return mainDAO.replySearchCount(cri);
	}
	
	@Override
	public Integer mediaBadSearchCount(SearchCriteria cri) throws SQLException {

		return mainDAO.mediaBadSearchCount(cri);
	}

	@Override
	public Integer portalBadSearchCount(SearchCriteria cri) throws SQLException {

		return mainDAO.portalBadSearchCount(cri);
	}

	@Override
	public Integer communityBadSearchCount(SearchCriteria cri) throws SQLException {

		return mainDAO.communityBadSearchCount(cri);
	}

	@Override
	public Integer replyBadSearchCount(SearchCriteria cri) throws SQLException {

		return mainDAO.replyBadSearchCount(cri);
	}

	@Override
	public List<TextTypeVO> mediaTextTypeCount(SearchCriteria cri) throws SQLException {

		return mainDAO.mediaTextTypeCount(cri);
	}

	@Override
	public List<TextTypeVO> replyTextTypeCount(SearchCriteria cri) throws SQLException {

		return mainDAO.replyTextTypeCount(cri);
	}

	@Override
	public List<TextTypeVO> writeTextTypeCount(SearchCriteria cri) throws SQLException {

		return mainDAO.writeTextTypeCount(cri);
	}

	@Override
	public List<MediaVO> detailMediaList(SearchCriteria cri) throws SQLException {

		return mainDAO.detailMediaList(cri);
	}

	@Override
	public List<MediaVO> detailwriteList(SearchCriteria cri) throws SQLException {

		return mainDAO.detailwriteList(cri);
	}

	@Override
	public Integer detailwriteCount(SearchCriteria cri) throws SQLException {

		return mainDAO.detailwriteCount(cri);
	}

	@Override
	public List<ReplyVO> detailReplyList(SearchCriteria cri) throws SQLException {

		return mainDAO.detailReplyList(cri);
	}

}
