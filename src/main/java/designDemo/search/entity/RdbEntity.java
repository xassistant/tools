package designDemo.search.entity;

import java.math.BigDecimal;

/**
 * @Author xlj
 * @Date 2018/9/30 10:12
 */
public class RdbEntity {
    private BigDecimal index;
    private String content;

    public BigDecimal getIndex() {
        return index;
    }

    public void setIndex(BigDecimal index) {
        this.index = index;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
