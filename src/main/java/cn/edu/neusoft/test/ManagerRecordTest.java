package cn.edu.neusoft.test;

import cn.edu.neusoft.controller.ManagerRecordC;
import cn.edu.neusoft.dao.RecordDao;
import cn.edu.neusoft.view.ManageRecordView;
import cn.edu.neusoft.domain.spot.role.generaluser.view.SpotLearnView;

public class ManagerRecordTest {
    void main() {
        ManagerRecordC mr = new ManagerRecordC();
//        mr.searchAllRecordsC();
//        mr.searchRecordByRecordIDC();
//        mr.searchRecordBySpotIDC();
//        mr.deleteRecordByIdC();
        RecordDao rd = new RecordDao();
        ManageRecordView.showAllRecordView(rd.searchRecordByTime(SpotLearnView.searchByTimeView()));
    }
}
