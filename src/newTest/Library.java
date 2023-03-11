package newTest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.Payloads;
import Files.reusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class Library{

	@ Test(dataProvider="inputData")
	public void addBook(String isbn, String aisle){
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().log().all().header("Content_Type","application/json").body(Payloads.library(isbn,aisle))
		.when().post("Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js=reusableMethods.rawToJson(response);
		String id=js.getString("ID");
		System.out.println(id);
		
	}
	@ DataProvider(name="inputData")
	
	public Object[][] input() {
		return new Object[][] {{"ghfgfd","ghghg"},{"g33hfgfd","gh33ghg"},{"ghfg44fd","ghg44hg"}};
	}
}
