package ru.otus.api.mock.stubs;

import com.github.tomakehurst.wiremock.WireMockServer;

public class StubServer {

    private WireMockServer wireMockServer;

    public StubServer() {
        wireMockServer = new WireMockServer(8080);
    }

    public void start() {
        wireMockServer.start();
        UserStub.setupUserStubs(wireMockServer);
        CourseStub.setupCourseStubs(wireMockServer);
    }

    public void stop() {
        wireMockServer.stop();
    }
}
