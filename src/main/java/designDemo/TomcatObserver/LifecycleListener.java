package designDemo.TomcatObserver;

public interface LifecycleListener {

    /**
     * Acknowledge the occurrence of the specified event.
     *
     * @param event LifecycleEvent that has occurred
     */

    void lifecycleEvent(LifecycleEvent event);

}