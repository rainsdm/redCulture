package cn.edu.neusoft.model;

public class Spot {
	private String spot_id;
	private String spot_name;
	private String location;
	private String history;

	public Spot() {
	}

	public Spot(String spot_id, String spot_name, String location, String history) {
		this.spot_id = spot_id;
		this.spot_name = spot_name;
		this.location = location;
		this.history = history;
	}

	public String getSpot_id() {
		return spot_id;
	}

	public void setSpot_id(String spot_id) {
		this.spot_id = spot_id;
	}

	public String getSpot_name() {
		return spot_name;
	}

	public void setSpot_name(String spot_name) {
		this.spot_name = spot_name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	@Override
	public String toString() {
		return "Spot{" + "spot_id='" + spot_id + '\'' + ", spot_name='" + spot_name + '\'' + ", location='" + location
				+ '\'' + ", history='" + history + '\'' + '}';
	}
}
