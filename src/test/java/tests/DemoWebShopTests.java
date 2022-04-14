package tests;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DemoWebShopTests {

    @Test
    void addToCartAsNewUserTest() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("product_attribute_72_5_18=53" +
                        "&product_attribute_72_6_19=54" +
                        "&product_attribute_72_3_20=57" +
                        "&addtocart_72.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your " +
                        "<a href=\"/cart\">shopping cart</a>"))
                .body("updatetopcartsectionhtml", is("(1)"));
    }

    @Test
    void addToCartWithCookieTest() {
        Integer cartSize = 0;

        ValidatableResponse response =
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .cookie("Nop.customer=88f590c6-59e9-4a55-b243-7395b35f0ce2;")
                        .body("product_attribute_72_5_18=53" +
                                "&product_attribute_72_6_19=54" +
                                "&product_attribute_72_3_20=57" +
                                "&addtocart_72.EnteredQuantity=1")
                        .when()
                        .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("success", is(true))
                        .body("message", is("The product has been added to your " +
                                "<a href=\"/cart\">shopping cart</a>"));

        // todo
//        assertThat(response.extract().path("updatetopcartsectionhtml").toString())
//                .body("updatetopcartsectionhtml", is("(43)"));

    }



    /*
    HttpResponse<String> response = Unirest.post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
  .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
  .header("Cookie", "Nop.customer=4a09ead1-437c-4294-96d5-41571fffae21; NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=72; ARRAffinity=1818b4c81d905377ced20e7ae987703a674897394db6e97dc1316168f754a687; __utmc=78382081; __utmz=78382081.1649868567.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utma=78382081.7867083.1649868567.1649872260.1649959367.3; __utmt=1; __atuvc=6%7C15; __atuvs=625861c636461107001; __utmb=78382081.2.10.1649959367; ARRAffinity=1818b4c81d905377ced20e7ae987703a674897394db6e97dc1316168f754a687; Nop.customer=4a09ead1-437c-4294-96d5-41571fffae21")
  .body("product_attribute_72_5_18=53&product_attribute_72_6_19=54&product_attribute_72_3_20=57&addtocart_72.EnteredQuantity=1")
  .asString();
     */
}
