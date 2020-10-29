package com.guiferrini.proposta.propostas;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name="proposta")
public class Proposta {

    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

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
    public Proposta(){
    }

    public Proposta(@NotBlank(message = "Documento é obrigatorio") String documento,
                    @NotBlank(message = "Email é obrigatorio") @Email(message = "Favor inserir um Email válido") String email,
                    @NotBlank(message = "Nome é obrigatorio") String nome,
                    @NotBlank(message = "Endereço é obrigatorio") String endereco,
                    @NotNull(message = "Salário é obrigatorio") BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public String getId() {
        //Assert.notNull(id,"O objeto precisa estar salvo para invocar o getId");
        return id;
    }
}
