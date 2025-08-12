package cn.edu.neusoft.dto.user.request;

/**
 * 用于接收、传输新用户注册信息的dto。
 * 它不能直接传递给DAO层，但是可以传递给Service层，由service层处理后，
 * 生成不完整的用户信息，从而传递给DAO层。
 */
public class RegisterUserInfo {
	private String userName;
	private String password;
	private int role;

	public RegisterUserInfo() {
	}

	public RegisterUserInfo(String userName, String password, int role) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public String toString() {
		
		return "registerUserInfo{userID= \"" + userName + "\", password= \"" + "******" + "\", role= " + role + " }";
	}

}
