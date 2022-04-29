package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class RegresinTests {

    @Test
    void successfullRegister() {
        given().body("{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}")
                .contentType(ContentType.JSON)
        .when().post("https://reqres.in/api/login")
                .then().statusCode(200).body("token", is("QpwL5tke4Pnpja7X4"));
    }
}
