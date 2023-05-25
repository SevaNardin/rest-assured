package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author NardinVN
 */
@Data
@AllArgsConstructor
public class UserLogin {
    @JsonProperty
    private String email;
    @JsonProperty
    private String password;
}
