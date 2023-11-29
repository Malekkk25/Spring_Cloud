package com.nadhem.users.restControllers;

import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nadhem.users.dto.UserDTO;
import com.nadhem.users.entities.Role;
import com.nadhem.users.entities.User;
import com.nadhem.users.repos.UserRepository;
import com.nadhem.users.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200/")
public class UserRestController {
	
	@Autowired
	UserRepository userRep;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(path = "all",method = RequestMethod.GET)
	public List<UserDTO> getAllUser() {
		return userService.getAllUsers();
	 }
	
	
	@RequestMapping(value="/getbyid/{idUser}",method=RequestMethod.GET)
	public UserDTO getUserById(@PathVariable("idUser") Long idUser) {
		return userService.getUser(idUser);
	}
	
	@RequestMapping(value="/{idUser}/roles",method=RequestMethod.GET)
	public List<Role> getUserRoles(@PathVariable("idUser") Long idUser) {
		UserDTO user =userService.getUser(idUser);
		List<Role> roles =user.getRoles();
		return roles;
	
	}
	
	@RequestMapping(path = "users",method = RequestMethod.GET)
	public List<User> getUsers() {
		return userService.getUsers();
	 }
	@RequestMapping(path="deleteUserById/{id}",method=RequestMethod.DELETE)
    public void deleteUserById(@PathVariable long id) {
        userService.deleteUser(id);
    }
	
    @RequestMapping(path="/removeRoleFromUser/{username}",method=RequestMethod.POST)
    public void removeRole(@PathVariable String username,@RequestBody Role r)
    {
         userService.removeRoleFromUser(username,r);

    }
    @RequestMapping(path="allRoles",method=RequestMethod.GET)
    public List<Role> getAllRoles() {
        return userService.findAllRoles();
    }
	@RequestMapping(value="/findUserByUsername/{username}",method=RequestMethod.GET)
	public User getUserByUsername(@PathVariable("username") String username) {
		return userService.findUserByUsername(username);
	}
	@RequestMapping(path="addRole/{username}",method=RequestMethod.POST)
    public User addRoleToUser(@PathVariable String username,@RequestBody Role r) {
        return userService.addRoleToUser(username, r);
    }
	
	
	
}
