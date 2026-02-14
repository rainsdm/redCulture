package cn.edu.neusoft.domain.user.dto.request;

public record ModifyPasswordRequest(String userId, String oldPassword, String newPassword) {

    @Override
    public String toString() {
        return "ModifiedPassword { userId= '" + userId + "' oldPassword= '******', modifiedPassword= '******'" + " }";
    }

}
