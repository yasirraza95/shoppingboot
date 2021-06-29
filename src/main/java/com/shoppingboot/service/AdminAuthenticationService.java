package com.shoppingboot.service;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shoppingboot.dao.UserDAO;
import com.shoppingboot.model.TgtUser;

@Service
public class AdminAuthenticationService implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		TgtUser tgtUser = userDAO.getUser(username, "admin");
		GrantedAuthority authority = new SimpleGrantedAuthority(tgtUser.getRole());
		
		TgtUser userDetails = new TgtUser(tgtUser.getId(), tgtUser.getUsername(), tgtUser.getPassword(), Arrays.asList(authority));
		return userDetails;
	}

}