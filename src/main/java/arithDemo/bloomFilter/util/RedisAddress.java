package arithDemo.bloomFilter.util;

/**
 * 描述：redis地址枚举类
 */
public enum RedisAddress {

    CollectorInput("CollectorInput"),
    RuleOutput("RuleOutput"),
    EsOutput("EsOutput"),
    MlNormalization("MlNormalization"),

    IntelligenceInput("IntelligenceInput");

    private String value;

    private RedisAddress(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
