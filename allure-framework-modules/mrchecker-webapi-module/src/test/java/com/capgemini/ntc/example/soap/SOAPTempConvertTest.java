package com.capgemini.ntc.example.soap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.ntc.example.soap.tempconvert.FarenheitToCelsiusMethod;
import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.webapi.core.base.driver.DriverManager;
import com.capgemini.ntc.webapi.core.stubs.StubSOAP;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;

public class SOAPTempConvertTest extends BaseTest {
	
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
		// TASK Auto-generated method stub
		
	}
	
	@Override
	public void tearDown() {
		// TASK Auto-generated method stub
		
	}
	
	@Test
	public void testSoapMessageFromFileWithVitualResponse() throws IOException {
		
		BFLogger.logInfo("#1 Start wiremock server");
		DriverManager.getDriver();
		
		BFLogger.logInfo("#2 Create Stub content message");
		FarenheitToCelsiusMethod farenheitToCelsiusMethod = new FarenheitToCelsiusMethod();
		String endpointURI = "/tempconvert.asmx?op=FahrenheitToCelsius";
		String requestXPathQuery = "//soap12:Envelope | //soap12:Body | //FahrenheitToCelsius | //Fahrenheit";
		
		/*
		 * ----------
		 * Mock response. Map request with virtual asset from file
		 * -----------
		 */
		BFLogger.logInfo("#3 Add resource to wiremock server");
		new StubSOAP.StubBuilder(endpointURI)
						.setRequestXPathQuery(requestXPathQuery)
						.setResponse(farenheitToCelsiusMethod.fromFile_response())
						.setStatusCode(200)
						.build();
		
		/*
		 * ----------
		 * Time to validate virtual response
		 * -----------
		 */
		BFLogger.logInfo("#4 Send request to generated stub");
		Response response = given()
						.with()
						.contentType("application/soap+xml")
						.body(farenheitToCelsiusMethod.fromFile_request())
						.log()
						.all()
						.when()
						.post(endpointBaseUri + endpointURI)
						.thenReturn();
		
		BFLogger.logInfo("#5 Validate reposponse ");
		BFLogger.logDebug("NEW RESPONSE /tempconvert.asmx?op=FahrenheitToCelsius: " + response.asString());
		assertThat(response.statusCode(), is(200));
	}
	
	@Test
	public void testSoapMessageFromClassObjectWithVitualResponse() throws Exception {
		BFLogger.logInfo("#1 Start wiremock server");
		DriverManager.getDriver();
		
		BFLogger.logInfo("#2 Create Stub content message");
		FarenheitToCelsiusMethod farenheitToCelsiusMethod = new FarenheitToCelsiusMethod();
		String endpointURI = "/tempconvert.asmx?op=FahrenheitToCelsius";
		String requestXPathQuery = "//soap12:Envelope | //soap12:Body | //FahrenheitToCelsius | //Fahrenheit";
		
		/*
		 * ----------
		 * Mock response. Map request with virtual asset from Object SOAP representation
		 * -----------
		 */
		BFLogger.logInfo("#3 Add resource to wiremock server");
		new StubSOAP.StubBuilder(endpointURI)
						.setRequestXPathQuery(requestXPathQuery)
						.setResponse(farenheitToCelsiusMethod.setFahrenheitToCelsiusResult(37.8888)
										.fromCode_response())
						.setStatusCode(200)
						.build();
		
		/*
		 * ----------
		 * Time to validate virtual response
		 * -----------
		 */
		BFLogger.logInfo("#4 Send request to generated stub");
		Response response = given()
						.with()
						.contentType("application/soap+xml")
						.body(farenheitToCelsiusMethod.fromFile_request())
						.log()
						.all()
						.when()
						.post(endpointBaseUri + endpointURI)
						.thenReturn();
		
		BFLogger.logInfo("#5 Validate reposponse ");
		BFLogger.logDebug("NEW RESPONSE /tempconvert.asmx?op=FahrenheitToCelsius: " + response.asString());
		assertThat(response.statusCode(), is(200));
	}
	
}
