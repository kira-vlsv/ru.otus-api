package ru.otus.api.apiobjects;

import io.qameta.allure.Step;
import ru.otus.api.dto.Pet;

public class PetApiObject extends BaseApiObject {

    @Step("Get pet with id [{petId}]")
    public Pet getPet(String id) {
        return requestGet(id).as(Pet.class);
    }

    @Step("Create pet")
    public Pet createPet(Pet pet) {
        return requestPost("/", pet).as(Pet.class);
    }

    @Step("Delete pet with id [{petId}]")
    public void deletePet(Long petId) {
        requestDelete(String.format("/%d", petId));
    }
}
