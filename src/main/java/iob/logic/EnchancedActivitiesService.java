package iob.logic;

import java.util.List;

import iob.boundary.ActivityBoundary;

public interface EnchancedActivitiesService extends ActivitiesService {
	public List<ActivityBoundary> getAllActivities(int page, int size, String userEmail, String userDomain);
	public void deleteAllActivities(String userDomain, String userEmail);
	public Object invokeActivity(ActivityBoundary activity);
}
