package test;

import clients.BookStoreApi;
import io.restassured.response.Response;
import models.Book;
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
        System.out.println(bookStore.getBooks().get(0).getPublishDate());
        Assertions.assertEquals(bookStore.getBooks().get(0).getIsbn(), "9781449325862");
    }

    @Test
    public void testGetBookByIsbnSuccessfully() {
        String isbn = "9781449325862";
        Book book = bookStoreApi.getBookByISBNSuccessful(isbn);

        System.out.println(book.getTitle());
        System.out.println(book.getPublishDate());
        Assertions.assertNotNull(book, "Response should not be null");
        Assertions.assertEquals(book.getAuthor(), "Richard E. Silverman");
    }

}
