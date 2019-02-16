package designDemo.search.action;

import designDemo.search.FileIndexer;
import designDemo.search.RdbIndexer;

/**
 * @Author xlj
 * @Date 2018/9/30 9:45
 */
public interface Indexer extends FileIndexer, RdbIndexer {
    void reIndexAll();

    void updateIndex();
}
