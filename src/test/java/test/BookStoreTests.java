package test;

import clients.BookStoreApi;
import models.Book;
import models.BookStore;
import models.ErrorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import setup.BaseSetUp;

import static common.LoggerUtils.*;
import static common.Utils.*;
import static data.TestData.*;
import static org.junit.jupiter.api.Assertions.*;

public class BookStoreTests extends BaseSetUp {

    private BookStoreApi bookStoreApi;

    @BeforeEach
    public void setUp() {
        bookStoreApi = container.provideBookStoreApi();
        divider();
    }

    @Test
    public void testGetAllBooksSuccessfully() {
        BookStore bookStore = bookStoreApi.getAllBooks();

        System.out.println(bookStore.getBooks().get(0).getTitle());
        System.out.println(bookStore.getBooks().get(0).getPublishDate());
        assertEquals(bookStore.getBooks().get(0).getIsbn(), "9781449325862");
    }

    @ParameterizedTest(name = "Test {index}")
    @MethodSource(value = "data.DataProviders#dataBooks")
    public void testGetBookByIsbnWithExistingIsbn(Book bookTest) {
        info("Getting book by ISBN: " + bookTest.getIsbn());
        Book bookResult = bookStoreApi.getBookByIsbnWithExistingIsbn(bookTest.getIsbn());

        assertNotNull(bookResult, "Response should not be null");
        assertEquals(bookTest, bookResult, "FAILED: The book data in the response does not match the expected data.");
        info("SUCCESS: The book data in the response matches the expected data.");
    }

    @Test
    public void testGetBookByIsbnWithInvalidIsbn() {
        String invalidIsbn = generateRandomIsbn();
        info("Testing to get a book with invalid ISBN: " + invalidIsbn);
        ErrorMessage errorMessageResult = bookStoreApi.getBookByIsbnWithInvalidIsbn(invalidIsbn);

        assertNotNull(errorMessageResult, "Response should not be null");
        assertEquals(ERROR_MESSAGE, errorMessageResult, "FAILED: The error message data in the response does not match the expected data");
        info("SUCCESS: The error message data in the response matches the expected data.");
    }
}
