package ru.otus.mock.helper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SoapHelper {
    public Response sendSoapRequest(String endpoint) {
        return RestAssured.given()
                .header("Content-Type", "application/soap+xml")
                .when()
                .get(endpoint);
    }
}
