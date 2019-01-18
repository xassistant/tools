package bigdata.anomaly.detection.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/5/9.
 */
public class FileOperation {

    /**
     * 从文件中读取所有行
     */
    public static List<String> read(String path) {

        String line;
        List<String> lines = new ArrayList<String>();
        File file = new File(path);
        FileReader fileReader;
        BufferedReader reader;

        try {
            fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            reader.close();
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * 读取文件夹下所有文件内容，返回 List<String>
     */
    public static List<String> readDir(String dirPath) {
        String line;
        List<String> ans = null;
        List<String> lines = new ArrayList<String>();

        File file = new File(dirPath);
        for (File file1 : file.listFiles()) {
            String filepath = file1.getAbsolutePath();
            ans = readBigFile(filepath);
            lines.addAll(ans);
        }

        return lines;
    }

    /**
     * 返回文件夹下的所有文件名
     */
    public static List<String> getFileNamesFromDir(String dirPath) {
        String name;
        List<String> names = new ArrayList<>();

        File file = new File(dirPath);
        for (File file1 : file.listFiles()) {
            name = file1.getAbsolutePath();
            names.add(name);
        }

        return names;
    }

    /**
     * 读取超大文件（超过 100 MB）
     */
    public static List<String> readBigFile(String path) {
        String line;
        List<String> lines = new ArrayList<String>();

        try (FileInputStream inputStream = new FileInputStream(path)) {
            Scanner scanner = new Scanner(inputStream);

            long cnt = 0;
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
                cnt++;
                if (cnt % 100 == 99) {
                    System.out.println(cnt);
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lines;
    }


    /**
     * 将数据按行写入到指定文件中
     */
    public static void write(List<String> lines, String path, boolean isTrue) {
        File file = new File(path);
        FileWriter fileWriter;
        BufferedWriter writer;

        try {
            fileWriter = new FileWriter(file, isTrue);
            writer = new BufferedWriter(fileWriter);

            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }

            writer.close();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将目录 dir 下的文件内容写入到同一个名为 filePath
     * 目录的所有文件下，每个文件中最多10万行数据
     */
    public static void writeAllToFiles(String dir, String filePath) {

        String line;
        List<String> lines = new ArrayList<>();
        File file = new File(dir);
        FileReader fr = null;
        BufferedReader reader = null;

        int cnt = 0;
        for (File file1 : file.listFiles()) {
            try {
                fr = new FileReader(file1);
                reader = new BufferedReader(fr);

                while ((line = reader.readLine()) != null) {
                    lines.add(line);

                    if (lines.size() >= 100000) {
                        cnt++;
                        write(lines, filePath + cnt + ".dat", true);
                        lines.clear();
                        System.out.println(cnt);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {



    }
}
