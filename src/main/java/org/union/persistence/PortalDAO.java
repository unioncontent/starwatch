package org.union.persistence;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.union.domain.CommunityVO;
import org.union.domain.FvVO;
import org.union.domain.GraphVO;
import org.union.domain.NavertalkVO;
import org.union.domain.NvVO;
import org.union.domain.PortalVO;
import org.union.domain.ScoreVO;
import org.union.domain.SearchCriteria;
import org.union.domain.SearchFv;
import org.union.domain.TextTypeDateVO;
import org.union.domain.TextTypeVO;

public interface PortalDAO {

	public void create(PortalVO vo) throws SQLException;
	
	public PortalVO read(Integer portal_idx) throws SQLException;
	
	public void update(PortalVO vo) throws SQLException;
	
	public void delete(Integer portal_idx) throws SQLException;
	
	public void scoreCheckList(Integer portal_idx) throws SQLException;
	public void scoreCheckDelete(Integer portal_idx) throws SQLException;
	public void scoreUpdate(Integer portal_idx) throws SQLException;
	public void scoreUpdate2(Integer portal_idx) throws SQLException;
	
	public Integer getScoreCount(SearchCriteria cri) throws SQLException;
	public List<PortalVO> getScoreList(SearchCriteria cri) throws SQLException;
	public List<PortalVO> getScoreExcelList(SearchCriteria cri) throws SQLException;
	public TextTypeVO getScoreTextType(SearchCriteria cri) throws SQLException;
	public List<Integer> getOnlyScore(SearchCriteria cri) throws SQLException;
	
	public Integer getTypeOfMovieCount(SearchCriteria cri) throws SQLException;
	public Integer getTypeOfActorCount(SearchCriteria cri) throws SQLException;
	public List<PortalVO> wPageSearch(SearchCriteria cri) throws SQLException;
	
	public List<PortalVO> allPageList(SearchCriteria cri) throws SQLException;
	public List<PortalVO> TotalAllPageList(SearchCriteria cri) throws SQLException;
	public Integer allPageCount(SearchCriteria cri) throws SQLException;
	public Integer TotalAllPageCount(SearchCriteria cri) throws SQLException;
	public List<PortalVO> allPage(SearchCriteria cri) throws SQLException;
	public List<PortalVO> totalAllPageex(SearchCriteria cri) throws SQLException;
	
	public Integer countAll(Date date) throws SQLException;
	public Integer showboxCountAll(Date date) throws SQLException;
	
	public GraphVO toDayCount(String type) throws SQLException;
	
	public GraphVO showboxToDayCount(String type) throws SQLException;
	
	public GraphVO yesterdayCount() throws SQLException;
	
	public GraphVO showboxYesterdayCount() throws SQLException;
	
	public TextTypeVO textTypeCount(SearchCriteria cri) throws SQLException;
	public List<TextTypeDateVO> textTypeCount2(SearchCriteria cri) throws SQLException;
	
	public TextTypeVO blogTextTypeCount(SearchCriteria cri) throws SQLException;
	public TextTypeVO cafeTextTypeCount(SearchCriteria cri) throws SQLException;
	
	public List<PortalVO> listNaver(SearchCriteria cri) throws SQLException;
	public Integer getNaverCount(SearchCriteria cri) throws SQLException;
	public List<TextTypeVO> naverTextTypeCountb(SearchCriteria cri) throws SQLException;
	public List<TextTypeVO> naverTextTypeCountc(SearchCriteria cri) throws SQLException;
	public List<TextTypeVO> naverTextTypeCountk(SearchCriteria cri) throws SQLException;
	public List<TextTypeVO> naverTextTypeCountw(SearchCriteria cri) throws SQLException;
	
	public List<PortalVO> listDaum(SearchCriteria cri) throws SQLException;
	public Integer getDaumCount(SearchCriteria cri) throws SQLException;
	public List<TextTypeVO> daumTextTypeCountb(SearchCriteria cri) throws SQLException;
	public List<TextTypeVO> daumTextTypeCountc(SearchCriteria cri) throws SQLException;
	public List<TextTypeVO> daumTextTypeCountk(SearchCriteria cri) throws SQLException;
	public List<TextTypeVO> daumTextTypeCountw(SearchCriteria cri) throws SQLException;
	
	public List<PortalVO> listExtract(SearchCriteria cri) throws SQLException;
	
	public Integer getExtractCount(SearchCriteria cri) throws SQLException;
	
	public List<PortalVO> listSearch(SearchCriteria cri) throws SQLException;
	public Integer getSearchCount(SearchCriteria cri) throws SQLException;

	public List<PortalVO> wlistSearch(SearchCriteria cri) throws SQLException;
	public Integer wgetSearchCount(SearchCriteria cri) throws SQLException;
	public List<PortalVO> periodWlistSearch(SearchCriteria cri) throws SQLException;
	public Integer periodWgetSearchCount(SearchCriteria cri) throws SQLException;
	
	public List<PortalVO> listAll(SearchCriteria cri) throws SQLException;
	public List<PortalVO> listAllEx(SearchCriteria cri) throws SQLException;
	public List<PortalVO> dashListAll(SearchCriteria cri) throws SQLException;
	
	public void updateTextType(PortalVO vo) throws SQLException;
	
	public void updateThumbnail(PortalVO vo) throws SQLException;
	
	public List<NvVO> naverVideosList(SearchCriteria cri) throws SQLException;
	public List<NvVO> naverVideosallList(SearchCriteria cri) throws SQLException;
	public List<NvVO> naverVideosList2(SearchCriteria cri) throws SQLException;
	public List<NvVO> naverVideosallList2(SearchCriteria cri) throws SQLException;
	public Integer naverVideosListTotalCnt(SearchCriteria cri) throws SQLException;
	public Integer naverVideosListTotalCnt2(SearchCriteria cri) throws SQLException;
	public List<NvVO> nvlistSearch(SearchCriteria cri) throws SQLException;
	public List<NvVO> nvlistSearchEx(SearchCriteria cri) throws SQLException;
	public Integer nvlistSearchTotalCnt(SearchCriteria cri) throws SQLException;
	public List<NvVO> nvlistSearchList(SearchCriteria cri) throws SQLException;
	public Integer nvlistSearchListTotalCnt(SearchCriteria cri) throws SQLException;
	public Integer nvlistViewCnt(SearchFv fv) throws SQLException;
	public Integer nvlistReply_cnt(SearchFv fv) throws SQLException;
	public Integer nvlistlike_cnt(SearchFv fv) throws SQLException;
	public List<NvVO> nvlistPlus(SearchCriteria cri) throws SQLException;
	public List<NvVO> nvlistlimt(SearchCriteria cri) throws SQLException;
	public List<NvVO> nvlistMinus2(SearchCriteria cri) throws SQLException;
	public List<NvVO> nvlistMinus(SearchCriteria cri) throws SQLException;
	public List<NvVO> nvlistSearchTime(SearchCriteria cri) throws SQLException;
	public List<NvVO> nvSearchlistSearchTime(SearchCriteria cri) throws SQLException;
	public List<NvVO> nvlistOne(SearchCriteria cri) throws SQLException;
	public List<NvVO> nvlistTwo(SearchFv fv) throws SQLException;
	public List<NvVO> nvlistGraph(SearchFv fv) throws SQLException;
	
	public Integer scoreTotalcnt(SearchCriteria cri) throws SQLException;
	
	public Integer portalTextcnt(SearchCriteria cri) throws SQLException;
	public Integer portalTextcnt2(SearchCriteria cri) throws SQLException;
	
	public Integer graphNaverCount(SearchCriteria cri) throws SQLException;
	public Integer graphDaumCount(SearchCriteria cri) throws SQLException;
	
	public List<ScoreVO> scoreListReport(SearchCriteria cri) throws SQLException;
	public Integer getNvCount(String url) throws SQLException;
	
	
	public List<NvVO> nvlistSearch2(SearchCriteria cri) throws SQLException;
	public List<NvVO> nvlistSearchList2(SearchCriteria cri) throws SQLException;
	public List<NvVO> nvlistMinus3(SearchCriteria cri) throws SQLException;
	public List<NvVO> nvlistSearchTime2(SearchCriteria cri) throws SQLException;
	public Integer nvlistSearchTotalCnt2(SearchCriteria cri) throws SQLException;
	
	public Integer nvlistViewCnt2(SearchFv fv) throws SQLException;
	public Integer nvlistReply_cnt2(SearchFv fv) throws SQLException;
	public Integer nvlistlike_cnt2(SearchFv fv) throws SQLException;
	
	public List<NvVO> nvlistSearchEx2(SearchCriteria cri) throws SQLException;
	public List<NvVO> nvlistPlus2(SearchCriteria cri) throws SQLException;
	public List<NvVO> nvlistMinus4(SearchCriteria cri) throws SQLException;
	public List<NvVO> nvlistlimt2(SearchCriteria cri) throws SQLException;
	
	public List<NvVO> nvSearchlistSearchTime2(SearchCriteria cri) throws SQLException;
	public Integer nvlistSearchListTotalCnt2(SearchCriteria cri) throws SQLException;
	
	public List<NvVO> nvlistGraph2(SearchFv fv) throws SQLException;
	public List<NvVO> nvlistOne2(SearchCriteria cri) throws SQLException;
	public List<NvVO> nvlistTwo2(SearchFv fv) throws SQLException;
	
	public void nvUpdate(NvVO vo) throws SQLException;
	public void nvUpdate2(NvVO vo) throws SQLException;
	
	public List<NvVO> nvCheckList() throws SQLException;
	public List<NvVO> nvCheckList2() throws SQLException;
	
	public List<NavertalkVO> naverTalkList(SearchCriteria cri) throws SQLException;
	public Integer naverTalkcount(SearchCriteria cri) throws SQLException;
}
