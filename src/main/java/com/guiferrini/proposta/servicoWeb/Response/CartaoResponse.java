package com.guiferrini.proposta.servicoWeb.Response;

import com.guiferrini.proposta.servicoWeb.Entity.Cartao;

import java.time.LocalDateTime;
import java.util.UUID;

public class CartaoResponse {

    private UUID id;
    private LocalDateTime emitidoEm;
    private String titular;
    private String idProposta;

    @Deprecated
    public CartaoResponse(){
    }

    public CartaoResponse(UUID id, LocalDateTime emitidoEm, String titular, String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.idProposta = idProposta;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public void setEmitidoEm(LocalDateTime emitidoEm) {
        this.emitidoEm = emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public void setIdProposta(String idProposta) {
        this.idProposta = idProposta;
    }

    public Cartao toModel() {
        return new Cartao(this.id, this.emitidoEm, this.titular) ;
    }
}
