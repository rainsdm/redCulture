package cn.edu.neusoft.model;

/**
 * 它用于注册新用户时的密码管理。它仅负责接收原始的视图信息，为只读数据类型。
 */
public class initPassword {
    private String userName;
    private String password_1;
    private String password_2;
    private int role;

    public initPassword() {
    }

    public initPassword(String userName, String password_1, String password_2, int role) {
        this.userName = userName;
        this.password_1 = password_1;
        this.password_2 = password_2;
        this.role = role;
    }

    public initPassword(String userName, String password_1, String password_2) {
        this.userName = userName;
        this.password_1 = password_1;
        this.password_2 = password_2;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword_1() {
        return password_1;
    }

    public String getPassword_2() {
        return password_2;
    }

    public int getRole() {
        return role;
    }
}
