package utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import pojos.UserLogin;

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

    private RestWrapper(Cookies cookies) {
        this.cookies = cookies;

        SPEC = new RequestSpecBuilder()
                .addCookies(cookies)
                .setBaseUri("https://reqres.in/api")
                .setBasePath("/users")
                .setContentType(ContentType.JSON)
                .build();
    }

    private static RestWrapper loginAs(String login, String password) {
        Cookies cookies = given()
                .spec(SPEC)
                .baseUri(BASE_URL)
                .basePath("/login")
                .contentType(ContentType.JSON)
                .body(new UserLogin(login, password))
                .post()
                .getDetailedCookies();

        return new RestWrapper(cookies);

    }

}
