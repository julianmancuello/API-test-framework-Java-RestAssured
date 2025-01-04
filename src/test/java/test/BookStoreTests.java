package test;

import clients.BookStoreApi;
import io.restassured.response.Response;
import models.BookStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import setup.BaseSetUp;

public class BookStoreTests extends BaseSetUp {

    private BookStoreApi bookStoreApi;

    @BeforeEach
    public void setUp() {
        bookStoreApi = container.provideBookStoreApi();
    }

    @Test
    public void testGetAllBooksSuccessfully() {
        BookStore bookStore = bookStoreApi.getAllBooks();

        System.out.println(bookStore.getBooks().get(0).getTitle());
        Assertions.assertEquals(bookStore.getBooks().get(0).getIsbn(), "9781449325862");
        //Assertions.assertEquals(bookStore.statusCode(), 200, "Response is not successfully!");
        //System.out.println("Get All Books Response: " + bookStore.asPrettyString());
    }

    @Test
    public void testGetBookByIsbnSuccessfully() {
        String isbn = "9781449325862";
        Response response = bookStoreApi.getBookByISBN(isbn);

        Assertions.assertNotNull(response, "Response should not be null");
        Assertions.assertEquals(response.statusCode(), 200, "Response is not successfully!");

        System.out.println("Response status code: " + response.getStatusCode());
        System.out.println("Response body: " + response.asPrettyString());
    }

}
