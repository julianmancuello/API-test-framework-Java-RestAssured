package test;

import clients.BookStoreApi;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import models.Book;
import models.BookStore;
import models.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import setup.BaseSetUp;

import static common.Utils.*;

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
    @UseDataProvider("dataBooks")
    public void testGetBookByIsbnWithExistingIsbn(String isbn, String title, String subtitle, String author, String publishDate, String publisher, int pages, String description, String website) {
        Book book = bookStoreApi.getBookByIsbnWithExistingIsbn(isbn);

        System.out.println(book.getTitle());
        System.out.println(book.getPublishDate());
        Assertions.assertNotNull(book, "Response should not be null");
        Assertions.assertEquals(book.getAuthor(), author);
    }

    @Test
    public void testGetBookByIsbnWithInvalidIsbn() {
        String invalidIsbn = generateRandomIsbn();
        System.out.println("Invalid ISBN: " + invalidIsbn);
        ErrorMessage errorMessage = bookStoreApi.getBookByIsbnWithInvalidIsbn(invalidIsbn);

        System.out.println(errorMessage.getCode());
        System.out.println(errorMessage.getMessage());
        Assertions.assertNotNull(errorMessage, "Response should not be null");
    }
}
