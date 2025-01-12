package ru.otus.api.mock.helper;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class HttpHelper {
    public Response get(String endpoint) {
        return given().when().get(endpoint);
    }

    public Response post(String endpoint, Object body) {
        return given().body(body).when().post(endpoint);
    }
}
