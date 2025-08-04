package it.flowbe.ai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;

@Configuration
public class SwaggerConfig {

	@Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
		Components components = new Components();
		
		SecurityScheme securityScheme = new SecurityScheme();
		 	        
		securityScheme.setType(Type.HTTP);
		securityScheme.setScheme("bearer");
		securityScheme.setIn(In.HEADER);
		securityScheme.setName("JWT Authentication");
		securityScheme.bearerFormat("JWT");
		securityScheme.description("Enter JWT Bearer token");
		
		components.addSecuritySchemes("jwtScheme", securityScheme);
		
        return new OpenAPI()
        	.components(components)
        	.info(new Info().title("AI API")
            .version(appVersion)
            .description("This is an api for ai engine."));
    }

}
