package models;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"code", "message"})
public class ErrorMessage {

    @JsonProperty("code")
    private int code;
    @JsonProperty("message")
    private String message;
}
