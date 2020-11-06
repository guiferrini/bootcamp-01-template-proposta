package com.guiferrini.proposta.servicoWeb;

import com.guiferrini.proposta.propostas.Proposta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SolicitacaoRequest {

    @NotBlank(message = "Documento é obrigatorio")
    private String documento;

    @NotBlank(message = "Documento é obrigatorio")
    private String nome;

    @NotBlank(message = "Documento é obrigatorio")
    private String idProposta;

    @Deprecated
    public SolicitacaoRequest(){}

    public SolicitacaoRequest(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdProposta(String idProposta) {
        this.idProposta = idProposta;
    }
}
