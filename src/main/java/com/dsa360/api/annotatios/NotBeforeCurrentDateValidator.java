package com.dsa360.api.annotatios;

import java.sql.Date;
import java.time.LocalDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author RAM
 *
 */
public class NotBeforeCurrentDateValidator implements ConstraintValidator<NotBeforeCurrentDate, Date> {

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext context) {
        // Check if the date is not before the current date
    	LocalDate currentDate = LocalDate.now();
    	return date == null || !date.toLocalDate().isBefore(currentDate);
    }
}

