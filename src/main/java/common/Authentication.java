package common;

import context.ContextStore;
import io.restassured.http.ContentType;
import models.requests.Credentials;

import static common.Endpoints.BASE_URI;
import static common.Endpoints.GENERATE_TOKEN_ENDPOINT;
import static common.Utils.getTestPassword;
import static common.Utils.getTestUser;
import static io.restassured.RestAssured.given;

public class Authentication {

    public enum UserType {
        MAIN_USER,
        EMPTY_USER,
        DISPOSABLE_USER,
        FULL_BOOKS_USER,
        RESETED_USER
    }

    public static Credentials userCredentials(UserType userType) {
        loadUserCredentials(userType);
        return new Credentials(getTestUser(), getTestPassword());
    }

    public static void loadUserCredentials(UserType userType) {
        switch (userType) {
            case MAIN_USER ->
                    setTestUser(ContextStore.get("main-user"), ContextStore.get("main-password"), ContextStore.get("main-user-id"));
            case EMPTY_USER ->
                    setTestUser(ContextStore.get("empty-user"), ContextStore.get("empty-password"), ContextStore.get("empty-user-id"));
            case DISPOSABLE_USER ->
                    setTestUser(ContextStore.get("newUsername"), ContextStore.get("newPassword"), ContextStore.get("newUserId"));
            case FULL_BOOKS_USER ->
                    setTestUser(ContextStore.get("full-books-user"), ContextStore.get("full-books-password"), ContextStore.get("full-books-user-id"));
            case RESETED_USER ->
                    setTestUser(ContextStore.get("reseted-user"), ContextStore.get("reseted-password"), ContextStore.get("reseted-user-id"));
        }
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
