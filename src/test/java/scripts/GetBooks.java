package scripts;

import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Utils.ExtentTestManager;
import commonlib.TestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetBooks extends TestBase {

	@Test
	public void getBooks() {
		Response res = av.getMethod(userID, "getMethodNoHeader", "GetBooks");

		int statusCode = HttpStatus.SC_OK;
		String methodName = new Exception().getStackTrace()[0].getMethodName();

		JSONObject ExpectedResponse = new JSONObject();
		ExpectedResponse.put("isbn", "9781449325862");
		ExpectedResponse.put("title", "Git Pocket Guide");

		String jsonString1 = res.asString();
		List<Map<String, String>> booksOfUser1 = JsonPath.from(jsonString1).get("books");
		JSONObject ActualResponseObject = new JSONObject(booksOfUser1.get(0));

		System.out.println("firsobject " + ActualResponseObject.toString());

		ActualResponseObject.remove("subTitle");
		ActualResponseObject.remove("author");
		ActualResponseObject.remove("publish_date");
		ActualResponseObject.remove("publisher");
		ActualResponseObject.remove("pages");
		ActualResponseObject.remove("description");
		ActualResponseObject.remove("website");

		System.out.println("After removing " + ActualResponseObject.toString());

		JSONCompareResult result = JSONCompare.compareJSON(ExpectedResponse, ActualResponseObject,
				JSONCompareMode.STRICT);

		boolean compareResult = false;

		System.out.println(result.passed());
		if (result.passed() == true) {
			compareResult = true;
		}

		ExtentTestManager
				.logsGeneration("Actual response and Expected response are same and result is " + compareResult);

		String jsonString = res.asString();
		List<Map<String, String>> booksOfUser = JsonPath.from(jsonString).get("books");
//		System.out.println(booksOfUser.get(0));
		Assert.assertNotEquals(0, booksOfUser.size());

		if (statusCode == res.statusCode() && booksOfUser.size() != 0 && compareResult == true) {
			ExtentTestManager.getTest().log(Status.PASS, methodName + " GetBooks Passed Expected Status " + statusCode
					+ " and Actual Status : " + res.getStatusCode());
		} else {
			ExtentTestManager.getTest().log(Status.FAIL,
					methodName + " GetBooks Passed Expected Status " + statusCode + " and Actual Status : "
							+ res.getStatusCode() + " and the Response is : <br />" + res.prettyPrint() + "<br />");
		}

// we can use this for POST method		System.out.println(json.getString("books.isbn").contains("9781449331818") +" Pass");

	}

}
