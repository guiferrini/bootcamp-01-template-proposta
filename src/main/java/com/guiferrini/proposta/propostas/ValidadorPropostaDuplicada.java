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
                //Antes do nome da variável sempre terá que ter : (dois pontos) para identificar que é uma variável.
                "select p.documento from Proposta p where p.documento = :documento") //:_ sig que depois é uma variável
                .setParameter("documento", propostaRequest.getDocumento())
                .getResultList().isEmpty();
    }
}
