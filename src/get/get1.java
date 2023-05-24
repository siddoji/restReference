package get;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
public class get1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        // declare base URI
		RestAssured.baseURI="https://reqres.in/";
		
		// configure response
		int statusCode=given().header("Content-Type","application/json").when().get("/api/users?page=2").then().extract().statusCode();
		String responseBody=given().header("Content-Type","application/json").when().get("/api/users?page=2").then().extract().response().asString();
		System.out.println(responseBody);
		// expected result
		int[]id= {7,8,9,10,11,12};
		String[] first_name= {"Michael","Lindsay", "Tobias","Byron","George","Rachel"};
		String[] last_name= { "Lawson","Ferguson","Funke","Fields","Edwards", "Howell"};
		
		// parse response
		JsonPath jspresponse=new JsonPath(responseBody);
		int count=jspresponse.getList("data").size();
		System.out.println(count);
		
		for(int i=0;i<count;i++)
		{
			// expected result
			int exp_id=id[i];
			String exp_first_name=first_name[i];
			String exp_last_name=last_name[i];
			
			// response param
			int res_id=jspresponse.getInt("data["+i+"].id");
			String res_first_name=jspresponse.getString("data["+i+"].first_name");
			String res_last_name=jspresponse.getString("data["+i+"].last_name");
			
			// validate 
			Assert.assertEquals(res_id, exp_id);
			Assert.assertEquals(res_first_name, exp_first_name);
			Assert.assertEquals(res_last_name, exp_last_name);
			
		}
		
		
	}

}
