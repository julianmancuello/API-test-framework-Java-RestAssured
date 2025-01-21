package data;

import models.responses.Book;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.List;

import static common.Authentication.UserType;
import static common.Authentication.UserType.EMPTY_USER;
import static common.Authentication.UserType.MAIN_USER;
import static data.TestData.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class DataProviders {

    public static List<Book> dataBooks() {
        return ALL_BOOKS.getBooks();
    }

    public static List<UserType> dataUsers() {
        return Arrays.asList(MAIN_USER, EMPTY_USER);
    }

    public static List<Arguments> dataUsersInformation() {
        return Arrays.asList(
                arguments(MAIN_USER, MAIN_USER_INF),
                arguments(EMPTY_USER, EMPTY_USER_INF)
        );
    }
}
