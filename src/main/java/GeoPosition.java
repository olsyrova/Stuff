import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by syrovo01 on 04.10.2016.
 */
public class GeoPosition {
	private float latitude;
	private float longitude;

	@JsonProperty("latitude")
	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	@JsonProperty("longitude")
	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}


}
