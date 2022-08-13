package iob.boundary;

import java.util.Date;
import java.util.Map;

public class ActivityBoundary {
	private ActivityIDBoundary activityId;
	private String type;
	private InstanceIDWrapperBoundary instance;
	private Date createdTimestamp;
	private UserIDWrapperBoundary invokedBy;
	private Map<String, Object> activityAttributes;

	public ActivityIDBoundary getActivityId() {
		return activityId;
	}

	public String getType() {
		return type;
	}

	public InstanceIDWrapperBoundary getInstance() {
		return instance;
	}

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public UserIDWrapperBoundary getInvokedBy() {
		return invokedBy;
	}

	public Map<String, Object> getActivityAttributes() {
		return activityAttributes;
	}
	
	public void setActivityId(ActivityIDBoundary activityId) {
		this.activityId = activityId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setInstance(InstanceIDWrapperBoundary instance) {
		this.instance = instance;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public void setInvokedBy(UserIDWrapperBoundary invokedBy) {
		this.invokedBy = invokedBy;
	}

	public void setActivityAttributes(Map<String, Object> activityAttributes) {
		this.activityAttributes = activityAttributes;
	}

	public ActivityBoundary(ActivityIDBoundary activityId, String type, InstanceIDWrapperBoundary instance,
			Date createdTimestamp, UserIDWrapperBoundary invokedBy, Map<String, Object> activityAttributes) {
		this.activityId = activityId;
		this.type = type;
		this.instance = instance;
		this.createdTimestamp = createdTimestamp;
		this.invokedBy = invokedBy;
		this.activityAttributes = activityAttributes;
	}

	public ActivityBoundary() {

	}
}