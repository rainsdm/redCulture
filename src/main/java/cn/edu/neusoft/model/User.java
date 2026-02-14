package cn.edu.neusoft.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 这个类用于存储Java项目的单个用户的全部信息，它是用户表的最完整的数据结构。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userId;
    private String username;
    private int role;
    private int studyPoints;
    private LocalDateTime createTime;
    private LocalDateTime lastAccessedTime;

    public User(String user_id, String username, String password, int role, int study_points) {
        this.userId = user_id;
        this.username = username;
        this.role = role;
        this.studyPoints = study_points;
    }

    public User(User sourceUser) {
        this.userId = sourceUser.getUserId();
        this.username = sourceUser.getUsername();
        this.role = sourceUser.getRole();
        this.studyPoints = sourceUser.getStudyPoints();
    }

    public User(String username, String password, int role) {
        this.username = username;
        this.role = role;
    }


    public String roleToString() {
        return this.role == 0 ? "管理员" : "普通用户";
    }

    public int stringToRole(String stRole) {
        if (stRole.equals("管理员")) {
            this.role = 0;
        } else {
            this.role = 1;
        }
        return getRole();
    }

    @Override
    public String toString() {
        return username + "{user_id='" + userId + '\'' + ", " + '\'' + ", password='" + "******" + '\'' + ", role="
                + role + ", study_points=" + studyPoints + ", create_time=" + createTime + ", last_accessed_time"
                + lastAccessedTime + '}';
    }

    public void clear() {
        this.userId = null;
        this.username = null;
        this.role = 1;
        this.studyPoints = 0;
    }
}
