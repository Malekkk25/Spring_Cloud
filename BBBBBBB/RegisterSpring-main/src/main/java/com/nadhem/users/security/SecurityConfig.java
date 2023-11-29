package com.nadhem.users.security;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
 
	@Autowired
 	UserDetailsService userDetailsService;
 	
 	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
 	
 	@Autowired
 	AuthenticationManager authMgr;
	
	
 	@Bean
	public AuthenticationManager authManager(HttpSecurity http, 
			BCryptPasswordEncoder bCryptPasswordEncoder, 
			UserDetailsService userDetailsService) 
	  throws Exception {
	    return http.getSharedObject(AuthenticationManagerBuilder.class)
	      .userDetailsService(userDetailsService)
	      .passwordEncoder(bCryptPasswordEncoder)
	      .and()
	      .build();
	}
	
	
	 @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 
		  http.csrf().disable();
		  http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		  http.authorizeRequests().antMatchers("/login").permitAll();  
		  http.authorizeRequests().antMatchers("/register/**").permitAll();
		  http.authorizeRequests().antMatchers("/all").hasAuthority("ADMIN");
		  http.authorizeRequests().antMatchers("/api/removeRoleFromUser").permitAll();
		  http.authorizeRequests().antMatchers("/api/allRoles").permitAll();
		  http.authorizeRequests().antMatchers("/api/addRole").permitAll();
		  http.authorizeRequests().anyRequest().authenticated();
		  http.addFilter(new  JWTAuthenticationFilter (authMgr)) ;
		  http.addFilterBefore(new JWTAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
		  return http.build();
		  
     }
	 
	 @Bean
	 public JavaMailSender getJavaMailSender() {
	     JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	     mailSender.setHost("smtp.gmail.com");
	     mailSender.setPort(587);
	     
	     mailSender.setUsername("malek1ayed@gmail.com");
	     mailSender.setPassword("ecrp fihu unle gsgx");
	     
	     Properties props = mailSender.getJavaMailProperties();
	     props.put("mail.transport.protocol", "smtp");
	     props.put("mail.smtp.auth", "true");
	     props.put("mail.smtp.starttls.enable", "true");
	     props.put("mail.debug", "true");
	     
	     return mailSender;
	 }

}
