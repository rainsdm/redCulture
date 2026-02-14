package cn.edu.neusoft.core.auth.dto.request;

public record loginRequest(String username, String password) {
    @Override
    public String toString() {
        return "loginInfo{username= \"" + username + "\", password= \"" + "******" + "\" }";
    }
}
