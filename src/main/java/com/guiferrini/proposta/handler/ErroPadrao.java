package com.guiferrini.proposta.handler;

import java.util.Collection;

public class ErroPadrao {

    private Collection<String> mensagem;

    @Deprecated
    public ErroPadrao(){
    }

    public ErroPadrao(Collection<String> mensagem) {
        this.mensagem = mensagem;
    }

    public Collection<String> getMensagem() {
        return mensagem;
    }

    public void setMensagem(Collection<String> mensagem) {
        this.mensagem = mensagem;
    }
}
