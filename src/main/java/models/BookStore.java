package models;

import com.fasterxml.jackson.annotation.*;

import java.util.List;

@JsonPropertyOrder({"books"})
public class BookStore {

    private List<Book> books;

    @JsonProperty("books")
    public List<Book> getBooks() {
        return books;
    }

    @JsonProperty("books")
    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
