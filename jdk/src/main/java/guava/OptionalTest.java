package guava;

import cn.itcast.heima2.User;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author xlj
 * @Date 2018/12/24 17:09
 */
public class OptionalTest {
    @Test
    public void testNull() {
        User user = new User("xioaming", 11);
        Optional<User> user1 = Optional.of(user);
        if(user1.isPresent()){
            User user2 = user1.get();
        }
        Optional<User> user2 = user1.filter(e -> e.getName() != null);

    }
}
