package designDemo.eventBus.one;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

/**
 * @Author xlj
 * @Date 2019/1/2 11:22
 */
public class TestEventBus {
    @Test
    public void testReceiveEvent(){
        EventBus eventBus = new EventBus("test");
        EventListener eventListener = new EventListener();
        eventBus.register(eventListener);

        eventBus.post(new TestEvent(111));
        eventBus.post(new TestEvent(222));
        eventBus.post(new TestEvent(333));

        System.out.println(eventListener.getLastMessage());
    }
}
