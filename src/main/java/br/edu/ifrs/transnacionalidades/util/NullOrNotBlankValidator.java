package br.edu.ifrs.transnacionalidades.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlank, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return value == null || !value.trim().isEmpty();
    }
}