package org.union.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.union.domain.CommunityVO;
import org.union.domain.ExtractVO;
import org.union.domain.GraphVO;
import org.union.domain.MediaVO;
import org.union.domain.NewsVO;
import org.union.domain.NvVO;
import org.union.domain.PageMaker;
import org.union.domain.PortalVO;
import org.union.domain.ReplyVO;
import org.union.domain.ReporterVO;
import org.union.domain.SNSVO;
import org.union.domain.SearchCriteria;
import org.union.domain.SearchFv;
import org.union.domain.SearchNv;
import org.union.domain.SwearwordVO;
import org.union.domain.UserVO;
import org.union.persistence.MediaDAO;
import org.union.service.KeywordService;
import org.union.service.MediaService;
import org.union.service.ReporterService;
import org.union.service.UserService;
import org.union.util.ExcelView;
import org.union.util.ExcelViewM;
import org.union.util.ListUtil;
import org.union.util.Match_Excel;

@Controller
@RequestMapping("/media/*")
public class MediaController {

	
	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private ReporterService reporterService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private KeywordService keywordService;
	
	private static Logger logger = LoggerFactory.getLogger(MediaController.class);
	
	
	@GetMapping("/news")
	public void newsGET(@ModelAttribute("cri") SearchCriteria cri, Model model) throws ParseException, SQLException {
		logger.info("newsGET called....");
		logger.info("first cri: " + cri);
		cri.setCompany(null);
		cri.setTextType(null);
		
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
		
		if(cri.getCompany() == null || cri.getCompany().equals("회사")) {
			logger.info(SecurityContextHolder.getContext().getAuthentication().getName().toString());
			UserVO vo = userService.viewById(SecurityContextHolder.getContext().getAuthentication().getName());
			
			if(!vo.getUser_name().equals("union")) {
			cri.setCompany(vo.getUser_name());
			
			}else {
				cri.setCompany(null);
			}
		}
		
		if(cri.getCompany() != null) {
			if(cri.getCompany().isEmpty() == false) {
			
				UserVO userVO  = userService.viewByName(cri.getCompany());
				logger.info("userVO: " + userVO);
			    logger.info("keywordList: " + keywordService.listByUser(userVO.getUser_idx()));
				model.addAttribute("modelKeywordList", keywordService.listByUser(
						userService.viewByName(cri.getCompany()).getUser_idx()));
			}
		}
		
		logger.info("cri: " + cri);
		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		List<MediaVO> mediaList = mediaService.allPageList(cri);
		
		for(int i = 0; i < mediaList.size(); i++) {
			mediaList.get(i).setWriteDate(date.format(mediaList.get(i).getUpdateDate()));
		}
		
		
		model.addAttribute("mediaList", mediaList);
		
		Integer totalCount = mediaService.allPageCount(cri);
		
		logger.info("totalCount: " + totalCount);
		
		PageMaker pageMaker = new PageMaker();
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		model.addAttribute("pageMaker", pageMaker);
		
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
		
	}
	
	@GetMapping("/replyAdd")
	public void replyAdd() {
		
	}
	
	
	@GetMapping("/replyAddOk")
	public String replyAddOk(NewsVO vo) throws SQLException {
		
		mediaService.replyAdd(vo);
		return "redirect:/media/replyAdd";
	}
	
	@GetMapping("/replyList")
	public void replyList(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception{
		logger.info("replyList called....");
		
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
		
		logger.info("cri: " + cri);
		
		model.addAttribute("newsList", mediaService.newsList(cri));
//		model.addAttribute("replyCount", mediaService.replyCount(news_idx));
		
		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		
		Integer totalCount = mediaService.newsAllPageCount(cri);
		
		logger.info("totalCount: " + totalCount);
		
		PageMaker pageMaker = new PageMaker();
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		model.addAttribute("pageMaker", pageMaker);
		
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
		logger.info("cri.getPerPageNum(): " + cri.getPerPageNum());
		logger.info("(cri.getPage()-1): " + (cri.getPage()-1));
		
	}
	
	
	@GetMapping("/reply")
	public void reply(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception{
		logger.info("reply called....");
		
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
		
		model.addAttribute("replyList", mediaService.replyList(cri));
		
		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Integer totalCount = mediaService.replyAllPageCount(cri);
		
		logger.info("totalCount: " + totalCount);
		
		PageMaker pageMaker = new PageMaker();
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		model.addAttribute("pageMaker", pageMaker);
		
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
	}
	
	@GetMapping("/media_match")
	public void media_match(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception{
		logger.info("media_match called....");
		
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
		
		logger.info("cri: " + cri);
		
		model.addAttribute("mediaTotalMatchList", mediaService.mediaTotalMatchList(cri));
	}
	
	@GetMapping("/media_popUp")
	public void media_popUp(@ModelAttribute("cri") SearchCriteria cri, Model model, String media_name,String part, String company, String selectKey, String startDate, String endDate) throws Exception{
		logger.info("media_popUp called....");
		
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
		cri.setMedia_name(media_name);
		
		logger.info("cri: " + cri);
		
		if(part.equals("media")) {
		model.addAttribute("mediaDataList", mediaService.mediaDataList(cri));
		}else if(part.equals("match")) {
		model.addAttribute("mediaMatchDataList", mediaService.mediaMatchDataList(cri));
		}
	}
	
	@GetMapping("/reporter_popUp")
	public void reporter_popUp(@ModelAttribute("cri") SearchCriteria cri, Model model, String reporter_name,String part, String company, String selectKey, String startDate, String endDate) throws Exception{
		logger.info("reporter_popUp called....");
		
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
		cri.setReporter_name(reporter_name);
		
		logger.info("cri: " + cri);
		
		if(part.equals("media")) {
		model.addAttribute("reporterDataList", mediaService.reporterDataList(cri));
		}else if(part.equals("match")) {
		model.addAttribute("reporterMatchDataList", mediaService.reporterMatchDataList(cri));
		}
	}
	
	@GetMapping("/reporter_match")
	public void reporter_match(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception{
		logger.info("reporter_match called....");
		
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
		
		logger.info("cri: " + cri);
		
		model.addAttribute("reporterTotalMatchList", mediaService.reporterTotalMatchList(cri));
	}
	
	@ResponseBody
	@PostMapping("/newsInsert")
	public void newsInsertPOST(Integer idx, String textType) throws SQLException {
		logger.info("newsInsert called....");
		
		logger.info("idx: " + idx);
		logger.info("textType: " + textType);

		MediaVO vo = new MediaVO();
		
		vo.setMedia_idx(idx);
		vo.setTextType(textType);
		
		mediaService.modifyType(vo);
	}
	
	@ResponseBody
	@GetMapping("/excel")
	public ModelAndView excelGET(ModelAndView mav, ExcelView excelView, SearchCriteria cri, Model model) throws SQLException {
		
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
		logger.info("perPageNum: " + cri.getPerPageNum());
		logger.info("getStartPage: " + cri.getStartPage());
		ListUtil util = new ListUtil();
		List<ExtractVO> extractList = new ArrayList<ExtractVO>();
		
		mav.addObject("list", util.listAddMediaList(extractList, mediaService.newsExcel(cri)));
		mav.setView(excelView);
		
		return mav;
	}
	
	@ResponseBody
	@GetMapping("/excelOk")
	public ModelAndView ReplyexcelGET(ModelAndView model, ExcelView excelView, SearchCriteria cri) throws SQLException {
		
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
		logger.info("perPageNum: " + cri.getPerPageNum());
		logger.info("getStartPage: " + cri.getStartPage());
		ListUtil util = new ListUtil();
		List<ExtractVO> extractList = new ArrayList<ExtractVO>();
		
		
		
		model.addObject("list", util.listAddReplyList(extractList, mediaService.replyAllPage(cri)));
		model.addObject("type", "news");
		model.setView(excelView);
		
		return model;
	}
	
	@ResponseBody
	@GetMapping("/excel_match")
	public ModelAndView excel_matchGET(ModelAndView model, Match_Excel excelView, SearchCriteria cri, String part) throws SQLException {
		
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
		logger.info("perPageNum: " + cri.getPerPageNum());
		logger.info("getStartPage: " + cri.getStartPage());
		ListUtil util = new ListUtil();
		List<ExtractVO> extractList = new ArrayList<ExtractVO>();
		
		if(part.equals("media")) {
			model.addObject("list", util.listAddMediaMatchList(extractList, mediaService.mediaMatchallList(cri)));
		}else if(part.equals("reporter")) {
			model.addObject("list", util.listAddMediaMatchList(extractList, mediaService.reporterMatchallList(cri)));
		}
		
		model.addObject("type", "match");
		model.setView(excelView);
		
		return model;
	}
	
	
	@GetMapping("/press")
	public void pressGET(@ModelAttribute("cri") SearchCriteria cri, Model model) throws SQLException {
		logger.info("pressGET called....");
		
		logger.info("cri: " + cri);
		
		List<ReporterVO> reporterList = reporterService.listSearch(cri);
		
		logger.info("reporterList: " + reporterList);
		
		model.addAttribute("reporterList", reporterList);
		
		Integer totalCount = reporterService.getSearchCount(cri);
		
		logger.info("totalCount: " + totalCount);
		
		PageMaker pageMaker = new PageMaker();
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(reporterService.getSearchCount(cri));
		
		logger.info("pageMaker: " + pageMaker);
		
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
	}
	
	@PostMapping("/pressInsert")
	public String pressInsertPOST(ReporterVO reporterVO) throws SQLException {
		logger.info("pressInsertPOST called....");
		
		logger.info("reporterVO: " + reporterVO);
		
		reporterService.insert(reporterVO);
		
		return "redirect:/media/press";
	}
	
	@ResponseBody
	@PostMapping("modify")
	public String modifyPOST(Integer idx, String textType) throws SQLException {
		logger.info("insertPOST called....");
		
		logger.info("idx: " + idx);
		logger.info("textType: " + textType );
		
		
		NewsVO nvo = new NewsVO();
		ReplyVO rvo = new ReplyVO();
		nvo.setNews_idx(idx);
		nvo.setTextType(textType);
		rvo.setReply_idx(idx);
		rvo.setTextType(textType);
			
		mediaService.newsUpdateTextType(nvo);
		mediaService.replyUpdateTextType(rvo);
		
		return "success";
	}
	
	@ResponseBody
	@PostMapping("insert")
	public String insertPOST(Integer idx, String textType) throws SQLException {
		logger.info("insertPOST called....");
		
		logger.info("idx: " + idx);
		logger.info("textType: " + textType );
		
		ReplyVO rvo = new ReplyVO();
		
		rvo.setReply_idx(idx);
		rvo.setTextType(textType);
		
		mediaService.replyUpdateTextType(rvo);
		
		return "success";
	}
	
	@ResponseBody
	@PostMapping("/remove")
	public String removePOST(Integer idx) throws SQLException {
		logger.info("removePOST called....");
		logger.info("idx: " + idx);
		
		mediaService.newsRemove(idx);
		mediaService.replyRemove(idx);
		
		return "success";
	}
	
	@ResponseBody
	@PostMapping("checkList")
	public String checkList(Integer idx) throws SQLException {
		logger.info("checkListPOST called....");
		logger.info("checkidx: " + idx);
		
		mediaService.checkList(idx);
		mediaService.headlineUpdate(idx);
		
		return "success";
	}
	
	@ResponseBody
	@PostMapping("uncheckList")
	public String uncheckList(Integer idx) throws SQLException {
		logger.info("uncheckListPOST called....");
		logger.info("checkidx: " + idx);
		
		mediaService.headlineUpdate2(idx);
		mediaService.checkDelete(idx);
		
		return "success";
	}
	
	@ResponseBody
	@PostMapping("update")
	public String newsUpdate(Integer idx, Integer state) throws SQLException {
		logger.info("newsUpdate called....");
		
		logger.info("idx: " + idx);
		logger.info("state: " + state);
		
		NewsVO vo = new NewsVO();
		
		vo.setNews_idx(idx);
		vo.setNews_state(state);
		
		mediaService.newsUpdateState(vo);
		
		return "success";
	}
	
	@ResponseBody
	@PostMapping("allUpdate")
	public String updatePOST(Integer idx, Integer state) throws SQLException {
		logger.info("updatePOST called....");
		
		logger.info("idx: " + idx);
		logger.info("state: " + state);
		
		NewsVO vo = new NewsVO();
		
		vo.setNews_idx(idx);
		vo.setNews_state(state);
		
		mediaService.newsUpdateState(vo);
		
		return "success";
		
	}
	
	@ResponseBody
	@PostMapping("/checkUrl")
	public Integer checkUrlPOST(String url) throws SQLException {
		logger.info("checkUrlPOST called....");
		
		logger.info("url: " + url);
		
		return mediaService.checkUrl(url);
	}
	
	@ResponseBody
	@PostMapping("/graphMatch")
	public List<GraphVO> graphMatch(Model model, String success, @ModelAttribute("cri") SearchCriteria cri, String company, String selectKey, String startDate, String endDate, String part) throws ParseException, SQLException {
		logger.info("graphMatchPOST called....");
		
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
		
	List<GraphVO> graphList = new ArrayList<GraphVO>();
		
	if(part.equals("media")) {
		
		Integer totalCount = mediaService.mediaMatchCount(cri);
		Integer totalCount2 = mediaService.mediaMatchCount4(cri);
		logger.info("totalCount: " + totalCount);
		logger.info("totalCount2: " + totalCount2);
		
		
		for(int i = 0; i < totalCount2; i++) {
			
			GraphVO graphVO = new GraphVO();
			
			double media = mediaService.mediaMatchList(cri).get(i).getTotal();
			
			graphVO.setMedia_total(Math.round(media / totalCount * 100));
			graphVO.setMedia(mediaService.mediaMatchList(cri).get(i).getMedia_name());
			
			graphList.add(graphVO);
			
			/*logger.info("graphset1: " + graphList.get(i).getMedia());
			logger.info("graphW2: " + graphList.get(i).getMedia_total());*/
			
		}
	}else if(part.equals("reporter")) {
		
		Integer totalCount = mediaService.reporterMatchCount(cri);
		logger.info("totalCount: " + totalCount);
		Integer totalCount2 = mediaService.reporterMatchCount4(cri);
		logger.info("totalCount2: " + totalCount2);
		
		
		for(int i = 0; i < totalCount2; i++) {
			
			GraphVO graphVO = new GraphVO();
			
			double reporter = mediaService.reporterMatchList(cri).get(i).getTotal();
			
			graphVO.setMedia_total(Math.round(reporter / totalCount * 100));
			graphVO.setMedia(mediaService.reporterMatchList(cri).get(i).getReporter_name());
			
			graphList.add(graphVO);
			
			/*logger.info("graphset1: " + graphList.get(i).getMedia());
			logger.info("graphW2: " + graphList.get(i).getMedia_total());*/
			
		}
		
		
	}
		logger.info("graphList: " + graphList);
		return graphList;
	}
	@ResponseBody
	@PostMapping("/graphMatch2")
	public List<GraphVO> graphMatch2(Model model, String success, @ModelAttribute("cri") SearchCriteria cri, String company, String selectKey, String startDate, String endDate, String part) throws ParseException, SQLException {
		logger.info("graphMatch2POST called....");
		
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
		
		List<GraphVO> graphList = new ArrayList<GraphVO>();
		
		if(part.equals("media")) {
			
			Integer totalCount = mediaService.mediaMatchCount2(cri);
			Integer totalCount2 = mediaService.mediaMatchCount3(cri);
			logger.info("totalCount2: " + totalCount);
			logger.info("matchTotalCount: " + totalCount2);
			
			
			for(int i = 0; i < totalCount2; i++) {
				
				GraphVO graphVO = new GraphVO();
				
				double media = mediaService.mediaMatchList2(cri).get(i).getTotal();
				
				graphVO.setMedia_total( Math.round(media / totalCount * 100));
				graphVO.setMedia(mediaService.mediaMatchList2(cri).get(i).getMedia_name());
				
				graphList.add(graphVO);
				
				/*logger.info("media: " + graphList.get(i).getMedia());
				logger.info("total: " + graphList.get(i).getMedia_total());*/
				
			}
		}else if(part.equals("reporter")) {

			Integer totalCount = mediaService.reporterMatchCount2(cri);
			Integer totalCount2 = mediaService.reporterMatchCount3(cri);
			logger.info("totalCount2: " + totalCount);
			logger.info("matchTotalCount: " + totalCount2);
			
			
			for(int i = 0; i < totalCount2; i++) {
				
				GraphVO graphVO = new GraphVO();
				
				double reporter = mediaService.reporterMatchList2(cri).get(i).getTotal();
				
				graphVO.setMedia_total( Math.round(reporter / totalCount * 100));
				graphVO.setMedia(mediaService.reporterMatchList2(cri).get(i).getReporter_name());
				
				graphList.add(graphVO);
				
				/*logger.info("media: " + graphList.get(i).getMedia());
				logger.info("total: " + graphList.get(i).getMedia_total());*/
				
			}
			
		}
		
		logger.info("graphList: " + graphList);
		return graphList;
	}
}
