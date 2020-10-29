package com.guiferrini.proposta.propostas;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidadorCpfCnpj implements ConstraintValidator<CpfCnpj, String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if(value == null){
            return true;
        }

        CPFValidator cpfValidator = new CPFValidator();
        CNPJValidator conjValidator = new CNPJValidator();

        cpfValidator.initialize(null); //tem q iniciar null
        conjValidator.initialize(null);

        return cpfValidator.isValid(value, context) ||
                conjValidator.isValid(value, context);
    }
}
