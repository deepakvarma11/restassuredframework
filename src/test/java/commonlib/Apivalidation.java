package commonlib;

import org.json.JSONObject;

import Utils.ExcelReader;
import io.restassured.response.Response;

public class Apivalidation extends TestBase {

	RestAssuredClient rc = new RestAssuredClient();
	TestReusable tr = new TestReusable();
	Response res = null;

	public Response getMethod(String locationId, String method, String testCase) {

		read = new ExcelReader(System.getProperty("user.dir") + "/src/test/resources/Suites/Suites.xlsx");

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

		String BearerHeaderValue = null;
		if (Header1Value.equalsIgnoreCase("Bearer")) {
			String token = tr.getTokenAndAuthorization();
			BearerHeaderValue = Header1Value + " " + token;
		}else if(Header2Value.equalsIgnoreCase("Bearer")) {
			String token = tr.getTokenAndAuthorization();
			BearerHeaderValue = Header2Value + " " + token;
		}


		if (method.equalsIgnoreCase("getMethodOneHeader")) {
			String endPoint = resourceURI + "/" + locationId;
			logs.info("Endpoint now for getUser " + endPoint);
			System.out.println("Endpoint now " + endPoint);
			res = rc.getMethodOneHeader(Header1Name, BearerHeaderValue, endPoint);
		}

		if (method.equalsIgnoreCase("getMethodNoHeader")) {
			logs.info("Final Endpnt :"+ resourceURI);
			res = rc.getMethodNoHeader(resourceURI);
		}

		return res;

	}

	public Response postMethod(JSONObject requestBody, String postMethod, String testCase) {

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


		String body = requestBody.toString();
		
		
		String BearerHeaderValue = null;
		if (Header1Value.equalsIgnoreCase("Bearer")) {
			String token = tr.getTokenAndAuthorization();
			BearerHeaderValue = Header1Value + " " + token;
		}else if(Header2Value.equalsIgnoreCase("Bearer")) {
			String token = tr.getTokenAndAuthorization();
			BearerHeaderValue = Header2Value + " " + token;
		}

		if (postMethod.equalsIgnoreCase("postMethodNoHeaders")) {
			res = rc.postMethodNoHeaders(body, resourceURI);
		}

		return res;
	}

}
