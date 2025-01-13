package ru.otus.api.modules;

import com.github.javafaker.Faker;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import jakarta.inject.Singleton;
import ru.otus.api.apiobjects.PetApiObject;

public class GuiceModules extends AbstractModule {

    @Singleton
    @Provides
    public PetApiObject getPetApiObject() {
        return new PetApiObject();
    }

    @Singleton
    @Provides
    public Faker getFaker() {
        return new Faker();
    }
}
