package iob.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import iob.boundary.UserBoundary;
import iob.boundary.UserIDBoundary;
import iob.data.UserEntity;
import iob.data.UserRole;

@Component
public class UserConverter {
	private String splitChar;

	@Value("${entity.split.character}")
	public void setSplitChar(String splitChar) {
		this.splitChar = splitChar;
	}

	public UserBoundary toBoundary(UserEntity entity) {
		UserBoundary result = new UserBoundary();
		result.setUserId(new UserIDBoundary(entity.getDomainEmail().split(splitChar)[0],
				entity.getDomainEmail().split(splitChar)[1]));
		result.setRole(entity.getRole().toString());
		result.setUsername(entity.getUsername());
		result.setAvatar(entity.getAvatar());
		return result;

	}

	public UserEntity toEntity(UserBoundary boundary) {
		UserEntity result = new UserEntity();
		result.setDomainEmail(boundary.getUserId().getDomain() + splitChar + boundary.getUserId().getEmail());
		if (boundary.getRole() != null && !boundary.getRole().trim().isEmpty()) {
			result.setRole(UserRole.valueOf(boundary.getRole()));
		} else {
			result.setRole(UserRole.PLAYER);
		}
		if (boundary.getUsername() != null && !boundary.getUsername().trim().isEmpty()) {
			result.setUsername(boundary.getUsername());
		} else {
			result.setUsername("");
		}
		if (boundary.getAvatar() != null && !boundary.getAvatar().trim().isEmpty()) {
			result.setAvatar(boundary.getAvatar());
		} 
		return result;
	}
}
