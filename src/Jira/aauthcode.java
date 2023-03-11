package Jira;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.getCourse;
import pojo.webAutomation;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

public class aauthcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	String url= "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AWtgzh6CIl7YxD7Yg-XhVPXyUFwURvblL6e7rM9uU-1Hx2HMGuGQQTscMmGmj__x5cqTRg&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		String parUrl= url.split("code=")[1];
		String code=parUrl.split("&scope")[0];
		String[] expectedTitle= {"Selenium Webdriver Java", "Cypress", "Protractor"};
		
	String getAccessToken= given().urlEncodingEnabled(false).queryParams("code",code)
                .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("grant_type", "authorization_code")
                .queryParams("state", "verifyfjdss")
                .queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
                .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php").when().log().
                all().post("https://www.googleapis.com/oauth2/v4/token").asString();
              
                JsonPath js= new JsonPath(getAccessToken);
               String access_token= js.getString("access_token");
               System.out.println(access_token);
                
		
		
               getCourse gc=given().header("Content_Type","application/json")
			.queryParam("access_token", access_token).expect().defaultParser(Parser.JSON)
		.when().get("https://rahulshettyacademy.com/getCourse.php")
		.as(getCourse.class);
               System.out.println (gc.getLinkedIn());
               System.out.println (gc.getInstructor());
               
            List<webAutomation> wA= gc.getCourses().getWebAutomation();
            for(int i=0; i<wA.size(); i++) {
            	 System.out.println (wA.get(i).getCourseTitle());
            	 if (wA.get(i).getCourseTitle().equalsIgnoreCase("Cypress"))
            	 {
            		 System.out.println(wA.get(i).getPrice());
            	 }
            }
            
            ArrayList<String> a= new ArrayList<String>();
            for(int j=0; j<wA.size(); j++) {
            	a.add(wA.get(j).getCourseTitle());
            }
           List<String> b=Arrays.asList(expectedTitle);
            Assert.assertTrue(a.equals(b));
               
  
	}
}
 
