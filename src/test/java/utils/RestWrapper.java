package utils;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import pojos.CreateUserRequest;
import pojos.CreateUserResponse;
import pojos.UserLogin;
import pojos.UserPojoFull;
import utils.services.OrderService;
import utils.services.UserService;

import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * @author NardinVN
 *
 * Авторизация
 */
public class RestWrapper {

    private static final String BASE_URL = "https://reqres.in/api";
    private static RequestSpecification SPEC;
    private Cookies cookies;
    public UserService userService;
    public OrderService orderService;

    private RestWrapper(Cookies cookies) {
        this.cookies = cookies;

        userService = new UserService(cookies);
        orderService = new OrderService(cookies);

        SPEC = new RequestSpecBuilder()
                .addCookies(cookies)
                .setBaseUri(BASE_URL)
                .setBasePath("/users")
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RestWrapper loginAs(String login, String password) {
        Cookies cookies = given()
                .baseUri(BASE_URL)
                .basePath("/login")
                .contentType(ContentType.JSON)
                .body(new UserLogin(login, password))
                .post()
                .getDetailedCookies();

        return new RestWrapper(cookies);
    }
//    UserService userService в тестах указываем конкретный сервис который нужен
//    @Step("Сохранение результатов в локальную переменную")
//    public CreateUserResponse createUser(CreateUserRequest rq) {
//        return given().spec(SPEC).body(rq).post().as(CreateUserResponse.class);
//    }
//
//    @Step("Получение списка пользователей")
//    public List<UserPojoFull> getUsers() {
//        return given()
//                .spec(SPEC)
//                .log().all()
//                .when().get()
//                .jsonPath()
//                .getList("data", UserPojoFull.class);
//    }

}
