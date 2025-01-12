package ru.otus.mock;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.otus.mock.helper.HttpHelper;
import ru.otus.mock.model.Course;
import ru.otus.mock.model.User;
import ru.otus.mock.model.UserScore;
import ru.otus.mock.stubs.rest.StubServer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MockRestTest {

    private static StubServer stubServer;
    private static HttpHelper httpHelper;

    @BeforeAll
    public static void setup() {
        stubServer = new StubServer();
        stubServer.start();
        httpHelper = new HttpHelper();
        RestAssured.baseURI = "http://localhost:8080";
    }

    @AfterAll
    public static void teardown() {
        stubServer.stop();
    }

    @Test
    public void testGetUserById() {
        var response = httpHelper.get("/user/get/1");
        assertEquals(200, response.statusCode());
        var expectedUserScore = new UserScore("Test user", 78);
        var actualUserScore = response.as(UserScore.class);
        assertEquals(expectedUserScore, actualUserScore, "User score");
    }

    @Test
    public void testGetAllUsers() {
        var response = httpHelper.get("/user/get/all");
        var actualUsers = List.of(response.as(User[].class));
        var expectedUsers = List.of(new User("Test user", "QA", "test@test.test", 23));
        assertEquals(200, response.statusCode());
        assertEquals(expectedUsers, actualUsers, "Users list");
    }

    @Test
    public void testGetAllCourses() {
        var response = httpHelper.get("/course/get/all");
        var expectedCourses = List.of(
                new Course("QA java", 15000),
                new Course("Java", 12000)
        );
        var actualCourses = List.of(response.as(Course[].class));
        assertEquals(201, response.statusCode());
        assertEquals(expectedCourses, actualCourses, "Courses list");

    }
}
