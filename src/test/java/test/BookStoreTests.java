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

import static common.LoggerUtils.divider;
import static common.LoggerUtils.info;
import static common.Utils.generateRandomIsbn;
import static data.TestData.ALL_BOOKS;
import static data.TestData.ERROR_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookStoreTests extends BaseSetUp {

    private BookStoreApi bookStoreApi;

    @BeforeEach
    public void setUp() {
        bookStoreApi = container.provideBookStoreApi();
        divider();
    }

    @Test
    public void testGetAllBooksSuccessfully() {
        info("Getting all books");
        BookStore bookStore = bookStoreApi.getAllBooks();

        assertNotNull(bookStore, "Response should not be null");
        assertEquals(ALL_BOOKS, bookStore, "FAILED: The retrieved books do not match the expected books");
        info("SUCCESS: The retrieved books matches the expected books");
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
