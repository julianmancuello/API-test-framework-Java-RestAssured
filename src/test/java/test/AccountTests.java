package test;

import clients.AccountApi;
import common.Authentication;
import models.responses.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import setup.BaseSetUp;

import static common.Authentication.*;
import static common.Authentication.UserType.*;
import static common.LoggerUtils.divider;
import static common.LoggerUtils.info;
import static data.TestData.MAIN_USER_INF;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTests extends BaseSetUp {

    private AccountApi accountApi;

    @BeforeEach
    public void setUp() {
        accountApi = container.provideAccountApi();
        divider();
    }

    @ParameterizedTest
    @MethodSource(value = "data.DataProviders#dataUsers")
    public void testGetUserInformation(UserType userType, User userTest) {
        info("Getting user's information");
        User user = accountApi.getUser(userType);

        assertEquals(userTest, user, "FAILED: " + user.getUsername() + "'s information in the response does not match the expected data.");
        info("SUCCESS: " + user.getUsername() + "'s information in the response matches the expected data.");
    }
}
