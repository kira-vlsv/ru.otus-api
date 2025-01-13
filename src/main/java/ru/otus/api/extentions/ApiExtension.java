package ru.otus.api.extentions;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import ru.otus.api.modules.GuiceModules;

public class ApiExtension implements BeforeEachCallback {

    private Injector injector;

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        extensionContext.getTestInstance()
                .ifPresent(instance -> {
                    injector = Guice.createInjector(new GuiceModules());
                    injector.injectMembers(instance);
                });
    }
}
