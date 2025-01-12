package ru.otus.api;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.api.apiobjects.PetApiObject;
import ru.otus.api.dto.Pet;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.api.specs.BaseSpecification.baseRequestSpecification;

public class PetTest {

    private Faker faker = new Faker();
    private PetApiObject petApiObject = new PetApiObject();

    @Test
    @DisplayName("Check pet creation")
    void createPet() {
        var pet = Pet
                .builder()
                .id(faker.number().randomNumber())
                .name(faker.name().name())
                .status("available").build();
        var response = petApiObject.createPet(pet);

        assertThat(response)
                .as("Pet object data should be returned")
                .isEqualTo(pet);
    }

    @Test
    @DisplayName("Check invalid pet creation")
    void createPetShouldFailWithInvalidContentType() {
        String invalidPet = "{" +
                "\"id\": \"1\"}";
        var response = given(baseRequestSpecification("/"))
                .contentType(ContentType.HTML)
                .body(invalidPet)
                .post();

        assertThat(response.getStatusCode())
                .as("Unsupported media type")
                .isEqualTo(415);
    }

    @Test
    @DisplayName("Check pet with valid id is returned")
    void getPetById() {
        // Создаём питомца
        var pet = Pet
                .builder()
                .id(faker.number().randomNumber())
                .name(faker.name().name())
                .status("available").build();
        petApiObject.createPet(pet);

        // Проверяем, что питомец возвращается на get запрос
        var response = petApiObject.getPet(String.valueOf(pet.getId()));

        assertThat(response)
                .as("Pet object data should be returned")
                .isEqualTo(pet);
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
