package cn.edu.neusoft.usersession.dto.user.auth.request;

/**
 * 用于接收、传输新用户注册信息的dto。 它不能直接传递给DAO层，但是可以传递给Service层，由service层处理后，
 * 生成不完整的用户信息，从而传递给DAO层。 按照现有项目的复杂度，它可以适应绝大多数注册类请求所涉及到的各种场景。
 */
public record CreateUserRequest(String username, String password, int role) {
	@Override
	public String toString() {
		return "registerUserInfo{username= \"" + username + "\", password= \"" + "******" + "\", role= " + role + " }";
	}
}