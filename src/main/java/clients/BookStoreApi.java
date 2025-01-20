package clients;

import context.ContextStore;
import io.restassured.response.Response;
import models.requests.AddBooks;
import models.requests.Isbn;
import models.responses.AddedBooks;
import models.responses.Book;
import models.responses.BookStore;
import models.responses.Message;

import java.util.List;

import static common.Authentication.*;
import static common.Endpoints.*;
import static common.Utils.getTestUserId;
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

    public AddedBooks addBooksToCollection(UserType userType, List<Isbn> listOfIsbns) {
        return given()
                .spec(getRequestSpecWithAuth(userType))
                .body(new AddBooks(ContextStore.get("testUserId"), listOfIsbns))
                .when()
                .post(BOOKS_ENDPOINT)
                .then().statusCode(201)
                .extract().body().as(AddedBooks.class);
    }

    public Response deleteAllBooksFromCollection(UserType userType) {
        return given()
                .spec(getRequestSpecWithAuth(userType))
                .param("UserId", getTestUserId())
                .when()
                .delete(BOOKS_ENDPOINT)
                .then().statusCode(204)
                .extract().response();
    }
}
