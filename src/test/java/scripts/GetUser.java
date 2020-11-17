package scripts;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Utils.ExtentTestManager;
import commonlib.TestBase;
import io.restassured.response.Response;

public class GetUser extends TestBase {

	@Test
	public void getUser() {

		Response res = av.getMethod(userID, "getMethodOneHeader", "GetUser");

		System.out.println(res.prettyPrint());

		Assert.assertEquals(HttpStatus.SC_OK, res.getStatusCode());

		logs.info("GetUser completed" + res.asString());

		String methodName = "GetUser";

		int statusCode = HttpStatus.SC_OK;

		if (statusCode == res.getStatusCode()) {
			ExtentTestManager.getTest().log(Status.PASS, methodName + " Positive Scenario Passed Expected Status "
					+ statusCode + " and Actual Status : " + res.getStatusCode());
		}else {
			ExtentTestManager.getTest().log(Status.FAIL, methodName + " Positive Scenario Failed Expected Status "
					+ statusCode + " and Actual Status : " + res.getStatusCode() + " and the Response is : <br />" + res.prettyPrint() +"<br />");
		}
	}

}
