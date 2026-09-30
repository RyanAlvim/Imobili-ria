package API;

import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import Login.Login;

public class Discord {
	
	public static String token = "";

	
	public Discord() throws UnirestException {
		Unirest.setTimeouts(0, 0);
		HttpResponse<String> response = Unirest.post("https://discord.com/api/v9/channels/1027272272072937495/messages")
		  .header("Host", "discord.com")
		  .header("Authorization", token)
		  .header("Content-Type", "application/json")
		  .header("Cookie", "__dcfduid=77bbc6b2247c11ed9c967e51b439b654; __sdcfduid=77bbc6b2247c11ed9c967e51b439b654432071bec0bc0278d0699f37770278a31cc8f49fd9932a0d335bc44d7dc5582c")
		  .body(String.format("{\n"
		  		+ "\"content\":"
		  		+ " \"https://lopeselite.com/captcha/%s\",\n"
		  		+ "        \"nonce\":"
		  		+ " \"%s\",\n "
		  		+ "       \"tts\":"
		  		+ " false\n}",Login.arquivo,new Random().nextInt(99999999)))
		  .asString();
		System.out.println("1-" + response.getBody());

	}
	
	public static String Run() throws UnirestException, InterruptedException {
		Unirest.setTimeouts(0, 0);
		HttpResponse<String> response = Unirest.post("https://discord.com/api/v9/channels/1027272272072937495/messages")
		  .header("Host", "discord.com")
		  .header("Authorization", token)
		  .header("Content-Type", "application/json")
		  .header("Cookie", "__dcfduid=77bbc6b2247c11ed9c967e51b439b654; __sdcfduid=77bbc6b2247c11ed9c967e51b439b654432071bec0bc0278d0699f37770278a31cc8f49fd9932a0d335bc44d7dc5582c")
		  .body(String.format("{\n"
		  		+ "\"content\":"
		  		+ " \"https://lopeselite.com/captcha/%s\",\n"
		  		+ "        \"nonce\":"
		  		+ " \"%s\",\n "
		  		+ "       \"tts\":"
		  		+ " false\n}",Login.arquivo, new Random().nextInt(99999999)))
		  .asString();
		
		System.out.println("2-" + response.getBody());
		return Get();
			
		
	}
	
	public static String Get() throws UnirestException, InterruptedException {
		Unirest.setTimeouts(0, 0);
		HttpResponse<String> response = Unirest.get("https://discord.com/api/v9/channels/1027272272072937495/messages?limit=1")
		  .header("Host", "discord.com")
		  .header("Authorization", token)
		  .header("Content-Type", "application/json")
		  .header("Cookie", "__dcfduid=77bbc6b2247c11ed9c967e51b439b654; __sdcfduid=77bbc6b2247c11ed9c967e51b439b654432071bec0bc0278d0699f37770278a31cc8f49fd9932a0d335bc44d7dc5582c")
		  
		  .asString();
		
		
		JSONArray jsonArray = new JSONArray(response.getBody());
		JSONObject embeds = new JSONObject(String.valueOf(jsonArray.get(0)));
		JSONObject array = new JSONObject(String.valueOf(embeds));
		JSONArray embeds_response = new JSONArray(String.valueOf(array.get("embeds")));
		JSONObject thumbnail = new JSONObject(String.valueOf(embeds_response.get(0)));

		JSONObject thumbnail_response = new JSONObject(String.valueOf(thumbnail.get("thumbnail")));
		String proxy_url = thumbnail_response.getString("proxy_url");
		System.out.println(proxy_url);
		System.out.println(response.getBody());

		System.out.println("3-" + response.getBody());
		return sendImage(proxy_url);
		
       
	}
	
	public static String sendImage(String link) throws UnirestException, InterruptedException {
		
		Unirest.setTimeouts(0, 0);
		HttpResponse<String> response = Unirest.post("https://discord.com/api/v9/channels/1027272272072937495/messages")
		  .header("Host", "discord.com")
		  .header("Authorization", token)
		  .header("Content-Type", "application/json")
		  .header("Cookie", "__dcfduid=77bbc6b2247c11ed9c967e51b439b654; __sdcfduid=77bbc6b2247c11ed9c967e51b439b654432071bec0bc0278d0699f37770278a31cc8f49fd9932a0d335bc44d7dc5582c")
		  .body(String.format("{\n"
		  		+ "\"content\":"
		  		+ " \"+ocr %s\",\n"
		  		+ "        \"nonce\":"
		  		+ " \"%s\",\n "
		  		+ "       \"tts\":"
		  		+ " false\n}",link,new Random().nextInt(99999999)))
		  .asString();
		System.out.println("4- " +response.getBody());

		return Response();
		
		
	}
	
	
	public static String Response() throws UnirestException, InterruptedException {
		Thread.sleep(1500);
		Unirest.setTimeouts(0, 0);
		HttpResponse<String> response = Unirest.get("https://discord.com/api/v9/channels/1027272272072937495/messages?limit=1")
		  .header("Host", "discord.com")
		  .header("Authorization", token)
		  .header("Content-Type", "application/json")
		  .header("Cookie", "__dcfduid=77bbc6b2247c11ed9c967e51b439b654; __sdcfduid=77bbc6b2247c11ed9c967e51b439b654432071bec0bc0278d0699f37770278a31cc8f49fd9932a0d335bc44d7dc5582c")
		  
		  .asString();
		
		
		JSONArray jsonArray = new JSONArray(response.getBody());
		JSONObject embeds = new JSONObject(String.valueOf(jsonArray.get(0)));
		JSONObject embeds_response = new JSONObject(String.valueOf(embeds));
		JSONArray array = new JSONArray(String.valueOf(embeds_response.get("embeds")));
		JSONObject response_discord = new JSONObject(String.valueOf(array.get(0)));

		//System.out.println(response_discord.get("description"));
		
		String replace = String.valueOf(response_discord.get("description")).replaceAll("[^a-zA-Z0-9]", "");
		System.out.println(replace);
		System.out.println("5-" + response.getBody());

		return replace;
	}

		
}
