package iob.data;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import iob.util.GeneralFieldConverter;

@Entity
@Table(name = "INSTANCES")
public class InstanceEntity {
	private String domainId;
	private String type;
	private String name;
	private Boolean active;
	private Date createdTimestamp;
	private String userDomain;
	private String userEmail;
	private double lat;
	private double lng;
	private Map<String, Object> instanceAttributes;
	public InstanceEntity() {
	}
	public InstanceEntity(String domainId, String type, String name, Boolean active, Date createdTimestamp,
			String userDomain, String userEmail, double lat, double lng, Map<String, Object> instanceAttributes) {
		this.domainId = domainId;
		this.type = type;
		this.name = name;
		this.active = active;
		this.createdTimestamp = createdTimestamp;
		this.userDomain = userDomain;
		this.userEmail = userEmail;
		this.lat = lat;
		this.lng = lng;
		this.instanceAttributes = instanceAttributes;
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
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	@Column(name = "INSTANCE_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	@Column(name = "CREATOR_USER_DOMAIN")
	public String getUserDomain() {
		return userDomain;
	}
	public void setUserDomain(String userDomain) {
		this.userDomain = userDomain;
	}
	@Column(name = "CREATOR_USER_EMAIL")
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	@Convert(converter = GeneralFieldConverter.class)
	@Lob
	public Map<String, Object> getInstanceAttributes() {
		return instanceAttributes;
	}
	public void setInstanceAttributes(Map<String, Object> instanceAttributes) {
		this.instanceAttributes = instanceAttributes;
	}
}
