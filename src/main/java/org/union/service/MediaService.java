package org.union.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.union.domain.ExtractVO;
import org.union.domain.GraphVO;
import org.union.domain.MailVO;
import org.union.domain.MediaVO;
import org.union.domain.NewsVO;
import org.union.domain.PeriodMediaVO;
import org.union.domain.ReplyVO;
import org.union.domain.SearchCriteria;
import org.union.domain.SearchFv;
import org.union.domain.SearchMedia;
import org.union.domain.TextTypeDateVO;
import org.union.domain.TextTypeVO;

public interface MediaService {

	public void regist(MediaVO vo) throws SQLException;
	public void replyAdd(NewsVO vo) throws SQLException;
	
	public List<NewsVO> newsList(SearchCriteria cri) throws SQLException;
	
	public MediaVO view(Integer media_idx) throws SQLException;
	
	public void modify(MediaVO vo) throws SQLException;
	
	public void remove(Integer media_idx) throws SQLException;
	
	public void checkList(Integer media_idx) throws SQLException;
	public void checkDelete(Integer media_idx) throws SQLException;
	public void headlineUpdate(Integer media_idx) throws SQLException;
	public void headlineUpdate2(Integer media_idx) throws SQLException;
	
	public void newsRemove(Integer media_idx) throws SQLException;
	
	public void replyRemove(Integer reply_idx) throws SQLException;
	
	public List<ReplyVO> replyList(SearchCriteria cri) throws SQLException; 
	public List<ReplyVO> replyTotalList(Integer news_idx) throws SQLException;
	
	public TextTypeVO periodTextTypeCount(SearchCriteria cri) throws SQLException;
	
	public List<MediaVO> allPageList(SearchCriteria cri) throws SQLException;
	public List<MediaVO> TotalAllPageList(SearchCriteria cri) throws SQLException;
	public Integer allPageCount(SearchCriteria cri) throws SQLException;
	public Integer TotalAllPageCount(SearchCriteria cri) throws SQLException;
	public Integer newsAllPageCount(SearchCriteria cri) throws SQLException;
	public Integer replyAllPageCount(SearchCriteria cri) throws SQLException;
	
	public List<MediaVO> allPage(SearchCriteria cri) throws SQLException;
	public List<MediaVO> newsExcel(SearchCriteria cri) throws SQLException;
	public List<MediaVO> totalAllPageex(SearchCriteria cri) throws SQLException;
	public List<ReplyVO> replyAllPage(SearchCriteria cri) throws SQLException;
	
	public GraphVO yesterdayCount() throws SQLException;
	public GraphVO showboxYesterdayCount() throws SQLException;
	
	public TextTypeVO naverMediaCount(SearchCriteria cri) throws SQLException;
	public TextTypeVO daumMediaCount(SearchCriteria cri) throws SQLException;
	public TextTypeVO totalMediaCount(SearchCriteria cri) throws SQLException;
	
	public List<ExtractVO> listExtract(SearchCriteria cri) throws SQLException;
	
	public Integer getExtractCount(SearchCriteria cri) throws SQLException;
	
	public List<MediaVO> listSearch(SearchCriteria criteria) throws SQLException;
	public List<MediaVO> textTypelistSearch(SearchCriteria cri) throws SQLException;
	public List<MediaVO> textTypelistSearch2(SearchCriteria cri) throws SQLException;
	public List<MediaVO> textTypelistSearch3(SearchCriteria cri) throws SQLException;
	public List<MediaVO> textTypelistSearch4(SearchCriteria cri) throws SQLException;
	
	public Integer getSearchCount(SearchCriteria cri) throws SQLException;

	public List<MediaVO> mediaListData(SearchCriteria cri) throws SQLException;
	public List<MediaVO> wlistSearch(SearchCriteria criteria) throws SQLException;
	public List<MediaVO> wlistSearch2(SearchCriteria criteria) throws SQLException;
	public List<MediaVO> wPageSearch(SearchCriteria criteria) throws SQLException;
	
	public Integer wgetSearchCount(SearchCriteria cri) throws SQLException;
	
	public List<MediaVO> listAll(SearchCriteria cri) throws SQLException;
	public List<MediaVO> listAllEx(SearchCriteria cri) throws SQLException;
	public List<MediaVO> dashListAll(SearchCriteria cri) throws SQLException;
	
	public List<PeriodMediaVO> periodMedia(SearchCriteria criteria) throws SQLException;
	
	public List<PeriodMediaVO> periodReporter(SearchCriteria criteria) throws SQLException;
	
	public Integer getTotalCount(SearchCriteria criteria) throws SQLException;
	public Integer getMatchCount(SearchCriteria criteria) throws SQLException;
	
	public Integer mediaGetTotalCount(SearchCriteria criteria) throws SQLException;
	
	public Integer reporterGetTotalCount(SearchCriteria criteria) throws SQLException;
	
	public void modifyType(MediaVO vo) throws SQLException;
	public void newsUpdateTextType (NewsVO vo) throws SQLException;
	public void newsUpdateState (NewsVO vo) throws SQLException;
	public void replyUpdateTextType (ReplyVO vo) throws SQLException;
	
	public void modifyThumbnail(MediaVO vo) throws SQLException;
	
	public TextTypeVO getMediaPortalCount(SearchCriteria cri) throws SQLException;
	public TextTypeVO getMediaTextTypeTotalCount(SearchCriteria cri) throws SQLException;
	public TextTypeVO getMediaTextTypeSearchCount(SearchCriteria cri) throws SQLException;
	
	public TextTypeVO getPressPortalCount(SearchCriteria cri) throws SQLException;
	public TextTypeVO getPressTextTypeTotalCount(SearchCriteria cri) throws SQLException;
	public TextTypeVO getPressTextTypeSearchCount(SearchCriteria cri) throws SQLException;
	
	public List<MediaVO> reporterGetTextTypeCount(SearchCriteria cri, String reporter,String textType) throws SQLException;
	
	public List<MediaVO> mediaCnt(SearchCriteria cri) throws SQLException;
	public List<TextTypeDateVO> textTypeCount2(SearchCriteria cri) throws SQLException;
	public Integer checkUrl(String url) throws SQLException;
	public Integer mTotalCnt(SearchCriteria cri) throws SQLException;
	public Integer graphmTotalCnt(SearchCriteria cri) throws SQLException;
	
	public List<MediaVO> headlineList(SearchCriteria cri) throws SQLException;
	public Integer mediaTotalcnt(SearchCriteria cri) throws SQLException;
	public Integer replyTotalcnt(SearchCriteria cri) throws SQLException;
	
	public Integer mediaTextcnt(SearchCriteria cri) throws SQLException;
	public Integer mediaTextcnt2(SearchCriteria cri) throws SQLException;
	public Integer mediaCountAll(SearchCriteria cri) throws SQLException;
	
	public List<MailVO> mailList(SearchCriteria cri) throws SQLException;
	
	public Integer mailCountAll(SearchCriteria cri) throws SQLException;
	
	public List<MediaVO> mediaMatchList(SearchCriteria cri) throws SQLException;
	public Integer mediaMatchCount(SearchCriteria cri) throws SQLException;
	public List<MediaVO> mediaMatchList2(SearchCriteria cri) throws SQLException;
	public Integer mediaMatchCount2(SearchCriteria cri) throws SQLException;
	public Integer mediaMatchCount3(SearchCriteria cri) throws SQLException;
	public Integer mediaMatchCount4(SearchCriteria cri) throws SQLException;
	public List<MediaVO> mediaTotalMatchList(SearchCriteria cri) throws SQLException;
	public List<MediaVO> mediaDataList(SearchCriteria cri) throws SQLException;
	public List<MediaVO> mediaMatchDataList(SearchCriteria cri) throws SQLException;
	
	public List<MediaVO> reporterMatchList(SearchCriteria cri) throws SQLException;
	public Integer reporterMatchCount(SearchCriteria cri) throws SQLException;
	public List<MediaVO> reporterMatchList2(SearchCriteria cri) throws SQLException;
	public Integer reporterMatchCount2(SearchCriteria cri) throws SQLException;
	public Integer reporterMatchCount3(SearchCriteria cri) throws SQLException;
	public Integer reporterMatchCount4(SearchCriteria cri) throws SQLException;
	public List<MediaVO> reporterTotalMatchList(SearchCriteria cri) throws SQLException;
	public List<MediaVO> reporterDataList(SearchCriteria cri) throws SQLException;
	public List<MediaVO> reporterMatchDataList(SearchCriteria cri) throws SQLException;
	
	public List<MediaVO> mediaMatchallList(SearchCriteria cri) throws SQLException;
	public List<MediaVO> reporterMatchallList(SearchCriteria cri) throws SQLException;
	public List<MediaVO> mailMatch(SearchCriteria cri) throws SQLException;
	public List<MediaVO> dashMediaMatch(SearchCriteria cri) throws SQLException;
}
