package cn.edu.neusoft.utils;

// 重要：将所有 import 从 com.fasterxml.jackson 更新为 tools.jackson
import tools.jackson.databind.ObjectMapper;
import tools.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * 最终优化的配置加载器 (适配 Jackson 3.x)。
 * <p>
 * 采用单例模式，在类加载时懒汉式地加载配置，并将其直接映射到类型安全的 AppConfig record。
 * 这确保了配置只被解析一次，并在整个应用程序生命周期内可重复使用、高性能地访问。
 * import 路径已根据 pom.xml (Jackson 3.x) 精确更新。
 */
public final class ConfigLoader {

    private static final String CONFIG_FILE_PATH = "/application.yml";

    // 使用一个静态 final 字段来持有唯一的、不可变的配置实例
    private static final AppConfig INSTANCE = loadAppConfig();

    // 私有化构造函数，防止外部创建新的加载器实例
    private ConfigLoader() {
        // 工具类不应被实例化
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
        // 创建 ObjectMapper，它是 Jackson 工作的核心，并指定 YAMLFactory
        // 使用 var 关键字是 JDK 25 的推荐风格
        var yamlMapper = new ObjectMapper(new YAMLFactory());

        try (InputStream inputStream = ConfigLoader.class.getResourceAsStream(CONFIG_FILE_PATH)) {

            if (inputStream == null) {
                // 如果配置文件缺失，这是一个严重错误，直接让应用启动失败
                throw new IllegalStateException("无法在类路径下找到配置文件: " + CONFIG_FILE_PATH);
            }

			// 核心：直接将 InputStream 解析为 AppConfig.class 类型！
			IO.println("成功加载 " + CONFIG_FILE_PATH + "，正在解析...");
            AppConfig config = yamlMapper.readValue(inputStream, AppConfig.class);
			IO.println("配置解析成功！");
            return config;

        } catch (IOException e) {
            // 如果文件存在但无法读取或解析失败，也应视为严重错误
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
			IO.println("SSH 主机: " + config.ssh().sshHost());
			IO.println("数据库用户: " + config.database().dbUser());

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
