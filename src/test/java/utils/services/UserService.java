package utils.services;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import pojos.CreateUserRequest;
import pojos.CreateUserResponse;
import pojos.UserPojoFull;

import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * @author NardinVN
 */
public class UserService extends RestService {

    @Override
    protected String getBasePath() {
        return "/users";
    }

    public UserService(Cookies cookies) {
        super(cookies);
    }

    @Step("Сохранение результатов в локальную переменную")
    public CreateUserResponse createUser(CreateUserRequest rq) {
        return given().spec(SPEC).body(rq).post().as(CreateUserResponse.class);
    }

    @Step("Получение списка пользователей")
    public List<UserPojoFull> getUsers() {
        return given()
                .spec(SPEC)
                .log().all()
                .when().get()
                .jsonPath()
                .getList("data", UserPojoFull.class);
    }
}
