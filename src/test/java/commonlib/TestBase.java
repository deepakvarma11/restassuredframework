package commonlib;

import static io.restassured.RestAssured.baseURI;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import Utils.ExcelReader;
import Utils.PropertyReader;

public class TestBase {

	protected ExcelReader read;
	protected String locationHeader;
	protected String baseUri;
	protected String userID;
	protected String token;

	protected Apivalidation av;
	PropertyReader pr;

	
	public static Logger logs = Logger.getLogger(TestBase.class);
	
	
	@BeforeSuite
	public void testBeforeSuite() {
		pr = new PropertyReader();
		av = new Apivalidation();
		System.out.println("testBeforeSuite()");
	}

	@AfterSuite
	public void testAfterSuite() {
		System.out.println("testAfterSuite()");
	}


	@BeforeClass
	public void beforeTest() {
		
		baseURI = pr.getBaseURI();
		logs.info(baseURI + " is baseURI");
		userID = pr.getUserId();
		logs.info(userID + " is userID");
		read = new ExcelReader(System.getProperty("user.dir") + "/src/test/resources/Suites/Suites.xlsx");
		
		logs.info("Before test completed");
	}

	@AfterClass
	public void afterTest() {
		logs.info("After test completed");
		av = null;
	}

}
