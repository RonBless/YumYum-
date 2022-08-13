package iob.boundary;

import java.util.Date;
import java.util.Map;

public class InstanceBoundary {
	private InstanceIDBoundary instanceId;
	private String type;
	private String name;
	private Boolean active;
	private Date createdTimestamp;
	private UserIDWrapperBoundary createdBy;
	private LocationBoundary location;
	private Map<String, Object> instanceAttributes;

	public InstanceBoundary(InstanceIDBoundary instanceId, String type, String name, Boolean active,
			Date createdTimestamp, UserIDWrapperBoundary createdBy, LocationBoundary location,
			Map<String, Object> instanceAttributes) {
		this.instanceId = instanceId;
		this.type = type;
		this.name = name;
		this.active = active;
		this.createdTimestamp = createdTimestamp;
		this.createdBy = createdBy;
		this.location = location;
		this.instanceAttributes = instanceAttributes;
	}

	public InstanceBoundary() {
	}

	public InstanceBoundary(String type, String name, Boolean active, Date createdTimestamp,
			UserIDWrapperBoundary createdBy, LocationBoundary location, Map<String, Object> instanceAttributes) {
		this(null, type, name, active, createdTimestamp, createdBy, location, instanceAttributes);
	}

	public InstanceIDBoundary getInstanceId() {
		return instanceId;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public Boolean getActive() {
		return active;
	}

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public UserIDWrapperBoundary getCreatedBy() {
		return createdBy;
	}

	public LocationBoundary getLocation() {
		return location;
	}

	public Map<String, Object> getInstanceAttributes() {
		return instanceAttributes;
	}

	public void setInstanceId(InstanceIDBoundary instanceId) {
		this.instanceId = instanceId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public void setCreatedBy(UserIDWrapperBoundary createdBy) {
		this.createdBy = createdBy;
	}

	public void setLocation(LocationBoundary location) {
		this.location = location;
	}

	public void setInstanceAttributes(Map<String, Object> instanceAttributes) {
		this.instanceAttributes = instanceAttributes;
	}

}
