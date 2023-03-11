package newTest;

import org.testng.Assert;

import Files.Payloads;
import io.restassured.path.json.JsonPath;

public class coursesDEmo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
JsonPath js= new JsonPath(Payloads.Courses());

//Print No of courses returned by API

int count=js.getInt("courses.size()");
System.out.println(count);

// Print Purchase Amount

int purchaseAmount=js.getInt("dashboard.purchaseAmount");
System.out.println(purchaseAmount);

// Print Title of the first course

String titleFirstCourse=js.getString("courses[0].title");
System.out.println(titleFirstCourse);

//Print All course titles and their respective Prices


for (int i=0; i<count; i++) {
	
	String courseTitles=js.get("courses["+i+"].title");  
	  System.out.println(courseTitles);	
	  System.out.println(js.get("courses["+i+"].price").toString());
	}

//Print no of copies sold by RPA Course

for (int i=0; i<count; i++) {

	String courseTitles=js.get("courses["+i+"].title");
	if(courseTitles.equalsIgnoreCase("RPA")) {
		System.out.println(js.get("courses["+i+"].copies").toString());
		break;
	}
	}

//Verify if Sum of all Course prices matches with Purchase Amount
int sum=0;
for (int i=0; i<count; i++) {
	int price=js.get("courses["+i+"].price"); 
	int copies=js.get("courses["+i+"].copies"); 
	int total=price*copies;
	System.out.println(total);
	sum=sum+total;
	}
System.out.println(sum);
Assert.assertEquals(sum, purchaseAmount);
	}
}
