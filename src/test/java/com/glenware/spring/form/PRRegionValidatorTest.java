package com.glenware.spring.form;

import static junit.framework.Assert.assertEquals;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.glenware.parkrunpb.form.PRPredict;
import com.glenware.parkrunpb.form.PRRegionValidator;

//http://www.programmingforliving.com/2012/10/howtodoobjectvalidationusingspring.html
//http://www.captaindebug.com/2011/07/writng-jsr-303-custom-constraint_26.html
public class PRRegionValidatorTest {

	@Test
	public void testValidationWithValidAddress() {

		// http://www.programmingforliving.com/2012/10/howtodoobjectvalidationusingspring.html
		Validator prRegionValidator = new PRRegionValidator();

		PRPredict prPredict = new PRPredict();

		BindException errors = new BindException(prPredict,
				PRPredict.class.getName());

		ValidationUtils.invokeValidator(prRegionValidator, prPredict, errors);

		if (errors.hasErrors()) {
			System.out.println("Errors: " + errors.getErrorCount());
			for (ObjectError error : errors.getAllErrors()) {
				assertEquals(
						"You must set the number of minutes between 0s and 59s.",
						error.getDefaultMessage());
			}
		}

		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////
		prPredict.setMm("a");

		errors = new BindException(prPredict, PRPredict.class.getName());

		ValidationUtils.invokeValidator(prRegionValidator, prPredict, errors);

		if (errors.hasErrors()) {
			System.out.println("Errors: " + errors.getErrorCount());
			for (ObjectError error : errors.getAllErrors()) {
				assertEquals(
						"You must set the number of minutes between 0s and 59s.",
						error.getDefaultMessage());
			}
		}

	}

}