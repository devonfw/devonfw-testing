package com.capgemini.ntc.example.rest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.webapi.core.base.driver.DriverManager;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestExampleTest extends BaseTest {
	
	private static String endpointBaseUri;
	
	@BeforeClass
	public static void beforeClass() {
		String baseURI = "http://localhost";
		int port = DriverManager.getDriver()
						.port();
		endpointBaseUri = baseURI + ":" + port;
		RestAssured.config = new RestAssuredConfig().encoderConfig(new EncoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
	}
	
	@Override
	public void setUp() {
		/*
		 * ----------
		 * Mock response. Map request with virtual asset from file
		 * -----------
		 */
		BFLogger.logInfo("#1 Create Stub content message");
		BFLogger.logInfo("#2 Add resource to wiremock server");
		DriverManager.getDriver()
						.givenThat(get(urlEqualTo("/some/thing"))
										.willReturn(aResponse()
														.withHeader("Content-Type", ContentType.JSON.toString())
														.withBody("Hello world!")));
	}
	
	@Override
	public void tearDown() {
	}
	
	@Test
	public void testRestMock_UrlExists() {
		
		BFLogger.logInfo("#3 Send request to generated stub");
		Response response = given()
						.with()
						.log()
						.all()
						.when()
						.get(endpointBaseUri + "/some/thing")
						.thenReturn();
		
		BFLogger.logInfo("#4 Validate response ");
		BFLogger.logDebug("/some/thing: " + response.asString());
		assertThat(response.statusCode(), is(200));
	}
	
	@Test
	public void testRestMock_UrlDoesNotExist() throws Exception {
		
		BFLogger.logInfo("#3 Send request to generated stub");
		Response response = given()
						.with()
						.log()
						.all()
						.when()
						.get(endpointBaseUri + "/some/thing/else")
						.thenReturn();
		
		BFLogger.logInfo("#4 Validate response ");
		BFLogger.logDebug("/some/thing/else: " + response.asString());
		assertThat(response.statusCode(), is(404));
		
	}
	
}
