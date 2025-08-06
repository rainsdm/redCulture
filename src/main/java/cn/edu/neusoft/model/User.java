package cn.edu.neusoft.model;

/**
 * 这个类用于存储Java项目的单个用户的全部信息，它是用户表的最完整的数据结构。
 */
public class User {
    private String user_id;
    private String username;
    private String password;
    private int role;
    private int study_points;

    public User() {
    }

    public User(String user_id, String username, String password, int role, int study_points) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.study_points = study_points;
    }

    public User(User sourceUser) {
        this.user_id = sourceUser.getUser_id();
        this.username = sourceUser.getUsername();
        this.password = sourceUser.getPassword();
        this.role = sourceUser.getRole();
        this.study_points = sourceUser.getStudy_points();
    }

    public User(String username, String password, int role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String roleToString() {
        return this.role == 0 ? "管理员" : "普通用户";
    }

    public int stringToRole(String stRole) {
        if (stRole.equals("管理员")) {
            this.role = 0;
            return getRole();
        } else {
            this.role = 1;
            return getRole();
        }
    }

    public int getStudy_points() {
        return study_points;
    }

    public void setStudy_points(int study_points) {
        this.study_points = study_points;
    }

    @Override
    public String toString() {
        return username +
                "{user_id='" + user_id + '\'' +
                ", " + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", study_points=" + study_points +
                '}';
    }

    public void clear() {
        this.user_id = null;
        this.username = null;
        this.password = null;
        this.role = 1;
        this.study_points = 0;
    }
}
