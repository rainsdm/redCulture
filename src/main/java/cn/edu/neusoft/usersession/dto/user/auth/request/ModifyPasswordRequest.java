package cn.edu.neusoft.usersession.dto.user.auth.request;

public record ModifyPasswordRequest(String userId, String oldPassword, String newPassword) {

	@Override
	public String toString() {
		return "ModifiedPassword { userId= '" + userId + "' oldPassword= '******', modifiedPassword= '******'" + " }";
	}

}
