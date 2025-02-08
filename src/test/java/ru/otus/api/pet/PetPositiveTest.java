package ru.otus.api.pet;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.api.annotations.ApiTest;
import ru.otus.api.apiobjects.PetApiObject;
import ru.otus.api.dto.Pet;

@ApiTest
@Feature("Positive tests /pet")
public class PetPositiveTest {

    @Inject
    private PetApiObject petApiObject;

    @Inject
    private Faker faker;

    private ThreadLocal<Long> petId = new ThreadLocal<>();

    @AfterEach
    public void teardown() {
        if (petId.get() != null) {
            petApiObject.deletePet(petId.get());
            petId.remove();
        }
    }

    @Test
    @AllureId("3")
    @DisplayName("Check pet creation")
    void createPet() {
        var id = faker.number().randomNumber();
        petId.set(id);
        var pet = Pet
                .builder()
                .id(id)
                .name(faker.name().name())
                .status("available").build();
        var response = petApiObject.createPet(pet);

        Allure.step("Verify pet data is returned", () -> assertThat(response)
                .as("Pet object data should be returned")
                .isEqualTo(pet)
        );
    }

    @Test
    @AllureId("4")
    @DisplayName("Check pet with valid id is returned")
    void getPetById() {
        // Создаём питомца
        var id = faker.number().randomNumber();
        petId.set(id);
        var pet = Pet
                .builder()
                .id(id)
                .name(faker.name().name())
                .status("available").build();
        petApiObject.createPet(pet);

        // Проверяем, что питомец возвращается на get запрос
        var response = petApiObject.getPet(String.valueOf(pet.getId()));

        Allure.step("Verify pet data is returned", () -> assertThat(response)
                .as("Pet object data should be returned")
                .isEqualTo(pet)
        );
    }

}
