package echarts;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EchartGenerate {

    private static final String JSpath = "D:\\sa\\echart-java\\echarts-convert\\echarts-convert1.js";


    public static void main(String[] args) {
        String optiona = "{\"calculable\":true,\"xAxis\":[{\"type\":\"value\"}],\"yAxis\":[{\"type\":\"category\",\"data\":[\"一\",\"日aaaaaaaaaaaaaaaaa\",\"的\",\"发\"]}],\"series\":[{\"type\":\"bar\",\"itemStyle\":{\"normal\":{\"label\":{\"show\":true,\"position\":\"insideRight\"}}},\"data\":[320,302,301,334]}]}\n";
        //String options = "test";
        Map<String, Object> resultMap = new HashMap<>();

        generateEChart(optiona, resultMap);

    }


    public static String generateEChart(String options, Map<String, Object> resultMap) {
        String dataPath = writeFile(options);
        String fileName = "test-" + UUID.randomUUID().toString().substring(0, 8) + ".png";
        String path = "D:/temp/Echart/" + fileName;
        try {
            File file = new File(path);     //文件路径（路径+文件名）
            if (!file.exists()) {   //文件不存在则创建文件，先创建目录
                File dir = new File(file.getParent());
                dir.mkdirs();
                file.createNewFile();
            }
            String cmd = "D:\\sa\\echart-java\\bin\\phantomjs " + JSpath + " -infile " + dataPath + " -outfile " + path + " -width 400 -height 300";
            System.out.println(cmd);
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return path;
        }
    }

    public static String writeFile(String options) {
        String dataPath = "D:\\chartData\\data" + UUID.randomUUID().toString().substring(0, 8) + ".json";
        try {
            /* 写入Txt文件 */
            File writename = new File(dataPath); // 相对路径，如果没有则要建立一个新的output.txt文件
            if (!writename.exists()) {   //文件不存在则创建文件，先创建目录
                File dir = new File(writename.getParent());
                dir.mkdirs();
                writename.createNewFile(); // 创建新文件
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write(options); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataPath;
    }
}
