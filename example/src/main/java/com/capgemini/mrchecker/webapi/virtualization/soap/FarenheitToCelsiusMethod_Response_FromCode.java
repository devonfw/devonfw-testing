package com.capgemini.mrchecker.webapi.virtualization.soap;

import com.capgemini.mrchecker.webapi.core.BasePageWebAPI;
import com.capgemini.mrchecker.webapi.soap.SoapMessageGenerator;
import com.jamesmurty.utils.XMLBuilder;
import org.xml.sax.SAXException;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class FarenheitToCelsiusMethod_Response_FromCode extends BasePageWebAPI {

	/*
	 * SOAP response built from Java code
	 */

	/*
	 * ----------------------------------
	 * Any handy actions after this point
	 * ----------------------------------
	 */
	private XMLBuilder xmlBody;

	public FarenheitToCelsiusMethod_Response_FromCode() {
		setRoot("FahrenheitToCelsiusResponse");
	}

	/**
	 * @return Generate SOAP request in String format
	 */
	public String getMessage() {
		String message = "";
		try {
			SOAPMessage soapMessage = SoapMessageGenerator.createSOAPmessage(this.getRoot()
					.asString());
			message = SoapMessageGenerator.printSoapMessage(soapMessage);
		} catch (SOAPException | SAXException | IOException | ParserConfigurationException | TransformerException e) {
			new Exception(e);
		}
		return message;
	}

	// Set any nodes type under xml Root

	/**
	 * @return Root XML structure
	 */
	public XMLBuilder getRoot() {
		return xmlBody;
	}

	private void setRoot(String nodeName) {
		try {
			this.xmlBody = XMLBuilder.create(nodeName);
		} catch (ParserConfigurationException | FactoryConfigurationError e) {
			new Exception(e);
		}
	}

	/**
	 * Set "FahrenheitToCelsiusResult" node under xml Root
	 *
	 * @param fahrenheit
	 * @return <FahrenheitToCelsius>
	 * <FahrenheitToCelsiusResult>37.777</FahrenheitToCelsiusResult>
	 * </FahrenheitToCelsius>
	 */
	public FarenheitToCelsiusMethod_Response_FromCode setFahrenheitToCelsiusResult(double value) {
		getRoot()
				.element("FahrenheitToCelsiusResult")
				.text(Double.toString(value));
		return this;
	}

	/**
	 * Set "Smth" node under xml Root
	 *
	 * @param fahrenheit
	 * @return <FahrenheitToCelsiusResponse>
	 * <Smth>Hello</Smth>
	 * </FahrenheitToCelsiusResponse>
	 */
	public FarenheitToCelsiusMethod_Response_FromCode setSmth(String Smth) {
		getRoot().element("Smth")
				.text(Smth);
		return this;
	}

	public String getEndpoint() {
		// Useful only for REST Tests
		return null;
	}
}