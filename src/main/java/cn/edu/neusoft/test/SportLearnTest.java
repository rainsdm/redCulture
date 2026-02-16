//package cn.edu.neusoft.test;
//
//import cn.edu.neusoft.controller.SpotLearnC;
//import cn.edu.neusoft.dao.SpotDao;
//import cn.edu.neusoft.domain.spot.model.Spot;
//import cn.edu.neusoft.domain.user.model.User;
//import cn.edu.neusoft.view.SpotLearnView;
//
//import java.util.List;
//
//public class SportLearnTest {
//    public static void main(String[] args) {
//        SpotLearnC slc = new SpotLearnC();
//        SpotDao sd = new SpotDao();
//        User user = new User();
//        user.setUser_id("1");
//        List<Spot> allSpots = sd.searchAllSpots(-1, 1); // 让程序启动时，默认显示所有信息。
//        int menu = SpotLearnView.showSpotsView(allSpots);
//        allSpots.clear();
//        slc.showSpots(menu, user.getUserId());
//        slc.searchRecordByTime();
//        slc.searchAllRecords();
//        slc.deleteRecordByIdC();
//        slc.showPopularView();
//    }
//}
