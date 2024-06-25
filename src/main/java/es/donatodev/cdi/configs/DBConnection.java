package es.donatodev.cdi.configs;

import jakarta.inject.Qualifier;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, METHOD, PARAMETER})
public @interface DBConnection {
}
