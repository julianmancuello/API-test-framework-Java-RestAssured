package models;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"isbn", "title", "subtitle", "author", "publishDate", "publisher", "pages", "description", "website"})
public class Book {
    @JsonProperty("isbn")
    private String isbn;
    @JsonProperty("title")
    private String title;
    @JsonProperty("subTitle")
    private String subtitle;
    @JsonProperty("author")
    private String author;
    @JsonProperty("publish_date")
    private String publishDate;
    @JsonProperty("publisher")
    private String publisher;
    @JsonProperty("pages")
    private int pages;
    @JsonProperty("description")
    private String description;
    @JsonProperty("website")
    private String website;
}
