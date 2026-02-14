package cn.edu.neusoft.utils.appconfig;

// 重要：将所有 import 从 com.fasterxml.jackson 更新为 tools.jackson

import tools.jackson.databind.DeserializationFeature;
import tools.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * 基于单例模式的项目启动配置文件的加载器。
 */
public final class ConfigLoader {

    /**
     * 位于resources文件夹下的应用配置文件。
     */
    private static final String CONFIG_FILE_PATH = "/application.yml";

    /**
     * 单一、不可变的实例
     */
    private static final AppConfig INSTANCE = loadAppConfig();

    // 私有化构造函数，防止外部创建新的加载器实例
    private ConfigLoader() {
        // 工具类不应被实例化
    }

    /**
     * 初始化方法，仅在程序初次启动时使用。
     */
    public static void init() {
        //noinspection ResultOfMethodCallIgnored
        getAppConfig();
    }

    /**
     * 获取全局唯一的 AppConfig 配置实例。
     *
     * @return 加载并解析完成的 AppConfig 对象。
     */
    public static AppConfig getAppConfig() {
        return INSTANCE;
    }

    /**
     * 在类初始化时执行一次的私有加载方法。
     */
    private static AppConfig loadAppConfig() {
        var yamlMapper = YAMLMapper.builder()
                .disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)
                .build();

        try (InputStream inputStream = ConfigLoader.class.getResourceAsStream(CONFIG_FILE_PATH)) {

            if (inputStream == null) {
                throw new IllegalStateException("无法在类路径下找到配置文件: " + CONFIG_FILE_PATH);
            }

            IO.println("成功加载 " + CONFIG_FILE_PATH + "，正在解析...");
            AppConfig config = yamlMapper.readValue(inputStream, AppConfig.class);
            IO.println("配置解析成功！");
            return config;

        } catch (IOException e) {
            throw new IllegalStateException("加载或解析配置文件失败: " + CONFIG_FILE_PATH, e);
        }
    }

    /**
     * Main 方法用于演示和独立测试加载器。
     */
    static void main(String[] args) {
        IO.println("--- 演示 ConfigLoader ---");
        try {
            IO.println("首次请求配置...");
            AppConfig config = ConfigLoader.getAppConfig();

            // 类型安全地访问配置！
            IO.println("SSH 主机: " + config.getSshHost());
            IO.println("数据库jdbc地址: " + config.getDbUrl());

            IO.println("\n再次请求配置...");
            AppConfig config2 = ConfigLoader.getAppConfig();

            // 验证两次获取的是否为同一个实例
            IO.println("两次获取的实例是否相同: " + (config == config2)); // 应为 true
        } catch (IllegalStateException e) {
            System.err.println("错误：应用启动失败，原因: " + e.getMessage());
            e.printStackTrace();
        }
        IO.println("--- 演示结束 ---");
    }
}
