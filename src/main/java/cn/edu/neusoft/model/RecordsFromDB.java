package cn.edu.neusoft.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordsFromDB {
	private String record_id;
	private String user_id;
	private String spot_id;
	private Timestamp produce_time;
	private String learn_note;

	@Override
	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
		produce_time.toLocalDateTime().format(dtf);

		return "Record{" + "record_id='" + record_id + '\'' + ", user_id='" + user_id + '\'' + ", spot_id='" + spot_id
				+ '\'' + ", produce_time='" + produce_time + '\'' + ", learn_note='" + learn_note + '\'' + '}';
	}
}
