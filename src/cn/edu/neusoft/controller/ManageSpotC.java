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
}
