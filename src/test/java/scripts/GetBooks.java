package scripts;

import org.testng.annotations.Test;

import commonlib.TestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetBooks extends TestBase{

	@Test
	public void getBooks() {
		Response res = av.getMethod(userID, "getMethodNoHeader", "GetBooks");
//		res.getBody().prettyPrint();
		JsonPath json = res.jsonPath();
		
// we can use this for POST method		System.out.println(json.getString("books.isbn").contains("9781449331818") +" Pass");
		
		
	}

}
