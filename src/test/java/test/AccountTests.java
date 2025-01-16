package test;

import clients.AccountApi;
import models.responses.ErrorMessage;
import models.responses.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import setup.BaseSetUp;

import static common.Authentication.*;
import static common.LoggerUtils.divider;
import static common.LoggerUtils.info;
import static common.Utils.generateRandomPassword;
import static common.Utils.generateRandomUser;
import static data.TestData.ERROR_UNAUTHORIZED_USER;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        ErrorMessage errorMessage = accountApi.getUserWithoutToken(userType);

        assertEquals(ERROR_UNAUTHORIZED_USER, errorMessage, "FAILED: The error message data in the response when trying to get " + userType + " information does not match the expected data");
        info("SUCCESS: The error message data in the response when trying to get " + userType + " information matches the expected data.");
    }

    @Test
    public void testNewUser() {
        info(generateRandomUser());
        info(generateRandomPassword());
    }
}
