package domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by syrovo01 on 04.10.2016.
 */
public class GeoPosition {
	private float latitude;
	private float longitude;


    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @JsonProperty("latitude")

	public float getLatitude() {
		return latitude;
	}

	@JsonProperty("longitude")
	public float getLongitude() {
		return longitude;
	}



}
