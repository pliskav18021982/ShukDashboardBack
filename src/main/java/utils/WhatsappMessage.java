package utils;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import com.mysql.cj.conf.ConnectionUrlParser.Pair;
import com.twilio.Twilio;
import com.twilio.Twilio; 
import com.twilio.converter.Promoter; 
import com.twilio.rest.api.v2010.account.Message; 
import com.twilio.type.PhoneNumber;

import dashboard.dao.Language;
import dashboard.service.TranslateReposutorySql;
import dashboard.service.UserRepositorySql;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal; 
public class WhatsappMessage {
	
	 public static final String ACCOUNT_SID = "AC76228e82c81cba6cf92bbf1b0d2d0e12"; 
	    public static final String AUTH_TOKEN = "0eaa8b411028e49511c6e581ffee4634"; 
	    public static WhatsappMessage instance = null;
	    
	    public static WhatsappMessage getInstance() {
	    	if(instance==null) {
	    		instance = new WhatsappMessage();
	    	}
	    	return instance;
	    }
	    
	    private WhatsappMessage() {
	    	 Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
	    } 
	    
	    public  void sendWhatsappMessage(String customerPhone, String messageText) { 
		  Message message = Message.creator( new
			  com.twilio.type.PhoneNumber("whatsapp:"+customerPhone), new
			  com.twilio.type.PhoneNumber("whatsapp:+972583327494"), messageText) .create();
			  
			  
			  System.out.println("from: "+message.getFrom()+" to:"+message.getTo()+": "
			  +message);
			 
	    } 
}
