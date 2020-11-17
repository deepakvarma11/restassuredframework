package commonlib;

import static io.restassured.RestAssured.baseURI;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import Utils.ExcelReader;

public class TestBase {

	protected ExcelReader read;
	protected String locationHeader;
	protected String baseUri;
	protected String userID;
	protected String token;

	protected Apivalidation av;

	
	public static Logger logs = Logger.getLogger(TestBase.class);
	
	@BeforeSuite
	public void testBeforeSuite() {
		System.out.println("testBeforeSuite()");
	}

	@AfterSuite
	public void testAfterSuite() {
		System.out.println("testAfterSuite()");
	}


	@BeforeClass
	public void beforeTest() {
		baseURI = "https://bookstore.toolsqa.com";
		av = new Apivalidation();
		read = new ExcelReader(System.getProperty("user.dir") + "/src/test/resources/Suites/Suites.xlsx");
		userID = "daac1f75-24cc-4e35-aa41-3de75f4baf28";
		logs.info("Before test completed");
	}

	@AfterClass
	public void afterTest() {
		logs.info("After test completed");
		av = null;
	}

}
