package ru.otus.mock.helper;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class HttpHelper {
    public Response get(String endpoint) {
        return given().when().get(endpoint);
    }
}
