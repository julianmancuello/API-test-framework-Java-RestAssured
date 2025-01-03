package booksTest;

import clients.BookStoreApi;

public class BaseSetUp {

    protected BookStoreApi bookstoreApi;

    public BaseSetUp() {
        bookstoreApi = new BookStoreApi();
    }
}
