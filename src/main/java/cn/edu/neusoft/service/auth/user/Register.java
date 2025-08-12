package cn.edu.neusoft.service.auth.user;

import cn.edu.neusoft.dao.UserDao;
import cn.edu.neusoft.dto.user.request.RegisterUserInfo;
import cn.edu.neusoft.model.User;

/**
 * 普通用户注册功能的实现。
 */
public class Register {
    /**
     * 根据注册请求，创建一个新的普通用户。
     *
     * @param registerForm 客户端发起的注册申请数据。
     * @return 状态码。1表示注册成功，-1表示注册失败。
     */
    public int handle(RegisterUserInfo registerForm) {
        User registeredUser = null;
        registeredUser = new User(registerForm.getUserName(), registerForm.getPassword(), registerForm.getRole());

        UserDao ud = new UserDao();

        User isUserExists = ud.findByUsername(registeredUser.getUsername());

        if (isUserExists != null) {
            System.out.println("用户名已经存在。中断注册流程。");
            return 0;
        }

        return ud.insertUser(registeredUser);
    }
}
