package cn.edu.neusoft.controller;

import cn.edu.neusoft.dao.RecordDao;
import cn.edu.neusoft.view.ManageRecordView;
import cn.edu.neusoft.view.ManageUserView;

public class ManagerRecordC {
    RecordDao rd = new RecordDao();
    //<editor-fold desc="管理学习记录">
    public void searchAllRecordsC() {
        ManageRecordView.showAllRecordView(rd.searchAllRecord());
    }
    //</editor-fold>
}
