package commonlib;

import org.json.JSONObject;

import Utils.ExcelReader;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestReusable extends TestBase {

	public String getTokenAndAuthorization() {

		av = new Apivalidation();
		read = new ExcelReader(System.getProperty("user.dir") + "/src/test/resources/Suites/Suites.xlsx");

		String body = read.getCellDataValue("APITestData", "Body", "Token");

		JSONObject requestBody = new JSONObject(body);

		Response res = av.postMethod(requestBody, "postMethodNoHeaders", "Token");

		JsonPath responseJson = res.jsonPath();
		token = responseJson.getString("token");

		JSONObject reqBody = new JSONObject(read.getCellDataValue("APITestData", "Body", "Authorization"));

		av.postMethod(reqBody, "postMethodNoHeaders", "Authorization");

		System.out.println(token);
		return token;

	}

//	public static void main(String[] args) {
//		TestReusable t = new TestReusable();
//		t.getTokenAndAuthorization();
//	}

}
