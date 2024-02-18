import org.testng.Assert;

import files.Payloads;
import io.restassured.path.json.JsonPath;
public class ComplexJsonParse {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonPath js = new JsonPath(Payloads.jsondata());
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		int purchaseamount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseamount);
		String courstitle = js.getString("courses[0].title");
		System.out.println(courstitle);
		for(int i = 0;i<js.getInt("courses.size()");i++)
		{
			String title = js.getString("courses["+i+"].title"); 
			int price = js.getInt("courses["+i+"].price");
			System.out.println(title+" = "+price);
		}
	
		
		for(int i = 0;i<js.getInt("courses.size()");i++)
		{
			String title = js.getString("courses["+i+"].title"); 
			if(title.equalsIgnoreCase("RPA"))
			{
				
				int copies = js.getInt("courses["+i+"].copies");
				System.out.println(copies);
			}
		
		
		}
		int sum = 0; int price = 0;int copies = 0;int multiple = 0;
		for(int i = 0;i<js.getInt("courses.size()");i++)
		{
			price = js.getInt("courses["+i+"].price");
			copies = js.getInt("courses["+i+"].copies");
			multiple = price * copies;
			System.out.println(multiple);
			sum = sum + multiple;
			
		}
		
		
		System.out.println(sum);
		int purchaseamount1 = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseamount1);
		
		
	}
}


class json
{
	public static void main(String[] args) {
		
	
	String jsondata = "{\r\n"
			+ "  \"Actors\": [\r\n"
			+ "    {\r\n"
			+ "      \"name\": \"Tom Cruise\",\r\n"
			+ "      \"age\": 56,\r\n"
			+ "      \"Born At\": \"Syracuse, NY\",\r\n"
			+ "      \"Birthdate\": \"July 3, 1962\",\r\n"
			+ "      \"photo\": \"https://jsonformatter.org/img/tom-cruise.jpg\"\r\n"
			+ "    },\r\n"
			+ "    {\r\n"
			+ "      \"name\": \"Robert Downey Jr.\",\r\n"
			+ "      \"age\": 53,\r\n"
			+ "      \"Born At\": \"New York City, NY\",\r\n"
			+ "      \"Birthdate\": \"April 4, 1965\",\r\n"
			+ "      \"photo\": \"https://jsonformatter.org/img/Robert-Downey-Jr.jpg\"\r\n"
			+ "    }\r\n"
			+ "  ]\r\n"
			+ "}";
	JsonPath js = new JsonPath(jsondata);
	String size = js.getString("Actors[0].name");
	System.out.println(size);
}}
