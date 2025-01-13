package ru.otus.api.pet;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.api.specs.BaseSpecification.baseRequestSpecification;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PetNegativeTest {

    @Test
    @DisplayName("Check invalid pet creation")
    void createPetShouldFailWithInvalidContentType() {
        String invalidPet = "{\"id\": \"1\"}";
        var response = given(baseRequestSpecification("/"))
                .contentType(ContentType.HTML)
                .body(invalidPet)
                .post();

        assertThat(response.getStatusCode())
                .as("Unsupported media type")
                .isEqualTo(415);
    }

    @Test
    @DisplayName("Check get pet with invalid id return 404 code")
    void getPetByInvalidId() {
        var id = -1223243453L;
        var response = given(baseRequestSpecification(String.valueOf(id)))
                .get();

        assertThat(response.getStatusCode())
                .as("No pet was found")
                .isEqualTo(404);
    }
}
