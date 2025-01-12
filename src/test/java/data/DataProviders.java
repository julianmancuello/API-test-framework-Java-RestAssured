package data;

import models.Book;

import java.util.Arrays;
import java.util.List;

import static data.TestData.*;

public class DataProviders {

    public static List<Book> dataBooks() {
        return Arrays.asList(BOOK1, BOOK2, BOOK3, BOOK4, BOOK5, BOOK6, BOOK7, BOOK8);
    }
}
