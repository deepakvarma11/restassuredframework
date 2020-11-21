package scripts;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Utils.ExtentTestManager;
import commonlib.TestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TokenGenerationTestCase extends TestBase {

	@Test(priority=0)
	void getTokenPositive() {

		JSONObject requestBody = new JSONObject(read.getCellDataValue("APITestData", "Body", "Token"));

		Response res = av.postMethod(requestBody, "postMethodNoHeaders", "Token");

		JsonPath responseJson = res.jsonPath();

		System.out.println(res.prettyPrint());

		Assert.assertEquals(HttpStatus.SC_OK, res.getStatusCode());
		Assert.assertTrue(res.asString().contains("token"));

		token = responseJson.getString("token");

		Boolean contains = res.asString().contains("token");
		int statusCode = HttpStatus.SC_OK;
		String methodName = new Exception().getStackTrace()[0].getMethodName();

		if (statusCode == res.getStatusCode() && contains == true) {
			ExtentTestManager.getTest().log(Status.PASS,
					methodName + " Positive Scenario Passed Expected Status " + statusCode + " and Actual Status : "
							+ res.getStatusCode() + " And Result is: " + responseJson.getString("result"));
		} else {
			ExtentTestManager.getTest().log(Status.FAIL,
					methodName + " Positive Scenario Failed Expected Status " + statusCode + " and Actual Status : "
							+ res.getStatusCode() + " and the Response is : <br />" + res.prettyPrint() + "<br />");
		}

	}

	@Test(priority=1)
	public void getTokenNegative() {
		JSONObject requestBody = new JSONObject(read.getCellDataValue("APITestData", "Body", "Token"));

		requestBody.put("password", "Deepak@11");

		Response res = av.postMethod(requestBody, "postMethodNoHeaders", "Token");

		JsonPath responseJson = res.jsonPath();

		System.out.println(res.prettyPrint());

		Assert.assertEquals(HttpStatus.SC_OK, res.getStatusCode());
		Assert.assertTrue(res.asString().contains("token"));

		Assert.assertEquals(responseJson.getString("status"), "Failed");

//		System.out.println("Result " + responseJson.getString("result"));

		String statusInResponse = responseJson.getString("status");

		int statusCode = HttpStatus.SC_OK;
		String responseStatus = "Failed";

		String methodName = new Exception().getStackTrace()[0].getMethodName();

		if (responseStatus.equalsIgnoreCase(statusInResponse)) {
			ExtentTestManager.getTest().log(Status.PASS,
					methodName + " Negative Scenario Passed Expected Status " + statusCode + " and Actual Status : "
							+ res.getStatusCode() + "  And Result is: " + responseJson.getString("result"));
		} else if (statusCode != res.getStatusCode()) {
			ExtentTestManager.getTest().log(Status.FAIL,
					methodName + " Negative Scenario Failed Expected Status " + statusCode + " and Actual Status : "
							+ res.getStatusCode() + " and the Response is : <br />" + res.prettyPrint() + "<br />");
		}
	}
}
