package setup;

import context.ContextStore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseSetUp {

    protected DependencyContainer container;

    public BaseSetUp() {
        container = new DependencyContainer();
    }

    @BeforeAll
    public static void loadPropertiesForStaticVariables() {
        ContextStore.loadPropertiesFile();
    }

    @BeforeEach
    public void loadProperties() {
        ContextStore.loadPropertiesFile();
    }

    @AfterEach
    public void tearDown() {
        ContextStore.clear();
    }
}
