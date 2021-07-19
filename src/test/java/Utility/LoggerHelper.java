package Utility;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerHelper {

    private static boolean root = false;
    String filepath = "";
    String propFile = filepath.concat("log4j.properties");

    public static Logger getLogger(Class cls) {
        if (root) {
            return Logger.getLogger(cls);
        }
        PropertyConfigurator.configure("log4j.properties");
        root = true;
        return Logger.getLogger(cls);
    }

//    public static void main(String[] args) {
//        Logger log = LoggerHelper.getLogger(LoggerHelper.class);
//        log.info("I am test");
//    }
}