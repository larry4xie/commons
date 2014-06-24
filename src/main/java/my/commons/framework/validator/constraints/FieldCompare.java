package my.commons.framework.validator.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;

import my.commons.framework.validator.FieldCompareValidator;

/**
 * Field Compare
 * @author xiegang
 * @since 2014/6/24
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = FieldCompareValidator.class)
@NotNull
public @interface FieldCompare {
    String message() default "{javax.validation.constraints.FieldCompare.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    /**
     * @return The first field name
     */
    String first();

    /**
     * @return The second field name
     */
    String second();

    int compare() default 0;

    @Target({ TYPE, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        FieldCompare[] value();
    }
}
