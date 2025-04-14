package it.flowbe.ai.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;

import it.flowbe.ai.service.ChatGptService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatGptServiceImpl implements ChatGptService {
    
	@Autowired
	private RestTemplate aiRestTemplate;
	@Value("${ai.baseurl}")
    private String sBaseUrlAPI;
	
	@Override
    public String startSession() throws NumberFormatException, JsonProcessingException, ClassNotFoundException, InstantiationException, 
        IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException {
        
    	HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		/*headers.set("client_id", sURLHeaderClientId);
		headers.set("client_secret", sURLHeaderSecret);*/
		JSONObject indexObject = new JSONObject( );
		indexObject.put( "index", new JSONArray());
		HttpEntity httpEntity = new HttpEntity<>(indexObject,headers);
		/*String newUrl = UriComponentsBuilder.fromUriString(sURLAPI+sURLParamClientType+"/"+sURLParamClientId).queryParam("status", sURLParamStatus)
				.queryParam("nbDays", sURLParamNBDays).queryParam("activity", sURLParamActivity)
				.buildAndExpand(sURLParamClientType, sURLParamClientId).toUriString();*/
    	String newUrl = sBaseUrlAPI+"/start_session";
		System.out.println("URL API: "+newUrl);
		//String sOrderIssueResponse = otRestTemplate.postForObject(newUrl, httpEntity, String.class);
		String sIdSessione = aiRestTemplate.exchange(newUrl, HttpMethod.POST, httpEntity, String.class).getBody();
		//String sIdSessione = aiRestTemplate.postForObject(newUrl, httpEntity, String.class);
		JSONObject jsonResponse = new JSONObject(sIdSessione);
		
        return jsonResponse.toString();
    }
	@Override
	public String chat(String sessionId, String message) throws NumberFormatException, JsonProcessingException,
			ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		/*headers.set("client_id", sURLHeaderClientId);
		headers.set("client_secret", sURLHeaderSecret);*/
		/*JSONObject indexObject = new JSONObject( );
		indexObject.put("session_id", sessionId);
		indexObject.put("message", message);*/
		Map<String, Object> indexObject = new HashMap<>();
		indexObject.put("session_id", sessionId);
		indexObject.put("message", message);
		HttpEntity httpEntity = new HttpEntity<>(indexObject,headers);
		/*String newUrl = UriComponentsBuilder.fromUriString(sURLAPI+sURLParamClientType+"/"+sURLParamClientId).queryParam("status", sURLParamStatus)
				.queryParam("nbDays", sURLParamNBDays).queryParam("activity", sURLParamActivity)
				.buildAndExpand(sURLParamClientType, sURLParamClientId).toUriString();*/
    	String newUrl = sBaseUrlAPI+"/chat";
		System.out.println("URL API: "+newUrl);
		//String sOrderIssueResponse = otRestTemplate.postForObject(newUrl, httpEntity, String.class);
		String sResponse = aiRestTemplate.exchange(newUrl, HttpMethod.POST, httpEntity, String.class).getBody();
		//String sIdSessione = aiRestTemplate.postForObject(newUrl, httpEntity, String.class);
		JSONObject jsonResponse = new JSONObject(sResponse);
		
        return jsonResponse.toString();
	}
	
	@Override
	public String endSession(String sessionId) throws NumberFormatException, JsonProcessingException,
			ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		/*headers.set("client_id", sURLHeaderClientId);
		headers.set("client_secret", sURLHeaderSecret);*/
		/*JSONObject indexObject = new JSONObject( );
		indexObject.put("session_id", sessionId);
		indexObject.put("message", message);*/
		Map<String, Object> indexObject = new HashMap<>();
		indexObject.put("session_id", sessionId);
		HttpEntity httpEntity = new HttpEntity<>(indexObject,headers);
		/*String newUrl = UriComponentsBuilder.fromUriString(sURLAPI+sURLParamClientType+"/"+sURLParamClientId).queryParam("status", sURLParamStatus)
				.queryParam("nbDays", sURLParamNBDays).queryParam("activity", sURLParamActivity)
				.buildAndExpand(sURLParamClientType, sURLParamClientId).toUriString();*/
    	String newUrl = sBaseUrlAPI+"/end_session";
		System.out.println("URL API: "+newUrl);
		//String sOrderIssueResponse = otRestTemplate.postForObject(newUrl, httpEntity, String.class);
		String sResponse = aiRestTemplate.exchange(newUrl, HttpMethod.POST, httpEntity, String.class).getBody();
		//String sIdSessione = aiRestTemplate.postForObject(newUrl, httpEntity, String.class);
		JSONObject jsonResponse = new JSONObject(sResponse);
		
        return jsonResponse.toString();
	}
 
	@Override
    public String getDocuments() throws NumberFormatException, JsonProcessingException, ClassNotFoundException, InstantiationException, 
        IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException {
        
    	HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		/*headers.set("client_id", sURLHeaderClientId);
		headers.set("client_secret", sURLHeaderSecret);*/
		HttpEntity httpEntity = new HttpEntity<>(headers);
		/*String newUrl = UriComponentsBuilder.fromUriString(sURLAPI+sURLParamClientType+"/"+sURLParamClientId).queryParam("status", sURLParamStatus)
				.queryParam("nbDays", sURLParamNBDays).queryParam("activity", sURLParamActivity)
				.buildAndExpand(sURLParamClientType, sURLParamClientId).toUriString();*/
    	String newUrl = sBaseUrlAPI+"/documents";
		System.out.println("URL API: "+newUrl);
		//String sOrderIssueResponse = otRestTemplate.postForObject(newUrl, httpEntity, String.class);
		String sDocuments = aiRestTemplate.exchange(newUrl, HttpMethod.GET, httpEntity, String.class).getBody();
		//String sIdSessione = aiRestTemplate.postForObject(newUrl, httpEntity, String.class);
		JSONObject jsonResponse = new JSONObject(sDocuments);
		
        return jsonResponse.toString();
    }
	
	@Override
    public String getDocumentStats() throws NumberFormatException, JsonProcessingException, ClassNotFoundException, InstantiationException, 
        IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException {
        
    	HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		/*headers.set("client_id", sURLHeaderClientId);
		headers.set("client_secret", sURLHeaderSecret);*/
		HttpEntity httpEntity = new HttpEntity<>(headers);
		/*String newUrl = UriComponentsBuilder.fromUriString(sURLAPI+sURLParamClientType+"/"+sURLParamClientId).queryParam("status", sURLParamStatus)
				.queryParam("nbDays", sURLParamNBDays).queryParam("activity", sURLParamActivity)
				.buildAndExpand(sURLParamClientType, sURLParamClientId).toUriString();*/
    	String newUrl = sBaseUrlAPI+"/document-stats";
		System.out.println("URL API: "+newUrl);
		//String sOrderIssueResponse = otRestTemplate.postForObject(newUrl, httpEntity, String.class);
		String sDocuments = aiRestTemplate.exchange(newUrl, HttpMethod.GET, httpEntity, String.class).getBody();
		//String sIdSessione = aiRestTemplate.postForObject(newUrl, httpEntity, String.class);
		JSONObject jsonResponse = new JSONObject(sDocuments);
		
        return jsonResponse.toString();
    }
	
	@Override
    public String getAnalytics() throws NumberFormatException, JsonProcessingException, ClassNotFoundException, InstantiationException, 
        IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException {
        
    	HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		/*headers.set("client_id", sURLHeaderClientId);
		headers.set("client_secret", sURLHeaderSecret);*/
		HttpEntity httpEntity = new HttpEntity<>(headers);
		/*String newUrl = UriComponentsBuilder.fromUriString(sURLAPI+sURLParamClientType+"/"+sURLParamClientId).queryParam("status", sURLParamStatus)
				.queryParam("nbDays", sURLParamNBDays).queryParam("activity", sURLParamActivity)
				.buildAndExpand(sURLParamClientType, sURLParamClientId).toUriString();*/
    	String newUrl = sBaseUrlAPI+"/analytics";
		System.out.println("URL API: "+newUrl);
		//String sOrderIssueResponse = otRestTemplate.postForObject(newUrl, httpEntity, String.class);
		String sDocuments = aiRestTemplate.exchange(newUrl, HttpMethod.GET, httpEntity, String.class).getBody();
		//String sIdSessione = aiRestTemplate.postForObject(newUrl, httpEntity, String.class);
		JSONObject jsonResponse = new JSONObject(sDocuments);
		
        return jsonResponse.toString();
    }
	
	@Override
    public String getSessions() throws NumberFormatException, JsonProcessingException, ClassNotFoundException, InstantiationException, 
        IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException {
        
    	HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		/*headers.set("client_id", sURLHeaderClientId);
		headers.set("client_secret", sURLHeaderSecret);*/
		HttpEntity httpEntity = new HttpEntity<>(headers);
		/*String newUrl = UriComponentsBuilder.fromUriString(sURLAPI+sURLParamClientType+"/"+sURLParamClientId).queryParam("status", sURLParamStatus)
				.queryParam("nbDays", sURLParamNBDays).queryParam("activity", sURLParamActivity)
				.buildAndExpand(sURLParamClientType, sURLParamClientId).toUriString();*/
    	String newUrl = sBaseUrlAPI+"/sessions";
		System.out.println("URL API: "+newUrl);
		//String sOrderIssueResponse = otRestTemplate.postForObject(newUrl, httpEntity, String.class);
		String sDocuments = aiRestTemplate.exchange(newUrl, HttpMethod.GET, httpEntity, String.class).getBody();
		//String sIdSessione = aiRestTemplate.postForObject(newUrl, httpEntity, String.class);
		JSONObject jsonResponse = new JSONObject(sDocuments);
		
        return jsonResponse.toString();
    }
	
	@Override
    public String refreshVectorStore() throws NumberFormatException, JsonProcessingException, ClassNotFoundException, InstantiationException, 
        IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException {
        
    	HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		/*headers.set("client_id", sURLHeaderClientId);
		headers.set("client_secret", sURLHeaderSecret);*/
		JSONObject indexObject = new JSONObject( );
		indexObject.put( "index", new JSONArray());
		HttpEntity httpEntity = new HttpEntity<>(indexObject,headers);
		/*String newUrl = UriComponentsBuilder.fromUriString(sURLAPI+sURLParamClientType+"/"+sURLParamClientId).queryParam("status", sURLParamStatus)
				.queryParam("nbDays", sURLParamNBDays).queryParam("activity", sURLParamActivity)
				.buildAndExpand(sURLParamClientType, sURLParamClientId).toUriString();*/
    	String newUrl = sBaseUrlAPI+"/refresh_vectorstore";
		System.out.println("URL API: "+newUrl);
		//String sOrderIssueResponse = otRestTemplate.postForObject(newUrl, httpEntity, String.class);
		String sResponse = aiRestTemplate.exchange(newUrl, HttpMethod.POST, httpEntity, String.class).getBody();
		//String sIdSessione = aiRestTemplate.postForObject(newUrl, httpEntity, String.class);
		JSONObject jsonResponse = new JSONObject(sResponse);
		
        return jsonResponse.toString();
    }
	
	@Override
	public String deleteDocument(String filename) throws NumberFormatException, JsonProcessingException,
			ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		/*headers.set("client_id", sURLHeaderClientId);
		headers.set("client_secret", sURLHeaderSecret);*/
		/*JSONObject indexObject = new JSONObject( );
		indexObject.put("session_id", sessionId);
		indexObject.put("message", message);*/
		Map<String, Object> indexObject = new HashMap<>();
		indexObject.put("filename", filename);
		HttpEntity httpEntity = new HttpEntity<>(indexObject,headers);
		/*String newUrl = UriComponentsBuilder.fromUriString(sURLAPI+sURLParamClientType+"/"+sURLParamClientId).queryParam("status", sURLParamStatus)
				.queryParam("nbDays", sURLParamNBDays).queryParam("activity", sURLParamActivity)
				.buildAndExpand(sURLParamClientType, sURLParamClientId).toUriString();*/
    	String newUrl = sBaseUrlAPI+"/documents/"+filename;
		System.out.println("URL API: "+newUrl);
		//String sOrderIssueResponse = otRestTemplate.postForObject(newUrl, httpEntity, String.class);
		String sResponse = aiRestTemplate.exchange(newUrl, HttpMethod.DELETE, httpEntity, String.class).getBody();
		//String sIdSessione = aiRestTemplate.postForObject(newUrl, httpEntity, String.class);
		JSONObject jsonResponse = new JSONObject(sResponse);
		
        return jsonResponse.toString();
	}
}
