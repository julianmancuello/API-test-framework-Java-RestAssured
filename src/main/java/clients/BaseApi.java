package clients;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.Data;

import static common.Authentication.*;
import static common.Authentication.generateToken;
import static common.Endpoints.BASE_URI;

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

    public RequestSpecification getRequestSpecWithAuth(UserType userType) {
            return requestSpec.header("Authorization", "Bearer " + generateToken(userType));
    }
}