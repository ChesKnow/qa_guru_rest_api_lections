package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.SetCredentialsData;
import models.getGeneratedTokenData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static listeners.CustomAllureListener.withCustomTemplates;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

public class BookStoreTests {

    /*
    Sosiska
    vTeste
     */


    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://demoqa.com/";
    }

    @Test
    void getBooksTest() {
        RestAssured.get("BookStore/v1/Books")
                .then()
                .body("books", hasSize(greaterThan(0)));
    }

    @Test
    void getBooksWithAllLogsTest() {
        given()
                .log().all()
                .when()
                .get("BookStore/v1/Books")
                .then()
                .log().all()
                .body("books", hasSize(greaterThan(0)));
    }

    @Test
    void getBooksWithSomeLogsTest() {
        given()
                .log().uri()
                .log().body()
                .when()
                .get("BookStore/v1/Books")
                .then()
                .log().status()
                .log().body()
                .body("books", hasSize(greaterThan(0)));
    }

    @Test
    void generateTokenTest() {

        String data = "{   \"userName\": \"alex\",   \"password\": \"asdsad#frew_DFS2\" }";

        given()
                .contentType(ContentType.JSON)
                .body(data)
                .log().uri()
                .log().body()
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .log().status()
                .log().body()
                .body("status", is("Success"))
                .body("result", is("User authorized successfully."))
                .body("token.size()", (greaterThan(10)));
    }

    @Test
    void generateTokenWithAllureListenerTest() {

        String data = "{   \"userName\": \"alex\",   \"password\": \"asdsad#frew_DFS2\" }";

        RestAssured.filters(new AllureRestAssured());

        given()
                .contentType(ContentType.JSON)
                .body(data)
                .log().uri()
                .log().body()
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .log().status()
                .log().body()
                .body("status", is("Success"))
                .body("result", is("User authorized successfully."))
                .body("token.size()", (greaterThan(10)));
    }

    @Test
    void generateTokenWithCustomAllureListenerTest() {

        String data = "{   \"userName\": \"alex\",   \"password\": \"asdsad#frew_DFS2\" }";


        given().filter(withCustomTemplates())
                .contentType(ContentType.JSON)
                .body(data)
                .log().uri()
                .log().body()
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .log().status()
                .log().body()
                .body("status", is("Success"))
                .body("result", is("User authorized successfully."))
                .body("token.size()", (greaterThan(10)));
    }

    @Test
    void geеTokenTest() {

        String data = "{   \"userName\": \"alex\",   \"password\": \"asdsad#frew_DFS2\" }";

        String token =
                given()
                        .contentType(ContentType.JSON)
                        .body(data)
                        .when()
                        .post("/Account/v1/GenerateToken")
                        .then()
                        .extract().path("token");

        System.out.println(token);
    }

    @Test
    void JsonSchemaCheckTest() {

        String data = "{   \"userName\": \"alex\",   \"password\": \"asdsad#frew_DFS2\" }";


        given().filter(withCustomTemplates())
                .contentType(ContentType.JSON)
                .body(data)
                .log().uri()
                .log().body()
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .log().status()
                .log().body()
                .body(matchesJsonSchemaInClasspath("schemas/GenerateToken_response_scheme.json"))
                .body("status", is("Success"))
                .body("result", is("User authorized successfully."))
                .body("token.size()", (greaterThan(10)));
    }

    @Test
    void ModelsTest() {

        SetCredentialsData scd = new SetCredentialsData();
        scd.setUserName("alex");
        scd.setPassword("asdsad#frew_DFS2");

        getGeneratedTokenData ggtd =
        given().filter(withCustomTemplates())
                .contentType(ContentType.JSON)
                .body(scd)
                .log().uri()
                .log().body()
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .log().status()
                .log().body()
                .body(matchesJsonSchemaInClasspath("schemas/GenerateToken_response_scheme.json"))
                .extract().as(getGeneratedTokenData.class);

        assertThat(ggtd.getStatus()).isEqualTo("Success");
        assertThat(ggtd.getResult()).isEqualTo("User authorized successfully.");
        assertThat(ggtd.getExpires()).hasSizeGreaterThan(10);
        assertThat(ggtd.getToken()).hasSizeGreaterThan(10).startsWith("eyJ");

    }
}
