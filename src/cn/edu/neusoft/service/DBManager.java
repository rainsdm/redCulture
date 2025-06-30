package cn.edu.neusoft.service;

import cn.edu.neusoft.model.User;

import java.sql.Connection;

public interface DBManager {
    User findUserByUsername(String username);
}
