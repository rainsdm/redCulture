package cn.edu.neusoft.domain.user.role.generaluser.usercenter.controller;

import cn.edu.neusoft.core.auth.service.user.PasswordManager;
import cn.edu.neusoft.domain.user.dto.request.ModifyPasswordRequest;
import cn.edu.neusoft.domain.user.dto.response.ModifyPasswordResponse;
import cn.edu.neusoft.domain.user.model.InputtedPassword;
import cn.edu.neusoft.domain.user.model.User;
import cn.edu.neusoft.domain.user.role.generaluser.usercenter.view.CentreView;
import cn.edu.neusoft.view.IndexView;

import java.util.Scanner;

/**
 * 管理个人中心。
 */
public class UserCenterC {
    /**
     * 个人中心的主入口。
     *
     * @param onlineUser 传递给它的用户数据。
     */
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
                    InputtedPassword inputtedPassword = CentreView.modifyPassword();

                    ModifyPasswordRequest changePassword = new ModifyPasswordRequest(onlineUser.getUserId(),
                            inputtedPassword.oldPassword(), inputtedPassword.newPassword());

                    PasswordManager pm = new PasswordManager();
                    ModifyPasswordResponse mpr = pm.changeToNewPassword(changePassword);

                    // 以下是预留代码，为的是它能够扩展新方法。
                    if (mpr.success()) {
                        IO.println(mpr.message());
                    } else {
                        System.err.println(mpr.message());
                    }

                    break;
                default:
                    // 默认状态下，什么也不干。
                    break;
            }
        } while (loopOperator);
    }
}
