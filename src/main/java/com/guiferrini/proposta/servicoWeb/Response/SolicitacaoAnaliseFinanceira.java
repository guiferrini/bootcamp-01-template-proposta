package com.guiferrini.proposta.servicoWeb.Response;

import com.guiferrini.proposta.servicoWeb.Enums.ResultadoComOuSem;
import com.guiferrini.proposta.servicoWeb.Enums.StatusAvaliacaoProposta;

// Valida se Solicitante possui restrição financeira

public class SolicitacaoAnaliseFinanceira {

    private String documento;
    private String nome;
    private ResultadoComOuSem resultadoSolicitacao;
    private String idProposta;

    @Deprecated
    public SolicitacaoAnaliseFinanceira(){
    }

    public SolicitacaoAnaliseFinanceira(String documento, String nome, ResultadoComOuSem resultadoSolicitacao, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ResultadoComOuSem getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public void setResultadoSolicitacao(ResultadoComOuSem resultadoSolicitacao) {
        this.resultadoSolicitacao = resultadoSolicitacao;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public void setIdProposta(String idProposta) {
        this.idProposta = idProposta;
    }
}
