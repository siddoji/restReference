package get;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

public class get {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        // step 1 declare base URI
		RestAssured.baseURI="https://reqres.in/";
		
		// step 2 configure response
		int stastusCode=given().header("Content-Type","application/json").when().get("api/users?page=2").then().extract().statusCode();
		String responseBody=given().header("Content-Type","application/json").when().get("api/users?page=2").then().extract().response().asString();
		System.out.println(responseBody);
		
		// step 3 expected result
		int []id= {7,8,9,10,11,12};
		String[] email= {"michael.lawson@reqres.in","lindsay.ferguson@reqres.in","tobias.funke@reqres.in","byron.fields@reqres.in",
				"george.edwards@reqres.in","rachel.howell@reqres.in"};
		
		String[]first_name= {"Michael","Lindsay","Tobias","Byron","George","Rachel"};
		
		// step 4 fetch response
		JsonPath jspresponse=new JsonPath(responseBody);
		int count=jspresponse.getList("data").size();
		
		for(int i=0;i<count;i++)
		{
			//expected result
			int exp_id=id[i];
			String exp_email=email[i];
			String exp_first_name=first_name[i];
			
			// response
			int res_id=jspresponse.getInt("data["+i+"].id");
			//int res_int_id=Integer.parseInt(res_id);
			String res_email=jspresponse.getString("data["+i+"].email");
			String res_first_name=jspresponse.getString("data["+i+"].first_name");
			
			// validate 
			Assert.assertEquals(res_id, exp_id);
			Assert.assertEquals(res_email, exp_email);
			Assert.assertEquals(res_first_name, exp_first_name);
			
			
		}
		
	}

}
