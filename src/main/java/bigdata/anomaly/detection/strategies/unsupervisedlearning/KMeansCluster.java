package bigdata.anomaly.detection.strategies.unsupervisedlearning;


import bigdata.anomaly.detection.tools.EsClient;
import bigdata.anomaly.detection.tools.FeatureProcessor;
import smile.clustering.KMeans;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/7/6.
 */
public class KMeansCluster {
    private static int pageSize = 100000;
    private static String index = "all_impressions";
    private static String[] attrs = {
            "mediaName", "materialId", "operator", "networkType", "deviceType",
            "osType", "areaCode", "advertiserId", "userId", "appName"
    };

    public static void main(String[] args) throws Exception {

        // 初始化 ES 和 Redis 客户端
        EsClient esClient = new EsClient();

        // 特征处理对象初始化
        FeatureProcessor featureProcessor = new FeatureProcessor(attrs,true);

        // 特征选择并数字化
        List<String> jsons = esClient.readByPageSize(index, pageSize);
        int cnt = 0;
        while (jsons != null && jsons.size() != 0) {

            System.out.print(jsons.size() + "\t");

            if (cnt % 10 == 9) {
                System.out.println();
            }
            cnt++;

            featureProcessor.countAttrTimesToRedis(jsons);
            jsons = esClient.readByPageSize(index, pageSize);
        }

        System.out.println("\nFeature select over...");

        // 将各属性所有分量的出现次数从Redis中获取并保存在mapList成员中
        featureProcessor.getFeatureCountToMapList();

        // 特征抽取
        int i = 0;
        int rows = (int) esClient.getDocConuts(index);
        int cols = attrs.length;
        double[][] trainData = new double[rows][cols];

        jsons = esClient.readByPageSize(index, pageSize);
        while (jsons != null && jsons.size() != 0) {
            for (String json : jsons) {
                trainData[i++] = featureProcessor.fetureExtract(json);
            }
            jsons = esClient.readByPageSize(index, pageSize);
        }
        System.out.println("Feature extract over...");


        // 进行无监督聚类
        KMeans kMeans = new KMeans(trainData, 2, 1000, 10);
        System.out.println("Clustering over...");


        // 预测并将异常结果写入ES
        int label;
        double[] vector;
        List<String> ans1 = new ArrayList<>();
        List<String> ans2 = new ArrayList<>();

        jsons = esClient.readByPageSize(index, pageSize);
        while (jsons != null && jsons.size() != 0) {
            for (String json : jsons) {
                vector = featureProcessor.fetureExtract(json);
                label = kMeans.predict(vector);
                if (label == 0) {
                    ans1.add(json);
                    if (ans1.size() >= 100000) {
                        esClient.write(ans1, "class1", "fraud");
                        ans1.clear();
                    }
                } else {
                    ans2.add(json);
                    if (ans2.size() >= 100000) {
                        esClient.write(ans2, "class2", "fraud");
                        ans2.clear();
                    }
                }
            }
            jsons = esClient.readByPageSize(index, pageSize);
        }

        // 剩余的写入ES
        if (ans1.size() > 0) {
            esClient.write(ans1, "class1", "fraud");
            ans1.clear();
        }

        if (ans2.size() > 0) {
            esClient.write(ans2, "class2", "fraud");
            ans2.clear();
        }


        System.out.println("Game Over...");

    }
}
