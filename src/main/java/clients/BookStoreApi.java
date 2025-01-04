package clients;

import io.restassured.response.Response;
import models.BookStore;

import static io.restassured.RestAssured.given;

public class BookStoreApi extends BaseApi {

    private final String BOOKSTORE_ROOT = "/BookStore/v1";
    private final String BOOKS_ENDPOINT = BOOKSTORE_ROOT + "/Books";
    private final String BOOK_ENDPOINT = BOOKSTORE_ROOT + "/Book";

    public BookStoreApi() {
        super();
    }

    public BookStore getAllBooks() {
        return given()
                .spec(getRequestSpec())
                .when()
                .get(BOOKS_ENDPOINT)
                .as(BookStore.class);
    }

    public Response getBookByISBN(String isbn) {
        return given()
                .spec(getRequestSpec())
                .param("ISBN", isbn)
                .when()
                .get(BOOK_ENDPOINT)
                .then()
                .extract().response();
    }

}
