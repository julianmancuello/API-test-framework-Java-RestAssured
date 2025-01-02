package clients;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BaseApi {

    protected RequestSpecification requestSpec;
    protected final String BASE_URI = "https://bookstore.toolsqa.com";

    public BaseApi() {

        requestSpec = RestAssured.given()
                .baseUri(BASE_URI)
                .contentType("application/json");
    }
}

