package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NardinVN
 */
@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class CreateUserRequest {
    // используем паттерн создания builder
    @JsonProperty
    private String name;
    @JsonProperty
    private String job;
}
