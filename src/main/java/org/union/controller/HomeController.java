package org.union.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/*")
public class HomeController {

	
	private static Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@GetMapping("/")
	public ModelAndView login(

			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

			ModelAndView model = new ModelAndView();

			if (error != null) {

				model.addObject("error", "Invalid username and password!");

			}

			if (logout != null) {

				model.addObject("msg", "You've been logged out successfully.");

			}

			model.setViewName("login/login");

			return model;

		}	

	
	
	
	/*@GetMapping("/login")
	public void loginGET(HttpSession session) throws Exception {
		logger.info("loginGET called....");
	}*/
	
	
	@GetMapping("/login_duplicate")
	public void login_duplicate()throws Exception{
		logger.info("중복로그인");	
	}
	
	
}
