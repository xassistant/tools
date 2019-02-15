package apache.commons.cli;


import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cli {

    private static final Logger log = LoggerFactory.getLogger(Cli.class.getName());
    private static Options options = new Options();
    private static String[] args = {"-h", "-v", "hello,world"};

    public static void main(String[] args) {
        args = new String[]{"-v", "hello,world", "-f", "/var/fileconfig.conf"};
        // 定义阶段
        options.addOption("h", "help", false, "show help.");
        options.addOption("v", "var", true, "Here you can set parameter .");
        options.addOption("f", "file", true, "Here you can set file .");

        // 解析阶段
        CommandLineParser parser = new BasicParser();
        CommandLine cmd = null;
        try {

            // 获取参数值，应用程序交互阶段。应用程序启动。
            cmd = parser.parse(options, args);
            if (cmd.hasOption("h"))
                help();

            if (cmd.hasOption("v")) {
                // cmd.getOptionValue("v") 获得v传入的参数值。
                log.info("Using cli argument -v=" + cmd.getOptionValue("v"));
                // Whatever you want to do with the setting goes here
            } else {
                log.info("MIssing v option");
                help();
            }
            if (cmd.hasOption("f")) {
                log.info("file name is : {}", cmd.getOptionValue("f"));
            } else {
                log.info("MIssing v option");
                help();
            }
        } catch (ParseException e) {
            log.error("Failed to parse comand line properties", e);
            help();
        }

    }

    private static void help() {
        // This prints out some help
        HelpFormatter formater = new HelpFormatter();
        formater.printHelp("SyslogUdpTest", options);
        System.exit(0);
    }

}
