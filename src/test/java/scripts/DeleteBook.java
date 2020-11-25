package scripts;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Utils.ExtentTestManager;
import commonlib.TestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DeleteBook extends TestBase {

	@Test
	public void deleteBook() {

		String methodName = new Exception().getStackTrace()[0].getMethodName();

		JSONObject requestBody = new JSONObject(read.getCellDataValue("APITestData", "Body", "DeleteBook"));

		requestBody.put("userId", userID);

		System.out.println(requestBody.toString());

		Response res = av.deleteMethod(userID, requestBody, "deleteMethodOneHeaderBody", "DeleteBook");

		int statusCode = HttpStatus.SC_NO_CONTENT;

		int alreadyDeletedStatusCode = HttpStatus.SC_BAD_REQUEST;

//		JSONObject json = new JSONObject(res.asString());

		String body = res.asString();

		
		

		if (statusCode == res.getStatusCode()) {

			ExtentTestManager.getTest().log(Status.PASS,
					methodName + " : Book successfully deleted and Expected statusCode is :" + statusCode
							+ " and Actual is " + res.statusCode());
		} else if (alreadyDeletedStatusCode == res.getStatusCode()) {
			String message = JsonPath.from(body).get("message");
			ExtentTestManager.getTest().log(Status.INFO, methodName + " : " + /* json.getString("message") */"message "
					+ message + " The book is not available or already Deleted by user ");
		} else {

			ExtentTestManager.getTest().log(Status.FAIL, methodName + " : " + "Failed " + res.asString());
		}

	}
}
