package common;

public class Endpoints {
    //Base URI
    public static final String BASE_URI = "https://bookstore.toolsqa.com";
    //Account endpoints
    public static final String ACCOUNT_ROOT = "/Account/v1";
    public static final String GENERATE_TOKEN_ENDPOINT = ACCOUNT_ROOT + "/GenerateToken";
    public static final String USER_ID_ENDPOINT = ACCOUNT_ROOT + "/User/{UUID}";
    //BookStore endpoints
    public static final String BOOKSTORE_ROOT = "/BookStore/v1";
    public static final String BOOKS_ENDPOINT = BOOKSTORE_ROOT + "/Books";
    public static final String BOOK_ENDPOINT = BOOKSTORE_ROOT + "/Book";
}
