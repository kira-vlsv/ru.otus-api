package ru.otus.mock.stubs.rest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import ru.otus.mock.model.Course;
import java.util.List;

public class CourseStub {
    public static void setupCourseStubs(WireMockServer wireMockServer) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            String coursesJson = objectMapper.writeValueAsString(List.of(
                    new Course("QA java", 15000),
                    new Course("Java", 12000)));
            wireMockServer.stubFor(get(urlEqualTo("/course/get/all")).willReturn(
                    aResponse()
                            .withStatus(201)
                            .withHeader("Content-Type", "application/json")
                            .withBody(coursesJson)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
