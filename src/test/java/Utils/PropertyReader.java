package Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

	private Properties p;

	public PropertyReader() {

		BufferedReader reader;
		try {
			FileReader file = new FileReader("./src/test/resources/config.properties");
			p = new Properties();
			p.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getBaseURI() {
		String baseURI = p.getProperty("baseURI");
		return baseURI;
	}

	public String getUserId() {
		String userID = p.getProperty("userID");
		return userID;
	}

//	public static void main(String[] args) {
//
//		PropertyReader r = new PropertyReader();
//		System.out.println(r.getUserId());
//	}

}
