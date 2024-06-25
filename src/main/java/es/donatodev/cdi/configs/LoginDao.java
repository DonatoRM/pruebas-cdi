package es.donatodev.cdi.configs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Stereotype;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Stereotype
@ApplicationScoped
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD})
public @interface LoginDao {
}
