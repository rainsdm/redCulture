package cn.edu.neusoft.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Spot {
	private String spot_id;
	private String spot_name;
	private String location;
	private String history;

	@Override
	public String toString() {
		return "Spot{" + "spot_id='" + spot_id + '\'' + ", spot_name='" + spot_name + '\'' + ", location='" + location
				+ '\'' + ", history='" + history + '\'' + '}';
	}
}
