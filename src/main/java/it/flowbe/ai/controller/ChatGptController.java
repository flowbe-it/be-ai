package it.flowbe.ai.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.flowbe.ai.service.ChatGptService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/ai/chatgpt")
@Schema(name = "ChatGptController", description = "Controller for manage chatgpt")
@RequiredArgsConstructor
public class ChatGptController {
    
    private final ChatGptService chatGptService;
    
    @Operation(summary = "Start session chatgpt", security = { @SecurityRequirement(name = "jwtScheme") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), description = "success"),
            @ApiResponse(responseCode = "503", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), description = "service not available") })
    @PostMapping(value = "/start_session/")
    public ResponseEntity<String> startSession() throws ClassNotFoundException, NoSuchFieldException, SecurityException, 
        IllegalArgumentException, IllegalAccessException, InstantiationException, JsonProcessingException, InvocationTargetException, NoSuchMethodException {
        return ResponseEntity.ok(chatGptService.startSession());
    }
    
    @Operation(summary = "Send message to existing session chatgpt", security = { @SecurityRequirement(name = "jwtScheme") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), description = "success"),
            @ApiResponse(responseCode = "503", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), description = "service not available") })
    @PostMapping(value = "/chat/{sessionId}/")
    public ResponseEntity<String> chat(@PathVariable String sessionId, @RequestBody String message) throws ClassNotFoundException, NoSuchFieldException, SecurityException, 
        IllegalArgumentException, IllegalAccessException, InstantiationException, JsonProcessingException, InvocationTargetException, NoSuchMethodException {
        return ResponseEntity.ok(chatGptService.chat(sessionId, message));
    }
    
    @Operation(summary = "End existing session chatgpt", security = { @SecurityRequirement(name = "jwtScheme") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), description = "success"),
            @ApiResponse(responseCode = "503", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), description = "service not available") })
    @PostMapping(value = "/end_session/{sessionId}/")
    public ResponseEntity<String> endSession(@PathVariable String sessionId) throws ClassNotFoundException, NoSuchFieldException, SecurityException, 
        IllegalArgumentException, IllegalAccessException, InstantiationException, JsonProcessingException, InvocationTargetException, NoSuchMethodException {
        return ResponseEntity.ok(chatGptService.endSession(sessionId));
    }
    
    @Operation(summary = "Get documents", security = { @SecurityRequirement(name = "jwtScheme") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), description = "success"),
            @ApiResponse(responseCode = "503", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), description = "service not available") })
    @GetMapping(value = "/documents/")
    public ResponseEntity<String> getDocuments() throws ClassNotFoundException, NoSuchFieldException, SecurityException, 
        IllegalArgumentException, IllegalAccessException, InstantiationException, JsonProcessingException, InvocationTargetException, NoSuchMethodException {
        return ResponseEntity.ok(chatGptService.getDocuments());
    }
    
    @Operation(summary = "Get documents stats", security = { @SecurityRequirement(name = "jwtScheme") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), description = "success"),
            @ApiResponse(responseCode = "503", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), description = "service not available") })
    @GetMapping(value = "/document-stats/")
    public ResponseEntity<String> getDocumentStats() throws ClassNotFoundException, NoSuchFieldException, SecurityException, 
        IllegalArgumentException, IllegalAccessException, InstantiationException, JsonProcessingException, InvocationTargetException, NoSuchMethodException {
        return ResponseEntity.ok(chatGptService.getDocumentStats());
    }
    
    @Operation(summary = "Get analytics", security = { @SecurityRequirement(name = "jwtScheme") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), description = "success"),
            @ApiResponse(responseCode = "503", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), description = "service not available") })
    @GetMapping(value = "/analytics/")
    public ResponseEntity<String> getAnalytics() throws ClassNotFoundException, NoSuchFieldException, SecurityException, 
        IllegalArgumentException, IllegalAccessException, InstantiationException, JsonProcessingException, InvocationTargetException, NoSuchMethodException {
        return ResponseEntity.ok(chatGptService.getAnalytics());
    }
    
    @Operation(summary = "Get sessions", security = { @SecurityRequirement(name = "jwtScheme") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), description = "success"),
            @ApiResponse(responseCode = "503", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), description = "service not available") })
    @GetMapping(value = "/sessions/")
    public ResponseEntity<String> getSessions() throws ClassNotFoundException, NoSuchFieldException, SecurityException, 
        IllegalArgumentException, IllegalAccessException, InstantiationException, JsonProcessingException, InvocationTargetException, NoSuchMethodException {
        return ResponseEntity.ok(chatGptService.getSessions());
    }
    
    @Operation(summary = "Refresh vector store", security = { @SecurityRequirement(name = "jwtScheme") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), description = "success"),
            @ApiResponse(responseCode = "503", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), description = "service not available") })
    @PostMapping(value = "/refresh_vectorstore/")
    public ResponseEntity<String> refreshVectorStore() throws ClassNotFoundException, NoSuchFieldException, SecurityException, 
        IllegalArgumentException, IllegalAccessException, InstantiationException, JsonProcessingException, InvocationTargetException, NoSuchMethodException {
        return ResponseEntity.ok(chatGptService.refreshVectorStore());
    }
    
    @Operation(summary = "Delete document", security = { @SecurityRequirement(name = "jwtScheme") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), description = "success"),
            @ApiResponse(responseCode = "503", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE), description = "service not available") })
    @DeleteMapping(value = "/documents/{filename}/")
    public ResponseEntity<String> deleteDocument(@PathVariable String filename) throws ClassNotFoundException, NoSuchFieldException, SecurityException, 
        IllegalArgumentException, IllegalAccessException, InstantiationException, JsonProcessingException, InvocationTargetException, NoSuchMethodException {
        return ResponseEntity.ok(chatGptService.deleteDocument(filename));
    }
}
