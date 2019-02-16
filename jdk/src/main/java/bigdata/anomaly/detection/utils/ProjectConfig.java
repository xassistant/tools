package bigdata.anomaly.detection.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by Administrator on 2017/6/5.
 */

@Configuration
@ComponentScan
@PropertySource("classpath:config.properties")
public class ProjectConfig {

    /*************** Redis Configuration ********************/

    @Value("${redis.host}")
    public String redisHost;

    @Value("${redis.port}")
    public int redisPort;

    @Value("${redis.timeout}")
    public int redisTimeout;



    /************* ElasticSearch Configuration **************/

    @Value("${es.cluster.name}")
    public String clusterName;

    @Value("${es.host}")
    public String esHost;

    @Value("${es.port}")
    public int esPort;


    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
