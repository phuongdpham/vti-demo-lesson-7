package vn.edu.vtiacademy.demolesson7.controller.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PageableValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(PageableCheck.List.class)
public @interface PageableCheck {
    String message() default "{vn.edu.vtiacademy.validation.constraints.PageableCheck.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int minPage() default 0;

    int minSize() default 4;
    int maxSize() default 20;

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        PageableCheck[] value();
    }
}
