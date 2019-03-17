package lab03.helpers.annotations;

import lab03.helpers.enumerators.Difficulty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DifficultyLevel {
    Difficulty level();
}
