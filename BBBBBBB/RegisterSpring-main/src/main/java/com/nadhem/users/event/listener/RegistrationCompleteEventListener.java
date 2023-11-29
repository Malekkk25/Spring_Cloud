package com.nadhem.users.event.listener;

import java.io.UnsupportedEncodingException; 
import java.util.UUID;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import com.nadhem.users.entities.User;
import com.nadhem.users.event.RegistrationCompleteEvent;
import com.nadhem.users.service.UserService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent>{
	org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RegistrationCompleteEventListener.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	private JavaMailSender mailSender; 
	
	private User theUser;
	
	@Override
	public void onApplicationEvent(RegistrationCompleteEvent event) {
		//get user
		theUser=event.getUser();
		
		//create verification token
		String verificationToken =UUID.randomUUID().toString();
		
		//save token
		userService.saveUserVerificationToken(theUser,verificationToken);
		
		//url send to the user
		String url =event.getApplicationUrl()+"/register/verifyEmail?token="+verificationToken;
		
		//send the email
		try {
		sendVerificationEmail(url);
		}catch( UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		log.info("Click the link to verify your resgistration :{}",url);
		
	}
	
	public void sendVerificationEmail(String url) throws  UnsupportedEncodingException {
        String subject = "Email Verification";
        String senderName = "User Registration Portal Service";
        String mailContent = "<p> Hi, "+ theUser.getUsername()+ ", </p>"+
                "<p>Thank you for registering with us,"+"" +
                "Please, follow the link below to complete your registration.</p>"+
                "<a href=\"" +url+ "\">Verify your email to activate your account</a>"+
                "<p> Thank you <br> Users Registration Portal Service";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        try {
			messageHelper.setFrom("malek1ayed@gmail.com", senderName);
		} catch (UnsupportedEncodingException | javax.mail.MessagingException e) {
			e.printStackTrace();
		}
        try {
			messageHelper.setTo(theUser.getEmail());
		} catch (javax.mail.MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			messageHelper.setSubject(subject);
		} catch (javax.mail.MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			messageHelper.setText(mailContent, true);
		} catch (javax.mail.MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        mailSender.send(message);
    }

	public RegistrationCompleteEventListener() {
		super();
	}
	
	

}
