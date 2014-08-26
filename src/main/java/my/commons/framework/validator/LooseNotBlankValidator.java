package my.commons.framework.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import my.commons.framework.validator.constraints.LooseNotBlank;

/**
 * not blank string, but not required
 * 
 * @author xiegang
 * @since 2014/08/26
 * @see LooseNotBlank
 */
public class LooseNotBlankValidator implements ConstraintValidator<LooseNotBlank, CharSequence> {

	@Override
	public void initialize(LooseNotBlank constraintAnnotation) {
	}

	@Override
	public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		
		return StringUtils.isNotBlank(value);
	}

}
