package post;
import static io.restassured.RestAssured.given;

import java.time.LocalDate;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class post1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// step 1 : declare base URI and request body variable
		String baseURI="https://reqres.in/";
		String requestBody="{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
		
		// step 2 declare base URI
				RestAssured.baseURI=baseURI;
		
		// step 3 set the expected result
				JsonPath jsprequest=new JsonPath(requestBody);
				String req_name=jsprequest.getString("name");
				String req_job=jsprequest.getString("job");
				
				String currentdate=LocalDate.now().toString();
			
		
		// step 4 configure the response
		int statusCode=given().header("Content-Type","application/json").body(requestBody).when().post("/api/users").then().extract().statusCode();
		String responseBody=given().header("Content-Type","application/json").body(requestBody).when().post("/api/users").then().extract().response().asString();
		System.out.println(responseBody);
		
		// step 5 parse the response
		JsonPath jspresponse=new JsonPath(responseBody);
		String res_name=jspresponse.getString("name");
		String res_job=jspresponse.getString("job");
		String res_id=jspresponse.getString("id");
		String res_createdAt=jspresponse.getString("createdAt");
		
		// step 6 validation
		Assert.assertEquals(res_name, req_name);
		Assert.assertEquals(res_job, req_job);
		Assert.assertNotNull(res_id);
		Assert.assertEquals(res_createdAt.substring(0,10), currentdate);
	
			
	}

}
