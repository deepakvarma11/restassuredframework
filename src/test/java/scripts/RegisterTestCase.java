package scripts;

import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Utils.ExtentTestManager;
import commonlib.TestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.apache.http.HttpStatus;

public class RegisterTestCase extends TestBase {

	@Test
	void registered() {

			JSONObject requestBody = new JSONObject(read.getCellDataValue("APITestData", "Body", "Register"));

			System.out.println(requestBody.toString());

			Response res = av.postMethod(requestBody, "postMethodNoHeaders", "Register");

			JsonPath responseJson = res.jsonPath();

			Assert.assertEquals(HttpStatus.SC_NOT_ACCEPTABLE, res.getStatusCode());
			Assert.assertEquals("User exists!", responseJson.getString("message"));
			
			int statusCode = HttpStatus.SC_NOT_ACCEPTABLE;
			String methodName = "RegisterTestCases";
			
			if (statusCode == res.getStatusCode()) {
				ExtentTestManager.getTest().log(Status.PASS, methodName + " Positive Scenario Passed Expected Status "
						+ statusCode + " and Actual Status : " + res.getStatusCode());
			}else {
				ExtentTestManager.getTest().log(Status.FAIL, methodName + " Positive Scenario Failed Expected Status "
						+ statusCode + " and Actual Status : " + res.getStatusCode() + " and the Response is : <br />" + res.prettyPrint() +"<br />");
			}
	}

}
