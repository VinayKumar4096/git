import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import files.Payloads;

import files.ReusableMethods;
public class Basics {
	public static void main(String[] args) throws IOException {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		//passing the data as string calling from another class
//		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
//		.body(Payloads.addPayload())		
//		.when().post("/maps/api/place/add/json").	
//		then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
//		.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

		
//		JsonPath js = new JsonPath(response);
//		String placeid = js.getString("place_id");
//		System.out.println(placeid);
	
		//passing the data from external json file(first convert json to bytes and then bytes to string
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\vinay\\Downloads\\addPlace.json"))))
				.when().post("/maps/api/place/add/json").	
				
				
				then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
				JsonPath js = new JsonPath(response);
				String placeid = js.getString("place_id");
				System.out.println(placeid);
		//update
		String Address = "Summer Palace Africa";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"		
	
				+ "\"place_id\":\""+placeid+"\",\r\n"
				+ "\"address\":\""+Address+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		
		.when().put("/maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		//given
		String response1 = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeid)		
		.when().get("maps/api/place/get/json").
		
		then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js1 = ReusableMethods.rawToJson(response1);
		js1.getString("address").equals(Address);
	}
	
	
	

}







