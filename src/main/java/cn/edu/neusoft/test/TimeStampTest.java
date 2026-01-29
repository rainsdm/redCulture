package cn.edu.neusoft.test;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class TimeStampTest {
	void main() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		IO.println(timestamp);

		IO.println(timestamp.toLocalDateTime().format(dtf));
	}
}
