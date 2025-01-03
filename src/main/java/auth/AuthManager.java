package auth;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class AuthManager {

    private String token;

    public AuthManager() {
        this.token = generateToken();
    }

    private String generateToken() {
        return "Bearer your-token-here";
    }

    public RequestSpecification getAuthenticatedSpec() {
        return new RequestSpecBuilder()
                .setContentType("application/json")
                .addHeader("Authorization", token)
                .build();
    }

    public String getToken() {
        return token;
    }
}
