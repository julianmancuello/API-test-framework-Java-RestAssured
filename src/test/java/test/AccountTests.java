package test;

import clients.AccountApi;
import models.responses.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import setup.BaseSetUp;

import static common.Authentication.UserType.*;
import static common.LoggerUtils.divider;
import static common.LoggerUtils.info;

public class AccountTests extends BaseSetUp {

    private AccountApi accountApi;

    @BeforeEach
    public void setUp() {
        accountApi = container.provideAccountApi();
        divider();
    }

    @Test
    public void testAccount() {
        User user = accountApi.getUser(MAIN_USER);
        info(user.getUserId());
        info(user.getUsername());
        info(user.getBooks().toString());
    }
}
