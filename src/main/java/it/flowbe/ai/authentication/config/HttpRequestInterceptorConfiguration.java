package it.flowbe.ai.authentication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class HttpRequestInterceptorConfiguration implements WebMvcConfigurer {
    
    private final HttpRequestInterceptor httpRequestInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpRequestInterceptor);
    }
}