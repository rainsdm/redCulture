package cn.edu.neusoft.test;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class TimeStampTest {
    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        System.out.println(timestamp);

        System.out.println(timestamp.toLocalDateTime().format(dtf));
    }
}
