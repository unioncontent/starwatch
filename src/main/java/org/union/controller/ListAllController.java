package org.union.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
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
@RequestMapping("/listAll/*")
public class ListAllController {

	
	@Autowired
	private CommunityService communityService;
	
	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private PortalService portalService;
	
	@Autowired
	private SNSService snsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private KeywordService keywordService;
	
	private static Logger logger = LoggerFactory.getLogger(ListAllController.class);
	
	
	@GetMapping("/listAll")
	public void listAllGET(@ModelAttribute("cri") SearchCriteria cri, Model model) throws SQLException {
		logger.info("listAllGET called....");
		
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
		if(cri.getTextType() != null) {
			if(cri.getTextType().equals("undefined") || cri.getTextType().equals("분류") || cri.getTextType().isEmpty()) {
				cri.setTextType(null);
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
		
		model.addAttribute("extractList", communityService.allPageallList(cri));
		
		PageMaker pageMaker = new PageMaker();
		
		logger.info("cri: " + cri);
		
		Integer totalCount = communityService.allPageallCount(cri);
		
		logger.info("totalCount: " + totalCount);
		model.addAttribute("totalCount", totalCount);
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
		logger.info("pageMaker: " + pageMaker);
		model.addAttribute("pageMaker", pageMaker);
	}
	
	@GetMapping("/listAllTotal")
	public void listAllTtotalGET(@ModelAttribute("cri") SearchCriteria cri, Model model) throws SQLException {
		
		logger.info("listAllTtotalGET called....");
		
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
		if(cri.getTextType() != null) {
			if(cri.getTextType().equals("undefined") || cri.getTextType().equals("분류") || cri.getTextType().isEmpty()) {
				cri.setTextType(null);
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
		
		model.addAttribute("extractList", communityService.totalallPageallList(cri));
		
		PageMaker pageMaker = new PageMaker();
		
		logger.info("cri: " + cri);
		
		Integer totalCount = communityService.totalallPageallCount(cri);

		model.addAttribute("totalCount", totalCount);
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
		logger.info("pageMaker: " + pageMaker);
		model.addAttribute("pageMaker", pageMaker);
	}
	
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

		List<ExtractVO> extractList = new ArrayList<ExtractVO>();
		ListUtil listUtil = new ListUtil();
		
		
		listUtil.listAddCommunityList(extractList, communityService.allPage(cri));
		listUtil.listAddPortalList(extractList, portalService.allPage(cri));
		listUtil.listAddMediaList(extractList, mediaService.allPage(cri));
		listUtil.listAddCommunityList(extractList, communityService.totalAllPageex(cri));
		listUtil.listAddPortalList(extractList, portalService.totalAllPageex(cri));
		listUtil.listAddMediaList(extractList, mediaService.totalAllPageex(cri));
		
		ExtractComparator comparator = new ExtractComparator();
		Collections.sort(extractList, comparator);
		
		
		model.addObject("list", extractList);
		model.setView(excelView);
		
		return model;
	}
	
	@ResponseBody
	@PostMapping("insert")
	public String insertPOST(Integer idx, String table, String textType) throws SQLException {
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
}
