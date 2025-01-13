package test;

import clients.AccountApi;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import setup.BaseSetUp;

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
        Response response = accountApi.getUser();
        info(response.asPrettyString());
    }
}
