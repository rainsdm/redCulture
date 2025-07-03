package cn.edu.neusoft.controller;

import cn.edu.neusoft.dao.RecordDao;
import cn.edu.neusoft.model.Records;
import cn.edu.neusoft.view.ManageRecordView;

import java.util.List;

public class ManagerRecordC {
    RecordDao rd = new RecordDao();
    //<editor-fold desc="管理学习记录">
    public void searchAllRecordsC() {
        ManageRecordView.showAllRecordView(rd.searchAllRecord());
    }

    public void searchRecordByRecordIDC() {
        Records record = rd.searchRecordByRecordID(ManageRecordView.searchRecordByRecordIDView());
        ManageRecordView.showRecordView(record);
    }

    public void searchRecordBySpotIDC() {
        List<Records> records = rd.searchRecordBySpotID(ManageRecordView.searchRecordBySpotIDView());
        ManageRecordView.showAllRecordView(records);
    }
    //</editor-fold>
}
