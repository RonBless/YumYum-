package iob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import iob.boundary.ActivityBoundary;
import iob.boundary.UserBoundary;
import iob.logic.EnchancedActivitiesService;
import iob.logic.EnchancedInstanceService;
import iob.logic.EnchancedUserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AdminController {

	private EnchancedActivitiesService activitiesService;
	private EnchancedUserService usersService;
	private EnchancedInstanceService instanceService;

	@Autowired
	public void setActivitiesService(EnchancedActivitiesService activitiesService) {
		this.activitiesService = activitiesService;
	}

	@Autowired
	public void setUsersService(EnchancedUserService usersService) {
		this.usersService = usersService;
	}

	@Autowired
	public void setInstanceService(EnchancedInstanceService instanceService) {
		this.instanceService = instanceService;
	}

	@RequestMapping(path = "/iob/admin/users", method = RequestMethod.DELETE)
	public void deleteAllUsers(
			@RequestParam(name = "userDomain", required = false) String userDomain,
			@RequestParam(name = "userEmail", required = false) String userEmail)  {
		usersService.deleteAllUsers(userDomain,userEmail);
	}

	@RequestMapping(path = "/iob/admin/instances", method = RequestMethod.DELETE)
	public void deleteAllInstances(
			@RequestParam(name = "userDomain", required = false) String userDomain,
			@RequestParam(name = "userEmail", required = false) String userEmail)  {
		instanceService.deleteAllInstances(userDomain,userEmail);
	}

	@RequestMapping(path = "/iob/admin/activities", method = RequestMethod.DELETE)
	public void deleteAllactivities(
			@RequestParam(name = "userDomain", required = false) String userDomain,
			@RequestParam(name = "userEmail", required = false) String userEmail) {
		activitiesService.deleteAllActivities(userDomain, userEmail);
	}

	@RequestMapping(path = "/iob/admin/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary[] getAllUsers(@RequestParam(name="size", required = false, defaultValue = "10") int size, @RequestParam(name="page", required = false, defaultValue= "0") int page,
			@RequestParam(name = "userDomain", required = false) String userDomain,
			@RequestParam(name = "userEmail", required = false) String userEmail) {
		List<UserBoundary> result = usersService.getAllUsers(userDomain,userEmail,size,page);
		return result.toArray(new UserBoundary[0]);
	}

	@RequestMapping(path = "/iob/admin/activities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ActivityBoundary[] getAllActivities(
			@RequestParam(name = "userDomain", required = false) String userDomain,
			@RequestParam(name = "userEmail", required = false) String userEmail,@RequestParam(name="size", required = false, defaultValue = "10") int size, @RequestParam(name="page", required = false, defaultValue= "0") int page) {
		List<ActivityBoundary> result = activitiesService.getAllActivities(page, size,userEmail, userDomain);
		return result.toArray(new ActivityBoundary[0]);
	}
}
