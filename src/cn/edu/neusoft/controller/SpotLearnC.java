package cn.edu.neusoft.controller;

import cn.edu.neusoft.dao.SpotDao;
import cn.edu.neusoft.model.Spot;
import cn.edu.neusoft.test.SportLearnTest;
import cn.edu.neusoft.view.SpotLearnView;

import java.util.List;

public class SpotLearnC {
    SpotDao sd = new SpotDao();

    public void showSpots(int nextOperator) {
        boolean continueWork = true;
        int page = 1, pageSize = 3; // 暂时放弃启动时，遍历所有页面的功能。
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
                    if (page < (sd.getSpots_count() / pageSize)) {
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
                    if (learnSpot() == 0) {
                        continueWork = false;
                    }
                    break;
            }
        }
    }

    public int learnSpot() {
        int spotId = SpotLearnView.chooseSpot();

        Spot spot = sd.searchSpotById(spotId);

        return SpotLearnView.showSpotDetail(spot);
    }
}
