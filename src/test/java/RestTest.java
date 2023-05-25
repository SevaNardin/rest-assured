import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

/**
 * @author NardinVN
 */
public class RestTest {
    @Test
    void getUser() {
        RestAssured.given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .when().get().then()
                .statusCode(200)
                .body("data[0].email", equalTo("george.bluth@reqres.in"));
    }
}
