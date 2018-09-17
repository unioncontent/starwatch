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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.union.domain.GraphVO;
import org.union.domain.SearchCriteria;
import org.union.domain.UserVO;
import org.union.service.KeywordService;
import org.union.service.StatisticsService;
import org.union.service.UserService;

@Controller
@RequestMapping("/statistics/*")
public class StatisticsController {
	
	@Autowired
	private KeywordService keywordService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StatisticsService statisticsService;
	
	private static Logger logger = LoggerFactory.getLogger(StatisticsController.class);

	@GetMapping("/statistics")
	public void statisticsGET(@ModelAttribute("cri") SearchCriteria cri, Model model, String selectKey) throws SQLException {
		logger.info("statistics called....");
		
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
		
		model.addAttribute("searchkeywordList", statisticsService.searchkeywordList(cri));
		model.addAttribute("selectKey", selectKey);
		
	
		Integer totalCnt = statisticsService.searchkeywordCount(cri);
		double badCnt = statisticsService.searchkeywordBadCount(cri);
		logger.info("총수:" + statisticsService.searchkeywordCount(cri));
		logger.info("나쁜글:" + statisticsService.searchkeywordBadCount(cri));
		
		double badpersen = Math.round(badCnt / totalCnt * 100);
		
		logger.info("퍼센트" + badpersen);
		
		model.addAttribute("badpersen", badpersen);
		
		
		logger.info("키워드:" + statisticsService.searchkeywordList(cri));
		
	}
	
	@ResponseBody
	@PostMapping("/graph")
	public List<GraphVO> graph_rePOST(@ModelAttribute("cri") SearchCriteria cri,Model model, String success, String company, String selectKey, String part) throws ParseException, SQLException {
		logger.info("graph_rePOST called....");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String current = sdf.format(new Date());
		logger.info("current: " + current);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		List<GraphVO> graphList = new ArrayList<GraphVO>();
		
		
		logger.info("cal.getTime: " + cal.getTime());
		
		for(int i = 0; i < 7; i++) {
			GraphVO graphVO = new GraphVO();
			
			cri.setSelectKey(selectKey);
			String  startDate = sdf.format(cal.getTime());
			String  endDate = sdf.format(cal.getTime());
			cri.setStartDate(startDate + " 00:00:00");
			cri.setEndDate(endDate + " 23:59:59");	
			
			Integer totalCount = statisticsService.searchkeywordCount(cri);
			
			if(totalCount == null){
				totalCount = 0;
			}
			
			graphVO.setWriteDate(sdf.format(cal.getTime()));
			graphVO.setType1(totalCount);
			
			graphList.add(graphVO);
			
			cal.add(Calendar.DATE, -1);
			
		}
		
		Collections.reverse(graphList);
		logger.info("graphList: " + graphList);
		return graphList;
	}

}
