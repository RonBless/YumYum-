package iob.data;

import java.util.Date;
import java.util.Map;
import iob.util.GeneralFieldConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Lob;

@Entity
@Table(name = "Activity")
public class ActivityEntity {
	private String domainId;
	private String type;
	private String instanceDomain;
	private String instanceId;
	private Date createdTimestamp;
	private String userDomain;
	private String userEmail;
	private Map<String, Object> activityAttributes;

	public ActivityEntity() {
	}

	public ActivityEntity(String domainId, String type, String instanceDomain, String instanceId,
			Date createdTimestamp, String userDomain, String userEmail, Map<String, Object> activityAttributes) {
		this.domainId = domainId;
		this.type = type;
		this.instanceDomain = instanceDomain;
		this.instanceId = instanceId;
		this.createdTimestamp = createdTimestamp;
		this.userDomain = userDomain;
		this.userEmail = userEmail;
		this.activityAttributes = activityAttributes;
	}
	@Id
	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "INSTANCE_DOMAIN")
	public String getInstanceDomain() {
		return instanceDomain;
	}

	public void setInstanceDomain(String instanceDomain) {
		this.instanceDomain = instanceDomain;
	}
	@Column(name = "INSTANCE_ID")
	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	@Column(name = "CREATED_TIMESTAMP")
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	@Column(name = "USER_DOMAIN")
	public String getUserDomain() {
		return userDomain;
	}

	public void setUserDomain(String userDomain) {
		this.userDomain = userDomain;
	}
	@Column(name = "USER_EMAIL")
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	@Convert(converter = GeneralFieldConverter.class)
	@Lob
	public Map<String, Object> getActivityAttributes() {
		return activityAttributes;
	}

	public void setActivityAttributes(Map<String, Object> activityAttributes) {
		this.activityAttributes = activityAttributes;
	}

}
