package clients;

import models.responses.User;

import static common.Authentication.*;
import static common.Endpoints.USER_ID_ENDPOINT;
import static common.Utils.getTestUserId;
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
}
