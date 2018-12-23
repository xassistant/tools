package bigdata.bloomFilter.util;

/**
 * 配置文件名枚举常量
 */
public enum ConfigFileName {

    Redis("RedisConfig.properties"),
    RedisDev("RedisConfig-dev.properties"),
    IntelligenceIp("ip.txt"),
    IntelligenceUrl("url.txt"),
    IntelligenceDomain("domain.txt");

    private String value;

    ConfigFileName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
