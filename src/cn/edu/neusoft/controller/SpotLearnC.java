package cn.edu.neusoft.controller;

import cn.edu.neusoft.dao.SpotDao;
import cn.edu.neusoft.model.Spot;
import cn.edu.neusoft.view.SpotLearnView;

import java.util.List;

public class SpotLearnC {
    public void showSpots() {
        SpotDao sd = new SpotDao();
        boolean continueWork = true;
        int page = 1, pageSize = 3; // 暂时放弃启动时，遍历所有页面的功能。
        while (continueWork) {
            List<Spot> spots = sd.searchAllSpots(page, pageSize);
            int manu = SpotLearnView.showSpotsView(spots);
            switch(manu) {
                case 0:
                    continueWork = false;
                    break;
                case 1:
                    // 上一页，bug 已修正
                    if (page > 1) {
                        page--;
                    } else {
                        page = 1;
                        System.out.println("已经是第一页了！");
                    }
                    break;
                case 2:
                    // 下一页（bug: 在程序一开始就按第一页时，就会莫名其妙的崩溃
                    if (page < (sd.getSpots_count() / pageSize)) {
                        page++;
                    } else {
                        page = sd.getSpots_count() / pageSize + 1;
                        System.out.println("已经是最后一页了！");
                    }
                case 3:
                    // 精确查询
                    break;
            }
//            SpotLearnView.showSpotsView(sd.searchAllSpots(page, pageSize));
        }
    }
}
