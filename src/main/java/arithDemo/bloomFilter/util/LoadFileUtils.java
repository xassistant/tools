package arithDemo.bloomFilter.util;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;

/**
 * 读取文件工具类
 */
public class LoadFileUtils {

    private static Logger logger = LoggerFactory.getLogger(LoadFileUtils.class);

    /**
     * 加载properties文件
     *
     * @param fileName 文件名.properties
     * @return Properties对象
     */
    public static Properties loadProperties(String fileName) {
        Properties properties = null;
        try {
            logger.debug("config file loading...");
            InputStream inputStream;
            String resourcePath = getResourcePath();
            String path = resourcePath + File.separator + fileName;
            inputStream = getInputStream(fileName, path);

            properties = new Properties();
            properties.load(inputStream);
        } catch (Exception e) {
            logger.error("loadProperties error:" + e);
        }
        return properties;
    }

    public static List<String> loadFile(String fileName, Charset encoding) {
        try {
            logger.debug("config file loading...");
            InputStream inputStream;
            String resourcePath = getResourcePath();
            String path = resourcePath + File.separator + fileName;
            inputStream = getInputStream(fileName, path);

            return IOUtils.readLines(inputStream, encoding);
        } catch (Exception e) {
            logger.error("loadProperties error:" + e);
        }
        return null;
    }

    private static InputStream getInputStream(String fileName, String path) throws FileNotFoundException {
        InputStream inputStream;
        if (!isFileExist(path)) {
            logger.debug("default file loading...");
            inputStream = LoadFileUtils.class.getClassLoader().getResourceAsStream(fileName);
        } else {
            logger.debug("ResourcePath file loading...");
            inputStream = new FileInputStream(path);
        }
        return inputStream;
    }

    /**
     * 更新properties文件
     *
     * @param properties properties
     * @param fileName   fileName
     * @return true or false
     */
    public static boolean updateProperties(Properties properties, String fileName) {
        boolean flag = true;
        OutputStream outputStream = null;
        try {
            String resourcePath = getResourcePath();

            outputStream = new FileOutputStream(resourcePath + File.separator + fileName);
            properties.store(outputStream, null);
        } catch (Exception e) {
            flag = false;
            logger.error("updateProperties error:" + e);
        } finally {
            try {
                if (outputStream != null) outputStream.close();
            } catch (IOException e) {
                flag = false;
                logger.error("flushProperties error:" + e);
            }
        }
        return flag;
    }

    private static String getResourcePath() {
        String resourcePath = System.getProperty("ResourcePath");
        if (resourcePath != null) {
            return resourcePath;
        } else {
            resourcePath = "./analysis_resource/";
            File dir = new File(resourcePath);
            if (!dir.exists()) dir.mkdirs();
            return resourcePath;
        }
    }

    private static boolean isFileExist(String path) {
        if (path == null || path.isEmpty()) return false;
        File file = new File(path);
        return file.isFile() && file.exists();
    }

}
