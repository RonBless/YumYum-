package iob.boundary;

public class ActivityIDBoundary 
{
	public ActivityIDBoundary()
	{	
	}
	public ActivityIDBoundary(String domain, String id) {
		this.domain = domain;
		this.id = id;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDomain() {
		return domain;
	}
	public String getId() {
		return id;
	}
	private String domain;
	private String id;
}
