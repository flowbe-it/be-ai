package it.flowbe.ai.service;

import java.lang.reflect.InvocationTargetException;


import com.fasterxml.jackson.core.JsonProcessingException;


public interface ChatGptService {

    String startSession() throws NumberFormatException, JsonProcessingException, ClassNotFoundException, InstantiationException, 
        IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException;

	String chat(String sessionId, String message) throws NumberFormatException, JsonProcessingException, ClassNotFoundException, InstantiationException, 
    IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException;
    
	String endSession(String sessionId) throws NumberFormatException, JsonProcessingException, ClassNotFoundException, InstantiationException, 
    IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException;
	
	String getDocuments() throws NumberFormatException, JsonProcessingException, ClassNotFoundException, InstantiationException, 
    IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException;

	String getDocumentStats() throws NumberFormatException, JsonProcessingException, ClassNotFoundException, InstantiationException, 
    IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException;

	String getAnalytics() throws NumberFormatException, JsonProcessingException, ClassNotFoundException, InstantiationException, 
    IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException;

	String getSessions() throws NumberFormatException, JsonProcessingException, ClassNotFoundException, InstantiationException, 
    IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException;

	String refreshVectorStore() throws NumberFormatException, JsonProcessingException, ClassNotFoundException, InstantiationException, 
    IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException;

	String deleteDocument(String filename) throws NumberFormatException, JsonProcessingException, ClassNotFoundException, InstantiationException, 
    IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException;

}
