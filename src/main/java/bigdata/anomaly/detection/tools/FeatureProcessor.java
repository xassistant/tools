package bigdata.anomaly.detection.tools;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.*;

/**
 * Created by Administrator on 2017/6/14.
 */
public class FeatureProcessor {

    // 统计样本总数目
    private int sampleCount = 0;

    // 属性名称列表
    private List<String> attrList;

    // 存储所有属性各分量的出现次数
    List<Map<String, Integer>> mapList;

    // Jedis客户端与Pipeline
    private Jedis jedis;
    private Pipeline pipeline;

    public FeatureProcessor(String[] attrs, boolean flushDB) {
        jedis = RedisClient.getJedis();

        if (flushDB == true) {
            jedis.flushDB();
        }

        pipeline = jedis.pipelined();
        attrList = new ArrayList<>();
        mapList = new ArrayList<>();

        // 设置属性名称集合
        attrList.addAll(Arrays.asList(attrs));
    }

    /**
     * 统计每个需要统计的属性的各个值出现的次数，并写入Redis中
     */
    public void countAttrTimesToRedis(List<String> jsons) {

        // 更新样本出现的次数
        sampleCount += jsons.size();
        pipeline.incrBy("sampleCount", jsons.size());

        JSONObject jsonObject;
        for (String json : jsons) {
            jsonObject = JSONObject.parseObject(json);
            for (String attr : attrList) {
                pipeline.hincrBy(attr, jsonObject.get(attr).toString(), 1);
            }
        }

        // 一次性将结果写入Redis
        pipeline.sync();
    }

    /**
     * 将各属性所有分量的出现次数从Redis中获取并保存在mapList成员中
     */
    public void getFeatureCountToMapList() throws Exception {
        if (attrList == null || attrList.size() == 0) {
            throw new Exception("未设置属性名称集合！");
        } else if (jedis == null) {
            throw new Exception("Jedis 对象为空！");
        }

        // 获取样本总数
        String cntStr = jedis.get("sampleCount");
        sampleCount = Integer.parseInt(cntStr);

        // 临时变量
        Map<String, String> map1;
        Map<String, Integer> map;

        for (int i = 0; i < attrList.size(); i++) {
            map1 = jedis.hgetAll(attrList.get(i));
            map = new HashMap<>();
            for (String key : map1.keySet()) {
                int val = Integer.parseInt(map1.get(key));
                map.put(key, val);
            }
            mapList.add(map);
        }
    }

    /**
     * 根据给定的json字符串抽取特征向量
     */
    public double[] fetureExtract(String json) throws Exception {

        // 检查参数
        if (attrList == null || attrList.size() == 0) {
            throw new Exception("attrList is empty...");
        } else if (mapList == null || mapList.size() == 0) {
            throw new Exception("mapList is empty...");
        }

        double times;
        String attrVal = null;
        double[] vector = new double[attrList.size()];
        JSONObject jsonObject = JSONObject.parseObject(json);
        Map<String, Integer> map = null;

        for (int i = 0; i < attrList.size(); i++) {
            try {
                attrVal = jsonObject.get(attrList.get(i)).toString();
                map = mapList.get(i);
                times = map.get(attrVal);
                vector[i] = times / sampleCount;
            } catch (Exception e) {
                System.out.println(mapList.size());
                for (Map<String, Integer> map0 : mapList) {
                    System.out.println(map0.toString());
                }
                System.out.println(json);
                System.out.println(map.toString());
                System.out.println(attrVal);
                System.exit(0);
            }
        }
        return vector;
    }

    public int getSampleCount() {
        return sampleCount;
    }

    public List<String> getAttrList() {
        return attrList;
    }

    public int getAttrsNum() {
        return attrList.size();
    }

    public List<Map<String, Integer>> getMapList() {
        return mapList;
    }
}
