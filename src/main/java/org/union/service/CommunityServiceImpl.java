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
import org.union.domain.SearchCriteria;
import org.union.domain.TextTypeDateVO;
import org.union.domain.TextTypeVO;
import org.union.persistence.CommunityDAO;
import org.union.persistence.KeywordDAO;

@Service
public class CommunityServiceImpl implements CommunityService {

	@Autowired
	private CommunityDAO communityDAO;
	
	@Autowired
	private KeywordDAO keywordDAO;
	
	@Override
	public void regist(CommunityVO vo) throws SQLException {

		communityDAO.create(vo);
	}

	@Override
	public CommunityVO read(Integer community_idx) throws SQLException {

		return null;
	}

	@Override
	public void modify(CommunityVO vo) throws SQLException {

	}

	@Override
	public void remove(Integer community_idx) throws SQLException {

		communityDAO.delete(community_idx);
	}

	@Override
	public List<ExtractVO> listExtract(SearchCriteria cri) throws SQLException {
		try {
			List<CommunityVO> communityList = communityDAO.listExtract(cri);
			
			List<ExtractVO> extractList = new ArrayList<ExtractVO>();
			
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			for(int i = 0; i < communityList.size(); i++) {
				ExtractVO vo = new ExtractVO();
				CommunityVO data = communityList.get(i);
				vo.setCommunity_idx(data.getCommunity_idx());
				vo.setDomain("community");
				vo.setDomainType(data.getCommunity_name());
				vo.setTitle(data.getCommunity_title());
				vo.setContent(data.getCommunity_content());
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
	public Integer getExtractCount(SearchCriteria cri) throws SQLException {

		return communityDAO.getExtractCount(cri);
	}

	@Override
	public List<CommunityVO> listSearch(SearchCriteria cri) throws SQLException {

		return communityDAO.listSearch(cri);
	}

	@Override
	public Integer getSearchCount(SearchCriteria cri) throws SQLException {

		return communityDAO.getSearchCount(cri);
	}
	
	@Override
	public Integer getSearchCount2(SearchCriteria cri) throws SQLException {

		return communityDAO.getSearchCount2(cri);
	}

	@Override
	public List<CommunityVO> wlistSearch(SearchCriteria cri) throws SQLException {

		return communityDAO.wlistSearch(cri);
	}

	@Override
	public Integer wgetSearchCount(SearchCriteria cri) throws SQLException {

		return communityDAO.wgetSearchCount(cri);
	}

	
	@Override
	public List<CommunityVO> listAll(SearchCriteria cri) throws SQLException {
		
		List<CommunityVO> list = communityDAO.listAll(cri);
		
		for (CommunityVO communityVO : list) {
			communityVO.setKeyword_main(keywordDAO.read(communityVO.getKeyword()).getKeyword_main());
		}

		return list;
	}
	
	@Override
	public void modifyType(CommunityVO vo) throws SQLException {

		communityDAO.updateTextType(vo);
	}

	@Override
	public void modifyThumbnail(CommunityVO vo) throws SQLException {

		communityDAO.updateThumbnail(vo);
	}

	@Override
	public List<CommunityVO> listComplete(SearchCriteria cri) throws SQLException {

		return communityDAO.listComplete(cri);
	}

	@Override
	public Integer getCompleteCount(SearchCriteria cri) throws SQLException {

		return communityDAO.getCompleteCount(cri);
	}

	@Override
	public TextTypeVO textTypeCount(SearchCriteria cri) throws SQLException {

		return communityDAO.textTypeCount(cri);
	}

	@Override
	public Integer countAll(Date date) throws SQLException {

		return communityDAO.countAll(date);
	}
	
	@Override
	public Integer showboxCountAll(Date date) throws SQLException {

		return communityDAO.showboxCountAll(date);
	}

	@Override
	public List<CommunityVO> allPageList(SearchCriteria cri) throws SQLException {

		return communityDAO.allPageList(cri);
	}
	
	@Override
	public List<CommunityVO> TotalAllPageList(SearchCriteria cri) throws SQLException {

		return communityDAO.TotalAllPageList(cri);
	}

	@Override
	public Integer allPageCount(SearchCriteria cri) throws SQLException {

		return communityDAO.allPageCount(cri);
	}
	
	@Override
	public Integer TotalAllPageCount(SearchCriteria cri) throws SQLException {

		return communityDAO.TotalAllPageCount(cri);
	}

	@Override
	public List<CommunityVO> wPageSearch(SearchCriteria cri) throws SQLException {
		
		List<CommunityVO> list = communityDAO.wPageSearch(cri);
		
		for (CommunityVO communityVO : list) {
			communityVO.setKeyword_main(keywordDAO.read(communityVO.getKeyword()).getKeyword_main());
		}

		return list;
	}

	@Override
	public List<CommunityVO> allPage(SearchCriteria cri) throws SQLException {

		List<CommunityVO> list = communityDAO.allPage(cri);
		
		for (CommunityVO communityVO : list) {
			communityVO.setKeyword_main(keywordDAO.read(communityVO.getKeyword()).getKeyword_main());
		}

		return list;
		
	}

	@Override
	public List<TextTypeDateVO> textTypeCount2(SearchCriteria cri) throws SQLException {

		return communityDAO.textTypeCount2(cri);
	}

	@Override
	public Integer communityTextcnt(SearchCriteria cri) throws SQLException {

		return communityDAO.communityTextcnt(cri);
	}

	@Override
	public Integer communityTextcnt2(SearchCriteria cri) throws SQLException {

		return communityDAO.communityTextcnt2(cri);
	}

	@Override
	public Integer graphSearchCount(SearchCriteria cri) throws SQLException {

		return communityDAO.graphSearchCount(cri);
	}

	@Override
	public List<CommunityVO> totalAllPageex(SearchCriteria cri) throws SQLException {

		return communityDAO.totalAllPageex(cri);
	}

	@Override
	public List<CommunityVO> dashListAll(SearchCriteria cri) throws SQLException {

		return communityDAO.dashListAll(cri);
	}

	@Override
	public Integer periodWgetSearchCount(SearchCriteria cri) throws SQLException {

		return communityDAO.periodWgetSearchCount(cri);
	}

	@Override
	public List<CommunityVO> alllistExtract(SearchCriteria cri) throws SQLException {

		return communityDAO.alllistExtract(cri);
	}

	@Override
	public Integer allgetExtractCount(SearchCriteria cri) throws SQLException {

		return communityDAO.allgetExtractCount(cri);
	}

	@Override
	public List<CommunityVO> alllistSearch(SearchCriteria cri) throws SQLException {

		return communityDAO.alllistSearch(cri);
	}

	@Override
	public Integer allgetSearchCount(SearchCriteria cri) throws SQLException {

		return communityDAO.allgetSearchCount(cri);
	}

	@Override
	public List<CommunityVO> allPageallList(SearchCriteria cri) throws SQLException {

		return communityDAO.allPageallList(cri);
	}

	@Override
	public Integer allPageallCount(SearchCriteria cri) throws SQLException {

		return communityDAO.allPageallCount(cri);
	}

	@Override
	public List<CommunityVO> totalallPageallList(SearchCriteria cri) throws SQLException {

		return communityDAO.totalallPageallList(cri);
	}

	@Override
	public Integer totalallPageallCount(SearchCriteria cri) throws SQLException {

		return communityDAO.totalallPageallCount(cri);
	}

	@Override
	public List<CommunityVO> listAllEx(SearchCriteria cri) throws SQLException {

		return communityDAO.listAllEx(cri);
	}

	
}
