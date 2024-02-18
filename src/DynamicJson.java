import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import files.Payloads;
import files.ReusableMethods;import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class DynamicJson {
	
	@Test(dataProvider = "booksdata")
	public void addbook(String isbn, String aisle)
	{	
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().header("Content-Type","application/json").body(Payloads.bookdata(isbn,aisle)).
		when().post("/Library/Addbook.php")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		JsonPath js = ReusableMethods.rawToJson(response);
		String id = js.get("ID");
		System.out.println(id);
		
		
		given().header("Content-Type","application/json").body(Payloads.datadelete(id))
		.when().delete("/Library/DeleteBook.php").
		then().log().all().statusCode(200);
	
	}

@DataProvider(name = "booksdata")
public Object[][] getData()
{

	
	return new Object[][] {{"adfem","981908"},{"admne","983809"},{"adnme","987809"}};	
}
}










