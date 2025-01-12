package ru.otus.api.mock.stubs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import ru.otus.api.mock.model.User;
import ru.otus.api.mock.model.UserScore;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;

public class UserStub {

    public static void setupUserStubs(WireMockServer wireMockServer) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // User object for /user/get/1
            UserScore userScore = new UserScore("Test user", 78);
            String userJson = objectMapper.writeValueAsString(userScore);
            wireMockServer.stubFor(get(urlPathMatching("/user/get/\\d+")).willReturn(
                    aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "application/json")
                            .withBody(userJson)));

            // User list for /user/get/all
            String usersJson = objectMapper.writeValueAsString(List.of(
                    new User("Test user", "QA", "test@test.test", 23)));
            wireMockServer.stubFor(get(urlEqualTo("/user/get/all")).willReturn(
                    aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "application/json")
                            .withBody(usersJson)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
