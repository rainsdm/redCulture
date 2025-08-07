package cn.edu.neusoft.model;

/**
 * 这个类用于存储Java项目的单个用户的全部信息，它是用户表的最完整的数据结构。
 */
public class User {
    private String userID;
    private String username;
    private String password;
    private int role;
    private int studyPoints;

    public User() {
    }

    public User(String user_id, String username, String password, int role, int study_points) {
        this.userID = user_id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.studyPoints = study_points;
    }

    public User(User sourceUser) {
        this.userID = sourceUser.getUserID();
        this.username = sourceUser.getUsername();
        this.password = sourceUser.getPassword();
        this.role = sourceUser.getRole();
        this.studyPoints = sourceUser.getStudyPoints();
    }

    public User(String username, String password, int role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUserID() {
        return userID;
    }

    public void setUser_id(String user_id) {
        this.userID = user_id;
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

    public int getStudyPoints() {
        return studyPoints;
    }

    public void setStudyPoints(int study_points) {
        this.studyPoints = study_points;
    }

    @Override
    public String toString() {
        return username +
                "{user_id='" + userID + '\'' +
                ", " + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", study_points=" + studyPoints +
                '}';
    }

    public void clear() {
        this.userID = null;
        this.username = null;
        this.password = null;
        this.role = 1;
        this.studyPoints = 0;
    }
}
