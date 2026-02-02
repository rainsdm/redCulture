package cn.edu.neusoft.utils.appconfig;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AppConfig(
        SShConfig ssh,
        DatabaseConfig database
) {
    //<editor-fold desc="只读访问器">
    /**
     * 获取 SSH 主机地址
     * 替代: ConfigLoader.getAppConfig().ssh().sshHost()
     */
    public String getSshHost() {
        // 这里的判空是为了防止配置文件缺项导致空指针
        return (ssh != null) ? ssh.sshHost() : null;
    }

    /**
     * 获取 SSH 端口
     * @return 远程主机的端口。默认为22。
     */
    public int getSshPort() {
        return (ssh != null) ? ssh.sshPort() : 22;
    }

    /**
     * 获取 SSH 用户
     * @return 远程主机的用户名。
     */
    public String getSshUser() {
        return (ssh != null) ? ssh.sshUser() : null;
    }

    /**
     * 获取访问远程主机的ssh私钥。
     * @return 私钥的具体路径。
     */
    public String getSshPrivateKey() {
        return (ssh != null) ? ssh.privateKey() : null;
    }

    /**
     * 获取数据库 JDBC URL (甚至可以在这里做逻辑拼接)
     * @return jdbc链接
     */
    public String getDbUrl() {
        if (database == null) return null;
        // 可以在这里封装拼接逻辑，业务代码直接拿 URL
        return String.format("jdbc:mysql://%s:%d/%s", database.dbHost(), database.dbPort(), database.dbName());
    }

    /**
     * 获取数据库的主机。
     * @return 数据库访问地址。
     */
    public String getDbHost() {
        return (database.dbHost() != null) ? database.dbHost() : "localhost";
    }

    /**
     * 获取数据库端口号。
     * @return 数据库的端口号。
     */
    public int getDbPort() {
        return (database != null) ? database.dbPort() : 3306;
    }

    /**
     * 获取登录到数据库的用户名。
     * @return 数据库的用户名。可能为空字符串。
     */
    public String getDbUser() {
        return (database != null) ? database.dbUser() : "";
    }

    /**
     * 获取登录到数据库的密码。
     * @return 数据库对应用户名的密码。可能为空字符串。
     */
    public String getDbPassword() {
        return (database != null) ? database.dbPassword() : "";
    }

    /**
     * 获取要使用的数据库名称。
     * @return 数据库名。可以返回长度为0的空字符串，且不做判空处理。
     */
    public String getDbName() {
        return (database != null) ? database.dbName() : "";
    }
    //</editor-fold>
}

record SShConfig(
        @JsonProperty("host")
        String sshHost,
        @JsonProperty("port")
        int sshPort,
        @JsonProperty("user")
        String sshUser,
        @JsonProperty("private-key")
        String privateKey
) {
    public SShConfig {
        if (sshPort <= 0) {
            sshPort = 22;
        }
    }


}

record DatabaseConfig(
        @JsonProperty("host")
        String dbHost,
        @JsonProperty("port")
        int dbPort,
        @JsonProperty("user")
        String dbUser,
        @JsonProperty("password")
        String dbPassword,
        @JsonProperty("name")
        String dbName
) {
    public DatabaseConfig {
        if (dbPort <= 0) {
            dbPort = 3306;
        }
    }
}
