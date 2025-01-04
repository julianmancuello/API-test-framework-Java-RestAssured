package setup;

import clients.BookStoreApi;

public class DependencyContainer {

    public BookStoreApi provideBookStoreApi() {
        return new BookStoreApi();
    }

//    public AccountApi provideAccountApi() {
//        return new AccountApi();
//    }
}