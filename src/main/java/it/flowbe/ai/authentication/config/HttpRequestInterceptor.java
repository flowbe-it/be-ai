package it.flowbe.ai.authentication.config;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import it.flowbe.tenetcommonlibrary.constants.OrderTrackingConstants;
import it.flowbe.tenetcommonlibrary.databasehandler.DbContextHolder;
import it.flowbe.tenetcommonlibrary.dto.ErrorDto;
import it.flowbe.tenetcommonlibrary.exception.ErrorCodeConstants;
import it.flowbe.tenetcommonlibrary.exception.ForbiddenException;
import it.flowbe.tenetcommonlibrary.exception.InternalServerErrorException;
import it.flowbe.tenetcommonlibrary.keycloak.KeycloakConstants;
import it.flowbe.tenetcommonlibrary.keycloak.KeycloakUtils;
import it.flowbe.tenetcommonlibrary.model.Customer;
import it.flowbe.tenetcommonlibrary.model.CustomerApplication;
import it.flowbe.tenetcommonlibrary.orchestrator.constants.OrchestratorUtilsConstants;
import it.flowbe.tenetcommonlibrary.repository.CustomerApplicationRepository;
import it.flowbe.tenetcommonlibrary.service.ApiMessageService;
import it.flowbe.tenetcommonlibrary.service.CustomerService;
import it.flowbe.tenetcommonlibrary.utils.DateUtils;
import it.flowbe.tenetcommonlibrary.utils.MaintenanceUtils;
import it.flowbe.tenetcommonlibrary.utils.Utils;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HttpRequestInterceptor implements HandlerInterceptor {
    
    private final KeycloakUtils keycloakUtils;
    private final CustomerService customerService;
    private final CustomerApplicationRepository customerApplicationRepository;
    private final MaintenanceUtils maintenanceUtils;
    private final ApiMessageService apiMessageService;
    @Value("${info.app.name}")
    private String appName;
    
    @Value("${security.keycloak.realm}")
    private String realm;
    
    @Value("${security.referrerAuthorized.url}")
    private String referrerAuthorizedUrl;
    
    public Map<String, String> customerApplication = new HashMap<>();
    public Map<String, String> clientAuth = new HashMap<>();
    public Map<String, String> excludeUriClientAuth = new HashMap<>();
    public Map<String, String> externalUserUri = new HashMap<>();
    
    private static final String CERTIFICATE_NULL_VALUE = "(null)";
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String scheme = request.getScheme();         
        String serverName = request.getServerName(); 
        //int serverPort = request.getServerPort(); 
        String url = request.getRequestURL().toString();
        //System.out.println(url);
        String baseUrl = scheme + "://" + serverName;
        System.out.println("BASEURL:" +baseUrl);
        String clientId = request.getHeader("clientId");
        String clientSecret = request.getHeader("clientSecret");
        
        if(!Objects.toString(clientId, "").isEmpty()) {
            String clientSecretKeycloak = keycloakUtils.getClientSecret(realm, clientId);
            if((clientSecretKeycloak.equals(clientSecret)) && (referrerAuthorizedUrl.equals(baseUrl)))
            	return true;
            else
            	return false;
        }
        else if ((url.contains("swagger-ui")) || (url.contains("/v3/api-docs")))
        	return true;
        else
        	return false;
      
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        // Operazioni dopo aver gestito la richiesta
    }
    
    
}
