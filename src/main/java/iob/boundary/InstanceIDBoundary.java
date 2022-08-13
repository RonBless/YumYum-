package iob.boundary;

public class InstanceIDBoundary {
	private String domain;
	private String id;

	public String getDomain() {
		return domain;
	}

	public String getId() {
		return id;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public void setId(String id) {
		this.id = id;
	}

	public InstanceIDBoundary(String domain, String id) {
		this.domain = domain;
		this.id = id;
	}

	public InstanceIDBoundary() {

	}
}
