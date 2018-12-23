package designDemo.search.action;

import designDemo.search.Resultset;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.expression.spel.ast.Indexer;

/**
 * @Author xlj
 * @Date 2018/9/30 9:48
 */
public class Client {
    public static void main(String[] args) {
        SearchService searchService = new SearchService();
        Resultset resultset = searchService.search(new String[]{"a", "b"});
        String except = resultset.getExcept();
        System.out.println(except);

    }
}
