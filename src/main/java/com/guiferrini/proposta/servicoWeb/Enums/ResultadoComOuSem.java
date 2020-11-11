package com.guiferrini.proposta.servicoWeb.Enums;

public enum ResultadoComOuSem {

    COM_RESTRICAO{
        @Override
        public StatusAvaliacaoProposta toPropostaStatus() {
            return StatusAvaliacaoProposta.NAO_ELEGIVEL;
        }
    },
    SEM_RESTRICAO{
        @Override
        public StatusAvaliacaoProposta toPropostaStatus() {
            return StatusAvaliacaoProposta.ELEGIVEL;
        }
    };

    public abstract StatusAvaliacaoProposta toPropostaStatus(); //METODO Q NÃO É IMPLEMENTADO...

// Alternativa/outra forma de fazer...
//    COM_RESTRICAO(StatusAvaliacaoProposta.NAO_ELEGIVEL),
//    SEM_RESTRICAO(StatusAvaliacaoProposta.ELEGIVEL);
//
//    private StatusAvaliacaoProposta statusAvaliacaoProposta;
//
//    private ResultadoComOuSem(StatusAvaliacaoProposta statusAvaliacaoProposta) {
//        this.statusAvaliacaoProposta = statusAvaliacaoProposta;
//    }
//
//    public StatusAvaliacaoProposta getStatusAvaliacaoProposta() {
//        return statusAvaliacaoProposta;
//    }


}
