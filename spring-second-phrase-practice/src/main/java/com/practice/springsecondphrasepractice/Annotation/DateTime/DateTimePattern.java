package com.practice.springsecondphrasepractice.Annotation.DateTime;



import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE,FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = {DateTimeValidator.class})
public @interface DateTimePattern {

    String DEFAULT_PATTERN = "uuuu-MM-dd";

    String pattern() default DEFAULT_PATTERN;

    String message() default "{javax.validation.constraints.DateTimePattern.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}