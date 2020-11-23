package commonlib;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestAssuredClient {

	public Response getMethodOneHeader(String headerKey, String headerValue, String endPoint) {

		Response res = given().header(headerKey, headerValue).get(endPoint);

		return res;
	}

	public Response getMethodNoHeader(String endpoint) {

		Response res = given().get(endpoint);

		return res;
	}

	public Response postMethodOneHeader(String headerKey, String headerValue, String body, String endPoint) {
		
//		System.out.println("HeaderKey :"+ headerKey + " headerValue :"+ headerValue + " body :" + body + " endpoint " + endPoint);

		Response res = given().header(headerKey, headerValue).contentType("application/json").body(body).when()
				.post(endPoint);
		return res;
	}

	public Response postMethodNoHeaders(String body, String endPoint) {

		Response res = given().contentType("application/json").body(body).when().post(endPoint);
		return res;
	}

	public Response postMethodTwoHeaders(String header1Name, String header1value, String header2Name,
			String header2Value, String body, String endPoint) {

		Response res = given().header(header1Name, header1value).contentType("application/json").body(body).when()
				.post(endPoint);
		return res;
	}
}
