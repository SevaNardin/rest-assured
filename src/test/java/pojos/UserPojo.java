package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

/**
 * @author NardinVN
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPojo {
    // модель данных
    // игнорируеем поля которые не указаны в моделе данных, иначе упадет
    @JsonProperty("id")
    private int id;

    @JsonProperty("email")
    private String email;
}
