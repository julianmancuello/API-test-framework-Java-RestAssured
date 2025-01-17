package clients;

import models.responses.Book;
import models.responses.BookStore;
import models.responses.Message;

import static common.Endpoints.BOOKS_ENDPOINT;
import static common.Endpoints.BOOK_ENDPOINT;
import static io.restassured.RestAssured.given;

public class BookStoreApi extends BaseApi {

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

    public Book getBookByIsbnWithValidIsbn(String isbn) {
        return given()
                .spec(getRequestSpec())
                .param("ISBN", isbn)
                .when()
                .get(BOOK_ENDPOINT)
                .then().statusCode(200)
                .extract().body().as(Book.class);
    }

    public Message getBookByIsbnWithInvalidIsbn(String isbn) {
        return given()
                .spec(getRequestSpec())
                .param("ISBN", isbn)
                .when()
                .get(BOOK_ENDPOINT)
                .then().statusCode(400)
                .extract().body().as(Message.class);
    }
}
