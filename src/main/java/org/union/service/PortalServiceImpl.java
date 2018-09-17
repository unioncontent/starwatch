package org.union.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.union.domain.CommunityVO;
import org.union.domain.ExtractVO;
import org.union.domain.GraphVO;
import org.union.domain.NavertalkVO;
import org.union.domain.NvVO;
import org.union.domain.PortalVO;
import org.union.domain.ScoreVO;
import org.union.domain.SearchCriteria;
import org.union.domain.SearchFv;
import org.union.domain.TextTypeDateVO;
import org.union.domain.TextTypeVO;
import org.union.persistence.KeywordDAO;
import org.union.persistence.PortalDAO;

@Service
public class PortalServiceImpl implements PortalService {

	@Autowired
	private PortalDAO portalDAO;
	
	@Autowired
	private KeywordDAO keywordDAO;
	
	@Override
	public void regist(PortalVO vo) throws SQLException {
		
		portalDAO.create(vo);

	}

	@Override
	public PortalVO view(Integer portal_idx) throws SQLException {

		return null;
	}

	@Override
	public void modify(PortalVO vo) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Integer portal_idx) throws SQLException {

		portalDAO.delete(portal_idx);
	}

	@Override
	public List<ExtractVO> listExtract(SearchCriteria cri) throws SQLException {

		List<PortalVO> portalList = portalDAO.listExtract(cri);
		
		List<ExtractVO> extractList = new ArrayList<ExtractVO>();
		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			
			for(int i = 0; i < portalList.size(); i++) {
				ExtractVO vo = new ExtractVO();
				
				PortalVO data = portalList.get(i);
				
				vo.setPortal_idx(data.getPortal_idx());
				vo.setDomain("portal");
				vo.setDomainType(data.getPortal_name());
				vo.setTitle(data.getPortal_title());
				vo.setContent(data.getPortal_content());
				vo.setKeyword(data.getKeyword());
				vo.setUrl(data.getUrl());
				vo.setCreateDate(date.format(data.getCreateDate()));
				vo.setWriteDate(data.getWriteDate());
				
				extractList.add(vo);
			}
			
			return extractList;
			
		} catch (Exception e) {
			e.printStackTrace();
			return extractList;
		}
	
	}
	
	@Override
	public Integer getExtractCount(SearchCriteria cri) throws SQLException {

		return portalDAO.getExtractCount(cri);
	}
	

	@Override
	public List<PortalVO> listSearch(SearchCriteria cri) throws SQLException {

		return portalDAO.listSearch(cri);
		
	}
	
	@Override
	public Integer getSearchCount(SearchCriteria cri) throws SQLException {

		return portalDAO.getSearchCount(cri);
	}

	@Override
	public List<PortalVO> wlistSearch(SearchCriteria cri) throws SQLException {

		return portalDAO.wlistSearch(cri);
		
	}
	
	@Override
	public Integer wgetSearchCount(SearchCriteria cri) throws SQLException {

		return portalDAO.wgetSearchCount(cri);
	}

	@Override
	public void modifyType(PortalVO vo) throws SQLException {

		portalDAO.updateTextType(vo);
	}

	@Override
	public void modifyThumbnail(PortalVO vo) throws SQLException {

		portalDAO.updateThumbnail(vo);
	}

	@Override
	public List<PortalVO> listAll(SearchCriteria cri) throws SQLException {

		List<PortalVO> list = portalDAO.listAll(cri);
		
		for (PortalVO portalVO : list) {
			portalVO.setKeyword_main(keywordDAO.read(portalVO.getKeyword()).getKeyword_main());
		}

		return list;
	}

	@Override
	public List<PortalVO> listNaver(SearchCriteria cri) throws SQLException {

		return portalDAO.listNaver(cri);
	}

	@Override
	public Integer getNaverCount(SearchCriteria cri) throws SQLException {

		return portalDAO.getNaverCount(cri);
	}

	@Override
	public List<PortalVO> listDaum(SearchCriteria cri) throws SQLException {

		return portalDAO.listDaum(cri);
	}

	@Override
	public Integer getDaumCount(SearchCriteria cri) throws SQLException {

		return portalDAO.getDaumCount(cri);
	}

	@Override
	public List<TextTypeVO> naverTextTypeCountb(SearchCriteria cri) throws SQLException {

		return portalDAO.naverTextTypeCountb(cri);
	}
	@Override
	public List<TextTypeVO> naverTextTypeCountc(SearchCriteria cri) throws SQLException {
		
		return portalDAO.naverTextTypeCountc(cri);
	}
	@Override
	public List<TextTypeVO> naverTextTypeCountk(SearchCriteria cri) throws SQLException {
		
		return portalDAO.naverTextTypeCountk(cri);
	}
	@Override
	public List<TextTypeVO> naverTextTypeCountw(SearchCriteria cri) throws SQLException {
		
		return portalDAO.naverTextTypeCountw(cri);
	}

	@Override
	public List<TextTypeVO> daumTextTypeCountb(SearchCriteria cri) throws SQLException {

		return portalDAO.daumTextTypeCountb(cri);
	}
	@Override
	public List<TextTypeVO> daumTextTypeCountc(SearchCriteria cri) throws SQLException {
		
		return portalDAO.daumTextTypeCountc(cri);
	}
	@Override
	public List<TextTypeVO> daumTextTypeCountk(SearchCriteria cri) throws SQLException {
		
		return portalDAO.daumTextTypeCountk(cri);
	}
	@Override
	public List<TextTypeVO> daumTextTypeCountw(SearchCriteria cri) throws SQLException {
		
		return portalDAO.daumTextTypeCountw(cri);
	}

	@Override
	public TextTypeVO textTypeCount(SearchCriteria cri) throws SQLException {

		return portalDAO.textTypeCount(cri);
	}
	
	@Override
	public List<TextTypeDateVO> textTypeCount2(SearchCriteria cri) throws SQLException {
		
		return portalDAO.textTypeCount2(cri);
	}

	@Override
	public TextTypeVO blogTextType(SearchCriteria cri) throws SQLException {

		return portalDAO.blogTextTypeCount(cri);
	}

	@Override
	public TextTypeVO cafeTextType(SearchCriteria cri) throws SQLException {

		return portalDAO.cafeTextTypeCount(cri);
	}

	@Override
	public GraphVO toDayCount(String type) throws SQLException {

		return portalDAO.toDayCount(type);
	}
	
	@Override
	public GraphVO showboxToDayCount(String type) throws SQLException {

		return portalDAO.showboxToDayCount(type);
	}

	@Override
	public GraphVO yesterdayCount() throws SQLException {

		return portalDAO.yesterdayCount();
	}
	
	@Override
	public GraphVO showboxYesterdayCount() throws SQLException {

		return portalDAO.showboxYesterdayCount();
	}

	@Override
	public Integer countAll(Date date) throws SQLException {

		return portalDAO.countAll(date);
	}
	
	@Override
	public Integer showboxCountAll(Date date) throws SQLException {

		return portalDAO.showboxCountAll(date);
	}

	@Override
	public List<PortalVO> allPageList(SearchCriteria cri) throws SQLException {

		return portalDAO.allPageList(cri);
	}
	
	@Override
	public List<PortalVO> TotalAllPageList(SearchCriteria cri) throws SQLException {
		
		return portalDAO.TotalAllPageList(cri);
	}
	
	@Override
	public Integer allPageCount(SearchCriteria cri) throws SQLException {

		return portalDAO.allPageCount(cri);
	}
	
	@Override
	public Integer TotalAllPageCount(SearchCriteria cri) throws SQLException {

		return portalDAO.TotalAllPageCount(cri);
	}

	@Override
	public Integer getTypeOfMovieCount(SearchCriteria cri) throws SQLException {

		return portalDAO.getTypeOfMovieCount(cri);
	}

	@Override
	public Integer getTypeOfActorCount(SearchCriteria cri) throws SQLException {

		return portalDAO.getTypeOfActorCount(cri);
	}

	@Override
	public Integer getScoreCount(SearchCriteria cri) throws SQLException {

		return portalDAO.getScoreCount(cri);
	}

	@Override
	public List<PortalVO> getScoreList(SearchCriteria cri) throws SQLException {

		return portalDAO.getScoreList(cri);
	}
	
	@Override
	public List<PortalVO> getScoreExcelList(SearchCriteria cri) throws SQLException {

		return portalDAO.getScoreExcelList(cri);
	}

	@Override
	public TextTypeVO getScoreTextType(SearchCriteria cri) throws SQLException {

		return portalDAO.getScoreTextType(cri);
	}

	@Override
	public Integer getOnlyScore(SearchCriteria cri) throws SQLException {

		List<Integer> list = portalDAO.getOnlyScore(cri);
		
		Integer totalCount = 0;
		
		if(list.size() != 0) {
			for (Integer integer : list) {
				totalCount += integer;
			}
			
			totalCount = totalCount/list.size();
			
		}

		return totalCount;
	}

	@Override
	public List<PortalVO> wPageSearch(SearchCriteria cri) throws SQLException {

		List<PortalVO> list = portalDAO.wPageSearch(cri);
		
		for (PortalVO portalVO : list) {
			portalVO.setKeyword_main(keywordDAO.read(portalVO.getKeyword()).getKeyword_main());
		}

		return list;
	}

	@Override
	public List<PortalVO> allPage(SearchCriteria cri) throws SQLException {

		List<PortalVO> list = portalDAO.allPage(cri);
		
		for (PortalVO portalVO : list) {
			portalVO.setKeyword_main(keywordDAO.read(portalVO.getKeyword()).getKeyword_main());
		}
		
		return list;
	}

	@Override
	public List<NvVO> naverVideosList(SearchCriteria cri) throws SQLException {

		return portalDAO.naverVideosList(cri);
	}

	@Override
	public Integer naverVideosListTotalCnt(SearchCriteria cri) throws SQLException {

		return portalDAO.naverVideosListTotalCnt(cri);
	}

	@Override
	public List<NvVO> nvlistSearch(SearchCriteria cri) throws SQLException {

		return portalDAO.nvlistSearch(cri);
	}

	@Override
	public Integer nvlistSearchTotalCnt(SearchCriteria cri) throws SQLException {

		return portalDAO.nvlistSearchTotalCnt(cri);
	}

	@Override
	public Integer nvlistViewCnt(SearchFv fv) throws SQLException {

		return portalDAO.nvlistViewCnt(fv);
	}

	@Override
	public List<NvVO> nvlistMinus(SearchCriteria cri) throws SQLException {

		return portalDAO.nvlistMinus(cri);
	}

	@Override
	public Integer nvlistReply_cnt(SearchFv fv) throws SQLException {

		return portalDAO.nvlistReply_cnt(fv);
	}

	@Override
	public Integer nvlistlike_cnt(SearchFv fv) throws SQLException {

		return portalDAO.nvlistlike_cnt(fv);
	}

	@Override
	public List<NvVO> nvlistSearchEx(SearchCriteria cri) throws SQLException {

		return portalDAO.nvlistSearchEx(cri);
	}

	@Override
	public List<NvVO> nvlistSearchTime(SearchCriteria cri) throws SQLException {

		return portalDAO.nvlistSearchTime(cri);
	}

	@Override
	public List<NvVO> nvlistSearchList(SearchCriteria cri) throws SQLException {

		return portalDAO.nvlistSearchList(cri);
	}

	@Override
	public Integer nvlistSearchListTotalCnt(SearchCriteria cri) throws SQLException {

		return portalDAO.nvlistSearchListTotalCnt(cri);
	}

	@Override
	public List<NvVO> nvSearchlistSearchTime(SearchCriteria cri) throws SQLException {

		return portalDAO.nvSearchlistSearchTime(cri);
	}

	@Override
	public List<NvVO> nvlistPlus(SearchCriteria cri) throws SQLException {

		return portalDAO.nvlistPlus(cri);
	}

	@Override
	public List<NvVO> nvlistlimt(SearchCriteria cri) throws SQLException {

		return portalDAO.nvlistlimt(cri);
	}

	@Override
	public List<NvVO> nvlistMinus2(SearchCriteria cri) throws SQLException {

		return portalDAO.nvlistMinus2(cri);
	}

	@Override
	public List<NvVO> nvlistOne(SearchCriteria cri) throws SQLException {

		return portalDAO.nvlistOne(cri);
	}

	@Override
	public List<NvVO> nvlistTwo(SearchFv fv) throws SQLException {

		return portalDAO.nvlistTwo(fv);
	}

	@Override
	public List<NvVO> nvlistGraph(SearchFv fv) throws SQLException {

		return portalDAO.nvlistGraph(fv);
	}

	@Override
	public List<NvVO> naverVideosallList(SearchCriteria cri) throws SQLException {

		return portalDAO.naverVideosallList(cri);
	}

	@Override
	public Integer scoreTotalcnt(SearchCriteria cri) throws SQLException {

		return portalDAO.scoreTotalcnt(cri);
	}

	@Override
	public Integer portalTextcnt(SearchCriteria cri) throws SQLException {

		return portalDAO.portalTextcnt(cri);
	}

	@Override
	public Integer portalTextcnt2(SearchCriteria cri) throws SQLException {

		return portalDAO.portalTextcnt2(cri);
	}

	@Override
	public Integer graphNaverCount(SearchCriteria cri) throws SQLException {

		return portalDAO.graphNaverCount(cri);
	}

	@Override
	public Integer graphDaumCount(SearchCriteria cri) throws SQLException {

		return portalDAO.graphDaumCount(cri);
	}

	@Override
	public void scoreCheckList(Integer portal_idx) throws SQLException {

		portalDAO.scoreCheckList(portal_idx);
		
	}

	@Override
	public void scoreUpdate(Integer portal_idx) throws SQLException {

		portalDAO.scoreUpdate(portal_idx);
		
	}

	@Override
	public List<ScoreVO> scoreListReport(SearchCriteria cri) throws SQLException {

		return portalDAO.scoreListReport(cri);
	}

	@Override
	public void scoreCheckDelete(Integer portal_idx) throws SQLException {

		portalDAO.scoreCheckDelete(portal_idx);
	}

	@Override
	public void scoreUpdate2(Integer portal_idx) throws SQLException {

		portalDAO.scoreUpdate2(portal_idx);
		
	}

	@Override
	public List<PortalVO> totalAllPageex(SearchCriteria cri) throws SQLException {

		return portalDAO.totalAllPageex(cri);
	}

	@Override
	public Integer getNvCount(String url) throws SQLException {

		return portalDAO.getNvCount(url);
	}

	@Override
	public List<PortalVO> dashListAll(SearchCriteria cri) throws SQLException {

		return portalDAO.dashListAll(cri);
	}

	@Override
	public List<PortalVO> periodWlistSearch(SearchCriteria cri) throws SQLException {

		return portalDAO.periodWlistSearch(cri);
	}

	@Override
	public Integer periodWgetSearchCount(SearchCriteria cri) throws SQLException {

		return portalDAO.periodWgetSearchCount(cri);
	}

	@Override
	public List<PortalVO> listAllEx(SearchCriteria cri) throws SQLException {

		return portalDAO.listAllEx(cri);
	}

	@Override
	public List<NvVO> naverVideosList2(SearchCriteria cri) throws SQLException {

		return portalDAO.naverVideosList2(cri);
	}

	@Override
	public List<NvVO> naverVideosallList2(SearchCriteria cri) throws SQLException {

		return portalDAO.naverVideosallList2(cri);
	}

	@Override
	public Integer naverVideosListTotalCnt2(SearchCriteria cri) throws SQLException {

		return portalDAO.naverVideosListTotalCnt2(cri);
	}

	@Override
	public List<NvVO> nvlistSearch2(SearchCriteria cri) throws SQLException {

		return portalDAO.nvlistSearch2(cri);
	}

	@Override
	public List<NvVO> nvlistSearchList2(SearchCriteria cri) throws SQLException {

		return portalDAO.nvlistSearchList2(cri);
	}

	@Override
	public List<NvVO> nvlistMinus3(SearchCriteria cri) throws SQLException {

		return portalDAO.nvlistMinus3(cri);
	}

	@Override
	public List<NvVO> nvlistSearchTime2(SearchCriteria cri) throws SQLException {

		return portalDAO.nvlistSearchTime2(cri);
	}

	@Override
	public Integer nvlistSearchTotalCnt2(SearchCriteria cri) throws SQLException {

		return portalDAO.nvlistSearchTotalCnt2(cri);
	}

	@Override
	public Integer nvlistViewCnt2(SearchFv fv) throws SQLException {

		return portalDAO.nvlistViewCnt2(fv);
	}

	@Override
	public Integer nvlistReply_cnt2(SearchFv fv) throws SQLException {

		return portalDAO.nvlistReply_cnt2(fv);
	}

	@Override
	public Integer nvlistlike_cnt2(SearchFv fv) throws SQLException {

		return portalDAO.nvlistlike_cnt2(fv);
	}

	@Override
	public List<NvVO> nvlistSearchEx2(SearchCriteria cri) throws SQLException {

		return portalDAO.nvlistSearchEx2(cri);
	}

	@Override
	public List<NvVO> nvlistPlus2(SearchCriteria cri) throws SQLException {

		return portalDAO.nvlistPlus2(cri);
	}

	@Override
	public List<NvVO> nvlistMinus4(SearchCriteria cri) throws SQLException {

		return portalDAO.nvlistMinus4(cri);
	}

	@Override
	public List<NvVO> nvlistlimt2(SearchCriteria cri) throws SQLException {

		return portalDAO.nvlistlimt2(cri);
	}

	@Override
	public List<NvVO> nvSearchlistSearchTime2(SearchCriteria cri) throws SQLException {

		return portalDAO.nvSearchlistSearchTime2(cri);
	}

	@Override
	public Integer nvlistSearchListTotalCnt2(SearchCriteria cri) throws SQLException {

		return portalDAO.nvlistSearchListTotalCnt2(cri);
	}

	@Override
	public List<NvVO> nvlistGraph2(SearchFv fv) throws SQLException {

		return portalDAO.nvlistGraph2(fv);
	}

	@Override
	public List<NvVO> nvlistOne2(SearchCriteria cri) throws SQLException {

		return portalDAO.nvlistOne2(cri);
	}

	@Override
	public List<NvVO> nvlistTwo2(SearchFv fv) throws SQLException {

		return portalDAO.nvlistTwo2(fv);
	}

	@Override
	public void nvUpdate(NvVO vo) throws SQLException {

		portalDAO.nvUpdate(vo);
	}

	@Override
	public void nvUpdate2(NvVO vo) throws SQLException {

		portalDAO.nvUpdate2(vo);
	}

	@Override
	public List<NvVO> nvCheckList() throws SQLException {

		List<NvVO> list = portalDAO.nvCheckList();
		for (NvVO vo : list) {
			
			if(vo != null) {
				vo.setCheckCondition(true);
				
				
			}else {
				vo.setCheckCondition(false);
			}
		}
		
		return list;
	}

	@Override
	public List<NvVO> nvCheckList2() throws SQLException {

		List<NvVO> list = portalDAO.nvCheckList2();
		for (NvVO vo : list) {
			
			if(vo != null) {
				vo.setCheckCondition(true);
				
				
			}else {
				vo.setCheckCondition(false);
			}
		}
		
		return list;
	}

	@Override
	public List<NavertalkVO> naverTalkList(SearchCriteria cri) throws SQLException {

		return portalDAO.naverTalkList(cri);
	}

	@Override
	public Integer naverTalkcount(SearchCriteria cri) throws SQLException {

		return portalDAO.naverTalkcount(cri);
	}
}
