package org.union.persistence;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.union.controller.MediaController;
import org.union.domain.GraphVO;
import org.union.domain.MailVO;
import org.union.domain.MediaVO;
import org.union.domain.NewsVO;
import org.union.domain.ReplyVO;
import org.union.domain.SearchCriteria;
import org.union.domain.SearchFv;
import org.union.domain.SearchMedia;
import org.union.domain.TextTypeDateVO;
import org.union.domain.TextTypeVO;

@Repository
public class MediaDAOImpl implements MediaDAO {

	@Autowired
	@Resource(name="oneSqlSession")
	private SqlSession session1;
	
	@Autowired
	@Resource(name="twoSqlSession")
	private SqlSession session2;
	
	private static final String namespace = "org.union.mappers.MediaMapper.";
	private static Logger logger = LoggerFactory.getLogger(MediaController.class);
	
	@Override
	public void create(MediaVO vo) throws SQLException {
		try {
			session1.insert(namespace + "create", vo);
			session2.insert(namespace + "create", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void replyAdd(NewsVO vo) throws SQLException {
		try {
			session1.insert(namespace + "replyAdd", vo);
			session2.insert(namespace + "replyAdd", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<NewsVO> newsList(SearchCriteria cri) throws SQLException {
		
		return session1.selectList(namespace + "newsList", cri);
	}

	@Override
	public List<ReplyVO> replyList(SearchCriteria cri) throws SQLException {
		logger.info("replyList: " + cri);
		return session1.selectList(namespace + "replyList", cri);
	}
	
	@Override
	public MediaVO read(Integer media_idx) throws SQLException {

		return session1.selectOne(namespace + "read", media_idx);
	}

	
	@Override
	public void update(MediaVO vo) throws SQLException {
		try {
			session1.update(namespace + "update", vo);
			session2.update(namespace + "update", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void delete(Integer media_idx) throws SQLException {
		try {
			session1.delete(namespace + "delete", media_idx);
			session2.delete(namespace + "delete", media_idx);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	@Override
	public void newsDelete(Integer media_idx) throws SQLException {
		try {
			session1.delete(namespace + "newsDelete", media_idx);
			session2.delete(namespace + "newsDelete", media_idx);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	@Override
	public void replyDelete(Integer reply_idx) throws SQLException {
		try {
			session1.delete(namespace + "replyDelete", reply_idx);
			session2.delete(namespace + "replyDelete", reply_idx);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	@Override
	public List<MediaVO> listExtract(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "listExtract", cri);
	}

	@Override
	public Integer getExtractCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getExtractCount", cri);
	}



	@Override
	public List<MediaVO> listSearch(SearchCriteria vo) throws SQLException {
		
		return session1.selectList(namespace + "listSearch", vo);
	}
	
	@Override
	public Integer getSearchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getSearchCount", cri);
	}
	
	@Override
	public List<MediaVO> wlistSearch(SearchCriteria vo) throws SQLException {
		
		return session1.selectList(namespace + "wlistSearch", vo);
	}
	
	@Override
	public List<MediaVO> wlistSearch2(SearchCriteria vo) throws SQLException {
		
		return session1.selectList(namespace + "wlistSearch2", vo);
	}

	
	@Override
	public Integer wgetSearchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "wgetSearchCount", cri);
	}
	
	@Override
	public List<MediaVO> searchAll(SearchCriteria criteria) throws SQLException {

		return session1.selectList(namespace + "searchAll", criteria);
	}

	public List<MediaVO> listAll(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "listAll", cri);
	}
	
	@Override
	public Integer mediaGetTotalCount(SearchCriteria criteria) throws SQLException {

		return session1.selectOne(namespace + "mediaGetTotalCount", criteria);
	}


	@Override
	public Integer reporterGetTotalCount(SearchCriteria criteria) throws SQLException {

		return session1.selectOne(namespace + "reporterGetTotalCount", criteria);
	}
	

	@Override
	public void updateTextType(MediaVO vo) throws SQLException {
		try {
			session1.update(namespace + "updateTextType", vo);
			session2.update(namespace + "updateTextType", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void newsUpdateTextType(NewsVO vo) throws SQLException {
		try {
			session1.update(namespace + "newsUpdateTextType", vo);
			session2.update(namespace + "newsUpdateTextType", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void newsUpdateState(NewsVO vo) throws SQLException {
		try {
			session1.update(namespace + "newsUpdateState", vo);
			session2.update(namespace + "newsUpdateState", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void replyUpdateTextType(ReplyVO vo) throws SQLException {
		try {
			session1.update(namespace + "replyUpdateTextType", vo);
			session2.update(namespace + "replyUpdateTextType", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void updateThumbnail(MediaVO vo) throws SQLException {
		try {
			session1.update(namespace + "updateThumbnail", vo);
			session2.update(namespace + "updateThumbnail", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public Integer mediaGetSearchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "mediaGetSearchCount", cri);
	}


	@Override
	public Integer reporterGetSearchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "reporterGetSearchCount", cri);
	}


	@Override
	public TextTypeVO naverMediaCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "naverMediaCount", cri);
	}


	@Override
	public TextTypeVO daumMediaCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "daumMediaCount", cri);
	}
	
	@Override
	public TextTypeVO totalMediaCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "totalMediaCount", cri);
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
	public List<MediaVO> allPageList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "allPageList", cri);
	}
	
	@Override
	public List<MediaVO> TotalAllPageList(SearchCriteria cri) throws SQLException {
		
		return session1.selectList(namespace + "TotalAllPageList", cri);
	}

	@Override
	public Integer allPageCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "allPageCount", cri);
	}
	
	@Override
	public Integer newsAllPageCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "newsAllPageCount", cri);
	}
	
	@Override
	public Integer replyAllPageCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "replyAllPageCount", cri);
	}
	
	@Override
	public List<ReplyVO> replyTotalList(Integer news_idx) throws SQLException {

		return session1.selectList(namespace + "replyTotalList", news_idx);
	}
	
	@Override
	public Integer TotalAllPageCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "TotalAllPageCount", cri);
	}

	@Override
	public List<MediaVO> allPage(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "allPage", cri);
	}
	
	@Override
	public List<ReplyVO> replyAllPage(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "replyAllPage", cri);
	}

	@Override
	public Integer getTotalCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getTotalCount", cri);
		
	}

	@Override
	public Integer getMatchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getMatchCount", cri);
	}


	@Override
	public TextTypeVO periodTextTypeCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "periodTextTypeCount", cri);
	}


	@Override
	public TextTypeVO getMediaPortalCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getMediaPortalCount", cri);
	}


	@Override
	public TextTypeVO getMediaTextTypeTotalCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "getMediaTextTypeTotalCount", cri);
	}


	@Override
	public TextTypeVO getMediaTextTypeSearchCount(SearchCriteria cri) throws SQLException {
		
		return session1.selectOne(namespace + "getMediaTextTypeSearchCount", cri);
	}


	@Override
	public TextTypeVO getPressPortalCount(SearchCriteria cri) throws SQLException {
		
		return session1.selectOne(namespace + "getPressPortalCount", cri);
	}


	@Override
	public TextTypeVO getPressTextTypeTotalCount(SearchCriteria cri) throws SQLException {
		
		return session1.selectOne(namespace + "getPressTextTypeTotalCount", cri);
	}


	@Override
	public TextTypeVO getPressTextTypeSearchCount(SearchCriteria cri) throws SQLException {
		
		return session1.selectOne(namespace + "getPressTextTypeSearchCount", cri);
	}


	@Override
	public List<MediaVO> wPageSearch(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "wPageSearch", cri);
	}
	
	@Override
	public List<MediaVO> textTypelistSearch(SearchCriteria cri) throws SQLException {
		
		return session1.selectList(namespace + "textTypelistSearch", cri);
	}

	@Override
	public List<MediaVO> textTypelistSearch2(SearchCriteria cri) throws SQLException {
		
		return session1.selectList(namespace + "textTypelistSearch2", cri);
	}


	@Override
	public List<MediaVO> textTypelistSearch3(SearchCriteria cri) throws SQLException {
		
		return session1.selectList(namespace + "textTypelistSearch3", cri);
	}


	@Override
	public List<MediaVO> textTypelistSearch4(SearchCriteria cri) throws SQLException {
		
		return session1.selectList(namespace + "textTypelistSearch4", cri);
	}


	@Override
	public List<MediaVO> reporterGetTextTypeCount(SearchCriteria cri, String reporter, String textType) throws SQLException {
		
		Map data = new HashMap();
		data.put("cri", cri);
		data.put("reporter", reporter);
		data.put("textType", textType);
		
		
		return session1.selectList(namespace + "reporterGetTextTypeCount", data);
	}

	@Override
	public List<MediaVO> mediaCnt(SearchCriteria cri) throws SQLException {
		return session1.selectList(namespace + "mediaCnt", cri);
	}

	@Override
	public List<TextTypeDateVO> textTypeCount2(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "textTypeCount2", cri);
	}

	@Override
	public Integer checkUrl(String url) throws SQLException {
		return session1.selectOne(namespace + "checkUrl", url);
	}

	@Override
	public Integer mTotalCnt(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "mTotalCnt", cri);
	}

	@Override
	public void checkList(Integer media_idx) throws SQLException {
		try {
			session1.insert(namespace + "checkList", media_idx);
			session2.insert(namespace + "checkList", media_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<MediaVO> headlineList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "headlineList", cri);
	}

	@Override
	public Integer mediaTotalcnt(SearchCriteria cri) throws SQLException {
		
		return session1.selectOne(namespace + "mediaTotalcnt", cri);
	}

	@Override
	public Integer replyTotalcnt(SearchCriteria cri) throws SQLException {
		
		return session1.selectOne(namespace + "replyTotalcnt", cri);
	}

	@Override
	public Integer mediaTextcnt(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "mediaTextcnt", cri);
	}

	@Override
	public Integer mediaTextcnt2(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "mediaTextcnt2", cri);
	}

	@Override
	public Integer mediaCountAll(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "mediaCountAll", cri);
	}

	@Override
	public void headlineUpdate(Integer media_idx) throws SQLException {
		try {
			session1.insert(namespace + "headlineUpdate", media_idx);
			session2.insert(namespace + "headlineUpdate", media_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void headlineUpdate2(Integer media_idx) throws SQLException {
		try {
			session1.insert(namespace + "headlineUpdate2", media_idx);
			session2.insert(namespace + "headlineUpdate2", media_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<MailVO> mailList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "mailList", cri);
	}

	@Override
	public Integer mailCountAll(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "mailCountAll", cri);
	}

	@Override
	public List<MediaVO> mediaMatchList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "mediaMatchList", cri);
	}

	@Override
	public Integer mediaMatchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "mediaMatchCount", cri);
	}

	@Override
	public List<MediaVO> mediaMatchList2(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "mediaMatchList2", cri);
	}

	@Override
	public Integer mediaMatchCount2(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "mediaMatchCount2", cri);
	}
	
	@Override
	public Integer mediaMatchCount3(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "mediaMatchCount3", cri);
	}

	@Override
	public List<MediaVO> mediaTotalMatchList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "mediaTotalMatchList", cri);
	}

	@Override
	public List<MediaVO> reporterMatchList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "reporterMatchList", cri);
	}

	@Override
	public Integer reporterMatchCount(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "reporterMatchCount", cri);
	}

	@Override
	public List<MediaVO> reporterMatchList2(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "reporterMatchList2", cri);
	}

	@Override
	public Integer reporterMatchCount2(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "reporterMatchCount2", cri);
	}

	@Override
	public Integer reporterMatchCount3(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "reporterMatchCount3", cri);
	}

	@Override
	public List<MediaVO> reporterTotalMatchList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "reporterTotalMatchList", cri);
	}

	@Override
	public List<MediaVO> mediaMatchallList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "mediaMatchallList", cri);
	}

	@Override
	public List<MediaVO> reporterMatchallList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "reporterMatchallList", cri);
	}

	@Override
	public List<MediaVO> mediaDataList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "mediaDataList", cri);
	}

	@Override
	public List<MediaVO> mediaMatchDataList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "mediaMatchDataList", cri);
	}

	@Override
	public List<MediaVO> reporterDataList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "reporterDataList", cri);
	}

	@Override
	public List<MediaVO> reporterMatchDataList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "reporterMatchDataList", cri);
	}

	@Override
	public Integer mediaMatchCount4(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "mediaMatchCount4", cri);
	}

	@Override
	public Integer reporterMatchCount4(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "reporterMatchCount4", cri);
	}

	@Override
	public void checkDelete(Integer media_idx) throws SQLException {
		try {
			session1.delete(namespace + "checkDelete", media_idx);
			session2.delete(namespace + "checkDelete", media_idx);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}

	@Override
	public List<MediaVO> totalAllPageex(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "totalAllPageex", cri);
	}

	@Override
	public List<MediaVO> mailMatch(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "mailMatch", cri);
	}

	@Override
	public List<MediaVO> mediaListData(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "mediaListData", cri);
	}

	@Override
	public Integer graphmTotalCnt(SearchCriteria cri) throws SQLException {

		return session1.selectOne(namespace + "graphmTotalCnt", cri);
	}

	@Override
	public List<MediaVO> dashListAll(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "dashListAll", cri);
	}

	@Override
	public List<MediaVO> newsExcel(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "newsExcel", cri);
	}

	@Override
	public List<MediaVO> dashMediaMatch(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "dashMediaMatch", cri);
	}

	@Override
	public List<MediaVO> listAllEx(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "listAllEx", cri);
	}
}
