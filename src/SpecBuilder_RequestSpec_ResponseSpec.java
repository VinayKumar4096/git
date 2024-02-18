import org.testng.annotations.Test;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import files.APIPojoClass;
import files.Location;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.List;
import io.restassured.RestAssured;
public class SpecBuilder_RequestSpec_ResponseSpec {
	@Test
	public void postapi()
	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		APIPojoClass api = new APIPojoClass();
		
		
		api.setAccuracy(50);
		Location ln = new Location();
		ln.setLat(-38.383494);
		ln.setLng(33.427362);
		
		api.setLocation(ln);
		api.setName("Frontline house");
		api.setPhone_number("(+91) 983 893 3937");
		api.setAddress("29, side layout, cohen 09");
		
		
		api.setWebsite("http://google.com");
		api.setLanguage("French-IN");
		List<String> li = new ArrayList<String>();
		li.add("shoe park");
		li.add("shop");
		api.setTypes(li);
		
		RequestSpecification request = new RequestSpecBuilder().setContentType(ContentType.JSON).addQueryParam("key","qaclick123")
									.setBaseUri("https://rahulshettyacademy.com").build();
		
		RequestSpecification request1 = given().spec(request).body(api);
		ResponseSpecification response = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		Response resp = request1.when().post("/maps/api/place/add/json")
		.then().spec(response).extract().response();
		String responsestring = resp.asString();	
		System.out.println(responsestring);
		System.out.println("here also git related changes processed ");
//		given().queryParams("key","qaclick123").body(api)
//		.when().post("/maps/api/place/add/json")
//		.then().log().all().assertThat().statusCode(200);
	
	
	}
	



}


