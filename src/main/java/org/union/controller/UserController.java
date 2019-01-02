package org.union.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.union.domain.GraphVO;
import org.union.domain.KeywordVO;
import org.union.domain.UserVO;
import org.union.service.KeywordService;
import org.union.service.UserService;

import com.mysql.cj.api.Session;

@Controller
@RequestMapping("/login")
public class UserController {

	
	static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private KeywordService keywordService;
	
	@GetMapping("/loginSuccess")
	public String loginSuccess(HttpSession session) throws SQLException, UnsupportedEncodingException {
			
		logger.info(SecurityContextHolder.getContext().getAuthentication().getName().toString());
		UserVO vo = userService.viewById(SecurityContextHolder.getContext().getAuthentication().getName());
		
		
		session.setAttribute("user", vo);
		
		if(vo.getUser_type() == 1) {
			logger.info("관리자 접속");

			session.setAttribute("companyList", userService.listAll());
			session.setAttribute("keywordList", keywordService.listAll());
		
		}else {
			logger.info("CP 접속");
			
			session.setAttribute("keywordList", keywordService.listByUser(vo.getUser_idx()));
			session.setAttribute("companyList", vo);
		}
		
		List<KeywordVO> userList = keywordService.listByUser(vo.getUser_idx());
		String userk = userList.get(0).getKeyword_main();
		String user = URLEncoder.encode(userk, "UTF-8");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date current = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(current);
		cal.add(Calendar.DATE, -7);
		String date1 = sdf.format(cal.getTime());
		
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(current);
		String date2 = sdf.format(cal2.getTime());
		
		return "redirect:../main/main?&selectKey="+user+"&startDate="+date1+"&endDate="+date2;
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		logger.info("logout...");
		
		session.invalidate();
		
		return "redirect:../logoutAction";
	}
	
	@GetMapping("/login")
	public void login() {
		logger.info("login....");
	}

}
