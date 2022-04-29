package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Lection17 {

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://reqres.in/";
    }

    @Test
    void unsuccessfulLoginApiTest() {
        given()
                .body("{\"email\": \"peter@klaven\"}").contentType(ContentType.JSON)
                .when().post("api/login")
                .then().statusCode(400).body("error", is("Missing password"));
    }
    @Test
    void successfullLoginApiTest() {
        given().body("{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}")
                .contentType(ContentType.JSON)
                .when().post("api/login")
                .then().statusCode(200).body("token", is("QpwL5tke4Pnpja7X4"));
    }
    @Test
    void unsuccessfullRegistrationApiTest() {
        given()
                .body("{\"email\": \"sydney@fife\"}").contentType(ContentType.JSON)
                .when().post("api/register")
                .then().statusCode(400).body("error", is("Missing password"));
    }
    @Test
    void listResourceApiTest() {

        get("api/unknown")
                .then().statusCode(200).body("data.id", equalTo(List.of(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void UserApiTest() {
        given().body("{\"name\": \"morpheus\",\n" +
                        "\"job\": \"leader\"}").contentType(ContentType.JSON)
                .when().post("api/users")
                .then().statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }
}
