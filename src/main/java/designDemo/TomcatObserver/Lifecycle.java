package designDemo.TomcatObserver;

/**
 * Lifecycle:相当于抽象主题角色，所有的容器类与组件实现类都实现了这个接口。如StandardContext
 * LifecycleListener:相当于抽象观察者角色,具体的实现类有ContextConfig, HostConfig,
 * EngineConfig类，它们在容器启动时与停止时触发。 LifecycleEvent:生命周期事件，对主题与发生的事件进行封装。
 * LifecycleSupport:生命周期管理的实用类，提供对观察者的添加，删除及通知观察者的方法。
 * LifecycleException:生命周期异常类。
 * 
 * @author xlj
 * 
 */
public interface Lifecycle {

	// 生命周期内的六个事件

	public static final String START_EVENT = "start";

	public static final String BEFORE_START_EVENT = "before_start";

	public static final String AFTER_START_EVENT = "after_start";

	public static final String STOP_EVENT = "stop";

	public static final String BEFORE_STOP_EVENT = "before_stop";

	public static final String AFTER_STOP_EVENT = "after_stop";

	// 观察者的管理与通知方法

	public void addLifecycleListener(LifecycleListener listener);

	public void removeLifecycleListener(LifecycleListener listener);

	public LifecycleListener[] findLifecycleListeners();

	// 主题的启动与停止方法

	public void start() throws LifecycleException;

	public void stop() throws LifecycleException;

}