package org.union.persistence;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.union.domain.FvVO;
import org.union.domain.GraphVO;
import org.union.domain.NvVO;
import org.union.domain.ReplyVO;
import org.union.domain.SNSVO;
import org.union.domain.SearchCriteria;
import org.union.domain.SearchFv;
import org.union.domain.SearchNv;

@Repository
public class SNSDAOImpl implements SNSDAO {

	@Autowired
	@Resource(name="oneSqlSession")
	private SqlSession session1;
	
	@Autowired
	@Resource(name="twoSqlSession")
	private SqlSession session2;
	
	private static final String namespace = "org.union.mappers.SNSMapper.";
	
	
	@Override
	public void create(SNSVO vo) throws SQLException {
		try {
			session1.insert(namespace + "create", vo);
			session2.insert(namespace + "create", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public SNSVO read(Integer SNS_idx) throws SQLException {
		
		return session1.selectOne(namespace + "read", SNS_idx);
	}

	
	@Override
	public void update(SNSVO vo) throws SQLException {
		try {
			session1.update(namespace + "update", vo);
			session2.update(namespace + "update", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void delete(Integer SNS_idx) throws SQLException {
		try {
			session1.delete(namespace + "delete", SNS_idx);
			session2.delete(namespace + "delete", SNS_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<SNSVO> listExtract(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "listExtract", cri);
	}


	@Override
	public Integer getExtractCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getExtractCount", cri);
	}
	
	
	@Override
	public List<SNSVO> listSearch(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "listSearch", cri);
	}


	@Override
	public Integer getSearchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getSearchCount", cri);
	}

	@Override
	public List<SNSVO> listAll(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "listAll", cri);
	}
	
	@Override
	public List<SNSVO> facebookList(SearchCriteria cri) throws SQLException {
		
		return session1.selectList(namespace + "facebookList", cri);
	}

	
	@Override
	public Integer facebookTotalCount(SearchCriteria  cri) throws SQLException {

		return session1.selectOne(namespace + "facebookTotalCount", cri);
	}
	
	
	@Override
	public List<SNSVO> instaList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "instaList", cri);
	}


	@Override
	public Integer instaTotalCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "instaTotalCount", cri);
	}


	@Override
	public List<SNSVO> twitterList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "twitterList", cri);
	}


	@Override
	public Integer twitterTotalCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "twitterTotalCount", cri);
	}


	@Override
	public void updateTextType(SNSVO vo) throws SQLException {
		try {
			session1.update(namespace + "updateTextType", vo);
			session2.update(namespace + "updateTextType", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void updateThumbnail(SNSVO vo) throws SQLException {
		try {
			session1.update(namespace + "updateThumbnail", vo);
			session2.update(namespace + "updateThumbnail", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public List<SNSVO> getDateCount(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "getDateCount", cri);
	}


	@Override
	public List<SNSVO> listExcel(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "listExcel", cri);
	}


	@Override
	public Integer listExcelCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "listExcelCount", cri);
	}


	@Override
	public GraphVO facebookSum(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "facebookSum", cri);
	}


	@Override
	public GraphVO twitterSum(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "twitterSum", cri);
	}


	@Override
	public GraphVO instagramSum(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "instagramSum", cri);
	}


	@Override
	public GraphVO yesterdayCount(String name) throws SQLException {

		return session1.selectOne(namespace + "yesterdayCount", name);
	}

	@Override
	public GraphVO showboxYesterdayCount(String name) throws SQLException {

		return session1.selectOne(namespace + "showboxYesterdayCount", name);
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
	public List<SNSVO> allPageList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "allPageList", cri);
	}


	@Override
	public Integer allPageCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "allPageCount", cri);
	}


	@Override
	public List<SNSVO> facebookCnt(SearchCriteria cri) throws SQLException {
		return session1.selectList(namespace + "facebookCnt", cri);
	}


	@Override
	public List<SNSVO> instaCnt(SearchCriteria cri) throws SQLException {
		return session1.selectList(namespace + "instaCnt", cri);
	}


	@Override
	public List<SNSVO> twiCnt(SearchCriteria cri) throws SQLException {
		return session1.selectList(namespace + "twiCnt", cri);
	}


	@Override
	public List<SNSVO> snsTotalCnt(SearchCriteria cri) throws SQLException {
		return session1.selectList(namespace + "snsTotalCnt", cri);
	}


	@Override
	public List<FvVO> facebookCGV(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "facebookCGV", cri);
	}


	@Override
	public List<FvVO> facebookCGVList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "facebookCGVList", cri);
	}


	@Override
	public Integer facebookCGVListTotalCnt(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "facebookCGVListTotalCnt", cri);
	}


	@Override
	public List<FvVO> fvlistSearch(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "fvlistSearch", cri);
	}


	@Override
	public Integer fvlistSearchTotalCnt(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "fvlistSearchTotalCnt", cri);
	}


	@Override
	public Integer fvlistViewCnt(SearchFv fv) throws SQLException {
		
		return session1.selectOne(namespace + "fvlistViewCnt", fv);
	}


	@Override
	public List<FvVO> fvlistMinus(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "fvlistMinus", cri);
	}


	@Override
	public Integer fvlistReply_cnt(SearchFv fv) throws SQLException {
		
		return session1.selectOne(namespace + "fvlistReply_cnt", fv);
	}


	@Override
	public Integer fvlistlike_cnt(SearchFv fv) throws SQLException {
		
		return session1.selectOne(namespace + "fvlistlike_cnt", fv);
	}


	@Override
	public List<FvVO> fvlistSearchEx(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "fvlistSearchEx", cri);
	}


	@Override
	public List<FvVO> fvlistSearchTime(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "fvlistSearchTime", cri);
	}


	@Override
	public List<FvVO> fvlistSearchList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "fvlistSearchList", cri);
	}


	@Override
	public Integer fvlistSearchListTotalCnt(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "fvlistSearchListTotalCnt", cri);
	}


	@Override
	public List<FvVO> fvSearchlistSearchTime(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "fvSearchlistSearchTime", cri);
	}

	@Override
	public List<FvVO> facebookCGVallList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "facebookCGVallList", cri);
	}


	@Override
	public List<FvVO> fvlistOne(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "fvlistOne", cri);
	}


	@Override
	public List<FvVO> fvlistTwo(SearchFv fv) throws SQLException {

		return session1.selectList(namespace + "fvlistOne", fv);
	}


	@Override
	public List<FvVO> fvlistPlus(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "fvlistPlus", cri);
	}


	@Override
	public List<FvVO> fvlistMinus2(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "fvlistMinus2", cri);
	}


	@Override
	public List<FvVO> fvlistlimt(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "fvlistlimt", cri);
	}


	@Override
	public List<FvVO> fvlistGraph(SearchFv fv) throws SQLException {

		return session1.selectList(namespace + "fvlistGraph", fv);
	}


	@Override
	public Integer snsTotalcount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "snsTotalcount", cri);
	}


	@Override
	public Integer graphfacebookCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "graphfacebookCount", cri);
	}


	@Override
	public Integer graphinstaCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "graphinstaCount", cri);
	}


	@Override
	public Integer graphtwitterCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "graphtwitterCount", cri);
	}


	@Override
	public Integer reportSnsCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "reportSnsCount", cri);
	}


	@Override
	public Integer replyGetDateCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "replyGetDateCount", cri);
	}


	@Override
	public Integer likeGetDateCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "likeGetDateCount", cri);
	}


	@Override
	public Integer shareGetDateCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "shareGetDateCount", cri);
	}
	
	@Override
	public Integer snsFacebookTotalCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "snsFacebookTotalCount", cri);
	}


	@Override
	public void fvUpdate(FvVO vo) throws SQLException {
		try {
			session1.update(namespace + "fvUpdate", vo);
			session2.update(namespace + "fvUpdate", vo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public FvVO fvCheckList(String sns_writer) throws SQLException {

		return session1.selectOne(namespace + "fvCheckList", sns_writer);
	}


	@Override
	public List<FvVO> fvMonitor(String sns_writer) throws SQLException {

		return session1.selectList(namespace + "fvMonitor", sns_writer);
	}


	@Override
	public List<SNSVO> youtubeList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "youtubeList", cri);
	}

	@Override
	public Integer youtubeTotalCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "youtubeTotalCount", cri);
	}


	@Override
	public Integer YviewGetDateCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "YviewGetDateCount", cri);
	}


	@Override
	public Integer YreplyGetDateCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "YreplyGetDateCount", cri);
	}


	@Override
	public Integer YlikeGetDateCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "YlikeGetDateCount", cri);
	}


	@Override
	public Integer graphyoutubeCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "graphyoutubeCount", cri);
	}


	@Override
	public List<SNSVO> youtubeListAll(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "youtubeListAll", cri);
	}


	@Override
	public List<SNSVO> periodListSearch(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "periodListSearch", cri);
	}


	@Override
	public Integer periodgetSearchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "periodgetSearchCount", cri);
	}
}
