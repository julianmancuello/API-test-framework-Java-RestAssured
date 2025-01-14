package setup;

import context.ContextStore;

public class BaseSetUp {

    protected DependencyContainer container;

    public BaseSetUp() {
        container = new DependencyContainer();
        ContextStore.loadProperties("src/test/resources/test.properties");
    }
}
