package scripts;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Utils.ExtentTestManager;
import commonlib.TestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AddBooks extends TestBase {

	@Test
	public void addBooks() {

		JSONObject requestBody = new JSONObject(read.getCellDataValue("APITestData", "Body", "AddBooks"));

		requestBody.put("userId", userID);

		Response res = av.postMethod(requestBody, "postMethodOneHeader", "AddBooks");

		JsonPath json = new JsonPath(res.asString());

		int bookAddedStatusCode = HttpStatus.SC_CREATED;

		int bookAlreadyExistsStatusCode = HttpStatus.SC_BAD_REQUEST;

		String methodName = new Exception().getStackTrace()[0].getMethodName();

		if (bookAddedStatusCode == res.getStatusCode()) {

			ExtentTestManager.getTest().log(Status.PASS,
					methodName + " is Passed as the Book added Successfully Passed and the Expected Status " + bookAddedStatusCode
							+ " and Actual Status : " + res.getStatusCode());
		} else if (bookAlreadyExistsStatusCode == res.getStatusCode()) {
			
			ExtentTestManager.
			getTest().
			log(Status.INFO,
					methodName + " is passed because Book is already added by the user and the Expected status "
							+ bookAlreadyExistsStatusCode + " and Actual status " + res.getStatusCode());
		} else {
			ExtentTestManager.getTest().log(Status.FAIL,
					methodName + " is Failed may be due the User is not authorized or Error occurred and Expected status is "
							+ bookAddedStatusCode + " and Actual Status is " + res.statusCode() + " And response is "
							+ res.prettyPrint());

		}
	}

}
