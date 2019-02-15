package designDemo.search.action;

import designDemo.search.Resultset;

/**
 * @Author xlj
 * @Date 2018/9/30 9:46
 */
public interface Searcher {

    Resultset search(String[] keywords);

    Resultset getResultset();
}
