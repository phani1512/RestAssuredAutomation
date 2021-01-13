package com.TestCases;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;

public class TC_001_GET_loginPage extends TestBase{

	@BeforeClass
	void URLPage() {
		// Base URI
		logger.info("***** Started TC001_Get_URLPage *****");
		RestAssured.baseURI = "https://transportal.azurewebsites.net/";
		RequestSpecification httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET);
	}

	@Test
	void loginPageResponseBody() {
		logger.info("***** loginPage Response Body *****");
		String responseBody = response.getBody().asString();
		logger.info("Response Body ==> " + responseBody);
		Assert.assertTrue(responseBody != null);
		
		Headers allheaders = response.headers(); // Capturing all the headers
		for(Header header:allheaders) {
			logger.info("Header Name & Value ==> " + header.getName()+ " "+ header.getValue());
		
		}
		
	}

	// Status code verification
	@Test
	void loginPageStatuscode() {
		logger.info("***** loginPage Status Code *****");
		int statusCode = response.getStatusCode();
		logger.info("Status Code ==> " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	// Status line verification
	void loginPageStatusLine() {
		logger.info("***** loginPage Status Line *****");
		String statusLine = response.getStatusLine();
		logger.info("Status Line ==> " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}

	// Response Time Verification
	@Test(enabled =false)
	void loginPageResponseTime() {
		logger.info("***** loginPage Response Time *****");
		long responseTime = response.getTime();
		logger.info("Response Time ==> " + responseTime);
		if (responseTime > 2000)
			logger.warn("Response Time is greater than 2000");
		Assert.assertTrue(responseTime < 2000);

	}

	@Test
	void loginPageContentType() {
		logger.info("***** loginPage Content Type *****");
		String contentType = response.header("Content-Type");
		logger.info("Content Type is ==> " + contentType);
		Assert.assertEquals(contentType, "text/html");

	}

	@Test
	void loginPageServerType() {
		logger.info("***** loginPage Server Type *****");
		String serverType = response.header("Server");
		logger.info("Server Type is ==> " + serverType);
		Assert.assertEquals(serverType, "Microsoft-IIS/10.0");

	}

	@Test
	void loginPageContentEncoding() {
		logger.info("***** loginPage Content Encoding *****");
		String ContentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding is ==> " + ContentEncoding);
		Assert.assertEquals(ContentEncoding, "gzip");
	}
	@Test
	void loginPageContentLength() {
		logger.info("***** loginPage Content Length *****");
		String ContentLength = response.header("Content-Length");
		logger.info("Content Length is ==> " + ContentLength);
		if(Integer.parseInt(ContentLength)<100)
			logger.warn("Content Length is less than 100");
		Assert.assertTrue(Integer.parseInt(ContentLength)>100);
	}
	
	@AfterClass
	void tearDown() 
	{
		logger.info("***** Finished TC001_Get_URLPage *****");
}
}
