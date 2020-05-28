package org.acme.practice.config;


import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.arc.config.ConfigProperties;

@ConfigProperties(prefix = "practice")
public interface ApplicationConfiguration {
    @ConfigProperty(name="application.name")
    String applicationName();    
}