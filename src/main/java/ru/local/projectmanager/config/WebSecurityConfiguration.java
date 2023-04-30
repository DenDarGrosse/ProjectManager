package ru.local.projectmanager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;
import java.util.List;

import static ru.local.projectmanager.router.Router.*;

@Configuration
@EnableWebMvc
public class WebSecurityConfiguration implements WebMvcConfigurer {

    @Value("${frontend.serverHost}")
    private String serverHost;
    @Value("${frontend.serverPort}")
    private String serverPort;

    @Bean
    public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
        var config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList(serverHost + ":" + serverPort));
        config.setAllowedMethods(List.of("GET", "POST", "OPTIONS"));
        config.setAllowedHeaders(List.of("Access-Control-Allow-Origin", "Authorization", "content-type"));

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(API_AUTH + "/*", config);
        source.registerCorsConfiguration(LOGIN_ENDPOINT, config);
        source.registerCorsConfiguration(REGISTRATION_ENDPOINT, config);

        var bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

}
