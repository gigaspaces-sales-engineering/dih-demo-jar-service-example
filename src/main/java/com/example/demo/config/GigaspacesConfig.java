package com.example.demo.config;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.SpaceProxyConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GigaspacesConfig {

    @Value("${space.name}")
    private String spaceName;

    @Value("${space.host}")
    private String spaceHost;

    @Bean
    public GigaSpace gigaSpace() {
        return new GigaSpaceConfigurer(new SpaceProxyConfigurer(spaceName).lookupLocators(spaceHost)).gigaSpace();
    }

}
