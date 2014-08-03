package com.glenware.spring.form;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import com.glenware.parkrunpb.form.PRCourse;

// http://musingsofaprogrammingaddict.blogspot.co.uk/2009/01/getting-started-with-jsr-303-beans.html
public class PRCourseTest {

	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void buildWithMandatoryInformation() {
		PRCourse prCourse = new PRCourse();

		assertNull(prCourse.getId());
		assertNull(prCourse.getPrName());
		assertNull(prCourse.getPrPredict());

	}

	@Test
	public void prCourseNameNotNull() {
		PRCourse prCourse = new PRCourse();

		assertNull(prCourse.getId());
		assertNull(prCourse.getPrName());
		assertNull(prCourse.getPrPredict());
		
        Set<ConstraintViolation<PRCourse>> constraintViolations = validator.validate(prCourse);
        
        assertEquals(3, constraintViolations.size());
        
        for (ConstraintViolation<PRCourse> prCourseConstraintValidation : constraintViolations) {
//        	System.out.println(prCourseConstraintValidation.toString() + " " + prCourseConstraintValidation.getMessage());
            if ( "url".equals( prCourseConstraintValidation.getPropertyPath().toString() ) ) {
                assertEquals("may not be empty", prCourseConstraintValidation.getMessage());
                assertEquals("url", prCourseConstraintValidation.getPropertyPath().toString());
             }
             else if ( "prName".equals( prCourseConstraintValidation.getPropertyPath().toString() ) ) 
             {
                 assertEquals("may not be empty", prCourseConstraintValidation.getMessage());
                 assertEquals("prName", prCourseConstraintValidation.getPropertyPath().toString());
             }
             else if ( "averageTime".equals( prCourseConstraintValidation.getPropertyPath().toString() ) )
             {
                 assertEquals("must be between 1 and 9223372036854775807", prCourseConstraintValidation.getMessage());
                 assertEquals("averageTime", prCourseConstraintValidation.getPropertyPath().toString());
             }
        }
	}
}