package cn.edu.neusoft.service.auth;

import java.time.Instant;
import java.time.LocalDateTime;

import cn.edu.neusoft.dao.UserDao;
import cn.edu.neusoft.dto.user.auth.request.loginRequest;
import cn.edu.neusoft.dto.user.auth.response.UserAuthInfo;
import cn.edu.neusoft.model.User;

/**
 * 负责用户登录的身份认证类。
 */
public class UserAuthenticator {
	/**
	 * 负责执行身份认证逻辑的唯一对外接口。
	 * @param loginRequest 从表单获取到的登录请求信息。
	 * @return 被批准登录的用户数据。
	 */
	public User authenticate(loginRequest loginRequest) {
        UserDao dm = new UserDao();
        UserAuthInfo usrFromDB = dm.findAuthInfoByUsername(loginRequest.username());

        if (usrFromDB.username().isBlank()) {
            System.out.println("用户不存在。");
            return null;
        }

        if (usrFromDB.password().equals(loginRequest.password())) {
            System.out.println("登录成功！");
            User applied = dm.findByUsernameWithTimestamps(usrFromDB.username());
            applied.setLastAccessedTime(LocalDateTime.now());
            //TODO: LocalDateTime.now()会绑定系统时间，妨碍数据测试。未来用Clock或者其他的方案代替。
            //TODO: 实现登录后，向服务器记录这一次登录的时间。
            dm.updateLastAccessTime(applied.getUserId(), applied.getLastAccessedTime());
            return applied;
        } else {
            System.out.println("密码错误，登录失败。");
            return null;
        }
	}
}
