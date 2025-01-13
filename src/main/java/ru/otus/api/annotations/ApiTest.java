package ru.otus.api.annotations;

import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.api.extentions.ApiExtension;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ExtendWith(ApiExtension.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ApiTest {
}
