package newTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import Files.Payloads;
import io.restassured.path.json.JsonPath;

public class sumValidation {

	@Test
	
	public void sumOfValidation(){
		JsonPath js= new JsonPath(Payloads.Courses());
		int sum=0;
		int count=js.getInt("courses.size()");
		for (int i=0; i<count; i++) {
			int price=js.get("courses["+i+"].price"); 
			int copies=js.get("courses["+i+"].copies"); 
			int total=price*copies;
			System.out.println(total);
			sum=sum+total;
			}
		System.out.println(sum);
		int purchaseAmount=js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseAmount);
	}
	
}
