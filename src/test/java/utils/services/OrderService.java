package utils.services;

import io.qameta.allure.Step;
import io.restassured.http.Cookies;
import pojos.UserPojoFull;

import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * @author NardinVN
 */
public class OrderService extends RestService {
    @Override
    protected String getBasePath() {
        return "/orders";
    }

    public OrderService(Cookies cookies) {
        super(cookies);
    }

    @Step("Получение списка заказов")
    public List<UserPojoFull> getOrders() {
        return given()
                .spec(SPEC)
                .log().all()
                .when().get()
                .jsonPath()
                .getList("data", UserPojoFull.class);
    }
}
