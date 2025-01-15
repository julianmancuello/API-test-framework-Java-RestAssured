package common;

import context.ContextStore;
import io.restassured.http.ContentType;
import models.requests.Credentials;

import static common.Endpoints.BASE_URI;
import static common.Endpoints.GENERATE_TOKEN_ENDPOINT;
import static io.restassured.RestAssured.given;

public class Authentication {

    public enum UserType {
        MAIN_USER,
        EMPTY_USER,
    }

    public static Credentials userCredentials(UserType userType) {
        loadUserCredentials(userType);
        return new Credentials(ContextStore.get("testUser"), ContextStore.get("testPassword"));
    }

    public static void loadUserCredentials(UserType userType) {
        switch (userType) {
            case MAIN_USER -> setTestUser(ContextStore.get("main-user"), ContextStore.get("main-password"), ContextStore.get("main-user-id"));
            case EMPTY_USER -> setTestUser(ContextStore.get("empty-user"), ContextStore.get("empty-password"), ContextStore.get("empty-user-id"));
        };
    }

    public static void setTestUser(String user, String password, String userId) {
        ContextStore.put("testUser", user);
        ContextStore.put("testPassword", password);
        ContextStore.put("testUserId", userId);
    }

    public static String generateToken(UserType userType) {
        return given()
                .body(userCredentials(userType))
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URI + GENERATE_TOKEN_ENDPOINT)
                .then().statusCode(200)
                .extract().path("token");
    }
}
