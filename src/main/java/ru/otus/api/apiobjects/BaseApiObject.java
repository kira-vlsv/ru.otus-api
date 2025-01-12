package ru.otus.api.apiobjects;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import ru.otus.api.dto.BaseDTO;

import static io.restassured.RestAssured.given;
import static ru.otus.api.specs.BaseSpecification.baseRequestSpecification;
import static ru.otus.api.specs.BaseSpecification.baseResponseSpecification;

public class BaseApiObject {

    private Response response;

    protected Response requestGet(String path) {
        response = given(baseRequestSpecification(path))
                .get();
        checkCode(HttpStatus.SC_OK, response);
        return response;
    }

    protected Response requestPost(String path, BaseDTO dto) {
        response = given(baseRequestSpecification(path))
                .body(dto)
                .post();
        checkCode(HttpStatus.SC_OK, response);
        return response;
    }

    private void checkCode(Integer httpStatus, Response response) {
        response.then().spec(baseResponseSpecification(httpStatus));
    }
}
