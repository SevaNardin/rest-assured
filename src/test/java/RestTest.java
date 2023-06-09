import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojos.CreateUserRequest;
import pojos.CreateUserResponse;
import pojos.UserPojo;
import pojos.UserPojoFull;
import steps.UserSteps;
import utils.RestWrapper;
import utils.UserGenerator;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author NardinVN
 */
public class RestTest {

    private static RestWrapper api;

    @BeforeAll
    public static void prepareClient() {
        api = RestWrapper.loginAs("eve.holt@reqres.in", "cityslicka");
    }

    private static final RequestSpecification SPEC =
            new RequestSpecBuilder()
                    .setBaseUri("https://reqres.in/api")
                    .setBasePath("/users")
                    .setContentType(ContentType.JSON)
                    .build();

    @Test
    void getUser() {
        // получаем пользователя
        given()
                .spec(SPEC)
                .when().get().then()
                .statusCode(200)
                .body("data.find{it.email=='george.bluth@reqres.in'}.first_name", equalTo("George"));
//                .body("data[0].email", equalTo("george.bluth@reqres.in"));
    }

    @Test
    void getUserList() {
        // получаем список емэйл пользователей
        List<String> emails =  given()
                .spec(SPEC)
                .when().get().then()
                .statusCode(200)
                .extract().jsonPath().getList("data.email");

        System.out.println(emails.stream().toList());

        //десереализация ответа в объект
    }

    @Test
    void getUserListPojo() {
        // получаем список емэйл пользователей
        // десереализация ответа в объект + lombok(геттеры и сеттеры)
        List<UserPojo> users =  given()
                .spec(SPEC)
                .log().all()
                .when().get().then()
                .statusCode(200).extract()
                .jsonPath()
                .getList("data", UserPojo.class);

        System.out.println(users);
    }

    @Test
    void getUserListPojoFull() {
        // получаем список емэйл пользователей
        // десереализация ответа в объект + lombok(геттеры и сеттеры)
//        List<UserPojoFull> users = UserSteps.getUsers();
//        assertThat(users).extracting(UserPojoFull::getEmail).contains("george.bluth@reqres.in");

        assertThat(api.userService.getUsers()).extracting(UserPojoFull::getEmail).contains("george.bluth@reqres.in");

        System.out.println(api.userService.getUsers());
    }

    @Test
    void createUser() {
        // создаем запрос на создание юзера 1 способ
//        CreateUserRequest rq = new CreateUserRequest();
//        rq.setJob("driver");
//        rq.setName("Seva");

        // отправляем запрос в виде json
//        CreateUserResponse rs = given()
//                .spec(SPEC)
//                .log().all()
//                .body(rq)
//                .when().post()
//                .then()
//                .extract().as(CreateUserResponse.class);

        // 2 способ
//        CreateUserRequest rq = CreateUserRequest
//                .builder()
//                .name("Seva")
//                .job("driver")
//                .build();

        // 3 способ
//        CreateUserRequest rq = UserGenerator.createUser();
//
//        UserSteps userSteps = new UserSteps();
//        CreateUserResponse rs = userSteps.createUser(rq);

        // 4
        CreateUserRequest rq = UserGenerator.createUser();
        CreateUserResponse rs = api.userService.createUser(rq);

        // проверяем то что указали
        assertThat(rs)
                .isNotNull()
                .extracting(CreateUserResponse::getName)
                .isEqualTo(rs.getName());
    }
}
