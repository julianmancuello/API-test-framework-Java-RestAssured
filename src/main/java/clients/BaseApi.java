package clients;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseApi {

    private final String BASE_URI = "https://bookstore.toolsqa.com";
    private RequestSpecification requestSpec;
    private String token;

    public BaseApi() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType("application/json")
                .build();
    }

    public RequestSpecification getRequestSpec(boolean authRequired) {
        if (authRequired) {
            return requestSpec.header("Authorization", "Bearer " + token);
        }
        return requestSpec;
    }
}

