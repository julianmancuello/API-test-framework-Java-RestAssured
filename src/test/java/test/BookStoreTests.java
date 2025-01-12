package test;

import clients.BookStoreApi;
import common.LoggerUtils;
import models.Book;
import models.BookStore;
import models.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import setup.BaseSetUp;

import static common.LoggerUtils.*;
import static common.Utils.*;

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
        Assertions.assertEquals(bookStore.getBooks().get(0).getIsbn(), "9781449325862");
    }

    @ParameterizedTest(name = "Test {index}")
    @MethodSource(value = "data.DataProviders#dataBooks")
    public void testGetBookByIsbnWithExistingIsbn(Book bookTest) {
        info("Getting book by ISBN: " + bookTest.getIsbn());
        Book bookResult = bookStoreApi.getBookByIsbnWithExistingIsbn(bookTest.getIsbn());

        Assertions.assertNotNull(bookResult, "Response should not be null");
        Assertions.assertEquals(bookTest, bookResult, "FAILED: The book data in the response does not match the expected data.");
        info("SUCCESS: The book data in the response matches the expected data.");
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
