package designDemo.eventBus.one;

import com.google.common.eventbus.Subscribe;

/**
 * @Author xlj
 * @Date 2019/1/2 11:19
 */
public class EventListener {
    public int lastMessage = 0;

    @Subscribe
    public void listen(TestEvent testEvent) {
        this.lastMessage = testEvent.getMessage();
    }

    public int getLastMessage() {
        return lastMessage;
    }
}
