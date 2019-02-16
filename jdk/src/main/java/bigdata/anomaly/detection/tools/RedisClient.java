package bigdata.anomaly.detection.tools;

import bigdata.anomaly.detection.utils.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2017/6/5.
 */
public class RedisClient {

    private Jedis jedis;
    public static RedisClient client = new RedisClient();

    private RedisClient() {

        // 获取 Redis 配置属性
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        ProjectConfig config = context.getBean(ProjectConfig.class);
        context.close();

        // 创建 Jedis 对象
        jedis = new Jedis(config.redisHost, config.redisPort, config.redisTimeout);
    }

    // 返回创建好的 Jedis 对象
    public static Jedis getJedis() {
        return client.jedis;
    }

}
