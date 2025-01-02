package booksTest;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookTest extends BaseSetUp {

    @Test
    public void testGetAllBooksSuccessfully() {
        Response response = bookstoreApi.getAllBooks();

        Assertions.assertNotNull(response, "Response should not be null");
        Assertions.assertEquals(response.statusCode(), 200, "Response is not successfully!");
        System.out.println("Get All Books Response: " + response.getBody().asString());
    }

    @Test
    public void testGetBookByIsbnSuccessfully() {
        String isbn = "9781449325862";
        Response response = bookstoreApi.getBookByISBN(isbn);

        Assertions.assertNotNull(response, "Response should not be null");
        Assertions.assertEquals(response.statusCode(), 200, "Response is not successfully!");

        System.out.println("Response status code: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody().asString());
    }

}
