package com.hepsiburada.steps;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.thoughtworks.gauge.Step;
import io.restassured.http.ContentType;

import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.given;


public class ClientsSteps extends BaseSteps {

    @Step("Returns for a client library options")
    public void getClientLibraryOptions() {
        response = given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .log()
                .all()
                .when()
                .request("GET", "/api/gen/clients")
                .prettyPeek()
                .then()
                .extract()
                .response();
    }

    @Step("Generates a client library for <java>")
    public void generateClientLibrary(String language) {
        response = given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .body(readSwaggerConfigFile().toString())
                .log()
                .all()
                .when()
                .request("POST", "/api/gen/clients/" + language)
                .prettyPeek()
                .then()
                .extract()
                .response();
    }

    private JsonObject readSwaggerConfigFile() {
        try {
            return JsonParser.parseReader(new FileReader("src/test/resources/swagger-config.json")).getAsJsonObject();
        } catch (IOException e) {
            Thread.currentThread().interrupt();
            return new JsonObject();
        }
    }
}
