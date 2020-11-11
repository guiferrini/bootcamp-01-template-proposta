package com.guiferrini.proposta.servicoWeb.Request;

import com.guiferrini.proposta.servicoWeb.Entity.Cartao;
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
