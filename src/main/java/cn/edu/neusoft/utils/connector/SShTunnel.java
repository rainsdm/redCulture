package cn.edu.neusoft.utils.connector;

import cn.edu.neusoft.utils.appconfig.AppConfig;
import cn.edu.neusoft.utils.appconfig.ConfigLoader;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.util.Properties;

public class SShTunnel {
    private static Session _session;

    /**
     * 建立SSH端口转发机制。
     * @return 成功返回true，无配置项则返回false。如果建立失败，将抛出异常。
     */
    public static boolean establish() {
        AppConfig appConfig = ConfigLoader.getAppConfig();
        if (appConfig.ssh() == null) {
            IO.println("未找到ssh隧道配置，跳过建立流程");
            return false;
        }
        try {
            JSch jsch = new JSch();
            jsch.addIdentity(appConfig.getSshPrivateKey());

            _session = jsch.getSession(appConfig.getSshUser(), appConfig.getSshHost());
//            _session.setPassword(appConfig.getSshPrivateKey());
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            _session.setConfig(config);
            _session.connect(5000);

            _session.setPortForwardingL(appConfig.getDbPort(), "localhost", 3306);
            IO.println("SSH 隧道已打通。数据库监听地址是: " + appConfig.getDbHost() + ":" + appConfig.getDbPort());


            return true;
        } catch (JSchException e) {
            IO.println("SSH 隧道建立失败：" + e.getMessage());
            throw new RuntimeException("无法通过 SSH 隧道连接数据库", e);
        }
    }

    /**
     * 关闭ssh端口转发。
     */
    public static void close() {
        if (_session != null && _session.isConnected()) {
            _session.disconnect();
            IO.println("SSH 隧道已关闭。");
        }
    }
}
