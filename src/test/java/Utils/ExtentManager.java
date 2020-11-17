package Utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports instance;
	public static final String EXTENT_REPORT_PATH = System.getProperty("user.dir") + "/target/Reports/";

	static ExtentReports getInstance() {
		if (instance == null)
			createInstance();
		return instance;
	}

	private static ExtentReports createInstance() {
		String time = systemTimeStamp();
		getReportPath(EXTENT_REPORT_PATH);
		String fileName = EXTENT_REPORT_PATH + "AutomationReport_" + time + ".html";
	       
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("Automation Report");
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
 
        instance = new ExtentReports();
        instance.attachReporter(htmlReporter);
        //Set environment details
        instance.setSystemInfo("OS", "Windows");
        instance.setSystemInfo("AUT", "QA");
 
        return instance;
	}
	
	public static String systemTimeStamp() {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yyyy_h_mm_ss_a");
        return sdf.format(date);
    }
	
	 private static void getReportPath (String path) {
	    	File testDirectory = new File(path);
	        if (!testDirectory.exists()) {
	        	if (testDirectory.mkdir()) {
	                System.out.println("Directory: " + path + " is created!" );
	            } else {
	                System.out.println("Failed to create directory: " + path);
	            }
	        } else {
	            System.out.println("Directory already exists: " + path);
	        }
	    }

}
