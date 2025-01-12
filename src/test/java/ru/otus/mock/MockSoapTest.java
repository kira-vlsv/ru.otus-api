package ru.otus.mock;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.otus.mock.helper.SoapHelper;
import ru.otus.mock.stubs.soap.SoapStubServer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MockSoapTest {

    private static SoapStubServer stubServer;
    private static SoapHelper soapHelper;

    @BeforeAll
    public static void setup() {
        stubServer = new SoapStubServer();
        stubServer.start();
        soapHelper = new SoapHelper();
        RestAssured.baseURI = "http://localhost:8080";
    }

    @AfterAll
    public static void teardown() {
        stubServer.stop();
    }

    @Test
    public void testGetUserScoreById() throws IOException {
        var requestBody = Files.readString(Paths.get("src/main/resources/soap-responses/user_score_response.xml"));
        var response = soapHelper.sendSoapRequest("/user/get/1");
        assertEquals(200, response.getStatusCode());
        assertEquals(response.getBody().asString(), requestBody);
    }

    @Test
    public void testGetAllUsers() throws IOException {
        var requestBody = Files.readString(Paths.get("src/main/resources/soap-responses/users_response.xml"));
        var response = soapHelper.sendSoapRequest("/user/getAll");
        assertEquals(200, response.getStatusCode());
        assertEquals(response.getBody().asString(), requestBody);
    }

    @Test
    public void testGetAllCourses() throws IOException {
        var requestBody = Files.readString(Paths.get("src/main/resources/soap-responses/courses_response.xml"));
        var response = soapHelper.sendSoapRequest("/course/getAll");
        assertEquals(200, response.getStatusCode());
        assertEquals(response.getBody().asString(), requestBody);
    }
}
