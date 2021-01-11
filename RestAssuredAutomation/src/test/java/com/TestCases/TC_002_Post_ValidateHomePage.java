package com.TestCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class TC_002_Post_ValidateHomePage extends TestBase{
	
	@Test
	void ValidateLoginPage() {
		// Base URI
		logger.info("***** Started TC002_Post_ValidateLoginPage *****");
		
		RestAssured.baseURI = "https://transportalapi-ent.azurewebsites.net";  
		RequestSpecification httpRequest = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("UserName","apple.admin");
		requestParams.put("Password","admin@123");
		
		httpRequest.header("Content-Type", "application/json");
		
		httpRequest.body(requestParams.toJSONString());
		response = httpRequest.request(Method.POST,"/api/Login/ValidateUser");

		logger.info("***** loginPage Response Body *****");
		String responseBody = response.getBody().asString();
		logger.info("Response Body ==> " + responseBody);
		JsonPath LoginResponse = JsonPath.from(responseBody);
	    String fullName = LoginResponse.get("fullName");
        Assert.assertEquals(fullName, "Apple Admin");

}
	@Test
	void ValidateLoginPageStatuscode() {
		logger.info("***** ValidateloginPage Status Code *****");
		int statusCode = response.getStatusCode();
		logger.info("Status Code ==> " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	// Status line verification
	void ValidateloginPageStatusLine() {
		logger.info("***** ValidateloginPage Status Line *****");
		String statusLine = response.getStatusLine();
		logger.info("Status Line ==> " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}
}
