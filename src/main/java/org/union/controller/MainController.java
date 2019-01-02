package org.union.controller;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.union.domain.PageMakerFv;
import org.union.domain.SearchCriteria;
import org.union.domain.UserVO;
import org.union.service.CommunityService;
import org.union.service.KeywordService;
import org.union.service.MainService;
import org.union.service.MediaService;
import org.union.service.PortalService;
import org.union.service.SNSService;
import org.union.service.UserService;

@Controller
@RequestMapping("/main/*")
public class MainController {
	
	@Autowired
	private KeywordService keywordService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private CommunityService communityService;

	@Autowired
	private PortalService portalService;
	
	@Autowired
	private SNSService snsService;
	
	@Autowired
	private MainService mainService;
	
	private static Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@GetMapping("/main")
	public void mainGET(@ModelAttribute("cri") SearchCriteria cri, Model model, String selectKey, String startDate, String endDate) throws SQLException {
		logger.info("main called....");
		
		cri.setKeyword(null);
		cri.setTextType(null);
		
		/*if(cri.getSelectKey() == "" || "키워드".equals(cri.getSelectKey()) ) {
			logger.info("selectKey is null");
			cri.setSelectKey(null);
		}*/
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

		// 회사 선택에 따른 키워드 재추출
		if (cri.getCompany() != null) {	
			if (cri.getCompany().isEmpty() == false) {

				UserVO userVO = userService.viewByName(cri.getCompany());
				logger.info("userVO: " + userVO);
				logger.info("keywordList: " + keywordService.listByUser(userVO.getUser_idx()));
				model.addAttribute("modelKeywordList",
						keywordService.listByUser(userService.viewByName(cri.getCompany()).getUser_idx()));
			}
		}
		
		logger.info("cri: " + cri);
		
		model.addAttribute("portalCount", mainService.portalSearchCount(cri));
		model.addAttribute("communityCount", mainService.communitySearchCount(cri));
		model.addAttribute("snsCount", (mainService.snsSearchCount(cri)+mainService.youtubeTotalCount(cri)));
		model.addAttribute("mediaCount", mainService.mediaSearchCount(cri));
		model.addAttribute("replyCount", mainService.replySearchCount(cri));
		
		Integer mTotal = mainService.mediaSearchCount(cri);
		double mbadCnt = mainService.mediaBadSearchCount(cri);
		double mpersen = Math.round(mbadCnt / mTotal * 100);
		
		Integer cmTotal = (mainService.communitySearchCount(cri)+mainService.portalSearchCount(cri));
		double cmbadCnt = (mainService.communityBadSearchCount(cri)+mainService.portalBadSearchCount(cri));
		double cmpersen = Math.round(cmbadCnt / cmTotal * 100);
		
		Integer rTotal = mainService.replySearchCount(cri);
		double rbadCnt = mainService.replyBadSearchCount(cri);
		double rpersen = Math.round(rbadCnt / rTotal * 100);
		
		model.addAttribute("mpersen", mpersen);
		model.addAttribute("cmpersen", cmpersen);
		model.addAttribute("rpersen", rpersen);
		
		model.addAttribute("mediaTextType", mainService.mediaTextTypeCount(cri));
		model.addAttribute("writeTextType", mainService.writeTextTypeCount(cri));
		model.addAttribute("replyTextType", mainService.replyTextTypeCount(cri));
		
		model.addAttribute("selectKey", selectKey);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
	}
	
	@GetMapping("/detail")
	public void detailGET(@ModelAttribute("cri") SearchCriteria cri, Model model, String part, Integer total, String selectKey, String startDate, String endDate) throws SQLException {
		logger.info("detail called....");
		
		cri.setKeyword(null);
		
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

		// 회사 선택에 따른 키워드 재추출
		if (cri.getCompany() != null) {	
			if (cri.getCompany().isEmpty() == false) {

				UserVO userVO = userService.viewByName(cri.getCompany());
				logger.info("userVO: " + userVO);
				logger.info("keywordList: " + keywordService.listByUser(userVO.getUser_idx()));
				model.addAttribute("modelKeywordList",
						keywordService.listByUser(userService.viewByName(cri.getCompany()).getUser_idx()));
			}
		}
		
		if(cri.getTextType() != null) {
			if(cri.getTextType().equals("undefined") || cri.getTextType().equals("분류") || cri.getTextType().isEmpty()) {
				cri.setTextType(null);
			}
		}
		
		logger.info("cri: " + cri);
		
		model.addAttribute("selectKey", selectKey);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		
		if(part.equals("media")) {
			
			model.addAttribute("detailMediaList", mainService.detailMediaList(cri));
			model.addAttribute("part", "media");
			model.addAttribute("textType", cri.getTextType());
			
			Integer totalCount = mainService.mediaSearchCount(cri);
			model.addAttribute("total", totalCount);
			
			PageMakerFv pageMakerFv = new PageMakerFv();
			
			pageMakerFv.setCri(cri);
			pageMakerFv.setTotalCount(totalCount);
			
			model.addAttribute("pageMaker", pageMakerFv);
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
			
		}else if(part.equals("write")) {
			
			model.addAttribute("detailWriteList", mainService.detailwriteList(cri));
			model.addAttribute("part", "write");
			model.addAttribute("textType", cri.getTextType());
			
			Integer totalCount = mainService.detailwriteCount(cri);
			model.addAttribute("total", totalCount);
			
			PageMakerFv pageMakerFv = new PageMakerFv();
			
			pageMakerFv.setCri(cri);
			pageMakerFv.setTotalCount(totalCount);
			
			model.addAttribute("pageMaker", pageMakerFv);
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
			
		}else if(part.equals("reply")) {
			
			model.addAttribute("detailReplyList", mainService.detailReplyList(cri));
			model.addAttribute("part", "reply");
			model.addAttribute("textType", cri.getTextType());
			
			Integer totalCount = mainService.replySearchCount(cri);
			model.addAttribute("total", totalCount);
			
			PageMakerFv pageMakerFv = new PageMakerFv();
			
			pageMakerFv.setCri(cri);
			pageMakerFv.setTotalCount(totalCount);
			
			model.addAttribute("pageMaker", pageMakerFv);
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
			
		}
		
		
	}

}
