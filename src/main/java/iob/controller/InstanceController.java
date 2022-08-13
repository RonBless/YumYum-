package iob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import iob.boundary.InstanceBoundary;
import iob.boundary.LocationBoundary;
import iob.logic.EnchancedInstanceService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class InstanceController {
	private EnchancedInstanceService instanceService;

	@Autowired
	public void setInstancesService(EnchancedInstanceService instanceService) {
		this.instanceService = instanceService;
	}

	@RequestMapping(path = "/iob/instances", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public InstanceBoundary createInstance(@RequestBody InstanceBoundary input)  {
		return instanceService.createInstance(input);
	}

	@RequestMapping(path = "/iob/instances/{instanceDomain}/{instanceId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateInstance(@PathVariable("instanceDomain") String instanceDomain,
			@PathVariable("instanceId") String instanceId, @RequestBody InstanceBoundary inputInstance,@RequestParam(name = "userDomain", required = false) String userDomain,@RequestParam(name="userEmail", required = false) String userEmail) {
		instanceService.updateInstance(instanceDomain, instanceId, inputInstance,userDomain, userEmail);
	}

	@RequestMapping(path = "/iob/instances/{instanceDomain}/{instanceId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public InstanceBoundary retrieveInstance(@PathVariable("instanceDomain") String instanceDomain,
			@PathVariable("instanceId") String instanceId, @RequestParam(name = "userDomain", required = false) String userDomain,@RequestParam(name="userEmail", required = false) String userEmail,
			@RequestParam(name="size", required = false, defaultValue = "10") int size, @RequestParam(name="page", required = false, defaultValue= "0") int page) 
	{
		return this.instanceService.getSpecificInstance(instanceDomain, instanceId,userDomain, userEmail);
	}

	@RequestMapping(path = "/iob/instances", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public InstanceBoundary[] getAllInstances(@RequestParam(name="size", required = false, defaultValue = "10") int size, @RequestParam(name="page", required = false, defaultValue= "0") int page,@RequestParam(name = "userDomain", required = false) String userDomain,@RequestParam(name="userEmail", required = false) String userEmail) {
		List<InstanceBoundary> instances = instanceService.getAllInstances(size,page,userEmail, userDomain);
		InstanceBoundary [] arr = new InstanceBoundary[instances.size()];
		instances.toArray(arr);
		return arr;
	}
	
	@RequestMapping(path = "/iob/instances/search/byName/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public InstanceBoundary[] getAllInstancesWithSpecificName(@PathVariable("name") String name, @RequestParam(name="userEmail", required = false) String email,
			@RequestParam(name = "userDomain", required = false) String domain,
			@RequestParam(name="size", required = false, defaultValue = "10") int size, @RequestParam(name="page", required = false, defaultValue= "0") int page) {
		List<InstanceBoundary> instances = instanceService.getAllInstancesBySpecificName(name, size,page,email,domain);
		return instances.toArray(new InstanceBoundary[0]);
	}
	
	@RequestMapping(path = "/iob/instances/search/byType/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public InstanceBoundary[] getAllInstancesWithSpecificType(@PathVariable("type") String type, @RequestParam(name="userEmail", required = false) String email,
			@RequestParam(name = "userDomain", required = false) String domain,
			@RequestParam(name="size", required = false, defaultValue = "10") int size, @RequestParam(name="page", required = false, defaultValue= "0") int page) {
		List<InstanceBoundary> instances = instanceService.getAllInstancesBySpecificType(type, size, page, email, domain);
		return instances.toArray(new InstanceBoundary[0]);
	}
	
	@RequestMapping(path = "/iob/instances/search/near/{lat}/{lng}/{distance}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public InstanceBoundary[] getAllInstancesNear(@PathVariable("lat") Double lat, @PathVariable("lng") Double lng, @PathVariable("distance") Double distance, @RequestParam(name="userEmail", required = false) String email,
			@RequestParam(name = "userDomain", required = false) String domain,
			@RequestParam(name="size", required = false, defaultValue = "10") int size, @RequestParam(name="page", required = false, defaultValue= "0") int page) {
		List<InstanceBoundary> instances = instanceService.getAllInstancesNear(new LocationBoundary(lat, lng), size, page, email, domain,distance.doubleValue());
		return instances.toArray(new InstanceBoundary[0]);
	}

}
