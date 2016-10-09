package domain;

/**
 * Created by syrovo01 on 04.10.2016.
 */
public class City {
	private int _id;
	private String name;
	private String type;
	private GeoPosition geo_position;


    public void set_id(int _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setGeo_position(GeoPosition geo_position) {
        this.geo_position = geo_position;
    }

    public int get_id() {

		return _id;
	}

	public GeoPosition getGeo_position() {
		return geo_position;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

}
