package test;

import clients.AccountApi;
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
import static data.TestData.*;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTests extends BaseSetUp {

    private AccountApi accountApi;

    @BeforeEach
    public void setUp() {
        accountApi = container.provideAccountApi();
        divider();
    }

    @Tag("smoke")
    @ParameterizedTest
    @MethodSource(value = "data.DataProviders#dataUsersInformation")
    public void testGetUserInformation(UserType userType, User userTest) {
        info("Getting user information");
        User user = accountApi.getUser(userType);

        assertEquals(userTest, user, "FAILED: " + user.getUsername() + " information in the response does not match the expected data.");
        info("SUCCESS: " + user.getUsername() + " information in the response matches the expected data.");
    }

    @Tag("smoke")
    @ParameterizedTest
    @MethodSource(value = "data.DataProviders#dataUsers")
    public void testGetUserInformationWithoutToken(UserType userType) {
        info("Testing to get a user without token");
        Message messageResult = accountApi.getUserWithoutToken(userType);

        assertEquals(ERROR_UNAUTHORIZED_USER, messageResult, "FAILED: The error message data in the response when trying to get " + userType + " information does not match the expected data");
        info("SUCCESS: The error message data in the response when trying to get " + userType + " information matches the expected data.");
    }

    @Tag("regression")
    @Test
    public void testCreateNewUserSuccessfully() {
        info("Creating a new user");
        UserWithTypo newUser = accountApi.createNewRandomUser();

        assertEquals(NEW_USER_INF, newUser, "FAILED: The response body when creating a new user does not match the expected data");
        info("SUCCESS: The response body when creating a new user matches the expected data");
        info(newUser.getUsername());
        info(newUser.getUserId());
        info(String.valueOf(NEW_USER_INF));
    }

    @Tag("regression")
    @Test
    public void testDeleteUser() {
        info("Deleting a user");
        info("Precondition: Create a new user to delete it");
        UserWithTypo newUser = accountApi.createNewRandomUser();
        info("User created, proceeding to delete it");
        Response response = accountApi.deleteUser(DISPOSABLE_USER);

        assertTrue(response.getBody().asString().isEmpty(), "FAILED: The response body is not empty");
        info("SUCCESS: The response body is empty");
        info(newUser.getUsername());
        info(newUser.getUserId());
    }
}
