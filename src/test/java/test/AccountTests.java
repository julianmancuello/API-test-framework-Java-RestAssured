package test;

import clients.AccountApi;
import context.ContextStore;
import io.restassured.response.Response;
import models.responses.Message;
import models.responses.User;
import models.responses.UserWithTypo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import setup.BaseSetUp;

import static common.Authentication.*;
import static common.Authentication.UserType.*;
import static common.LoggerUtils.divider;
import static common.LoggerUtils.info;
import static common.Utils.generateRandomPassword;
import static common.Utils.generateRandomUser;
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
        String newUsername = generateRandomUser();
        String newPassword = generateRandomPassword();
        ContextStore.put("newUsername", newUsername);
        ContextStore.put("newPassword", newPassword);
        UserWithTypo newUser = accountApi.createUser(newUsername, newPassword);
        ContextStore.put("newUserId", newUser.getUserId());

        info(ContextStore.get("newUsername"));
        info(ContextStore.get("newPassword"));
        info(ContextStore.get("newUserId"));
        info(String.valueOf(newUser));
    }

    @Test
    public void testDeleteUser() {
        String newUsername = generateRandomUser();
        String newPassword = generateRandomPassword();
        ContextStore.put("newUsername", newUsername);
        ContextStore.put("newPassword", newPassword);
        UserWithTypo newUser = accountApi.createUser(newUsername, newPassword);
        ContextStore.put("newUserId", newUser.getUserId());

        info("-/-/-/-/-");
        info("Deleting created user");
        Response response = accountApi.deleteUser(DISPOSABLE_USER);
        info(response.asPrettyString());
        assertTrue(response.getBody().asString().isEmpty(), "Error: The user was not deleted");
        info("The user was deleted");
    }
}
