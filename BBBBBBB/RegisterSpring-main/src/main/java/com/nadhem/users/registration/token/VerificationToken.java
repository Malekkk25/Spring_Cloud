package com.nadhem.users.registration.token;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.nadhem.users.entities.User;

@Entity
public class VerificationToken {
	
	
		@Id
		@GeneratedValue(strategy =GenerationType.IDENTITY)
		private Long id;
		private String token;
		private Date expirationTime;
		private static final int EXPIRATION_TIME =15;
		
		@OneToOne
		@JoinColumn(name="user_id")
		private User user;
		
		public VerificationToken(String token, User user) {
			super();
			this.token = token;
			this.user = user;
			this.expirationTime= this.getTokenExpirationTime();
		}
		
		

		public VerificationToken(String token) {
			super();
			this.token = token;
			this.expirationTime= this.getTokenExpirationTime();
		}



		public Date getTokenExpirationTime() {
			Calendar calendar=Calendar.getInstance();
			calendar.setTimeInMillis(new Date().getTime());
			calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
			return new Date(calendar.getTime().getTime());
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public Date getExpirationTime() {
			return expirationTime;
		}

		public void setExpirationTime(Date expirationTime) {
			this.expirationTime = expirationTime;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public VerificationToken() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		


}
