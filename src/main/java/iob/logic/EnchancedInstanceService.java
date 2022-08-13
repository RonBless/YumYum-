package iob.logic;

import java.util.List;

import iob.boundary.InstanceBoundary;
import iob.boundary.LocationBoundary;

public interface EnchancedInstanceService extends InstancesService {
	public List<InstanceBoundary> getAllInstancesBySpecificName(String name, int size, int page, String userEmail, String userDomain);
	public List<InstanceBoundary> getAllInstancesBySpecificType(String type, int size, int page, String userEmail, String userDomain);
	public List<InstanceBoundary> getAllInstancesNear(LocationBoundary location, int size, int page, String userEmail, String userDomain, double distance);
	public List<InstanceBoundary> getAllInstances(int size, int page, String userEmail, String userDomain);
	public void deleteAllInstances(String userDomain, String userEmail);
	public InstanceBoundary createInstance(InstanceBoundary boundary);
	public InstanceBoundary updateInstance(String instanceDomain, String instanceID, InstanceBoundary update, String userDomain, String userEmail);
	public InstanceBoundary getSpecificInstance(String instanceDomain, String instanceID, String userDomain, String userEmail);
}
