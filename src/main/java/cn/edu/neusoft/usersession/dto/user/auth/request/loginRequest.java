package cn.edu.neusoft.usersession.dto.user.auth.request;

public record loginRequest(String username, String password) {
	@Override
	public String toString() {
		return "loginInfo{username= \"" + username + "\", password= \"" + "******" + "\" }";
	}
}
