package org.acme.practice.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.eclipse.microprofile.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;

@ApplicationScoped
public class ApplicationLifecycleBean {
    
    private static final Logger logger = LoggerFactory.getLogger(ApplicationLifecycleBean.class);

    @Inject
    private Config config;

    void onStart(@Observes StartupEvent ev) throws UnknownHostException{
        String protocol = "http";
        logger.info("------- STARTING APPLICATION ------");
        logger.info("\n----------------------------------------------------------\n\t" +
                    "Application '{}' is running! on Access URLs:\n\t" + "Local: \t\t{}://localhost:{}\n\t" +
                    "External: \t{}://{}:{}\n\t" +
                    "Profile(s): \t{}\n----------------------------------------------------------\n----------------------------------------------------------",
                    config.getValue("practice.application.name", String.class),
                    protocol,config.getValue("quarkus.http.port", String.class), protocol, InetAddress.getLocalHost().getHostAddress(),
                    config.getValue("quarkus.http.port", String.class), ProfileManager.getActiveProfile());


    }

    void onStop(@Observes ShutdownEvent ev){
        logger.info("----------- SHUTTING DOWN APPLICATION ---------");
    }
}