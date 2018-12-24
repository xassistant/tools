package cache;

/**
 * @Author xlj
 * @Date 2018/12/23 14:56
 */
public interface CacheManager<T> {
    void put(String key, T data, long expire);

    T get(String key);

    void remove(String key);
}
