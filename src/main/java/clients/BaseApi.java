package clients;

import io.restassured.specification.RequestSpecification;

public class BaseApi {

    protected RequestSpecification requestSpec;
    private final String BASE_URI = "https://bookstore.toolsqa.com";

    public BaseApi(RequestSpecification requestSpec) {
        this.requestSpec = requestSpec;
        this.requestSpec.baseUri(BASE_URI);
    }
}

