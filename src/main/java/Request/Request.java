package Request;

import java.util.Random;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import Login.Login;

public class Request {
	
	public static String Request(String list) throws UnirestException {
		Unirest.setTimeouts(0, 0);
		HttpResponse<String> response = Unirest.post("http://191.101.234.4:8080/robo?list="+list)
		  
		  .body("")
		  .asString();
		System.out.println("1-" + response.getBody());
		System.out.println("http://191.101.234.4:8080/robo?list="+list);
		return response.getBody();
	}

}
