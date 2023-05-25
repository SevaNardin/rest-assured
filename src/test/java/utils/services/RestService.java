package utils.services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;

/**
 * @author NardinVN
 *
 * Базовый класс для сервисов
 */
public abstract class RestService {
    private static final String BASE_URL = "https://reqres.in/api";
    protected RequestSpecification SPEC;
    protected Cookies cookies;

    protected abstract String getBasePath();

    public RestService(Cookies cookies) {
        this.cookies = cookies;

        SPEC = new RequestSpecBuilder()
                .addCookies(cookies)
                .setBaseUri(BASE_URL)
                .setBasePath(getBasePath())
                .setContentType(ContentType.JSON)
                .build();
    }
}
