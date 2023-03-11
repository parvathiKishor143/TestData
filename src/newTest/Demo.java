package newTest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.Payloads;
import Files.reusableMethods;

//  ADD PLACE API
public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response= given().log().all().queryParam("key ", "qaclick123").header("Content_Type","application/json").body(Payloads.addPlace())
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200)
		.body("scope", equalTo("APP")).header("Server", equalTo("Apache/2.4.41 (Ubuntu)")).extract().response().asString();
		JsonPath js=reusableMethods.rawToJson(response);// parsing json
		 
		String placeId=js.getString("place_id");
		System.out.println(placeId);

	

	//  UPDATE PLACE
	String updatedAdress="70 Summer walk, CANNADA";
	given().log().all().queryParam("key", "qaclick123").header("Content_Type","application/json").
	body("{\r\n"
			+ "\"place_id\":\""+placeId+"\",\r\n"
			+ "\"address\":\""+updatedAdress+"\",\r\n"
			+ "\"key\":\"qaclick123\"\r\n"
			+ "}").when().put("/maps/api/place/update/json").then().assertThat().log().all().statusCode(200)
	.body("msg", equalTo("Address successfully updated"));
	
	
	// GET PLACE
	String getResponse=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
	.when().get("/maps/api/place/get/json")
	.then().assertThat().log().all().statusCode(200).body("address", equalTo("70 Summer walk, CANNADA"))
	.extract().response().asString();
	JsonPath js1=reusableMethods.rawToJson(getResponse);
	
	String actualAddress=js1.get("address");
	System.out.println(actualAddress);
	Assert.assertEquals(actualAddress,updatedAdress );
	
}
}
