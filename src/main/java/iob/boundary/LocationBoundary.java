package iob.boundary;

public class LocationBoundary {
	private Double lat;
	private Double lng;

	public LocationBoundary(double lat, double lng) {
		this.lat = lat;
		this.lng = lng;
	}

	public LocationBoundary() {
	}

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

}