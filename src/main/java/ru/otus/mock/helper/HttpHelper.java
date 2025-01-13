package ru.otus.mock.helper;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class HttpHelper {
    public Response get(String endpoint) {
        return given().when().get(endpoint);
    }
}
