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
import org.union.domain.FvVO;
import org.union.domain.GraphVO;
import org.union.domain.MediaVO;
import org.union.domain.NvVO;
import org.union.domain.SNSVO;
import org.union.domain.SearchCriteria;
import org.union.domain.SearchFv;
import org.union.domain.SearchNv;
import org.union.persistence.KeywordDAO;
import org.union.persistence.SNSDAO;

@Service
public class SNSServiceImpl implements SNSService{

	@Autowired
	private SNSDAO snsDAO;
	
	@Autowired
	private KeywordDAO keywordDAO;
	
	@Override
	public void regist(SNSVO vo) throws SQLException {

		snsDAO.create(vo);
	}
	

	@Override
	public SNSVO view(Integer SNS_idx) throws SQLException {

		return snsDAO.read(SNS_idx);
	}
	

	@Override
	public void modify(SNSVO vo) throws SQLException {

		snsDAO.update(vo);
	}
	

	@Override
	public void remove(Integer SNS_idx) throws SQLException {

		snsDAO.delete(SNS_idx);
	}

	@Override
	public List<ExtractVO> listExtract(SearchCriteria cri) throws SQLException {

		try {
			List<SNSVO> snsList = snsDAO.listExtract(cri);
			
			List<ExtractVO> extractList = new ArrayList<ExtractVO>();
			
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
			
			for(int i = 0; i < snsList.size(); i++) {
				ExtractVO vo = new ExtractVO();
				
				SNSVO data = snsList.get(i);
				
				vo.setSns_idx(data.getSns_idx());
				vo.setDomain("sns");
				vo.setDomainType(data.getSns_name());
				vo.setTitle(data.getSns_title());
				vo.setContent(data.getSns_content());
				vo.setKeyword(data.getKeyword());
				vo.setUrl(data.getUrl());
				vo.setCreateDate(date.format(data.getCreateDate()));
				vo.setWriteDate(data.getWriteDate());
				extractList.add(vo);
			}
			
			return extractList;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Override
	public Integer getExtractCount (SearchCriteria cri) throws SQLException {

		return snsDAO.getExtractCount(cri);
	}
	
	
	@Override
	public List<SNSVO> listSearch(SearchCriteria cri) throws SQLException {

		return snsDAO.listSearch(cri);
	}


	@Override
	public Integer getSearchCount(SearchCriteria cri) throws SQLException {

		return snsDAO.getSearchCount(cri);
	}
	
	
	
	@Override
	public List<SNSVO> facebookList(SearchCriteria cri) throws SQLException {

		return snsDAO.facebookList(cri);
	}
	
	
	@Override
	public Integer facebookTotalCount(SearchCriteria  cri) throws SQLException {

		return snsDAO.facebookTotalCount(cri);
	}
	
	
	@Override
	public List<SNSVO> instaList(SearchCriteria cri) throws SQLException {

		return snsDAO.instaList(cri);
	}


	@Override
	public Integer instaTotalCount(SearchCriteria cri) throws SQLException {

		return snsDAO.instaTotalCount(cri);
	}


	@Override
	public List<SNSVO> twitterList(SearchCriteria cri) throws SQLException {

		return snsDAO.twitterList(cri);
	}


	@Override
	public Integer twitterTotalCount(SearchCriteria cri) throws SQLException {

		return snsDAO.twitterTotalCount(cri);
	}
	

	@Override
	public void modifyTextType(SNSVO vo) throws SQLException {

		snsDAO.updateTextType(vo);
	}
	

	@Override
	public void modifyThumbnail(SNSVO vo) throws SQLException {

		snsDAO.updateThumbnail(vo);
	}


	@Override
	public List<SNSVO> getDateCount(SearchCriteria cri) throws SQLException {

		return snsDAO.getDateCount(cri);
	}


	@Override
	public List<SNSVO> listAll(SearchCriteria cri) throws SQLException {

		List<SNSVO> list = snsDAO.listAll(cri);

		for (SNSVO snsvo : list) {
			snsvo.setKeyword_main(keywordDAO.read(snsvo.getKeyword()).getKeyword_main());
		}
		
		return list;
	}


	@Override
	public List<SNSVO> listExcel(SearchCriteria cri) throws SQLException {

		return snsDAO.listExcel(cri);
	}


	@Override
	public Integer listExcelCount(SearchCriteria cri) throws SQLException {

		return snsDAO.listExcelCount(cri);
	}


	@Override
	public GraphVO facebookSum(SearchCriteria cri) throws SQLException {

		return snsDAO.facebookSum(cri);
	}


	@Override
	public GraphVO twitterSum(SearchCriteria cri) throws SQLException {

		return snsDAO.twitterSum(cri);
	}


	@Override
	public GraphVO instagramSum(SearchCriteria cri) throws SQLException {

		return snsDAO.instagramSum(cri);
	}


	@Override
	public GraphVO yesterdayCount(String name) throws SQLException {

		return snsDAO.yesterdayCount(name);
	}

	@Override
	public GraphVO showboxYesterdayCount(String name) throws SQLException {

		return snsDAO.showboxYesterdayCount(name);
	}

	@Override
	public Integer countAll(Date date) throws SQLException {

		return snsDAO.countAll(date);
	}

	@Override
	public Integer showboxCountAll(Date date) throws SQLException {

		return snsDAO.showboxCountAll(date);
	}

	@Override
	public List<SNSVO> allPageList(SearchCriteria cri) throws SQLException {

		return snsDAO.allPageList(cri);
	}


	@Override
	public Integer allPageCount(SearchCriteria cri) throws SQLException {

		return snsDAO.allPageCount(cri);
	}


	@Override
	public List<SNSVO> facebookCnt(SearchCriteria cri) throws SQLException {

		return snsDAO.facebookCnt(cri);
	}


	@Override
	public List<SNSVO> instaCnt(SearchCriteria cri) throws SQLException {

		return snsDAO.instaCnt(cri);
	}


	@Override
	public List<SNSVO> twiCnt(SearchCriteria cri) throws SQLException {

		return snsDAO.twiCnt(cri);
	}


	@Override
	public List<SNSVO> snsTotalCnt(SearchCriteria cri) throws SQLException {

		return snsDAO.snsTotalCnt(cri);
	}


	@Override
	public List<FvVO> facebookCGV(SearchCriteria cri) throws SQLException {

		return snsDAO.facebookCGV(cri);
	}


	@Override
	public List<FvVO> facebookCGVList(SearchCriteria cri) throws SQLException {

		return snsDAO.facebookCGVList(cri);
	}


	@Override
	public Integer facebookCGVListTotalCnt(SearchCriteria cri) throws SQLException {

		return snsDAO.facebookCGVListTotalCnt(cri);
	}


	@Override
	public List<FvVO> fvlistSearch(SearchCriteria cri) throws SQLException {

		return snsDAO.fvlistSearch(cri);
	}


	@Override
	public Integer fvlistSearchTotalCnt(SearchCriteria cri) throws SQLException {

		return snsDAO.fvlistSearchTotalCnt(cri);
	}


	@Override
	public Integer fvlistViewCnt(SearchFv fv) throws SQLException {

		return snsDAO.fvlistViewCnt(fv);
	}


	@Override
	public List<FvVO> fvlistMinus(SearchCriteria cri) throws SQLException {

		return snsDAO.fvlistMinus(cri);
	}


	@Override
	public Integer fvlistReply_cnt(SearchFv fv) throws SQLException {

		return snsDAO.fvlistReply_cnt(fv);
	}


	@Override
	public Integer fvlistlike_cnt(SearchFv fv) throws SQLException {

		return snsDAO.fvlistlike_cnt(fv);
	}


	@Override
	public List<FvVO> fvlistSearchEx(SearchCriteria cri) throws SQLException {

		return snsDAO.fvlistSearchEx(cri);
	}


	@Override
	public List<FvVO> fvlistSearchTime(SearchCriteria cri) throws SQLException {

		return snsDAO.fvlistSearchTime(cri);
	}


	@Override
	public List<FvVO> fvlistSearchList(SearchCriteria cri) throws SQLException {

		return snsDAO.fvlistSearchList(cri);
	}


	@Override
	public Integer fvlistSearchListTotalCnt(SearchCriteria cri) throws SQLException {

		return snsDAO.fvlistSearchListTotalCnt(cri);
	}


	@Override
	public List<FvVO> fvSearchlistSearchTime(SearchCriteria cri) throws SQLException {

		return snsDAO.fvSearchlistSearchTime(cri);
	}

	@Override
	public List<FvVO> facebookCGVallList(SearchCriteria cri) throws SQLException {

		return snsDAO.facebookCGVallList(cri);
	}


	@Override
	public List<FvVO> fvlistOne(SearchCriteria cri) throws SQLException {

		return snsDAO.fvlistOne(cri);
	}

	@Override
	public List<FvVO> fvlistTwo(SearchFv fv) throws SQLException {

		return snsDAO.fvlistTwo(fv);
	}


	@Override
	public List<FvVO> fvlistPlus(SearchCriteria cri) throws SQLException {

		return snsDAO.fvlistPlus(cri);
	}


	@Override
	public List<FvVO> fvlistMinus2(SearchCriteria cri) throws SQLException {

		return snsDAO.fvlistMinus2(cri);
	}


	@Override
	public List<FvVO> fvlistlimt(SearchCriteria cri) throws SQLException {

		return snsDAO.fvlistlimt(cri);
	}


	@Override
	public List<FvVO> fvlistGraph(SearchFv fv) throws SQLException {
		
		return snsDAO.fvlistGraph(fv);
	}


	@Override
	public Integer snsTotalcount(SearchCriteria cri) throws SQLException {

		return snsDAO.snsTotalcount(cri);
	}


	@Override
	public Integer graphfacebookCount(SearchCriteria cri) throws SQLException {

		return snsDAO.graphfacebookCount(cri);
	}


	@Override
	public Integer graphinstaCount(SearchCriteria cri) throws SQLException {

		return snsDAO.graphinstaCount(cri);
	}


	@Override
	public Integer graphtwitterCount(SearchCriteria cri) throws SQLException {

		return snsDAO.graphtwitterCount(cri);
	}


	@Override
	public Integer reportSnsCount(SearchCriteria cri) throws SQLException {

		return snsDAO.reportSnsCount(cri);
	}


	@Override
	public Integer replyGetDateCount(SearchCriteria cri) throws SQLException {

		return snsDAO.replyGetDateCount(cri);
	}


	@Override
	public Integer likeGetDateCount(SearchCriteria cri) throws SQLException {

		return snsDAO.likeGetDateCount(cri);
	}


	@Override
	public Integer shareGetDateCount(SearchCriteria cri) throws SQLException {

		return snsDAO.shareGetDateCount(cri);
	}
	
	@Override
	public Integer snsFacebookTotalCount(SearchCriteria cri) throws SQLException {

		return snsDAO.snsFacebookTotalCount(cri);
	}


	@Override
	public void fvUpdate(FvVO vo) throws SQLException {

		snsDAO.fvUpdate(vo);
	}

	@Override
	public List<FvVO> fvCheckList(String sns_writer) throws SQLException {
		
		List<FvVO> list = snsDAO.fvMonitor(sns_writer);
		for (FvVO fvVO : list) {
			
			FvVO vo = snsDAO.fvCheckList(fvVO.getSns_writer());
			
			if(vo != null) {
				fvVO.setCheckCondition(true);
				
				
			}else {
				fvVO.setCheckCondition(false);
			}
		}
		
		return list;
	}


	@Override
	public List<SNSVO> youtubeList(SearchCriteria cri) throws SQLException {

		return snsDAO.youtubeList(cri);
	}


	@Override
	public Integer youtubeTotalCount(SearchCriteria cri) throws SQLException {

		return snsDAO.youtubeTotalCount(cri);
	}


	@Override
	public Integer YviewGetDateCount(SearchCriteria cri) throws SQLException {

		return snsDAO.YviewGetDateCount(cri);
	}


	@Override
	public Integer YreplyGetDateCount(SearchCriteria cri) throws SQLException {

		return snsDAO.YreplyGetDateCount(cri);
	}


	@Override
	public Integer YlikeGetDateCount(SearchCriteria cri) throws SQLException {

		return snsDAO.YlikeGetDateCount(cri);
	}


	@Override
	public Integer graphyoutubeCount(SearchCriteria cri) throws SQLException {

		return snsDAO.graphyoutubeCount(cri);
	}


	@Override
	public List<SNSVO> youtubeListAll(SearchCriteria cri) throws SQLException {

		List<SNSVO> list = snsDAO.youtubeListAll(cri);

		for (SNSVO snsvo : list) {
			snsvo.setKeyword_main(keywordDAO.read(snsvo.getKeyword()).getKeyword_main());
		}
		
		return list;
	}


	@Override
	public List<SNSVO> periodListSearch(SearchCriteria cri) throws SQLException {

		return snsDAO.periodListSearch(cri);
	}


	@Override
	public Integer periodgetSearchCount(SearchCriteria cri) throws SQLException {

		return snsDAO.periodgetSearchCount(cri);
	}

}
