package com.guiferrini.proposta.propostas;

import com.guiferrini.proposta.servicoWeb.Enums.StatusAvaliacaoProposta;

public class PropostaResponse {

    private String documento;
    private String nome;
    private StatusAvaliacaoProposta status;

    public PropostaResponse(Proposta proposta){
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.status = proposta.getStatus();
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
}
