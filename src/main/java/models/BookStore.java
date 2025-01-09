package models;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.List;

@Data
@JsonPropertyOrder({"books"})
public class BookStore {

    @JsonProperty("books")
    private List<Book> books;
}
