package iob.boundary;

public class UserIDBoundary {
	private String domain;
	private String email;

	public UserIDBoundary(String domain, String email) {
		this.domain = domain;
		this.email = email;
	}

	public UserIDBoundary() {

	}

	public String getDomain() {
		return domain;
	}

	public String getEmail() {
		return email;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
