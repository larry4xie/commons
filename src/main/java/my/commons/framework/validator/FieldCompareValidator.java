package my.commons.framework.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import my.commons.framework.validator.constraints.FieldCompare;

import org.apache.commons.lang3.reflect.FieldUtils;

/**
 * FieldCompare Validator
 * @author xiegang
 * @since 2014/6/24
 */
public class FieldCompareValidator implements ConstraintValidator<FieldCompare, Object> {
    private String firstFieldName;
    private String secondFieldName;
    private int compare;

    @Override
    public void initialize(FieldCompare constraintAnnotation) {
        this.firstFieldName = constraintAnnotation.first();
        this.secondFieldName = constraintAnnotation.second();
        this.compare = constraintAnnotation.compare();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            final Object firstObj = FieldUtils.readDeclaredField(value, this.firstFieldName, true);
            final Object secondObj = FieldUtils.readDeclaredField(value, this.secondFieldName, true);

            if (firstObj instanceof Comparable && secondObj instanceof Comparable) {
                @SuppressWarnings("unchecked")
                int result = ((Comparable<Object>) firstObj).compareTo(secondObj);

                if (compare > 0) {
                    return result > 0;
                } else if (compare < 0) {
                    return result < 0;
                } else {
                    return result == 0;
                }
            } else {
                throw new IllegalArgumentException("FieldCompareValidator field need implement Comparable interface");
            }

        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
