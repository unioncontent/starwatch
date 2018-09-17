package org.union.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.union.domain.CommunityVO;
import org.union.domain.ExtractVO;
import org.union.domain.MediaVO;
import org.union.domain.PageMaker;
import org.union.domain.PortalVO;
import org.union.domain.SNSVO;
import org.union.domain.SearchCriteria;
import org.union.domain.UserVO;
import org.union.service.CommunityService;
import org.union.service.KeywordService;
import org.union.service.MediaService;
import org.union.service.PortalService;
import org.union.service.SNSService;
import org.union.service.UserService;
import org.union.util.ExcelView;
import org.union.util.ExtractComparator;
import org.union.util.ListUtil;

@Controller
@RequestMapping("/classification/*")
public class ClassificationController {

	
	@Autowired
	private SNSService snsService;
	
	@Autowired
	private CommunityService communityService;
	
	@Autowired
	private PortalService portalService;
	
	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private KeywordService keywordService;
	
	@Autowired
	private UserService userService;
	
	private static Logger logger = LoggerFactory.getLogger(ClassificationController.class);
	

	@GetMapping("/classification")
	public void classificationGET(@ModelAttribute("cri") SearchCriteria cri, Model model) throws SQLException {
		logger.info("classificationGET called....");

		if(cri.getKeyword() == "" || "undefined".equals(cri.getKeyword()))  {
			logger.info("keyword is null");
			cri.setKeyword(null);
			
		} 
		if(cri.getSelectKey() == "" || "키워드".equals(cri.getSelectKey()) ) {
			logger.info("selectKey is null");
			cri.setSelectKey(null);
		}
		
		if("undefined".equals(cri.getStartDate()) || "undefined".equals(cri.getEndDate())
				|| cri.getStartDate() == "" || cri.getEndDate() == ""){
			cri.setStartDate(null);
			cri.setEndDate(null);
		
		} 
		
		if(cri.getStartDate() != null && cri.getEndDate() != null) {
			logger.info("not null");
			logger.info(cri.getStartDate());
			logger.info(cri.getEndDate());
			if(cri.getStartDate().indexOf("00:00:00") < 0 && cri.getEndDate().indexOf("23:59:59") < 0){ 
				cri.setStartDate(cri.getStartDate() + " 00:00:00"); 
				cri.setEndDate(cri.getEndDate() + " 23:59:59"); 
			}
		}
		if(cri.getCompany() != null) {
			if(cri.getCompany().isEmpty()) {
				cri.setCompany(null);
			}
		}
		
		if(cri.getCompany() == null || cri.getCompany().equals("회사")) {
			logger.info(SecurityContextHolder.getContext().getAuthentication().getName().toString());
			UserVO vo = userService.viewById(SecurityContextHolder.getContext().getAuthentication().getName());
			
			if(!vo.getUser_name().equals("union")) {
			cri.setCompany(vo.getUser_name());
			
			}else {
				cri.setCompany(null);
			}
		}
		
		if(cri.getTextType() != null) {
			if(cri.getTextType().equals("undefined") || cri.getTextType().equals("분류") || cri.getTextType().isEmpty()) {
				cri.setTextType(null);
			}
		}
		
		// 회사 선택에 따른 키워드 재추출
				if(cri.getCompany() != null) {
					if(cri.getCompany().isEmpty() == false) {
					
						UserVO userVO  = userService.viewByName(cri.getCompany());
						logger.info("userVO: " + userVO);
					    logger.info("keywordList: " + keywordService.listByUser(userVO.getUser_idx()));
						model.addAttribute("modelKeywordList", keywordService.listByUser(
								userService.viewByName(cri.getCompany()).getUser_idx()));
					}
				}
		
		model.addAttribute("classiList", communityService.alllistSearch(cri));		
				
		PageMaker pageMaker = new PageMaker();
		
		logger.info("cri: " + cri);
		
		Integer totalCount = communityService.allgetSearchCount(cri);
		
		logger.info("totalCount: " + totalCount);
		model.addAttribute("totalCount", totalCount);
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
		logger.info("pageMaker: " + pageMaker);
		model.addAttribute("pageMaker", pageMaker);
		
	}
	
	/*@GetMapping("/classification")
	public void classificationGET(@ModelAttribute("cri") SearchCriteria cri, Model model) {
		logger.info("classificationGET called....");

		if(cri.getKeyword() == "" || "undefined".equals(cri.getKeyword()))  {
			logger.info("keyword is null");
			cri.setKeyword(null);
			
		} 
		if(cri.getSelectKey() == "" || "키워드".equals(cri.getSelectKey()) ) {
			logger.info("selectKey is null");
			cri.setSelectKey(null);
		}
		
		if("undefined".equals(cri.getStartDate()) || "undefined".equals(cri.getEndDate())
				|| cri.getStartDate() == "" || cri.getEndDate() == ""){
			cri.setStartDate(null);
			cri.setEndDate(null);
		
		} 
		
		if(cri.getStartDate() != null && cri.getEndDate() != null) {
			logger.info("not null");
			logger.info(cri.getStartDate());
			logger.info(cri.getEndDate());
			if(cri.getStartDate().indexOf("00:00:00") < 0 && cri.getEndDate().indexOf("23:59:59") < 0){ 
				cri.setStartDate(cri.getStartDate() + " 00:00:00"); 
				cri.setEndDate(cri.getEndDate() + " 23:59:59"); 
			}
		}
		if(cri.getCompany() != null) {
			if(cri.getCompany().isEmpty()) {
				cri.setCompany(null);
			}
		}
		
		if(cri.getCompany() == null || cri.getCompany().equals("회사")) {
			logger.info(SecurityContextHolder.getContext().getAuthentication().getName().toString());
			UserVO vo = userService.viewById(SecurityContextHolder.getContext().getAuthentication().getName());
			
			if(!vo.getUser_name().equals("union")) {
			cri.setCompany(vo.getUser_name());
			
			}else {
				cri.setCompany(null);
			}
		}
		
		if(cri.getTextType() != null) {
			if(cri.getTextType().equals("undefined") || cri.getTextType().equals("분류") || cri.getTextType().isEmpty()) {
				cri.setTextType(null);
			}
		}
		
		PageMaker pageMaker = new PageMaker();
		
		// 3번 리스트기 때문에  perPageNum / 3
		if(cri.getPerPageNum() != 10) {
			cri.setPerPageNum(cri.getPerPageNum()/3);
				
		}
		
		logger.info("cri: " + cri);
		
		Integer totalCount = + communityService.getSearchCount(cri)
							 + portalService.getSearchCount(cri)
							 + mediaService.getSearchCount(cri);
		
		logger.info("totalCount: " + totalCount);
		model.addAttribute("totalCount", totalCount);
		
		List<ExtractVO> classiList = new ArrayList<ExtractVO>();
		ListUtil listUtil = new ListUtil();

		listUtil.listAddCommunityList(classiList, communityService.listSearch(cri));
		listUtil.listAddPortalList(classiList, portalService.listSearch(cri));
		listUtil.listAddMediaList(classiList, mediaService.listSearch(cri));
		
		cri.setPerPageNum(cri.getPerPageNum()*3);
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
		logger.info("pageMaker: " + pageMaker);
		model.addAttribute("pageMaker", pageMaker);
		
		// 회사 선택에 따른 키워드 재추출
		if(cri.getCompany() != null) {
			if(cri.getCompany().isEmpty() == false) {
			
				UserVO userVO  = userService.viewByName(cri.getCompany());
				logger.info("userVO: " + userVO);
			    logger.info("keywordList: " + keywordService.listByUser(userVO.getUser_idx()));
				model.addAttribute("modelKeywordList", keywordService.listByUser(
						userService.viewByName(cri.getCompany()).getUser_idx()));
			}
		}
		
		
		// 리스트 정렬
		ExtractComparator comparator = new ExtractComparator();
		Collections.sort(classiList, comparator);
		
		
		// 리스트 회사 추가
		keywordService.viewByKeyword(classiList);
		
		model.addAttribute("classiList", classiList);

		//excelList = classiList;
	}*/
	
	@ResponseBody
	@GetMapping("/excel")
	public ModelAndView excelGET(@ModelAttribute("cri") ModelAndView model, ExcelView excelView, SearchCriteria cri) throws SQLException {
		
		if(cri.getKeyword() == "" || "undefined".equals(cri.getKeyword()))  {
			logger.info("keyword is null");
			cri.setKeyword(null);
			
		} 
		if(cri.getSelectKey() == "" || "키워드".equals(cri.getSelectKey()) ) {
			logger.info("selectKey is null");
			cri.setSelectKey(null);
		}
		
		if("undefined".equals(cri.getStartDate()) || "undefined".equals(cri.getEndDate())
				|| cri.getStartDate() == "" || cri.getEndDate() == ""){
			cri.setStartDate(null);
			cri.setEndDate(null);
		
		} 
		if(cri.getStartDate() != null && cri.getEndDate() != null) {
			logger.info("not null");
			logger.info(cri.getStartDate());
			logger.info(cri.getEndDate());
			if(cri.getStartDate().indexOf("00:00:00") < 0 && cri.getEndDate().indexOf("23:59:59") < 0){ 
				cri.setStartDate(cri.getStartDate() + " 00:00:00"); 
				cri.setEndDate(cri.getEndDate() + " 23:59:59"); 
			}
		}
		if(cri.getCompany() != null) {
			if(cri.getCompany().isEmpty()) {
				cri.setCompany(null);
			}
		}
		
		if(cri.getCompany() == null || cri.getCompany().equals("회사")) {
			logger.info(SecurityContextHolder.getContext().getAuthentication().getName().toString());
			UserVO vo = userService.viewById(SecurityContextHolder.getContext().getAuthentication().getName());
			
			if(!vo.getUser_name().equals("union")) {
			cri.setCompany(vo.getUser_name());
			
			}else {
				cri.setCompany(null);
			}
		}
		if(cri.getTextType() != null) {
			if(cri.getTextType().equals("undefined") || cri.getTextType().equals("분류") || cri.getTextType().isEmpty()) {
				cri.setTextType(null);
			}
		}
		
		logger.info("cri: " + cri);

		List<ExtractVO> classiList = new ArrayList<ExtractVO>();
		ListUtil listUtil = new ListUtil();
		
		
		listUtil.listAddCommunityList(classiList, communityService.listAllEx(cri));
		listUtil.listAddPortalList(classiList, portalService.listAllEx(cri));
		listUtil.listAddMediaList(classiList, mediaService.listAllEx(cri));
		
		ExtractComparator comparator = new ExtractComparator();
		Collections.sort(classiList, comparator);
		
		
		model.addObject("list", classiList);
		model.setView(excelView);
		
		return model;
	}
	
	
	/*@ResponseBody
	@PostMapping("/insert")
	public void insertPOST(String keyword, String textType, String domain,
				String domainType, String board_number, String title,
				String content, String writer, String writerIP,
				String writeDate, String url, String thumbnail) {
		
		logger.info("insert called....");
		
		logger.info(keyword + textType + domain + domainType + board_number + title + content + writer + writerIP + writeDate
				+ url + thumbnail);
		
		try {
			
			if(domain.equals("sns")) {
				SNSVO vo = new SNSVO();
				vo.setSns_name(domainType);
				vo.setSns_title(title);
				vo.setSns_content(content);
				vo.setWriteDate(writeDate);
				vo.setSns_writer(writer);
				vo.setKeyword(keyword);
				vo.setTextType(textType);
				vo.setUrl(url);
				vo.setThumbnail(thumbnail);
				
				snsService.regist(vo);
				
			}else if(domain.equals("portal")) {
				PortalVO vo = new PortalVO();
				vo.setPortal_name(domainType);
				vo.setPortal_title(title);
				vo.setWriteDate(writeDate);
				vo.setKeyword(keyword);
				vo.setTextType(textType);
				vo.setUrl(url);
				vo.setThumbnail(thumbnail);
				vo.setDeviceType(1);
				vo.setKeyword_type("수동");
				
				portalService.regist(vo);
				
			}else if(domain.equals("media")) {
				MediaVO vo = new MediaVO();
				vo.setMedia_name(domainType);
				vo.setMedia_title(title);
				vo.setMedia_content(content);
				vo.setWriteDate(writeDate);
				vo.setReporter_name(writer);
				vo.setKeyword(keyword);
				vo.setTextType(textType);
				vo.setUrl(url);
				vo.setThumbnail(thumbnail);
				
				mediaService.regist(vo);
				
			}else if(domain.equals("community")) {
				CommunityVO vo = new CommunityVO();
				vo.setCommunity_name(domainType);
				vo.setCommunity_title(title);
				vo.setCommunity_content(content);
				vo.setCommunity_writer(writer);
				vo.setCommunity_writer_IP(writerIP);
				vo.setWriteDate(writeDate);
				vo.setKeyword(keyword);
				vo.setTextType(textType);
				vo.setUrl(url);
				vo.setThumbnail(thumbnail);
				
				
				communityService.regist(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		
	}*/
	
	
	
	@ResponseBody
	@PostMapping("modify")
	public String modifyPOST(Integer idx, String table, String textType) throws SQLException {
		logger.info("insertPOST called....");
		
		logger.info("idx: " + idx);
		logger.info("table: " + table);
		logger.info("textType: " + textType );
		
		if("sns".equals(table)) {
			logger.info("sns");
			SNSVO vo = new SNSVO();
			vo.setSns_idx(idx);
			vo.setTextType(textType);
			
			snsService.modifyTextType(vo);
			
		}else if("media".equals(table) ){
			logger.info("media");
			MediaVO vo = new MediaVO();
			vo.setMedia_idx(idx);
			vo.setTextType(textType);
			
			mediaService.modifyType(vo);
			
		}else if ("portal".equals(table)) {
			logger.info("portal");
			PortalVO vo = new PortalVO();
			vo.setPortal_idx(idx);
			vo.setTextType(textType);
			
			portalService.modifyType(vo);
			
		}else if ("community".equals(table)) {
			logger.info("community");
			CommunityVO vo = new CommunityVO();
			vo.setCommunity_idx(idx);
			vo.setTextType(textType);

			communityService.modifyType(vo);
		
		}
		
		return "success";
	}
	
	
	@ResponseBody
	@PostMapping("/remove")
	public String removePOST(Integer idx, String table) throws SQLException {
		logger.info("removePOST called....");
		
		logger.info("idx: " + idx);
		logger.info("table: " + table);
		
		if("sns".equals(table)) {
			logger.info("sns");
			snsService.remove(idx);
			
		}else if("media".equals(table) ){
			logger.info("media");
			mediaService.remove(idx);
			
		}else if ("portal".equals(table)) {
			logger.info("portal");
			portalService.remove(idx);
			
		}else if ("community".equals(table)) {
			logger.info("community");
			communityService.remove(idx);
		}
		
		return "success";
	}
	
	
	@Resource(name="imageView")
    private View imageView;

	@GetMapping("/imageDownload")
    public ModelAndView sample(SearchCriteria cri) throws SQLException {
		
		if(cri.getKeyword() == "" || "undefined".equals(cri.getKeyword()))  {
			logger.info("keyword is null");
			cri.setKeyword(null);
			
		} 
		if(cri.getSelectKey() == "" || "키워드".equals(cri.getSelectKey()) ) {
			logger.info("selectKey is null");
			cri.setSelectKey(null);
		}
		
		if("undefined".equals(cri.getStartDate()) || "undefined".equals(cri.getEndDate())
				|| cri.getStartDate() == "" || cri.getEndDate() == ""){
			cri.setStartDate(null);
			cri.setEndDate(null);
		
		} 
		if(cri.getStartDate() != null && cri.getEndDate() != null) {
			logger.info("not null");
			logger.info(cri.getStartDate());
			logger.info(cri.getEndDate());
			if(cri.getStartDate().indexOf("00:00:00") < 0 && cri.getEndDate().indexOf("23:59:59") < 0){ 
				cri.setStartDate(cri.getStartDate() + " 00:00:00"); 
				cri.setEndDate(cri.getEndDate() + " 23:59:59"); 
			}
		}
		if(cri.getCompany() != null) {
			if(cri.getCompany().isEmpty()) {
				cri.setCompany(null);
			}
		}
		
		if(cri.getCompany() == null || cri.getCompany().equals("회사")) {
			logger.info(SecurityContextHolder.getContext().getAuthentication().getName().toString());
			UserVO vo = userService.viewById(SecurityContextHolder.getContext().getAuthentication().getName());
			
			if(!vo.getUser_name().equals("union")) {
			cri.setCompany(vo.getUser_name());
			
			}else {
				cri.setCompany(null);
			}
		}
		if(cri.getTextType() != null) {
			if(cri.getTextType().equals("undefined") || cri.getTextType().equals("분류") || cri.getTextType().isEmpty()) {
				cri.setTextType(null);
			}
		}
		
		logger.info("cri: " + cri);
		
		List<ExtractVO> classiList = new ArrayList<ExtractVO>();
		ListUtil listUtil = new ListUtil();
		
		
		listUtil.listAddCommunityList(classiList, communityService.listAll(cri));
		listUtil.listAddPortalList(classiList, portalService.listAll(cri));
		listUtil.listAddMediaList(classiList, mediaService.listAll(cri));
		
		ExtractComparator comparator = new ExtractComparator();
		Collections.sort(classiList, comparator);
		
		List<String> fimeNames = new ArrayList<String>();
		
		for (ExtractVO vo : classiList) {
			String thumbnail = vo.getThumbnail();
			if(thumbnail != null && !thumbnail.isEmpty()) {
				fimeNames.add(thumbnail);
			}
		}
		
        ModelAndView mav = new ModelAndView();
        mav.setView(this.imageView);

        //File downloadFile = new File("downloadFile");
        List<File> downloadFiles = new ArrayList<File>();
        
        for (String fimeName : fimeNames) {
        	File downloadFile  = new File("D:\\img" + fimeName);
        	downloadFiles.add(downloadFile);
            
        }
        
        mav.addObject("downloadFile", downloadFiles);

        return mav;
        
	}
        
    public void copyInto(MultipartFile upload,HttpServletRequest re,CommunityVO vo){
    	
    	String paths = "D:\\";
            
            try {
               byte bytes[]=upload.getBytes();//내가올린파일에 정보를 가져올수 있다.
               String savaPath=paths+"img/"+upload.getOriginalFilename();
               File newFile=new File(savaPath);
               FileOutputStream fos=new FileOutputStream(newFile);//내가써야될 대상에 파일정보를 가지면서 실제파일을 저장
               fos.write(bytes);//내가올린 파일을 복사하는  코드
               fos.close();//파일 자원 반환(쓰면 반환해라)
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
        }
	}


