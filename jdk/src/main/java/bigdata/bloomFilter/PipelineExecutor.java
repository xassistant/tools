package bigdata.bloomFilter;

import redis.clients.jedis.Pipeline;

@FunctionalInterface
public interface PipelineExecutor {
    void load(Pipeline pipeline);
}