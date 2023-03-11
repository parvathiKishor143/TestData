package Jira;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import static io.restassured.RestAssured.*;

public class commentPlay {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

			
RestAssured.baseURI="http://localhost:8080";
SessionFilter session= new SessionFilter();

String Auth=given().relaxedHTTPSValidation().header("Content_Type","application/json")
.body("{ \r\n"
		+ "    \"username\": \"parvathikishor143\", \r\n"
		+ "\"password\": \"Krishna@123\" \r\n"
		+ "}")
.log().all()
.filter(session).post("/rest/auth/1/session").then().log().all().assertThat().statusCode(200).extract().response().asString();

System.out.println("Res is"+ Auth);

given().pathParam("key", "10005").log().all().header("Content_Type","application/json").body("{\r\n"
		+ "    \"fields\": {\r\n"
		+ "        \"project\": {\r\n"
		+ "            \"key\": \"RES\"\r\n"
		+ "        },\r\n"
		+ "         \"summary\": \"Debit card defect\",\r\n"
		+ "           \"description\": \"Creating my API bug\",\r\n"
		+ "        \"issuetype\": {\r\n"
		+ "            \"name\": \"Bug\"\r\n"
		+ "        }\r\n"
		+ "    }\r\n"
		+ "}").filter(session).when().post("/rest/api/2/issue/{key}/comment").then().log().all().assertThat().statusCode(201);
	}
	
	

}
