package com.thread.immuable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 模式角色：不可变模式  ImmutableObject
 */
public class MMSRouter {
    private static volatile MMSRouter instance = new MMSRouter();

    private final Map<String, MMSInfo> routeMap;

    MMSRouter() {
        this.routeMap = this.retrieveRouteMapFromDB();
    }

    private Map<String, MMSInfo> retrieveRouteMapFromDB() {
        // TODO code
        return null;
    }

    public MMSInfo getMMS(String msisdnPrefix) {
        return routeMap.get(msisdnPrefix);
    }

    /**
     * set instance 保证了每次调动的router都是全新的instance，避免并发开销
     * 但是却加大了内存开销
     *
     * @param newInstance
     */
    public static void setInstance(MMSRouter newInstance) {
        instance = newInstance;
    }

    public static MMSRouter getInstance() {
        return instance;
    }

    public Map<String, MMSInfo> getRouteMap() {
        return Collections.unmodifiableMap(deepCopy(routeMap));
    }

    private Map<String, MMSInfo> deepCopy(Map<String, MMSInfo> routeMap) {
        Map<String, MMSInfo> result = new HashMap<>();
        for (Map.Entry<String, MMSInfo> entry : routeMap.entrySet()) {
            String key = entry.getKey();
            MMSInfo value = entry.getValue();
            result.put(key, value);
        }
        return result;
    }
}
