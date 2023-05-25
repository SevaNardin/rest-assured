package utils;

import pojos.CreateUserRequest;

/**
 * @author NardinVN
 *
 * Генератор создания пользователя
 */
public class UserGenerator {
    public static CreateUserRequest createUser() {
        return CreateUserRequest.builder()
                .name("Seva")
                .job("driver")
                .build();
    }

}
