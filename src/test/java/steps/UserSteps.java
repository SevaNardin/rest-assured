package steps;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojos.CreateUserRequest;
import pojos.CreateUserResponse;
import pojos.UserPojo;
import pojos.UserPojoFull;

import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * @author NardinVN
 *
 * Класс описывает бизнес действия
 */
public class UserSteps {

    private CreateUserResponse user;

    private static final RequestSpecification SPEC =
            new RequestSpecBuilder()
                    .setBaseUri("https://reqres.in/api")
                    .setBasePath("/users")
                    .setContentType(ContentType.JSON)
                    .build();

    @Step("Получение списка пользователей")
    public static List<UserPojoFull> getUsers() {
        return given()
                .spec(SPEC)
                .log().all()
                .when().get().then()
                .statusCode(200).extract()
                .jsonPath()
                .getList("data", UserPojoFull.class);
    }

    @Step("Сохранение результатов в локальную переменную")
    public CreateUserResponse createUser(CreateUserRequest rq) {
        return user = given().spec(SPEC).body(rq).post().as(CreateUserResponse.class);
    }

    @Step("Получить последнего созданного пользотвателя")
    public UserPojoFull getUser() {
        return given().spec(SPEC).get("/"+ user.getId()).as(UserPojoFull.class);
    }

}
