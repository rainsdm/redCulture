package cn.edu.neusoft.model;

/**
 * 它是User类的简化版，其目的是实现修改自己的密码时，需要的原始密码与新密码。
 */
public class modPassword {
    private String userID;
    private String oldPassword;
    private String newPassword;

    public modPassword() {
    }

    public modPassword(String userID) {
        this.userID = userID;
    }

    public modPassword(String userID, String oldPassword, String newPassword) {
        this.userID = userID;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return userID + "{" +
                "oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
