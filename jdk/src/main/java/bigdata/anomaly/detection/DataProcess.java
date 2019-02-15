package bigdata.anomaly.detection;

import bigdata.anomaly.detection.tools.EsClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
public class DataProcess {

    public static void main(String[] args) {

        String dir = "D:\\数据\\auction_event";
        String line;
        List<String> lines = new ArrayList<>();

        EsClient esClient = new EsClient();
        FileReader fileReader;
        BufferedReader reader;
        File files = new File(dir);

        for (File file : files.listFiles()) {

            try {
                fileReader = new FileReader(file);
                reader = new BufferedReader(fileReader);

                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                    if (lines.size() >= 100000) {
                        esClient.write(lines, "auction", "test");
                        lines.clear();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
