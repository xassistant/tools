package com.internet;

import java.util.List;

/**
 * @Date 2019/2/18 9:23
 */
public class Page {
    static class PageBean<T> {
        Integer pageNum;

        Integer pageTotal;

        Integer pageLimit;

        List<T> pageData;
    }

}
