package org.union.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.union.domain.CommunityVO;
import org.union.domain.ExtractVO;
import org.union.domain.SearchCriteria;
import org.union.domain.TextTypeDateVO;
import org.union.domain.TextTypeVO;

public interface CommunityService {
	
	public void regist(CommunityVO vo) throws SQLException;
	
	public CommunityVO read(Integer community_idx) throws SQLException;
	
	public void modify(CommunityVO vo) throws SQLException;

	public void remove(Integer community_idx) throws SQLException;
	
	public List<CommunityVO> totalallPageallList(SearchCriteria cri) throws SQLException;
	public Integer totalallPageallCount(SearchCriteria cri) throws SQLException;
	public List<CommunityVO> allPageallList(SearchCriteria cri) throws SQLException;
	public Integer allPageallCount(SearchCriteria cri) throws SQLException;
	
	public List<CommunityVO> allPageList(SearchCriteria cri) throws SQLException;
	public List<CommunityVO> TotalAllPageList(SearchCriteria cri) throws SQLException;
	public Integer allPageCount(SearchCriteria cri) throws SQLException;
	public Integer TotalAllPageCount(SearchCriteria cri) throws SQLException;
	public List<CommunityVO> allPage(SearchCriteria cri) throws SQLException;
	public List<CommunityVO> totalAllPageex(SearchCriteria cri) throws SQLException;
	
	public List<CommunityVO> wPageSearch(SearchCriteria cri) throws SQLException;
	
	public Integer countAll(Date date) throws SQLException;
	public Integer showboxCountAll(Date date) throws SQLException;
	
	public TextTypeVO textTypeCount(SearchCriteria cri) throws SQLException;
	
	public List<ExtractVO> listExtract(SearchCriteria cri) throws SQLException;
	public Integer getExtractCount(SearchCriteria cri) throws SQLException;
	
	public List<CommunityVO> alllistExtract(SearchCriteria cri) throws SQLException;
	public Integer allgetExtractCount(SearchCriteria cri) throws SQLException;
	
	public List<CommunityVO> alllistSearch(SearchCriteria cri) throws SQLException;
	public Integer allgetSearchCount(SearchCriteria cri) throws SQLException;
	
	public List<CommunityVO> listSearch(SearchCriteria vo) throws SQLException;
	
	public Integer getSearchCount(SearchCriteria cri) throws SQLException;
	public Integer getSearchCount2(SearchCriteria cri) throws SQLException;

	public List<CommunityVO> wlistSearch(SearchCriteria vo) throws SQLException;
	
	public Integer wgetSearchCount(SearchCriteria cri) throws SQLException;
	public Integer periodWgetSearchCount(SearchCriteria cri) throws SQLException;
	
	public List<CommunityVO> listComplete(SearchCriteria cri) throws SQLException;
	
	public Integer getCompleteCount(SearchCriteria cri) throws SQLException;
	
	public List<CommunityVO> listAll(SearchCriteria cri) throws SQLException;
	public List<CommunityVO> listAllEx(SearchCriteria cri) throws SQLException;
	public List<CommunityVO> dashListAll(SearchCriteria cri) throws SQLException;
	
	public void modifyType (CommunityVO vo) throws SQLException;
	
	public void modifyThumbnail (CommunityVO vo) throws SQLException;
	
	public List<TextTypeDateVO> textTypeCount2(SearchCriteria cri) throws SQLException;
	
	public Integer communityTextcnt(SearchCriteria cri) throws SQLException;
	public Integer communityTextcnt2(SearchCriteria cri) throws SQLException;
	
	public Integer graphSearchCount(SearchCriteria cri) throws SQLException;
}
