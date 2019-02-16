package guava;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Author xlj
 * @Date 2018/12/25 11:37
 */
public class ImmutableCollections {
    @Test
    public void test1() {
        List<String> lists = Lists.newArrayList("aa", "bb", "cc");
        List<String> unmodifiedLists = Collections.unmodifiableList(lists);
        assertEquals(3, unmodifiedLists.size());

        lists.add("dd");
        assertEquals(4, unmodifiedLists.size());

        ImmutableSet<String> strings = ImmutableSet.copyOf(lists);

    }

}
