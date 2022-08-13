package iob.util;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import iob.boundary.ActivityBoundary;
import iob.boundary.ActivityIDBoundary;
import iob.boundary.InstanceIDBoundary;
import iob.boundary.InstanceIDWrapperBoundary;
import iob.boundary.UserIDBoundary;
import iob.boundary.UserIDWrapperBoundary;
import iob.data.ActivityEntity;

@Component
public class ActivityConverter {
	
	private String domain;
	private String splitChar;
	
	@Value("${entity.split.character}")
	public void setSplitChar(String splitChar)
	{
		this.splitChar = splitChar;
	}
	
	@Value("${spring.application.name}")
	public void setDomain(String domain) {
		this.domain = domain;
	}

	public ActivityBoundary toBoundary(ActivityEntity entity) {
		ActivityBoundary result = new ActivityBoundary();
		result.setActivityId(new ActivityIDBoundary(entity.getDomainId().split(splitChar)[0], String.valueOf(entity.getDomainId().split(splitChar)[1])));
		result.setType(entity.getType());
		result.setInstance(new InstanceIDWrapperBoundary(
				new InstanceIDBoundary(entity.getInstanceDomain(), String.valueOf(entity.getInstanceId()))));
		result.setCreatedTimestamp(entity.getCreatedTimestamp());
		result.setInvokedBy(
				new UserIDWrapperBoundary(new UserIDBoundary(entity.getUserDomain(), entity.getUserEmail())));
		result.setActivityAttributes(entity.getActivityAttributes());
		return result;
	}

	public ActivityEntity toEntity(ActivityBoundary boundary) {
		ActivityEntity result = new ActivityEntity();
		if (boundary.getActivityId()!=null && boundary.getActivityId().getDomain() != null && !boundary.getActivityId().getDomain().trim().isEmpty() && boundary.getActivityId().getId()!=null && !boundary.getActivityId().getId().trim().isEmpty()) {
			long id;
			try {
				id = Long.parseLong(boundary.getActivityId().getId());
			} catch (NumberFormatException e) {
				id = -1;
			}
			result.setDomainId(boundary.getActivityId().getDomain() + splitChar + id);
		} else {
			result.setDomainId(domain + splitChar + -1);
		}
		if (boundary.getType() != null && !boundary.getType().trim().isEmpty()) {
			result.setType(boundary.getType());
		} else {
			result.setType("default");
		}
		InstanceIDBoundary instanceIDBoundary = null;
		if (boundary.getInstance() != null)
			instanceIDBoundary = boundary.getInstance().getInstanceId();
		if (instanceIDBoundary != null && instanceIDBoundary.getDomain() != null
				&& !instanceIDBoundary.getDomain().trim().isEmpty()) {
			result.setInstanceDomain(instanceIDBoundary.getDomain());
		} else {
			result.setInstanceDomain(domain);
		}
		if (instanceIDBoundary != null && instanceIDBoundary.getId() != null
				&& !instanceIDBoundary.getId().trim().isEmpty()) {
			try {
				result.setInstanceId(instanceIDBoundary.getId());
			} catch (NumberFormatException e) {
				result.setInstanceId("-1");
			}
		} else {
			result.setInstanceId("-1");
		}
		if (boundary.getCreatedTimestamp() != null) {
			result.setCreatedTimestamp(boundary.getCreatedTimestamp());
		} else {
			result.setCreatedTimestamp(Date.from(Instant.now()));
		}
		UserIDBoundary userIDBoundary = null;
		if (boundary.getInvokedBy() != null)
			userIDBoundary = boundary.getInvokedBy().getUserId();
		if (userIDBoundary != null && userIDBoundary.getEmail() != null
				&& !userIDBoundary.getEmail().trim().isEmpty()) {
			result.setUserEmail(userIDBoundary.getEmail());
		} else {
			result.setUserEmail("No mail specified");
		}
		if (userIDBoundary != null && userIDBoundary.getDomain() != null
				&& !userIDBoundary.getDomain().trim().isEmpty()) {
			result.setUserDomain(userIDBoundary.getDomain());
		} else {
			result.setUserDomain(domain);
		}
		if (boundary.getActivityAttributes() != null) {
			result.setActivityAttributes(boundary.getActivityAttributes());
		} else {
			result.setActivityAttributes(new HashMap<String, Object>());
		}
		return result;
	}
}
