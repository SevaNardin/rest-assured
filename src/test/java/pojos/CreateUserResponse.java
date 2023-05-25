package pojos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import utils.DateDeserializer;

import java.time.LocalDateTime;

/**
 * @author NardinVN
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserResponse {
    @JsonProperty
    private String name;

    @JsonProperty
    private String job;

    @JsonProperty
    private int id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss.SSSZ")
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDateTime createAdt;
}
