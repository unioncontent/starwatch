package org.union.persistence;

import java.sql.SQLException;
import java.util.List;

import org.union.domain.MediaVO;
import org.union.domain.ReplyVO;
import org.union.domain.SearchCriteria;
import org.union.domain.TextTypeVO;

public interface MainDAO {
	
	public Integer portalSearchCount(SearchCriteria cri) throws SQLException;
	public Integer portalBadSearchCount(SearchCriteria cri) throws SQLException;
	public Integer communitySearchCount(SearchCriteria cri) throws SQLException;
	public Integer communityBadSearchCount(SearchCriteria cri) throws SQLException;
	public Integer snsSearchCount(SearchCriteria cri) throws SQLException;
	public Integer youtubeTotalCount(SearchCriteria cri) throws SQLException;
	public Integer mediaSearchCount(SearchCriteria cri) throws SQLException;
	public Integer mediaBadSearchCount(SearchCriteria cri) throws SQLException;
	public Integer replySearchCount(SearchCriteria cri) throws SQLException;
	public Integer replyBadSearchCount(SearchCriteria cri) throws SQLException;
	
	public List<TextTypeVO> mediaTextTypeCount(SearchCriteria cri) throws SQLException;
	public List<TextTypeVO> replyTextTypeCount(SearchCriteria cri) throws SQLException;
	public List<TextTypeVO> writeTextTypeCount(SearchCriteria cri) throws SQLException;
	
	public List<MediaVO> detailMediaList(SearchCriteria cri) throws SQLException;
	public List<MediaVO> detailwriteList(SearchCriteria cri) throws SQLException;
	public Integer detailwriteCount(SearchCriteria cri) throws SQLException;
	public List<ReplyVO> detailReplyList(SearchCriteria cri) throws SQLException;

}
