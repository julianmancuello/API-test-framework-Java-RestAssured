package setup;

import context.ContextStore;
import org.junit.jupiter.api.BeforeAll;

public class BaseSetUp {

    protected DependencyContainer container;

    public BaseSetUp() {
        container = new DependencyContainer();
    }

    @BeforeAll
    public static void loadProperties() {
        ContextStore.loadProperties("src/test/resources/test.properties");
    }
}
