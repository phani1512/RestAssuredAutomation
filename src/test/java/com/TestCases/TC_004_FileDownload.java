package com.TestCases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;

public class TC_004_FileDownload extends TestBase {
	@BeforeClass
	public void downLoadFile() {
		logger.info("***** Started TC004_File_Download *****");
		RestAssured.baseURI = "http://localhost:8080";
		RequestSpecification httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/download");
	}

	@Test
	public void downLoadContentType() {
		logger.info("***** Download Content Type *****");
		String contentType = response.header("Content-Type");
		logger.info("Content Type is ==> " + contentType);
//		String contentType = RestAssured.given().when().get("http://localhost:8080/download").then().assertThat()
//				.statusCode(200).and().extract().contentType();

		assertEquals(contentType, "text/json");
	}
	
	@Test
	public void downLoadResponseBody() {
		logger.info("***** FileDownload Response Body *****");
		String responseBody = response.getBody().asString();
		logger.info("Response Body ==> " + responseBody);
		assertTrue(responseBody.contains("Download successfully"));
	}
	@Test
	void DownloadStatuscode() {
		
		logger.info("***** FileDownload Status Code *****");
		int statusCode = response.getStatusCode();
		logger.info("Status Code ==> " + statusCode);
		Assert.assertEquals(statusCode, 200);

}
	@AfterClass
	void tearDown() {
		logger.info("***** Finished TC004_FileDownload *****");
	}
}
