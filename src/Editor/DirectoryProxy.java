package Editor;

import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Routes information from some source to a directory
 * @author Andrew Huelsman / Steven Messer
 *
 */
public class DirectoryProxy implements IDirectory{

	IDirectory _dir;
	
	// string to hold the result of reading in the response
	private String receiveMessage = "";
	
	private void send(String Json){
		try {
			System.out.println("in the client");
			
			// Client will connect to this location
			// TODO: Replace the localhost with an IP to test sending it to another computer on a network
			URL site = new URL("http://localhost:8000/sendresults");
			HttpURLConnection conn = (HttpURLConnection) site.openConnection();

			// now create a POST request
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());

			// build a string that contains JSON from console
			String content = "";
			content = Json;

			// write out string to output buffer for message
			out.writeBytes(content);
			out.flush();
			out.close();

			System.out.println("Done sent to server");

			InputStreamReader inputStr = new InputStreamReader(conn.getInputStream());
			
			// string to hold the result of reading in the response
			StringBuilder sb = new StringBuilder();

			// read the characters from the request byte by byte and build up
			// the Response
			int nextChar;
			while ((nextChar = inputStr.read()) > -1) {
				sb = sb.append((char) nextChar);
			}
			System.out.println("Return String: " + sb);
			receiveMessage = sb.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * To be called after an add command to verify
	 * server received/added to library
	 */
	public boolean serverReceived(){
		if(receiveMessage.equals("ROGER JSON RECEIVED")){
			receiveMessage = "";
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public void add(String json) {
		send(json);
	}

	@Override
	public void print(String json) {
		send(json);
	}

	@Override
	public void clear(String json) {
		send(json);
	}
	

}
