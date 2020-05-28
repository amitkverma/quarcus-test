package org.acme.practice;

import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
@OpenAPIDefinition(
    info = @Info(
        title="Practice API",
        version = "1.0.0",
        contact = @Contact(
            name = "Amit Verma",
            email = "amit.verma@iqvia.com"),
        license = @License(
            name = "Apache 2.0",
            url = "http://www.apache.org/licenses/LICENSE-2.0.html"))
)
public class App extends Application {
    public static void main(String... args) {
        Quarkus.run(PracticeApp.class, args);
    }

    public static class PracticeApp implements QuarkusApplication {
        @Override
        public int run(String... args) {
            Logger logger = LoggerFactory.getLogger(App.class);
            logger.info("---- Executing Starup Tasks ------");
            Quarkus.waitForExit();
            return 0;
        }
    }
    
}