import org.testng.annotations.Test;
import files.APIPojoClass;
import files.Location;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.List;
import io.restassured.RestAssured;
public class GoogleAPIUsingPojoClassSerialization {
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
		
		
		given().queryParams("key","qaclick123").body(api)
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200);
	
	
	}
	



}


