package cn.edu.neusoft.controller;

import cn.edu.neusoft.dao.RecordDao;
import cn.edu.neusoft.dao.SpotDao;
import cn.edu.neusoft.dao.UserDao;
import cn.edu.neusoft.model.Records;
import cn.edu.neusoft.model.Spot;
import cn.edu.neusoft.view.SpotLearnView;

import java.sql.Timestamp;
import java.util.List;

public class SpotLearnC {
	SpotDao sd = new SpotDao();
	RecordDao rd = new RecordDao();

	public void showSpots(int nextOperator, String user_Id) {
		boolean continueWork = true;
		int page = 0, pageSize = 3; // 暂时放弃启动时，遍历所有页面的功能。page = 0时，表示还没有开始翻页。
		while (continueWork) {
			List<Spot> spots = null;
			switch (nextOperator) {
			case 0:
				continueWork = false;
				break;
			case 1:
				// 上一页
				if (page > 1) {
					page--;
				} else {
					page = 1;
					System.out.println("已经是第一页了！");
				}
				spots = sd.searchAllSpots(page, pageSize);
				nextOperator = SpotLearnView.showSpotsView(spots);
				break;
			case 2:
				// 下一页
//                    page < (sd.getSpots_count() / pageSize)
				if (page < (int) Math.ceil((double) sd.getSpots_count() / pageSize)) { // 向上取整，防止意外错误。
					page++;
				} else {
					page = sd.getSpots_count() / pageSize + 1; // 利用了整数除法会自动抹零的特性。它计算翻页后的起始索引。
					System.out.println("已经是最后一页了！");
				}
				spots = sd.searchAllSpots(page, pageSize);
				nextOperator = SpotLearnView.showSpotsView(spots);
				break;
			case 3:
				// 精确查询
				if (learnSpot(user_Id) == 0) {
					continueWork = false;
				}
				break;
			}
		}
	}

	public int learnSpot(String user_ID) {
		int spotId = SpotLearnView.chooseSpot();

		Spot spot = sd.searchSpotById(spotId);

		int menu = SpotLearnView.showSpotDetail(spot);

		Records record = new Records();
		record.setUser_id(user_ID);
		record.setSpot_id(spotId);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		record.setProduce_time(ts);

		RecordDao rd = new RecordDao();
		int newRecordID = rd.addRecord(record);

		UserDao ud = new UserDao();
		ud.addPoints(user_ID, 2);

		if (menu == 1) {
			writeNote(newRecordID, spotId, user_ID);
			menu = 0; // 笔记发表完成后，自动退出程序。
		}

		return menu;
	}

	public void writeNote(int record_ID, int spot_ID, String user_ID) {
		String note = SpotLearnView.writeNoteView();

		Records record = new Records();
		record.setRecord_id(record_ID); // 修正bug的关键代码。
		record.setUser_id(user_ID);
		record.setSpot_id(spot_ID);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		record.setProduce_time(ts);
		record.setLearn_note(note);

		RecordDao rd = new RecordDao();
		int num = rd.addNote(record);
		if (num == 1) {
			System.out.println("笔记发表成功! ");

			UserDao ud = new UserDao();
			ud.addPoints(user_ID, 3);
		} else {
			System.out.println("笔记发表失败! ");
		}
	}
}
