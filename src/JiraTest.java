import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.File;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
public class JiraTest {
	@Test
	
	public void jira()
	{
		RestAssured.baseURI = "http://localhost:8085";
		SessionFilter session = new SessionFilter();
		String response = given().log().all().header("Content-Type","application/json").body("{ \"username\": \"vinaykumarggl\", \"password\": \"Vinay@9898\" }")
		.filter(session)
		.when().post("/rest/auth/1/session").
		then().extract().response().asString();
		//add comment
		String addcommentresponse = given().log().all().pathParam("id","10003").body("{\r\n"
	
				
				+ "    \"body\": \"I added the comments\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
		
				+ "}").header("Content-Type","application/json").filter(session)
		.when().post("/rest/api/2/issue/{id}/comment")
		.then().log().all().assertThat().statusCode(201).extract().response().asString();	
		
		
		JsonPath js1 = new JsonPath(addcommentresponse);
		String commentid = js1.getString("id");
		//add attachment
		given().header("X-Atlassian-Token","no-check").filter(session).pathParam("id", "10003").header("Content-Type","multipart/form-data").multiPart("file",new File("File.txt"))
		.when().post("/rest/api/2/issue/{id}/attachments")
		.then().log().all().assertThat().statusCode(200);
		//get issue
		String issuedetails = given().filter(session).pathParam("id", "10003").queryParam("fields", "comment").log().all().
		when().get("/rest/api/2/issue/{id}")
		
		.then().log().all().extract().response().asString();
		System.out.println(issuedetails);
		JsonPath js = new JsonPath(issuedetails);
		int commentscount = js.getInt("fields.comment.comments.size()");
		for(int i = 0;i<commentscount;i++)
		{
			String commentidissue = js.get("fields.comment.comments["+i+"].id").toString();
			if(commentidissue.equals(commentid))
			{
				String message = js.get("fields.comment.comments[\"+i+\"].body").toString();
		
				System.out.println(message);
				Assert.assertEquals(message,"I added the comments");
			}
		}
	}
	
	public void validatejson()
	{
		String resp = "courses:Automation";
		
		
		
	}

}