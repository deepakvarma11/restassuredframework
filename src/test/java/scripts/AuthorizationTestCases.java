package scripts;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import Utils.ExtentTestManager;
import commonlib.TestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AuthorizationTestCases extends TestBase {

	@Test(priority = 1)
	public void authorizationPositive() {
		JSONObject requestBody = new JSONObject(read.getCellDataValue("APITestData", "Body", "Authorization"));

		Response res = av.postMethod(requestBody, "postMethodNoHeaders", "Authorization");

		JsonPath responseJson = res.jsonPath();

		System.out.println(res.prettyPrint());

		Assert.assertEquals(HttpStatus.SC_OK, res.getStatusCode());
		Assert.assertTrue(res.asString().contains("true"));

		int statusCode = HttpStatus.SC_OK;
		String methodName = "AuthorizationPostiveCase";

		if (res.asString().contains("true") && statusCode == res.getStatusCode()) {
			ExtentTestManager.getTest().log(Status.PASS, methodName + " Positive Scenario Passed Expected Status "
					+ statusCode + " and Actual Status : " + res.getStatusCode());
		} else {
			ExtentTestManager.getTest().log(Status.FAIL,
					methodName + " Positive Scenario Failed Expected Status " + statusCode + " and Actual Status : "
							+ res.getStatusCode() + " and the Response is : <br />" + res.prettyPrint() + "<br />");
		}
	}

	

	@Test(priority = 2)
	public void authorizationNegative() {
		JSONObject requestBody = new JSONObject(read.getCellDataValue("APITestData", "Body", "Authorization"));

		requestBody.put("password", "Deepak@11");

		Response res = av.postMethod(requestBody, "postMethodNoHeaders", "Authorization");

		JsonPath responseJson = res.jsonPath();

		System.out.println(res.prettyPrint());

//		softassert.assertAll();

		Assert.assertEquals(HttpStatus.SC_NOT_FOUND, res.getStatusCode());
		Assert.assertFalse(res.asString().contains("true"));

		int statusCode = HttpStatus.SC_NOT_FOUND;
		String methodName = "AuthorizationPostiveCase";

		if (res.asString().contains("true")) {

			ExtentTestManager.getTest().log(Status.FAIL,
					methodName + " Negative Scenario Failed Expected Status " + statusCode + " and Actual Status : "
							+ res.getStatusCode() + " and the Response is : <br />" + res.prettyPrint() + "<br />");

		} else {
			ExtentTestManager.getTest().log(Status.PASS, methodName + " Positive Scenario Passed Expected Status "
					+ statusCode + " and Actual Status : " + res.getStatusCode());
		}
	}

}
