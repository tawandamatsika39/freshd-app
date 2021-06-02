package co.za.freshdapp.configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.ahus1.keycloak.dropwizard.KeycloakConfiguration;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayFactory;

public class AppConfiguration extends Configuration {
    @Valid
    @NotNull
    private DataSourceFactory datasource = new DataSourceFactory();

    @Valid
    @NotNull
    private FlywayFactory flywayFactory;

    @Valid
    @NotNull
    private KeycloakConfiguration keycloakConfiguration = new KeycloakConfiguration();

    @JsonProperty("datasource")
    public DataSourceFactory getDataSourceFactory() {
        return datasource;
    }

    @JsonProperty("datasource")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.datasource = dataSourceFactory;
    }

    @JsonProperty("flyway")
    public FlywayFactory getFlywayFactory() {
        return flywayFactory;
    }

    @JsonProperty("flyway")
    public void setFlywayFactory(FlywayFactory flywayFactory) {
        this.flywayFactory = flywayFactory;
    }

    @JsonProperty("keycloakConfiguration")
    public KeycloakConfiguration getKeycloakConfiguration() {
        return keycloakConfiguration;
    }

    public void setKeycloakConfiguration(KeycloakConfiguration keycloakConfiguration) {
        this.keycloakConfiguration = keycloakConfiguration;
    }
}
