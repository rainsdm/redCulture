package cn.edu.neusoft.service.auth.user;

import cn.edu.neusoft.dao.UserDao;
import cn.edu.neusoft.usersession.dto.user.auth.request.ModifyPasswordRequest;
import cn.edu.neusoft.usersession.dto.user.auth.response.ModifyPasswordResponse;
import cn.edu.neusoft.usersession.dto.user.auth.response.UserAuthInfo;

public class PasswordManager {
	UserDao ud = new UserDao();

	public ModifyPasswordResponse changeToNewPassword(ModifyPasswordRequest newUserInfo) {
		UserAuthInfo uai = ud.findAuthInfoByUserId(newUserInfo.userId());
		// 1. 判断用户是否存在；
		// 2. 判断是否是本人操作；
		// 3. 操作数据库，修改密码。
		if (uai == null) {
			return new ModifyPasswordResponse(false, "用户不存在。");
		}

		if (!uai.password().equals(newUserInfo.oldPassword())) {
			return new ModifyPasswordResponse(false, "旧密码错误。");
		}

		int result = ud.changePassword(newUserInfo.userId(), newUserInfo.newPassword());

		if (result == 1) {
			return new ModifyPasswordResponse(true, "成功修改密码。");
		} else {
			return new ModifyPasswordResponse(false, "修改密码失败。出现意想不到的问题。");
		}

	}
}
