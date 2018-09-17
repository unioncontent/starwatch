package org.union.util;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Component;
import org.union.domain.CommunityVO;
import org.union.domain.ExtractVO;
import org.union.domain.FvVO;
import org.union.domain.MediaVO;
import org.union.domain.MobileEntVO;
import org.union.domain.NaverMovieVO;
import org.union.domain.NvVO;
import org.union.domain.PortalVO;
import org.union.domain.ReplyVO;
import org.union.domain.SNSVO;
import org.union.domain.ViralVO;

@Component
public class ListUtil {
	
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	SimpleDateFormat date2 = new SimpleDateFormat("yyyy-MM-dd HH:00");
	

	public List<ExtractVO> listAddList(List<ExtractVO> list, List<ExtractVO> addList) {

		for (int i = 0; i < addList.size(); i++) {
			list.add(addList.get(i));
		}

		return list;
	}

	public List<ExtractVO> listAddSNSList(List<ExtractVO> list, List<SNSVO> addList) {

		try {

			for(int i = 0; i < addList.size(); i++) {
				ExtractVO vo = new ExtractVO();
				
				vo.setDomain("sns");
				vo.setDomainType(addList.get(i).getSns_name());
				vo.setKeyword_main(addList.get(i).getKeyword_main());
				vo.setKeyword(addList.get(i).getKeyword());
				vo.setTitle(addList.get(i).getSns_title());
				vo.setContent(addList.get(i).getSns_content());
				vo.setWriteDate(addList.get(i).getWriteDate());
				vo.setSns_idx(addList.get(i).getSns_idx());
				vo.setUrl(addList.get(i).getUrl());
				vo.setCreateDate(date.format(addList.get(i).getCreateDate()));
				vo.setTextType(addList.get(i).getTextType());
				vo.setWriter(addList.get(i).getSns_writer());
				
				/*System.out.println(keywordService.viewByKeyword(vo.getKeyword()).getKeyword_idx());
				System.out.println(userService.view(keywordService.viewByKeyword(vo.getKeyword()).getKeyword_idx()).getUser_name());
				*/
				/*vo.setCompany(
						userService.view(keywordService.viewByKeyword(vo.getKeyword()).getUser_idx()).getUser_name());*/
			
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<ExtractVO> listAddCommunityList(List<ExtractVO> list, List<CommunityVO> addList) {

		try {

			for(int i = 0; i < addList.size(); i++) {
				ExtractVO vo = new ExtractVO();
				
				vo.setDomain("community");
				vo.setDomainType(addList.get(i).getCommunity_name());
				vo.setKeyword_main(addList.get(i).getKeyword_main());
				vo.setKeyword(addList.get(i).getKeyword());
				vo.setTitle(addList.get(i).getCommunity_title());
				vo.setContent(addList.get(i).getCommunity_content());
				vo.setWriter_IP(addList.get(i).getCommunity_writer_IP());
				vo.setWriteDate(addList.get(i).getWriteDate());
				vo.setSns_idx(addList.get(i).getCommunity_idx());
				vo.setUrl(addList.get(i).getUrl());
				vo.setCreateDate(date.format(addList.get(i).getCreateDate()));
				vo.setTextType(addList.get(i).getTextType());
				vo.setWriter(addList.get(i).getCommunity_writer());
				vo.setThumbnail(addList.get(i).getThumbnail());
			
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<ExtractVO> listAddViralList(List<ExtractVO> list, List<ViralVO> addList) {

		try {

			for(int i = 0; i < addList.size(); i++) {
				ExtractVO vo = new ExtractVO();
				
				vo.setDomain("viral");
				vo.setDomainType(addList.get(i).getPortal_name());
				vo.setKeyword(addList.get(i).getKeyword());
				vo.setTitle(addList.get(i).getViral_title());
				vo.setContent(addList.get(i).getPortal_type());
				vo.setPortal_idx(addList.get(i).getViral_idx());
				vo.setUrl(addList.get(i).getUrl());
				vo.setCreateDate(date.format(addList.get(i).getCreateDate()));
				vo.setThumbnail(addList.get(i).getThumbnail());
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<ExtractVO> listAddMovieList(List<ExtractVO> list, List<NaverMovieVO> addList) {

		try {

			for(int i = 0; i < addList.size(); i++) {
				ExtractVO vo = new ExtractVO();
				
				vo.setDomain("movie");
				vo.setDomainType("naver");
				vo.setKeyword_main(addList.get(i).getTitle_key());
				vo.setKeyword(addList.get(i).getKeyword());
				vo.setTitle(addList.get(i).getNM_title());
				vo.setWriter(addList.get(i).getWriter());
				vo.setUrl(addList.get(i).getUrl());
				vo.setWriteDate(date.format(addList.get(i).getUpdateDate()));
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<ExtractVO> listAddMobileList(List<ExtractVO> list, List<MobileEntVO> addList) {
		try {
			for(int i = 0; i < addList.size(); i++) {
				ExtractVO vo = new ExtractVO();
				vo.setDomain("mobile");
				vo.setDomainType("naver");
				vo.setKeyword_main(addList.get(i).getTitle_key());
				vo.setKeyword(addList.get(i).getKeyword());
				vo.setTitle(addList.get(i).getME_title());
				vo.setME_rank(addList.get(i).getME_rank());
				if(addList.get(i).getReply_cnt() == null) {
					int reply_cnt = 0;
					vo.setReply_cnt(Integer.toString(reply_cnt));
				}else {
					vo.setReply_cnt(Integer.toString(addList.get(i).getReply_cnt()));
				}
				vo.setReporter_name(addList.get(i).getReporter_name());
				vo.setReporter_media_name(addList.get(i).getReporter_media_name());
				vo.setWriteDate(addList.get(i).getWriteDate());
				vo.setUrl(addList.get(i).getUrl());
				
				list.add(vo);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	public List<ExtractVO> listAddMediaList(List<ExtractVO> list, List<MediaVO> addList) {

		try {

			for(int i = 0; i < addList.size(); i++) {
				ExtractVO vo = new ExtractVO();
				
				vo.setDomain("media");
				vo.setDomainType(addList.get(i).getMedia_name());
				vo.setKeyword_main(addList.get(i).getKeyword_main());
				vo.setKeyword(addList.get(i).getKeyword());
				vo.setTitle(addList.get(i).getMedia_title());
				vo.setContent(addList.get(i).getMedia_content());
				vo.setWriteDate(addList.get(i).getWriteDate());
				vo.setSns_idx(addList.get(i).getMedia_idx());
				vo.setUrl(addList.get(i).getUrl());
				vo.setCreateDate(date.format(addList.get(i).getCreateDate()));
				vo.setTextType(addList.get(i).getTextType());
				vo.setWriter(addList.get(i).getReporter_name());
				vo.setThumbnail(addList.get(i).getThumbnail());
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<ExtractVO> listAddMediaMatchList(List<ExtractVO> list, List<MediaVO> addList) {

		try {

			for(int i = 0; i < addList.size(); i++) {
				ExtractVO vo = new ExtractVO();
				
				vo.setDomain("media");
				vo.setDomainType(addList.get(i).getMedia_name());
				vo.setTitle(addList.get(i).getMedia_title());
				vo.setContent(addList.get(i).getMedia_content());
				vo.setWriter(addList.get(i).getReporter_name());
				vo.setWriteDate(addList.get(i).getWriteDate());
				vo.setKeyword_main(addList.get(i).getKeyword_main());
				vo.setKeyword(addList.get(i).getKeyword());
				vo.setUrl(addList.get(i).getUrl());
				vo.setCreateDate(date.format(addList.get(i).getCreateDate()));
				vo.setTextType(addList.get(i).getTextType());
				vo.setThumbnail(addList.get(i).getThumbnail());
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<ExtractVO> listAddReplyList(List<ExtractVO> list, List<ReplyVO> addList) {

		try {

			for(int i = 0; i < addList.size(); i++) {
				ExtractVO vo = new ExtractVO();
				
				vo.setDomain("news");
				vo.setDomainType("naver");
				vo.setKeyword_main(addList.get(i).getTitle_key());
				vo.setKeyword(addList.get(i).getKeyword());
				vo.setTitle(addList.get(i).getMedia_title());
				vo.setContent(addList.get(i).getReply_content());
				vo.setWriter(addList.get(i).getReply_writer());
				vo.setTextType(addList.get(i).getTextType());
				vo.setWriteDate(addList.get(i).getWriteDate());
				vo.setUrl(addList.get(i).getUrl());
				vo.setCreateDate(date.format(addList.get(i).getUpdateDate()));
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<ExtractVO> listAddNvList(List<ExtractVO> list, List<NvVO> addList) {

		try {

			for(int i = 0; i < addList.size(); i++) {
				ExtractVO vo = new ExtractVO();
				
				vo.setDomain("videos");
				vo.setDomainType("naver");
				vo.setTitle(addList.get(i).getPortal_title());
				vo.setWriter(addList.get(i).getPortal_writer());
				vo.setUrl(addList.get(i).getUrl());
				vo.setView_cnt(Integer.toString(addList.get(i).getView_cnt()));
				vo.setReply_cnt(Integer.toString(addList.get(i).getReply_cnt()));
				vo.setLike_cnt(Integer.toString(addList.get(i).getLike_cnt()));
				vo.setWriteDate(addList.get(i).getWriteDate());
				vo.setCreateDate(date2.format(addList.get(i).getUpdateDate()));
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<ExtractVO> listAddFvList(List<ExtractVO> list, List<FvVO> addList) {

		try {

			for(int i = 0; i < addList.size(); i++) {
				
		            ExtractVO vo = new ExtractVO();
		            
		            vo.setDomain("videos");
		            vo.setDomainType("facebook");
		            vo.setTitle(addList.get(i).getSns_content());
		            vo.setWriter(addList.get(i).getSns_writer());
		            vo.setUrl(addList.get(i).getUrl());
		            vo.setView_cnt(Integer.toString(addList.get(i).getView_cnt()));
		            vo.setReply_cnt(Integer.toString(addList.get(i).getReply_cnt()));
		            vo.setLike_cnt(Integer.toString(addList.get(i).getLike_cnt()));
		            vo.setWriteDate(addList.get(i).getWriteDate());
		            vo.setCreateDate(date2.format(addList.get(i).getUpdateDate()));
		            
		            list.add(vo);
		            
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<ExtractVO> listAddFvListUp(List<ExtractVO> list, List<FvVO> addList, List<FvVO> addList2, List<FvVO> addList3) {

		try {
			int view1 = addList.get(0).getView_cnt();
			int view2 = addList3.get(0).getView_cnt();
			if(view1 == view2) {
				
				ExtractVO vo = new ExtractVO();
				
				vo.setDomain("videos");
	            vo.setDomainType("facebook");
	            vo.setTitle(addList.get(0).getSns_content());
	            vo.setWriter(addList.get(0).getSns_writer());
	            vo.setUrl(addList.get(0).getUrl());
	            vo.setView_cnt(Integer.toString(addList.get(0).getView_cnt()));
	            vo.setReply_cnt(Integer.toString(addList.get(0).getReply_cnt()));
	            vo.setLike_cnt(Integer.toString(addList.get(0).getLike_cnt()));
	            vo.setWriteDate(addList.get(0).getWriteDate());
	            vo.setCreateDate(date2.format(addList.get(0).getUpdateDate()));
	            
	            list.add(vo);
	            
	            for(int i = 0; i < addList.size(); i++) {

				   ExtractVO vo1 = new ExtractVO();
		            vo1.setDomain("videos");
		            vo1.setDomainType("facebook");
		            vo1.setTitle(addList.get(i).getSns_content());
		            vo1.setWriter(addList.get(i).getSns_writer());
		            vo1.setUrl(addList.get(i).getUrl());
		            vo1.setView_cnt(Integer.toString(addList.get(i+1).getView_cnt() -addList.get(i).getView_cnt()));
		            vo1.setReply_cnt(Integer.toString(addList.get(i+1).getReply_cnt() -addList.get(i).getReply_cnt()));
		            vo1.setLike_cnt(Integer.toString(addList.get(i+1).getLike_cnt() - addList.get(i).getLike_cnt()));
		            vo1.setWriteDate(addList.get(i).getWriteDate());
		            vo1.setCreateDate(date2.format(addList.get(i+1).getUpdateDate()));
		            
		            /*vo.setView_cnt(Integer.toString(addList2.get(i).getView_cnt() - addList.get(i).getView_cnt()));
		            vo.setReply_cnt(Integer.toString(addList2.get(i).getReply_cnt() - addList.get(i).getReply_cnt()));
		            vo.setLike_cnt(Integer.toString(addList2.get(i).getLike_cnt() - addList.get(i).getLike_cnt()));*/
		            System.out.print("view1:" + addList.get(0).getView_cnt());
		            System.out.print("view2:" + addList3.get(0).getView_cnt());
		            System.out.print("list1:" + addList.get(i).getUpdateDate());
		            System.out.print("list2:" + addList.get(i).getUpdateDate());
		            list.add(vo1);
	            }
	            
		}else {
			
			for(int i = 0; i < addList.size(); i++) {

				   ExtractVO vo2 = new ExtractVO();
		            vo2.setDomain("videos");
		            vo2.setDomainType("facebook");
		            vo2.setTitle(addList.get(i).getSns_content());
		            vo2.setWriter(addList.get(i).getSns_writer());
		            vo2.setUrl(addList.get(i).getUrl());
		            vo2.setView_cnt(Integer.toString(addList.get(i).getView_cnt() -addList2.get(i).getView_cnt()));
		            vo2.setReply_cnt(Integer.toString(addList.get(i).getReply_cnt() -addList2.get(i).getReply_cnt()));
		            vo2.setLike_cnt(Integer.toString(addList.get(i).getLike_cnt() - addList2.get(i).getLike_cnt()));
		            vo2.setWriteDate(addList.get(i).getWriteDate());
		            vo2.setCreateDate(date2.format(addList.get(i).getUpdateDate()));
		            
		            System.out.print("view1:" + addList.get(0).getView_cnt());
		            System.out.print("view2:" + addList3.get(0).getView_cnt());
		            System.out.print("list1:" + addList.get(i).getUpdateDate());
		            System.out.print("list2:" + addList.get(i).getUpdateDate());
		            list.add(vo2);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<ExtractVO> listAddnvListUp(List<ExtractVO> list, List<NvVO> addList, List<NvVO> addList2, List<NvVO> addList3) {

		try {
			int view1 = addList.get(0).getView_cnt();
			int view2 = addList3.get(0).getView_cnt();
			if(view1 == view2) {
				
				ExtractVO vo = new ExtractVO();
				
				vo.setDomain("videos");
	            vo.setDomainType("facebook");
	            vo.setTitle(addList.get(0).getPortal_title());
	            vo.setWriter(addList.get(0).getPortal_writer());
	            vo.setUrl(addList.get(0).getUrl());
	            vo.setView_cnt(Integer.toString(addList.get(0).getView_cnt()));
	            vo.setReply_cnt(Integer.toString(addList.get(0).getReply_cnt()));
	            vo.setLike_cnt(Integer.toString(addList.get(0).getLike_cnt()));
	            vo.setWriteDate(addList.get(0).getWriteDate());
	            vo.setCreateDate(date2.format(addList.get(0).getUpdateDate()));
	            
	            list.add(vo);
	            
	            for(int i = 0; i < addList.size(); i++) {

				   ExtractVO vo1 = new ExtractVO();
		            vo1.setDomain("videos");
		            vo1.setDomainType("facebook");
		            vo1.setTitle(addList.get(i).getPortal_title());
		            vo1.setWriter(addList.get(i).getPortal_writer());
		            vo1.setUrl(addList.get(i).getUrl());
		            vo1.setView_cnt(Integer.toString(addList.get(i+1).getView_cnt() -addList.get(i).getView_cnt()));
		            vo1.setReply_cnt(Integer.toString(addList.get(i+1).getReply_cnt() -addList.get(i).getReply_cnt()));
		            vo1.setLike_cnt(Integer.toString(addList.get(i+1).getLike_cnt() - addList.get(i).getLike_cnt()));
		            vo1.setWriteDate(addList.get(i).getWriteDate());
		            vo1.setCreateDate(date2.format(addList.get(i+1).getUpdateDate()));
		            
		            /*vo.setView_cnt(Integer.toString(addList2.get(i).getView_cnt() - addList.get(i).getView_cnt()));
		            vo.setReply_cnt(Integer.toString(addList2.get(i).getReply_cnt() - addList.get(i).getReply_cnt()));
		            vo.setLike_cnt(Integer.toString(addList2.get(i).getLike_cnt() - addList.get(i).getLike_cnt()));*/
		            System.out.print("view1:" + addList.get(0).getView_cnt());
		            System.out.print("view2:" + addList3.get(0).getView_cnt());
		            System.out.print("list1:" + addList.get(i).getUpdateDate());
		            System.out.print("list2:" + addList.get(i).getUpdateDate());
		            list.add(vo1);
	            }
	            
		}else {
			
			for(int i = 0; i < addList.size(); i++) {

				   ExtractVO vo2 = new ExtractVO();
		            vo2.setDomain("videos");
		            vo2.setDomainType("facebook");
		            vo2.setTitle(addList.get(i).getPortal_title());
		            vo2.setWriter(addList.get(i).getPortal_writer());
		            vo2.setUrl(addList.get(i).getUrl());
		            vo2.setView_cnt(Integer.toString(addList.get(i).getView_cnt() -addList2.get(i).getView_cnt()));
		            vo2.setReply_cnt(Integer.toString(addList.get(i).getReply_cnt() -addList2.get(i).getReply_cnt()));
		            vo2.setLike_cnt(Integer.toString(addList.get(i).getLike_cnt() - addList2.get(i).getLike_cnt()));
		            vo2.setWriteDate(addList.get(i).getWriteDate());
		            vo2.setCreateDate(date2.format(addList.get(i).getUpdateDate()));
		            
		            System.out.print("view1:" + addList.get(0).getView_cnt());
		            System.out.print("view2:" + addList3.get(0).getView_cnt());
		            System.out.print("list1:" + addList.get(i).getUpdateDate());
		            System.out.print("list2:" + addList.get(i).getUpdateDate());
		            list.add(vo2);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<ExtractVO> listAddPortalList(List<ExtractVO> list, List<PortalVO> addList) {

		try {
			
			for(int i = 0; i < addList.size(); i++) {
				ExtractVO vo = new ExtractVO();
				
				vo.setDomain("portal");
				vo.setDomainType(addList.get(i).getPortal_name());
				vo.setKeyword_main(addList.get(i).getKeyword_main());
				vo.setKeyword(addList.get(i).getKeyword());
				vo.setTitle(addList.get(i).getPortal_title());
				vo.setContent(addList.get(i).getPortal_content());
				vo.setWriter(addList.get(i).getWriter());
				vo.setWriteDate(addList.get(i).getWriteDate());
				vo.setSns_idx(addList.get(i).getPortal_idx());
				vo.setUrl(addList.get(i).getUrl());
				vo.setCreateDate(date.format(addList.get(i).getCreateDate()));
				vo.setTextType(addList.get(i).getTextType());
				vo.setThumbnail(addList.get(i).getThumbnail());
				
				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
