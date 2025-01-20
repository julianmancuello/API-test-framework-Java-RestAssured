package models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"userId", "collectionOfIsbns"})
public class AddBooks {
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("collectionOfIsbns")
    private List<Isbn> collectionOfIsbns;
}
