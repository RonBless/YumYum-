package iob.boundary;

public class InstanceIDWrapperBoundary {
	private InstanceIDBoundary instanceId;

	public InstanceIDBoundary getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(InstanceIDBoundary instanceId) {
		this.instanceId = instanceId;
	}

	public InstanceIDWrapperBoundary(InstanceIDBoundary instanceId) {
		this.instanceId = instanceId;
	}

	public InstanceIDWrapperBoundary() {
	}
}
