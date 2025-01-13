package clients;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.Data;

import static io.restassured.RestAssured.given;

@Data
public class BaseApi {

    private final String BASE_URI = "https://bookstore.toolsqa.com";
    private final String GENERATE_TOKEN_ENDPOINT = "/Account/v1/GenerateToken";
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
            generateToken();
            return requestSpec.header("Authorization", "Bearer " + token);
        }
        return requestSpec;
    }

    private void generateToken() {
        System.out.println("Generando token");
        token = given()
                .spec(getRequestSpec())
                .body("{ \"userName\": \"test1\", \"password\": \"Testtest1!\" }")
                .when()
                .post(GENERATE_TOKEN_ENDPOINT)
                .then().statusCode(200)
                .extract().path("token");
    }
}

