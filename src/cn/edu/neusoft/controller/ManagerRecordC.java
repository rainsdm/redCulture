package cn.edu.neusoft.controller;

import cn.edu.neusoft.dao.RecordDao;
import cn.edu.neusoft.model.Records;
import cn.edu.neusoft.view.ManageRecordView;

import java.util.List;

public class ManagerRecordC {
    RecordDao rd = new RecordDao();

    public void deleteRecordByIdC() {
        if (rd.deleteRecord(ManageRecordView.deleteRecordByRecordIDView())) {
            System.out.println("记录删除成功！");
        } else {
            System.out.println("记录删除失败！");
        }
    }

    //<editor-fold desc="查找学习记录">
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
