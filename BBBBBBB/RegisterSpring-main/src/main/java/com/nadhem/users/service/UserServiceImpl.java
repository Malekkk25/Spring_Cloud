package com.nadhem.users.service;
import java.util.Calendar ;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.nadhem.users.dto.UserDTO;
import com.nadhem.users.entities.Role;
import com.nadhem.users.entities.User;
import com.nadhem.users.exception.UserAlreadyExistsException;
import com.nadhem.users.registration.RegistrationRequest;
import com.nadhem.users.repos.RoleRepository;
import com.nadhem.users.repos.UserRepository;

import org.slf4j.Logger;

import com.nadhem.users.registration.token.VerificationToken;
import com.nadhem.users.registration.token.VerificationTokenRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService{
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userRep;
	
	@Autowired
	RoleRepository roleRep;
	
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	VerificationTokenRepository tokenRepository;
	
	@Override
	public UserDTO saveUser(UserDTO user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return convertEntityToDto(userRep.save(convertDtoToEntity(user)));
	}
	
	@Override
	public UserDTO getUser(Long id) {
		return convertEntityToDto(userRep.findById(id).get());
	}
	
	@Override
	public List<UserDTO> getAllUsers() {
		return userRep.findAll().stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
	}
	

	@Override
	public User convertDtoToEntity(UserDTO userDto) {
		User user = new  User();
		user=modelMapper.map(userDto, User.class);
		return user;
	}
	
	@Override
	public UserDTO convertEntityToDto(User user) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		UserDTO userDTO =modelMapper.map(user, UserDTO.class);
		return userDTO;

	}
	
	@Override
	public void deleteUser(Long userId) {
	    // Check if the user exists
	    Optional<User> userOptional = userRep.findById(userId);
	    if (userOptional.isPresent()) {
	        User user = userOptional.get();

	        // Delete the user
	        userRep.delete(user);
	    } 
	}
	


	

	@Override
	public User findUserByUsername(String username) {	
		return userRep.findByUsername(username);
	}

	@Override
	public User registerUser(RegistrationRequest request) {
		Optional<User> user=this.findByEmail(request.getEmail());
		if(user.isPresent()) {
			throw new UserAlreadyExistsException(
					"User with email" +request.getEmail()+"already exists");
		}
		User newUser =new User();
		newUser.setUsername(request.getUsername());
		newUser.setEmail(request.getEmail());
		newUser.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
		 List<Role> roles = new ArrayList<>();
		 Role defaultRole = roleRep.getReferenceById((long) 2);
		 roles.add(defaultRole);
		newUser.setRoles(roles);
		return userRep.save(newUser);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRep.findByEmail(email);
	}

	@Override
	public List<User> getUsers() {
		return userRep.findAll();
	}

	@Override
	public void saveUserVerificationToken(User theUser, String token) {
		VerificationToken verificationToken =new VerificationToken(token,theUser);
		tokenRepository.save(verificationToken);
		
	}

	@Override
	public String validateToken(String theToken) {
		VerificationToken token=tokenRepository.findByToken(theToken);
		if(token == null) {
			return "Invalid verification token";
		}
		User user =token.getUser();
		Calendar calendar =Calendar.getInstance();
		if((token.getExpirationTime().getTime()-calendar.getTime().getTime())<=0) {
			tokenRepository.delete(token);
			return "Token already expired";
		}
		
		user.setEnabled(true);
		userRep.save(user);
		return "Valid";
	}

	  public void removeRoleFromUser(String username, Role role) {
	        User user = userRep.findByUsername(username);

	        if (user != null) {
	            // Check if the user has the role before removing it
	            if (user.getRoles().contains(role)) {
	                user.getRoles().remove(role);
	                userRep.save(user);
	            }
	        }
	    }
    @Override
    public User addRoleToUser(String username, Role r) {
        User usr = userRep.findByUsername(username);

        List<Role> roles = usr.getRoles();
        roles.add(r);

        usr.setRoles(roles);

        return userRep.save(usr);
    }
	
    @Override
    public Role addRole(Role role) {
        return roleRep.save(role);
    }

	@Override
	public List<Role> findAllRoles() {
		return roleRep.findAll();
	}
	






}
