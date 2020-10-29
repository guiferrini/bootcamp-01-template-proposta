package com.guiferrini.proposta.propostas;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {ValidadorCpfCnpj.class})
@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface CpfCnpj {

    String message() default "Erro. Documento inválido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
