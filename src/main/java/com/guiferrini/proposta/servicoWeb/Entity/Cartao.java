package com.guiferrini.proposta.servicoWeb.Entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name="cartao")
@EnableScheduling
public class Cartao {

    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @NotNull
    private UUID idCartaoEmitido;

    @CreationTimestamp
    private LocalDateTime emitidoEm;

    @NotBlank
    private String titular;

    @Deprecated
    public Cartao(){
    }

    public Cartao(@NotNull UUID idCartaoEmitido,
                  LocalDateTime emitidoEm,
                  @NotBlank String titular) {
        this.idCartaoEmitido = idCartaoEmitido;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
    }

    public String getId() {
        return id;
    }

    //    @Override
//    public String toString() {
//        return "Cartao{" +
//                "id='" + id + '\'' +
//                ", idCartaoEmitido=" + idCartaoEmitido +
//                ", emitidoEm=" + emitidoEm +
//                ", titular='" + titular + '\'' +
//                '}';
//    }
}
