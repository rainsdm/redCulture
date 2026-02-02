package cn.edu.neusoft.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Records {
	private int record_id;
	private String user_id;
	private int spot_id;
	private Timestamp produce_time;
	private String learn_note;

	@Override
	public String toString() {
		return "Records{" + "record_id=" + record_id + ", user_id=" + user_id + ", spot_id=" + spot_id
				+ ", produce_time=" + produce_time + ", learn_note='" + learn_note + '\'' + '}';
	}
}
