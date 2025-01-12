package data;

import models.Book;

import java.util.List;

import static data.TestData.ALL_BOOKS;

public class DataProviders {

    public static List<Book> dataBooks() {
        return ALL_BOOKS.getBooks();
    }
}
