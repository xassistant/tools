package designDemo.search;

/**
 * @Author xlj
 * @Date 2018/9/30 9:44
 */
public interface Resultset {
    void first();

    void last();

    void next();

    void previous();

    String getExcept();

    String getFullRecord();
}
