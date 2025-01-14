package clients;

import context.ContextStore;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import models.requests.Credentials;

import static common.Endpoints.BASE_URI;
import static common.Endpoints.GENERATE_TOKEN_ENDPOINT;
import static io.restassured.RestAssured.given;

@Data
public class BaseApi {

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
        Credentials credentialsMainUser = new Credentials(ContextStore.get("standard-user"), ContextStore.get("standard-password"));
        token = given()
                .spec(getRequestSpec())
                .body(credentialsMainUser)
                //.body("{ \"userName\": \"test1\", \"password\": \"Testtest1!\" }")
                .when()
                .post(GENERATE_TOKEN_ENDPOINT)
                .then().statusCode(200)
                .extract().path("token");
    }
}

