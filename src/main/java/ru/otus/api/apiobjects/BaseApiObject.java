package ru.otus.api.apiobjects;

import static io.restassured.RestAssured.given;
import static ru.otus.api.specs.BaseSpecification.baseRequestSpecification;
import static ru.otus.api.specs.BaseSpecification.baseResponseSpecification;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;

public class BaseApiObject {

    private Response response;

    protected Response requestGet(String path) {
        response = given(baseRequestSpecification(path))
                .get();
        checkCode(HttpStatus.SC_OK, response);
        return response;
    }

    protected <T> Response requestPost(String path, T dto) {
        response = given(baseRequestSpecification(path))
                .body(dto)
                .post();
        checkCode(HttpStatus.SC_OK, response);
        return response;
    }

    protected Response requestDelete(String path) {
        response = given(baseRequestSpecification(path))
                .delete();
        checkCode(HttpStatus.SC_OK, response);
        return response;
    }

    private void checkCode(Integer httpStatus, Response response) {
        response.then().spec(baseResponseSpecification(httpStatus));
    }
}
