package Jira;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.GoogleMaps;
import pojo.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class serialization {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://rahulshettyacademy.com";
		GoogleMaps G=new GoogleMaps();
		Location L= new Location();
		L.setLat(-38.383494);
		L.setLng(33.427362);
		G.setLocation(L);
		G.setAccuracy(50);
		G.setName("Shylaja Kishor");
		G.setPhone_number("04742790351");
		G.setAddress("Kallummotil veedu, Kaval, Kollam");
		List<String> types=new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		G.setTypes(types);
		G.setLanguage("French-IN");
		G.setWebsite("http://google.com");
		
		
		
		Response R=given().log().all().queryParam("key", "qaclick123").header("Content_Type","application/json")
		.body(G).when().post("/maps/api/place/add/json").then().extract().response();
		String responseString=R.asString();
		System.out.println(responseString);
		
	}

}
