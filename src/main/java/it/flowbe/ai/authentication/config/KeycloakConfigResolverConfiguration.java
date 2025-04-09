package it.flowbe.ai.authentication.config;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.KeycloakDeploymentBuilder;
import org.keycloak.adapters.spi.HttpFacade.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.flowbe.tenetcommonlibrary.keycloak.KeycloakConstants;
import it.flowbe.tenetcommonlibrary.keycloak.KeycloakUtils;
import it.flowbe.tenetcommonlibrary.model.Customer;
import it.flowbe.tenetcommonlibrary.resttemplate.profiling.constants.ProfilingConstants;
import it.flowbe.tenetcommonlibrary.service.CustomerService;

@Component
public class KeycloakConfigResolverConfiguration implements KeycloakConfigResolver {
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private KeycloakUtils keycloakUtils;
    
    public static Map<String, KeycloakDeployment> realms = new HashMap<>();

    @Override
    public KeycloakDeployment resolve(Request facade) {
        String authorization = facade.getHeader("Authorization");
        String customer = "";
        byte[] decodedJsonRealm = null;
        InputStream is = null;
        KeycloakDeployment realm = null;
        if(authorization != null && !authorization.isEmpty()) {
            String token = authorization.replace("Bearer ", "");
            customer = keycloakUtils.getSingleClaimFromToken(token, KeycloakConstants.CUSTOMER_KEY);
            String userType = keycloakUtils.getSingleClaimFromToken(token, KeycloakConstants.USER_TYPE_KEY);
            String customerToCheck = customer;
            if(userType.equalsIgnoreCase(KeycloakConstants.APP_USER_TYPE)) {
                customerToCheck = KeycloakConstants.APPLICATION_REALM_PREFIX+customerToCheck;
            }
            if(realms.get(customerToCheck) == null) {
                Customer customerObj = customerService.findByCode(customer).get(0);
                if(userType.equalsIgnoreCase(KeycloakConstants.HUMAN_USER_TYPE)) {
                    decodedJsonRealm = customerObj.getHumanJsonRealm().getBytes();
                } else {
                    decodedJsonRealm = customerObj.getApplicationJsonRealm().getBytes();
                    customer = KeycloakConstants.APPLICATION_REALM_PREFIX+customer;
                }
                is = new ByteArrayInputStream(decodedJsonRealm);
                realm = KeycloakDeploymentBuilder.build(is);
                realms.put(customer, realm);
            } else {
                realm = realms.get(customerToCheck);
            }
        } else {
            customer = ProfilingConstants.DEFAULT_CUSTOMER;
            if(realms.get(customer) == null) {
                decodedJsonRealm = customerService.findByCode(ProfilingConstants.DEFAULT_CUSTOMER).get(0).getHumanJsonRealm().getBytes();
                is = new ByteArrayInputStream(decodedJsonRealm);
                realm = KeycloakDeploymentBuilder.build(is);
                realms.put(customer, realm);
            } else {
                realm = realms.get(customer);
            }
        }
        
        return realm;
    }
}
