package com.TestCases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_003_FileUpload extends TestBase {

	@BeforeClass
	public void uploadFile() {
		logger.info("***** Started TC003_File_Upload *****");
		RestAssured.baseURI = "http://localhost:8080";

		 File testUploadFile = new File("C:\\temp\\testfile.png"); //Specify your own

		RequestSpecification httpRequest = RestAssured.given()
				.multiPart(testUploadFile);
		response = httpRequest.request(Method.POST, "/upload");

		// String resp = RestAssured.given().multiPart("file", new
		// File("src/test/resources/info.xlsx")).when().post("http://localhost:8080/upload").then().assertThat().statusCode(200).and().extract().body().asString();
	}

	@Test
	public void uploadReponseBody() {
		String responseBody = response.getBody().asString();
		logger.info("Response Body ==> " + responseBody);
		// JsonPath uploadResponse = JsonPath.from(responseBody);
		// String fullName = uploadResponse.get("fullName");
		assertTrue(responseBody.contains("successfully uploaded"));
	}

	@Test
	void uploadStatuscode() {
		logger.info("***** Upload Status Code *****");
		int statusCode = response.getStatusCode();
		logger.info("Status Code ==> " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	// Status line verification
	void uploadStatusLine() {
		logger.info("***** Upload Status Line *****");
		String statusLine = response.getStatusLine();
		logger.info("Status Line ==> " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}
	
//	public class UploadMultiPartFile {
//		 
//	    @Test
//	    public void uploadFile() {
//	        File testUploadFile = new File("C:\\temp\\testfile.png"); //Specify your own location and file
//	 
//	        RestAssured.baseURI = "http://localhost:8080";
//	 
//	        RequestSpecification httpRequest = (RequestSpecification) RestAssured.given()
//	            .multiPart(testUploadFile)
//	            .when().
//	        post("/uploadFile");
//	 
//	 
//	        System.out.println(response.getStatusCode());
//	        System.out.println(response.asString());
//	 
//	        assertTrue(response.asString().contains("successfully uploaded"));
//	    }
//	}

	@AfterClass
	void tearDown() {
		logger.info("***** Finished TC003_FileUpload *****");
	}
}
