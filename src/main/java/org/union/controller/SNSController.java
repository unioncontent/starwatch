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
import org.springframework.web.servlet.ModelAndView;
import org.union.domain.ExtractVO;
import org.union.domain.GraphVO;
import org.union.domain.PageMaker;
import org.union.domain.SNSVO;
import org.union.domain.SearchCriteria;
import org.union.domain.UserVO;
import org.union.service.KeywordService;
import org.union.service.SNSService;
import org.union.service.UserService;
import org.union.util.ExcelView;
import org.union.util.ExcelViewM;
import org.union.util.ExtractComparator;
import org.union.util.ListUtil;

@Controller
@RequestMapping("/sns/*")
public class SNSController {

	
	@Autowired
	private SNSService snsService;
	
	@Autowired
	private KeywordService keywordService;
	
	@Autowired
	private UserService userService;
	
	private static Logger logger = LoggerFactory.getLogger(SNSController.class);
	
	
	@GetMapping("/facebook")
	public void facebookGET(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception{
		logger.info("facebookGET called....");
		
		if(cri.getKeyword() != null) {
			if(cri.getKeyword().isEmpty() || cri.getKeyword().equals("undefined")) {
				cri.setKeyword(null);
			}
		}
		if(cri.getSelectKey() != null) {
			if(cri.getSelectKey().isEmpty() || cri.getSelectKey().equals("키워드")){
				cri.setSelectKey(null);
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
			cri.setTextType(null);
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
		if(cri.getCompany() != null) {
			if(cri.getCompany().isEmpty() == false) {
					
				UserVO userVO  = userService.viewByName(cri.getCompany());
				logger.info("userVO: " + userVO);
					logger.info("keywordList: " + keywordService.listByUser(userVO.getUser_idx()));
					model.addAttribute("modelKeywordList", keywordService.listByUser(
					userService.viewByName(cri.getCompany()).getUser_idx()));
			}
		}
		
		logger.info("cri : " + cri);
		
		PageMaker pageMaker = new PageMaker();
		
		Integer totalCount = snsService.snsFacebookTotalCount(cri);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		model.addAttribute("pageMaker", pageMaker);
		logger.info("pageMaker: " + pageMaker);
		
		List<SNSVO> list = new ArrayList<SNSVO>();
		list = snsService.facebookList(cri);
		model.addAttribute("facebookList", list);
		
		
	}
	
	@GetMapping("/instagram")
	public void instagramGET(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception{
		logger.info("instagramGET called....");
		
		if(cri.getKeyword() != null) {
			if(cri.getKeyword().isEmpty() || cri.getKeyword().equals("undefined")) {
				cri.setKeyword(null);
			}
		}
		if(cri.getSelectKey() != null) {
			if(cri.getSelectKey().isEmpty() || cri.getSelectKey().equals("키워드")){
				cri.setSelectKey(null);
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
			cri.setTextType(null);
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
		if(cri.getCompany() != null) {
			if(cri.getCompany().isEmpty() == false) {
					
				UserVO userVO  = userService.viewByName(cri.getCompany());
				logger.info("userVO: " + userVO);
					logger.info("keywordList: " + keywordService.listByUser(userVO.getUser_idx()));
					model.addAttribute("modelKeywordList", keywordService.listByUser(
					userService.viewByName(cri.getCompany()).getUser_idx()));
			}
		}
		
		logger.info("cri : " + cri);
		
		PageMaker pageMaker = new PageMaker();
		
		Integer totalCount = snsService.instaTotalCount(cri);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		model.addAttribute("pageMaker", pageMaker);
		logger.info("pageMaker: " + pageMaker);
		
		List<SNSVO> list = new ArrayList<SNSVO>();
		list = snsService.instaList(cri);
		model.addAttribute("instagramList", list);
		
	}
	
	
	@GetMapping("/twitter")
	public void twitterGET(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception{
		logger.info("twitterGET called....");
		
		if(cri.getKeyword() != null) {
			if(cri.getKeyword().isEmpty() || cri.getKeyword().equals("undefined")) {
				cri.setKeyword(null);
			}
		}
		if(cri.getSelectKey() != null) {
			if(cri.getSelectKey().isEmpty() || cri.getSelectKey().equals("키워드")){
				cri.setSelectKey(null);
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
			cri.setTextType(null);
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
		if(cri.getCompany() != null) {
			if(cri.getCompany().isEmpty() == false) {
					
				UserVO userVO  = userService.viewByName(cri.getCompany());
				logger.info("userVO: " + userVO);
					logger.info("keywordList: " + keywordService.listByUser(userVO.getUser_idx()));
					model.addAttribute("modelKeywordList", keywordService.listByUser(
					userService.viewByName(cri.getCompany()).getUser_idx()));
			}
		}
		
		logger.info("cri : " + cri);
		
		PageMaker pageMaker = new PageMaker();
		
		Integer totalCount = snsService.twitterTotalCount(cri);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		model.addAttribute("pageMaker", pageMaker);
		logger.info("pageMaker: " + pageMaker);
		
		List<SNSVO> list = new ArrayList<SNSVO>();
		list = snsService.twitterList(cri);
		model.addAttribute("twitterList", list);
		
	}
	
	@GetMapping("/youtube")
	public void youtubeGET(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception{
		logger.info("youtubeGET called....");
		
		if(cri.getKeyword() != null) {
			if(cri.getKeyword().isEmpty() || cri.getKeyword().equals("undefined")) {
				cri.setKeyword(null);
			}
		}
		if(cri.getSelectKey() != null) {
			if(cri.getSelectKey().isEmpty() || cri.getSelectKey().equals("키워드")){
				cri.setSelectKey(null);
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
			cri.setTextType(null);
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
		if(cri.getCompany() != null) {
			if(cri.getCompany().isEmpty() == false) {
					
				UserVO userVO  = userService.viewByName(cri.getCompany());
				logger.info("userVO: " + userVO);
					logger.info("keywordList: " + keywordService.listByUser(userVO.getUser_idx()));
					model.addAttribute("modelKeywordList", keywordService.listByUser(
					userService.viewByName(cri.getCompany()).getUser_idx()));
			}
		}
		
		logger.info("cri : " + cri);
		
		PageMaker pageMaker = new PageMaker();
		
		Integer totalCount = snsService.youtubeTotalCount(cri);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		model.addAttribute("pageMaker", pageMaker);
		logger.info("pageMaker: " + pageMaker);
		
		List<SNSVO> list = new ArrayList<SNSVO>();
		list = snsService.youtubeList(cri);
		model.addAttribute("youtubeList", list);
		
	}
	
	/*@PostMapping("/graph")
	@ResponseBody
	public List<GraphVO> graphPOST(SearchCriteria cri) throws Exception{
		logger.info("grpahPOST called....");
		
		if(cri.getKeyword() != null) {
			if(cri.getKeyword().isEmpty() || cri.getKeyword().equals("undefined")) {
				cri.setKeyword(null);
			}
		}
		if(cri.getSelectKey() != null) {
			if(cri.getSelectKey().isEmpty() || cri.getSelectKey().equals("키워드")){
				cri.setSelectKey(null);
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
			cri.setTextType(null);
		}
		if("undefined".equals(cri.getStartDate()) || "undefined".equals(cri.getEndDate())
				|| cri.getStartDate() == "" || cri.getEndDate() == ""){
			cri.setStartDate(null);
			cri.setEndDate(null);
		
		}
		
		String startDate = cri.getStartDate();
		
		if(cri.getStartDate() != null && cri.getEndDate() != null) {
			logger.info("not null");
			logger.info(cri.getStartDate());
			logger.info(cri.getEndDate());
			startDate = cri.getStartDate();
			if(cri.getStartDate().indexOf("00:00:00") < 0 && cri.getEndDate().indexOf("23:59:59") < 0){ 
				startDate = cri.getStartDate();
				cri.setStartDate(cri.getStartDate() + " 00:00:00"); 
				cri.setEndDate(cri.getEndDate() + " 23:59:59"); 
				
			}
		}
		
		logger.info("cri: " + cri);
		logger.info("startDate: " + startDate);
		
		List<SNSVO> list= snsService.getDateCount(cri);

		logger.info("list: " + list.size());
		
		List<GraphVO> graphList = new ArrayList<GraphVO>();
		
		int like =0;
		int share =0;
		int reply =0;
		
		GraphVO graphData = new GraphVO();
		for(int i = 0; i < list.size(); i++) {
			
			// equlas
			if((startDate.equals(list.get(i).getWriteDate()))) {
				graphData.setWriteDate(startDate);
				like = like + list.get(i).getLike_cnt();
				share =share + list.get(i).getShare_cnt();
				reply =reply + list.get(i).getReply_cnt();
				
			}else {
				graphData.setLikeCount(like);
				graphData.setShareCount(share);
				graphData.setReplyCount(reply);
				
				// 데이터 부족할 때
				if(graphData.getWriteDate() != null) {
					graphList.add(graphData);
				}
				
				graphData = new GraphVO();
				
				like = 0;
				share = 0;
				reply = 0;
				
				startDate = list.get(i).getWriteDate();
			}
			
			// 마지막 날짜
			if(i == list.size()-1) {
				
				graphData.setLikeCount(like);
				graphData.setShareCount(share);
				graphData.setReplyCount(reply);
				
				graphList.add(graphData);
			}
			
		}// end for
		
		//logger.info("graphList: " + graphList);
		if(graphList.size() > 1) {
			if(graphList.get(graphList.size()-1).getWriteDate() == null) {
				logger.info("index last is null");
				graphList.remove(graphList.size()-1);
			}
		}
		
		return graphList;
		
	}*/
	
	@ResponseBody
	@PostMapping("/graph")
	public List<GraphVO> graphPOST(Model model, String success, @ModelAttribute("cri") SearchCriteria cri,String portal_name, String company, String selectKey) throws SQLException {
		logger.info("grpahPOST called....");
		
		if (cri.getSelectKey() == "" || "키워드".equals(cri.getSelectKey())) {
		    logger.info("selectKey is null");
		    cri.setSelectKey(null);
		}
		if (cri.getCompany() == null || cri.getCompany().equals("회사")) {
		    logger.info(SecurityContextHolder.getContext().getAuthentication().getName().toString());
		    UserVO vo = userService.viewById(SecurityContextHolder.getContext().getAuthentication().getName());

		    if (!vo.getUser_name().equals("union")) {
			cri.setCompany(vo.getUser_name());

		    } else {
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd HH:00:00");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
		
		String current = sdf.format(new Date());
		logger.info("current: " + current);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		List<GraphVO> graphList = new ArrayList<GraphVO>();
		
		logger.info("cal.getTime: " + cal.getTime());
		
		if(portal_name.equals("youtube")) {
			
			for(int i = 0; i < 30; i++) {
				
				GraphVO graphData = new GraphVO();
				
				cri.setPortal_name(portal_name);
				String startDate = sdf3.format(cal.getTime());
				String endDate = sdf3.format(cal.getTime());
				cri.setStartDate(startDate + " 00:00:00");
				cri.setEndDate(endDate + " 23:59:59");
				
				graphData.setWriteDate(sdf3.format(cal.getTime()));
				graphData.setLikeCount(snsService.YlikeGetDateCount(cri));
				graphData.setShareCount(snsService.YviewGetDateCount(cri));
				graphData.setReplyCount(snsService.YreplyGetDateCount(cri));
				
				
				graphList.add(graphData);
				
				cal.add(Calendar.DATE, -1);
		}// end for
				
		}else {
		
		for(int i = 0; i < 24; i++) {
			
				GraphVO graphData = new GraphVO();
				
				cri.setPortal_name(portal_name);
				cri.setWriteDate(sdf.format(cal.getTime()) + ":00:00");
				
				graphData.setWriteDate(sdf2.format(cal.getTime()));
				graphData.setLikeCount(snsService.likeGetDateCount(cri));
				graphData.setShareCount(snsService.shareGetDateCount(cri));
				graphData.setReplyCount(snsService.replyGetDateCount(cri));
				
				
				graphList.add(graphData);
				
				cal.add(Calendar.HOUR, -1);
		}// end for
		}
		
		Collections.reverse(graphList);
		return graphList;
		
	}
	
	@GetMapping("/excel")
	public ModelAndView excelGET(ModelAndView model, ExcelView excelView, ExcelViewM excelViewM,SearchCriteria cri) throws SQLException {
		
		if(cri.getKeyword() != null) {
			if(cri.getKeyword().isEmpty() || cri.getKeyword().equals("undefined")) {
				cri.setKeyword(null);
			}
		}
		if(cri.getSelectKey() != null) {
			if(cri.getSelectKey().isEmpty() || cri.getSelectKey().equals("키워드")){
				cri.setSelectKey(null);
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
			cri.setTextType(null);
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
		
		logger.info("cri: " + cri);

		List<ExtractVO> classiList = new ArrayList<ExtractVO>();

		ExtractComparator comparator = new ExtractComparator();
		Collections.sort(classiList, comparator);
		
		if(cri.getPortal_name().equals("youtube")) {
			model.addObject("part", "sns");
			model.addObject("snsList", snsService.youtubeListAll(cri));
			model.setView(excelViewM);
			
		}else {
			model.addObject("part", "sns");
			model.addObject("snsList", snsService.listAll(cri));
			model.setView(excelView);
		}
		
		return model;
	}
	
}
