package Utils;

import org.apache.log4j.PropertyConfigurator;

import commonlib.TestBase;

public class Logs {

	private Logs() {
        PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/test/resources/log4j.properties");
    }

    public static void Ulog(String statement) {
        TestBase.logs.info(statement);
    }

    public static void debug(String message, String exception) {
    	TestBase.logs.debug(message + exception);

    }
}
