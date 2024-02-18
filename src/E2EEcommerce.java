import org.testng.annotations.Test;
import files.CreateOrder1;
import files.Login;
import files.LoginResponse;
import files.OrderDetails;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
public class E2EEcommerce {
	@Test
	public void login()
	{		
		
		
		RequestSpecification request = new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri("https://rahulshettyacademy.com").build();
		LoginResponse resp = given().spec(request).body(Login.login()).
		when().post("api/ecom/auth/login").
		then().log().all().extract().response().as(LoginResponse.class);
		String userId = resp.getUserId();
	
		String token = resp.getToken();
		String message = resp.getMessage();
		System.out.println(userId+" "+token+" "+message);
		
		
		RequestSpecification request1 = new RequestSpecBuilder().setContentType(ContentType.MULTIPART).setBaseUri("https://rahulshettyacademy.com")
		
				.addHeader("Authorization", token).build();
		Response resp1 = given().spec(request1).param("productName", "qwerty").param("productAddedBy", userId).param("productCategory", "fashion")
		.param("productSubCategory", "shirts").param("productPrice", "11500").param("productDescription", "Adidas Originals")
		.param("productFor", "women")
		.multiPart("productImage", new File("C:\\Users\\vinay\\OneDrive\\Pictures\\Sweet-Whatsapp-DP-Pics-Download.jpg"))
		.when().post("api/ecom/product/add-product").
		then().log().all().assertThat().statusCode(201).extract().response();
		
		String respstring = resp1.asString();
		JsonPath js = new JsonPath(respstring);
		String productId = js.getString("productId");
		System.out.println(productId);
		//create order
		RequestSpecification order = new RequestSpecBuilder().setContentType("application/json").
										setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token).build();
		CreateOrder1 co = new CreateOrder1();
		OrderDetails od = new OrderDetails();
		od.setCountry("British Indian Ocean Territory");
		
		od.setProductOrderId(productId);
		List<OrderDetails> li = new ArrayList<OrderDetails>();
		li.add(od);
		co.setOrders(li);
		String respnse = given().spec(order).body(co)
		.when().post("api/ecom/order/create-order").then().log().all().extract().response().asString();
		//Delete Product
		RequestSpecification deleteorder1 = new RequestSpecBuilder().
				setBaseUri("https://rahulshettyacademy.com").addPathParam("productId", productId).addHeader("Authorization", token).build();
		given().spec(deleteorder1)
		
		
		.when().delete("api/ecom/product/delete-product/{productId}")
		
		.then().log().all().assertThat().statusCode(200);
		
		
	}
	
	
}