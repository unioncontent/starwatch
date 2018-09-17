package org.union.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.union.domain.ExtractVO;
import org.union.domain.FvVO;
import org.union.domain.GraphVO;
import org.union.domain.NvVO;
import org.union.domain.SNSVO;
import org.union.domain.SearchCriteria;
import org.union.domain.SearchFv;
import org.union.domain.SearchNv;

public interface SNSService {

	public void regist(SNSVO vo) throws SQLException;
	
	public SNSVO view(Integer SNS_idx) throws SQLException;
	
	public void modify(SNSVO vo) throws SQLException;
	
	public void remove(Integer SNS_idx) throws SQLException;
	
	public List<SNSVO> allPageList(SearchCriteria cri) throws SQLException;
	public Integer allPageCount(SearchCriteria cri) throws SQLException;
	
	public Integer countAll(Date date) throws SQLException;
	public Integer showboxCountAll(Date date) throws SQLException;
	
	public Integer reportSnsCount(SearchCriteria cri) throws SQLException;
	
	public GraphVO yesterdayCount(String name) throws SQLException;
	public GraphVO showboxYesterdayCount(String name) throws SQLException;
	
	public GraphVO facebookSum(SearchCriteria cri) throws SQLException; 
	public GraphVO twitterSum(SearchCriteria cri) throws SQLException; 
	public GraphVO instagramSum(SearchCriteria cri) throws SQLException; 
	
	public List<SNSVO> listSearch(SearchCriteria cri) throws SQLException;
	public List<SNSVO> periodListSearch(SearchCriteria cri) throws SQLException;
	
	public Integer getSearchCount(SearchCriteria cri) throws SQLException;
	public Integer periodgetSearchCount(SearchCriteria cri) throws SQLException;
	
	public List<SNSVO> listExcel(SearchCriteria cri) throws SQLException;
	
	public Integer listExcelCount(SearchCriteria cri) throws SQLException;
	
	public List<ExtractVO> listExtract(SearchCriteria cri) throws SQLException;
	
	public Integer getExtractCount(SearchCriteria cri) throws SQLException;
	
	public List<SNSVO> listAll(SearchCriteria cri) throws SQLException;
	public List<SNSVO> youtubeListAll(SearchCriteria cri) throws SQLException;
	
	public List<SNSVO> facebookList (SearchCriteria cri) throws SQLException;
	
	public Integer facebookTotalCount(SearchCriteria  cri) throws SQLException;
	public Integer snsFacebookTotalCount(SearchCriteria  cri) throws SQLException;
	
	public List<SNSVO> instaList(SearchCriteria  cri) throws SQLException;
	
	public Integer instaTotalCount(SearchCriteria  cri) throws SQLException;
	
	public List<SNSVO> twitterList(SearchCriteria  cri) throws SQLException;
	
	public Integer twitterTotalCount(SearchCriteria  cri) throws SQLException;
	
	public List<SNSVO> youtubeList(SearchCriteria cri) throws SQLException;
	
	public Integer youtubeTotalCount(SearchCriteria cri) throws SQLException;
	
	public List<SNSVO> getDateCount(SearchCriteria cri) throws SQLException;
	
	public void modifyTextType(SNSVO vo) throws SQLException;
	
	public void modifyThumbnail(SNSVO vo) throws SQLException;
	
	public List<SNSVO> facebookCnt(SearchCriteria cri) throws SQLException;
	public List<SNSVO> instaCnt(SearchCriteria cri) throws SQLException;
	public List<SNSVO> twiCnt(SearchCriteria cri) throws SQLException;
	public List<SNSVO> snsTotalCnt(SearchCriteria cri) throws SQLException;
	
	public List<FvVO> facebookCGV(SearchCriteria cri) throws SQLException;
	public List<FvVO> facebookCGVList(SearchCriteria cri) throws SQLException;
	public List<FvVO> facebookCGVallList(SearchCriteria cri) throws SQLException;
	public Integer facebookCGVListTotalCnt(SearchCriteria cri) throws SQLException;
	public List<FvVO> fvlistSearch(SearchCriteria cri) throws SQLException;
	public List<FvVO> fvlistSearchEx(SearchCriteria cri) throws SQLException;
	public Integer fvlistSearchTotalCnt(SearchCriteria cri) throws SQLException;
	public List<FvVO> fvlistSearchList(SearchCriteria cri) throws SQLException;
	public Integer fvlistSearchListTotalCnt(SearchCriteria cri) throws SQLException;
	public Integer fvlistViewCnt(SearchFv fv) throws SQLException;
	public Integer fvlistReply_cnt(SearchFv fv) throws SQLException;
	public Integer fvlistlike_cnt(SearchFv fv) throws SQLException;
	public List<FvVO> fvlistPlus(SearchCriteria cri) throws SQLException;
	public List<FvVO> fvlistlimt(SearchCriteria cri) throws SQLException;
	public List<FvVO> fvlistMinus2(SearchCriteria cri) throws SQLException;
	public List<FvVO> fvlistMinus(SearchCriteria cri) throws SQLException;
	public List<FvVO> fvlistSearchTime(SearchCriteria cri) throws SQLException;
	public List<FvVO> fvSearchlistSearchTime(SearchCriteria cri) throws SQLException;
	public List<FvVO> fvlistOne(SearchCriteria cri) throws SQLException;
	public List<FvVO> fvlistTwo(SearchFv fv) throws SQLException;
	public List<FvVO> fvlistGraph(SearchFv fv) throws SQLException;
	
	public Integer snsTotalcount(SearchCriteria cri) throws SQLException;
	
	public Integer graphfacebookCount(SearchCriteria cri) throws SQLException;
	public Integer graphinstaCount(SearchCriteria cri) throws SQLException;
	public Integer graphtwitterCount(SearchCriteria cri) throws SQLException;
	public Integer graphyoutubeCount(SearchCriteria cri) throws SQLException;
	
	public Integer replyGetDateCount(SearchCriteria cri) throws SQLException;
	public Integer likeGetDateCount(SearchCriteria cri) throws SQLException;
	public Integer shareGetDateCount(SearchCriteria cri) throws SQLException;
	public Integer YviewGetDateCount(SearchCriteria cri) throws SQLException;
	public Integer YreplyGetDateCount(SearchCriteria cri) throws SQLException;
	public Integer YlikeGetDateCount(SearchCriteria cri) throws SQLException;
	
	public void fvUpdate(FvVO vo) throws SQLException;
	
	public List<FvVO> fvCheckList(String sns_writer) throws SQLException;
}
