package cn.edu.neusoft.controller;

import cn.edu.neusoft.dao.UserDao;
import cn.edu.neusoft.model.User;
import cn.edu.neusoft.model.modPassword;
import cn.edu.neusoft.view.CentreView;
import cn.edu.neusoft.view.IndexView;

import java.util.Scanner;

/**
 * 管理个人中心。
 */
public class UserCenterC {
    public void managerCenter(User onlineUser) {
        Scanner sc = new Scanner(System.in);
        boolean loopOperator = true;
        int menu;
        do {
            CentreView.showUserManageOperator();
            menu = sc.nextInt();

            switch (menu) {
                case 0:
                    loopOperator = false;
                    IndexView.indexOfGeneralUser();
                    break;
                case 1:
                    CentreView.showInfo(onlineUser);
                    break;
                case 2:
                    modPassword pass = CentreView.modifyPassword(onlineUser);

                    if (!pass.getOldPassword().equals(onlineUser.getPassword())) {
                        System.out.println("旧密码输入错误，修改失败。");
                        continue;
                    } else {
                        modifyCenter(pass, onlineUser);
                    }
                    break;
                default:
                    // 默认状态下，什么也不干。
                    break;
            }
        } while (loopOperator);
    }

    private void modifyCenter(modPassword userWillBeChanged, User currentUserInfo) {
        if (!userWillBeChanged.getUserID().equals(currentUserInfo.getUserId())) {
            System.out.println("没有找到正确的用户信息。");
            return;
        }
        if (!userWillBeChanged.getOldPassword().equals(currentUserInfo.getPassword())) {
            System.out.println("旧密码不正确。密码修改失败。");
            CentreView.showInfo(currentUserInfo);
            return;
        }
        if (userWillBeChanged.getNewPassword().isEmpty()) {
            System.out.println("新密码设置错误，无法更改。");
            return;
        }
        UserDao ud = new UserDao();
        int r = ud.changePassword(userWillBeChanged);
        if (r == 1) {
            System.out.println("密码修改成功");
            currentUserInfo.setPassword(userWillBeChanged.getNewPassword());
        } else {
            System.out.println("密码修改失败。");
        }
    }
}
