package clients;

import models.Book;
import models.BookStore;
import models.ErrorMessage;

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
                .then().statusCode(200)
                .extract().body().as(BookStore.class);
    }

    public Book getBookByIsbnWithExistingIsbn(String isbn) {
        return given()
                .spec(getRequestSpec())
                .param("ISBN", isbn)
                .when()
                .get(BOOK_ENDPOINT)
                .then().statusCode(200)
                .extract().body().as(Book.class);
    }

    public ErrorMessage getBookByIsbnWithInvalidIsbn(String isbn) {
        return given()
                .spec(getRequestSpec())
                .param("ISBN", isbn)
                .when()
                .get(BOOK_ENDPOINT)
                .then().statusCode(400)
                .extract().body().as(ErrorMessage.class);
    }
}
