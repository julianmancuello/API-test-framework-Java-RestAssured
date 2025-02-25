package clients;

import context.ContextStore;
import io.restassured.response.Response;
import models.requests.Credentials;
import models.responses.Message;
import models.responses.User;
import models.responses.UserWithTypo;

import static common.Authentication.UserType;
import static common.Endpoints.USER_ENDPOINT;
import static common.Endpoints.USER_ID_ENDPOINT;
import static common.Utils.getTestUserId;
import static common.Utils.loadUserId;
import static io.restassured.RestAssured.given;

public class AccountApi extends BaseApi {

    public AccountApi() {
        super();
    }

    public User getUser(UserType userType) {
        return given()
                .spec(getRequestSpecWithAuth(userType))
                .pathParams("UUID", getTestUserId())
                .when()
                .get(USER_ID_ENDPOINT)
                .then().statusCode(200)
                .extract().body().as(User.class);
    }

    public Message getUserWithoutToken(UserType userType) {
        loadUserId(userType);
        return given()
                .spec(getRequestSpec())
                .pathParams("UUID", getTestUserId())
                .when()
                .get(USER_ID_ENDPOINT)
                .then().statusCode(401)
                .extract().body().as(Message.class);
    }

    public UserWithTypo createUser(Credentials credentials) {
        UserWithTypo newUser = given()
                .spec(getRequestSpec())
                .body(credentials)
                .when()
                .post(USER_ENDPOINT)
                .then().statusCode(201)
                .extract().body().as(UserWithTypo.class);
        ContextStore.put("newUserId", newUser.getUserId());
        return newUser;
    }

    public Response deleteUser(UserType userType) {
        return given()
                .spec(getRequestSpecWithAuth(userType))
                .pathParams("UUID", getTestUserId())
                .when()
                .delete(USER_ID_ENDPOINT)
                .then().statusCode(204)
                .extract().response();
    }
}
