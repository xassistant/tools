package bigdata.anomaly.detection.tools;

import bigdata.anomaly.detection.datastructure.Impression;
import bigdata.anomaly.detection.utils.FileOperation;
import bigdata.anomaly.detection.utils.ProjectConfig;
import bigdata.anomaly.detection.utils.Utils;
import com.alibaba.fastjson.JSON;
 import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.SearchHit;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/8.
 */
public class EsClient {

    private TransportClient client;
    private SearchResponse scrollRespons = null;
    private String clusterName;
    private String host;
    private int port;


    public EsClient() {

        // 获取配置参数
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        ProjectConfig config = context.getBean(ProjectConfig.class);
        clusterName = config.clusterName;
        host = config.esHost;
        port = config.esPort;

        try {
            Settings settings = Settings.settingsBuilder().put("cluster.name", clusterName).build();
            client = TransportClient.builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得ES中指定index中的文档总条数
     */
    public int getDocConuts(String index) {
        int count;
        CountResponse response = client.prepareCount(index).get();
        count = (int) response.getCount();
        return count;
    }

    /**
     * 写入单条数据到ES
     */
    public void write(String json, String index, String type) {
        client.prepareIndex(index, type).setSource(json).get();
    }

    /**
     * 写入多条数据到ES
     */
    public void write(List<String> jsons, String index, String type) {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        IndexRequestBuilder requestBuilder;

        for (String json : jsons) {
            requestBuilder = client.prepareIndex(index, type).setSource(json);
            bulkRequest.add(requestBuilder);
        }
        bulkRequest.get();
    }

    /**
     * 分页读取结果
     */
    public List<String> readByPageSize(String index, int pageSize) {
        String json;
        List<String> jsons = new ArrayList<>();

        if (scrollRespons == null) {
            scrollRespons = client.prepareSearch(index)
                    .setScroll(new TimeValue(60000))
                    .setSize(pageSize).get();

            for (SearchHit hit : scrollRespons.getHits().getHits()) {
                json = JSON.toJSONString(hit.getSource());
                jsons.add(json);
            }
        } else {
            scrollRespons = client.prepareSearchScroll(scrollRespons.getScrollId())
                    .setScroll(new TimeValue(60000)).get();

            // 数据读取完毕
            if (scrollRespons.getHits().getHits().length == 0) {
                scrollRespons = null; // 保证下次还能从头开始
                return null;
            }

            for (SearchHit hit : scrollRespons.getHits().getHits()) {
                json = JSON.toJSONString(hit.getSource());
                jsons.add(json);
            }
        }
        return jsons;
    }

    /**
     * 本测试只是将指定目录下的所有文件的日志写人ES中
     */
    public static void main(String[] args) {


        EsClient client = new EsClient();
        List<String> names = FileOperation.getFileNamesFromDir("ImpressedData");

        List<String> lines;
        List<Impression> impressions;
        List<String> jsons = new ArrayList<>();

        for (String name : names) {

            lines = FileOperation.read(name);
            impressions = Utils.convertToImpressions(lines);

            for (Impression impression : impressions) {
                jsons.add(JSON.toJSONString(impression));
                if (jsons.size() >= 10000) {
                    client.write(jsons, "all_impressions", "fraud");
                    jsons.clear();
                }
            }

        }

        if (jsons.size() > 0) {
            client.write(jsons, "all_impressions", "fraud");
            jsons.clear();
        }
    }
}
