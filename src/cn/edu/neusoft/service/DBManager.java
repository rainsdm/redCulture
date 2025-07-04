package cn.edu.neusoft.service;

import cn.edu.neusoft.model.User;

public interface DBManager {
    User findUserByUsername(String username);
}
