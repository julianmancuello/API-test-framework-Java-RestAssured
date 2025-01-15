package data;

import io.restassured.specification.Argument;
import models.responses.Book;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.List;

import static common.Authentication.UserType.EMPTY_USER;
import static common.Authentication.UserType.MAIN_USER;
import static data.TestData.*;
import static org.junit.jupiter.params.provider.Arguments.*;

public class DataProviders {

    public static List<Book> dataBooks() {
        return ALL_BOOKS.getBooks();
    }

    public static List<Arguments> dataUsers() {
        return Arrays.asList(arguments(MAIN_USER, MAIN_USER_INF), arguments(EMPTY_USER, EMPTY_USER_INF));
    }
}
