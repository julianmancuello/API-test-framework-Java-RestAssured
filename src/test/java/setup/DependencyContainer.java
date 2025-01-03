package setup;

import auth.AuthManager;
import clients.BookStoreApi;

public class DependencyContainer {

    private final AuthManager authManager;

    public DependencyContainer() {
        authManager = new AuthManager();
    }

    public BookStoreApi provideBookStoreApi() {
        return new BookStoreApi(authManager.getAuthenticatedSpec());
    }

//    public AccountApi provideAccountApi() {
//        return new AccountApi(authManager.getAuthenticatedSpec());
//    }

    public AuthManager getAuthManager() {
        return authManager;
    }
}