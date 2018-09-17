package arithDemo.bloomFilter.util;

/**
 * EnvKit
 *
 * @author Adc-yang 2017/9/22
 */
public class EnvKit {

    public static boolean isWin() {
        String os = System.getProperty("os.name");
        return os.toLowerCase().startsWith("win");
    }
}
