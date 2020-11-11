package com.guiferrini.proposta.servicoWeb.Request;

import com.guiferrini.proposta.servicoWeb.Entity.Cartao;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class CartaoRequest {

    private UUID idCartaEmitido;

    private LocalDateTime emitidoEm;

    private String titular;

    private BigDecimal limite;

    public UUID getIdCartaEmitido() {
        return idCartaEmitido;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public Cartao toModel(){
        return new Cartao(idCartaEmitido, emitidoEm, titular);
    }



}
