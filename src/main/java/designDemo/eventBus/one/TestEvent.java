package designDemo.eventBus.one;

/**
 * @Author xlj
 * @Date 2019/1/2 11:13
 */
public class TestEvent {
    private final int message;

    public TestEvent(int message) {
        this.message = message;
    }

    public int getMessage() {
        return message;
    }
}
