package putrest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;

import java.time.LocalDate;

import org.testng.Assert;

public class put1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        // declare base uri and request body variable
		String baseURI="https://reqres.in/";
		String requestBody="{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}";
		// parse request param
		JsonPath jsprequest=new JsonPath(requestBody);
		String req_name=jsprequest.getString("name");
		String req_job=jsprequest.getString("job");
		String currentdate=LocalDate.now().toString();
		
		// configure response
		RestAssured.baseURI=baseURI;
		int statusCode=given().header("Content-Type","application/json").body(requestBody).when().put("/api/users/2").then().extract().statusCode();
		String responseBody=given().header("Content-Type","application/json").body(requestBody).when().put("/api/users/2").then().extract().response().asString();
		System.out.println(responseBody);
		
		// fetch response body
		JsonPath jspresponse=new JsonPath(responseBody);
		String res_name=jspresponse.getString("name");
		String res_job=jspresponse.getString("job");
		String res_updatedAt=jspresponse.getString("updatedAt");
		
		// validate
		Assert.assertEquals(res_name, req_name);
		Assert.assertEquals(res_job, req_job);
		Assert.assertEquals(res_updatedAt.substring(0,10), currentdate);
		
	}

}
