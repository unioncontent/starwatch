package org.union.controller;

import java.sql.SQLException;
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
import org.union.domain.KeywordListVO;
import org.union.domain.KeywordVO;
import org.union.domain.NewsVO;
import org.union.domain.SearchCriteria;
import org.union.domain.SwearwordVO;
import org.union.domain.UserVO;
import org.union.service.KeywordService;
import org.union.service.UserService;

@Controller
@RequestMapping("/keyword/*")
public class KeywordController {

	
	@Autowired
	private KeywordService keywordService;
	
	@Autowired
	private UserService userService;
	
	private static Logger logger = LoggerFactory.getLogger(KeywordController.class);
	
	@GetMapping("/keyword")
	public void keywordGET(@ModelAttribute("cri") SearchCriteria cri, Model model) throws SQLException {
		logger.info("keywordGET called....");
		
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
		
		model.addAttribute("mainList", keywordService.listPage(cri));
	}
	
	@GetMapping("/swearword")
	public void swearwordGET(Model model) throws SQLException {
		logger.info("swearwordGET called....");
		
		model.addAttribute("swearwordList", keywordService.swearwordList());
	}
	
	
	@GetMapping("/create")
	public void createGET(Model model) throws SQLException {
		logger.info("createGET called....");
		
		model.addAttribute("companyList", userService.listAll());
	}
	
	@GetMapping("/swearwordCreate")
	public void swearwordCreate() throws SQLException {
		
	}
	
	@GetMapping("/swearwordCreateOk")
	public String swearwordCreateGET(SwearwordVO vo) throws SQLException {
		logger.info("createGET called....");
		
		keywordService.swearwordCreate(vo);
		
		return "redirect:/keyword/swearword";
	}
	
	
	@ResponseBody
	@PostMapping("/checkMain")
	public Integer checkMainPOST(String keyword_main) throws SQLException {
		logger.info("checkMainPOST called....");
		
		logger.info("keywordMain: " + keyword_main);
		
		return keywordService.checkMain(keyword_main);
	}
	
	@ResponseBody
	@PostMapping("/checkSwearword")
	public Integer checkSwearwordPOST(String swearword) throws SQLException {
		logger.info("checkSwearwordPOST called....");
		
		logger.info("swearword: " + swearword);
		
		return keywordService.checkSwearword(swearword);
	}

	
	@ResponseBody
	@PostMapping("/insertMain")
	public String insertMainPOST(KeywordListVO vo) throws SQLException {
		logger.info("insertMainPOST called....");
		
		logger.info("vo: " + vo);
		
		keywordService.insertMain(vo);
		
		return "keyword";
	}
	
	
	@ResponseBody
	@PostMapping("/removeMain")
	public String removeMain(String keyword_main) throws SQLException {
		logger.info("removeMain called....");
		
		keywordService.removeMain(keyword_main);
		
		return "success";
	}
	
	@GetMapping("/modify")
	public void modifyGET(@ModelAttribute("keyword_main") String keyword_main, Model model) throws SQLException {
		logger.info("keywordGET called....");
		
		logger.info("keyword_main: " + keyword_main);
		
		List<KeywordVO> list = keywordService.listByMain(keyword_main);
		
		logger.info(list.toString());
		
		model.addAttribute("keyword_main", keyword_main);
		model.addAttribute("company_name", userService.view(list.get(0).getUser_idx()).getCompany_name());
		
		for(int i = 0 ; i < list.size(); i ++) {
			if(list.get(i).getKeyword() == null) {
				list.remove(i);
			}
		}
		
		model.addAttribute("keywordList", list);
		
	}
	
	@ResponseBody
	@PostMapping("/insertKeyword")
	public void insertKeywordPOST(KeywordVO vo) throws SQLException {
		logger.info("insertKeywordPOST called....");
		
		logger.info("keywordVO: " + vo);
		
		keywordService.insertKeyword(vo);
	}
	
	
	@ResponseBody
	@PostMapping("/removeKeyword")
	public void removeKeywordPOST(String keyword) throws SQLException {
		logger.info("removeKeywordPOST called....");
		
		logger.info("keyword: " + keyword);
		
		keywordService.remove(keyword);
		
	}
	
	@ResponseBody
	@PostMapping("swearwordRemove")
	public String swearwordRemovePOST(String swearword) throws SQLException {
		logger.info("swearwordRemovePOST called....");
		logger.info("swearword: " + swearword);
		
		keywordService.swearwordDelete(swearword);
		
		return "success";
	}
	
	@ResponseBody
	@PostMapping("updateOn")
	public String keywordStateUpdateOn(String keyword_main) throws SQLException {
		logger.info("keywordStateUpdate called....");
		
		logger.info("keyword_main: " + keyword_main);
		
		
		keywordService.stateUpdateOn(keyword_main);
		
		return "success";
	}
	
	@ResponseBody
	@PostMapping("updateOff")
	public String keywordStateUpdateOff(String keyword_main) throws SQLException {
		logger.info("keywordStateUpdate called....");
		
		logger.info("keyword_main: " + keyword_main);
		
		
		keywordService.stateUpdateOff(keyword_main);
		
		return "success";
	}
}
