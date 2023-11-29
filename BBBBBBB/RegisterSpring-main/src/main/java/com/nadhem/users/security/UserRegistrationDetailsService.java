package com.nadhem.users.security;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.nadhem.users.entities.User;
import com.nadhem.users.repos.UserRepository;
import com.nadhem.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@Service
public class UserRegistrationDetailsService implements UserDetailsService{
	
	@Autowired
    UserService userService;
	
	private static final Logger log = LogManager.getLogger(UserRegistrationDetailsService.class);
	
	public UserRegistrationDetailsService(UserRepository userRepos) {
		super();
		this.userRepos = userRepos;
	}

	@Autowired
	UserRepository userRepos;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    User user = userService.findUserByUsername(username);
	    if (user == null)
	        throw new UsernameNotFoundException("User Not Found !");
	    
	    // Check if the user is enabled before logging
	    if (user.isEnabled() == true) {
	        // Log user information
	        log.info("Logging enabled user: " + user.getUsername());
	        
	        List<GrantedAuthority> auths = new ArrayList<>();
	        user.getRoles().forEach(role -> {
	            GrantedAuthority authority = new SimpleGrantedAuthority(role.getRole());
	            auths.add(authority);
	        });

	        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), auths);
	    } else {
	        // Log disabled user information
	        log.info("User is not enabled: " + user.getUsername());
	        throw new UsernameNotFoundException("Utilisateur introuvable !");
	    }
	}

}
