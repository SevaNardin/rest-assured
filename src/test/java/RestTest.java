import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import pojos.UserPojo;
import pojos.UserPojoFull;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author NardinVN
 */
public class RestTest {

    @Test
    void getUser() {
        // получаем пользователя
        given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
                .when().get().then()
                .statusCode(200)
                .body("data.find{it.email=='george.bluth@reqres.in'}.first_name", equalTo("George"));
//                .body("data[0].email", equalTo("george.bluth@reqres.in"));
    }

    @Test
    void getUserList() {
        // получаем список емэйл пользователей
        List<String> emails =  given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON)
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
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON).log().all()
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
        List<UserPojoFull> users =  given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON).log().all()
                .when().get().then()
                .statusCode(200).extract()
                .jsonPath()
                .getList("data", UserPojoFull.class);

        System.out.println(users);

        assertThat(users).extracting(UserPojoFull::getEmail).contains("george.bluth@reqres.in");
    }

    @Test
    void createUser() {
        // получаем список емэйл пользователей
        // десереализация ответа в объект + lombok(геттеры и сеттеры)
        List<UserPojo> users =  given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON).log().all()
                .when().get().then()
                .statusCode(200).extract()
                .jsonPath()
                .getList("data", UserPojo.class);

        System.out.println(users);
    }
}
