package bigdata.anomaly.detection.utils;

import bigdata.anomaly.detection.datastructure.Click;
import bigdata.anomaly.detection.datastructure.Impression;
import bigdata.anomaly.detection.tools.EsClient;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/23.
 */
public class Utils {

    /**
     * 日期相关数据结构
     */
    private static Map<String, String> map = new HashMap<>();
    private static String[] monthStr = {"Jan", "Feb", "Mar", "Apr", " May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private static String[] monthNum = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

    static {
        for (int i = 0; i < 12; i++) {
            map.put(monthStr[i], monthNum[i]);
        }
    }

    /**
     * 将 2017-Feb-28 09:10:49.401 格式的日期转换成 2017-02-28 09:10:49.401
     */
    public static String transfer(String date) {
        String ans = null;

        String[] dateSplit = date.split("-");
        String month = dateSplit[1];
        month = map.get(month);

        ans = dateSplit[0] + "-" + month + "-" + dateSplit[2];
        return ans;
    }

    /**
     * 将字符串集合转换成 Click 结构体集合
     */
    public static List<Click> convertToClicks(List<String> lines) {

        Click click = null;
        List<Click> clicks = new ArrayList<>();

        for (String line : lines) {
            // 分割字符串
            String[] splits = line.split("\t");

            // 转换日期格式
            splits[5] = transfer(splits[5]);

            // 设置每个点击的所有属性
            click = new Click();
            click.setEventType(splits[0]);
            click.setRequestId(splits[1]);
            click.setMediaName(splits[2]);
            click.setMaterialId(splits[3]);
            click.setImpressionId(splits[4]);
            click.setClickTime(splits[5]);
            click.setOperator(splits[6]);
            click.setNetworkType(splits[7]);
            click.setDeviceType(splits[8]);
            click.setOs(splits[9]);
            click.setAreaCode(splits[10]);
            click.setPlanId(splits[11]);
            click.setAdvertiserId(splits[12]);
            click.setMediaPrice(splits[13]);
            click.setCustomerPrice(splits[14]);
            click.setUserId(splits[15]);

            // 将封装好的 Click 加入集合
            clicks.add(click);
        }
        return clicks;
    }

    /**
     * 将字符串集合转换成 Click 结构体集合
     */
    public static List<Impression> convertToImpressions(List<String> lines) {

        Impression impression = null;
        List<Impression> impressions = new ArrayList<>();

        for (String line : lines) {
            // 分割字符串
            String[] splits = line.split("\t");

            // 转换日期格式
            splits[5] = transfer(splits[5]);

            try {
                // 设置每个曝光的所有属性
                impression = new Impression();
                impression.setEventType(splits[0]);
                impression.setRequestId(splits[1]);
                impression.setMediaName(splits[2]);
                impression.setMaterialId(splits[3]);
                impression.setImpressionId(splits[4]);
                impression.setImpressTime(splits[5]);
                impression.setOperator(splits[6]);
                impression.setNetworkType(splits[7]);
                impression.setDeviceType(splits[8]);
                impression.setOsType(splits[9]);
                impression.setAreaCode(splits[10]);
                impression.setPlanId(splits[11]);
                impression.setAdvertiserId(splits[12]);
                impression.setMediaPrice(splits[13]);
                impression.setCustomerPrice(splits[14]);
                impression.setUserId(splits[15]);
                impression.setAppName(splits[16]);
            } catch (Exception e) {
                // 有异常输出异常信息，并将该跳过此次封装
                e.printStackTrace();
                continue;
            }

            // 将封装好的 ImpressionFraud 加入集合
            impressions.add(impression);
        }

        return impressions;
    }

    /**
     * 将指定目录下的所有文件读取，并格式化写入到ES中
     */
    public static void writeFileToEs(String dir) {
        String json;
        List<String> lines;
        EsClient esClient = new EsClient();
        List<String> jsons = new ArrayList<>();
        List<Impression> impressions = new ArrayList<>();

        for (int i = 1; i <= 40; i++) {
            lines = FileOperation.read(String.format(dir + "/ImpressedData%d.dat", i));
            impressions = Utils.convertToImpressions(lines);

            for (Impression impression : impressions) {
                json = JSON.toJSONString(impression);
                jsons.add(json);
            }
            esClient.write(jsons, "all_impressions", "fraud");
            System.out.println(i);
            jsons.clear();
        }

    }
}
