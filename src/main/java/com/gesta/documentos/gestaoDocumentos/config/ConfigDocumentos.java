package com.gesta.documentos.gestaoDocumentos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigDocumentos {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
