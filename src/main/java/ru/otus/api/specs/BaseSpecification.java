package ru.otus.api.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseSpecification {

    private static final String BASE_URL = System.getProperty("baseUrl");

    public static RequestSpecification baseRequestSpecification(String path) {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .setBasePath(path)
                .build();
    }

    public static ResponseSpecification baseResponseSpecification(Integer statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();
    }
}
