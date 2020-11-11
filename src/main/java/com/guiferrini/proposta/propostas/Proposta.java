package com.guiferrini.proposta.propostas;

import com.guiferrini.proposta.servicoWeb.Entity.Cartao;
import com.guiferrini.proposta.servicoWeb.Enums.ResultadoComOuSem;
import com.guiferrini.proposta.servicoWeb.Enums.StatusAvaliacaoProposta;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

import static com.guiferrini.proposta.servicoWeb.Enums.ResultadoComOuSem.COM_RESTRICAO;
import static com.guiferrini.proposta.servicoWeb.Enums.ResultadoComOuSem.SEM_RESTRICAO;
import static com.guiferrini.proposta.servicoWeb.Enums.StatusAvaliacaoProposta.ELEGIVEL;
import static com.guiferrini.proposta.servicoWeb.Enums.StatusAvaliacaoProposta.NAO_ELEGIVEL;

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

    @Enumerated(EnumType.STRING) //informa q eh um Emun
    private StatusAvaliacaoProposta status ;

    @OneToOne(cascade = CascadeType.ALL)
    private Cartao cartao;

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
        this.status = StatusAvaliacaoProposta.AGUARDANDO_AVALIACAO;
    }

    public String getId() {
        //Assert.notNull(id,"O objeto precisa estar salvo para invocar o getId");
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public StatusAvaliacaoProposta getStatus() {
        return status;
    }

    public Cartao getCartao() {
        return cartao;
    }

//    NÃO UTILIZAR - BOAS PRATIOCAS EST DENTRO DO 'RESULTADOCOMOUSEM'
//    public void aplicaResultadoAnalise(ResultadoComOuSem resultadoComOuSem) {
//        System.out.println(resultadoComOuSem);
//        if (resultadoComOuSem == COM_RESTRICAO) {
//            setStatus(NAO_ELEGIVEL);
//
//        }else if (resultadoComOuSem == SEM_RESTRICAO) {
//            setStatus(ELEGIVEL);
//
//        }
//
//        //com erro... ?! Validar
//        throw new IllegalArgumentException("Argumento inválido para a atualização do status da proposta!");
//    }

    public void setStatus(StatusAvaliacaoProposta status) {
        this.status = status;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public void adicionaCartao(Cartao cartao){
        this.cartao = cartao;
    }

//    @Override
//    public String toString() {
//        return "Proposta{" +
//                "id='" + id + '\'' +
//                ", documento='" + documento + '\'' +
//                ", email='" + email + '\'' +
//                ", nome='" + nome + '\'' +
//                ", endereco='" + endereco + '\'' +
//                ", salario=" + salario +
//                ", status=" + status +
//                ", cartao=" + cartao +
//                '}';
//    }
}
