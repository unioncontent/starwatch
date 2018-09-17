package org.union.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.union.domain.UserVO;

public class MemberService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

		System.out.println("memberService called....");
		System.out.println("username: " + username);
		UserVO userVO;
		try {
			userVO = userService.viewById(username);
			
			if (userVO == null) {

				throw new UsernameNotFoundException("No user found with username" + userVO.getUser_name());

			}
			
			Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();

			roles.add(new SimpleGrantedAuthority("ROLE_USER"));

			UserDetails user = new User(username, userVO.getUser_PW(), roles);
			
			return user;
			
		} catch (SQLException e) {
	        e.printStackTrace();
	    }finally {
	    	
		}
		
		return null;
	}

}
