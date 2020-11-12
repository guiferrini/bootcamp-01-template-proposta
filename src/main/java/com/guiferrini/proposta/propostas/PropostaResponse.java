package com.guiferrini.proposta.propostas;

import com.guiferrini.proposta.servicoWeb.Enums.ResultadoComOuSem;
import com.guiferrini.proposta.servicoWeb.Enums.StatusAvaliacaoProposta;

// Retorno Proposta com info serviço de Cartão

public class PropostaResponse {

    private String documento;
    private String nome;
    private StatusAvaliacaoProposta status;
    private String idProposta;

    @Deprecated
    public PropostaResponse(){
    }

    public PropostaResponse(Proposta proposta) { //colocar tds valores
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.status = proposta.getStatus();
        this.idProposta = proposta.getId();
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

    public String getIdProposta() {
        return idProposta;
    }

}
