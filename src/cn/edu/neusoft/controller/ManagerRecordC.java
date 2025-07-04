package cn.edu.neusoft.controller;

import cn.edu.neusoft.dao.RecordDao;
import cn.edu.neusoft.model.Records;
import cn.edu.neusoft.model.User;
import cn.edu.neusoft.view.ManageRecordView;

import java.util.List;

public class ManagerRecordC {
    RecordDao rd = new RecordDao();

    public void manageRecord(User loggedUser) {
        boolean loop = true;
        while (loop) {
            int menu = ManageRecordView.manageRecordView();
            switch (menu) {
            case 0: // 返回上一级
                loop = false;
                break;
            case 1: // 查找学习记录的汇总视图
                boolean searchOrNot = true;
                while (searchOrNot) {
                    int method = ManageRecordView.selectRecordMethod();
                    switch (method) {
                        case 0: // 结束查询
                            searchOrNot = false;
                            break;
                        case 1: // 显示所有学习记录
                            searchAllRecordsC(loggedUser); // 普通用户只能查询自己的学习记录。
                            break;
                    }
                }
                break;
            case 2: // 删除学习记录
                deleteRecordByIdC();
                break;
            }
        }
    }

    public void recordOperation() {}

    public void deleteRecordByIdC() {
        if (rd.deleteRecord(ManageRecordView.deleteRecordByRecordIDView())) {
            System.out.println("记录删除成功！");
        } else {
            System.out.println("记录删除失败！");
        }
    }

    //<editor-fold desc="查找学习记录">
    public void searchAllRecordsC(User loggedUser) {
        ManageRecordView.showAllRecordView(rd.searchAllRecord(loggedUser));
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
