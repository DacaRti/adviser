package com.spring.program.project.services;

import com.spring.program.project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author DacaP
 * @version 1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserService userService;

	@Autowired
	public UserDetailsServiceImpl(UserService userService) {
		this.userService = userService;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			User user = userService.findByUsername(username);
			if (user == null) {
				throw new UsernameNotFoundException("No user found with username: " + username);
			}
			user.setAccountNonExpired(true);
			user.setCredentialsNonExpired(true);
			user.setAccountNonLocked(true);
			user.setEnabled(true);
			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}