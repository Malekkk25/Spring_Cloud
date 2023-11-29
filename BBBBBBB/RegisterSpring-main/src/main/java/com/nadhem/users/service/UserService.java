package com.nadhem.users.service;

import java.util.List;
import java.util.Optional;

import com.nadhem.users.dto.UserDTO;
import com.nadhem.users.entities.Role;
import com.nadhem.users.entities.User;
import com.nadhem.users.registration.RegistrationRequest;
import com.nadhem.users.registration.token.VerificationToken;

public interface UserService {
	UserDTO saveUser(UserDTO user);
	UserDTO getUser(Long id);
	List<UserDTO> getAllUsers();
	UserDTO convertEntityToDto (User user);
	User convertDtoToEntity(UserDTO userDto);
	User findUserByUsername (String username);
	Role addRole(Role role);
	User addRoleToUser(String username, Role r);
	
	List<User> getUsers();
	User registerUser(RegistrationRequest request);
	Optional <User> findByEmail(String email);
	void saveUserVerificationToken(User theUser, String verificationToken);
	String validateToken(String theToken);
	void deleteUser(Long userId);
	List<Role> findAllRoles();
	void removeRoleFromUser(String username, Role r);
	
	
	
	
	
}
