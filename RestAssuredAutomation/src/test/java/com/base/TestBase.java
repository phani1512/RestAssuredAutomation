package com.base;

import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	public static RequestSpecification httpRequest;
	public static Response response;
	public Logger logger;
	public Properties prop;

	@BeforeClass
	public void setup() {
		logger = Logger.getLogger("TransportalRestAPI");
		PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.DEBUG);

	}

	@BeforeClass
	public void loadProperties() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					"D:\\Git\\RestAssuredAutomation\\RestAssuredAutomation\\config.Properties\\config.properties");
			prop.load(fis);
		} catch (Exception ex) {
		}
	}
}
