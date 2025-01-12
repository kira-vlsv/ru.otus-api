package ru.otus.mock.stubs.soap;

import com.github.tomakehurst.wiremock.WireMockServer;

import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;

public class UserSoapStub {

    public static void setupUserStubs(WireMockServer wireMockServer) {
        try {
            String getUserScoreByIdResponse = Files.readString(Paths.get("src/main/resources/soap-responses/user_score_response.xml"));
            wireMockServer.stubFor(get(urlPathMatching("/user/get/\\d+"))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "application/soap+xml")
                            .withBody(getUserScoreByIdResponse)));

            String getAllUsersResponse = Files.readString(Paths.get("src/main/resources/soap-responses/users_response.xml"));
            wireMockServer.stubFor(get(urlPathEqualTo("/user/getAll"))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "application/soap+xml")
                            .withBody(getAllUsersResponse)));

        } catch (Exception e) {
            throw new RuntimeException("Failed to load SOAP responses", e);
        }
    }
}
