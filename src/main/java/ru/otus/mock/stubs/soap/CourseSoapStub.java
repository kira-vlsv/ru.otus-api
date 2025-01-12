package ru.otus.mock.stubs.soap;

import com.github.tomakehurst.wiremock.WireMockServer;

import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

public class CourseSoapStub {

    public static void setupCourseStubs(WireMockServer wireMockServer) {
        try {
            String getAllCoursesResponse = Files.readString(Paths.get("src/main/resources/soap-responses/courses_response.xml"));
            wireMockServer.stubFor(get(urlPathEqualTo("/course/getAll"))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "application/soap+xml")
                            .withBody(getAllCoursesResponse)));

        } catch (Exception e) {
            throw new RuntimeException("Failed to load SOAP responses", e);
        }
    }
}
