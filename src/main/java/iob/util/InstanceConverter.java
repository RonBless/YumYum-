package iob.util;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import iob.boundary.InstanceBoundary;
import iob.boundary.InstanceIDBoundary;
import iob.boundary.LocationBoundary;
import iob.boundary.UserIDBoundary;
import iob.boundary.UserIDWrapperBoundary;
import iob.data.InstanceEntity;

@Component
public class InstanceConverter {

	private String domain;
	private String splitChar;

	@Value("${entity.split.character}")
	public void setSplitChar(String splitChar) {
		this.splitChar = splitChar;
	}

	@Value("${spring.application.name}")
	public void setDomain(String domain) {
		this.domain = domain;
	}

	public InstanceBoundary toBoundary(InstanceEntity entity) {
		InstanceBoundary boundary = new InstanceBoundary();
		boundary.setActive(entity.getActive());
		boundary.setCreatedBy(new UserIDWrapperBoundary(
				new UserIDBoundary(entity.getDomainId().split(splitChar)[0], entity.getUserEmail())));
		boundary.setCreatedTimestamp(entity.getCreatedTimestamp());
		boundary.setInstanceId(new InstanceIDBoundary(entity.getDomainId().split(splitChar)[0],
				entity.getDomainId().split(splitChar)[1]));
		boundary.setInstanceAttributes(entity.getInstanceAttributes());
		boundary.setLocation(new LocationBoundary(entity.getLat(), entity.getLng()));
		boundary.setName(entity.getName());
		boundary.setType(entity.getType());
		return boundary;
	}

	public InstanceEntity toEntity(InstanceBoundary boundary) {
		InstanceEntity instanceEntity = new InstanceEntity();
		if (boundary.getInstanceId() != null && boundary.getInstanceId().getDomain() != null
				&& !boundary.getInstanceId().getDomain().trim().isEmpty() && boundary.getInstanceId().getId() != null
				&& !boundary.getInstanceId().getId().trim().isEmpty()) {
			long id;
			try {
				id = Long.parseLong(boundary.getInstanceId().getId());
			} catch (NumberFormatException e) {
				id = -1;
			}
			instanceEntity.setDomainId(boundary.getInstanceId().getDomain() + splitChar + id);
		} else
			instanceEntity.setDomainId(domain + splitChar + -1);
		if (boundary.getType() != null && !boundary.getType().trim().isEmpty())
			instanceEntity.setType(boundary.getType());
		else
			instanceEntity.setType("");
		if (boundary.getName() != null && !boundary.getName().trim().isEmpty())
			instanceEntity.setName(boundary.getName());
		else
			instanceEntity.setName("");
		if (boundary.getActive() != null)
			instanceEntity.setActive(boundary.getActive());
		if (boundary.getCreatedTimestamp() != null)
			instanceEntity.setCreatedTimestamp(boundary.getCreatedTimestamp());
		else
			instanceEntity.setCreatedTimestamp(Date.from(Instant.now()));
		UserIDBoundary userIDBoundary = null;
		if (boundary.getCreatedBy() != null)
			userIDBoundary = boundary.getCreatedBy().getUserId();
		if (userIDBoundary != null && userIDBoundary.getDomain() != null
				&& !userIDBoundary.getDomain().trim().isEmpty())
			instanceEntity.setUserDomain(userIDBoundary.getDomain());
		else
			instanceEntity.setUserDomain(domain);
		if (userIDBoundary != null && userIDBoundary.getEmail() != null && !userIDBoundary.getEmail().trim().isEmpty())
			instanceEntity.setUserEmail(userIDBoundary.getEmail());
		else
			instanceEntity.setUserEmail("default");
		if (boundary.getLocation() != null) {
			instanceEntity.setLat(boundary.getLocation().getLat());
			instanceEntity.setLng(boundary.getLocation().getLng());
		}
		if (boundary.getInstanceAttributes() != null)
			instanceEntity.setInstanceAttributes(boundary.getInstanceAttributes());
		else
			instanceEntity.setInstanceAttributes(new HashMap<String, Object>());
		return instanceEntity;

	}

}
