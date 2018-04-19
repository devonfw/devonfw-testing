package com.capgemini.ntc.webapi.endpoint.soap;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.capgemini.ntc.webapi.core.BasePageWebAPI;
import com.capgemini.ntc.webapi.endpoint.soap.FarenheitToCelsiusMethod_Response_FromFile;

public class FarenheitToCelsiusMethod_Response_FromFile extends BasePageWebAPI {
	
	/*
	 * SOAP response built from Java code
	 */
	private String path = "/src/test/resources/soapInput/farenheittocelsius/sampleresponse.xml";
	
	public FarenheitToCelsiusMethod_Response_FromFile() {
	}
	
	public FarenheitToCelsiusMethod_Response_FromFile setFilePath(String path) {
		this.path = path;
		return this;
	}
	
	@Override
	public String getMessage() {
		String message = "";
		try {
			message = getStringOutOfFile(this.path);
		} catch (IOException e) {
			new Exception(e);
		}
		return message;
	}
	
	/*
	 * ------------
	 * Util methods
	 * ------------
	 */
	
	private String getStringOutOfFile(final String filePath) throws IOException {
		String path = System.getProperty("user.dir") + Paths.get(filePath);
		if (!exists(path)) {
			throw new IOException("Could not find file. Path='" + path + "' does not exist");
		}
		
		String responseEnvelope = readFile(path, StandardCharsets.UTF_8);
		return responseEnvelope;
	}
	
	private boolean exists(String path) {
		File f = new File(path);
		return f.exists();
	}
	
	private String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
}
