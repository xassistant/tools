package bigdata.anomaly.detection;

import bigdata.anomaly.detection.tools.EsClient;
import bigdata.anomaly.detection.tools.FeatureProcessor;
import bigdata.anomaly.detection.tools.StatisticalAnalysis;
import smile.clustering.KMeans;

import java.util.ArrayList;
import java.util.List;

import bigdata.anomaly.detection.tools.StatisticalAnalysis.*;

/**
 * Created by Administrator on 2017/6/20.
 */
public class Test {

    public static void main(String[] args) {

        // ES 客户端
        EsClient esClient = new EsClient();

        // 特征处理类
        FeatureProcessor featureProcessor = new FeatureProcessor(StatisticalAnalysis.attrs, true);

        System.out.println("Start...");

        // 特征处理
        List<String> jsons00 = esClient.readByPageSize("auction", 100000);
        while (jsons00 != null && jsons00.size() > 0) {
            featureProcessor.countAttrTimesToRedis(jsons00);
            jsons00 = esClient.readByPageSize("auction", 100000);
        }
        System.out.println("Feature Process over...");

        try {
            featureProcessor.getFeatureCountToMapList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 读取数据，抽取特征
        int rows = esClient.getDocConuts("auction");
        int cols = featureProcessor.getAttrsNum();
        double[][] samples = new double[rows][cols];
        int cnt = 0;

        List<String> jsons = esClient.readByPageSize("auction", 100000);
        while (jsons != null && jsons.size() > 0) {
            for (String json : jsons) {
                try {
                    samples[cnt++] = featureProcessor.fetureExtract(json);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            jsons = esClient.readByPageSize("auction", 100000);
        }
        System.out.println("Feature Extract over...");

        // 训练模型
        KMeans kMeans = new KMeans(samples, 2, 10000, 10);
        System.out.println("Model Training over...");

        // 分类，写入不同的 ES index
        double[] sample;
        int label;
        List<String> jsons0 = new ArrayList<>();
        List<String> jsons1 = new ArrayList<>();

        jsons = esClient.readByPageSize("auction", 100000);
        while (jsons != null && jsons.size() > 0) {
            for (String json : jsons) {
                try {
                    sample = featureProcessor.fetureExtract(json);
                    label = kMeans.predict(sample);

                    if (label == 0) {
                        jsons0.add(json);
                        if (jsons0.size() >= 100000) {
                            esClient.write(jsons0, "auction00", "test");
                            jsons0.clear();
                        }
                    } else {
                        jsons1.add(json);
                        if (jsons1.size() >= 100000) {
                            esClient.write(jsons1, "auction11", "test");
                            jsons1.clear();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            jsons = esClient.readByPageSize("auction", 100000);
        }

        if (jsons0.size() > 0) {
            esClient.write(jsons0, "auction0", "test");
        }

        if (jsons1.size() > 0) {
            esClient.write(jsons1, "auction1", "test");
        }

        System.out.println("Game over...");
    }
}