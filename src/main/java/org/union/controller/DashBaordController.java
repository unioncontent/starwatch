package org.union.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
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
import org.union.domain.CalendarVO;
import org.union.domain.ExtractVO;
import org.union.domain.GraphVO;
import org.union.domain.SearchCriteria;
import org.union.domain.UserVO;
import org.union.service.CalendarService;
import org.union.service.CommunityService;
import org.union.service.KeywordService;
import org.union.service.MediaService;
import org.union.service.PortalService;
import org.union.service.RelationService;
import org.union.service.SNSService;
import org.union.service.UserService;
import org.union.util.ExtractComparator;
import org.union.util.ListUtil;

@Controller
@RequestMapping("/dashBoard/*")
public class DashBaordController {

	
	@Autowired
	private PortalService portalService;
	
	@Autowired
	private SNSService snsService;
	
	@Autowired
	private CommunityService communityService;
	
	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private KeywordService keywordService;
	
	@Autowired
	private RelationService relationService;
	
	@Autowired
	private CalendarService calendarService;
	
	@Autowired
	private UserService userService;
	
	private static Logger logger = LoggerFactory.getLogger(DashBaordController.class);
	
	@GetMapping("/dashBoard_main")
	public void dashBoard_mainGET(@ModelAttribute("cri") SearchCriteria cri, Model model) throws SQLException {
		logger.info("dashBoard_main called....");
		
		cri.setKeyword(null);
		cri.setTextType(null);
		
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
	}
	
	@GetMapping("/dashBoard_re")
	public void dashBoard_re_mainGET(@ModelAttribute("cri") SearchCriteria cri, Model model) throws SQLException {
		logger.info("dashBoard_re called....");
		
		cri.setKeyword(null);
		cri.setTextType(null);
		
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String current = sdf.format(new Date());
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1); // 오늘날짜로부터 -1
		String current2 = sdf.format(cal.getTime());
		logger.info("current: " + current);
		
		model.addAttribute("headlineList", mediaService.headlineList(cri));
		
		model.addAttribute("mediaTotalcnt", mediaService.mediaTotalcnt(cri));
		model.addAttribute("replyTotalcnt", mediaService.replyTotalcnt(cri));
		model.addAttribute("snsTotalcount", snsService.snsTotalcount(cri));
		model.addAttribute("scoreTotalcnt", portalService.scoreTotalcnt(cri));
		model.addAttribute("mailList", mediaService.mailList(cri));
		
		cri.setStartDate(current + " 00:00:00");
		cri.setEndDate(current + " 23:59:59");
		model.addAttribute("mediaTextcnt", mediaService.mediaTotalcnt(cri));
		model.addAttribute("mediaTextcnt2", mediaService.mediaTextcnt2(cri));
		model.addAttribute("portalTextcnt", portalService.portalTextcnt(cri));
		model.addAttribute("portalTextcnt2", portalService.portalTextcnt2(cri));
		model.addAttribute("communityTextcnt", communityService.communityTextcnt(cri));
		model.addAttribute("communityTextcnt2", communityService.communityTextcnt2(cri));
		
		cri.setSns_name("facebook");
		cri.setStartDate(current + " 00:00:00");
		cri.setEndDate(current + " 23:59:59");
		model.addAttribute("facebookCount", snsService.reportSnsCount(cri));
		
		cri.setSns_name("twitter");
		cri.setStartDate(current + " 00:00:00");
		cri.setEndDate(current + " 23:59:59");
		model.addAttribute("twitterCount", snsService.reportSnsCount(cri));
		
		cri.setSns_name("instagram");
		cri.setStartDate(current + " 00:00:00");
		cri.setEndDate(current + " 23:59:59");
		model.addAttribute("instagramCount", snsService.reportSnsCount(cri));
		
		cri.setSns_name("youtube");
		cri.setStartDate(current2 + " 00:00:00");
		cri.setEndDate(current2 + " 23:59:59");
		model.addAttribute("youtubeCount", snsService.reportSnsCount(cri));
		
	}
	
	@GetMapping("/dashBoard_popUp")
	public void dashBoard_popupGET(@ModelAttribute("cri") SearchCriteria cri, Model model, String part, String company, String selectKey, String emailDate) throws SQLException {
		logger.info("dashBoard_popup called....");
		
		cri.setKeyword(null);
		cri.setTextType(null);
		
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String current = sdf.format(new Date());
		logger.info("current: " + current);
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1); // 오늘날짜로부터 -1
		String current2 = sdf.format(cal.getTime());
		logger.info("current2: " + current2);
		
		cri.setStartDate(current + " 00:00:00");
		cri.setEndDate(current + " 23:59:59");
		
		if(part.equals("기사")) {
			model.addAttribute("mediaList", mediaService.dashMediaMatch(cri));
			model.addAttribute("part", part);
		}else if(part.equals("댓글")) {
			model.addAttribute("replyList", mediaService.replyAllPage(cri));
			model.addAttribute("part", part);
		}else if(part.equals("페이스북")) {
			cri.setPortal_name("facebook");
			model.addAttribute("snsList", snsService.listAll(cri));
			model.addAttribute("part", part);
		}else if(part.equals("인스타그램")) {
			cri.setPortal_name("instagram");
			model.addAttribute("snsList", snsService.listAll(cri));
			model.addAttribute("part", part);
		}else if(part.equals("트위터")) {
			cri.setPortal_name("twitter");
			model.addAttribute("snsList", snsService.listAll(cri));
			model.addAttribute("part", part);
		}else if(part.equals("유튜브")) {
			cri.setStartDate(current2 + " 00:00:00");
			cri.setEndDate(current2 + " 23:59:59");
			cri.setPortal_name("youtube");
			model.addAttribute("snsList2", snsService.listAll(cri));
			model.addAttribute("part", part);
		}else if(part.equals("평점")) {
			model.addAttribute("scoreList", portalService.getScoreExcelList(cri));
			model.addAttribute("part", part);
		}else if(part.equals("분류글")) {
			
			List<ExtractVO> classiList = new ArrayList<ExtractVO>();
			ListUtil listUtil = new ListUtil();
			
			listUtil.listAddCommunityList(classiList, communityService.dashListAll(cri));
			listUtil.listAddPortalList(classiList, portalService.dashListAll(cri));
			listUtil.listAddMediaList(classiList, mediaService.dashListAll(cri));
			
			// 리스트 정렬
			ExtractComparator comparator = new ExtractComparator();
			Collections.sort(classiList, comparator);
			
			model.addAttribute("classiList", classiList);
			model.addAttribute("part", part);
		}else if(part.equals("나쁜글")) {
			cri.setTextType("나쁜글");
			
			List<ExtractVO> classiList = new ArrayList<ExtractVO>();
			ListUtil listUtil = new ListUtil();
			
			listUtil.listAddCommunityList(classiList, communityService.dashListAll(cri));
			listUtil.listAddPortalList(classiList, portalService.dashListAll(cri));
			listUtil.listAddMediaList(classiList, mediaService.dashListAll(cri));
			
			// 리스트 정렬
			ExtractComparator comparator = new ExtractComparator();
			Collections.sort(classiList, comparator);
			
			model.addAttribute("classiList2", classiList);
			model.addAttribute("part", part);
		}else if(part.equals("이메일")) {
			cri.setStartDate(emailDate + " 00:00:00");
			cri.setEndDate(emailDate + " 23:59:59");
			logger.info("mail:" + cri.getStartDate());
			logger.info("mail:" + cri.getEndDate());
			model.addAttribute("emailList", mediaService.mailMatch(cri));
			model.addAttribute("part", part);
		}
	
	}
	
	/*@GetMapping("/dashBoard")
	public void dashBoardGET(@ModelAttribute("cri") SearchCriteria cri, Model model) {
		logger.info("dashBaordGET called....");
		
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
		
		model.addAttribute("blogCount", portalService.toDayCount("blog"));
		model.addAttribute("cafeCount", portalService.toDayCount("cafe"));
		model.addAttribute("kintipCount", portalService.toDayCount("kintip"));
		model.addAttribute("webdocCount", portalService.toDayCount("webdoc"));
		model.addAttribute("mediaCount", portalService.toDayCount("media"));
		
		model.addAttribute("keywordCount", keywordService.listAll().size());
		model.addAttribute("relationCount", portalService.toDayCount("score"));
		
		model.addAttribute("portalCount", portalService.yesterdayCount());
		model.addAttribute("mediaCount", mediaService.yesterdayCount());
		model.addAttribute("facebookCount", snsService.yesterdayCount("facebook"));
		model.addAttribute("twitterCount", snsService.yesterdayCount("twitter"));
		model.addAttribute("instagramCount", snsService.yesterdayCount("instagram"));
	}*/
	
	@GetMapping("/showDashBoard")
	public void showDashBoardGET(@ModelAttribute("cri") SearchCriteria cri, Model model) throws SQLException {
		logger.info("showDashBoardGET called....");
		
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
		model.addAttribute("showboxblogCount", portalService.showboxToDayCount("blog"));
		model.addAttribute("showboxcafeCount", portalService.showboxToDayCount("cafe"));
		model.addAttribute("showboxkintipCount", portalService.showboxToDayCount("kintip"));
		model.addAttribute("showboxwebdocCount", portalService.showboxToDayCount("webdoc"));
		model.addAttribute("showboxmediaCount", portalService.showboxToDayCount("media"));
		
		model.addAttribute("showboxkeywordCount", keywordService.showboxListAll().size());
		model.addAttribute("showboxrelationCount", portalService.showboxToDayCount("score"));
		
		model.addAttribute("showboxportalCount", portalService.showboxYesterdayCount());
		model.addAttribute("showboxmediaCount", mediaService.showboxYesterdayCount());
		model.addAttribute("showboxfacebookCount", snsService.showboxYesterdayCount("facebook"));
		model.addAttribute("showboxtwitterCount", snsService.showboxYesterdayCount("twitter"));
		model.addAttribute("showboxinstagramCount", snsService.showboxYesterdayCount("instagram"));
		
		
	}
	
	@ResponseBody
	@PostMapping("/graph")
	public List<GraphVO> graphPOST(Model model, String success) throws SQLException {
		logger.info("graphPOST called....");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		
		String current = sdf.format(new Date());
		logger.info("current: " + current);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		List<GraphVO> graphList = new ArrayList<GraphVO>();
		
		
		logger.info("cal.getTime: " + cal.getTime());

		for(int i = 0; i < 24; i++) {
			GraphVO graphVO = new GraphVO();
			
			graphVO.setWriteDate(sdf.format(cal.getTime()) + ":00:00");
			graphVO.setType1(portalService.countAll(cal.getTime()));
			graphVO.setType2(communityService.countAll(cal.getTime()));
			graphVO.setType3(snsService.countAll(cal.getTime()));
			
			graphList.add(graphVO);
			
			cal.add(Calendar.HOUR, -1);
			
		}
		

		logger.info("graphList: " + graphList);
		return graphList;
	}
	
	@ResponseBody
	@PostMapping("/showGraph")
	public List<GraphVO> showGraphPOST(Model model, String success) throws SQLException {
		logger.info("showgraphPOST called....");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		
		String current = sdf.format(new Date());
		logger.info("current: " + current);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		List<GraphVO> graphList = new ArrayList<GraphVO>();
		
		
		logger.info("cal.getTime: " + cal.getTime());

		for(int i = 0; i < 24; i++) {
			GraphVO graphVO = new GraphVO();
			
			graphVO.setWriteDate(sdf.format(cal.getTime()) + ":00:00");
			graphVO.setType1(portalService.showboxCountAll(cal.getTime()));
			graphVO.setType2(communityService.showboxCountAll(cal.getTime()));
			graphVO.setType3(snsService.showboxCountAll(cal.getTime()));
			
			graphList.add(graphVO);
			
			cal.add(Calendar.HOUR, -1);
			
		}
		

		logger.info("graphList: " + graphList);
		return graphList;
	}
	
	@ResponseBody
	@PostMapping("/graph_re")
	public List<GraphVO> graph_rePOST(Model model, String success, @ModelAttribute("cri") SearchCriteria cri, String selectKey) throws SQLException {
		logger.info("graph_rePOST called....");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd HH");
		
		String current = sdf.format(new Date());
		logger.info("current: " + current);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		List<GraphVO> graphList = new ArrayList<GraphVO>();
		
		
		logger.info("cal.getTime: " + cal.getTime());

		for(int i = 0; i < 24; i++) {
			GraphVO graphVO = new GraphVO();
			
			cri.setDate(cal.getTime());
			cri.setSelectKey(selectKey);
			
			graphVO.setWriteDate(sdf2.format(cal.getTime()) + ":00:00");
			graphVO.setType1(mediaService.mediaCountAll(cri));
			
			graphList.add(graphVO);
			
			cal.add(Calendar.HOUR, -1);
			
		}
		
		Collections.reverse(graphList);
		logger.info("graphList: " + graphList);
		return graphList;
	}
	
	@ResponseBody
	@PostMapping("listDate")
	public List<CalendarVO> listDatePOST(String date) throws SQLException{
		logger.info("listDatePost called....");
		
		List<CalendarVO> calList = calendarService.listDate();
		//List<CalendarVO> resultList = new ArrayList<CalendarVO>();
		
		logger.info("calList: " + calList);
		
		
		
		return calList;
	}
	
	@ResponseBody
	@PostMapping("insert")
	public String insertPOST(String title, String date) throws SQLException {
		logger.info("insertPOST called....");
		
		date = date.replaceAll("/", "-");
		
		logger.info("title: " + title);
		logger.info("date: " + date);
		
		CalendarVO calendarVO = new CalendarVO();
		
		calendarVO.setCalendar_title(title);
		calendarVO.setCalendar_date(date);
		
		calendarService.insert(calendarVO);
		
		return "success";
	}
	
	@ResponseBody
	@PostMapping("delete")
	public String deletePOST(String title) throws SQLException {
		logger.info("deletePOST called....");
		
		calendarService.remove(title);
		
		return "success";
		
	}
}
