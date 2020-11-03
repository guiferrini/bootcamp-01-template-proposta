package com.guiferrini.proposta;

import com.guiferrini.proposta.propostas.ValidadorCpfCnpj;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ValidadorCpjCnpjTest {

    @Test
    @DisplayName("Não aceitar quando não for CPF ou CNPJ")
    void test() throws Exception{
        ValidadorCpfCnpj validadorCpfCnpj = new ValidadorCpfCnpj();
        boolean seValido = validadorCpfCnpj.isValid("", null);
        Assertions.assertFalse(seValido);
    }

    @Test
    @DisplayName("Aceitar quando é CPF")
    void testAceitaCpf() throws Exception{
        ValidadorCpfCnpj validadorCpfCnpj = new ValidadorCpfCnpj();
        boolean validoCPF = validadorCpfCnpj.isValid("550.581.630-47", null);
        Assertions.assertTrue(validoCPF);
    }

    @Test
    @DisplayName("Aceitar quando é CNPJ")
    void testeAceitaCnpj() throws Exception{
        ValidadorCpfCnpj validadorCpfCnpj = new ValidadorCpfCnpj();
        boolean validoCNPJ = validadorCpfCnpj.isValid("54.439.813/0001-01", null);
        Assertions.assertTrue(validoCNPJ);
    }

}
