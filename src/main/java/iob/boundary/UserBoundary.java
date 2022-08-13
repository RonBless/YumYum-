package iob.boundary;

public class UserBoundary {
	private UserIDBoundary userId;
	private String role;
	private String username;
	private String avatar;

	public UserBoundary(NewUserBoundary newUserBoundary, String domain) {
		this.userId = new UserIDBoundary(domain, newUserBoundary.getEmail());
		role = newUserBoundary.getRole();
		username = newUserBoundary.getUsername();
		avatar = newUserBoundary.getAvatar();
	}

	public UserBoundary(UserIDBoundary userId, String role, String username, String avatar) {
		this.userId = userId;
		this.role = role;
		this.username = username;
		this.avatar = avatar;
	}
	
	public UserBoundary() {

	}

	public UserIDBoundary getUserId() {
		return userId;
	}

	public String getRole() {
		return role;
	}

	public String getUsername() {
		return username;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setUserId(UserIDBoundary userId) {
		this.userId = userId;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
}
