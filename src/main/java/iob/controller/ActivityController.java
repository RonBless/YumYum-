package iob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import iob.boundary.ActivityBoundary;
import iob.logic.EnchancedActivitiesService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ActivityController {

	private EnchancedActivitiesService activitiesService;

	@Autowired
	public void setActivitiesService(EnchancedActivitiesService activitiesService) {
		this.activitiesService = activitiesService;
	}

	@RequestMapping(path = "/iob/activities", method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object invokeInstanceActivity(@RequestBody ActivityBoundary activityBoundary)  {
		return activitiesService.invokeActivity(activityBoundary);
	}
}
