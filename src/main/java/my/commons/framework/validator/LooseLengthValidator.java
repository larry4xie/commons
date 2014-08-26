package my.commons.framework.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import my.commons.Assert;
import my.commons.framework.validator.constraints.LooseLength;

/**
 * not blank string, but not required(null or empty string)
 * 
 * @author xiegang
 * @since 2014/08/26
 * @see LooseLength
 */
public class LooseLengthValidator implements ConstraintValidator<LooseLength, CharSequence> {
	private int min;
	private int max;

	@Override
	public void initialize(LooseLength parameters) {
		min = parameters.min();
		max = parameters.max();
		
		Assert.isTrue(min >= 1);
		Assert.isTrue(max >= min);
	}

	@Override
	public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
		if (value == null || value.length() == 0) {
			return true;
		}
		
		int length = value.length();
		return length >= min && length <= max;
	}

}
