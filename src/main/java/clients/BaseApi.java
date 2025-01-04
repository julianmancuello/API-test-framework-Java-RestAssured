package clients;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseApi {

    private final String BASE_URI = "https://bookstore.toolsqa.com";
    protected RequestSpecification requestSpec;

    public BaseApi() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType("application/json")
                .build();
    }
}

