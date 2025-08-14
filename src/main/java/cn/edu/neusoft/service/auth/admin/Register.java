package cn.edu.neusoft.service.auth.admin;

import cn.edu.neusoft.dao.UserDao;
import cn.edu.neusoft.dto.user.auth.request.CreateUserRequest;
import cn.edu.neusoft.model.User;

/**
 * 管理员添加新用户功能的服务层实现。
 */
public class Register {
    /**
     * 根据注册请求，创建一个新的普通用户。
     *
     * @param registerForm 客户端发起的注册申请数据。
     * @return 状态码。1表示注册成功，-1表示注册失败。
     */
    public int handle(CreateUserRequest registerForm) {
        //TODO: 这里也应该用dto传输。
        // 一切注册流程中，传递的是申请表单，最后才能得到User这个完整的身份证。
        User registeredUser = new User(registerForm.username(), registerForm.password(), registerForm.role());
        // 一旦它能够知道是谁调用了它，它就能决定是否强制控制用户的角色等级。

        UserDao ud = new UserDao();

        User isUserExists = ud.findByUsername(registeredUser.getUsername());

        if (isUserExists != null) {
            System.out.println("用户名已经存在。中断注册流程。");
            return 0;
        }

        return ud.insertUser(registeredUser);
    }
}
