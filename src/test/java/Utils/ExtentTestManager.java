package Utils;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentTestManager {
	protected static ExtentTest test;
	static Map<Integer, ExtentTest>  extentTestMap= new HashMap<Integer, ExtentTest>();
	static ExtentReports extent = ExtentManager.getInstance();

	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized void endTest() {
		extent.flush();
	}

	public static synchronized ExtentTest startTest(String testName) {
		return startTest(testName, "");
	}

	public static synchronized ExtentTest startTest(String testName, String desc) {
		test = extent.createTest(testName, desc);
		test.log(Status.INFO, "Started execution of : " + testName);
		extentTestMap.put((int) (Thread.currentThread().getId()), test);
		return test;
	}
	
	public static synchronized void logsGeneration(String message) {
		test.log(Status.INFO, message);
	}
}
