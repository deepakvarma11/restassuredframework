package scripts;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Utils.TestListener;
import commonlib.TestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TokenGenerationTestCase extends TestBase {

	@Test
	void getTokenPositive() {

		
		JSONObject requestBody = new JSONObject(read.getCellDataValue("APITestData", "Body", "Token"));

		Response res = av.postMethod(requestBody, "postMethodNoHeaders", "Token");

		JsonPath responseJson = res.jsonPath();
		
		System.out.println(res.prettyPrint());

		Assert.assertEquals(HttpStatus.SC_OK, res.getStatusCode());
		Assert.assertTrue(res.asString().contains("token"));

		token = responseJson.getString("token");
//		System.out.println(token);
		
		

	}
	
	@Test
	public void getTokenNegative() {
		JSONObject requestBody = new JSONObject(read.getCellDataValue("APITestData", "Body", "Token"));
		
		requestBody.put("password", "Deepak@11");

		Response res = av.postMethod(requestBody, "postMethodNoHeaders", "Token");

		JsonPath responseJson = res.jsonPath();
		
		System.out.println(res.prettyPrint());

		Assert.assertEquals(HttpStatus.SC_OK, res.getStatusCode());
		Assert.assertTrue(res.asString().contains("token"));

		Assert.assertEquals(responseJson.getString("status"),"Failed");
		
		System.out.println("Result "+ responseJson.getString("result"));
	}
}
