package cn.edu.neusoft.controller;

import cn.edu.neusoft.dao.BaseDao;
import cn.edu.neusoft.dao.SpotDao;
import cn.edu.neusoft.model.Spot;
import cn.edu.neusoft.view.ManageSpotView;
import cn.edu.neusoft.view.ManagerSpotView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 管理员用户对景点的CRUD。
 */
public class ManageSpotC {
    SpotDao sd = new SpotDao();
    Spot spotData = null;

    public boolean addSpot() {
        spotData = ManageSpotView.addSpot();
        if (!spotData.getSpot_name().isEmpty() && spotData.getSpot_name() != null) {
            sd.addSpot(spotData);
            return true;
        } else  {
            return false;
        }
    }

    public boolean deleteSpot() {
        String spot_to_delete_Name = ManageSpotView.deleteSpot();
        Spot spot_from_db = sd.searchSpotByName(spot_to_delete_Name);

        if (spot_from_db.getSpot_id() == null || spot_from_db.getSpot_id().isEmpty()) {
            System.out.println("没有找到要删除的景点! "); // 为了防止意外失败，这里的检查我认为是有必要的。
            return false;
        }

        return sd.deleteSpot(spot_from_db);
    }
}
