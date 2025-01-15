package models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"userId", "username", "books"})
public class User {
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("username")
    private String username;
    @JsonProperty("books")
    private List<Book> books;
}
