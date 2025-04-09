package it.flowbe.ai.authentication.config;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfiguration {

    /**
     * utilizzo il config resolver custom e non quello di springboot per poter scegliere il realm di riferimento dinamicamente a seconda di chi mi sta chiamando
     * 
     * @return
     */
    @Bean
    public KeycloakConfigResolver keycloakConfigResolver() {
        return new KeycloakConfigResolverConfiguration();
    }
}
