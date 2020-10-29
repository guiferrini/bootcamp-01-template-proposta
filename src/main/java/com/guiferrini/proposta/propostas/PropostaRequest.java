package com.guiferrini.proposta.propostas;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaRequest {

    @NotBlank(message = "Documento é obrigatorio")
    @CpfCnpj
    private String documento;

    @NotBlank(message = "Email é obrigatorio")
    @Email(message = "Favor inserir um Email válido")
    private String email;

    @NotBlank(message = "Nome é obrigatorio")
    private String nome;

    @NotBlank(message = "Endereço é obrigatorio")
    private String endereco;

    @NotNull(message = "Salário é obrigatorio")
    @Positive
    private BigDecimal salario;

    @Deprecated
    public PropostaRequest(){
    }

    public PropostaRequest(@NotBlank(message = "Documento é obrigatorio") String documento,
                           @NotBlank(message = "Email é obrigatorio") @Email(message = "Favor inserir um Email válido") String email,
                           @NotBlank(message = "Nome é obrigatorio") String nome,
                           @NotBlank(message = "Endereço é obrigatorio") String endereco,
                           @NotNull(message = "Salário é obrigatorio") @Positive BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Proposta toModel(){

        Proposta proposta = new Proposta(documento, email, nome, endereco, salario);

        return proposta;
    }
}
