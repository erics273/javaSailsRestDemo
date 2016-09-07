package localhost.javaSailsRestDemo.employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 
 * Sample class that makes simple request to our sails API
 * 
 * @author Eric Schwartz
 * @since 2016-09-07
 *
 */
public class RestRequest {
	
	/**
     * The URL of the API endpoint we want to connect to
     */
	protected static String endpoint = "http://localhost:1337/employee";
	
	/**
     * The character set to use when encoding URL parameters
     */
	protected static String charset = "UTF-8";
	
	public static void main(String[] args) {
	
		try {
			
			
			//The firstName of the employee (string, required, at least 2 characters)
			String firstName = "eric";
			
			//The lastName of the employee (string, required, at least 2 characters)
			String lastName = "schwartz";
			
			//The email address of the employee (string, required, must looks like a real email address)
			String email = "erics273@gmail.com";
			
			//The home phone of the employee (string, must look like ###-###-####|###.###.####|##########)
			String homePhone = "828-514-2335";
			
			//The cell phone of the employee (string, must look like ###-###-####|###.###.####|##########)
			String cellPhone = "828-514-2334";
			
			//The password for the employee (string, required, at least 1 uppercase, 1 lowercase, 1 number, 8 characters)
			String password = "EricR0cks";
			
			//Is the employee active (Integer, required, must be 1|0)
			String active = "1";
			
			//creates the url parameters as a string encodeing them with the defined charset
			String queryString = String.format("firstName=%s&lastName=%s&email=%s&homePhone=%s&cellPhone=%s&password=%s&active=%s",
					URLEncoder.encode(firstName, charset),
					URLEncoder.encode(lastName, charset),
					URLEncoder.encode(email, charset),
					URLEncoder.encode(homePhone, charset),
					URLEncoder.encode(cellPhone, charset),
					URLEncoder.encode(password, charset),
					URLEncoder.encode(active, charset)
			);
			
			//creates a new URL out of the endpoint, returnType and  queryString
			URL googleDirections = new URL(endpoint + "?" + queryString);
			HttpURLConnection connection = (HttpURLConnection) googleDirections.openConnection();
			connection.setRequestMethod("POST");
			
			//if we did not get a 201 (success) throw an exception
			if (connection.getResponseCode() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}
			
			//read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

			//loop of buffer line by line until it return null meaning there are no more lines
			while (br.readLine() != null) {
				//print out each line to the screen
				System.out.println(br.readLine());
			}
			
			//close connection to API
			connection.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		
	}
	
	
	
	
	}