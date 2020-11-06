package com.guiferrini.proposta.servicoWeb.Request;

import com.guiferrini.proposta.servicoWeb.Enums.ResultadoComOuSem;

public class ResultadoAnaliseRequest {

    private ResultadoComOuSem resultadoComOuSem;

    @Deprecated
    public ResultadoAnaliseRequest(){
    }

    public ResultadoComOuSem getResultadoComOuSem() {
        return resultadoComOuSem;
    }

    public void setResultadoComOuSem(ResultadoComOuSem resultadoComOuSem) {
        this.resultadoComOuSem = resultadoComOuSem;
    }
}
