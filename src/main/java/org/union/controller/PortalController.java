package org.union.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.number.money.CurrencyUnitFormatter;
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
import org.union.domain.NaverMovieVO;
import org.union.domain.NavertalkVO;
import org.union.domain.NvVO;
import org.union.domain.MobileEntVO;
import org.union.domain.PageMaker;
import org.union.domain.PageMakerFv;
import org.union.domain.SearchCriteria;
import org.union.domain.UserVO;
import org.union.service.EtService;
import org.union.service.KeywordService;
import org.union.service.NaverMovieService;
import org.union.service.MobileEntService;
import org.union.service.PortalService;
import org.union.service.SNSService;
import org.union.service.UserService;
import org.union.service.ViralService;
import org.union.util.ExcelView;
import org.union.util.ListUtil;

@Controller
@RequestMapping("/portal/*")
public class PortalController {

    @Autowired
    private PortalService portalService;

    @Autowired
    private NaverMovieService naverMovieService;

    @Autowired
    private MobileEntService mobileEntService;

    @Autowired
    private EtService etService;
    
    @Autowired
	private SNSService snsService;

    @Autowired
    private UserService userService;

    @Autowired
    private KeywordService keywordService;

    @Autowired
    private ViralService viralService;

    @Autowired
    private NaverMovieService movieService;

    private static Logger logger = LoggerFactory.getLogger(PortalController.class);

    @GetMapping("/naver_mobile")
    public void naverMobileGET(@ModelAttribute("cri") SearchCriteria cri, Model model) throws ParseException, SQLException {
	logger.info("naverGET called....");
		
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
	
	if (cri.getCompany() != null) {
	    if (cri.getCompany().isEmpty()) {
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

	logger.info("main cri: " + cri);

	// 영화/배우 /매칭
	model.addAttribute("movieCount", mobileEntService.getTypeOfMovieCount(cri));
	model.addAttribute("actorCount", mobileEntService.getTypeOfActorCount(cri));
	model.addAttribute("matchCount", mobileEntService.getMatchCount(cri));

	// 네이버모바일리스트
	
	model.addAttribute("mobileList", mobileEntService.searchList(cri));
	model.addAttribute("showmobileList", mobileEntService.showSearchList(cri));
	
	Integer totalCount = mobileEntService.getSearchCount(cri);
	
	logger.info("mobiletotalCount: " + totalCount);
	
	PageMaker pageMaker = new PageMaker();
	pageMaker.setCri(cri);
	pageMaker.setTotalCount(totalCount);
	

	model.addAttribute("pageMaker", pageMaker);
	model.addAttribute("totalCount", totalCount);
	model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
    }
    
    @GetMapping("/n_mobilemovie")
    public void n_mobilemovieGET(@ModelAttribute("cri") SearchCriteria cri, Model model) throws ParseException, SQLException {
	logger.info("n_mobilemovieGET called....");
	
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
	
	if (cri.getCompany() != null) {
	    if (cri.getCompany().isEmpty()) {
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
	if (cri.getHour() != null) {
	    if (cri.getHour().isEmpty()) {
		cri.setHour(null);
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

	logger.info("main cri: " + cri);

	// 영화/배우/ 매칭
	model.addAttribute("movieCount", mobileEntService.MgetTypeOfMovieCount(cri));
	model.addAttribute("actorCount", mobileEntService.MgetTypeOfActorCount(cri));
	model.addAttribute("matchCount", mobileEntService.MgetMatchCount(cri));

	// 네이버모바일리스트
	
	model.addAttribute("mobileList", mobileEntService.MsearchList(cri));
	model.addAttribute("showMsearchList", mobileEntService.showMsearchList(cri));
	
	Integer totalCount = mobileEntService.MgetSearchCount(cri);
	
	logger.info("mobiletotalCount: " + totalCount);
	
	PageMaker pageMaker = new PageMaker();
	//cri.setPerPageNum(24);
	pageMaker.setCri(cri);
	pageMaker.setTotalCount(totalCount);
	

	model.addAttribute("pageMaker", pageMaker);
	model.addAttribute("totalCount", totalCount);
	model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));
    }

    @GetMapping("/naver_movie")
    public void naverMovieGET(@ModelAttribute("cri") SearchCriteria cri, Model model) throws ParseException, SQLException {
	logger.info("naverGET called....");

	Date curDate = new Date(); 
	SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd"); //요청시간을 Date로 parsing 후 time가져오기
	String strDate = dateFormat.format(curDate);

	if (cri.getKeyword() == "" || "undefined".equals(cri.getKeyword())) {
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
	
	if (cri.getCompany() != null) {
	    if (cri.getCompany().isEmpty()) {
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
	
	/*logger.info("cri: " + cri);
	PageMaker pageMaker = new PageMaker();
	if (cri.getPerPageNum() != null) {
	    if (cri.getPerPageNum() == 10) {
		cri.setPerPageNum(30);
	    }
	}*/

	// 네이버영화리스트
	List<NaverMovieVO> movieList = naverMovieService.searchList(cri);
	List<NaverMovieVO> movieLists = naverMovieService.showSearchList(cri);
	Integer totalCount = naverMovieService.getSearchCount(cri);
	logger.info("totalCountaaa:" + totalCount);
	model.addAttribute("movieList", movieList);
	model.addAttribute("showmovieList", movieLists);
	
	PageMaker pageMaker = new PageMaker();
	//cri.setPerPageNum(24);
	pageMaker.setCri(cri);
	pageMaker.setTotalCount(totalCount);

	model.addAttribute("pageMaker", pageMaker);
	model.addAttribute("totalCount", totalCount);
	model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));

    }
    
    @GetMapping("/naverTalk")
    public void naverTalkGET(@ModelAttribute("cri") SearchCriteria cri, Model model) throws ParseException, SQLException {
	logger.info("naverTalkGET called....");

	Date curDate = new Date(); 
	SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd"); //요청시간을 Date로 parsing 후 time가져오기
	String strDate = dateFormat.format(curDate);

	if (cri.getKeyword() == "" || "undefined".equals(cri.getKeyword())) {
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
	
	if (cri.getCompany() != null) {
	    if (cri.getCompany().isEmpty()) {
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
	
	model.addAttribute("naverTalkList", portalService.naverTalkList(cri));
	
	Integer totalCount = portalService.naverTalkcount(cri);
	
	logger.info("totalCount: " + totalCount);
	
	PageMakerFv pageMakerFv = new PageMakerFv();
	
	pageMakerFv.setCri(cri);
	pageMakerFv.setTotalCount(portalService.naverTalkcount(cri));
	
	logger.info("pageMaker: " + pageMakerFv);
	
	model.addAttribute("pageMaker", pageMakerFv);
	model.addAttribute("totalCount", totalCount);
	model.addAttribute("minusCount", cri.getPerPageNum() * (cri.getPage()-1));

    }
    
    @ResponseBody
    @PostMapping("/graph")
    public List<GraphVO> graph_rePOST(Model model, String success, @ModelAttribute("cri") SearchCriteria cri,String part, String company, String selectKey) throws SQLException {
		logger.info("graph_rePOST called....");
		
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
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd HH");
		
		String current = sdf.format(new Date());
		logger.info("current: " + current);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		List<GraphVO> graphList = new ArrayList<GraphVO>();
		
		
		logger.info("cal.getTime: " + cal.getTime());
		
		if(part.equals("ent")) {
		for(int i = 0; i < 24; i++) {
			GraphVO graphVO = new GraphVO();
			
			
			/*cri.setSelectKey(selectKey);*/
			cri.setDate(cal.getTime());
			/*logger.info("selectcompanygraph: " + company);
			logger.info("selectKeygraph: " + selectKey);*/
			
			graphVO.setWriteDate(sdf2.format(cal.getTime()) + ":00:00");
			graphVO.setType1(mobileEntService.getTypeOfMovieCountGraph(cri));
			graphVO.setType2(mobileEntService.getTypeOfActorCountGraph(cri));
			graphVO.setType3(mobileEntService.getMatchCountGraph(cri));
			
			graphList.add(graphVO);
			
			cal.add(Calendar.HOUR, -1);
			}
		}else if(part.equals("movie")) {
			for(int i = 0; i < 24; i++) {
				GraphVO graphVO = new GraphVO();
				
				
				/*cri.setSelectKey(selectKey);*/
				cri.setDate(cal.getTime());
				/*logger.info("selectcompanygraph: " + company);
				logger.info("selectKeygraph: " + selectKey);*/
				
				graphVO.setWriteDate(sdf2.format(cal.getTime()) + ":00:00");
				graphVO.setType1(mobileEntService.MgetTypeOfMovieCountGraph(cri));
				graphVO.setType2(mobileEntService.MgetTypeOfActorCountGraph(cri));
				graphVO.setType3(mobileEntService.MgetMatchCountGraph(cri));
				
				graphList.add(graphVO);
				
				cal.add(Calendar.HOUR, -1);
				}
			
			
		}
		
		Collections.reverse(graphList);
		logger.info("graphList: " + graphList);
		return graphList;
	}
    
	/*@ResponseBody
    @PostMapping("/graph")
    public List<GraphVO> graphPOST(String startDate, String endDate, String company, String selectKey) throws ParseException, SQLException {
	logger.info("graphPOST called....");

	if(company.equals("회사") || company.equals("undefined") || company.equals("")) {
	    company = null;
	}
	if(selectKey.equals("키워드") || selectKey.equals("undefined") || selectKey.equals("")) {
	    selectKey = null;
	}
	String oDate = startDate;
	startDate = startDate + " 00:00:00";
	endDate = endDate + " 23:59:59";
	logger.info("startDate: " + startDate);
	logger.info("endDate: " + endDate);

	SearchCriteria cri = new SearchCriteria();
	cri.setSelectKey(selectKey);
	cri.setCompany(company);
	logger.info("cri: " + cri);

	SimpleDateFormat standFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	Date transStart = standFormat.parse(startDate);
	Date transEnd = standFormat.parse(endDate);

	Calendar cal = Calendar.getInstance();
	cal.setTime(transStart);

	List<GraphVO> graphList = new ArrayList<GraphVO>();
	
	    cri.setStartDate(startDate);
	    cri.setEndDate(endDate);
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date currentTime = new Date();
	    String oNow = sdf.format(currentTime);

	    int compare = oNow.compareTo(oDate);
	    int hourSize = 24;
	    if(compare==0) {
		hourSize = currentTime.getHours()+1; 
	    }
	    for(int i = 0; i < hourSize; i++) {
		Date dh = standFormat.parse(oDate+" "+String.valueOf(i)+":00:00");
		cri.setHour(String.valueOf(i));
		
		Integer m = mobileEntService.getTypeOfMovieCountGraph(cri);
		Integer a = mobileEntService.getTypeOfActorCountGraph(cri);
		Integer n = mobileEntService.getMatchCountGraph(cri);

		GraphVO graphVO = new GraphVO();
		graphVO.setWriteDate(standFormat.format(dh));
		graphVO.setType1(m);
		graphVO.setType2(a);
		graphVO.setType3(n);

		graphList.add(graphVO);
	    }

	return graphList;
    }*/
	
	@ResponseBody
    @PostMapping("/Mgraph")
    public List<GraphVO> MgraphPOST(String startDate, String endDate, String company, String selectKey) throws ParseException, SQLException {
	logger.info("MgraphPOST called....");

	if(company.equals("회사") || company.equals("undefined") || company.equals("")) {
	    company = null;
	}
	if(selectKey.equals("키워드") || selectKey.equals("undefined") || selectKey.equals("")) {
	    selectKey = null;
	}
	String oDate = startDate;
	startDate = startDate + " 00:00:00";
	endDate = endDate + " 23:59:59";
	logger.info("startDate: " + startDate);
	logger.info("endDate: " + endDate);

	SearchCriteria cri = new SearchCriteria();
	cri.setSelectKey(selectKey);
	cri.setCompany(company);
	logger.info("cri: " + cri);

	SimpleDateFormat standFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	Date transStart = standFormat.parse(startDate);
	Date transEnd = standFormat.parse(endDate);

	Calendar cal = Calendar.getInstance();
	cal.setTime(transStart);

	List<GraphVO> graphList = new ArrayList<GraphVO>();

	long gap = (transEnd.getTime() - transStart.getTime()) / (24 * 60 * 60 * 1000);
	logger.info("gap: " + gap);

	if(gap != 0) {
	    while ((transEnd.getTime() - cal.getTimeInMillis()) / (24 * 60 * 60 * 1000) > -1) {
		cri.setStartDate(standFormat.format(cal.getTime()));
		cal.add(Calendar.SECOND, (24 * 60 * 60) - 1);
		cri.setEndDate(standFormat.format(cal.getTime()));
		
		GraphVO graphVO = new GraphVO();
		graphVO.setWriteDate(standFormat.format(cal.getTime()).toString().split(" ")[0]);
		
		Integer m = mobileEntService.MgetTypeOfMovieCountGraph(cri);
		Integer a = mobileEntService.MgetTypeOfActorCountGraph(cri);
		Integer n = mobileEntService.MgetMatchCountGraph(cri);

		graphVO.setType1(m);
		graphVO.setType2(a);
		graphVO.setType3(n);

		graphList.add(graphVO);

		cal.add(Calendar.HOUR, 1);
	    }
	}
	else {
	    cri.setStartDate(startDate);
	    cri.setEndDate(endDate);
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date currentTime = new Date();
	    String oNow = sdf.format(currentTime);

	    int compare = oNow.compareTo(oDate);
	    int hourSize = 24;
	    if(compare==0) {
		hourSize = currentTime.getHours()+1; 
	    }
	    for(int i = 0; i < hourSize; i++) {
		Date dh = standFormat.parse(oDate+" "+String.valueOf(i)+":00:00");
		cri.setHour(String.valueOf(i));
		Integer m = mobileEntService.MgetTypeOfMovieCountGraph(cri);
		Integer a = mobileEntService.MgetTypeOfActorCountGraph(cri);
		Integer n = mobileEntService.MgetMatchCountGraph(cri);

		GraphVO graphVO = new GraphVO();
		graphVO.setWriteDate(standFormat.format(dh));
		graphVO.setType1(m);
		graphVO.setType2(a);
		graphVO.setType3(n);

		graphList.add(graphVO);
	    }
	}

	return graphList;
    }

    @GetMapping("/viral")
    public void viralGET(@ModelAttribute("cri") SearchCriteria cri, Model model) throws SQLException {
	logger.info("viralGET called....");

	cri.setKeyword(null);
	cri.setTextType(null);

	if (cri.getSelectKey() == "" || "키워드".equals(cri.getSelectKey())) {
	    logger.info("selectKey is null");
	    cri.setSelectKey(null);
	}

	/*// startDate format
		if ("undefined".equals(cri.getStartDate())  || cri.getStartDate() == "") {
			cri.setStartDate(null);
		}
		if(cri.getEndDate() == "" || "undefined".equals(cri.getEndDate())) {
			cri.setEndDate(null);
		}

		if(cri.getStartDate() == null && cri.getEndDate() == null) {
			String currentDate = sdf.format(new Date());
			currentDate = currentDate.split(":")[0];
			currentDate = currentDate + ":00:00";

			cri.setStartDate(currentDate);
		}

		// startDate, endDate 모두 값 O
		if (cri.getStartDate() != null && cri.getEndDate() != null) {
			if (cri.getStartDate().indexOf("00:00:00") < 0 && cri.getEndDate().indexOf("23:59:59") < 0) {
				cri.setStartDate(cri.getStartDate() + " 00:00:00");
				cri.setEndDate(cri.getEndDate() + " 23:59:59");
			}
		}*/
	if (cri.getCompany() != null) {
	    if (cri.getCompany().isEmpty()) {
		cri.setCompany(null);
	    }
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

	logger.info("cri: " + cri);

	// start naver
	cri.setPortal_name("naver");

	cri.setPortal_type("blog");
	model.addAttribute("nb1", viralService.getSearchInCount(cri));
	model.addAttribute("nb2", viralService.getSearchOutCount(cri));

	cri.setPortal_type("cafe");
	model.addAttribute("nc1", viralService.getSearchInCount(cri));
	model.addAttribute("nc2", viralService.getSearchOutCount(cri));

	cri.setPortal_type("kintip");
	model.addAttribute("nk1", viralService.getSearchInCount(cri));
	model.addAttribute("nk2", viralService.getSearchOutCount(cri));

	cri.setPortal_type("webdoc");
	model.addAttribute("nw1", viralService.getSearchInCount(cri));
	model.addAttribute("nw2", viralService.getSearchOutCount(cri));
	// end naver

	// start daum
	cri.setPortal_name("daum");

	cri.setPortal_type("blog");
	model.addAttribute("db1", viralService.getSearchInCount(cri));
	model.addAttribute("db2", viralService.getSearchOutCount(cri));

	cri.setPortal_type("cafe");
	model.addAttribute("dc1", viralService.getSearchInCount(cri));
	model.addAttribute("dc2", viralService.getSearchOutCount(cri));

	cri.setPortal_type("kintip");
	model.addAttribute("dk1", viralService.getSearchInCount(cri));
	model.addAttribute("dk2", viralService.getSearchOutCount(cri));

	cri.setPortal_type("webdoc");
	model.addAttribute("dw1", viralService.getSearchInCount(cri));
	model.addAttribute("dw2", viralService.getSearchOutCount(cri));

    }

    @GetMapping("/v_blog")
    public void v_blogGET(@ModelAttribute("cri") SearchCriteria cri, Model model) throws SQLException {
	logger.info("v_blogGET called....");

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

	if (cri.getCompany() != null) {
	    if (cri.getCompany().isEmpty()) {
		cri.setCompany(null);
	    }
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

	if (cri.getSelectKey() == "" || "키워드".equals(cri.getSelectKey())) {
	    logger.info("selectKey is null");
	    cri.setSelectKey(null);
	}

	// 사이트 미설정시
	if(cri.getPortal_name() != null) {
	    if(cri.getPortal_name().equals("네이버")) {
		cri.setPortal_name("naver");
	    }
	    if(cri.getPortal_name().equals("다음")) {
		cri.setPortal_name("daum");
	    }
	    if(cri.getPortal_name().equals("사이트")) {
		cri.setPortal_name("all");
	    }
	}


	// 키워드 설정 
	// 

	cri.setPortal_type("blog");

	logger.info("cri: " + cri);

	PageMaker pageMaker = new PageMaker();
	Integer totalCount = 0;

	if(cri.getCompany() != null && cri.getSelectKey() != null) {

	    totalCount = viralService.getHistoryCount(cri);

	    model.addAttribute("blog0", totalCount);
	    model.addAttribute("historyList", viralService.historyPage(cri));

	    cri.setStartDate(cri.getStartDate().split(" ")[0]);
	    cri.setEndDate(cri.getEndDate().split(" ")[0]);
	    pageMaker.setCri(cri);
	    pageMaker.setTotalCount(totalCount);


	    model.addAttribute("pageMaker", pageMaker);

	    String tempEndDate = cri.getEndDate();

	    cri.setEndDate(null);
	    model.addAttribute("blog1", viralService.getSearchInCount(cri));
	    model.addAttribute("blog2", viralService.getSearchOutCount(cri));
	    model.addAttribute("blogList", viralService.searchAllList(cri));

	    cri.setEndDate(tempEndDate);

	    // endDate null로 셋팅한 것 복원.
	}else {

	    pageMaker.setCri(cri);
	    pageMaker.setTotalCount(totalCount);

	    model.addAttribute("pageMaker", pageMaker);

	}

    }

    @GetMapping("/v_cafe")
    public void v_cafeGET(@ModelAttribute("cri") SearchCriteria cri, Model model) throws SQLException {
	logger.info("v_cafeGET called....");

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

	if (cri.getCompany() != null) {
	    if (cri.getCompany().isEmpty()) {
		cri.setCompany(null);
	    }
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

	if (cri.getSelectKey() == "" || "키워드".equals(cri.getSelectKey())) {
	    logger.info("selectKey is null");
	    cri.setSelectKey(null);
	}

	// 사이트 미설정시
	if(cri.getPortal_name() != null) {
	    if(cri.getPortal_name().equals("네이버")) {
		cri.setPortal_name("naver");
	    }
	    if(cri.getPortal_name().equals("다음")) {
		cri.setPortal_name("daum");
	    }
	    if(cri.getPortal_name().equals("사이트")) {
		cri.setPortal_name("all");
	    }
	}

	cri.setPortal_type("cafe");

	logger.info("cri: " + cri);
	PageMaker pageMaker = new PageMaker();
	Integer totalCount = 0;

	if(cri.getCompany() != null && cri.getSelectKey() != null) {

	    totalCount = viralService.getHistoryCount(cri);

	    model.addAttribute("blog0", totalCount);
	    model.addAttribute("historyList", viralService.historyPage(cri));

	    model.addAttribute("pageMaker", pageMaker);

	    cri.setStartDate(cri.getStartDate().split(" ")[0]);
	    cri.setEndDate(cri.getEndDate().split(" ")[0]);
	    pageMaker.setCri(cri);
	    pageMaker.setTotalCount(totalCount);

	    String tempEndDate = cri.getEndDate();

	    cri.setEndDate(null);
	    model.addAttribute("blog1", viralService.getSearchInCount(cri));
	    model.addAttribute("blog2", viralService.getSearchOutCount(cri));
	    model.addAttribute("blogList", viralService.searchAllList(cri));

	    cri.setEndDate(tempEndDate);

	    // endDate null로 셋팅한 것 복원.
	}else {

	    pageMaker.setCri(cri);
	    pageMaker.setTotalCount(totalCount);

	    model.addAttribute("pageMaker", pageMaker);

	}

	pageMaker.setCri(cri);
    }

    @GetMapping("/v_kin")
    public void v_kinGET(@ModelAttribute("cri") SearchCriteria cri, Model model) throws SQLException {

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

	if (cri.getCompany() != null) {
	    if (cri.getCompany().isEmpty()) {
		cri.setCompany(null);
	    }
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

	if (cri.getSelectKey() == "" || "키워드".equals(cri.getSelectKey())) {
	    logger.info("selectKey is null");
	    cri.setSelectKey(null);
	}

	// 사이트 미설정시
	if(cri.getPortal_name() != null) {
	    if(cri.getPortal_name().equals("네이버")) {
		cri.setPortal_name("naver");
	    }
	    if(cri.getPortal_name().equals("다음")) {
		cri.setPortal_name("daum");
	    }
	    if(cri.getPortal_name().equals("사이트")) {
		cri.setPortal_name("all");
	    }
	}

	cri.setPortal_type("kintip");

	logger.info("cri: " + cri);
	PageMaker pageMaker = new PageMaker();
	Integer totalCount = 0;

	if(cri.getCompany() != null && cri.getSelectKey() != null) {

	    totalCount = viralService.getHistoryCount(cri);

	    model.addAttribute("blog0", totalCount);
	    model.addAttribute("historyList", viralService.historyPage(cri));

	    model.addAttribute("pageMaker", pageMaker);

	    cri.setStartDate(cri.getStartDate().split(" ")[0]);
	    cri.setEndDate(cri.getEndDate().split(" ")[0]);
	    pageMaker.setCri(cri);
	    pageMaker.setTotalCount(totalCount);

	    String tempEndDate = cri.getEndDate();

	    cri.setEndDate(null);
	    model.addAttribute("blog1", viralService.getSearchInCount(cri));
	    model.addAttribute("blog2", viralService.getSearchOutCount(cri));
	    model.addAttribute("blogList", viralService.searchAllList(cri));

	    cri.setEndDate(tempEndDate);

	    // endDate null로 셋팅한 것 복원.
	}else {

	    pageMaker.setCri(cri);
	    pageMaker.setTotalCount(totalCount);

	    model.addAttribute("pageMaker", pageMaker);

	}

	pageMaker.setCri(cri);
    }

    @GetMapping("/v_web")
    public void v_webGET(@ModelAttribute("cri") SearchCriteria cri, Model model) throws SQLException {
	logger.info("v_webGET called....");

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

	if (cri.getCompany() != null) {
	    if (cri.getCompany().isEmpty()) {
		cri.setCompany(null);
	    }
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

	if (cri.getSelectKey() == "" || "키워드".equals(cri.getSelectKey())) {
	    logger.info("selectKey is null");
	    cri.setSelectKey(null);
	}

	// 사이트 미설정시
	if(cri.getPortal_name() != null) {
	    if(cri.getPortal_name().equals("네이버")) {
		cri.setPortal_name("naver");
	    }
	    if(cri.getPortal_name().equals("다음")) {
		cri.setPortal_name("daum");
	    }
	    if(cri.getPortal_name().equals("사이트")) {
		cri.setPortal_name("all");
	    }
	}

	cri.setPortal_type("webdoc");

	logger.info("cri: " + cri);
	PageMaker pageMaker = new PageMaker();
	Integer totalCount = 0;

	if(cri.getCompany() != null && cri.getSelectKey() != null) {

	    totalCount = viralService.getHistoryCount(cri);

	    model.addAttribute("blog0", totalCount);
	    model.addAttribute("historyList", viralService.historyPage(cri));

	    model.addAttribute("pageMaker", pageMaker);

	    cri.setStartDate(cri.getStartDate().split(" ")[0]);
	    cri.setEndDate(cri.getEndDate().split(" ")[0]);
	    pageMaker.setCri(cri);
	    pageMaker.setTotalCount(totalCount);

	    String tempEndDate = cri.getEndDate();

	    cri.setEndDate(null);
	    model.addAttribute("blog1", viralService.getSearchInCount(cri));
	    model.addAttribute("blog2", viralService.getSearchOutCount(cri));
	    model.addAttribute("blogList", viralService.searchAllList(cri));

	    cri.setEndDate(tempEndDate);

	    // endDate null로 셋팅한 것 복원.
	}else {

	    pageMaker.setCri(cri);
	    pageMaker.setTotalCount(totalCount);

	    model.addAttribute("pageMaker", pageMaker);

	}

	pageMaker.setCri(cri);
    }

    @GetMapping("/v_score")
    public void v_scoreGET(@ModelAttribute("cri") SearchCriteria cri, Model model) throws SQLException {
	logger.info("v_scoreGET called....");

	cri.setKeyword(null);
	cri.setTextType(null);

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

	if (cri.getCompany() != null) {
	    if (cri.getCompany().isEmpty()) {
		cri.setCompany(null);
	    }
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

	if (cri.getSelectKey() == "" || "키워드".equals(cri.getSelectKey())) {
	    logger.info("selectKey is null");
	    cri.setSelectKey(null);
	}

	// 사이트 미설정시
	if(cri.getPortal_name() != null) {
	    if(cri.getPortal_name().equals("네이버")) {
		cri.setPortal_name("naver");
	    }
	    if(cri.getPortal_name().equals("다음")) {
		cri.setPortal_name("daum");
	    }
	    if(cri.getPortal_name().equals("사이트")) {
		cri.setPortal_name("all");
	    }
	}


	logger.info("cri: " + cri);

	PageMaker pageMaker = new PageMaker();

	Integer totalCount = portalService.getScoreCount(cri);
	
	logger.info("totalCount: " + totalCount);

	pageMaker.setCri(cri);
	pageMaker.setTotalCount(totalCount);

	model.addAttribute("pageMaker", pageMaker);
	model.addAttribute("totalCount", totalCount);
	model.addAttribute("minusCount", cri.getPerPageNum()*(cri.getPage()-1));

	model.addAttribute("scoreList", portalService.getScoreList(cri));

	model.addAttribute("textType", portalService.getScoreTextType(cri));

	model.addAttribute("scoreCount", portalService.getOnlyScore(cri));
    }
    
    @ResponseBody
	@PostMapping("/checkList")
	public String checkList(Integer idx) throws SQLException {
		logger.info("checkListPOST called....");
		logger.info("checkidx: " + idx);
		
		portalService.scoreCheckList(idx);
		portalService.scoreUpdate(idx);
		
		return "success";
	}
    
    @ResponseBody
	@PostMapping("uncheckList")
	public String uncheckList(Integer idx) throws SQLException {
		logger.info("uncheckListPOST called....");
		logger.info("checkidx: " + idx);
		
		portalService.scoreUpdate2(idx);
		portalService.scoreCheckDelete(idx);
		
		return "success";
	}

    @ResponseBody
    @PostMapping("/historyGraph")
    public List<GraphVO> historyGraphPOST(String url) throws SQLException {
	logger.info("historyGraphPOST called....");

	List<GraphVO> list=  viralService.getHistoryRank(url);

	return list;
    }

    @ResponseBody
    @GetMapping("/excel")
    public ModelAndView excelGET(@ModelAttribute("cri") ModelAndView model, ExcelView excelView, SearchCriteria cri, String hour) throws SQLException {
	logger.info("excelGET called....");

	if (cri.getCompany() != null) {
	    if (cri.getCompany().isEmpty()) {
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

	if(cri.getSelectKey() == "" || "키워드".equals(cri.getSelectKey()) ) {
		logger.info("selectKey is null");
		cri.setSelectKey(null);
	}

	// 사이트 미설정시
	if(cri.getPortal_name() != null) {
	    if(cri.getPortal_name().equals("네이버")) {
		cri.setPortal_name("naver");
	    }
	    if(cri.getPortal_name().equals("다음")) {
		cri.setPortal_name("daum");
	    }
	    if(cri.getPortal_name().equals("사이트")) {
		cri.setPortal_name("all");
	    }
	}

	// startDate format
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
	if (cri.getHour() != null) {
	    if (cri.getHour().isEmpty()) {
		cri.setHour(null);
	    }
	}
	
	logger.info("cri: " + cri);
	
	List<ExtractVO> classiList = new ArrayList<ExtractVO>();
	ListUtil listUtil = new ListUtil();

	if (cri.getPortal_type().equals("movie")) {
	    model.addObject("list", listUtil.listAddMovieList(classiList, movieService.searchAllList(cri)));
	}
	else if (cri.getPortal_type().equals("movies")) {
	    model.addObject("list", listUtil.listAddMovieList(classiList, movieService.showSearchAllList(cri)));
	}
	else if (cri.getPortal_type().equals("mobile")) {
	    model.addObject("list", listUtil.listAddMobileList(classiList, mobileEntService.searchAllList(cri)));
	}
	else if (cri.getPortal_type().equals("mobiles")) {
	    model.addObject("list", listUtil.listAddMobileList(classiList, mobileEntService.showSearchAllList(cri)));
	}
	else if (cri.getPortal_type().equals("mobileM")) {
	    model.addObject("list", listUtil.listAddMobileList(classiList, mobileEntService.MsearchAllList(cri)));
	}
	else if (cri.getPortal_type().equals("mobileMs")) {
	    model.addObject("list", listUtil.listAddMobileList(classiList, mobileEntService.showMsearchAllList(cri)));
	}
	else {
	    model.addObject("list", listUtil.listAddViralList(classiList, viralService.searchAllList(cri)));
	}

	model.addObject("type", cri.getPortal_type());
	model.setView(excelView);

	return model;
    }
    
    @ResponseBody
	@GetMapping("/excelOk")
	public ModelAndView scoreExcelGET(ModelAndView model, ExcelView excelView, SearchCriteria cri) throws SQLException {
		
    	logger.info("excelGET called....");

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
		
		model.addObject("list", util.listAddPortalList(extractList, portalService.getScoreExcelList(cri)));
		//model.addObject("type", "news");
		model.setView(excelView);
		
		return model;
	}
    
}
