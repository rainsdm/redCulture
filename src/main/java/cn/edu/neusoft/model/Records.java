package cn.edu.neusoft.model;

import java.sql.Timestamp;

public class Records {
	private int record_id;
	private String user_id;
	private int spot_id;
	private Timestamp produce_time;
	private String learn_note;

	public Records() {
	}

	public Records(int record_id, String user_id, int spot_id, Timestamp produce_time, String learn_note) {
		this.record_id = record_id;
		this.user_id = user_id;
		this.spot_id = spot_id;
		this.produce_time = produce_time;
		this.learn_note = learn_note;
	}

	public int getRecord_id() {
		return record_id;
	}

	public void setRecord_id(int record_id) {
		this.record_id = record_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getSpot_id() {
		return spot_id;
	}

	public void setSpot_id(int spot_id) {
		this.spot_id = spot_id;
	}

	public Timestamp getProduce_time() {
		return produce_time;
	}

	public void setProduce_time(Timestamp produce_time) {
		this.produce_time = produce_time;
	}

	public String getLearn_note() {
		return learn_note;
	}

	public void setLearn_note(String learn_note) {
		this.learn_note = learn_note;
	}

	@Override
	public String toString() {
		return "Records{" + "record_id=" + record_id + ", user_id=" + user_id + ", spot_id=" + spot_id
				+ ", produce_time=" + produce_time + ", learn_note='" + learn_note + '\'' + '}';
	}
}
