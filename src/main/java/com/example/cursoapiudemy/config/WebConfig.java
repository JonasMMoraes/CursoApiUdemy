package com.example.cursoapiudemy.config;

import com.example.cursoapiudemy.serializationConverter.YamelJackson2HttpMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("application/x-yaml");

    @Value("${spring.graphql.cors.allowed-origin-patterns}")
    private String corsOriginPatterns = "";

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        //Via queryParam
//        configurer
//                .favorParameter(true)
//                .parameterName("mediaType")
//                .ignoreAcceptHeader(true)
//                .useRegisteredExtensionsOnly(false)
//                .defaultContentType(MediaType.APPLICATION_JSON)
//                .mediaType("json", MediaType.APPLICATION_JSON)
//                .mediaType("xml", MediaType.APPLICATION_XML);

//        Via HEADER Param
        configurer
                .favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YML);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] allowedOrigins = corsOriginPatterns.split(",");
        registry.addMapping("/**")
                .allowedMethods("*") //                .allowedMethods("GET", "POST", "PUT")
                .allowedOrigins(allowedOrigins)
                .allowCredentials(true);

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YamelJackson2HttpMessageConverter());
    }
}
