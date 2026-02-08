package cn.edu.neusoft.core.auth.dto.response;

public record UserAuthInfo(String username, String password) {
	@Override
	public String toString() {
		return "loginInfo{username= \"" + username + "\", password= \"" + "******" + "\" }";
	}
}
