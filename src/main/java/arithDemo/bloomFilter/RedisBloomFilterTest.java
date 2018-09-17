package arithDemo.bloomFilter;

import arithDemo.bloomFilter.util.ConfigFileName;
import arithDemo.bloomFilter.util.LoadFileUtils;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class RedisBloomFilterTest {

    private static final double FPP = 0.03;

    @Test
    public void test() {
        // ip
        List<String> ipList = LoadFileUtils.loadFile(ConfigFileName.IntelligenceIp.getValue(), StandardCharsets.UTF_8);
        RedisBloomFilter ipFilter = RedisBloomFilter.create("rediskey", ipList.size(), FPP);
        ipFilter.resetBitmap();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < ipList.size(); i++) {
            ipFilter.put(ipList.get(i));
        }

        System.out.println(System.currentTimeMillis() - startTime);

     /*   // url
        List<String> urlList = LoadFileUtils.loadFile(ConfigFileName.IntelligenceUrl.getValue(), StandardCharsets.UTF_8);
        RedisBloomFilter urlFilter = RedisBloomFilter.create(IndexKey.IntelligenceUrl.getValue(), urlList.size(), FPP);
        urlFilter.resetBitmap();
        for (int i = 0; i < urlList.size(); i++) {
            urlFilter.put(urlList.get(i));
            System.out.println("urlList" + i);
        }

        // domain
        List<String> domainList = LoadFileUtils.loadFile(ConfigFileName.IntelligenceDomain.getValue(), StandardCharsets.UTF_8);
        RedisBloomFilter domainFilter = RedisBloomFilter.create(IndexKey.IntelligenceUrl.getValue(), domainList.size(), FPP);
        domainFilter.resetBitmap();
        for (int i = 0; i < domainList.size(); i++) {
            domainFilter.put(domainList.get(i));
            System.out.println("domainList" + i);
        }*/
    }
}