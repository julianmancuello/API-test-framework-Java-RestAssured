package test;

import clients.AccountApi;
import context.ContextStore;
import io.restassured.response.Response;
import models.responses.Message;
import models.responses.User;
import models.responses.UserWithTypo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import setup.BaseSetUp;

import static common.Authentication.*;
import static common.LoggerUtils.divider;
import static common.LoggerUtils.info;
import static data.TestData.ERROR_UNAUTHORIZED_USER;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTests extends BaseSetUp {

    private AccountApi accountApi;

    @BeforeEach
    public void setUp() {
        accountApi = container.provideAccountApi();
        divider();
    }

    @ParameterizedTest
    @MethodSource(value = "data.DataProviders#dataUsersInformation")
    public void testGetUserInformation(UserType userType, User userTest) {
        info("Getting user information");
        User user = accountApi.getUser(userType);

        assertEquals(userTest, user, "FAILED: " + user.getUsername() + " information in the response does not match the expected data.");
        info("SUCCESS: " + user.getUsername() + " information in the response matches the expected data.");
    }

    @ParameterizedTest
    @MethodSource(value = "data.DataProviders#dataUsers")
    public void testGetUserInformationWithoutToken(UserType userType) {
        info("Testing to get a user without token");
        Message messageResult = accountApi.getUserWithoutToken(userType);

        assertEquals(ERROR_UNAUTHORIZED_USER, messageResult, "FAILED: The error message data in the response when trying to get " + userType + " information does not match the expected data");
        info("SUCCESS: The error message data in the response when trying to get " + userType + " information matches the expected data.");
    }

    @Test
    public void testNewUser() {
        UserWithTypo newUser = accountApi.createNewUser();

        info(ContextStore.get("newUsername"));
        info(ContextStore.get("newPassword"));
        info(ContextStore.get("newUserId"));
        info(String.valueOf(newUser));
        //ContextStore.put("newUserId", newUser.getUserId());

        Response response = accountApi.deleteDisposableUser();
        info("-/-/-/-/-");
        info(response.asPrettyString());
        assertTrue(response.getBody().asString().isEmpty(), "The body response is not empty");
    }
}
