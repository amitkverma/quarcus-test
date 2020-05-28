package org.acme.practice.config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

@Readiness
@ApplicationScoped
public class ReadinessHealthCheck implements HealthCheck {

    @Inject
    @ConfigProperty(name = "health.membership.dbtimeout", defaultValue = "3")
    private int timeout;

    @Inject
    private DataSource datasource;

	@Override
	public HealthCheckResponse call() {
        HealthCheckResponseBuilder  responseBuilder = HealthCheckResponse.named("database");
        try {
            Connection connection = datasource.getConnection();
            boolean isValid = connection.isValid(timeout);
            DatabaseMetaData metaData = connection.getMetaData();

            responseBuilder.withData("ProductName", metaData.getDatabaseProductName())
            .withData("ProductVersion", metaData.getDatabaseProductVersion())
            .withData("DriverName", metaData.getDriverName())
            .withData("DriverVersion", metaData.getDriverVersion())
            .withData("isValid", isValid);

            return responseBuilder.state(isValid).build();
		} catch (SQLException e) {
            responseBuilder = responseBuilder.withData("ExceptionMsg", e.getMessage());
            return responseBuilder.down().build();
		}
	}
    
}