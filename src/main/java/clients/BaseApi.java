package clients;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.Data;

import static common.Authentication.UserType;
import static common.Authentication.generateToken;
import static common.Endpoints.BASE_URI;

@Data
public class BaseApi {

    private RequestSpecification requestSpec;
    private static String token;

    public BaseApi() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType("application/json")
                .build();
    }

    public RequestSpecification getRequestSpecWithAuth(UserType userType) {
        if (token == null) {
            token = generateToken(userType);
        }
        return requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType("application/json")
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }

    public static void resetToken() {
        token = null;
    }
}