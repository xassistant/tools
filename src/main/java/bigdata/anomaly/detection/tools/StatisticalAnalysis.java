package bigdata.anomaly.detection.tools;


import bigdata.anomaly.detection.datastructure.AttrInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * Created by Administrator on 2017/7/12.
 */
public class StatisticalAnalysis {

    // 属性集
    public static String[] attrs = new String[]{
            "platformUserId",
            "ua",
            "ip",
            "province",
            "city",
            "mediaId",
            "pageUrl",
            "creativeId",
            "adSlotId",
            "adSlotType",
            "userTag",
            "os",
            "deviceType",
            "osv",
            "exchangeId",
            "bidId",
            "model",
            "carrier",
            "connectionType",
            "memberId"
    };

    // ES 客户端对象
    public EsClient esClient;

    // 保存个具体属性值出现次数的Map对象
    public Map<String, Map<String, Integer>> maps;


    public StatisticalAnalysis() {
        esClient = new EsClient();

        maps = new HashMap<>();
        for (String attr : attrs) {
            maps.put(attr, new HashMap<>());
        }
    }


    /**
     * 读取ES原始数据，并将属性统计结果保存于哈希表Maps
     */
    public void countAndSaveToMaps(String index) {

        int cnt;
        String attrVal;
        JSONObject jsonObject;

        System.out.println(index);

        List<String> jsons = esClient.readByPageSize(index, 100000);
        System.out.println(index);
        Map<String, Integer> map1;

        // 读取ES，将属性值出现次数统计到maps里
        while (jsons != null && jsons.size() > 0) {

            // 处理每个属性
            for (String json : jsons) {

                jsonObject = JSONObject.parseObject(json);

                for (String attr : attrs) {

                    map1 = maps.get(attr);
                    attrVal = jsonObject.get(attr).toString();

                    if (attrVal.equals("")) {
                        attrVal = "null";
                    }

                    if (map1.containsKey(attrVal)) {

                        // 具体属性值次数+1，保存
                        cnt = map1.get(attrVal);
                        cnt++;
                        map1.put(attrVal, cnt);
                    } else {

                        // 属性值未出现过，初始化
                        map1.put(attrVal, 1);
                    }
                }
            }
            System.out.println("********");
            jsons = esClient.readByPageSize(index, 100000);
        }
    }

    /**
     * 读取 Maps 属性统计哈希表，并将其分门别类写入ES
     *
     * 参数 index 是原始数据的ES索引
     */
    public void writeMapsToEs(String index) {

        String json;
        List<String> jsons = new ArrayList<>();
        double sampleCount = esClient.getDocConuts(index);

        int counts;
        double percentage;
        AttrInfo attrInfo;
        List<AttrInfo> attrInfos = new ArrayList<>();
        Map<String, Integer> map;

        for (String attr : maps.keySet()) {

            map = maps.get(attr);
            attrInfos.clear();
            for (String attrVal : map.keySet()) {
                counts = map.get(attrVal);
                percentage = counts / sampleCount;
                attrInfo = new AttrInfo(attrVal, counts, percentage);
                attrInfos.add(attrInfo);
            }

            for (AttrInfo attrInfo1 : attrInfos) {
                json = JSON.toJSONString(attrInfo1);
                jsons.add(json);

                if (jsons.size() >= 100000) {

                    esClient.write(jsons, attr, "test");
                    jsons.clear();
                }
            }

            if (jsons.size() > 0) {
                esClient.write(jsons, attr, "test");
                jsons.clear();
            }

        }
    }

    public static void main(String[] args) {

        String index = "auction";
        StatisticalAnalysis sa = new StatisticalAnalysis();


        System.out.println("Start...");

        sa.countAndSaveToMaps(index);
        System.out.println("countAndSaveToMaps Over...");

        sa.writeMapsToEs(index);
        System.out.println("Game over...");


    }

}
