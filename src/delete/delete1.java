package delete;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class delete1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://reqres.in/";
		//step 2 : Configure Request body
		int statusCode=given().header("Content-Type","application/json").log().all().when().
				delete("/api/users/2").then().extract().statusCode();

	System.out.println(statusCode);
	}

}
