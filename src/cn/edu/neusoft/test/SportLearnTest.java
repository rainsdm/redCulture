package cn.edu.neusoft.test;

import cn.edu.neusoft.controller.SpotLearnC;
import cn.edu.neusoft.dao.SpotDao;
import cn.edu.neusoft.model.Spot;
import cn.edu.neusoft.view.SpotLearnView;

import java.util.ArrayList;
import java.util.List;

public class SportLearnTest {
    public static void main(String[] args) {
        SpotLearnC slc = new SpotLearnC();
        SpotDao sd = new SpotDao();
        List<Spot> allSpots = new ArrayList<>();
        allSpots = sd.searchAllSpots(-1, 1); // 让程序启动时，默认显示所有信息。
        int menu = SpotLearnView.showSpotsView(allSpots);
        allSpots.clear();
        slc.showSpots(menu);
    }
}
