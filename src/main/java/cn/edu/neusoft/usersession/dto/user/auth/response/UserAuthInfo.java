package cn.edu.neusoft.usersession.dto.user.auth.response;

public record UserAuthInfo(String username, String password) {
	@Override
	public String toString() {
		return "loginInfo{username= \"" + username + "\", password= \"" + "******" + "\" }";
	}
}
