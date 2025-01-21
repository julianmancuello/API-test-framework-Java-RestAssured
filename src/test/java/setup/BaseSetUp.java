package setup;

import context.ContextStore;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static clients.BaseApi.resetToken;

public class BaseSetUp {

    protected DependencyContainer container;

    public BaseSetUp() {
        container = new DependencyContainer();
    }

    @BeforeAll
    public static void loadPropertiesForStaticVariables() {
        ContextStore.loadPropertiesFile();
        RestAssured.filters(
                new RequestLoggingFilter(),
                new ResponseLoggingFilter()
        );
    }

    @BeforeEach
    public void loadProperties() {
        ContextStore.loadPropertiesFile();
    }

    @AfterEach
    public void tearDown() {
        ContextStore.clear();
        resetToken();
    }
}
