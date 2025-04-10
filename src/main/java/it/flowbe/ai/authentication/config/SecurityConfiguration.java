package it.flowbe.ai.authentication.config;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

import it.flowbe.tenetcommonlibrary.config.DynamicJwtDecoder;
import it.flowbe.tenetcommonlibrary.controller.P12ErrorController;
import lombok.RequiredArgsConstructor;

@KeycloakConfiguration
@RequiredArgsConstructor
public class SecurityConfiguration {
    
    private final DynamicJwtDecoder dynamicJwtDecoder;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/swagger-ui.html").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .requestMatchers("/actuator/**").permitAll()
                //abilitate bypassando sicurezza keycloak chiamate token e admin
                .requestMatchers("/api/v1/token/**", "/api/v1/ai/**").permitAll()
                .requestMatchers(new RegexRequestMatcher(P12ErrorController.PATH_FOR_SECURITY_CONFIG, HttpMethod.POST.name())).permitAll()
                .requestMatchers(new RegexRequestMatcher(P12ErrorController.PATH_FOR_SECURITY_CONFIG, HttpMethod.GET.name())).permitAll()
                .requestMatchers(new RegexRequestMatcher(P12ErrorController.PATH_FOR_SECURITY_CONFIG, HttpMethod.DELETE.name())).permitAll()
                .requestMatchers(new RegexRequestMatcher(P12ErrorController.PATH_FOR_SECURITY_CONFIG, HttpMethod.PATCH.name())).permitAll()
                .requestMatchers(new RegexRequestMatcher(P12ErrorController.PATH_FOR_SECURITY_CONFIG, HttpMethod.PUT.name())).permitAll()
                .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.decoder(dynamicJwtDecoder)))
                .csrf(AbstractHttpConfigurer::disable);
        
        return http.build();
    }
	
	/**
	 * session listener per corretto funzionamento di Single Sign Out
	 * 
	 * @return
	 */
	@Bean
	public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
	    return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
	}
}
