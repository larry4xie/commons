package my.commons.framework.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import my.commons.framework.validator.constraints.NotPattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @NotPattern
 */
public class NotPatternValidator implements ConstraintValidator<NotPattern, CharSequence> {
	private static final Logger log = LoggerFactory.getLogger(NotPatternValidator.class);

	private Pattern pattern;

	public void initialize(NotPattern parameters) {
		NotPattern.Flag[] flags = parameters.flags();
		int intFlag = 0;
		for ( NotPattern.Flag flag : flags ) {
			intFlag = intFlag | flag.getValue();
		}

		try {
			pattern = Pattern.compile( parameters.regexp(), intFlag );
		} catch ( PatternSyntaxException e ) {
			log.warn("NotPattern regexp invalid", e);
		}
	}

	public boolean isValid(CharSequence value, ConstraintValidatorContext constraintValidatorContext) {
		if ( value == null ) {
			return true;
		}
		Matcher m = pattern.matcher( value );
		return !m.matches();
	}
}
