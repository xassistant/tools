package arithDemo.bloomFilter;

import redis.clients.jedis.Jedis;

@FunctionalInterface
public interface JedisExecutor<T> {
    T execute(Jedis jedis);
}
