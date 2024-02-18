import org.testng.annotations.Test;
import files.PojoClass;
import files.WebAutomationJson;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.List;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
public class OAuth_DeSerialization {

	@Test
	public void oauth()
	{
		//RestAssured.baseURI = "https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token";	
		String response = given().formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.formParams("grant_type","client_credentials")
		.formParams("scope","trust")
		.when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
		.then().log().all().extract().response().asString();
		
		
		JsonPath js = new JsonPath(response);
		String accesstken = js.getString("access_token");
		System.out.println(accesstken);		
		PojoClass response1 = given().queryParam("access_token", accesstken)//.expect().defaultParser(Parser.JSON)
		.when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(PojoClass.class);
	
		System.out.println(response1.getLinkedIn());
		System.out.println(response1.getInstructor());
		for(int i = 0;i<response1.getCourses().getApi().size();i++){
			
			
		String cursetitle = response1.getCourses().getApi().get(i).getCourseTitle();	
		if(cursetitle.equalsIgnoreCase("SoapUI Webservices testing"))
		{
		System.out.println(cursetitle);
		}
		}
		List<WebAutomationJson> we = response1.getCourses().getWebAutomation();
		for(int i = 0;i<we.size();i++)
		{
			
			System.out.println(we.get(i).getCourseTitle());
		}
	}
}


