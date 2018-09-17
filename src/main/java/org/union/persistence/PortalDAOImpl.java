package org.union.persistence;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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

@Repository
public class PortalDAOImpl implements PortalDAO {

	@Autowired
	@Resource(name="oneSqlSession")
	private SqlSession session1;
	
	@Autowired
	@Resource(name="twoSqlSession")
	private SqlSession session2;
	
	private static final String namespace = "org.union.mappers.PortalMapper.";
	
	
	@Override
	public void create(PortalVO vo) throws SQLException {
		try {
			session1.insert(namespace + "create", vo);
			session2.insert(namespace + "create", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public PortalVO read(Integer portal_idx) throws SQLException {
		
		return session1.selectOne(namespace + "read", portal_idx);
			
	}

	
	@Override
	public void update(PortalVO vo) throws SQLException {
		try {
			session1.update(namespace + "update", vo);
			session2.update(namespace + "update", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void delete(Integer portal_idx) throws SQLException {
		try {
			session1.delete(namespace + "delete", portal_idx);
			session2.delete(namespace + "delete", portal_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public List<PortalVO> listExtract(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "listExtract", cri);
		
	}
	
	
	@Override
	public Integer getExtractCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getExtractCount", cri);
	}
	

	@Override
	public List<PortalVO> listSearch(SearchCriteria vo) throws SQLException {
		
		return session1.selectList(namespace + "listSearch", vo);
	}
	
	@Override
	public Integer getSearchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getSearchCount", cri);
	}

	@Override
	public List<PortalVO> wlistSearch(SearchCriteria vo) throws SQLException {
		
		return session1.selectList(namespace + "wlistSearch", vo);
	}
	
	@Override
	public Integer wgetSearchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "wgetSearchCount", cri);
	}
	
	@Override
	public List<PortalVO> listAll(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "listAll", cri);
	}

	@Override
	public void updateTextType(PortalVO vo) throws SQLException {
		try {
			session1.update(namespace + "updateTextType", vo);
			session2.update(namespace + "updateTextType", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void updateThumbnail(PortalVO vo) throws SQLException {
		try {
			session1.update(namespace + "updateThumbnail", vo);
			session2.update(namespace + "updateThumbnail", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public List<PortalVO> listNaver(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "listNaver", cri);
	}


	@Override
	public Integer getNaverCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getNaverCount", cri);
	}


	@Override
	public List<PortalVO> listDaum(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "listDaum", cri);
	}


	@Override
	public Integer getDaumCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getDaumCount", cri);
	}


	@Override
	public List<TextTypeVO> naverTextTypeCountb(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "naverTextTypeCountb", cri);
	}
	@Override
	public List<TextTypeVO> naverTextTypeCountc(SearchCriteria cri) throws SQLException {
		
		return session1.selectList(namespace + "naverTextTypeCountc", cri);
	}
	@Override
	public List<TextTypeVO> naverTextTypeCountk(SearchCriteria cri) throws SQLException {
		
		return session1.selectList(namespace + "naverTextTypeCountk", cri);
	}
	@Override
	public List<TextTypeVO> naverTextTypeCountw(SearchCriteria cri) throws SQLException {
		
		return session1.selectList(namespace + "naverTextTypeCountw", cri);
	}


	@Override
	public List<TextTypeVO> daumTextTypeCountb(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "daumTextTypeCountb", cri);
	}
	@Override
	public List<TextTypeVO> daumTextTypeCountc(SearchCriteria cri) throws SQLException {
		
		return session1.selectList(namespace + "daumTextTypeCountc", cri);
	}
	@Override
	public List<TextTypeVO> daumTextTypeCountk(SearchCriteria cri) throws SQLException {
		
		return session1.selectList(namespace + "daumTextTypeCountk", cri);
	}
	@Override
	public List<TextTypeVO> daumTextTypeCountw(SearchCriteria cri) throws SQLException {
		
		return session1.selectList(namespace + "daumTextTypeCountw", cri);
	}


	@Override
	public TextTypeVO textTypeCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "textTypeCount", cri);
	}
	
	@Override
	public List<TextTypeDateVO> textTypeCount2(SearchCriteria cri) throws SQLException {
		
		return session1.selectList(namespace + "textTypeCount2", cri);
	}

	@Override
	public TextTypeVO blogTextTypeCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "blogTextTypeCount", cri);
	}


	@Override
	public TextTypeVO cafeTextTypeCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "cafeTextTypeCount", cri);
	}


	@Override
	public GraphVO toDayCount(String type) throws SQLException {

		return session1.selectOne(namespace + "toDayCount", type);
	}
	
	@Override
	public GraphVO showboxToDayCount(String type) throws SQLException {
		
		return session1.selectOne(namespace + "showboxToDayCount", type);
	}

	@Override
	public GraphVO yesterdayCount() throws SQLException {

		return session1.selectOne(namespace + "yesterdayCount");
	}
	
	@Override
	public GraphVO showboxYesterdayCount() throws SQLException {

		return session1.selectOne(namespace + "showboxYesterdayCount");
	}

	@Override
	public Integer countAll(Date date) throws SQLException {

		return session1.selectOne(namespace + "countAll", date);
	}

	@Override
	public Integer showboxCountAll(Date date) throws SQLException {

		return session1.selectOne(namespace + "showboxCountAll", date);
	}

	@Override
	public List<PortalVO> allPageList(SearchCriteria cri) throws SQLException {
	
		return session1.selectList(namespace + "allPageList", cri);
	}

	@Override
	public List<PortalVO> TotalAllPageList(SearchCriteria cri) throws SQLException {
		
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
	public Integer getTypeOfMovieCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getTypeOfMovieCount", cri);
	}


	@Override
	public Integer getTypeOfActorCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getTypeOfActorCount", cri);
	}


	@Override
	public Integer getScoreCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getScoreCount", cri);
	}


	@Override
	public List<PortalVO> getScoreList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "getScoreList", cri);
	}
	
	@Override
	public List<PortalVO> getScoreExcelList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "getScoreExcelList", cri);
	}


	@Override
	public TextTypeVO getScoreTextType(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getScoreTextType", cri);
	}

	@Override
	public List<Integer> getOnlyScore(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "getOnlyScore", cri);
	}


	@Override
	public List<PortalVO> wPageSearch(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "wPageSearch", cri);
	}


	@Override
	public List<PortalVO> allPage(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "allPage", cri);
	}


	@Override
	public List<NvVO> naverVideosList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "naverVideosList", cri);
	}


	@Override
	public Integer naverVideosListTotalCnt(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "naverVideosListTotalCnt", cri);
	}


	@Override
	public List<NvVO> nvlistSearch(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "nvlistSearch", cri);
	}


	@Override
	public Integer nvlistSearchTotalCnt(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "nvlistSearchTotalCnt", cri);
	}


	@Override
	public Integer nvlistViewCnt(SearchFv fv) throws SQLException {

		return session1.selectOne(namespace + "nvlistViewCnt", fv);
	}


	@Override
	public List<NvVO> nvlistMinus(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "nvlistMinus", cri);
	}


	@Override
	public Integer nvlistReply_cnt(SearchFv fv) throws SQLException {

		return session1.selectOne(namespace + "nvlistReply_cnt", fv);
	}


	@Override
	public Integer nvlistlike_cnt(SearchFv fv) throws SQLException {

		return session1.selectOne(namespace + "nvlistlike_cnt", fv);
	}


	@Override
	public List<NvVO> nvlistSearchEx(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "nvlistSearchEx", cri);
	}


	@Override
	public List<NvVO> nvlistSearchTime(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "nvlistSearchTime", cri);
	}


	@Override
	public List<NvVO> nvlistSearchList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "nvlistSearchList", cri);
	}


	@Override
	public Integer nvlistSearchListTotalCnt(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "nvlistSearchListTotalCnt", cri);
	}


	@Override
	public List<NvVO> nvSearchlistSearchTime(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "nvSearchlistSearchTime", cri);
	}


	@Override
	public List<NvVO> nvlistPlus(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "nvlistPlus", cri);
	}


	@Override
	public List<NvVO> nvlistlimt(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "nvlistlimt", cri);
	}


	@Override
	public List<NvVO> nvlistMinus2(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "nvlistMinus2", cri);
	}


	@Override
	public List<NvVO> nvlistOne(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "nvlistOne", cri);
	}


	@Override
	public List<NvVO> nvlistTwo(SearchFv fv) throws SQLException {

		return session1.selectList(namespace + "nvlistTwo", fv);
	}


	@Override
	public List<NvVO> nvlistGraph(SearchFv fv) throws SQLException {

		return session1.selectList(namespace + "nvlistGraph", fv);
	}


	@Override
	public List<NvVO> naverVideosallList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "naverVideosallList", cri);
	}


	@Override
	public Integer scoreTotalcnt(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "scoreTotalcnt", cri);
	}


	@Override
	public Integer portalTextcnt(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "portalTextcnt", cri);
	}


	@Override
	public Integer portalTextcnt2(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "portalTextcnt2", cri);
	}


	@Override
	public Integer graphNaverCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "graphNaverCount", cri);
	}


	@Override
	public Integer graphDaumCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "graphDaumCount", cri);
	}


	@Override
	public void scoreCheckList(Integer portal_idx) throws SQLException {
		try {
			session1.insert(namespace + "scoreCheckList", portal_idx);
			session2.insert(namespace + "scoreCheckList", portal_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public void scoreUpdate(Integer portal_idx) throws SQLException {
		try {
			session1.insert(namespace + "scoreUpdate", portal_idx);
			session2.insert(namespace + "scoreUpdate", portal_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public List<ScoreVO> scoreListReport(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "scoreListReport", cri);
	}


	@Override
	public void scoreCheckDelete(Integer portal_idx) throws SQLException {
		try {
			session1.delete(namespace + "scoreCheckDelete", portal_idx);
			session2.delete(namespace + "scoreCheckDelete", portal_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void scoreUpdate2(Integer portal_idx) throws SQLException {
		try {
			session1.insert(namespace + "scoreUpdate2", portal_idx);
			session2.insert(namespace + "scoreUpdate2", portal_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public List<PortalVO> totalAllPageex(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "totalAllPageex", cri);
	}


	@Override
	public Integer getNvCount(String url) throws SQLException {

		return session1.selectOne(namespace + "getNvCount", url);
	}


	@Override
	public List<PortalVO> dashListAll(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "dashListAll", cri);
	}


	@Override
	public List<PortalVO> periodWlistSearch(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "periodWlistSearch", cri);
	}


	@Override
	public Integer periodWgetSearchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "periodWgetSearchCount", cri);
	}


	@Override
	public List<PortalVO> listAllEx(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "listAllEx", cri);
	}


	@Override
	public List<NvVO> naverVideosList2(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "naverVideosList2", cri);
	}


	@Override
	public List<NvVO> naverVideosallList2(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "naverVideosallList2", cri);
	}


	@Override
	public Integer naverVideosListTotalCnt2(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "naverVideosListTotalCnt2", cri);
	}


	@Override
	public List<NvVO> nvlistSearch2(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "nvlistSearch2", cri);
	}


	@Override
	public List<NvVO> nvlistSearchList2(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "nvlistSearchList2", cri);
	}


	@Override
	public List<NvVO> nvlistMinus3(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "nvlistMinus3", cri);
	}


	@Override
	public List<NvVO> nvlistSearchTime2(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "nvlistSearchTime2", cri);
	}


	@Override
	public Integer nvlistSearchTotalCnt2(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "nvlistSearchTotalCnt2", cri);
	}


	@Override
	public Integer nvlistViewCnt2(SearchFv fv) throws SQLException {

		return session1.selectOne(namespace + "nvlistViewCnt2", fv);
	}


	@Override
	public Integer nvlistReply_cnt2(SearchFv fv) throws SQLException {

		return session1.selectOne(namespace + "nvlistReply_cnt2", fv);
	}


	@Override
	public Integer nvlistlike_cnt2(SearchFv fv) throws SQLException {

		return session1.selectOne(namespace + "nvlistlike_cnt2", fv);
	}


	@Override
	public List<NvVO> nvlistSearchEx2(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "nvlistSearchEx2", cri);
	}


	@Override
	public List<NvVO> nvlistPlus2(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "nvlistPlus2", cri);
	}


	@Override
	public List<NvVO> nvlistMinus4(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "nvlistMinus4", cri);
	}


	@Override
	public List<NvVO> nvlistlimt2(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "nvlistlimt2", cri);
	}


	@Override
	public List<NvVO> nvSearchlistSearchTime2(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "nvSearchlistSearchTime2", cri);
	}


	@Override
	public Integer nvlistSearchListTotalCnt2(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "nvlistSearchListTotalCnt2", cri);
	}


	@Override
	public List<NvVO> nvlistGraph2(SearchFv fv) throws SQLException {

		return session1.selectList(namespace + "nvlistGraph2", fv);
	}


	@Override
	public List<NvVO> nvlistOne2(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "nvlistOne2", cri);
	}


	@Override
	public List<NvVO> nvlistTwo2(SearchFv fv) throws SQLException {

		return session1.selectList(namespace + "nvlistTwo2", fv);
	}


	@Override
	public void nvUpdate(NvVO vo) throws SQLException {
		try {
			session1.update(namespace + "nvUpdate", vo);
			session2.update(namespace + "nvUpdate", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void nvUpdate2(NvVO vo) throws SQLException {
		try {
			session1.update(namespace + "nvUpdate2", vo);
			session2.update(namespace + "nvUpdate2", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public List<NvVO> nvCheckList() throws SQLException {

		return session1.selectList(namespace + "nvCheckList");
	}


	@Override
	public List<NvVO> nvCheckList2() throws SQLException {

		return session1.selectList(namespace + "nvCheckList2");
	}


	@Override
	public List<NavertalkVO> naverTalkList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "naverTalkList", cri);
	}


	@Override
	public Integer naverTalkcount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "naverTalkcount", cri);
	}
}
