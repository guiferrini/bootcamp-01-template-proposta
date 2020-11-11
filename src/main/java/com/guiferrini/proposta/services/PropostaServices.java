package com.guiferrini.proposta.services;

import com.guiferrini.proposta.propostas.*;
import com.guiferrini.proposta.servicoWeb.OperacaoServicoWebFeign;
import com.guiferrini.proposta.servicoWeb.Request.SolicitacaoRequest;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class PropostaServices {

    private final Logger logger = LoggerFactory.getLogger(CadastraPropostaController.class);

    @PersistenceContext
    EntityManager entityManager;

    private Proposta proposta;

    @Autowired
    private ValidadorPropostaDuplicada validadorPropostaDuplicada;

    @Autowired
    private OperacaoServicoWebFeign operacaoServicoWebFeign;

    @Transactional
    public Proposta salvaProposta(PropostaRequest propostaRequest){

        var digitosFinaisDocumento = propostaRequest.getDocumento().substring(8); //ignora os 8 1° digitos
        Proposta obj = propostaRequest.toModel();

        entityManager.persist(obj); //propostaRepository.save(obj);
        logger.info("Proposta salva no BD - ultimos digitos do documento {}", digitosFinaisDocumento);


        var solicitacaoRequest = new SolicitacaoRequest(obj);
        //Tradando o Erro, COM_RESTRICAO...
        try{
            var resultadoAnalise = operacaoServicoWebFeign.analiseDadosFinanceiros(solicitacaoRequest);
            var propostaStatus = resultadoAnalise.getResultadoSolicitacao().toPropostaStatus();
            obj.setStatus(propostaStatus);

        } catch (FeignException exception){
            logger.warn("ERRO. FeignException - Consulta a Analise com COM_RESTRICÃO {}", exception.contentUTF8());
        }


        entityManager.merge(obj); //propostaRepository.save(obj);
        logger.info("Proposta alterada: COM ou SEM restrição no BD - ultimos digitos do documento {}", digitosFinaisDocumento);

        return obj;
    }
}
