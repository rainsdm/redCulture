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
        int page = 0, pageSize = 6; // 暂时放弃启动时，遍历所有页面的功能。page = 0时，表示还没有开始翻页。
        while (continueWork) {
            List<Spot> spots = null;
            switch(nextOperator) {
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
                    //page < (sd.getSpots_count() / pageSize)
                    if (page < (int) Math.ceil((double) sd.getSpots_count() / pageSize)) { // 向上取整，防止意外错误。
                        page++;
                    } else {
                        page = sd.getSpots_count() / pageSize + 1;
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
        rd.addRecord(record);

        UserDao ud = new UserDao();
        ud.addPoints(user_ID, 2);

        if (menu == 1) {
            writeNote(user_ID, spotId);
            menu = 0; // 笔记发表完成后，自动退出程序。
        }

        return menu;
    }

    public void writeNote(String  user_ID, int spot_ID) {
        String note = SpotLearnView.writeNoteView();

        Records record = new Records();
        record.setUser_id(user_ID);
        record.setSpot_id(spot_ID);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        record.setProduce_time(ts);
        record.setLearn_note(note);

        RecordDao rd = new RecordDao();
        int num =  rd.addNote(record);
        if (num == 1) {
            System.out.println("笔记发表成功! ");

            UserDao ud = new UserDao();
            ud.addPoints(user_ID, 3);
        } else {
            System.out.println("笔记发表失败! ");
        }
    }

    /**
     * 景点的查找学习记录。
     */
    public void searchRecordByTime() {
        List<String> timeList = SpotLearnView.searchByTimeView();

        Records rds =  new Records();
        List<Records> recordsList = rd.searchRecordByTime(timeList);

        SpotLearnView.showLearnRecord(recordsList);
    }

    public void searchAllRecords() {
        SpotLearnView.showLearnRecord(rd.searchAllRecord());
    }

    public void showPopularView() {
        SpotLearnView.showPopularSpot(sd.getPopularSpots());
    }
}
