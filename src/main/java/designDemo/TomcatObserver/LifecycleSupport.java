package designDemo.TomcatObserver;

public final class LifecycleSupport {

	public LifecycleSupport(Lifecycle lifecycle) {
		super();
		this.lifecycle = lifecycle;
	}

	private Lifecycle lifecycle = null;
	private LifecycleListener listeners[] = new LifecycleListener[0];

	// 添加一个观察者
	public void addLifecycleListener(LifecycleListener listener) {
		synchronized (listeners) {
			LifecycleListener results[] = new LifecycleListener[listeners.length + 1];
			for (int i = 0; i < listeners.length; i++)
				results[i] = listeners[i];
			results[listeners.length] = listener;
			listeners = results;
		}
	}

	// 找出所注册的观察者
	public LifecycleListener[] findLifecycleListeners() {
		return listeners;
	}

	// 通知观察者
	public void fireLifecycleEvent(String type, Object data) {
		LifecycleEvent event = new LifecycleEvent(lifecycle, type, data);
		LifecycleListener interested[] = null;
		synchronized (listeners) {
			interested = (LifecycleListener[]) listeners.clone();
		}
		for (int i = 0; i < interested.length; i++)
			interested[i].lifecycleEvent(event);
	}

	// 删除一个观察者
	public void removeLifecycleListener(LifecycleListener listener) {
		synchronized (listeners) {
			int n = -1;
			for (int i = 0; i < listeners.length; i++) {
				if (listeners[i] == listener) {
					n = i;
					break;
				}
			}
			if (n < 0)
				return;
			LifecycleListener results[] = new LifecycleListener[listeners.length - 1];
			int j = 0;
			for (int i = 0; i < listeners.length; i++) {
				if (i != n)
					results[j++] = listeners[i];
			}
			listeners = results;
		}
	}
}