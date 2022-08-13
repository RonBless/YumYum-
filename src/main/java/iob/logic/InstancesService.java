package iob.logic;

import java.util.List;
import iob.boundary.InstanceBoundary;


public interface InstancesService {
	
	public InstanceBoundary createInstance(InstanceBoundary instance);
	public InstanceBoundary updateInstance(String instanceDomain,String instanceID,InstanceBoundary update);
	public InstanceBoundary getSpecificInstance(String instanceDomain,String instanceID);
	public List<InstanceBoundary> getAllInstances();
	public void deleteAllInstances();
	
	
}
