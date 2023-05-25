package steps;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojos.UserPojoFull;

import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * @author NardinVN
 *
 * Класс описывает бизнес действия
 */
public class UserSteps {

    private static final RequestSpecification SPEC =
            new RequestSpecBuilder()
                    .setBaseUri("https://reqres.in/api")
                    .setBasePath("/users")
                    .setContentType(ContentType.JSON)
                    .build();

    public static List<UserPojoFull> getUsers() {
        return given()
                .spec(SPEC)
                .log().all()
                .when().get().then()
                .statusCode(200).extract()
                .jsonPath()
                .getList("data", UserPojoFull.class);

    }

}
