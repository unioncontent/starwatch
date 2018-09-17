package org.union.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.util.HSSFColor.DARK_BLUE;
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
import org.union.domain.FvVO;
import org.union.domain.GraphVO;
import org.union.domain.NewsVO;
import org.union.domain.NvVO;
import org.union.domain.PageMaker;
import org.union.domain.PageMakerFv;
import org.union.domain.ReporterVO;
import org.union.domain.SearchCriteria;
import org.union.domain.SearchFv;
import org.union.domain.SearchNv;
import org.union.domain.TextTypeVO;
import org.union.domain.UserVO;
import org.union.service.CalendarService;
import org.union.service.CommunityService;
import org.union.service.EtService;
import org.union.service.KeywordService;
import org.union.service.MediaService;
import org.union.service.MobileEntService;
import org.union.service.NaverMovieService;
import org.union.service.PortalService;
import org.union.service.RelationService;
import org.union.service.SNSService;
import org.union.service.UserService;
import org.union.service.ViralService;
import org.union.util.ExcelView;
import org.union.util.ExcelViewM;
import org.union.util.ListUtil;

@Controller
@RequestMapping("/marketing/*")
public class MarketingController {
	
	@Autowired
    private PortalService portalService;
	
	@Autowired
	private MediaService mediaService;

    @Autowired
    private NaverMovieService naverMovieService;

    @Autowired
    private MobileEntService mobileEntService;

    @Autowired
    private EtService etService;
    
    @Autowired
	private SNSService snsService;
    
    @Autowired
	private CommunityService communityService;

    @Autowired
	private UserService userService;
    
    @Autowired
	private RelationService relationService;

    @Autowired
    private KeywordService keywordService;
    
    @Autowired
	private CalendarService calendarService;

    @Autowired
    private ViralService viralService;
    
    @Autowired
    private NaverMovieService movieService;
   
    private static Logger logger = LoggerFactory.getLogger(PortalController.class);
	
	@GetMapping("/f_channel")
    public void f_channelGET(@ModelAttribute("cri") SearchCriteria cri, Model model, String sns_content) throws ParseException, SQLException, SQLException {
    	logger.info("f_channelGET called....");
    	
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
		
		cri.setSns_writer("CGV");
		
		logger.info("cri: " + cri);
		
		model.addAttribute("fV", snsService.facebookCGV(cri));
		model.addAttribute("fVList", snsService.facebookCGVList(cri));
		model.addAttribute("fVallList", snsService.facebookCGVallList(cri));
		model.addAttribute("fVMonitor", snsService.fvCheckList("CGV"));
		
		Integer totalCount = snsService.facebookCGVListTotalCnt(cri);
		
		logger.info("totalCount: " + totalCount);
		
		PageMakerFv pageMakerFv = new PageMakerFv();
		
		pageMakerFv.setCri(cri);
		pageMakerFv.setTotalCount(snsService.facebookCGVListTotalCnt(cri));
		
		logger.info("pageMaker: " + pageMakerFv);
		
		model.addAttribute("pageMaker", pageMakerFv);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
    }
	
	@GetMapping("/f_list")
    public void f_listGET(@ModelAttribute("cri") SearchCriteria cri, Model model, String url) throws ParseException, SQLException {
    	logger.info("f_list called....");
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
		
		cri.setSns_writer("CGV");
		cri.setUrl(url);
		logger.info("crifvList: " + cri);
		
		model.addAttribute("list", snsService.fvlistSearch(cri));
		model.addAttribute("creatDate", snsService.fvlistSearchTime(cri));
		model.addAttribute("title", snsService.fvlistOne(cri));
		
		logger.info("url: " + url);
		
		Integer totalCount = snsService.fvlistSearchTotalCnt(cri);
		
		logger.info("totalCount: " + totalCount);
		
		PageMakerFv pageMakerFv = new PageMakerFv();
		
		pageMakerFv.setCri(cri);
		pageMakerFv.setTotalCount(snsService.fvlistSearchTotalCnt(cri));
		
		logger.info("pageMaker: " + pageMakerFv);
		
		model.addAttribute("pageMaker", pageMakerFv);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
		
    }
	
	@GetMapping("/f_listall")
    public void f_listallGET(@ModelAttribute("cri") SearchCriteria cri, Model model, String url, String createstartDate, String createendDate) throws ParseException, SQLException {
    	logger.info("f_listall called....");
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
		
		cri.setSns_writer("CGV");
		cri.setUrl(url);
		cri.setCreatestartDate(createstartDate + " 00:00:00");
		cri.setCreateminusDate(createstartDate + " 23:00:00");
		cri.setCreateendDate(createendDate + " 23:59:59");
		logger.info("crifvList: " + cri);
		
		model.addAttribute("list1", snsService.fvlistSearchList(cri));
		model.addAttribute("list2", snsService.fvlistMinus(cri));
		model.addAttribute("creatDate", snsService.fvSearchlistSearchTime(cri));
		model.addAttribute("title", snsService.fvlistOne(cri));
		
		logger.info("createstartDate: " + createstartDate);
		logger.info("createendDate: " + createendDate);
		logger.info("url: " + url);
		
		Integer totalCount = snsService.fvlistSearchListTotalCnt(cri);
		
		logger.info("totalCount: " + totalCount);
		
		PageMakerFv pageMakerFv = new PageMakerFv();
		
		pageMakerFv.setCri(cri);
		pageMakerFv.setTotalCount(snsService.fvlistSearchListTotalCnt(cri));
		
		logger.info("pageMaker: " + pageMakerFv);
		
		model.addAttribute("pageMaker", pageMakerFv);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
		
    }
	
	@GetMapping("/f_graph")
    public void f_graphGET(@ModelAttribute("cri") SearchCriteria cri,SearchFv fv, Model model, String url, String url2) throws ParseException, SQLException {
    	logger.info("f_graph called....");
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
		
		cri.setSns_writer("CGV");
		cri.setUrl(url);
		fv.setUrl(url2);
		logger.info("crifvonegraph: " + cri);
		logger.info("crifvtwograph: " + fv);
		model.addAttribute("url", url);
		model.addAttribute("url2", url2);
		model.addAttribute("list1", snsService.fvlistOne(cri));
		model.addAttribute("list2", snsService.fvlistTwo(fv));
		
    }
	
	@GetMapping("/m_channel")
    public void m_channelGET(@ModelAttribute("cri") SearchCriteria cri, Model model, String sns_content) throws ParseException, SQLException {
    	logger.info("m_channelGET called....");
    	
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
		
		cri.setSns_writer("메가박스");
		
		logger.info("cri: " + cri);
		
		model.addAttribute("fV", snsService.facebookCGV(cri));
		model.addAttribute("fVList", snsService.facebookCGVList(cri));
		model.addAttribute("fVallList", snsService.facebookCGVallList(cri));
		model.addAttribute("fVMonitor", snsService.fvCheckList("메가박스"));
		
		Integer totalCount = snsService.facebookCGVListTotalCnt(cri);
		
		logger.info("totalCount: " + totalCount);
		
		PageMakerFv pageMakerFv = new PageMakerFv();
		
		pageMakerFv.setCri(cri);
		pageMakerFv.setTotalCount(snsService.facebookCGVListTotalCnt(cri));
		
		logger.info("pageMaker: " + pageMakerFv);
		
		model.addAttribute("pageMaker", pageMakerFv);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
    }
	
	@GetMapping("/m_list")
    public void m_listGET(@ModelAttribute("cri") SearchCriteria cri, Model model, String url) throws ParseException, SQLException {
    	logger.info("m_list called....");
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
		
		cri.setSns_writer("메가박스");
		cri.setUrl(url);
		logger.info("crifvList: " + cri);
		
		model.addAttribute("list", snsService.fvlistSearch(cri));
		model.addAttribute("creatDate", snsService.fvlistSearchTime(cri));
		model.addAttribute("title", snsService.fvlistOne(cri));
		
		logger.info("url: " + url);
		
		Integer totalCount = snsService.fvlistSearchTotalCnt(cri);
		
		logger.info("totalCount: " + totalCount);
		
		PageMakerFv pageMakerFv = new PageMakerFv();
		
		pageMakerFv.setCri(cri);
		pageMakerFv.setTotalCount(snsService.fvlistSearchTotalCnt(cri));
		
		logger.info("pageMaker: " + pageMakerFv);
		
		model.addAttribute("pageMaker", pageMakerFv);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
		
    }
	
	@GetMapping("/m_listall")
    public void m_listallGET(@ModelAttribute("cri") SearchCriteria cri, Model model, String url, String createstartDate, String createendDate) throws ParseException, SQLException {
    	logger.info("m_listall called....");
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
		
		cri.setSns_writer("메가박스");
		cri.setUrl(url);
		cri.setCreatestartDate(createstartDate + " 00:00:00");
		cri.setCreateminusDate(createstartDate + " 23:00:00");
		cri.setCreateendDate(createendDate + " 23:59:59");
		logger.info("crifvList: " + cri);
		
		model.addAttribute("list1", snsService.fvlistSearchList(cri));
		model.addAttribute("list2", snsService.fvlistMinus(cri));
		model.addAttribute("creatDate", snsService.fvSearchlistSearchTime(cri));
		model.addAttribute("title", snsService.fvlistOne(cri));
		
		logger.info("createstartDate: " + createstartDate);
		logger.info("createendDate: " + createendDate);
		logger.info("url: " + url);
		
		Integer totalCount = snsService.fvlistSearchListTotalCnt(cri);
		
		logger.info("totalCount: " + totalCount);
		
		PageMakerFv pageMakerFv = new PageMakerFv();
		
		pageMakerFv.setCri(cri);
		pageMakerFv.setTotalCount(snsService.fvlistSearchListTotalCnt(cri));
		
		logger.info("pageMaker: " + pageMakerFv);
		
		model.addAttribute("pageMaker", pageMakerFv);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
		
    }
	
	@GetMapping("/m_graph")
    public void m_graphGET(@ModelAttribute("cri") SearchCriteria cri,SearchFv fv, Model model, String url, String url2) throws ParseException, SQLException {
    	logger.info("m_graph called....");
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
		
		cri.setSns_writer("메가박스");
		cri.setUrl(url);
		fv.setUrl(url2);
		logger.info("crifvonegraph: " + cri);
		logger.info("crifvtwograph: " + fv);
		model.addAttribute("url", url);
		model.addAttribute("url2", url2);
		model.addAttribute("list1", snsService.fvlistOne(cri));
		model.addAttribute("list2", snsService.fvlistTwo(fv));
		
    }
	
	@GetMapping("/l_channel")
    public void l_channelGET(@ModelAttribute("cri") SearchCriteria cri, Model model, String sns_content) throws ParseException, SQLException {
    	logger.info("l_channelGET called....");
    	
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
		
		cri.setSns_writer("롯데시네마");
		
		logger.info("cri: " + cri);
		
		model.addAttribute("fV", snsService.facebookCGV(cri));
		model.addAttribute("fVList", snsService.facebookCGVList(cri));
		model.addAttribute("fVallList", snsService.facebookCGVallList(cri));
		model.addAttribute("fVMonitor", snsService.fvCheckList("롯데시네마"));
		
		Integer totalCount = snsService.facebookCGVListTotalCnt(cri);
		
		logger.info("totalCount: " + totalCount);
		
		PageMakerFv pageMakerFv = new PageMakerFv();
		
		pageMakerFv.setCri(cri);
		pageMakerFv.setTotalCount(snsService.facebookCGVListTotalCnt(cri));
		
		logger.info("pageMaker: " + pageMakerFv);
		
		model.addAttribute("pageMaker", pageMakerFv);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
    }
	
	@GetMapping("/l_list")
    public void l_listGET(@ModelAttribute("cri") SearchCriteria cri, Model model, String url) throws ParseException, SQLException {
    	logger.info("l_list called....");
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
		
		cri.setSns_writer("롯데시네마");
		cri.setUrl(url);
		logger.info("crifvList: " + cri);
		
		model.addAttribute("list", snsService.fvlistSearch(cri));
		model.addAttribute("creatDate", snsService.fvlistSearchTime(cri));
		model.addAttribute("title", snsService.fvlistOne(cri));
		
		logger.info("url: " + url);
		
		Integer totalCount = snsService.fvlistSearchTotalCnt(cri);
		
		logger.info("totalCount: " + totalCount);
		
		PageMakerFv pageMakerFv = new PageMakerFv();
		
		pageMakerFv.setCri(cri);
		pageMakerFv.setTotalCount(snsService.fvlistSearchTotalCnt(cri));
		
		logger.info("pageMaker: " + pageMakerFv);
		
		model.addAttribute("pageMaker", pageMakerFv);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
		
    }
	
	@GetMapping("/l_listall")
    public void l_listallGET(@ModelAttribute("cri") SearchCriteria cri, Model model, String url, String createstartDate, String createendDate) throws ParseException, SQLException {
    	logger.info("l_listall called....");
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
		
		cri.setSns_writer("롯데시네마");
		cri.setUrl(url);
		cri.setCreatestartDate(createstartDate + " 00:00:00");
		cri.setCreateminusDate(createstartDate + " 23:00:00");
		cri.setCreateendDate(createendDate + " 23:59:59");
		logger.info("crifvList: " + cri);
		
		model.addAttribute("list1", snsService.fvlistSearchList(cri));
		model.addAttribute("list2", snsService.fvlistMinus(cri));
		model.addAttribute("creatDate", snsService.fvSearchlistSearchTime(cri));
		model.addAttribute("title", snsService.fvlistOne(cri));
		
		logger.info("createstartDate: " + createstartDate);
		logger.info("createendDate: " + createendDate);
		logger.info("url: " + url);
		
		Integer totalCount = snsService.fvlistSearchListTotalCnt(cri);
		
		logger.info("totalCount: " + totalCount);
		
		PageMakerFv pageMakerFv = new PageMakerFv();
		
		pageMakerFv.setCri(cri);
		pageMakerFv.setTotalCount(snsService.fvlistSearchListTotalCnt(cri));
		
		logger.info("pageMaker: " + pageMakerFv);
		
		model.addAttribute("pageMaker", pageMakerFv);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
		
    }
	
	@GetMapping("/l_graph")
    public void l_graphGET(@ModelAttribute("cri") SearchCriteria cri,SearchFv fv, Model model, String url, String url2) throws ParseException, SQLException {
    	logger.info("l_graph called....");
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
		
		cri.setSns_writer("롯데시네마");
		cri.setUrl(url);
		fv.setUrl(url2);
		logger.info("crifvonegraph: " + cri);
		logger.info("crifvtwograph: " + fv);
		model.addAttribute("url", url);
		model.addAttribute("url2", url2);
		model.addAttribute("list1", snsService.fvlistOne(cri));
		model.addAttribute("list2", snsService.fvlistTwo(fv));
		
    }
	
	@GetMapping("/b_channel")
    public void b_channelGET(@ModelAttribute("cri") SearchCriteria cri, Model model, String sns_content) throws ParseException, SQLException {
    	logger.info("b_channelGET called....");
    	
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
		
		cri.setSns_writer("방울방울");
		
		logger.info("cri: " + cri);
		
		model.addAttribute("fV", snsService.facebookCGV(cri));
		model.addAttribute("fVList", snsService.facebookCGVList(cri));
		model.addAttribute("fVallList", snsService.facebookCGVallList(cri));
		model.addAttribute("fVMonitor", snsService.fvCheckList("방울방울"));
		
		Integer totalCount = snsService.facebookCGVListTotalCnt(cri);
		
		logger.info("totalCount: " + totalCount);
		
		PageMakerFv pageMakerFv = new PageMakerFv();
		
		pageMakerFv.setCri(cri);
		pageMakerFv.setTotalCount(snsService.facebookCGVListTotalCnt(cri));
		
		logger.info("pageMaker: " + pageMakerFv);
		
		model.addAttribute("pageMaker", pageMakerFv);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
    }
	
	@GetMapping("/b_list")
    public void b_listGET(@ModelAttribute("cri") SearchCriteria cri, Model model, String url) throws ParseException, SQLException {
    	logger.info("b_list called....");
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
		
		cri.setSns_writer("방울방울");
		cri.setUrl(url);
		logger.info("crifvList: " + cri);
		
		model.addAttribute("list", snsService.fvlistSearch(cri));
		model.addAttribute("creatDate", snsService.fvlistSearchTime(cri));
		model.addAttribute("title", snsService.fvlistOne(cri));
		
		logger.info("url: " + url);
		
		Integer totalCount = snsService.fvlistSearchTotalCnt(cri);
		
		logger.info("totalCount: " + totalCount);
		
		PageMakerFv pageMakerFv = new PageMakerFv();
		
		pageMakerFv.setCri(cri);
		pageMakerFv.setTotalCount(snsService.fvlistSearchTotalCnt(cri));
		
		logger.info("pageMaker: " + pageMakerFv);
		
		model.addAttribute("pageMaker", pageMakerFv);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
		
    }
	
	@GetMapping("/b_listall")
    public void b_listallGET(@ModelAttribute("cri") SearchCriteria cri, Model model, String url, String createstartDate, String createendDate) throws ParseException, SQLException {
    	logger.info("b_listall called....");
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
		
		cri.setSns_writer("방울방울");
		cri.setUrl(url);
		cri.setCreatestartDate(createstartDate + " 00:00:00");
		cri.setCreateminusDate(createstartDate + " 23:00:00");
		cri.setCreateendDate(createendDate + " 23:59:59");
		logger.info("crifvList: " + cri);
		
		model.addAttribute("list1", snsService.fvlistSearchList(cri));
		model.addAttribute("list2", snsService.fvlistMinus(cri));
		model.addAttribute("creatDate", snsService.fvSearchlistSearchTime(cri));
		model.addAttribute("title", snsService.fvlistOne(cri));
		
		logger.info("createstartDate: " + createstartDate);
		logger.info("createendDate: " + createendDate);
		logger.info("url: " + url);
		
		Integer totalCount = snsService.fvlistSearchListTotalCnt(cri);
		
		logger.info("totalCount: " + totalCount);
		
		PageMakerFv pageMakerFv = new PageMakerFv();
		
		pageMakerFv.setCri(cri);
		pageMakerFv.setTotalCount(snsService.fvlistSearchListTotalCnt(cri));
		
		logger.info("pageMaker: " + pageMakerFv);
		
		model.addAttribute("pageMaker", pageMakerFv);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
		
    }
	
	@GetMapping("/b_graph")
    public void b_graphGET(@ModelAttribute("cri") SearchCriteria cri,SearchFv fv, Model model, String url, String url2) throws ParseException, SQLException {
    	logger.info("b_graph called....");
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
		
		cri.setSns_writer("방울방울");
		cri.setUrl(url);
		fv.setUrl(url2);
		logger.info("crifvonegraph: " + cri);
		logger.info("crifvtwograph: " + fv);
		model.addAttribute("url", url);
		model.addAttribute("url2", url2);
		model.addAttribute("list1", snsService.fvlistOne(cri));
		model.addAttribute("list2", snsService.fvlistTwo(fv));
		
    }
	
	@GetMapping("/n_channel")
    public void n_channelGET(@ModelAttribute("cri") SearchCriteria cri, Model model, String sns_content) throws ParseException, SQLException {
    	logger.info("n_channelGET called....");
    	
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
		
		model.addAttribute("nVList", portalService.naverVideosList(cri));
		model.addAttribute("nVallList", portalService.naverVideosallList(cri));
		//model.addAttribute("fVListsearch", snsService.fvlistSearch(sns_content));
		model.addAttribute("nVmonitorList", portalService.nvCheckList());
		
		Integer totalCount = portalService.naverVideosListTotalCnt(cri);
		
		logger.info("totalCount: " + totalCount);
		
		PageMakerFv pageMakerFv = new PageMakerFv();
		
		pageMakerFv.setCri(cri);
		pageMakerFv.setTotalCount(portalService.naverVideosListTotalCnt(cri));
		
		logger.info("pageMaker: " + pageMakerFv);
		
		model.addAttribute("pageMaker", pageMakerFv);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
    }
	
	@GetMapping("/n_list")
    public void n_listGET(@ModelAttribute("cri") SearchCriteria cri, Model model, String url, String startDate, String endDate) throws ParseException, SQLException {
    	logger.info("n_list called....");
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
		
		cri.setUrl(url);
		logger.info("crifvList: " + cri);
		
		model.addAttribute("list", portalService.nvlistSearch(cri));
		model.addAttribute("list1", portalService.nvlistSearchList(cri));
		model.addAttribute("list2", portalService.nvlistMinus(cri));
		model.addAttribute("creatDate", portalService.nvlistSearchTime(cri));
		model.addAttribute("title", portalService.nvlistOne(cri));
		
		logger.info("url: " + url);
		
		Integer totalCount = portalService.nvlistSearchTotalCnt(cri);
		
		logger.info("totalCount: " + totalCount);
		
		PageMakerFv pageMakerFv = new PageMakerFv();
		
		pageMakerFv.setCri(cri);
		pageMakerFv.setTotalCount(portalService.nvlistSearchTotalCnt(cri));
		
		logger.info("pageMaker: " + pageMakerFv);
		
		model.addAttribute("pageMaker", pageMakerFv);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
		
    }
	
	@GetMapping("/n_listall")
    public void n_listallGET(@ModelAttribute("cri") SearchCriteria cri, Model model, String url, String createstartDate, String createendDate) throws ParseException, SQLException {
    	logger.info("n_listall called....");
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
		cri.setUrl(url);
		cri.setCreatestartDate(createstartDate + " 00:00:00");
		cri.setCreateminusDate(createstartDate + " 23:00:00");
		cri.setCreateendDate(createendDate + " 23:59:59");
		logger.info("crinvList: " + cri);
		
		model.addAttribute("list1", portalService.nvlistSearchList(cri));
		model.addAttribute("list2", portalService.nvlistMinus(cri));
		model.addAttribute("creatDate", portalService.nvSearchlistSearchTime(cri));
		model.addAttribute("title", portalService.nvlistOne(cri));
		
		logger.info("url: " + url);
		
		Integer totalCount = portalService.nvlistSearchListTotalCnt(cri);
		
		logger.info("totalCount: " + totalCount);
		
		PageMakerFv pageMakerFv = new PageMakerFv();
		
		pageMakerFv.setCri(cri);
		pageMakerFv.setTotalCount(portalService.nvlistSearchListTotalCnt(cri));
		
		logger.info("pageMaker: " + pageMakerFv);
		
		model.addAttribute("pageMaker", pageMakerFv);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
		
    }
	
	@GetMapping("/n_channel2")
    public void n_channelGET2(@ModelAttribute("cri") SearchCriteria cri, Model model, String sns_content) throws ParseException, SQLException {
    	logger.info("n_channelGET called....");
    	
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
		
		model.addAttribute("nVList", portalService.naverVideosList2(cri));
		model.addAttribute("nVallList", portalService.naverVideosallList2(cri));
		//model.addAttribute("fVListsearch", snsService.fvlistSearch(sns_content));
		model.addAttribute("nVmonitorList", portalService.nvCheckList2());
		
		Integer totalCount = portalService.naverVideosListTotalCnt2(cri);
		
		logger.info("totalCount: " + totalCount);
		
		PageMakerFv pageMakerFv = new PageMakerFv();
		
		pageMakerFv.setCri(cri);
		pageMakerFv.setTotalCount(portalService.naverVideosListTotalCnt2(cri));
		
		logger.info("pageMaker: " + pageMakerFv);
		
		model.addAttribute("pageMaker", pageMakerFv);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
    }
	
	@GetMapping("/n_list2")
    public void n_listGET2(@ModelAttribute("cri") SearchCriteria cri, Model model, String url, String content, String startDate, String endDate) throws ParseException, SQLException {
    	logger.info("f_list called....");
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
		
		cri.setUrl(url);
		logger.info("crifvList: " + cri);
		
		model.addAttribute("list", portalService.nvlistSearch2(cri));
		model.addAttribute("list1", portalService.nvlistSearchList2(cri));
		model.addAttribute("list2", portalService.nvlistMinus3(cri));
		model.addAttribute("creatDate", portalService.nvlistSearchTime2(cri));
		
		logger.info("url: " + url);
		model.addAttribute("content", content);
		
		Integer totalCount = portalService.nvlistSearchTotalCnt2(cri);
		
		logger.info("totalCount: " + totalCount);
		
		PageMakerFv pageMakerFv = new PageMakerFv();
		
		cri.setPortal_name(content);
		pageMakerFv.setCri(cri);
		pageMakerFv.setTotalCount(portalService.nvlistSearchTotalCnt2(cri));
		
		logger.info("pageMaker: " + pageMakerFv);
		
		model.addAttribute("pageMaker", pageMakerFv);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
		
    }
	
	@GetMapping("/n_listall2")
    public void n_listallGET2(@ModelAttribute("cri") SearchCriteria cri, Model model, String url, String content, String createstartDate, String createendDate) throws ParseException, SQLException {
    	logger.info("n_listall called....");
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
		cri.setUrl(url);
		cri.setCreatestartDate(createstartDate + " 00:00:00");
		cri.setCreateminusDate(createstartDate + " 23:00:00");
		cri.setCreateendDate(createendDate + " 23:59:59");
		logger.info("crinvList: " + cri);
		
		model.addAttribute("list1", portalService.nvlistSearchList2(cri));
		model.addAttribute("list2", portalService.nvlistMinus3(cri));
		model.addAttribute("creatDate", portalService.nvSearchlistSearchTime2(cri));
		
		logger.info("url: " + url);
		model.addAttribute("content", content);
		
		Integer totalCount = portalService.nvlistSearchListTotalCnt2(cri);
		
		logger.info("totalCount: " + totalCount);
		
		PageMakerFv pageMakerFv = new PageMakerFv();
		
		cri.setPortal_name(content);
		pageMakerFv.setCri(cri);
		pageMakerFv.setTotalCount(portalService.nvlistSearchListTotalCnt2(cri));
		
		logger.info("pageMaker: " + pageMakerFv);
		
		model.addAttribute("pageMaker", pageMakerFv);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
		
		
    }
	
	@GetMapping("/n_graph")
    public void n_graphGET(@ModelAttribute("cri") SearchCriteria cri,SearchFv fv, Model model, String url, String url2) throws ParseException, SQLException {
    	logger.info("n_graph called....");
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
		cri.setUrl(url);
		fv.setUrl(url2);
		logger.info("crifvonegraph: " + cri);
		logger.info("crifvtwograph: " + fv);
		model.addAttribute("url", url);
		model.addAttribute("url2", url2);
		model.addAttribute("list1", portalService.nvlistOne(cri));
		model.addAttribute("list2", portalService.nvlistTwo(fv));
		
    }
	
	@GetMapping("/n_graph2")
    public void n_graphGET2(@ModelAttribute("cri") SearchCriteria cri,SearchFv fv, Model model, String url, String url2) throws ParseException, SQLException {
    	logger.info("n_graph called....");
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
		cri.setUrl(url);
		fv.setUrl(url2);
		logger.info("crifvonegraph: " + cri);
		logger.info("crifvtwograph: " + fv);
		model.addAttribute("url", url);
		model.addAttribute("url2", url2);
		model.addAttribute("list1", portalService.nvlistOne2(cri));
		model.addAttribute("list2", portalService.nvlistTwo2(fv));
		
    }
	
	@ResponseBody
	@GetMapping("/f_update")
	public String fvUpdate(String sns_subcontent, String url) throws SQLException {
		logger.info("fvUpdate called....");
		
		logger.info("url: " + url);
		logger.info("sns_subcontent: " + sns_subcontent);
		
		FvVO vo = new FvVO();
		
		vo.setSns_subcontent(sns_subcontent);;
		vo.setUrl(url);
		
		snsService.fvUpdate(vo);
		
		return "success";
	}
	
	@ResponseBody
	@GetMapping("/n_update")
	public String nvUpdate(String portal_subtitle, String url) throws SQLException {
		logger.info("nvUpdate called....");
		
		logger.info("url: " + url);
		logger.info("portal_subtitle: " + portal_subtitle);
		
		NvVO vo = new NvVO();
		
		vo.setPortal_subtitle(portal_subtitle);
		vo.setUrl(url);
		
		portalService.nvUpdate(vo);
		
		return "success";
	}
	
	@ResponseBody
	@GetMapping("/n_update2")
	public String nvUpdate2(String portal_subtitle, String url) throws SQLException {
		logger.info("nvUpdate2 called....");
		
		logger.info("url: " + url);
		logger.info("portal_subtitle: " + portal_subtitle);
		
		NvVO vo = new NvVO();
		
		vo.setPortal_subtitle(portal_subtitle);
		vo.setUrl(url);
		
		portalService.nvUpdate2(vo);
		
		return "success";
	}
	
	@ResponseBody
	@PostMapping("/graph")
	public List<GraphVO> graphPOST(Model model, String success, String url, SearchFv fv, String Mcreate) throws ParseException, SQLException {
		logger.info("graphPOST called....");
		
		String current2 = Mcreate;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd HH");
		
		String current = sdf.format(new Date());
		logger.info("current: " + current);
		
		Date Mcreatedate = sdf.parse(current2); 
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(Mcreatedate);
		
		List<GraphVO> graphList = new ArrayList<GraphVO>();
		
		
		logger.info("cal.getTime: " + cal.getTime());
		logger.info("graphUrl: " + url);
		logger.info("graphCreate: " + Mcreate);

		for(int i = 0; i < 48; i++) {
			GraphVO graphVO = new GraphVO();
			
			fv.setDate(cal.getTime());
			fv.setUrl(url);
			
			graphVO.setWriteDate(sdf2.format(cal.getTime()) + ":00:00");
			graphVO.setType1(snsService.fvlistViewCnt(fv));
			graphVO.setType2(snsService.fvlistReply_cnt(fv));
			graphVO.setType3(snsService.fvlistlike_cnt(fv));
			
			graphList.add(graphVO);
			
			cal.add(Calendar.HOUR, +1);
					
		}
	
		logger.info("graphList: " + graphList);
		return graphList;
	}
	
	@ResponseBody
	@PostMapping("/allgraph")
	public List<GraphVO> allgraphPOST(Model model, String success, String url, SearchFv fv, String Mcreate) throws ParseException, SQLException {
		logger.info("allgraphPOST called....");
		
		String current2 = Mcreate;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd HH");
		
		String current = sdf.format(new Date());
		logger.info("current: " + current);
		
		Date Mcreatedate = sdf.parse(current2); 
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(Mcreatedate);
		
		List<GraphVO> graphList = new ArrayList<GraphVO>();
		
		
		logger.info("cal.getTime: " + cal.getTime());
		logger.info("graphUrl: " + url);
		logger.info("graphCreate: " + Mcreate);

		for(int i = 0; i < 24; i++) {
			GraphVO graphVO = new GraphVO();
			
			fv.setDate(cal.getTime());
			fv.setUrl(url);
			
			graphVO.setWriteDate(sdf2.format(cal.getTime()) + ":00:00");
			graphVO.setType1(snsService.fvlistViewCnt(fv));
			graphVO.setType2(snsService.fvlistReply_cnt(fv));
			graphVO.setType3(snsService.fvlistlike_cnt(fv));
			
			graphList.add(graphVO);
			
			cal.add(Calendar.HOUR, +1);
			
		}
		

		logger.info("graphList: " + graphList);
		return graphList;
	}
	
	@ResponseBody
	@PostMapping("/graphOne")
	public List<GraphVO> graphOne(Model model, String success, String url, String url2,SearchFv fv,SearchNv nv, String Mcreate, String Mcreate2) throws ParseException, SQLException {
		logger.info("graphOnePOST called....");
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		
		Date current = new Date();
		logger.info("current: " + current);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(current);
		
		logger.info("cal.getTime: " + cal.getTime());
		
		List<FvVO> viewList = new ArrayList<FvVO>();
		for(int i = 0; i < 24; i++) {
		
			FvVO fvVO = new FvVO();
			fv.setUrl(url);
			
			int view = 0;
			int reply = 0;
			int like = 0;
			
			if(i < snsService.fvlistGraph(fv).size()-1) {
				if(snsService.fvlistGraph(fv).get(i).getView_cnt() != null) {
					view = snsService.fvlistGraph(fv).get(i+1).getView_cnt() - snsService.fvlistGraph(fv).get(i).getView_cnt();
					reply = snsService.fvlistGraph(fv).get(i+1).getReply_cnt() - snsService.fvlistGraph(fv).get(i).getReply_cnt();
					like = snsService.fvlistGraph(fv).get(i+1).getLike_cnt() - snsService.fvlistGraph(fv).get(i).getLike_cnt();
	
					fvVO.setView_cnt(view);
					fvVO.setReply_cnt(reply);
					fvVO.setLike_cnt(like);

				}else{
					fvVO.setView_cnt(view);
					fvVO.setReply_cnt(reply);
					fvVO.setLike_cnt(like);
				}
				
			} else {
				fvVO.setView_cnt(view);
				fvVO.setReply_cnt(reply);
				fvVO.setLike_cnt(like);
				
			}
			
			viewList.add(fvVO);
		}
		
		List<FvVO> viewList2 = new ArrayList<FvVO>();
		for(int i = 0; i < 24; i++) {
		
			FvVO fvVO2 = new FvVO();
			fv.setUrl(url2);
			
			
			int view = 0;
			int reply = 0;
			int like = 0;
			
			if(i < snsService.fvlistGraph(fv).size()-1) {
				if(snsService.fvlistGraph(fv).get(i).getView_cnt() != null) {
					view = snsService.fvlistGraph(fv).get(i+1).getView_cnt() - snsService.fvlistGraph(fv).get(i).getView_cnt();
					reply = snsService.fvlistGraph(fv).get(i+1).getReply_cnt() - snsService.fvlistGraph(fv).get(i).getReply_cnt();
					like = snsService.fvlistGraph(fv).get(i+1).getLike_cnt() - snsService.fvlistGraph(fv).get(i).getLike_cnt();
	
					fvVO2.setView_cnt(view);
					fvVO2.setReply_cnt(reply);
					fvVO2.setLike_cnt(like);

				}else{
					fvVO2.setView_cnt(view);
					fvVO2.setReply_cnt(reply);
					fvVO2.setLike_cnt(like);
				}
				
			} else {
				fvVO2.setView_cnt(view);
				fvVO2.setReply_cnt(reply);
				fvVO2.setLike_cnt(like);
				
			}
			
			viewList2.add(fvVO2);
		}
		
		List<GraphVO> graphList = new ArrayList<GraphVO>();
		for(int i = 0; i < 24; i++) {
			if(viewList.get(i)==null && viewList2.get(i)==null)break;
			GraphVO graphVO = new GraphVO();
			
			graphVO.setWriteDate(sdf.format(cal.getTime()) + ":00");
			graphVO.setType1(viewList.get(i).getView_cnt());
			graphVO.setType2(viewList2.get(i).getView_cnt());
			graphVO.setType3(viewList.get(i).getReply_cnt());
			graphVO.setType4(viewList2.get(i).getReply_cnt());
			graphVO.setType5(viewList.get(i).getLike_cnt());
			graphVO.setType6(viewList2.get(i).getLike_cnt());
			
			graphList.add(graphVO);
			
			cal.add(Calendar.HOUR, -1);
		}
		logger.info("graphList: " + graphList);
		return graphList;
	}
	
	// facebook_graph 테이블 사용시
	/*@ResponseBody
	@PostMapping("/graphOne")
	public List<GraphVO> graphOne(Model model, String success, String url, String url2,SearchFv fv,SearchNv nv, String Mcreate, String Mcreate2) throws ParseException, SQLException {
		logger.info("graphOnePOST called....");
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		
		Date current = new Date();
		logger.info("current: " + current);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(current);
		
		logger.info("cal.getTime: " + cal.getTime());
		
		List<FvVO> viewList = new ArrayList<FvVO>();
		for(int i = 0; i < 24; i++) {
		
			FvVO fvVO = new FvVO();
			fv.setUrl(url);
			
			int view = 0;
			int reply = 0;
			int like = 0;
			
			if(i < snsService.fvlistGraph(fv).size()) {
				if(snsService.fvlistGraph(fv).get(i).getView_cnt() != null) {
					view = snsService.fvlistGraph(fv).get(i).getView_cnt();
					reply = snsService.fvlistGraph(fv).get(i).getReply_cnt();
					like = snsService.fvlistGraph(fv).get(i).getLike_cnt();
	
					fvVO.setView_cnt(view);
					fvVO.setReply_cnt(reply);
					fvVO.setLike_cnt(like);

				}else{
					fvVO.setView_cnt(view);
					fvVO.setReply_cnt(reply);
					fvVO.setLike_cnt(like);
				}
				
			} else {
				fvVO.setView_cnt(view);
				fvVO.setReply_cnt(reply);
				fvVO.setLike_cnt(like);
				
			}
			
			viewList.add(fvVO);
		}
		
		List<FvVO> viewList2 = new ArrayList<FvVO>();
		for(int i = 0; i < 24; i++) {
		
			FvVO fvVO2 = new FvVO();
			fv.setUrl(url2);
			
			
			int view = 0;
			int reply = 0;
			int like = 0;
			
			if(i < snsService.fvlistGraph(fv).size()) {
				if(snsService.fvlistGraph(fv).get(i).getView_cnt() != null) {
					view = snsService.fvlistGraph(fv).get(i).getView_cnt();
					reply = snsService.fvlistGraph(fv).get(i).getReply_cnt();
					like = snsService.fvlistGraph(fv).get(i).getLike_cnt();
	
					fvVO2.setView_cnt(view);
					fvVO2.setReply_cnt(reply);
					fvVO2.setLike_cnt(like);

				}else{
					fvVO2.setView_cnt(view);
					fvVO2.setReply_cnt(reply);
					fvVO2.setLike_cnt(like);
				}
				
			} else {
				fvVO2.setView_cnt(view);
				fvVO2.setReply_cnt(reply);
				fvVO2.setLike_cnt(like);
				
			}
			
			viewList2.add(fvVO2);
		}
		
		List<GraphVO> graphList = new ArrayList<GraphVO>();
		for(int i = 0; i < 24; i++) {
			if(viewList.get(i)==null && viewList2.get(i)==null)break;
			GraphVO graphVO = new GraphVO();
			
			graphVO.setWriteDate(sdf.format(cal.getTime()) + ":00");
			graphVO.setType1(viewList.get(i).getView_cnt());
			graphVO.setType2(viewList2.get(i).getView_cnt());
			graphVO.setType3(viewList.get(i).getReply_cnt());
			graphVO.setType4(viewList2.get(i).getReply_cnt());
			graphVO.setType5(viewList.get(i).getLike_cnt());
			graphVO.setType6(viewList2.get(i).getLike_cnt());
			
			graphList.add(graphVO);
			
			cal.add(Calendar.HOUR, -1);
		}
		logger.info("graphList: " + graphList);
		return graphList;
	}*/	
	
	@ResponseBody
	@PostMapping("/graphTwo")
	public List<GraphVO> graphTwo(Model model, String success, String url, String url2,SearchFv fv,SearchNv nv, String Mcreate, String Mcreate2) throws ParseException, SQLException {
		logger.info("graphTwoPOST called....");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		
		Date current = new Date();
		logger.info("current: " + current);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(current);
		
		logger.info("cal.getTime: " + cal.getTime());
		
		List<NvVO> viewList = new ArrayList<NvVO>();
		for(int i = 0; i < 24; i++) {
			
			NvVO nvVO = new NvVO();
			fv.setUrl(url);
			
			int view = 0;
			int reply = 0;
			int like = 0;
			
			if(i < portalService.nvlistGraph(fv).size()-1) {
				if(portalService.nvlistGraph(fv).get(i).getView_cnt() != null) {
					view = portalService.nvlistGraph(fv).get(i+1).getView_cnt() - portalService.nvlistGraph(fv).get(i).getView_cnt();
					reply = portalService.nvlistGraph(fv).get(i+1).getReply_cnt() - portalService.nvlistGraph(fv).get(i).getReply_cnt();
					like = portalService.nvlistGraph(fv).get(i+1).getLike_cnt() - portalService.nvlistGraph(fv).get(i).getLike_cnt();
	
					nvVO.setView_cnt(view);
					nvVO.setReply_cnt(reply);
					nvVO.setLike_cnt(like);

				}else{
					nvVO.setView_cnt(view);
					nvVO.setReply_cnt(reply);
					nvVO.setLike_cnt(like);
				}
				
			} else {
				nvVO.setView_cnt(view);
				nvVO.setReply_cnt(reply);
				nvVO.setLike_cnt(like);
				
			}
			
			viewList.add(nvVO);
		}
		
		
		
		List<NvVO> viewList2 = new ArrayList<NvVO>();
		for(int i = 0; i < 24; i++) {
			
			NvVO nvVO2 = new NvVO();
			fv.setUrl(url2);
			
			int view = 0;
			int reply = 0;
			int like = 0;
			
			if(i < portalService.nvlistGraph(fv).size()-1) {
				if(portalService.nvlistGraph(fv).get(i).getView_cnt() != null) {
					view = portalService.nvlistGraph(fv).get(i+1).getView_cnt() - portalService.nvlistGraph(fv).get(i).getView_cnt();
					reply = portalService.nvlistGraph(fv).get(i+1).getReply_cnt() - portalService.nvlistGraph(fv).get(i).getReply_cnt();
					like = portalService.nvlistGraph(fv).get(i+1).getLike_cnt() - portalService.nvlistGraph(fv).get(i).getLike_cnt();
	
					nvVO2.setView_cnt(view);
					nvVO2.setReply_cnt(reply);
					nvVO2.setLike_cnt(like);

				}else{
					nvVO2.setView_cnt(view);
					nvVO2.setReply_cnt(reply);
					nvVO2.setLike_cnt(like);
				}
				
			} else {
				nvVO2.setView_cnt(view);
				nvVO2.setReply_cnt(reply);
				nvVO2.setLike_cnt(like);
				
			}
			
			viewList2.add(nvVO2);
		}
		
		
		List<GraphVO> graphList = new ArrayList<GraphVO>();
		for(int i = 0; i < 24; i++) {
			GraphVO graphVO = new GraphVO();
			
			graphVO.setWriteDate(sdf.format(cal.getTime()) + ":00");
			graphVO.setType1(viewList.get(i).getView_cnt());
			graphVO.setType2(viewList2.get(i).getView_cnt());
			graphVO.setType3(viewList.get(i).getReply_cnt());
			graphVO.setType4(viewList2.get(i).getReply_cnt());
			graphVO.setType5(viewList.get(i).getLike_cnt());
			graphVO.setType6(viewList2.get(i).getLike_cnt());
			
			graphList.add(graphVO);
			
			cal.add(Calendar.HOUR, -1);
		}
		return graphList;
	}
	
	// naver_graph 테이블 사용시
	/*@ResponseBody
	@PostMapping("/graphTwo")
	public List<GraphVO> graphTwo(Model model, String success, String url, String url2,SearchFv fv,SearchNv nv, String Mcreate, String Mcreate2) throws ParseException, SQLException {
		logger.info("graphTwoPOST called....");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		
		Date current = new Date();
		logger.info("current: " + current);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(current);
		
		logger.info("cal.getTime: " + cal.getTime());
		
		List<NvVO> viewList = new ArrayList<NvVO>();
		for(int i = 0; i < 24; i++) {
			
			NvVO nvVO = new NvVO();
			fv.setUrl(url);
			
			int view = 0;
			int reply = 0;
			int like = 0;
			
			if(i < portalService.nvlistGraph(fv).size()) {
				if(portalService.nvlistGraph(fv).get(i).getView_cnt() != null) {
					view = portalService.nvlistGraph(fv).get(i).getView_cnt();
					reply = portalService.nvlistGraph(fv).get(i).getReply_cnt();
					like = portalService.nvlistGraph(fv).get(i).getLike_cnt();
	
					nvVO.setView_cnt(view);
					nvVO.setReply_cnt(reply);
					nvVO.setLike_cnt(like);

				}else{
					nvVO.setView_cnt(view);
					nvVO.setReply_cnt(reply);
					nvVO.setLike_cnt(like);
				}
				
			} else {
				nvVO.setView_cnt(view);
				nvVO.setReply_cnt(reply);
				nvVO.setLike_cnt(like);
				
			}
			
			viewList.add(nvVO);
		}
		
		
		
		List<NvVO> viewList2 = new ArrayList<NvVO>();
		for(int i = 0; i < 24; i++) {
			
			NvVO nvVO2 = new NvVO();
			fv.setUrl(url2);
			
			int view = 0;
			int reply = 0;
			int like = 0;
			
			if(i < portalService.nvlistGraph(fv).size()) {
				if(portalService.nvlistGraph(fv).get(i).getView_cnt() != null) {
					view = portalService.nvlistGraph(fv).get(i).getView_cnt();
					reply = portalService.nvlistGraph(fv).get(i).getReply_cnt();
					like = portalService.nvlistGraph(fv).get(i).getLike_cnt();
	
					nvVO2.setView_cnt(view);
					nvVO2.setReply_cnt(reply);
					nvVO2.setLike_cnt(like);

				}else{
					nvVO2.setView_cnt(view);
					nvVO2.setReply_cnt(reply);
					nvVO2.setLike_cnt(like);
				}
				
			} else {
				nvVO2.setView_cnt(view);
				nvVO2.setReply_cnt(reply);
				nvVO2.setLike_cnt(like);
				
			}
			
			viewList2.add(nvVO2);
		}
		
		
		List<GraphVO> graphList = new ArrayList<GraphVO>();
		for(int i = 0; i < 24; i++) {
			GraphVO graphVO = new GraphVO();
			
			graphVO.setWriteDate(sdf.format(cal.getTime()) + ":00");
			graphVO.setType1(viewList.get(i).getView_cnt());
			graphVO.setType2(viewList2.get(i).getView_cnt());
			graphVO.setType3(viewList.get(i).getReply_cnt());
			graphVO.setType4(viewList2.get(i).getReply_cnt());
			graphVO.setType5(viewList.get(i).getLike_cnt());
			graphVO.setType6(viewList2.get(i).getLike_cnt());
			
			graphList.add(graphVO);
			
			cal.add(Calendar.HOUR, -1);
		}
		return graphList;
	}*/
	
	@ResponseBody
	@PostMapping("/ngraph")
	public List<GraphVO> NgraphPOST(Model model, String success, String url, SearchFv fv, String Mcreate) throws ParseException, SQLException {
		logger.info("graphPOST called....");
		
		String current2 = Mcreate;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd HH");
		
		String current = sdf.format(new Date());
		logger.info("current: " + current);
		
		Date Mcreatedate = sdf.parse(current2); 
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(Mcreatedate);
		
		List<GraphVO> graphList = new ArrayList<GraphVO>();
		
		logger.info("cal.getTime: " + cal.getTime());
		logger.info("graphUrl: " + url);
		logger.info("graphCreate: " + Mcreate);
		
		for(int i = 0; i < 48; i++) {
			GraphVO graphVO = new GraphVO();
			
			fv.setDate(cal.getTime());
			fv.setUrl(url);
			
			graphVO.setWriteDate(sdf2.format(cal.getTime()) + ":00:00");
			graphVO.setType1(portalService.nvlistViewCnt(fv));
			graphVO.setType2(portalService.nvlistReply_cnt(fv));
			graphVO.setType3(portalService.nvlistlike_cnt(fv));
			
			graphList.add(graphVO);
			
			cal.add(Calendar.HOUR, +1);
			
		}
		
		logger.info("graphList: " + graphList);
		return graphList;
	}
	
	@ResponseBody
	@PostMapping("/nallgraph")
	public List<GraphVO> NallgraphPOST(Model model, String success, String url, SearchFv fv, String Mcreate) throws ParseException, SQLException {
		logger.info("nallgraphPOST called....");
		
		String current2 = Mcreate;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd HH");
		
		String current = sdf.format(new Date());
		logger.info("current: " + current);
		
		Date Mcreatedate = sdf.parse(current2); 
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(Mcreatedate);
		
		List<GraphVO> graphList = new ArrayList<GraphVO>();
		
		logger.info("cal.getTime: " + cal.getTime());
		logger.info("graphUrl: " + url);
		logger.info("graphCreate: " + Mcreate);
		
		for(int i = 0; i < 24; i++) {
			GraphVO graphVO = new GraphVO();
			
			fv.setDate(cal.getTime());
			fv.setUrl(url);
			
			graphVO.setWriteDate(sdf2.format(cal.getTime()) + ":00:00");
			graphVO.setType1(portalService.nvlistViewCnt(fv));
			graphVO.setType2(portalService.nvlistReply_cnt(fv));
			graphVO.setType3(portalService.nvlistlike_cnt(fv));
			
			graphList.add(graphVO);
			
			cal.add(Calendar.HOUR, +1);
			
		}
		
		logger.info("graphList: " + graphList);
		return graphList;
	}
	
	@ResponseBody
	@PostMapping("/ngraph2")
	public List<GraphVO> NgraphPOST2(Model model, String success, String url, SearchFv fv, String Mcreate) throws ParseException, SQLException {
		logger.info("graphPOST called....");
		
		String current2 = Mcreate;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd HH");
		
		String current = sdf.format(new Date());
		logger.info("current: " + current);
		
		Date Mcreatedate = sdf.parse(current2); 
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(Mcreatedate);
		
		List<GraphVO> graphList = new ArrayList<GraphVO>();
		
		logger.info("cal.getTime: " + cal.getTime());
		logger.info("graphUrl: " + url);
		logger.info("graphCreate: " + Mcreate);
		
		for(int i = 0; i < 48; i++) {
			GraphVO graphVO = new GraphVO();
			
			fv.setDate(cal.getTime());
			fv.setUrl(url);
			
			graphVO.setWriteDate(sdf2.format(cal.getTime()) + ":00");
			graphVO.setType1(portalService.nvlistViewCnt2(fv));
			graphVO.setType2(portalService.nvlistReply_cnt2(fv));
			graphVO.setType3(portalService.nvlistlike_cnt2(fv));
			
			graphList.add(graphVO);
			
			cal.add(Calendar.HOUR, +1);
			
		}
		
		logger.info("graphList: " + graphList);
		return graphList;
	}
	
	@ResponseBody
	@PostMapping("/nallgraph2")
	public List<GraphVO> NallgraphPOST2(Model model, String success, String url, SearchFv fv, String Mcreate) throws ParseException, SQLException {
		logger.info("nallgraphPOST called....");
		
		String current2 = Mcreate;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd HH");
		
		String current = sdf.format(new Date());
		logger.info("current: " + current);
		
		Date Mcreatedate = sdf.parse(current2); 
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(Mcreatedate);
		
		List<GraphVO> graphList = new ArrayList<GraphVO>();
		
		logger.info("cal.getTime: " + cal.getTime());
		logger.info("graphUrl: " + url);
		logger.info("graphCreate: " + Mcreate);
		
		for(int i = 0; i < 24; i++) {
			GraphVO graphVO = new GraphVO();
			
			fv.setDate(cal.getTime());
			fv.setUrl(url);
			
			graphVO.setWriteDate(sdf2.format(cal.getTime()) + ":00:00");
			graphVO.setType1(portalService.nvlistViewCnt2(fv));
			graphVO.setType2(portalService.nvlistReply_cnt2(fv));
			graphVO.setType3(portalService.nvlistlike_cnt2(fv));
			
			graphList.add(graphVO);
			
			cal.add(Calendar.HOUR, +1);
			
		}
		
		logger.info("graphList: " + graphList);
		return graphList;
	}
	
	@ResponseBody
	@PostMapping("/graphTwo2")
	public List<GraphVO> graphTwo2(Model model, String success, String url, String url2,SearchFv fv,SearchNv nv, String Mcreate, String Mcreate2) throws ParseException, SQLException {
		logger.info("graphTwoPOST called....");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd HH");
		
		Date current = new Date();
		logger.info("current: " + current);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(current);
		
		logger.info("cal.getTime: " + cal.getTime());
		
		List<NvVO> viewList = new ArrayList<NvVO>();
		for(int i = 0; i < 24; i++) {
		
			NvVO nvVO = new NvVO();
			fv.setUrl(url);
			
			
			nvVO.setView_cnt(portalService.nvlistGraph2(fv).get(i+1).getView_cnt() - portalService.nvlistGraph2(fv).get(i).getView_cnt());
			nvVO.setReply_cnt(portalService.nvlistGraph2(fv).get(i+1).getReply_cnt() - portalService.nvlistGraph2(fv).get(i).getReply_cnt());
			nvVO.setLike_cnt(portalService.nvlistGraph2(fv).get(i+1).getLike_cnt() - portalService.nvlistGraph2(fv).get(i).getLike_cnt());
			
			viewList.add(nvVO);
			/*logger.info("viewList: " + viewList.get(i).getView_cnt());
			logger.info("viewListW: " + viewList.get(i).getWriteDate());*/
		}
		
		List<NvVO> viewList2 = new ArrayList<NvVO>();
		for(int i = 0; i < 24; i++) {
		
			NvVO nvVO2 = new NvVO();
			fv.setUrl(url2);
			
			
			nvVO2.setView_cnt(portalService.nvlistGraph2(fv).get(i+1).getView_cnt() - portalService.nvlistGraph2(fv).get(i).getView_cnt());
			nvVO2.setReply_cnt(portalService.nvlistGraph2(fv).get(i+1).getReply_cnt() - portalService.nvlistGraph2(fv).get(i).getReply_cnt());
			nvVO2.setLike_cnt(portalService.nvlistGraph2(fv).get(i+1).getLike_cnt() - portalService.nvlistGraph2(fv).get(i).getLike_cnt());
			
			viewList2.add(nvVO2);
			/*logger.info("viewList2: " + viewList2.get(i).getView_cnt());
			logger.info("viewListW2: " + viewList2.get(i).getWriteDate());*/
		}
		
		List<GraphVO> graphList = new ArrayList<GraphVO>();
		for(int i = 0; i < 24; i++) {
			GraphVO graphVO = new GraphVO();
			
			graphVO.setWriteDate(sdf2.format(cal.getTime()) + ":00");
			graphVO.setType1(viewList.get(i).getView_cnt());
			graphVO.setType2(viewList2.get(i).getView_cnt());
			graphVO.setType3(viewList.get(i).getReply_cnt());
			graphVO.setType4(viewList2.get(i).getReply_cnt());
			graphVO.setType5(viewList.get(i).getLike_cnt());
			graphVO.setType6(viewList2.get(i).getLike_cnt());
			
			graphList.add(graphVO);
			
			cal.add(Calendar.HOUR, -1);
			
			/*logger.info("graphW1: " + graphList.get(i).getWriteDate());
			logger.info("graphW2: " + graphList.get(i).getWriteDate2());
			logger.info("graphset1: " + graphList.get(i).getType1());
			logger.info("graphset2: " + graphList.get(i).getType2());
			logger.info("graphset3: " + graphList.get(i).getType3());
			logger.info("graphset4: " + graphList.get(i).getType4());*/
		}
		/*logger.info("graphList: " + graphList);*/
		return graphList;
	}
	
	@ResponseBody
	@GetMapping("/excel")
	public ModelAndView nListlGET(ModelAndView model, ExcelViewM excelView, SearchCriteria cri,String url) throws SQLException {
		
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
		
		cri.setUrl(url);
		
		logger.info("cri: " + cri);
		logger.info("perPageNum: " + cri.getPerPageNum());
		logger.info("getStartPage: " + cri.getStartPage());
		ListUtil util = new ListUtil();
		List<ExtractVO> extractList = new ArrayList<ExtractVO>();
		
		model.addObject("list", util.listAddNvList(extractList, portalService.nvlistSearchEx(cri)));
		model.addObject("type", "videos");
		model.setView(excelView);
		
		return model;
	}
	@ResponseBody
	@GetMapping("/excelOk")
	public ModelAndView fListlGET(ModelAndView model, ExcelViewM excelView, SearchCriteria cri,String url) throws SQLException {
		
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
		
		cri.setUrl(url);
		logger.info("cri: " + cri);
		logger.info("perPageNum: " + cri.getPerPageNum());
		logger.info("getStartPage: " + cri.getStartPage());
		ListUtil util = new ListUtil();
		List<ExtractVO> extractList = new ArrayList<ExtractVO>();
		
		model.addObject("list", util.listAddFvList(extractList, snsService.fvlistSearchEx(cri)));
		model.addObject("type", "videos");
		model.setView(excelView);
		
		return model;
	}
	
	@ResponseBody
	@GetMapping("/excelupfOk")
	public ModelAndView fListlfupGET(ModelAndView model, ExcelViewM excelView, SearchCriteria cri,String url,String startDate, String endDate) throws SQLException {
		
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
		
		cri.setUrl(url);
		cri.setCreateminusDate(startDate + " 23:00:00");
		logger.info("cri: " + cri);
		logger.info("perPageNum: " + cri.getPerPageNum());
		logger.info("getStartPage: " + cri.getStartPage());
		ListUtil util = new ListUtil();
		List<ExtractVO> extractList = new ArrayList<ExtractVO>();
		model.addObject("list", util.listAddFvListUp(extractList, snsService.fvlistPlus(cri), snsService.fvlistMinus2(cri), snsService.fvlistlimt(cri)));
		model.addObject("type", "videosUp");
		model.setView(excelView);
		
		return model;
	}
	
	@ResponseBody
	@GetMapping("/excelupnOk")
	public ModelAndView fListlnupGET(ModelAndView model, ExcelViewM excelView, SearchCriteria cri,String url,String startDate, String endDate) throws SQLException {
		
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
		
		cri.setUrl(url);
		cri.setCreateminusDate(startDate + " 23:00:00");
		logger.info("cri: " + cri);
		logger.info("perPageNum: " + cri.getPerPageNum());
		logger.info("getStartPage: " + cri.getStartPage());
		ListUtil util = new ListUtil();
		List<ExtractVO> extractList = new ArrayList<ExtractVO>();
		model.addObject("list", util.listAddnvListUp(extractList, portalService.nvlistPlus(cri), portalService.nvlistMinus2(cri), portalService.nvlistlimt(cri)));
		model.addObject("type", "videosUp");
		model.setView(excelView);
		
		return model;
	}
	
	@ResponseBody
	@GetMapping("/excel2")
	public ModelAndView nListlGET2(ModelAndView model, ExcelViewM excelView, SearchCriteria cri,String url) throws SQLException {
		
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
		
		cri.setUrl(url);
		
		logger.info("cri: " + cri);
		logger.info("perPageNum: " + cri.getPerPageNum());
		logger.info("getStartPage: " + cri.getStartPage());
		ListUtil util = new ListUtil();
		List<ExtractVO> extractList = new ArrayList<ExtractVO>();
		
		model.addObject("list", util.listAddNvList(extractList, portalService.nvlistSearchEx2(cri)));
		model.addObject("type", "videos");
		model.setView(excelView);
		
		return model;
	}
	
	@ResponseBody
	@GetMapping("/excelupnOk2")
	public ModelAndView fListlnupGET2(ModelAndView model, ExcelViewM excelView, SearchCriteria cri,String url,String startDate, String endDate) throws SQLException {
		
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
		
		cri.setUrl(url);
		cri.setCreateminusDate(startDate + " 23:00:00");
		logger.info("cri: " + cri);
		logger.info("perPageNum: " + cri.getPerPageNum());
		logger.info("getStartPage: " + cri.getStartPage());
		ListUtil util = new ListUtil();
		List<ExtractVO> extractList = new ArrayList<ExtractVO>();
		model.addObject("list", util.listAddnvListUp(extractList, portalService.nvlistPlus2(cri), portalService.nvlistMinus4(cri), portalService.nvlistlimt2(cri)));
		model.addObject("type", "videosUp");
		model.setView(excelView);
		
		return model;
	}
	
}
