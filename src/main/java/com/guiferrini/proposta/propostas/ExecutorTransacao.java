package com.guiferrini.proposta.propostas;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component //p usar @Autoride, sem usao com new...
public class ExecutorTransacao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public <T> T salvaEcomita(T t){
        entityManager.persist(t);
        return t;
    }

    @Transactional
    public <T> T atualizaEComita(T t){
        return entityManager.merge(t);
    }

}
