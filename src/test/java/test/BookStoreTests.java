package test;

import clients.BookStoreApi;
import models.responses.Book;
import models.responses.BookStore;
import models.responses.Message;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import setup.BaseSetUp;

import static common.LoggerUtils.divider;
import static common.LoggerUtils.info;
import static common.Utils.generateRandomIsbn;
import static data.TestData.ALL_BOOKS;
import static data.TestData.ERROR_INVALID_ISBN;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookStoreTests extends BaseSetUp {

    private BookStoreApi bookStoreApi;

    @BeforeEach
    public void setUp() {
        bookStoreApi = container.provideBookStoreApi();
        divider();
    }

    @Tag("smoke")
    @Test
    public void testGetAllBooksSuccessfully() {
        info("Getting all books");
        BookStore bookStore = bookStoreApi.getAllBooks();

        assertEquals(ALL_BOOKS, bookStore, "FAILED: The retrieved books do not match the expected books");
        info("SUCCESS: The retrieved books matches the expected books");
    }

    @Tag("regression")
    @ParameterizedTest(name = "Test Book {index}")
    @MethodSource(value = "data.DataProviders#dataBooks")
    public void testGetBookByIsbnWithExistingIsbn(Book bookTest) {
        info("Getting book by ISBN: " + bookTest.getIsbn());
        Book bookResult = bookStoreApi.getBookByIsbnWithValidIsbn(bookTest.getIsbn());

        assertEquals(bookTest, bookResult, "FAILED: The book data in the response does not match the expected data.");
        info("SUCCESS: The book data in the response matches the expected data.");
    }

    @Tag("regression")
    @Test
    public void testGetBookByIsbnWithInvalidIsbn() {
        String invalidIsbn = generateRandomIsbn();
        info("Testing to get a book with invalid ISBN: " + invalidIsbn);
        Message messageResult = bookStoreApi.getBookByIsbnWithInvalidIsbn(invalidIsbn);

        assertEquals(ERROR_INVALID_ISBN, messageResult, "FAILED: The error message data in the response does not match the expected data");
        info("SUCCESS: The error message data in the response matches the expected data.");
    }
}
