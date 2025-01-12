package ru.otus.mock.stubs.soap;

import com.github.tomakehurst.wiremock.WireMockServer;

public class SoapStubServer {
    private WireMockServer wireMockServer;

    public SoapStubServer() {
        wireMockServer = new WireMockServer(8080);
    }

    public void start() {
        wireMockServer.start();
        UserSoapStub.setupUserStubs(wireMockServer);
        CourseSoapStub.setupCourseStubs(wireMockServer);
    }

    public void stop() {
        wireMockServer.stop();
    }
}
