package cn.edu.neusoft.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AppConfig(
        SShConfig ssh,
        DatabaseConfig database
) {
}

record SShConfig(
        @JsonProperty("host") String sshHost,
        @JsonProperty("user") String sshUser,
        @JsonProperty("private-key") String privateKey
) {
}

record DatabaseConfig(
        @JsonProperty("host") String dbHost,
        @JsonProperty("port") int dbPort,
        @JsonProperty("user") String dbUser,
        @JsonProperty("password") String dbPassword,
        @JsonProperty("name") String dbName
) {
}
