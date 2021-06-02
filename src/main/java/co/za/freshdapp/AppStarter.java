package co.za.freshdapp;

import co.za.freshdapp.configuration.AppConfiguration;
import co.za.freshdapp.resources.ProductResource;

import de.ahus1.keycloak.dropwizard.KeycloakBundle;
import de.ahus1.keycloak.dropwizard.KeycloakConfiguration;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayBundle;
import io.dropwizard.flyway.FlywayFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class AppStarter extends Application<AppConfiguration> {

    private static final String SWAGGER_PACKAGE = "co.za.freshdapp.resources";

    private final FlywayBundle<AppConfiguration> flywayBundle = new FlywayBundle<AppConfiguration>() {
        @Override
        public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
        
        @Override
        public FlywayFactory getFlywayFactory(AppConfiguration configuration) {
            return configuration.getFlywayFactory();
        }
    };

    private final KeycloakBundle<AppConfiguration> keycloakBundle = new KeycloakBundle<AppConfiguration>() {
        @Override
        protected KeycloakConfiguration getKeycloakConfiguration(AppConfiguration configuration) {
            return configuration.getKeycloakConfiguration();
        }
        /* OPTIONAL: override getUserClass(), createAuthorizer() and createAuthenticator() if you want to use
         * a class other than de.ahus1.keycloak.dropwizard.User to be injected by @Auth */
    };
    
    public static void main(String[] args) throws Exception {
        new AppStarter().run(args);
    }

    @Override
    public String getName() {
        return "freshd-app";
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {

        bootstrap.setConfigurationSourceProvider(
            new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                new EnvironmentVariableSubstitutor(false))
        );

        bootstrap.addBundle(flywayBundle);
        bootstrap.addBundle(keycloakBundle);
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) {

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
        environment.jersey().register(new ProductResource(jdbi));
    }

}
