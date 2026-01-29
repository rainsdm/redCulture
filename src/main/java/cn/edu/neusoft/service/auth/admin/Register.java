package cn.edu.neusoft.service.auth.admin;

import cn.edu.neusoft.dao.UserDao;
import cn.edu.neusoft.model.User;
import cn.edu.neusoft.usersession.dto.user.auth.request.CreateUserRequest;

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
		UserDao ud = new UserDao();

		User isUserExists = ud.findByUsername(registerForm.username());

		if (isUserExists != null) {
			IO.println("用户名已经存在。中断注册流程。");
			return 0;
		}

		return ud.insertUser(registerForm);
	}
}
