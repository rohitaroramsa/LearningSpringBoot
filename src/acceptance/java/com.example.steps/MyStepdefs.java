package com.example.steps;


import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class MyStepdefs {

    static {
        RestAssured.baseURI = "http://localhost:8080/shop";
    }

    private Response response;
    Map<String, Object> requestBody = new HashMap<>();


    @Given("^baseurl and endpoints$")
    public void baseurlAndEndpoints() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("baseurl is: " + RestAssured.baseURI);
    }

    @When("^we add a product$")
    public void weAddAProduct() {
        requestBody.put("productName", "TV");
        requestBody.put("productCost", 159.66);
    }

    @Then("^we get success$")
    public void weGetSuccess() {
        String endpoint = "products";
        response = given()
//                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(endpoint)
                .then()
//                .statusCode(201)
                .extract().response();
    }

    @And("^we can view all products$")
    public void weCanViewAllProducts() {
        String endpoint = "products/all";
        response = given()
                .header("Content-Type", "application/json")
                .get(endpoint)
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("Pretty printed response for all product:");
        response.prettyPrint();
        System.out.println("as String works fine too:");
        System.out.println(response.asString());

    }

    @And("^we can delete a product$")
    public void weCanDeleteAProduct() {

        // path param will replace the placeholder, so we must provide a placeholder for path param
        String uri = "products/{productName}";

        response = given()
                .header("Content-Type", "application/json")
                .pathParam("productName","TV")
                .delete(uri)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
}
