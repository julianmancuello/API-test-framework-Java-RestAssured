package data;

import models.Book;

import java.util.Arrays;
import java.util.List;

import static data.TestData.*;

public class DataProviders {

    public static List<Book> dataBooks() {
        return ALL_BOOKS.getBooks();
    }
}
