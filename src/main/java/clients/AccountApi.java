package clients;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AccountApi extends BaseApi {

    private final String ACCOUNT_ROOT = "/Account/v1";
    private final String USER_ID_ENDPOINT = ACCOUNT_ROOT + "/User/{UUID}";

    public AccountApi() {
        super();
    }

    public Response getUser() {
        return given()
                .spec(getRequestSpec(true))
                .pathParams("UUID", "e1494aaa-674b-4cc8-a824-9d34d8ecdc95")
                .when()
                .get(USER_ID_ENDPOINT)
                .then().statusCode(200)
                .extract().response();
    }
}
