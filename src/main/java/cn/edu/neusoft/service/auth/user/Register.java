package cn.edu.neusoft.service.auth.user;

import cn.edu.neusoft.dao.UserDao;
import cn.edu.neusoft.model.User;
import cn.edu.neusoft.usersession.dto.user.auth.request.CreateUserRequest;

/**
 * 普通用户注册功能的服务层实现。
 */
public class Register {
	/**
	 * 根据注册请求，创建一个新的普通用户。
	 *
	 * @param registerForm 客户端发起的注册申请数据。
	 * @return 状态码。1表示注册成功，-1表示注册失败。
	 */
	@SuppressWarnings("DuplicatedCode") // 这是故意的。因为，管理员用户和普通用户可能会出现完全不同的业务逻辑。
	public int handle(CreateUserRequest registerForm) {
		UserDao ud = new UserDao();

		User isUserExists = ud.findByUsername(registerForm.username());

		if (isUserExists != null) {
			System.out.println("用户名已经存在。中断注册流程。");
			return 0;
		}

		return ud.insertUser(registerForm);
	}
}
