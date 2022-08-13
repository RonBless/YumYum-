package iob.boundary;

public class UserIDWrapperBoundary {
	private UserIDBoundary userId;

	public UserIDBoundary getUserId() {
		return userId;
	}

	public void setUserId(UserIDBoundary userId) {
		this.userId = userId;
	}

	public UserIDWrapperBoundary(UserIDBoundary userId) {
		this.userId = userId;
	}

	public UserIDWrapperBoundary() {

	}

}
