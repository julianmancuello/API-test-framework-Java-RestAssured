package clients;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookStoreApi extends BaseApi {

    private final String BOOKSTORE_ROOT = "/BookStore/v1";
    private final String BOOKS_ENDPOINT = BOOKSTORE_ROOT + "/Books";
    private final String BOOK_ENDPOINT = BOOKSTORE_ROOT + "/Book";

    public BookStoreApi() {
        super();
    }

    public Response getAllBooks() {
        return given()
                .spec(requestSpec)
                .when()
                .get(BOOKS_ENDPOINT)
                .then()
                .extract().response();
    }

    public Response getBookByISBN(String isbn) {
        return requestSpec
                .param("ISBN", isbn)
                .when()
                .get(BOOK_ENDPOINT)
                .then()
                .extract().response();
    }

}
