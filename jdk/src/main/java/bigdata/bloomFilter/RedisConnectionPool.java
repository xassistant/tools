package bigdata.bloomFilter;

import bigdata.bloomFilter.util.ConfigFileName;
import bigdata.bloomFilter.util.EnvKit;
import bigdata.bloomFilter.util.LoadFileUtils;
import bigdata.bloomFilter.util.RedisAddress;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Redis连接池
 */
public class RedisConnectionPool {

    private static final Logger logger = LoggerFactory.getLogger(RedisConnectionPool.class);

    private static final ConcurrentMap<String, JedisPool> poolCache = new ConcurrentHashMap<>();

    static {
        initPool();
    }

    /**
     * 初始化redis连接池
     * # common
     * maxIdle=500
     * maxActive=500
     * maxWait=5000
     * timeout=0
     * testOnBorrow=true
     * testOnReturn=true
     * <p>
     * # input
     * collectorInputHost=127.0.0.1
     * collectorInputPort=6379
     * collectorInputPassword=
     */
    private static void initPool() {
        try {
            Properties config = LoadFileUtils.loadProperties(EnvKit.isWin() ? ConfigFileName.RedisDev.getValue() : ConfigFileName.Redis.getValue());

            String intelligenceHost = config.getProperty("intelligenceHost", "127.0.0.1");
            int intelligencePort = Integer.parseInt(config.getProperty("intelligencePort", "6379"));
            String intelligencePassword = config.getProperty("intelligencePassword");

            int timeOut = Integer.parseInt(config.getProperty("timeout", "5000"));
            JedisPoolConfig jedisPoolConfig = getJedisPoolConfig(config);

            JedisPool intelligencePoll;
            if (StringUtils.isBlank(intelligencePassword)) {
                intelligencePoll = new JedisPool(jedisPoolConfig, intelligenceHost, intelligencePort, timeOut);
            } else {
                intelligencePoll = new JedisPool(jedisPoolConfig, intelligenceHost, intelligencePort, timeOut, intelligencePassword);
            }

            loadPoolCache(RedisAddress.IntelligenceInput.getValue(), intelligencePoll);
//            loadPoolCache(RedisAddress.MlNormalization.getValue(), mlNormalizationPool);
        } catch (Exception e) {
            logger.error("initPool error: ", e);
        }
    }

    /**
     * 获得JedisPoolConfig
     *
     * @param config Properties config
     * @return JedisPoolConfig
     */
    private static JedisPoolConfig getJedisPoolConfig(Properties config) {
        if (config == null) return null;

        final JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        jedisPoolConfig.setMaxTotal(Integer.parseInt(config.getProperty("maxActive", "500")));
        jedisPoolConfig.setMaxIdle(Integer.parseInt(config.getProperty("maxIdle", "500")));
        jedisPoolConfig.setMaxWaitMillis(Integer.parseInt(config.getProperty("maxWait", "5000")));
        jedisPoolConfig.setTestOnBorrow(Boolean.parseBoolean(config.getProperty("testOnBorrow", "true")));
        jedisPoolConfig.setTestOnReturn(Boolean.parseBoolean(config.getProperty("testOnReturn", "true")));

        return jedisPoolConfig;
    }

    /**
     * 更新配置
     *
     * @param ruleOutputHost     ruleOutputHost
     * @param ruleOutputPort     ruleOutputPort
     * @param ruleOutputPassword ruleOutputPassword
     * @param esOutputHost       esOutputHost
     * @param esOutputPort       esOutputPort
     * @param esOutputPassword   esOutputPassword
     * @return true or false
     */
    public static boolean updateRedisOutputResource(String ruleOutputHost, int ruleOutputPort, String ruleOutputPassword, String esOutputHost, int esOutputPort, String esOutputPassword) {
        try {
            Properties config = LoadFileUtils.loadProperties(EnvKit.isWin() ? ConfigFileName.RedisDev.getValue() : ConfigFileName.Redis.getValue());
            final int timeOut = Integer.parseInt(config.getProperty("timeout"));

            JedisPoolConfig jedisPoolConfig = getJedisPoolConfig(config);
            JedisPool ruleOutputPool, esOutputPool;

            if (ruleOutputHost != null) {
                if (StringUtils.isBlank(ruleOutputPassword)) {
                    ruleOutputPool = new JedisPool(jedisPoolConfig, ruleOutputHost, ruleOutputPort, timeOut);
                } else {
                    ruleOutputPool = new JedisPool(jedisPoolConfig, ruleOutputHost, ruleOutputPort, timeOut, ruleOutputPassword);
                }

                /**
                 * 测试连接是否成功
                 */
                ruleOutputPool.getResource();
                JedisPool oldPool = poolCache.get(RedisAddress.RuleOutput.getValue());
                oldPool.destroy();
                loadPoolCache(RedisAddress.RuleOutput.getValue(), ruleOutputPool);

                config.setProperty("ruleOutputHost", ruleOutputHost);
                config.setProperty("ruleOutputPort", String.valueOf(ruleOutputPort));
                config.setProperty("ruleOutputPassword", ruleOutputPassword);
            }

            if (esOutputHost != null) {
                if (StringUtils.isBlank(esOutputPassword)) {
                    esOutputPool = new JedisPool(jedisPoolConfig, esOutputHost, esOutputPort, timeOut);
                } else {
                    esOutputPool = new JedisPool(jedisPoolConfig, esOutputHost, esOutputPort, timeOut, esOutputPassword);
                }

                /**
                 * 测试连接是否成功
                 */
                esOutputPool.getResource();
                JedisPool oldPool = poolCache.get(RedisAddress.EsOutput.getValue());
                oldPool.destroy();
                loadPoolCache(RedisAddress.EsOutput.getValue(), esOutputPool);

                config.setProperty("esOutputHost", esOutputHost);
                config.setProperty("esOutputPort", String.valueOf(esOutputPort));
                config.setProperty("esOutputPassword", esOutputPassword);
            }

            return LoadFileUtils.updateProperties(config, EnvKit.isWin() ? ConfigFileName.RedisDev.getValue() : ConfigFileName.Redis.getValue());

        } catch (Exception e) {
            logger.error("updateOutputResource error: " + e.getMessage());
            return false;
        }
    }

    /**
     * 加载pool cache
     *
     * @param index     index
     * @param jedisPool jedisPool
     * @return boolean
     */
    public static boolean loadPoolCache(String index, JedisPool jedisPool) {
        try {
            if (poolCache.containsKey(index)) {
                poolCache.replace(index, jedisPool);
            } else {
                poolCache.put(index, jedisPool);
            }
            return true;
        } catch (Exception e) {
            logger.error("loadPoolCache error: " + e.getMessage());
            return false;
        }
    }

    /**
     * 获得连接
     *
     * @param redisType redisType
     * @return Jedis
     */
    public static Jedis getJedisResource(String redisType) {
        try {
            return poolCache.containsKey(redisType) ? poolCache.get(redisType).getResource() : null;
        } catch (Exception e) {
            logger.error("getJedisResource error: " + e.getMessage());
            return null;
        }
    }

    /**
     * 获得连接池
     *
     * @param redisType redisType
     * @return Jedis
     */
    public static JedisPool getJedisPool(String redisType) {
        try {
            return poolCache.containsKey(redisType) ? poolCache.get(redisType) : null;
        } catch (Exception e) {
            logger.error("getJedisResource error: " + e.getMessage());
            return null;
        }
    }

    /**
     * 释放连接，返回到连接池
     *
     * @param jedis     jedis
     * @param redisType redisType
     */
    public static void returnResource(Jedis jedis, String redisType) {
        try {
            if (jedis != null && redisType != null) {
                if (poolCache.containsKey(redisType)) {
                    JedisPool jedisPool = poolCache.get(redisType);
                    jedisPool.returnResourceObject(jedis);
                }
            }
        } catch (Exception e) {
            logger.error("returnResource error: " + e.getMessage());
        }
    }

    /**
     * 释放redis对象
     *
     * @param jedis     jedis
     * @param redisType redisType
     */
    public static void returnBrokenResource(Jedis jedis, String redisType) {
        try {
            if (jedis != null && redisType != null) {
                if (poolCache.containsKey(redisType)) {
                    JedisPool jedisPool = poolCache.get(redisType);
                    jedisPool.returnResourceObject(jedis);
                }
            }
        } catch (Exception e) {
            logger.error("returnBrokenResource error: " + e.getMessage());
        }
    }

}