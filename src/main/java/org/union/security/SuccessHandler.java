package org.union.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.union.domain.UserVO;
import org.union.service.UserService;

public class SuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	
	static final int DEFAULT_MAX_AGE = 60 * 60 * 24 * 7;

	private int maxAge = DEFAULT_MAX_AGE;

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
		
	}

	private UserService userService;

	public void setSerivce(UserService serivce) {
		this.userService = serivce;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {

		super.onAuthenticationSuccess(request, response, auth);
		User user = (User) auth.getPrincipal();

		UserVO userVO = null;
		try {
			userVO = userService.viewById(user.getUsername());
			
			request.getSession().setAttribute("userVO", userVO);

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("login success handler......called............." + user.toString());

		System.out.println("login success handler......called.............");

		System.out.println("login success handler......called.............");

		System.out.println("login success handler......called.............");

	}
}
