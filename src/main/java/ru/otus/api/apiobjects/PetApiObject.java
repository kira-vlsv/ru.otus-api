package ru.otus.api.apiobjects;

import ru.otus.api.dto.Pet;

public class PetApiObject extends BaseApiObject {

    public Pet getPet(String id) {
        return requestGet(id).as(Pet.class);
    }

    public Pet createPet(Pet pet) {
        return requestPost("/", pet).as(Pet.class);
    }
}
