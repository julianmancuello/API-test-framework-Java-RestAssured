package clients;

import io.restassured.response.Response;

public class BookStoreApi extends BaseApi {

    // Endpoints for the Bookstore API
    private final String BOOKS_BASE_URI = BASE_URI + "/BookStore/v1";
    private final String BOOKS_ENDPOINT = "/Books";
    private final String BOOK_ENDPOINT = "/Book";

    public BookStoreApi() {
        super();
        requestSpec = requestSpec.baseUri(BOOKS_BASE_URI);
    }

    public Response getAllBooks() {

        return requestSpec
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
