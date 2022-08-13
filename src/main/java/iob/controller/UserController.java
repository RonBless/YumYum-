package iob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import iob.boundary.NewUserBoundary;
import iob.boundary.UserBoundary;
import iob.logic.UsersService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
	private UsersService usersService;

	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping(path = "/iob/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary createNewUser(@RequestBody NewUserBoundary input) {
		UserBoundary u = new UserBoundary(input, "");
		return usersService.createUser(u);
	}

	@RequestMapping(path = "/iob/users/login/{userDomain}/{userEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary loginAndRetriveValidUser(@PathVariable("userDomain") String domain,
			@PathVariable("userEmail") String email) {
		return usersService.login(domain, email);
	}

	@RequestMapping(path = "/iob/users/{userDomain}/{userEmail}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateUserDetails(@PathVariable("userDomain") String domain, @PathVariable("userEmail") String email,
			@RequestBody UserBoundary input) {
		usersService.updateUser(domain, email, input);
	}
}
