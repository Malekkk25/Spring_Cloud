package com.nadhem.users.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nadhem.users.entities.User;

import lombok.Data;


@Data
public class UserRegistrationDetails implements UserDetails{
	
	
	private String username;
	private String password;
	private boolean isEnabled; 
	private List<GrantedAuthority> authorities= new ArrayList<>();
	
	
	
	public UserRegistrationDetails(User user) {
		super();
		this.username = user.getEmail();
		this.password = user.getPassword();
		this.isEnabled = user.isEnabled();
		user.getRoles().forEach(role -> {
			 GrantedAuthority auhority = new SimpleGrantedAuthority(role.getRole());
			 authorities.add(auhority);
		 });;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isEnabled;
	}

}
