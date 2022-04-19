package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static listeners.CustomAllureListener.withCustomTemplates;
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
}
