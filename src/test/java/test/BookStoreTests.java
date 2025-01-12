package test;

import clients.BookStoreApi;
import models.Book;
import models.BookStore;
import models.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
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

    @ParameterizedTest(name = "Run: {index}  -  value: {arguments}")
    @MethodSource(value = "data.DataProviders#dataBooks")
    public void testGetBookByIsbnWithExistingIsbn(Book bookTest) {
        Book bookResult = bookStoreApi.getBookByIsbnWithExistingIsbn(bookTest.getIsbn());

        Assertions.assertNotNull(bookResult, "Response should not be null");
        Assertions.assertEquals(bookResult, bookTest, "The information in the responded book is not equal as that in the expected book");
        System.out.println("The information in the responded book is equal as that in the expected book");
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
