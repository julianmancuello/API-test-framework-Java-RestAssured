package models;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

@Data
@JsonPropertyOrder({"code", "message"})
public class ErrorMessage {

    @JsonProperty("code")
    private int code;
    @JsonProperty("message")
    private String message;
}
