package com.nadhem.users.registration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nadhem.users.entities.User;
import com.nadhem.users.event.RegistrationCompleteEvent;
import com.nadhem.users.registration.token.VerificationToken;
import com.nadhem.users.registration.token.VerificationTokenRepository;
import com.nadhem.users.service.UserService;

@RestController
@RequestMapping("register")
public class RegistrationController {
	@Autowired
	UserService userService;
	
	@Autowired
	ApplicationEventPublisher publisher;
	
	@Autowired
	VerificationTokenRepository tokenRepository;
	
	@PostMapping
	public String registerUser(@RequestBody RegistrationRequest registrationRequest,final HttpServletRequest request ) {
		User user=userService.registerUser(registrationRequest);
		// publish registration event
		publisher.publishEvent(new RegistrationCompleteEvent(user,applicationUrl(request)));
		return "Success ! Please,Cheack your email to complete your registration";
	}
	
	@GetMapping("/verifyEmail")
	public String verifyEmail(@RequestParam("token") String token) {
		VerificationToken theToken=tokenRepository.findByToken(token);
		if(theToken.getUser().isEnabled()) {
			return "This account has already been verified, please login";
		}
		String verificationResult =userService.validateToken(token);
		if(verificationResult.equalsIgnoreCase("valid")) {
			return "Email verified successfully. Now you can login to your account";
		}
		return"Invalid verification token!";
	}

	public String applicationUrl(HttpServletRequest request) {
		
		return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
	}
	
}
