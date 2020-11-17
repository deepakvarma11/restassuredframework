package commonlib;

import org.json.JSONObject;

import Utils.ExcelReader;
import io.restassured.response.Response;

public class Apivalidation extends TestBase {

	RestAssuredClient rc = new RestAssuredClient();
	TestReusable tr = new TestReusable();
	private String header1Value;

	public Response getMethod(String locationId, String method, String testCase) {

		read = new ExcelReader(System.getProperty("user.dir") + "/src/test/resources/Suites/Suites.xlsx");

		Response res = null;

		String resourceURI = read.getCellDataValue("APITestData", "ResourceURI", testCase);
		String Header1Name = read.getCellDataValue("APITestData", "Header1Name", testCase);
		String Header1Value = read.getCellDataValue("APITestData", "Header1Value", testCase);
		String Header2Name = read.getCellDataValue("APITestData", "Header2Name", testCase);
		String Header2Value = read.getCellDataValue("APITestData", "Header2Value", testCase);
		String Query1Key = read.getCellDataValue("APITestData", "Query1Key", testCase);
		String Query1Value = read.getCellDataValue("APITestData", "Query1Value", testCase);
		String Query2Key = read.getCellDataValue("APITestData", "Query2Key", testCase);
		String Query2Value = read.getCellDataValue("APITestData", "Query2Value", testCase);
		String Query3Key = read.getCellDataValue("APITestData", "Query3Key", testCase);
		String Query3Value = read.getCellDataValue("APITestData", "Query3Value", testCase);
		
		if(Header1Value.equalsIgnoreCase("Bearer")) {
			String token = tr.getTokenAndAuthorization();
			Header1Value = Header1Value + " " + token;
		}
		
//		if(Header2Value.equalsIgnoreCase("Bearer")) {
//			String token = tr.getTokenAndAuthorization();
//			Header2Value = Header2Value + " " + token;
//		}
		
		String endPoint = resourceURI +"/"+ locationId;
		
		System.out.println("Endpoint now " + endPoint);
		if(method.equalsIgnoreCase("getMethodOneHeader")) {
			res = rc.getMethodOneHeader(Header1Name, Header1Value, endPoint);
		}

		return res;

	}

	public Response postMethod(JSONObject requestBody, String postMethod, String testCase) {

		read = new ExcelReader(System.getProperty("user.dir") + "/src/test/resources/Suites/Suites.xlsx");

		Response res = null;

		String resourceURI = read.getCellDataValue("APITestData", "ResourceURI", testCase);
		String HeaderKey = read.getCellDataValue("APITestData", "HeaderName", testCase);
		String HeaderValue = read.getCellDataValue("APITestData", "HeaderValue", testCase);
		String Query1Key = read.getCellDataValue("APITestData", "Query1Key", testCase);
		String Query1Value = read.getCellDataValue("APITestData", "Query1Value", testCase);
		String Query2Key = read.getCellDataValue("APITestData", "Query2Key", testCase);
		String Query2Value = read.getCellDataValue("APITestData", "Query2Value", testCase);
		String Query3Key = read.getCellDataValue("APITestData", "Query3Key", testCase);
		String Query3Value = read.getCellDataValue("APITestData", "Query3Value", testCase);

		String body = requestBody.toString();

		if (postMethod.equalsIgnoreCase("postMethodNoHeaders")) {
			res = rc.postMethodNoHeaders(body, resourceURI);
		}

		return res;
	}

}
