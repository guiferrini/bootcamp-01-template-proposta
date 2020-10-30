package com.guiferrini.proposta.propostas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class ValidadorPropostaDuplicada {

    @Autowired
    EntityManager entityManager;

    public boolean validadoDocumento(PropostaRequest propostaRequest){

        return entityManager.createQuery(
                "select p.documento from Proposta p where p.documento = :documento") //estudar p._ e :_ -> querry!!!
                .setParameter("documento", propostaRequest.getDocumento())
                .getResultList().isEmpty();
    }
}
