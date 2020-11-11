package com.guiferrini.proposta.propostas;

import com.guiferrini.proposta.servicoWeb.Enums.ResultadoComOuSem;
import com.guiferrini.proposta.servicoWeb.Enums.StatusAvaliacaoProposta;

public class PropostaDeFatoResponse {

    private String documento;
    private String nome;
    private StatusAvaliacaoProposta status;
    private String idProposta;

    @Deprecated
    public PropostaDeFatoResponse(){
    }

    public PropostaDeFatoResponse(Proposta proposta) { //colocar tds valores
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
