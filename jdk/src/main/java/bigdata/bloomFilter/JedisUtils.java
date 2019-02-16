package bigdata.bloomFilter;

import bigdata.bloomFilter.util.RedisAddress;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.List;

public class JedisUtils {

    private JedisPool jedisPool;

    public JedisUtils() {
        jedisPool = RedisConnectionPool.getJedisPool(RedisAddress.IntelligenceInput.getValue());
    }

    public <T> T execute(JedisExecutor<T> jedisExecutor) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedisExecutor.execute(jedis);
        }
    }

    public List<Object> pipeline(List<PipelineExecutor> pipelineExecutors) {
        try (Jedis jedis = jedisPool.getResource()) {
            Pipeline pipeline = jedis.pipelined();
            for (PipelineExecutor executor : pipelineExecutors)
                executor.load(pipeline);
            return pipeline.syncAndReturnAll();
        }
    }

    public void pipeline(PipelineExecutor executor) {
        try (Jedis jedis = jedisPool.getResource()) {
            Pipeline pipeline = jedis.pipelined();
            executor.load(pipeline);
            pipeline.sync();
        }
    }

    public List<Object> pipelineExec(PipelineExecutor executor) {
        try (Jedis jedis = jedisPool.getResource()) {
            Pipeline pipeline = jedis.pipelined();
            executor.load(pipeline);
            return pipeline.syncAndReturnAll();
        }
    }
}