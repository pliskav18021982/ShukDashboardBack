package utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

public class HttpRequest {

	public Pair<Integer, String> sendRequest(String baseUrl, String method,Map<String, String> parameters) throws MalformedURLException{
		URL url;
		HttpURLConnection con=null;
		Pair<Integer, String> response = null;
		try {
			url = new URL(baseUrl);
			 con = (HttpURLConnection) url.openConnection();
		
			con.setRequestMethod(method);
			///con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			if(parameters!=null) {
				con.setDoOutput(true);
				DataOutputStream out = new DataOutputStream(con.getOutputStream());
				out.writeBytes(getParamsString(parameters));
				out.flush();
				out.close();
				int status = con.getResponseCode();
				BufferedReader in = new BufferedReader(
						  new InputStreamReader(con.getInputStream()));
						String inputLine;
						StringBuffer content = new StringBuffer();
						while ((inputLine = in.readLine()) != null) {
						    content.append(inputLine);
						}
						in.close();
						response = new Pair(status,content.toString());
			}
		} catch (IOException e) {
			
			e.printStackTrace();
			response = new Pair(0,e.getStackTrace().toString());
		}finally {
			if(con!=null) {
				con.disconnect();
			}
		}
		
		return response; 
		
	}
	
	    public static String getParamsString(Map<String, String> params) 
	      throws UnsupportedEncodingException{
	        StringBuilder result = new StringBuilder();

	        for (Map.Entry<String, String> entry : params.entrySet()) {
	          result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
	          result.append("=");
	          result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
	          result.append("&");
	        }

	        String resultString = result.toString();
	        return resultString.length() > 0
	          ? resultString.substring(0, resultString.length() - 1)
	          : resultString;
	    }
	
}

