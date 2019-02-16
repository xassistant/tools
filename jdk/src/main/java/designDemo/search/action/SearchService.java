package designDemo.search.action;

import designDemo.search.Resultset;
import designDemo.search.entity.FileEntity;

/**
 * @Author xlj
 * @Date 2018/9/30 9:56
 */
public class SearchService implements Searcher {

    @Override
    public Resultset search(String[] keywords) {
        for (String keyword : keywords) {
            FileEntity fileEntity = new FileEntity();
            if (keyword.equalsIgnoreCase("a")) {
                fileEntity.setIndex(20);
                fileEntity.setContent("aaa");
                return new Resultset() {
                    @Override
                    public void first() {
                        fileEntity.setIndex(0);
                    }

                    @Override
                    public void last() {
                        fileEntity.setIndex(100);
                    }

                    @Override
                    public void next() {
                        fileEntity.setIndex(fileEntity.getIndex() + 1);
                    }

                    @Override
                    public void previous() {
                        fileEntity.setIndex(fileEntity.getIndex() - 1);
                    }

                    @Override
                    public String getExcept() {
                        return fileEntity.getContent();
                    }

                    @Override
                    public String getFullRecord() {
                        return fileEntity.getContent();
                    }
                };
            }
        }
        return null;
    }

    @Override
    public Resultset getResultset() {
        return null;
    }
}
