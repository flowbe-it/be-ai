package it.flowbe.ai.authentication.config;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@KeycloakConfiguration
public class SecurityConfiguration extends KeycloakWebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception { 

		//richiamo configurazione di keycloak
		super.configure(httpSecurity);
		httpSecurity.csrf().disable()
		.authorizeRequests()
		.antMatchers("/swagger-ui.html").permitAll()
		.antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
		.antMatchers("/actuator/**").permitAll()
		//abilitate bypassando sicurezza keycloak chiamate token
		.antMatchers("/api/v1/token/**", "/api/v1/p12ErrorController/**/**/**/**/", "/api/v1/customerApplicationErrorController/wrongCustomerApplication/**/**/**/**/", "/api/v1/ot/memHealthCheck").permitAll()
		.antMatchers("/api/v1/ai/**/").permitAll()
		.anyRequest().authenticated()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
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
	
	/**
     * registers the KeycloakAuthenticationProvider with the authentication manager.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(keycloakAuthenticationProvider());
    }

    /**
     * defines the session authentication strategy.
     */
    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

}
