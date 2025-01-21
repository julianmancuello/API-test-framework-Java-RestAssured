package test;

import clients.BookStoreApi;
import io.restassured.response.Response;
import models.requests.Isbn;
import models.responses.AddedBooks;
import models.responses.Book;
import models.responses.BookStore;
import models.responses.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import setup.BaseSetUp;

import java.util.List;

import static common.Authentication.UserType.FULL_BOOKS_USER;
import static common.Authentication.UserType.RESETED_USER;
import static common.LoggerUtils.divider;
import static common.LoggerUtils.info;
import static common.Utils.generateRandomIsbn;
import static data.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        info("SUCCESS: The retrieved books match the expected books");
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

    @Tag("smoke")
    @Test
    public void testGetBookByIsbnWithInvalidIsbn() {
        String invalidIsbn = generateRandomIsbn();
        info("Testing to get a book with invalid ISBN: " + invalidIsbn);
        Message messageResult = bookStoreApi.getBookByIsbnWithInvalidIsbn(invalidIsbn);

        assertEquals(ERROR_INVALID_ISBN, messageResult, "FAILED: The error message data in the response does not match the expected data");
        info("SUCCESS: The error message data in the response matches the expected data.");
    }

    @Tag("regression")
    @Test
    public void testAddBooksToCollectionSuccessfully() {
        info("Adding books to the user's collection");
        List<Isbn> listOfIsbns = selectRandomListOfIsbns();
        AddedBooks addedBooks = bookStoreApi.addBooksToCollection(RESETED_USER, listOfIsbns);

        info("Added books: " + listOfIsbns);
        assertEquals(listOfIsbns, addedBooks.getBooks(), "FAILED: The added books do not match the list of selected books");
        info("SUCCESS: The added books match the list of selected books");
        info("Resetting user, deleting all books");
        bookStoreApi.deleteAllBooksFromCollection(RESETED_USER);
        info("All books deleted");
    }

    @Tag("regression")
    @Test
    public void testDeleteAllBooksFromCollectionSuccessfully() {
        info("Deleting all books from the user's collection");
        info("Before running the API, the user had " + getNumberOfBooksOfUser(FULL_BOOKS_USER) + " books in the collection");
        Response response = bookStoreApi.deleteAllBooksFromCollection(FULL_BOOKS_USER);

        assertTrue(response.getBody().asString().isEmpty(), "FAILED: The response body is not empty");
        info("SUCCESS: The user have " + getNumberOfBooksOfUser(FULL_BOOKS_USER) + " books in the collection, all the books were deleted");
        info("Resetting user, adding all books again");
        bookStoreApi.addBooksToCollection(FULL_BOOKS_USER, listAllIsbns());
        info("All books added again");
    }
}
