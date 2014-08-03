package com.glenware.parkrunpb.form;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.glenware.parkrunpb.controller.ParkrunController;

// https://stackoverflow.com/questions/5704743/unit-testing-jsr-303-validation-in-spring
public class PRRegionValidator implements Validator {

	private static final Logger logger = LoggerFactory
			.getLogger(PRRegionValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(PRPredict.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {

		PRPredict prPredict = (PRPredict) obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "courseId",
				"NotEmpty.prPredict.courseId", "You must specify a course.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mm",
				"NotNull.prPredict.mm",
				"You must set the number of minutes between 0s and 59s.");

		Integer mmInt = null;
		try {
			mmInt = Integer.parseInt(prPredict.getMm());
		} catch (NumberFormatException nfe) {
			errors.rejectValue("mm", "NotNull.prPredict.mm",
					"You must set the number of minutes between 0s and 59s.");
			return;
		}

		if (mmInt == null) {
			logger.info("mmInt is null " + mmInt);
		}

		if (mmInt < 0 || mmInt > 60) {
			errors.rejectValue("mm", "NotNull.prPredict.mm",
					"You must set the number of minutes between 0s and 59s.");
		}

		logger.info("" + mmInt);
		prPredict.setMmInt(mmInt);

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ss",
				"NotNull.prPredict.ss",
				"You must set the number of seconds between 0s and 59s.");

		Integer ssInt = null;
		try {
			ssInt = Integer.parseInt(prPredict.getSs());
		} catch (NumberFormatException nfe) {
			errors.rejectValue("ss", "NotNull.prPredict.ss",
					"You must set the number of seconds between 0s and 59s.");
			return;
		}

		if (ssInt < 0 || ssInt > 60) {
			errors.rejectValue("ss", "NotNull.prPredict.ss",
					"You must set the number of seconds between 0s and 59s.");
		}
		logger.info("" + ssInt);
		prPredict.setSsInt(ssInt);

	}

}